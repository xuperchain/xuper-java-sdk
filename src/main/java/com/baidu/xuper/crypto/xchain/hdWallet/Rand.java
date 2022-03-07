package com.baidu.xuper.crypto.xchain.hdWallet;

import java.security.SecureRandom;

public class Rand {

    /**
     * 底层调用跟操作系统相关的函数（读取系统熵）来产生一些伪随机数，
     * 对外建议管这个返回值叫做“熵”
     *
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
     * +8的原因在于引入了8个bit的标记位来定义使用的密码学算法
     *
     * @param bitSize 熵的位大小.
     */
    private static void validateRawEntropySize(int bitSize) {
        if ((bitSize + 8) % 32 != 0 || (bitSize + 8) < 128 || (bitSize + 8) > 256) {
            throw new RuntimeException("invalid bitSize");
        }
    }

}
