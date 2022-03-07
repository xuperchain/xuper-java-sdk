package com.baidu.xuper.crypto.xchain.hash;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static MessageDigest newDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1次SHA256
     *
     * @param msg 加密信息
     * @return 1次sha256的结果
     */
    public static byte[] hashUsingSha256(byte[] msg) {
        final MessageDigest md = newDigest("SHA-256");
        return md.digest(msg);
    }

    /**
     * 2次SHA256
     *
     * @param msg 加密信息
     * @return msg 2次sha256的结果
     */
    public static byte[] doubleSha256(byte[] msg) {
        return doubleSha256(msg, 0, msg.length);
    }

    /**
     *
     * @param msg
     * @param offset
     * @param length
     * @return
     */
    public static byte[] doubleSha256(byte[] msg, int offset, int length) {
        final MessageDigest md = newDigest("SHA-256");
        md.update(msg, offset, length);
        return md.digest(md.digest());
    }

    /**
     * ripeMD160，这种hash算法可以缩短长度
     *
     * @param msg 加密信息
     * @return byte[]
     */
    static public byte[] ripeMD160(byte[] msg) {
        RIPEMD160Digest rd = new RIPEMD160Digest();
        rd.update(msg, 0, msg.length);
        byte[] out = new byte[20];
        rd.doFinal(out, 0);
        return out;
    }

}
