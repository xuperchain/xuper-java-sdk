package com.baidu.xuper.crypto;

import com.baidu.xuper.api.CryptoClient;
import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ECCTest {
    @Test
    public void ECKeyPairTest() throws Exception {
        Crypto cli = CryptoClient.getCryptoClient();

        ECKeyPair ec = ECKeyPair.create("a".getBytes());
        assertNotNull(ec.getJSONPrivateKey());

        ECKeyPair ecKeyPair = ECKeyPair.create();
        assertNotNull(ecKeyPair.getJSONPrivateKey());
        assertNotNull(ecKeyPair.getJSONPublicKey());
        assertNotNull(ecKeyPair.getPrivateKey());
        assertNotNull(ecKeyPair.getPublicKey());

        try {
            byte[] sign = cli.signECDSA("a".getBytes(), ecKeyPair.getPrivateKey());
            assertNotNull(sign);
        } catch (Exception e) {
            throw e;
        }
    }
}
