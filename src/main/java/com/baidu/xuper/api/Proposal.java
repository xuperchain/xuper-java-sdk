package com.baidu.xuper.api;

import com.alibaba.fastjson.JSON;
import com.baidu.xuper.config.Config;
import com.baidu.xuper.crypto.Crypto;
import com.baidu.xuper.crypto.xchain.hash.Hash;
import com.baidu.xuper.pb.XchainOuterClass;
import com.baidu.xuper.pb.XendorserOuterClass;
import com.google.protobuf.ByteString;

import java.math.BigInteger;
import java.util.*;

public class Proposal {
    String chainName = "xuper";

    Account initiator;
    String to;
    BigInteger amount;
    String fee;

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

    public Proposal setFee(String fee) {
        this.fee = fee;
        return this;
    }

    /**
     * @param name Must be Account.getAuthrequireId
     * @return proposal
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

    public Transaction preExec(XuperClient client) {
        ArrayList<XchainOuterClass.InvokeRequest> requests = new ArrayList<>();
        requests.add(XchainOuterClass.InvokeRequest.newBuilder()
                .setModuleName(this.moduleName)
                .setMethodName(this.methodName)
                .setContractName(this.contractName)
                .putAllArgs(this.args)
                .build());

        XchainOuterClass.InvokeRPCRequest request = XchainOuterClass.InvokeRPCRequest.newBuilder()
                .setBcname(this.chainName)
                .addAllRequests(requests)
                .setInitiator(this.initiator.getAKAddress())
                .addAllAuthRequire(this.authRequire)
                .build();
        XchainOuterClass.InvokeRPCResponse invokeRPCResponse = client.getBlockingClient().preExec(request);
        return new Transaction(invokeRPCResponse, this);
    }

    public Transaction build(XuperClient client) {
        if (this.initiator == null) {
            throw new RuntimeException("missing initiator");
        }

        XchainOuterClass.Header header = Common.newHeader();
        XchainOuterClass.InvokeRequest.Builder invokeRequestBuilder = null;
        if (moduleName != null && contractName != null && methodName != null && args != null) {
            invokeRequestBuilder = XchainOuterClass.InvokeRequest.newBuilder();
            invokeRequestBuilder.setModuleName(moduleName)
                    .setMethodName(methodName)
                    .setContractName(contractName)
                    .putAllArgs(args);
            // transfer to contract
            if (Objects.equals(this.to, contractName)) {
                invokeRequestBuilder.setAmount(this.amount.toString());
            }
        }
        XchainOuterClass.InvokeRequest invokeRequest = null;
        if (invokeRequestBuilder != null) {
            invokeRequest = invokeRequestBuilder.build();
        }

        int extAmount = 0;
        try {
            if (Config.getInstance().getComplianceCheck().isNeedComplianceCheck()) {
                if (Config.getInstance().getComplianceCheck().isNeedComplianceCheckFee()) {
                    extAmount = Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceFee();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        XchainOuterClass.InvokeRPCRequest.Builder invokeRPCBuilder = XchainOuterClass.InvokeRPCRequest.newBuilder()
                .setHeader(header)
                .setBcname(chainName)
                .setInitiator(initiator.getAKAddress());

        if (this.authRequire != null) {
            invokeRPCBuilder.addAllAuthRequire(this.authRequire);
        } else {
            invokeRPCBuilder.addAuthRequire(this.initiator.getAuthRequireId());
        }

        if (invokeRequest != null) {
            invokeRPCBuilder.addRequests(invokeRequest);
        }

        XchainOuterClass.InvokeRPCRequest invokeRPCRequest = invokeRPCBuilder.build();

        long amount = 0;
        if (this.amount != null) {
            amount = this.amount.longValue();
        }

        if (this.fee != null) {
            amount += Long.parseLong(fee);
        }
        amount += extAmount;

        try {
            byte[] hash = Hash.doubleSha256((chainName + initiator.getAKAddress() + amount + false).getBytes());

//            byte[] sign = initiator.getKeyPair().sign(hash);
            Crypto cli = CryptoClient.getCryptoClient();
            byte[] sign = cli.signECDSA(hash, initiator.getKeyPair().getPrivateKey());

            XchainOuterClass.SignatureInfo signature = XchainOuterClass.SignatureInfo.newBuilder()
                    .setPublicKey(initiator.getKeyPair().getJSONPublicKey())
                    .setSign(ByteString.copyFrom(sign))
                    .build();

            XchainOuterClass.PreExecWithSelectUTXORequest request = XchainOuterClass.PreExecWithSelectUTXORequest.newBuilder()
                    .setHeader(header)
                    .setBcname(chainName)
                    .setAddress(initiator.getAKAddress())
                    .setTotalAmount(amount)
                    .setSignInfo(signature)
                    .setRequest(invokeRPCRequest)
                    .build();

            XchainOuterClass.PreExecWithSelectUTXOResponse pr;
            if (Config.hasConfigFile() && Config.getInstance().getComplianceCheck().isNeedComplianceCheck()) {
                XendorserOuterClass.EndorserResponse r = client.getXendorserClient().getBlockingClient().endorserCall(XendorserOuterClass.EndorserRequest.newBuilder()
                        .setHeader(header)
                        .setBcName(chainName)
                        .setRequestData(ByteString.copyFrom(pb2JsonString(request).getBytes()))
                        .setRequestName("PreExecWithFee")
                        .build());

                pr = JsonUtils.json2PreExecWithSelUTXO(new String(r.getResponseData().toByteArray()));
            } else {
                pr = client.getBlockingClient().preExecWithSelectUTXO(request);
            }

            Common.checkResponseHeader(pr.getHeader(), "PreExec");
            return new Transaction(pr, this, client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String pb2JsonString(XchainOuterClass.PreExecWithSelectUTXORequest request) {
        LinkedHashMap<String, Object> m = new LinkedHashMap<>();
        if (!request.getBcname().isEmpty()) {
            m.put("bcname", request.getBcname());
        }

        if (!request.getAddress().isEmpty()) {
            m.put("address", request.getAddress());
        }

        m.put("totalAmount", request.getTotalAmount());

        LinkedHashMap<String, Object> m1 = new LinkedHashMap<>();
        if (!request.getBcname().isEmpty()) {
            m1.put("bcname", request.getBcname());
        }

        m1.put("initiator", request.getRequest().getInitiator());
        m1.put("auth_require", request.getRequest().getAuthRequireList());

        ArrayList<Object> l = new ArrayList<>();
        for (XchainOuterClass.InvokeRequest r : request.getRequest().getRequestsList()) {
            LinkedHashMap<String, Object> m2 = new LinkedHashMap<>();
            if (!r.getModuleName().isEmpty()) {
                m2.put("module_name", r.getModuleName());
            }
            if (!r.getContractName().isEmpty()) {
                m2.put("contract_name", r.getContractName());
            }
            if (!r.getMethodName().isEmpty()) {
                m2.put("method_name", r.getMethodName());
            }

            if (r.getArgsMap().size() > 0) {
                LinkedHashMap<String, Object> m3 = new LinkedHashMap<>();
                for (Map.Entry<String, ByteString> entry : r.getArgsMap().entrySet()) {
                    m3.put(entry.getKey(), entry.getValue().toByteArray());
                }
                m2.put("args", m3);
            }

            if (!r.getAmount().isEmpty()) {
                m2.put("amount", r.getAmount());
            }

            l.add(m2);
        }

        if (l.size() > 0) {
            m1.put("requests", l);
        }

        m.put("request", m1);
        return JSON.toJSONString(m);
    }
}
