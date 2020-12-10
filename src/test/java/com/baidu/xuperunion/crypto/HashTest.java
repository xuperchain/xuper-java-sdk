package com.baidu.xuperunion.crypto;

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
        byte[] bytes = Hash.sha256("test".getBytes());
        byte[] bytes1 = Hash.sha256("test".getBytes());
        assertNotNull(bytes);
        assertNotNull(bytes1);
        assertEquals(new String(bytes), new String(bytes1));
    }

    @Test
    public void ripeMD128Test() {
        byte[] bytes = Hash.ripeMD128("test".getBytes());
        assertNotNull(bytes);
    }
}
