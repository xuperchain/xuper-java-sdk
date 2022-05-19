package com.baidu.xuper.crypto;

import com.baidu.xuper.crypto.xchain.hash.Hash;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AESTest {

    @Test
    public void encrypt() {
        try {
            byte[] ds = Hex.decode("994ea33d94058425c90ddc4efe6776ac692e91361e388c98134f0d0fc2a012d8");
            byte[] result = AES.encrypt(ds, Hash.doubleSha256("test".getBytes()));
            System.out.println(Hex.toHexString(result));
            assertEquals(Hex.toHexString(result), "7458c8efa73cfaba78daca0d3a3767456ed4333dd353ba581b361e333169172b6579ac625ecc3baec6b96709c56fa3f2");
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public void decrypt() {
        byte[] ds = Hex.decode("7458c8efa73cfaba78daca0d3a3767456ed4333dd353ba581b361e333169172b6579ac625ecc3baec6b96709c56fa3f2");
        byte[] result = AES.decrypt(ds, Hash.doubleSha256("test".getBytes()));
        assertEquals("994ea33d94058425c90ddc4efe6776ac692e91361e388c98134f0d0fc2a012d8", Hex.toHexString(result));
    }
}
