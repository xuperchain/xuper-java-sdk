package com.baidu.xuper.crypto.xchain.hdWallet;

import com.baidu.xuper.crypto.account.ECDSAAccount;
import com.baidu.xuper.crypto.account.ECDSAInfo;
import com.baidu.xuper.crypto.AES;
import com.baidu.xuper.crypto.xchain.hash.Hash;
import com.baidu.xuper.crypto.xchain.account.FileKey;

import java.io.FileWriter;
import java.util.Base64;

public class Key {

    /**
     * @param path
     * @param language
     * @param mnemonic
     * @param password
     * @return ECDSAInfo
     */
    public static ECDSAInfo createAndSaveSecretKeyWithMnemonic(String path, Integer language, String mnemonic, String password) {
        // 通过助记词来产生钱包账户
        ECDSAAccount ecdsaAccount = FileKey.generateAccountByMnemonic(mnemonic, language);

        if (!path.endsWith("/")) {
            path += "/";
        }

        byte[] newPW = Hash.doubleSha256(password.getBytes());
        byte[] encryptContent = AES.encrypt(ecdsaAccount.getJsonPrivateKey().getBytes(), newPW);
        writeFileUsingFileName(path + "private.key", encryptContent);

        return getECDSAInfoFromECDSAAccount(ecdsaAccount);
    }


    private static void writeFileUsingFileName(String fileName, byte[] content) {
        Base64.Encoder encoder = Base64.getEncoder();
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

    /**
     * @param ecdsaAccount
     * @return
     */
    private static ECDSAInfo getECDSAInfoFromECDSAAccount(ECDSAAccount ecdsaAccount) {
        ECDSAInfo ecdsaInfo = new ECDSAInfo();
        ecdsaInfo.entropyByte = ecdsaAccount.entropyByte;
        ecdsaInfo.mnemonic = ecdsaAccount.mnemonic;
        ecdsaInfo.address = ecdsaAccount.address;
        return ecdsaInfo;
    }


}
