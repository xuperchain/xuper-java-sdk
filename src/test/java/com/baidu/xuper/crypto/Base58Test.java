package com.baidu.xuper.crypto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Base58Test {
    @Test
    public void encodeTest() {
        String encode = Base58.encode("a".getBytes());
        assertNotNull(encode);
    }

    @Test
    public void decodeTest() throws Exception {
        String encode = Base58.encode("a".getBytes());
        assertNotNull(encode);
        try {
            byte[] decode = Base58.decode(encode);
            assertEquals(new String(decode), "a");
        } catch (Exception e) {
            throw e;
        }
    }
}
