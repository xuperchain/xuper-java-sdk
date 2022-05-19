package com.baidu.xuper.api;

import com.baidu.xuper.pb.XchainOuterClass;

import java.io.File;
import java.nio.ByteBuffer;

public class Common {
    public static XchainOuterClass.Header newHeader() {
        return XchainOuterClass.Header.newBuilder()
                .setLogid(newNonce())
                .build();
    }

    public static String newNonce() {
        return String.valueOf(getTimestamp() + (int) (Math.random() * 100000000));
    }

    public static Long getTimestamp() {
        return System.currentTimeMillis() * 1000 * 1000;
    }

    public static void checkResponseHeader(XchainOuterClass.Header header, String msg) {
        if (header.getError() != XchainOuterClass.XChainErrorEnum.SUCCESS) {
            throw new RuntimeException("Error " + header.getError().toString() + " while " + msg);
        }
    }

    /**
     * 创建目录
     *
     * @param path
     */
    public static void mkdir(String path) {
        File file = new File(path);
        if (file.exists() || file.isDirectory()) {
            throw new RuntimeException("dir exist");
        }

        if (!file.mkdir()) {
            throw new RuntimeException("mkdir failed.");
        }
    }

    /**
     * 类型转换 int to byte[]
     *
     * @param x
     * @param length
     * @return
     */
    public static byte[] intToBytes(int x, int length) {
        byte[] bs = new byte[length];
        for (int i = bs.length - 1; i >= 0; i--) {
            bs[i] = (byte) (x % 0xFF);
            x = x / 0xFF;
        }
        return bs;
    }

    /**
     * 类型转换 long to byte[]
     * @param x
     * @return
     */
    public static byte[] longToBytes(long x)
    {
        final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
}
