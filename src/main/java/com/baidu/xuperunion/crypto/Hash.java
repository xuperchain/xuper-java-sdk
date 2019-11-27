package com.baidu.xuperunion.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Hash {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static MessageDigest newDigest(String algo) {
        try {
            return MessageDigest.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Can't happen.
        }
    }

    public static byte[] sha256(byte[] msg) {
        final MessageDigest md = newDigest("SHA-256");
        return md.digest(msg);
    }

    public static byte[] doubleSha256(byte[] msg) {
        return doubleSha256(msg, 0, msg.length);
    }

    public static byte[] doubleSha256(byte[] msg, int offset, int length) {
        final MessageDigest md = newDigest("SHA-256");
        md.update(msg, offset, length);
        return md.digest(md.digest());
    }



    static public byte[] ripeMD128(byte[] msg) {
        final MessageDigest md = newDigest("RipeMD160");
        return md.digest(msg);
    }
}
