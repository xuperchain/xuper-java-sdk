package com.baidu.xuper.api;

import com.baidu.xuper.pb.XchainOuterClass;

public class Common {
    static public XchainOuterClass.Header newHeader() {
        return XchainOuterClass.Header.newBuilder()
                .setLogid(newNonce())
                .build();
    }

    static public String newNonce() {
        return String.valueOf(getTimestamp() + (int) (Math.random() * 100000000));
    }

    static public Long getTimestamp() {
        return System.currentTimeMillis() * 1000 * 1000;
    }

    static public void checkResponseHeader(XchainOuterClass.Header header, String msg) {
        if (header.getError() != XchainOuterClass.XChainErrorEnum.SUCCESS) {
            throw new RuntimeException("Error " + header.getError().toString() + " while " + msg);
        }
    }
}
