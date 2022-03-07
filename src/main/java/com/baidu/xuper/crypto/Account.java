package com.baidu.xuper.crypto;

import com.baidu.xuper.api.CryptoClient;
import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Account {
    private final ECKeyPair ecKeyPair;
    private final String address;
    private String contractAccount;
    private String mnemonic;
    private CryptoClient cryptoClient;

    private Account(ECKeyPair ecKeyPair, String address) {
        this.ecKeyPair = ecKeyPair;
        this.address = address;
    }

    public static byte[] readFileWithBASE64Decode(String path) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));

            Base64.Decoder decoder = Base64.getDecoder();
            return decoder.decode(fileBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
