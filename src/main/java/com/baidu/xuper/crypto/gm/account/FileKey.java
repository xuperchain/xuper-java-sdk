package com.baidu.xuper.crypto.gm.account;

import com.baidu.xuper.crypto.Common;
import com.baidu.xuper.crypto.account.ECDSAAccount;
import com.baidu.xuper.crypto.wordlists.WordList;
import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import com.baidu.xuper.crypto.Base58;
import com.baidu.xuper.crypto.gm.bip39.MnemonicCode;
import com.baidu.xuper.crypto.gm.hash.Hash;
import com.baidu.xuper.crypto.gm.sign.SM2KeyPair;
import com.baidu.xuper.crypto.gm.hdWallet.Rand;

import java.math.BigInteger;

public class FileKey {
    /**
     * @param mnemonic 助记词。
     * @param language 助记词语言。
     */
    public static ECDSAAccount generateAccountByMnemonic(String mnemonic, int language) {
        MnemonicCode mg = new MnemonicCode(Common.getWordList(language));
        int cryptography = mg.getCryptographyFromMnemonic(mnemonic.split(" "));
        if (cryptography != 2) {
            throw new RuntimeException("Only cryptoGraphy NIST[1] is supported in this version.");
        }

        // todo: go 版本的密码就是这个，所以保存一致了
        byte[] seed = MnemonicCode.toSeed(mnemonic, "jingbo is handsome!");

        SM2KeyPair sm2KeyPair = SM2KeyPair.create(seed);
        byte[] pubKey = sm2KeyPair.getPublicKey().getEncoded(false);
        byte[] hash = Hash.ripeMD160(Hash.hashUsingSM3(pubKey));

        ECDSAAccount account = new ECDSAAccount();

        account.jsonPublicKey = sm2KeyPair.getJSONPublicKey();
        account.address = Base58.encodeChecked(Common.gm, hash);
        account.jsonPrivateKey = sm2KeyPair.getJSONPrivateKey();
        account.entropyByte = seed;
        account.mnemonic = mnemonic;
        ECKeyPair ecKeyPair = new ECKeyPair();
        ecKeyPair.privateKey = sm2KeyPair.getPrivateKey();
        ecKeyPair.publicKey = sm2KeyPair.getPublicKey();
        ecKeyPair.jsonPrivateKey = sm2KeyPair.getJSONPrivateKey();
        ecKeyPair.jsonPublicKey = sm2KeyPair.getJSONPublicKey();
        account.ecKeyPair = ecKeyPair;

        return account;
    }

    /**
     * @param language     语言
     * @param strength     强度
     * @param cryptography 版本号
     * @return ECDSAAccount 助记词、私钥的json、公钥的json、钱包地址
     */
    public static ECDSAAccount createNewAccountWithMnemonic(Integer language, Integer strength, Integer cryptography) {

        // 根据强度来判断随机数长度
        // 预留出8个bit用来指定当使用助记词时来恢复私钥时所需要的密码学算法组合
        int bitSize = calcBitSize(strength);
        // 产生随机熵
        byte[] entropyBytes = Rand.generateEntropy(bitSize);

        // TODO: 校验密码学算法是否得到支持

        // 把带有密码学标记位的byte数组转化为一个bigint，方便后续做比特位运算（主要是移位操作）
        byte[] bb = new byte[]{2};
        BigInteger cryptographyInt = new BigInteger(bb);
        // 创建综合标记位， // 综合标记位获取密码学标记位最右边的4个比特
        BigInteger tagInt = cryptographyInt.and(new BigInteger("15"));
        // 将综合标记位左移4个比特
        tagInt = tagInt.multiply(new BigInteger("16"));

        // 定义预留标记位
        byte[] reservedBit = new byte[]{0};

        BigInteger reservedInt = new BigInteger(reservedBit);
        // 综合标记位获取预留标记位最右边的4个比特
        reservedInt = reservedInt.and(new BigInteger("15"));

        // 合并密码学标记位和预留标记位
        tagInt = tagInt.or(reservedInt);

        // 把比特补齐为 1个字节
        byte[] tagByte = Common.bytesPad(tagInt.toByteArray(), 1);

        byte[] newEntropyByteSlice = new byte[entropyBytes.length + tagByte.length];
        System.arraycopy(entropyBytes, 0, newEntropyByteSlice, 0, entropyBytes.length);
        System.arraycopy(tagByte, 0, newEntropyByteSlice, entropyBytes.length, tagByte.length);

        WordList wordList = Common.getWordList(language);
        MnemonicCode mg = new MnemonicCode(wordList);
        String mnemonic = mg.createMnemonic(newEntropyByteSlice);

        return FileKey.generateAccountByMnemonic(mnemonic, language);
    }

    private static int calcBitSize(int strength) {
        switch (strength) {
            case 1:
                // 弱 12个助记词
                return 120;
            case 2:
                // 中 18个助记词
                return 184;
            case 3:
                // 高 24个助记词
                return 248;
            default:
                // 不支持的语言类型
                throw new IllegalStateException("Unexpected value: " + strength);
        }
    }


}
