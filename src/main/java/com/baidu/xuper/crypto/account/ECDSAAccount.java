package com.baidu.xuper.crypto.account;

import com.baidu.xuper.crypto.AES;
import com.baidu.xuper.crypto.Base58;
import com.baidu.xuper.crypto.ECKeyPair;
import com.baidu.xuper.crypto.Hash;
import com.baidu.xuper.crypto.bip39.EntropyGenerator;
import com.baidu.xuper.crypto.bip39.MnemonicCode;
import com.baidu.xuper.crypto.bip39.WordList;
import com.baidu.xuper.crypto.bip39.wordlists.Chinese;
import com.baidu.xuper.crypto.bip39.wordlists.English;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class ECDSAAccount {
    public byte[] entropyByte;
    public String mnemonic;
    public String jsonPrivateKey;
    public String jsonPublicKey;
    public String address;
    public ECKeyPair ecKeyPair;

    /**
     * @param strength 强度。
     * @param language 助记词语言。
     */
    public void createAccountWithMnemonic(int strength, int language) {
        int bitSize = calcBitSize(strength);
        byte[] entropyBytes = EntropyGenerator.generateEntropy(bitSize);

        WordList wl = getWordList(language);
        MnemonicCode mg = new MnemonicCode(wl);

        byte[] bb = new byte[]{1};
        BigInteger cryptographyInt = new BigInteger(bb);

        BigInteger tagInt = cryptographyInt.and(new BigInteger("15"));
        tagInt = tagInt.multiply(new BigInteger("16"));

        byte[] reservedBit = new byte[]{0};

        BigInteger reservedInt = new BigInteger(reservedBit);
        reservedInt = reservedInt.and(new BigInteger("15"));
        tagInt = tagInt.or(reservedInt);

        byte[] tagByte = bytesPad(tagInt.toByteArray(), 1);
        byte[] newEntropyByteSlice = new byte[entropyBytes.length + tagByte.length];
        System.arraycopy(entropyBytes, 0, newEntropyByteSlice, 0, entropyBytes.length);
        System.arraycopy(tagByte, 0, newEntropyByteSlice, entropyBytes.length, tagByte.length);
        String mnemonic = mg.createMnemonic(newEntropyByteSlice);

        createByMnemonic(mnemonic, language);
    }

    /**
     * @param mnemonic 助记词。
     * @param language 助记词语言。
     */
    public void createByMnemonic(String mnemonic, int language) {
        MnemonicCode mg = new MnemonicCode(getWordList(language));
        int cryptography = mg.getCryptographyFromMnemonic(mnemonic.split(" "));
        if (cryptography != 1) {
            throw new RuntimeException("Only cryptoGraphy NIST[1] is supported in this version.");
        }

        byte[] seed = MnemonicCode.toSeed(mnemonic, "jingbo is handsome!");// go 版本的密码就是这个，所以保存一致了。
        ECKeyPair e = ECKeyPair.create(seed);
        byte[] pubKey = e.getPublicKey().getEncoded(false);
        byte[] hash = Hash.ripeMD128(Hash.sha256(pubKey));

        this.jsonPublicKey = e.getJSONPublicKey();
        this.address = Base58.encodeChecked(1, hash);
        this.jsonPrivateKey = e.getJSONPrivateKey();
        this.entropyByte = seed;
        this.mnemonic = mnemonic;
        this.ecKeyPair = e;
    }

    /**
     * @param path   保存路径。
     * @param passwd 密码。
     */
    public void saveToFile(String path, String passwd) {
        mkdir(path);
        if (!path.endsWith("/")) {
            path += "/";
        }
        byte[] newPW = Hash.doubleSha256(passwd.getBytes());
        byte[] encryptContent = AES.encrypt(this.jsonPrivateKey.getBytes(), newPW);
        writeFileUsingFileName(path + "private.key", encryptContent);
    }

    private void writeFileUsingFileName(String fileName, byte[] content) {
        Encoder encoder =  Base64.getEncoder();
        String encoded = encoder.encodeToString(content);
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            writer.write(encoded);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mkdir(String path) {
        File file = new File(path);
        if (file.exists() || file.isDirectory()) {
            throw new RuntimeException("dir exist");
        }

        if (!file.mkdir()) {
            throw new RuntimeException("mkdir failed.");
        }
    }

    public static byte[] getBinaryECDSAPrivateKey(String path, String passwd) {
        String fileName = path + "/private.key";
        byte[] content = readFileWithBASE64Decode(fileName);
        byte[] newPasswd = Hash.doubleSha256(passwd.getBytes());
        return AES.decrypt(content, newPasswd);
    }

    private int calcBitSize(int strength) {
        switch (strength) {
            case 1:
                return 120;
            case 2:
                return 184;
            case 3:
                return 248;
            default:
                throw new IllegalStateException("Unexpected value: " + strength);
        }
    }

    private WordList getWordList(int language) {
        switch (language) {
            case 1:
                return Chinese.INSTANCE;
            case 2:
                return English.INSTANCE;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
    }

    private static byte[] readFileWithBASE64Decode(String path) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));

            Decoder decoder  = Base64.getDecoder();
            //BASE64Decoder d =decoder new BASE64Decoder();
           // return decoder.decode(new String(fileBytes));
            return decoder.decode(fileBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] bytesPad(byte[] data, int length) {
        byte[] result = new byte[length];
        int index = length - 1;
        for (int i = data.length - 1; i >= 0; i--) {
            result[index] = data[i];
            index--;
        }
        return result;
    }
}
