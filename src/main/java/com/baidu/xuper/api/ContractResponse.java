package com.baidu.xuper.api;

import com.baidu.xuper.pb.XchainOuterClass;

public class ContractResponse {
    private final int status;
    private final String message;
    private final byte[] body;

    ContractResponse(XchainOuterClass.ContractResponse resp) {
        this.status = resp.getStatus();
        this.message = resp.getMessage();
        this.body = resp.getBody().toByteArray();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public byte[] getBody() {
        return body;
    }

    public String getBodyStr() {
        return new String(body);
    }
}
