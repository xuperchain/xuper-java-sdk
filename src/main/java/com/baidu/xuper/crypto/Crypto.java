package com.baidu.xuper.crypto;

import com.baidu.xuper.crypto.account.ECDSAAccount;
import com.baidu.xuper.crypto.account.ECDSAAccountToCloud;
import com.baidu.xuper.crypto.account.ECDSAInfo;

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
     * 将助记词转为指定长度的随机数种子，在此过程中，校验助记词是否合法
     *
     * @param mnemonic
     * @param password
     * @param keyLen
     * @param language
     * @return byte[]
     */
    byte[] generateSeedWithErrorChecking(String mnemonic, String password, Integer keyLen, Integer language);


    /**
     * 获取ECC私钥的json格式的表达
     *
     * @param ecKeyPair
     * @return
     */
    String getEcdsaPrivateKeyJsonFormatStr(ECKeyPair ecKeyPair);

    /**
     * 获取ECC公钥的json格式的表达
     *
     * @param ecKeyPair
     * @return
     */
    String getEcdsaPublicKeyJsonFormatStr(ECKeyPair ecKeyPair);

    /**
     * 从json格式私钥内容字符串产生ECC私钥
     *
     * @param keyStr
     * @return
     */
    ECKeyPair getEcdsaPrivateKeyFromJsonStr(String keyStr);

    /**
     * 从json格式公钥内容字符串产生ECC公钥
     *
     * @param keyStr
     * @return
     */
    ECKeyPair getEcdsaPublicKeyFromJsonStr(String keyStr);

    /**
     * 使用单个公钥来生成钱包地址
     *
     * @param ecKeyPair
     * @return
     */
    String getAddressFromPublicKey(ECKeyPair ecKeyPair);

    /**
     * 使用多个公钥来生成钱包地址（环签名，多重签名地址）
     *
     * @param ecKeyPair
     * @return
     */
    String getAddressFromPublicKeys(ECKeyPair ecKeyPair);

//    // 验证钱包地址是否是合法的格式。如果成功，返回true和对应的版本号；如果失败，返回false和默认的版本号0
//    CheckAddressFormat(address string) (bool, uint8)
//
//    // 验证钱包地址是否和指定的公钥match。如果成功，返回true和对应的版本号；如果失败，返回false和默认的版本号0
//    VerifyAddressUsingPublicKey(address string, pub *ecdsa.PublicKey) (bool, uint8)
//
//    // 验证钱包地址（环签名，多重签名地址）是否和指定的公钥数组match。如果成功，返回true和对应的版本号；如果失败，返回false和默认的版本号0
//    VerifyAddressUsingPublicKeys(address string, pub []*ecdsa.PublicKey) (bool, uint8)

    ECKeyPair generateKeyBySeed(byte[] seed);

    /**
     * 创建新账户(不使用助记词，不推荐使用)
     *
     * @param path
     */
    void exportNewAccount(String path);

    /**
     * 创建含有助记词的新的账户，返回的字段：（助记词、私钥的json、公钥的json、钱包地址） as ECDSAAccount，以及可能的错误信息
     *
     * @param language
     * @param strength TODO:uint8
     * @return
     */
    ECDSAAccount createNewAccountWithMnemonic(Integer language, Integer strength);


    /**
     * 创建新的账户，并用支付密码加密私钥后存在本地，
     * 返回的字段：（随机熵（供其他钱包软件推导出私钥）、助记词、私钥的json、公钥的json、钱包地址） as ECDSAAccount，以及可能的错误信息
     *
     * @param path
     * @param language
     * @param strength
     * @param password
     * @return
     */
    ECDSAInfo createNewAccountAndSaveSecretKey(String path, Integer language, Integer strength, String password);

    /**
     * 创建新的账户，并导出相关文件（含助记词）到本地。生成如下几个文件：1.助记词，2.私钥，3.公钥，4.钱包地址
     *
     * @param path
     * @param language
     * @param strength
     */
    void exportNewAccountWithMnemonic(String path, Integer language, Integer strength);

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
     *
     * @param path
     * @param language
     * @param mnemonic
     * @param password
     * @return
     */
    ECDSAInfo retrieveAccountByMnemonicAndSavePrivKey(String path, Integer language, String mnemonic, String password);

    /**
     * 使用支付密码加密账户信息并返回加密后的数据（后续用来回传至云端）
     *
     * @param info
     * @param password
     * @return
     */
    ECDSAAccountToCloud encryptAccount(ECDSAInfo info, String password);

    /**
     * 从导出的私钥文件读取私钥的byte格式
     *
     * @param path
     * @param password
     * @return
     */
    byte[] getBinaryEcdsaPrivateKeyFromFile(String path, String password);

    /**
     * 使用支付密码从导出的私钥文件读取私钥
     *
     * @param path
     * @param password
     * @return TODO: *ecdsa.PrivateKey
     */
    ECKeyPair getEcdsaPrivateKeyFromFileByPassword(String path, String password);

    /**
     * 使用支付密码从二进制加密字符串获取真实私钥的字节数组
     *
     * @param encryptedPrivateKey
     * @param password
     * @return
     */
    byte[] getEcdsaPrivateKeyBytesFromEncryptedStringByPassword(String encryptedPrivateKey, String password);

    /**
     * 使用支付密码从二进制加密字符串获取真实ECC私钥
     *
     * @param encryptedPrivateKey
     * @param password
     * @return
     */
    ECKeyPair getEcdsaPrivateKeyFromEncryptedStringByPassword(String encryptedPrivateKey, String password);

    /**
     * 从导出的私钥文件读取私钥
     *
     * @param filename
     * @return
     */
    ECKeyPair getEcdsaPrivateKeyFromFile(String filename);

    /**
     * 从导出的公钥文件读取公钥
     *
     * @param filename
     * @return
     */
    ECKeyPair getEcdsaPublicKeyFromFile(String filename);

    /**
     * 切分账户私钥
     *
     * @param jsonPrivateKey
     * @param totalShareNumber
     * @param minimumShareNumber
     * @return
     */
    String[] splitPrivateKey(String jsonPrivateKey, Integer totalShareNumber, Integer minimumShareNumber);

    /**
     * 通过私钥片段恢复私钥
     *
     * @param jsonPrivateKeyShares
     * @return
     */
    String retrievePrivateKeyByShares(String[] jsonPrivateKeyShares);

