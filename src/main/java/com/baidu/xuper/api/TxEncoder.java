package com.baidu.xuper.api;

import com.baidu.xuper.crypto.Hash;
import com.baidu.xuper.pb.XchainOuterClass;
import com.baidu.xuper.pb.XchainOuterClass.*;
import com.google.gson.*;
import com.google.protobuf.ByteString;
import org.bouncycastle.util.encoders.Base64;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

class TxEncoder {
    //private static int anInt;
    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .registerTypeHierarchyAdapter(ByteString.class, new PbByteStringAdapter())
            .disableHtmlEscaping()
            .create();
    private StringBuilder buffer;

    TxEncoder() {
        buffer = new StringBuilder();
    }

    static byte[] makeTxDigest(XchainOuterClass.Transaction tx) {
        TxEncoder enc = new TxEncoder();
        return Hash.doubleSha256(enc.encodeTx(tx, false));
    }

    static byte[] makeTxID(XchainOuterClass.Transaction tx) {
        TxEncoder enc = new TxEncoder();
        return Hash.doubleSha256(enc.encodeTx(tx, true));
    }

    private void encode(Object obj) {
        String s = gson.toJson(obj);
        buffer.append(s);
        buffer.append("\n");
    }

    private void encode(ByteString bs) {
        if (bs == null){
            String s = gson.toJson(null);
            buffer.append(s);
            buffer.append("\n");
            return;
        }
        if (!bs.isEmpty()) {
            encode(Base64.toBase64String(bs.toByteArray()));
        }
    }

    byte[] encodeTx(XchainOuterClass.Transaction tx, boolean needSign) {
        for (TxInput input : tx.getTxInputsList()) {
            encode(input.getRefTxid());
            encode(input.getRefOffset());
            encode(input.getFromAddr());
            encode(input.getAmount());
            encode(input.getFrozenHeight());
        }
        Object[] txOutputs = new Object[tx.getTxOutputsCount()];
        for (int i = 0; i < tx.getTxOutputsCount(); i++) {
            txOutputs[i] = TxOutputBean.create(tx.getTxOutputs(i));
        }
        if (txOutputs.length > 0){
            encode(txOutputs);
        }else{
            encode(null);
        }

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
        Object[] invokes = null;
        if (tx.getContractRequestsCount() != 0) {
            invokes = new Object[tx.getContractRequestsCount()];
            for (int i = 0; i < tx.getContractRequestsCount(); i++) {
                invokes[i] = InvokeRequestBean.create(tx.getContractRequests(i));
            }
        }
        encode(invokes);

        encode(tx.getInitiator());
        if (tx.getAuthRequireCount() > 0) {
            encode(tx.getAuthRequireList().toArray());
        } else {
            encode((Object) null);
        }

        if (needSign) {
            Object[] sigs = null;
            if (tx.getInitiatorSignsCount() != 0) {
                sigs = new Object[tx.getInitiatorSignsCount()];
            }
            for (int i = 0; i < tx.getInitiatorSignsCount(); i++) {
                sigs[i] = SignatureInfoBean.create(tx.getInitiatorSigns(i));
            }
            encode(sigs);

            sigs = null;
            if (tx.getAuthRequireSignsCount() != 0) {
                sigs = new Object[tx.getAuthRequireSignsCount()];
            }
            for (int i = 0; i < tx.getAuthRequireSignsCount(); i++) {
                sigs[i] = SignatureInfoBean.create(tx.getAuthRequireSigns(i));
            }
            encode(sigs);
            if (tx.hasXuperSign()) {
                encode(tx.getXuperSign());
            }
        }
        encode(tx.getCoinbase());
        encode(tx.getAutogen());
//        try {
//            anInt++;
//            System.out.println("anInt:" + anInt);
//            System.out.println("txID:" + Arrays.toString(tx.getTxid().toByteArray()));
//            FileOutputStream f = new FileOutputStream("./tmp/tx" + anInt + ".pb");
//            f.write(tx.toByteArray());
//            f.close();
//            f = new FileOutputStream("./tmp/tx" + anInt + "txt");
//            f.write(buffer.toString().getBytes());
//            f.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return buffer.toString().getBytes();
    }

    private static class PbByteStringAdapter implements JsonSerializer<ByteString> {
        public JsonElement serialize(ByteString src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.toBase64String(src.toByteArray()));
        }
    }

    private static class TxOutputBean {
        static Object create(TxOutput pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (!pb.getAmount().isEmpty()) {
                m.put("amount", pb.getAmount());
            }
            if (!pb.getToAddr().isEmpty()) {
                m.put("to_addr", pb.getToAddr());
            }
            if (pb.getFrozenHeight() != 0) {
                m.put("frozen_height", pb.getFrozenHeight());
            }
            return m;
        }
    }

    private static class InvokeRequestBean {
        static Object create(InvokeRequest pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (!pb.getMethodName().isEmpty()) {
                m.put("module_name", pb.getModuleName());
            }
            if (!pb.getContractName().isEmpty()) {
                m.put("contract_name", pb.getContractName());
            }
            if (!pb.getMethodName().isEmpty()) {
                m.put("method_name", pb.getMethodName());
            }
            if (pb.getArgsCount() != 0) {
                TreeMap<String, ByteString> margs = new TreeMap<>();
                for (Map.Entry<String, ByteString> entry : pb.getArgsMap().entrySet()) {
                    if (entry.getValue().isEmpty()) {
                        margs.put(entry.getKey(), null);
                    } else {
                        margs.put(entry.getKey(), entry.getValue());
                    }
                }
                m.put("args", margs);
            }
            if (pb.getResourceLimitsCount() != 0) {
                Object[] resource_limits = new Object[pb.getResourceLimitsCount()];
                for (int i = 0; i < pb.getResourceLimitsCount(); i++) {
                    resource_limits[i] = ResourceLimitBean.create(pb.getResourceLimits(i));
                }
                m.put("resource_limits", resource_limits);
            }
            if (!pb.getAmount().isEmpty()) {
                m.put("amount", pb.getAmount());
            }
            return m;
        }
    }

    private static class ResourceLimitBean {
        static Object create(ResourceLimit pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (pb.getType().getNumber() != 0) {
                m.put("type", pb.getType().getNumber());
            }
            if (pb.getLimit() != 0) {
                m.put("limit", pb.getLimit());
            }
            return m;
        }
    }

    private static class SignatureInfoBean {
        static Object create(SignatureInfo pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (!pb.getPublicKey().isEmpty()) {
                m.put("PublicKey", pb.getPublicKey());
            }
            if (!pb.getSign().isEmpty()) {
                m.put("Sign", pb.getSign());
            }
            return m;
        }
    }
}
