package com.baidu.xuper.crypto.gm.bip39;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class PBKDF2SHA512 {
    public static byte[] derive(String P, String S, int c, int dkLen) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            int hLen = 20;

            if (dkLen > ((Math.pow(2, 32)) - 1) * hLen) {
                throw new IllegalArgumentException("derived key too long");
            } else {
                int l = (int) Math.ceil((double) dkLen / (double) hLen);
                for (int i = 1; i <= l; i++) {
                    byte[] T = F(P, S, c, i);
                    baos.write(T);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        byte[] baDerived = new byte[dkLen];
        System.arraycopy(baos.toByteArray(), 0, baDerived, 0, baDerived.length);

        return baDerived;
    }

    private static byte[] F(String P, String S, int c, int i) throws Exception {
        byte[] U_LAST = null;
        byte[] U_XOR = null;

        SecretKeySpec key = new SecretKeySpec(P.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        Mac mac = Mac.getInstance(key.getAlgorithm());
        mac.init(key);

        for (int j = 0; j < c; j++) {
            if (j == 0) {
                byte[] baS = S.getBytes(StandardCharsets.UTF_8);
                byte[] baI = INT(i);
                byte[] baU = new byte[baS.length + baI.length];

                System.arraycopy(baS, 0, baU, 0, baS.length);
                System.arraycopy(baI, 0, baU, baS.length, baI.length);

                U_XOR = mac.doFinal(baU);
                U_LAST = U_XOR;
                mac.reset();
            } else {
                byte[] baU = mac.doFinal(U_LAST);
                mac.reset();

                for (int k = 0; k < U_XOR.length; k++) {
                    U_XOR[k] = (byte) (U_XOR[k] ^ baU[k]);
                }

                U_LAST = baU;
            }
        }

        return U_XOR;
    }

    private static byte[] INT(int i) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(i);

        return bb.array();
    }
}
