package com.baidu.xuperunion.api;

import com.baidu.xuperunion.config.Config;
import com.baidu.xuperunion.crypto.Hash;
import com.baidu.xuperunion.pb.XchainOuterClass;
import com.baidu.xuperunion.pb.XendorserOuterClass;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;

import java.math.BigInteger;
import java.util.*;

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

    /**
     * @param name Must be Account.getAuthrequireId
     * @return
     */
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
            if (Objects.equals(this.to, contractName)) {
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
        byte[] hash = Hash.doubleSha256((chainName + initiator.getAddress() + amount + false).getBytes());
        byte[] sign = initiator.getKeyPair().sign(hash);
        XchainOuterClass.SignatureInfo signature = XchainOuterClass.SignatureInfo.newBuilder()
                .setPublicKey(initiator.getKeyPair().getJSONPublicKey())
                .setSign(ByteString.copyFrom(sign))
                .build();

        XchainOuterClass.PreExecWithSelectUTXORequest request = XchainOuterClass.PreExecWithSelectUTXORequest.newBuilder()
                .setHeader(header)
                .setBcname(chainName)
                .setAddress(initiator.getAddress())
                .setTotalAmount(amount)
                .setSignInfo(signature)
                .setRequest(invokeRPCRequest)
                .build();

        XendorserClient ec = new XendorserClient(Config.getInstance().getEndorseServiceHost());
        Gson g = new Gson();
        XendorserOuterClass.EndorserResponse r = ec.getBlockingClient().endorserCall(XendorserOuterClass.EndorserRequest.newBuilder()
                .setHeader(header)
                .setBcName(chainName)
                .setRequestData(ByteString.copyFrom(g.toJson(request).getBytes()))
                .setRequestName("PreExecWithFee")
                .build());

        XchainOuterClass.PreExecWithSelectUTXOResponse pr = g.fromJson(r.getResponseData().toString(),XchainOuterClass.PreExecWithSelectUTXOResponse.class);

        //XchainOuterClass.PreExecWithSelectUTXOResponse response = client.getBlockingClient().preExecWithSelectUTXO(request);
        Common.checkResponseHeader(pr.getHeader(), "PreExec");
        return new Transaction(pr, this,client.getPlatformAccount());
    }
}
