package com.baidu.xuperunion.api;

import com.baidu.xuperunion.crypto.Hash;
import com.baidu.xuperunion.pb.XchainOuterClass;
import com.baidu.xuperunion.pb.XchainOuterClass.*;
import com.google.gson.*;
import com.google.protobuf.ByteString;
import org.bouncycastle.util.encoders.Base64;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

class TxEncoder {
    private static final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(ByteString.class, new PbByteStringAdapter())
            .disableHtmlEscaping()
            .create();
    private StringBuilder buffer;

    TxEncoder() {
        buffer = new StringBuilder();
    }

    private void encode(Object obj) {
        String s = gson.toJson(obj);
        buffer.append(s);
        buffer.append("\n");
    }

    private void encode(ByteString bs) {
        if (!bs.isEmpty()) {
            encode(Base64.toBase64String(bs.toByteArray()));
        }
    }

    byte[] makeTxDigest(XchainOuterClass.Transaction tx) {
        return Hash.doubleSha256(encodeTx(tx, false));
    }

    byte[] makeTxID(XchainOuterClass.Transaction tx) {
        return Hash.doubleSha256(encodeTx(tx, true));
    }

    private byte[] encodeTx(XchainOuterClass.Transaction tx, boolean needSign) {
        for (TxInput input : tx.getTxInputsList()) {
            encode(input.getRefTxid());
            encode(input.getRefOffset());
            encode(input.getFromAddr());
            encode(input.getAmount());
            encode(input.getFrozenHeight());
        }
        TxOutputBean[] txOutputs = new TxOutputBean[tx.getTxOutputsCount()];
        for (int i = 0; i < tx.getTxOutputsCount(); i++) {
            txOutputs[i] = new TxOutputBean(tx.getTxOutputs(i));
        }
        encode(txOutputs);

        encode(tx.getDesc());
        encode(tx.getNonce());
        encode(tx.getTimestamp());
        encode(tx.getVersion());
        for (TxInputExt input : tx.getTxInputsExtList()) {
            encode(input.getBucket());
            encode(input.getKey());
            encode(input.getRefTxid());
            encode(input.getRefOffset());
        }
        for (TxOutputExt output : tx.getTxOutputsExtList()) {
            encode(output.getBucket());
            encode(output.getKey());
            encode(output.getValue());
        }
        InvokeRequestBean[] invokes = null;
        if (tx.getContractRequestsCount() != 0) {
            invokes = new InvokeRequestBean[tx.getContractRequestsCount()];
            for (int i = 0; i < tx.getContractRequestsCount(); i++) {
                invokes[i] = new InvokeRequestBean(tx.getContractRequests(i));
            }
        }
        encode(invokes);

        encode(tx.getInitiator());
        encode(tx.getAuthRequireList().toArray());

        if (needSign) {
            SignatureInfoBean[] sigs = new SignatureInfoBean[tx.getInitiatorSignsCount()];
            for (int i = 0; i < tx.getInitiatorSignsCount(); i++) {
                sigs[i] = new SignatureInfoBean(tx.getInitiatorSigns(i));
            }
            encode(sigs);

            sigs = new SignatureInfoBean[tx.getAuthRequireCount()];
            for (int i = 0; i < tx.getInitiatorSignsCount(); i++) {
                sigs[i] = new SignatureInfoBean(tx.getAuthRequireSigns(i));
            }
            encode(sigs);
            if (tx.hasXuperSign()) {
                encode(sigs);
            }
        }
        encode(tx.getCoinbase());
        encode(tx.getAutogen());
//        FileOutputStream f = new FileOutputStream("tx.pb");
//        f.write(tx.toByteArray());
//        f.close();
//        f = new FileOutputStream("tx.txt");
//        f.write(buffer.toString().getBytes());
//        f.close();
        return buffer.toString().getBytes();
    }

    private static class PbByteStringAdapter implements JsonSerializer<ByteString> {
        public JsonElement serialize(ByteString src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.toBase64String(src.toByteArray()));
        }
    }

    private static class TxOutputBean {
        ByteString amount;
        ByteString to_addr;
        Long frozen_height;

        TxOutputBean(TxOutput pb) {
            if (!pb.getAmount().isEmpty()) {
                this.amount = pb.getAmount();
            }
            if (!pb.getToAddr().isEmpty()) {
                this.to_addr = pb.getToAddr();
            }
            if (pb.getFrozenHeight() != 0) {
                this.frozen_height = pb.getFrozenHeight();
            }
        }
    }

    private static class InvokeRequestBean {
        String module_name;
        String contract_name;
        String method_name;
        Map<String, ByteString> args;
        ResourceLimitBean[] resource_limits;
        String amount;

        InvokeRequestBean(InvokeRequest pb) {
            if (!pb.getMethodName().isEmpty()) {
                module_name = pb.getModuleName();
            }
            if (!pb.getContractName().isEmpty()) {
                contract_name = pb.getContractName();
            }
            if (!pb.getMethodName().isEmpty()) {
                method_name = pb.getMethodName();
            }
            if (pb.getResourceLimitsCount() != 0) {
                args = new TreeMap<>();
                args.putAll(pb.getArgsMap());
                resource_limits = new ResourceLimitBean[pb.getResourceLimitsCount()];
                for (int i = 0; i < pb.getResourceLimitsCount(); i++) {
                    resource_limits[i] = new ResourceLimitBean(pb.getResourceLimits(i));
                }
            }
            if (!pb.getAmount().isEmpty()) {
                amount = pb.getAmount();
            }
        }
    }

    private static class ResourceLimitBean {
        Integer type;
        Long limit;

        ResourceLimitBean(ResourceLimit pb) {
            if (pb.getType().getNumber() != 0) {
                type = pb.getType().getNumber();
            }
            if (pb.getLimit() != 0) {
                limit = pb.getLimit();
            }
        }
    }

    private static class SignatureInfoBean {
        String PublicKey;
        ByteString Sign;

        SignatureInfoBean(SignatureInfo pb) {
            if (!pb.getPublicKey().isEmpty()) {
                PublicKey = pb.getPublicKey();
            }
            if (!pb.getSign().isEmpty()) {
                Sign = pb.getSign();
            }
        }
    }
}
