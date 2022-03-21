package com.baidu.xuper.crypto;

import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ECCTest {
    @Test
    public void ECKeyPairTest() throws Exception {
        ECKeyPair ec = ECKeyPair.create("a".getBytes());
        assertNotNull(ec.getJSONPrivateKey());

        ECKeyPair ecKeyPair = ECKeyPair.create();
        assertNotNull(ecKeyPair.getJSONPrivateKey());
        assertNotNull(ecKeyPair.getJSONPublicKey());
        assertNotNull(ecKeyPair.getPrivateKey());
        assertNotNull(ecKeyPair.getPublicKey());

//        try {
//            byte[] sign = ecKeyPair.sign("a".getBytes());
//            assertNotNull(sign);
//        } catch (Exception e) {
//            throw e;
//        }
    }
}
