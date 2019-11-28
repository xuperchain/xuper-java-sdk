package com.baidu.xuperunion.api;

import com.baidu.xuperunion.crypto.Hash;
import com.baidu.xuperunion.pb.XchainOuterClass;
import com.google.protobuf.ByteString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proposal {
    String chainName = "xuper";

    Account initiator;
    String to;
    BigInteger amount;

    String moduleName;
    String contractName;
    String methodName;
    Map<String, ByteString> args;

    List<String> authRequire;
    String desc;

    public Proposal setChainName(String name) {
        this.chainName = name;
        return this;
    }

    public Proposal setInitiator(Account initiator) {
        this.initiator = initiator;
        this.addAuthRequire(initiator.getAuthRequireId());
        return this;
    }

    public Proposal setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Proposal addAuthRequire(String name) {
        if (this.authRequire == null) {
            this.authRequire = new ArrayList<>();
        }
        this.authRequire.add(name);
        return this;
    }

    public Proposal transfer(String to, BigInteger amount) {
        this.to = to;
        this.amount = amount;
        return this;
    }

    public Proposal invokeContract(String module, String contract, String method, Map<String, byte[]> args) {
        this.moduleName = module;
        this.contractName = contract;
        this.methodName = method;
        this.args = new HashMap<>();
        for (Map.Entry<String, byte[]> entry : args.entrySet()) {
            this.args.put(entry.getKey(), ByteString.copyFrom(entry.getValue()));
        }
        return this;
    }

    public Transaction build(XuperClient client) throws Exception {
        if (this.initiator == null) {
            throw new Exception("missing initiator");
        }

        XchainOuterClass.Header header = Common.newHeader();
        XchainOuterClass.InvokeRequest.Builder invokeRequestBuilder = XchainOuterClass.InvokeRequest.newBuilder();
        if (moduleName != null && contractName != null && methodName != null && args != null) {
            invokeRequestBuilder.setModuleName(moduleName)
                    .setMethodName(methodName)
                    .setContractName(contractName)
                    .putAllArgs(args);
            // transfer to contract
            if (this.to.equals(contractName)) {
                invokeRequestBuilder.setAmount(this.amount.toString());
            }
        }
        XchainOuterClass.InvokeRequest invokeRequest = invokeRequestBuilder.build();

        XchainOuterClass.InvokeRPCRequest invokeRPCRequest = XchainOuterClass.InvokeRPCRequest.newBuilder()
                .setHeader(header)
                .setBcname(chainName)
                .addRequests(invokeRequest)
                .setInitiator(initiator.getAddress())
                .addAllAuthRequire(authRequire)
                .build();

        long amount = 0;
        if (this.amount != null) {
            amount = this.amount.longValue();
        }
        byte[] hash = Hash.doubleSha256((chainName + initiator.getPayableAddress() + amount + false).getBytes());
        byte[] sign = initiator.getKeyPair().sign(hash);
        XchainOuterClass.SignatureInfo signature = XchainOuterClass.SignatureInfo.newBuilder()
                .setPublicKey(initiator.getKeyPair().getJSONPublicKey())
                .setSign(ByteString.copyFrom(sign))
                .build();

        XchainOuterClass.PreExecWithSelectUTXORequest request = XchainOuterClass.PreExecWithSelectUTXORequest.newBuilder()
                .setHeader(header)
                .setBcname(chainName)
                .setAddress(initiator.getPayableAddress())
                .setTotalAmount(amount)
                .setSignInfo(signature)
                .setRequest(invokeRPCRequest)
                .build();

        XchainOuterClass.PreExecWithSelectUTXOResponse response = client.getBlockingClient().preExecWithSelectUTXO(request);
        Common.checkResponseHeader(response.getHeader(), "PreExec");
        return new Transaction(response, this);
    }
}
