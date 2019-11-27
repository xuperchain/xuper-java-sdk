package com.baidu.xuperunion.api;

import com.baidu.xuperunion.crypto.Base58;
import com.baidu.xuperunion.crypto.ECKeyPair;
import com.baidu.xuperunion.crypto.Hash;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.math.BigInteger;
import java.nio.file.Paths;

public class Account {
    private final ECKeyPair ecKeyPair;
    private final String address;
    private String contractAccount;

    private Account(ECKeyPair ecKeyPair, String address) {
        this.ecKeyPair = ecKeyPair;
        this.address = address;
    }

    class privatePubKey {
        String CurvName;
        BigInteger D;
        BigInteger X;
        BigInteger Y;
    }

    /**
     * @param keyPath the path to ./data/keys which contains private.key file
     * @return
     * @throws Exception
     */
    public static Account create(String keyPath) throws Exception {
        String privateKeyPath = Paths.get(keyPath, "private.key").toString();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(privateKeyPath));
        privatePubKey json = gson.fromJson(reader, privatePubKey.class);
        if (json.D == null) {
            throw new Exception("invalid private.key file");
        }
        return create(ECKeyPair.create(json.D));
    }

    /**
     * @param keyPair the private and public key of account
     * @return
     */
    public static Account create(ECKeyPair keyPair) {
        byte[] pubkey = keyPair.getPublicKey().getEncoded(false);
        byte [] hash = Hash.ripeMD128(Hash.sha256(pubkey));
        String address = Base58.encodeChecked(1, hash);
        return new Account(keyPair, address);
    }

    public void setContractAccount(String name) {
        this.contractAccount = name;
    }

    public ECKeyPair getKeyPair() {
        return ecKeyPair;
    }

    public String getAddress() {
        return this.address;
    }

    public String getContractAccount() {
        return this.contractAccount;
    }

    /**
     * @return the address which can be used to transfer token
     */
    public String getPayableAddress() {
        if (this.contractAccount != null) {
            return this.contractAccount;
        }
        return this.address;
    }

    public String getAuthRequireId() {
        if (this.contractAccount != null) {
            return this.contractAccount+"/"+this.address;
        }
        return this.address;
    }
}
