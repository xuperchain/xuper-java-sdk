package com.baidu.xuper.crypto;

import com.baidu.xuper.crypto.account.ECDSAAccount;
import com.baidu.xuper.crypto.account.ECDSAInfo;
import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

public interface Crypto {

    /**
     * 产生随机熵
     *
     * @param bitSize
     * @return byte[]
     */
    byte[] generateEntropy(int bitSize);

    /**
     * 将随机熵转为助记词
     *
     * @param entropy
     * @param language
     * @return Strings
     */
    String generateMnemonic(byte[] entropy, Integer language);

    /**
     * 创建含有助记词的新的账户，返回的字段：（助记词、私钥的json、公钥的json、钱包地址） as ECDSAAccount，以及可能的错误信息
     *
     * @param language
     * @param strength
     * @return
     */
    ECDSAAccount createNewAccountWithMnemonic(Integer language, Integer strength);

    /**
     * 从助记词恢复钱包账户
     * TODO: 后续可以从助记词中识别出语言类型
     *
     * @param mnemonic
     * @param language
     * @return
     */
    ECDSAAccount retrieveAccountByMnemonic(String mnemonic, Integer language);

    /**
     * 从助记词恢复钱包账户，并用支付密码加密私钥后存在本地，
     * 返回的字段：（随机熵（供其他钱包软件推导出私钥）、助记词、私钥的json、公钥的json、钱包地址） as ECDSAAccount，以及可能的错误信息
     * @param path
     * @param language
     * @param mnemonic
     * @param password
     * @return
     */
    ECDSAInfo retrieveAccountByMnemonicAndSavePrivKey(String path, Integer language, String mnemonic, String password);

    /**
    * 使用支付密码从导出的私钥文件读取私钥
    *
    * @param path
    * @param password
    * @return
    */
    byte[] getEcdsaPrivateKeyFromFileByPassword(String path, String password);

    /**
     * 使用ECC私钥来签名
     *
     * @param msg
     * @param privateKey
     * @return
     */
    byte[] signECDSA(byte[] msg, BigInteger privateKey) throws Exception;


    /**
     * 使用单个公钥来生成钱包地址
     *
     * @param publicKey
     * @return
     */
    String getAddressFromPublicKey(ECPoint publicKey);

    /**
     * using random create ECKeyPair
     *
     * @return
     */
    ECKeyPair createECKeyPair();

    /**
     *
     * @param privateKey
     * @return
     */
    ECKeyPair getECKeyPairFromPrivateKey(BigInteger privateKey);
}
