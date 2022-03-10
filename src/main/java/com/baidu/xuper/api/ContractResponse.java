package com.baidu.xuper.api;

import lombok.Data;

import com.baidu.xuper.pb.XchainOuterClass;

@Data
public class ContractResponse {
    private final int status;
    private final String message;
    private final byte[] body;

    ContractResponse(XchainOuterClass.ContractResponse resp) {
        this.status = resp.getStatus();
        this.message = resp.getMessage();
        this.body = resp.getBody().toByteArray();
    }
}
