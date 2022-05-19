package com.baidu.xuper.crypto.account;

import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;

public class ECDSAAccount {
    public byte[] entropyByte;
    public String mnemonic;
    public String jsonPrivateKey;
    public String jsonPublicKey;
    public String address;
    public ECKeyPair ecKeyPair;

    public byte[] getEntropyByte() {
        return entropyByte;
    }

    public void setEntropyByte(byte[] entropyByte) {
        this.entropyByte = entropyByte;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getJsonPrivateKey() {
        return jsonPrivateKey;
    }

    public void setJsonPrivateKey(String jsonPrivateKey) {
        this.jsonPrivateKey = jsonPrivateKey;
    }

    public String getJsonPublicKey() {
        return jsonPublicKey;
    }

    public void setJsonPublicKey(String jsonPublicKey) {
        this.jsonPublicKey = jsonPublicKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ECKeyPair getEcKeyPair() {
        return ecKeyPair;
    }

    public void setEcKeyPair(ECKeyPair ecKeyPair) {
        this.ecKeyPair = ecKeyPair;
    }
}
