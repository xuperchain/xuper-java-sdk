package com.baidu.xuperunion.api;

import com.baidu.xuperunion.pb.XchainOuterClass;

public class Common {
    static public XchainOuterClass.Header newHeader() {
        return XchainOuterClass.Header.newBuilder()
                .setLogid(newNonce())
                .build();
    }

    static public String newNonce() {
        return String.valueOf(System.nanoTime()) + (int) (Math.random() * 100000000);
    }

    static public void checkResponseHeader(XchainOuterClass.Header header, String msg){
        if (header.getError() != XchainOuterClass.XChainErrorEnum.SUCCESS) {
            throw new RuntimeException("Error " + header.getError().toString() + " while " + msg);
        }
    }
}
