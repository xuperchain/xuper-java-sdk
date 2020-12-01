package com.baidu.xuperunion.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecretKey sKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int b = cipher.getBlockSize();
            byte[] bb = new byte[key.length - b];
            System.arraycopy(key, 0, bb, 0, key.length - b);
            IvParameterSpec iv = new IvParameterSpec(bb);
            cipher.init(Cipher.ENCRYPT_MODE, sKey, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            SecretKey sKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int b = cipher.getBlockSize();
            byte[] bb = new byte[key.length - b];
            System.arraycopy(key, 0, bb, 0, key.length - b);
            IvParameterSpec iv = new IvParameterSpec(bb);
            cipher.init(Cipher.DECRYPT_MODE, sKey, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
