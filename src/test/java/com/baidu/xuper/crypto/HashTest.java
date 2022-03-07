package com.baidu.xuper.crypto;

import com.baidu.xuper.crypto.xchain.hash.Hash;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HashTest {

    @Test
    public void doubleSha256Test() {
        byte[] bytes = Hash.doubleSha256("test".getBytes());
        byte[] bytes1 = Hash.doubleSha256("test".getBytes());
        assertNotNull(bytes);
        assertNotNull(bytes1);
        assertEquals(new String(bytes), new String(bytes1));
    }

    @Test
    public void sha256Test() {
        byte[] bytes = Hash.hashUsingSha256("test".getBytes());
        byte[] bytes1 = Hash.hashUsingSha256("test".getBytes());
        assertNotNull(bytes);
        assertNotNull(bytes1);
        assertEquals(new String(bytes), new String(bytes1));
    }

    @Test
    public void ripeMD160Test() {
        byte[] bytes = Hash.ripeMD160("test".getBytes());
        assertNotNull(bytes);
    }
}
