package com.baidu.xuper.crypto.gm;

import com.baidu.xuper.crypto.Base58;
import com.baidu.xuper.crypto.Common;
import com.baidu.xuper.crypto.Crypto;
import com.baidu.xuper.crypto.account.ECDSAInfo;
import com.baidu.xuper.crypto.gm.sign.SM2KeyPair;
import com.baidu.xuper.crypto.xchain.bip39.MnemonicCode;
import com.baidu.xuper.crypto.wordlists.WordList;
import com.baidu.xuper.crypto.gm.account.FileKey;
import com.baidu.xuper.crypto.gm.hash.Hash;
import com.baidu.xuper.crypto.gm.hdWallet.Rand;
import com.baidu.xuper.crypto.account.ECDSAAccount;
import com.baidu.xuper.crypto.gm.sign.Ecc;
import com.baidu.xuper.crypto.AES;
import com.baidu.xuper.crypto.gm.hdWallet.Key;
import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

public class GmCryptoClient implements Crypto {
    /**
     * 产生随机熵
     *
     * @param bitSize
     * @return byte[]
     */
    @Override
    public byte[] generateEntropy(int bitSize) {
        return Rand.generateEntropy(bitSize);
    }

    /**
     * 将随机熵转为助记词
     *
     * @param entropy
     * @param language
     * @return Strings
     */
    @Override
    public String generateMnemonic(byte[] entropy, Integer language) {
        WordList wordList = Common.getWordList(language);
        MnemonicCode mc = new MnemonicCode(wordList);
        return mc.createMnemonic(entropy);
    }

    /**
     * 创建含有助记词的新的账户，返回的字段：（助记词、私钥的json、公钥的json、钱包地址） as ECDSAAccount，以及可能的错误信息
     *
     * @param language
     * @param strength
     * @return
     */
    @Override
    public ECDSAAccount createNewAccountWithMnemonic(Integer language, Integer strength) {
        return FileKey.createNewAccountWithMnemonic(language, strength, 1);
    }

    /**
     * 从助记词恢复钱包账户
     * TODO: 后续可以从助记词中识别出语言类型
     *
     * @param mnemonic
     * @param language
     * @return
     */
    @Override
    public ECDSAAccount retrieveAccountByMnemonic(String mnemonic, Integer language) {
        return FileKey.generateAccountByMnemonic(mnemonic, language);
    }

    /**
     * 从助记词恢复钱包账户，并用支付密码加密私钥后存在本地，
     * 返回的字段：（随机熵（供其他钱包软件推导出私钥）、助记词、私钥的json、公钥的json、钱包地址） as ECDSAAccount，以及可能的错误信息
     *
     * @param path
     * @param language
     * @param mnemonic
     * @param password
     * @return
     */
    @Override
    public ECDSAInfo retrieveAccountByMnemonicAndSavePrivKey(String path, Integer language, String mnemonic, String password) {
        return Key.createAndSaveSecretKeyWithMnemonic(path, language, mnemonic, password);
    }

    /**
     * 使用支付密码从导出的私钥文件读取私钥
     *
     * @param path
     * @param password
     * @return
     */
    @Override
    public byte[] getEcdsaPrivateKeyFromFileByPassword(String path, String password) {
        String fileName = path + "/private.key";
        byte[] content = Common.readFileWithBASE64Decode(fileName);
        byte[] newPasswd = Hash.hashUsingSM3(password.getBytes(), 0, password.getBytes().length);
        return AES.decrypt(content, newPasswd);
    }

    /**
     * 使用ECC私钥来签名
     *
     * @param msg
     * @param privateKey
     * @return
     */
    @Override
    public byte[] signECDSA(byte[] msg, BigInteger privateKey) throws Exception {
        return Ecc.sign(msg, privateKey.toByteArray());
    }

    /**
     * 使用单个公钥来生成钱包地址
     *
     * @param publicKey
     * @return
     */
    @Override
    public String getAddressFromPublicKey(ECPoint publicKey) {
        byte[] hash = Hash.ripeMD160(Hash.hashUsingSM3(publicKey.getEncoded(false)));

        String address = Base58.encodeChecked(Common.gm, hash);
        return address;
    }

    /**
     * using random create ECKeyPair
     *
     * @return
     */
    @Override
    public ECKeyPair createECKeyPair() {
        ECKeyPair ecKeyPair = new ECKeyPair();
        SM2KeyPair sm2KeyPair = SM2KeyPair.create();
        ecKeyPair.privateKey = sm2KeyPair.getPrivateKey();
        ecKeyPair.publicKey = sm2KeyPair.getPublicKey();
        ecKeyPair.jsonPublicKey = sm2KeyPair.getJSONPublicKey();
        ecKeyPair.jsonPrivateKey = sm2KeyPair.getJSONPrivateKey();
        return ecKeyPair;
    }

    /**
     * @param privateKey
     * @return
     */
    @Override
    public ECKeyPair getECKeyPairFromPrivateKey(BigInteger privateKey) {
        ECKeyPair ecKeyPair = new ECKeyPair();
        SM2KeyPair sm2KeyPair = SM2KeyPair.create(privateKey);
        ecKeyPair.privateKey = sm2KeyPair.getPrivateKey();
        ecKeyPair.publicKey = sm2KeyPair.getPublicKey();
        ecKeyPair.jsonPublicKey = sm2KeyPair.getJSONPublicKey();
        ecKeyPair.jsonPrivateKey = sm2KeyPair.getJSONPrivateKey();
        return ecKeyPair;
    }
}
