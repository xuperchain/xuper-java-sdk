package com.baidu.xuper.crypto.gm.bip39;

import com.baidu.xuper.crypto.gm.hash.Hash;
import com.baidu.xuper.crypto.wordlists.WordList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class MnemonicCode {
    private final WordList wordList;

    public MnemonicCode(final WordList wordList) {
        this.wordList = wordList;
    }

    /**
     * @param entropy 随机熵。
     * @return 助记词。
     */
    public String createMnemonic(byte[] entropy) {
        List<String> words = genMnemonic(entropy);
        String mnemonic = "";
        for (int i = 0; i < words.size(); i++) {
            mnemonic = mnemonic + words.get(i);
            if (i != words.size() - 1) {
                mnemonic += " ";
            }
        }
        return mnemonic;
    }

    /**
     * @param mnemonic 助记词。
     * @return 随机熵。
     */
    public byte[] getEntropyFromMnemonic(String[] mnemonic) {
        if (!this.checkMnemonic(mnemonic)) {
            throw new RuntimeException("checkMnemonic failed");
        }

        int mnemonicBitSize = mnemonic.length * 11;
        int checksumBitSize = mnemonicBitSize % 32;
        BigInteger b = new BigInteger("0");

        for (String word : mnemonic) {
            byte[] wordBytes = new byte[2];
            int index = this.wordList.getIndex(word);
            int ss = index >> 8;
            wordBytes[0] = (byte) ss;
            wordBytes[1] = (byte) index;

            b = b.multiply(new BigInteger("2048"));
            b = b.or(new BigInteger(wordBytes));
        }

        BigInteger two = new BigInteger("2");
        BigInteger checksumModulo = two.pow(checksumBitSize);

        BigInteger entropy = b.divide(checksumModulo);
        int entropyByteSize = (mnemonicBitSize - checksumBitSize) / 8;
        int fullByteSize = entropyByteSize + 1;

        byte[] entropyBytes = bytesPad(toByteArray(entropy), entropyByteSize);
        byte[] entropyWithChecksumBytes = bytesPad(toByteArray(b), fullByteSize);
        byte[] addChecksumEntropyBytes = addChecksum(entropyBytes);
        byte[] newEntropyWithChecksumBytes = bytesPad(addChecksumEntropyBytes, fullByteSize);

        if (!Arrays.equals(entropyWithChecksumBytes, newEntropyWithChecksumBytes)) {
            throw new RuntimeException("The checksum within the Mnemonic sentence incorrect.");
        }
        return toByteArray(entropy);
    }

    /**
     * @param mnemonic 助记词。
     * @return 密码学算法标志位。
     */
    public int getCryptographyFromMnemonic(String[] mnemonic) {
        byte[] entropy = getEntropyFromMnemonic(mnemonic);
        byte[] tagByte = new byte[1];
        System.arraycopy(entropy, entropy.length - 1, tagByte, 0, 1);
        BigInteger tagInt = new BigInteger(1, tagByte);
        tagInt = tagInt.divide(new BigInteger("16"));
        BigInteger cryptographyInt = tagInt.and(new BigInteger("15"));

        byte[] cryptographyByte = toByteArray(cryptographyInt);
        if (cryptographyByte.length == 0) {
            throw new RuntimeException("invalid cryptographyByte length");
        }
        return cryptographyByte[0];
    }

    /**
     * @param words      助记词。
     * @param passphrase 密码。
     * @return seed
     */
    public static byte[] toSeed(String words, String passphrase) {
        String salt = "mnemonic" + passphrase;
        return PBKDF2SHA512.derive(words, salt, 2048, 40);
    }

    private byte[] addChecksum(byte[] data) {
        byte[] hashByte = Hash.hashUsingSM3(data);
        byte firstChecksumByte = hashByte[1];
        int checksumBitLength = data.length / 4;
        BigInteger dataBigInt = new BigInteger(1, data);

        for (int i = 0; i < checksumBitLength; i++) {
            dataBigInt = dataBigInt.multiply(new BigInteger("2"));
            if ((firstChecksumByte & (1 << (7 - i))) > 0) {
                dataBigInt = dataBigInt.or(BigInteger.ONE);
            }
        }
        return toByteArray(dataBigInt);
    }

    private byte[] bytesPad(byte[] data, int length) {
        if (length == data.length) {
            return data;
        }

        byte[] result = new byte[length];
        System.arraycopy(data, 0, result, 1, data.length);
        return result;
    }

    private byte[] toByteArray(BigInteger bi) {
        byte[] array = bi.toByteArray();
        if (array[0] == 0) {
            byte[] tmp = new byte[array.length - 1];
            System.arraycopy(array, 1, tmp, 0, tmp.length);
            array = tmp;
        }
        return array;
    }

    private boolean checkMnemonic(String[] mnemonic) {
        List<String> validLength = asList("12", "15", "18", "21", "24");
        if (!validLength.contains(String.valueOf(mnemonic.length))) {
            return false;
        }

        for (String s : mnemonic) {
            if (!this.wordList.has(s)) {
                return false;
            }
        }
        return true;
    }

    private List<String> genMnemonic(byte[] entropy) {
        int entropyBitLength = entropy.length * 8;
        validateEntropyBitSize(entropyBitLength);
        int checksumBitLength = entropyBitLength / 32;
        int sentenceLength = (entropyBitLength + checksumBitLength) / 11;
        byte[] entropyWithChecksum = addChecksum(entropy);
        BigInteger entropyInt = new BigInteger(1, entropyWithChecksum);

        String[] words = new String[sentenceLength];
        BigInteger word = new BigInteger("0");
        for (int i = sentenceLength - 1; i >= 0; i--) {
            word = entropyInt.and(new BigInteger("2047"));
            entropyInt = entropyInt.divide(new BigInteger("2048"));
            byte[] wordBytes = bytesPad(toByteArray(word), 2);
            int ii = byteBE2Int(wordBytes);
            words[i] = this.wordList.getWord(ii);
        }

        return new ArrayList<>(asList(words));
    }

    private int byteBE2Int(byte[] bytes) {
        int result;
        if (bytes.length == 1) {
            result = bytes[0] & 0xFF;
        } else {
            result = bytes[0] & 0xFF;
            result = (result << 8) | (bytes[1] & 0xff);
        }
        return result;
    }

    private void validateEntropyBitSize(int bitSize) {
        if (bitSize % 32 != 0 || bitSize < 128 || bitSize > 256) {
            throw new RuntimeException("invalid bitSize");
        }
    }
}
