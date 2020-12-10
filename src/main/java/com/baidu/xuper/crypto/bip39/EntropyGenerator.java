package com.baidu.xuper.crypto.bip39;

import java.security.SecureRandom;

public class EntropyGenerator {

    /**
     * @param bitSize 生成熵的位大小。
     * @return 随机熵。
     */
    public static byte[] generateEntropy(int bitSize) {
        validateRawEntropySize(bitSize);
        byte[] entropy = new byte[bitSize / 8];
        new SecureRandom().nextBytes(entropy);
        return entropy;
    }

    /**
     * @param bitSize 熵的位大小.
     */
    private static void validateRawEntropySize(int bitSize) {
        if ((bitSize + 8) % 32 != 0 || (bitSize + 8) < 128 || (bitSize + 8) > 256) {
            throw new RuntimeException("invalid bitSize");
        }
    }
}
