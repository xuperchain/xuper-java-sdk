package com.baidu.xuper.api;

import com.baidu.xuper.crypto.Base58;
import com.baidu.xuper.crypto.ECKeyPair;
import com.baidu.xuper.crypto.Hash;
import com.baidu.xuper.crypto.account.ECDSAAccount;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Data;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
public class Account {
    private final ECKeyPair ecKeyPair;
    private final String address;
    private String contractAccount;
    private String mnemonic;

    private Account(ECKeyPair ecKeyPair, String address) {
        this.ecKeyPair = ecKeyPair;
        this.address = address;
    }

    private Account(ECKeyPair ecKeyPair, String address, String mnemonic) {
        this.ecKeyPair = ecKeyPair;
        this.address = address;
        this.mnemonic = mnemonic;
    }

    /**
     * @param inputStream  ./data/keys/private.key fileInputStream
     * @return
     * @throws Exception
     */
    public static Account create(InputStream inputStream) {
        Gson gson = new Gson();
        privatePubKey json;
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            json = gson.fromJson(reader, privatePubKey.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (json.D == null) {
            throw new RuntimeException("invalid private.key file");
        }
        return create(ECKeyPair.create(json.D));
    }

    /**
     * @param keyPath the path to ./data/keys which contains private.key file
     * @return
     * @throws Exception
     */
    public static Account create(String keyPath) {
        try {
            String privateKeyPath = Paths.get(keyPath, "private.key").toString();
            File privateKeyFile=new File(privateKeyPath);
            return create(new FileInputStream(privateKeyFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param keyPair the private and public key of account
     * @return
     */
    public static Account create(ECKeyPair keyPair) {
        byte[] pubkey = keyPair.getPublicKey().getEncoded(false);
        byte[] hash = Hash.ripeMD128(Hash.sha256(pubkey));
        String address = Base58.encodeChecked(1, hash);
        return new Account(keyPair, address);
    }

    /**
     * Create a account using random private key
     *
     * @return
     */
    public static Account create() {
        return create(ECKeyPair.create());
    }

    /**
     * @param strength 助记词强度。
     * @param language 助记词语言，1中文，2英文。
     * @return Account 账户信息。
     */
    public static Account create(int strength, int language) {
        ECDSAAccount ecdsaAccount = new ECDSAAccount();
        ecdsaAccount.createAccountWithMnemonic(strength, language);
        return new Account(ecdsaAccount.ecKeyPair, ecdsaAccount.address, ecdsaAccount.mnemonic);
    }

    /**
     * @param mnemonic 助记词。
     * @param language 助记词语言，1中文，2英文。
     * @return Account 账户信息。
     */
    public static Account retrieve(String mnemonic, int language) {
        ECDSAAccount ecdsaAccount = new ECDSAAccount();
        ecdsaAccount.createByMnemonic(mnemonic, language);
        return new Account(ecdsaAccount.ecKeyPair, ecdsaAccount.address, ecdsaAccount.mnemonic);
    }

    /**
     * @param path     保存的路径。
     * @param passwd   密码。
     * @param strength 助记词强度，1弱 12个助记词，2中 18个助记词，3强 24个助记词。
     * @param language 助记词语言，1中文，2英文。
     * @return Account 账户信息。
     */
    public static Account createAndSave(String path, String passwd, int strength, int language) {
        ECDSAAccount ecdsaAccount = new ECDSAAccount();
        ecdsaAccount.createAccountWithMnemonic(strength, language);
        ecdsaAccount.saveToFile(path, passwd);
        return new Account(ecdsaAccount.ecKeyPair, ecdsaAccount.address, ecdsaAccount.mnemonic);
    }

    /**
     * @param path   文件路径。
     * @param passwd 密码。
     * @return Account 账户信息。
     */
    public static Account getAccountFromFile(String path, String passwd) {
        byte[] p = ECDSAAccount.getBinaryECDSAPrivateKey(path, passwd);
        Gson gson = new Gson();
        privatePubKey json = gson.fromJson(new String(p), privatePubKey.class);
        if (json.D == null) {
            throw new RuntimeException("invalid private.key file");
        }
        return create(ECKeyPair.create(json.D));
    }

    /**
     * @param path 文件路径。
     *             The structure of path is like below:
     *             - keys
     *             |-- address
     *             |-- private.key
     *             |-- public.key
     *             |-- mnemonic
     * @return 账户信息。
     */
    public static Account getAccountFromPlainFile(String path) {
        try {
            byte[] address = Files.readAllBytes(Paths.get(path + "/address"));
            byte[] pubKey = Files.readAllBytes(Paths.get(path + "/public.key"));
            byte[] privKey = Files.readAllBytes(Paths.get(path + "/private.key"));

            Gson gson = new Gson();
            privatePubKey json = gson.fromJson(new String(privKey), privatePubKey.class);
            if (json.D == null) {
                throw new RuntimeException("invalid private.key file");
            }

            Account a = create(ECKeyPair.create(json.D));
            if (!a.getAKAddress().equals(new String(address))) {
                throw new RuntimeException("address and private key not match.");
            }

            if (!a.getKeyPair().getJSONPublicKey().equals(new String(pubKey))) {
                throw new RuntimeException("public key and private key not match.");
            }
            return a;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return 公私钥相关。
     */
    public ECKeyPair getKeyPair() {
        return ecKeyPair;
    }

    /**
     * @return 账户地址，如果存在合约账户地址，返回合约账户地址。
     */
    public String getAddress() {
        if (this.contractAccount != null) {
            return this.contractAccount;
        }
        return this.address;
    }

    /**
     * @return 账户地址，不会返回合约账户地址。
     */
    public String getAKAddress() {
        return this.address;
    }

    /**
     * @param name 合约账户。
     */
    public void setContractAccount(String name) {
        this.contractAccount = name;
    }

    /**
     * @return AuthRequireId。
     */
    public String getAuthRequireId() {
        if (this.contractAccount != null) {
            return this.contractAccount + "/" + this.address;
        }
        return this.address;
    }

    class privatePubKey {
        String CurvName;
        BigInteger D;
        BigInteger X;
        BigInteger Y;
    }
}
