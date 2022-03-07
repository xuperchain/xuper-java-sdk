package com.baidu.xuper.crypto.gm.hash;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SM3Digest;

public class Hash {
    public static byte[] hashUsingSM3(byte[] data) {
        byte[] md = new byte[32];
        SM3Digest sm3 = new SM3Digest();
        sm3.update(data, 0, data.length);
        sm3.doFinal(md, 0);
        return md;
    }

    public static byte[] hashUsingSM3(byte[] data, int start, int end) {
        byte[] md = new byte[32];
        SM3Digest sm3 = new SM3Digest();
        sm3.update(data, start, end);
        sm3.doFinal(md, start);
        return md;
    }

    /**
     * Ripemd160，这种hash算法可以缩短长度
     *
     * @param msg
     * @return
     */
    static public byte[] ripeMD160(byte[] msg) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(msg, 0, msg.length);
        byte[] out = new byte[20];
        digest.doFinal(out, 0);
        return out;
    }

}