//    // 使用ECC私钥来签名
//    byte[] signECDSA(k *ecdsa.PrivateKey, byte[] msg);
//
//    // 使用ECC私钥来签名，生成统一签名的新签名函数
//    byte[] signV2ECDSA(k *ecdsa.PrivateKey, byte[] msg);
//
//    // 使用ECC公钥来验证签名，验证统一签名的新签名函数
//    boolean verifyECDSA(k *ecdsa.PublicKey, byte[] signature, byte[] msg);
//
//    // 使用ECC公钥来验证签名，验证统一签名的新签名函数  -- 内部函数，供统一验签函数调用
//    boolean verifyV2ECDSA(k *ecdsa.PublicKey, byte[] signature, byte[] msg);

//    // 使用椭圆曲线非对称加密
//    byte[] encryptByEcdsaKey(publicKey *ecdsa.PublicKey, byte[] msg);
//
//    // 使用椭圆曲线非对称解密
//    byte[] decryptByEcdsaKey(privateKey *ecdsa.PrivateKey, byte[] cypherText);

    /**
     * 使用AES对称加密算法加密
     *
     * @param info
     * @param cypherKey
     * @return
     */
    String encryptByAESKey(String info, String cypherKey);

    /**
     * 使用AES对称加密算法解密
     *
     * @param cipherInfo
     * @param cypherKey
     * @return
     */
    String decryptByAESKey(String cipherInfo, String cypherKey);

    /**
     * 使用AES对称加密算法加密，密钥会被增强拓展，提升破解难度
     *
     * @param info
     * @param cypherKey
     * @return
     */
    String encryptHardenByAESKey(String info, String cypherKey);

    /**
     * 使用AES对称加密算法解密，密钥曾经被增强拓展，提升破解难度
     *
     * @param cipherInfo
     * @param cypherKey
     * @return
     */
    String decryptHardenByAESKey(String cipherInfo, String cypherKey);

    /**
     * 将经过支付密码加密的账户保存到文件中
     *
     * @param account
     * @param path
     */
    void saveEncryptedAccountToFile(ECDSAAccountToCloud account, String path);


    // 每个多重签名算法流程的参与节点生成32位长度的随机byte，返回值可以认为是k
    byte[] getRandom32Bytes();

//  // 每个多重签名算法流程的参与节点生成Ri = Ki*G
//  byte[] getRiUsingRandomBytes(key *ecdsa.PublicKey, k []byte);
//
//  // 负责计算多重签名的节点来收集所有节点的Ri，并计算R = k1*G + k2*G + ... + kn*G
//  byte[] getRUsingAllRi(key *ecdsa.PublicKey, arrayOfRi [][]byte;

//  // 负责计算多重签名的节点来收集所有节点的公钥Pi，并计算公共公钥：C = P1 + P2 + ... + Pn
//  byte[] getSharedPublicKeyForPublicKeys(keys []*ecdsa.PublicKey) ([]byte, error)

//    // 负责计算多重签名的节点将计算出的R和C分别传递给各个参与节点后，由各个参与节点再次计算自己的Si
//    // 计算 Si = Ki + HASH(C,R,m) * Xi
//    // X代表大数D，也就是私钥的关键参数
//    byte[] GetSiUsingKCRM(key *ecdsa.PrivateKey, k []byte, c []byte, r []byte, message []byte);

    /**
     * 负责计算多重签名的节点来收集所有节点的Si，并计算出S = sum(si)
     *
     * @param arrayOfSi
     * @return
     */
    byte[] GetSUsingAllSi(byte[][] arrayOfSi);

    /**
     * 负责计算多重签名的节点，最终生成多重签名的统一签名格式XuperSignature
     *
     * @param s
     * @param r
     * @return
     */
    byte[] GenerateMultiSignSignature(byte[] s, byte[] r);

//    // 使用ECC公钥数组来进行多重签名的验证  -- 内部函数，供统一验签函数调用
//    boolean VerifyMultiSig(keys []*ecdsa.PublicKey, signature, message []byte);

//    // -- 多重签名的另一种用法，适用于完全中心化的流程
//    // 使用ECC私钥数组来进行多重签名，生成统一签名格式XuperSignature
//    byte[] MultiSign(keys []*ecdsa.PrivateKey, message []byte);
}
