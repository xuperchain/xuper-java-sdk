package com.baidu.xuper.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baidu.xuper.pb.XchainOuterClass;
import com.google.protobuf.ByteString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    public static XchainOuterClass.PreExecWithSelectUTXOResponse json2PreExecWithSelUTXO(String json) {
        XchainOuterClass.PreExecWithSelectUTXOResponse.Builder builder = XchainOuterClass.PreExecWithSelectUTXOResponse.newBuilder();
        PreExecWithSelectUTXOResponse response = JSON.parseObject(json, PreExecWithSelectUTXOResponse.class);

        if (response.header != null) {
            XchainOuterClass.Header.Builder headerBuilder = XchainOuterClass.Header.newBuilder();
            if (response.header.logid != null) {
                headerBuilder.setLogid(response.header.logid);
            }
            if (response.header.from_node != null) {
                headerBuilder.setFromNode(response.header.from_node);
            }
            if (response.header.error != 0) {
                headerBuilder.setError(XchainOuterClass.XChainErrorEnum.forNumber(response.header.error));
            }
            builder.setHeader(headerBuilder.build());
        }

        if (response.bcname != null) {
            builder.setBcname(response.bcname);
        }

        if (response.response != null) {
            XchainOuterClass.InvokeResponse.Builder irBuilder = XchainOuterClass.InvokeResponse.newBuilder();
            if (response.response.inputs != null) {
                for (TxInputExt txInputExt : response.response.inputs) {
                    XchainOuterClass.TxInputExt.Builder txInputExtBuilder = XchainOuterClass.TxInputExt.newBuilder();
                    if (txInputExt.bucket != null) {
                        txInputExtBuilder.setBucket(txInputExt.bucket);
                    }
                    if (txInputExt.key != null) {
                        txInputExtBuilder.setKey(ByteString.copyFrom(txInputExt.key));
                    }
                    if (txInputExt.ref_txid != null) {
                        txInputExtBuilder.setRefTxid(ByteString.copyFrom(txInputExt.ref_txid));
                    }
                    if (txInputExt.ref_offset != 0) {
                        txInputExtBuilder.setRefOffset(txInputExt.ref_offset);
                    }
                    irBuilder.addInputs(txInputExtBuilder.build());
                }
            }

            if (response.response.outputs != null) {
                for (TxOutputExt txOutputExt : response.response.outputs) {
                    XchainOuterClass.TxOutputExt.Builder toeBuilder = XchainOuterClass.TxOutputExt.newBuilder();
                    if (txOutputExt.bucket != null) {
                        toeBuilder.setBucket(txOutputExt.bucket);
                    }
                    if (txOutputExt.key != null) {
                        toeBuilder.setKey(ByteString.copyFrom(txOutputExt.key));
                    }
                    if (txOutputExt.value != null) {
                        toeBuilder.setValue(ByteString.copyFrom(txOutputExt.value));
                    }
                    irBuilder.addOutputs(toeBuilder.build());
                }
            }

            if (response.response.response != null) {
                for (byte[] temp : response.response.response) {
                    irBuilder.addResponse(ByteString.copyFrom(temp));
                }
            }

            if (response.response.gas_used != 0) {
                irBuilder.setGasUsed(response.response.gas_used);
            }

            if (response.response.requests != null) {
                for (InvokeRequest r : response.response.requests) {
                    XchainOuterClass.InvokeRequest.Builder ireqBuilder = XchainOuterClass.InvokeRequest.newBuilder();
                    if (r.module_name != null) {
                        ireqBuilder.setModuleName(r.module_name);
                    }
                    if (r.contract_name != null) {
                        ireqBuilder.setContractName(r.contract_name);
                    }
                    if (r.method_name != null) {
                        ireqBuilder.setMethodName(r.method_name);
                    }

                    if (r.amount != null) {
                        ireqBuilder.setAmount(r.amount);
                    }
                    if (r.args != null) {
                        for (Map.Entry<String, byte[]> entry : r.args.entrySet()) {
                            if (entry.getValue() == null) {
                                ireqBuilder.putArgs(entry.getKey(), ByteString.EMPTY);
                            } else {
                                ireqBuilder.putArgs(entry.getKey(), ByteString.copyFrom(entry.getValue()));
                            }
                        }
                    }
                    if (r.resource_limits != null) {
                        for (ResourceLimit rl : r.resource_limits) {
                            XchainOuterClass.ResourceLimit.Builder rlBuilder = XchainOuterClass.ResourceLimit.newBuilder();
                            rlBuilder.setLimit(rl.limit);
                            rlBuilder.setType(XchainOuterClass.ResourceType.forNumber(rl.type));
                            ireqBuilder.addResourceLimits(rlBuilder.build());
                        }
                    }
                    irBuilder.addRequests(ireqBuilder.build());
                }
            }

            if (response.response.responses != null) {
                for (ContractResponse cr : response.response.responses) {
                    XchainOuterClass.ContractResponse.Builder crBuilder = XchainOuterClass.ContractResponse.newBuilder();
                    if (cr.message != null) {
                        crBuilder.setMessage(cr.message);
                    }
                    if (cr.body != null) {
                        crBuilder.setBody(ByteString.copyFrom(cr.body));
                    }
                    crBuilder.setStatus(cr.status);
                    irBuilder.addResponses(crBuilder.build());
                }
            }

            if (response.response.utxoInputs != null) {
                for (TxInput ti : response.response.utxoInputs) {
                    XchainOuterClass.TxInput.Builder tiBuilder = XchainOuterClass.TxInput.newBuilder();
                    if (ti.ref_txid != null) {
                        tiBuilder.setRefTxid(ByteString.copyFrom(ti.ref_txid));
                    }
                    tiBuilder.setRefOffset(ti.ref_offset);
                    tiBuilder.setFrozenHeight(ti.frozen_height);
                    if (ti.from_addr != null) {
                        tiBuilder.setFromAddr(ByteString.copyFrom(ti.from_addr));
                    }
                    if (ti.amount != null) {
                        tiBuilder.setAmount(ByteString.copyFrom(ti.amount));
                    }
                    irBuilder.addUtxoInputs(tiBuilder.build());
                }
            }

            if (response.response.utxoOutputs != null) {
                for (TxOutput to : response.response.utxoOutputs) {
                    XchainOuterClass.TxOutput.Builder toBuilder = XchainOuterClass.TxOutput.newBuilder();
                    if (to.amount != null) {
                        toBuilder.setAmount(ByteString.copyFrom(to.amount));
                    }
                    if (to.to_addr != null) {
                        toBuilder.setToAddr(ByteString.copyFrom(to.to_addr));
                    }
                    toBuilder.setFrozenHeight(to.frozen_height);
                    irBuilder.addUtxoOutputs(toBuilder.build());
                }
            }

            builder.setResponse(irBuilder.build());
        }

        if (response.utxoOutput != null) {
            XchainOuterClass.UtxoOutput.Builder uoBuilder = XchainOuterClass.UtxoOutput.newBuilder();
            if (response.utxoOutput.totalSelected != null) {
                uoBuilder.setTotalSelected(response.utxoOutput.totalSelected);
            }

            if (response.utxoOutput.header != null) {
                XchainOuterClass.Header.Builder headerBuilder = XchainOuterClass.Header.newBuilder();
                if (response.utxoOutput.header.logid != null) {
                    headerBuilder.setLogid(response.utxoOutput.header.logid);
                }
                if (response.utxoOutput.header.from_node != null) {
                    headerBuilder.setFromNode(response.utxoOutput.header.from_node);
                }
                if (response.utxoOutput.header.error != 0) {
                    headerBuilder.setError(XchainOuterClass.XChainErrorEnum.forNumber(response.utxoOutput.header.error));
                }
                uoBuilder.setHeader(headerBuilder.build());
            }

            for (Utxo utxo : response.utxoOutput.utxoList) {
                XchainOuterClass.Utxo.Builder uBuilder = XchainOuterClass.Utxo.newBuilder();
                if (utxo.amount != null) {
                    uBuilder.setAmount(ByteString.copyFrom(utxo.amount));
                }
                if (utxo.toAddr != null) {
                    uBuilder.setToAddr(ByteString.copyFrom(utxo.toAddr));
                }
                if (utxo.toPubkey != null) {
                    uBuilder.setToPubkey(ByteString.copyFrom(utxo.toPubkey));
                }
                if (utxo.refTxid != null) {
                    uBuilder.setRefTxid(ByteString.copyFrom(utxo.refTxid));
                }
                uBuilder.setRefOffset(utxo.refOffset);
                uoBuilder.addUtxoList(uBuilder.build());
            }

            builder.setUtxoOutput(uoBuilder.build());
        }

        return builder.build();
    }


    public static String TxStatus2Json(XchainOuterClass.TxStatus txStatus) {
        TxStatus result = new TxStatus();
        if (txStatus.hasHeader()) {
            XchainOuterClass.Header header = txStatus.getHeader();
            Header h = new Header();
            h.logid = header.getLogid();
            h.from_node = header.getFromNode();
            h.error = header.getErrorValue();
            result.header = h;
        }

        result.bcname = txStatus.getBcname();

        if (txStatus.getTxid() != null && txStatus.getTxid().size() > 0) {
            result.txid = txStatus.getTxid().toByteArray();
        }
        result.status = txStatus.getStatusValue();
        result.distance = txStatus.getDistance();

        if (txStatus.getTx() != null) {
            XchainOuterClass.Transaction t = txStatus.getTx();
            Transaction rt = new Transaction();
            rt.txid = t.getTxid().toByteArray();
            if (t.getBlockid() != null && t.getBlockid().size() > 0) {
                rt.blockid = t.getBlockid().toByteArray();
            }
            if (t.getDesc().toByteArray() != null && t.getDesc().toByteArray().length > 0) {
                rt.desc = t.getDesc().toByteArray();
            }
            if (t.getCoinbase()) {
                rt.coinbase = t.getCoinbase();
            }
            if (t.getNonce() != null && !t.getNonce().isEmpty()) {
                rt.nonce = t.getNonce();
            }
            rt.timestamp = t.getTimestamp();
            rt.version = t.getVersion();
            rt.initiator = t.getInitiator();
            rt.auth_require = t.getAuthRequireList().toArray(new String[t.getAuthRequireCount()]);
            rt.received_timestamp = t.getReceivedTimestamp();

            if (t.getTxInputsList() != null && t.getTxInputsList().size() > 0) {
                ArrayList<TxInput> tis = new ArrayList<>();
                for (XchainOuterClass.TxInput ti : t.getTxInputsList()) {
                    TxInput newTxInput = new TxInput();
                    newTxInput.ref_txid = ti.getRefTxid().toByteArray();
                    newTxInput.ref_offset = ti.getRefOffset();
                    newTxInput.from_addr = ti.getFromAddr().toByteArray();
                    newTxInput.amount = ti.getAmount().toByteArray();
                    newTxInput.frozen_height = ti.getFrozenHeight();
                    tis.add(newTxInput);
                }
                rt.tx_inputs = tis.toArray(new TxInput[0]);
            }

            if (t.getTxOutputsList() != null && t.getTxOutputsList().size() > 0) {
                ArrayList<TxOutput> tos = new ArrayList<>();
                for (XchainOuterClass.TxOutput to : t.getTxOutputsList()) {
                    TxOutput newTxOutput = new TxOutput();
                    newTxOutput.amount = to.getAmount().toByteArray();
                    newTxOutput.to_addr = to.getToAddr().toByteArray();
                    newTxOutput.frozen_height = to.getFrozenHeight();
                    tos.add(newTxOutput);
                }
                rt.tx_outputs = tos.toArray(new TxOutput[0]);
            }

            if (t.getTxInputsExtList() != null && t.getTxInputsExtList().size() > 0) {
                ArrayList<TxInputExt> tis = new ArrayList<>();
                for (XchainOuterClass.TxInputExt tie : t.getTxInputsExtList()) {
                    TxInputExt newTxInput = new TxInputExt();
                    if (tie.getBucket() != null && !tie.getBucket().isEmpty()) {
                        newTxInput.bucket = tie.getBucket();
                    }
                    if (tie.getKey() != null && !tie.getKey().isEmpty()) {
                        newTxInput.key = tie.getKey().toByteArray();
                    }
                    if (tie.getRefTxid() != null && !tie.getRefTxid().isEmpty()) {
                        newTxInput.ref_txid = tie.getRefTxid().toByteArray();
                    }
                    if (tie.getRefOffset() != 0) {
                        newTxInput.ref_offset = tie.getRefOffset();
                    }
                    tis.add(newTxInput);
                }
                rt.tx_inputs_ext = tis.toArray(new TxInputExt[0]);
            }

            if (t.getTxOutputsExtList() != null && t.getTxOutputsExtList().size() > 0) {
                ArrayList<TxOutputExt> tos = new ArrayList<>();
                for (XchainOuterClass.TxOutputExt to : t.getTxOutputsExtList()) {
                    TxOutputExt newTxOutput = new TxOutputExt();
                    if (to.getBucket() != null && !to.getBucket().isEmpty()) {
                        newTxOutput.bucket = to.getBucket();
                    }
                    if (to.getKey() != null && !to.getKey().isEmpty()) {
                        newTxOutput.key = to.getKey().toByteArray();
                    }
                    if (to.getValue() != null && !to.getValue().isEmpty()) {
                        newTxOutput.value = to.getValue().toByteArray();
                    }
                    tos.add(newTxOutput);
                }
                rt.tx_outputs_ext = tos.toArray(new TxOutputExt[0]);
            }

            if (t.getContractRequestsList() != null && t.getContractRequestsList().size() > 0) {
                ArrayList<InvokeRequest> irs = new ArrayList<>();
                for (XchainOuterClass.InvokeRequest item : t.getContractRequestsList()) {
                    InvokeRequest newIR = new InvokeRequest();
                    if (item.getModuleName() != null && !item.getModuleName().isEmpty()) {
                        newIR.module_name = item.getModuleName();
                    }
                    if (item.getContractName() != null && !item.getContractName().isEmpty()) {
                        newIR.contract_name = item.getContractName();
                    }
                    if (item.getMethodName() != null && !item.getMethodName().isEmpty()) {
                        newIR.method_name = item.getMethodName();
                    }
                    if (item.getAmount() != null && !item.getAmount().isEmpty()) {
                        newIR.amount = item.getAmount();
                    }

                    if (item.getArgsMap().size() != 0) {
                        HashMap<String, byte[]> m = new HashMap<>();
                        for (Map.Entry<String, ByteString> entry : item.getArgsMap().entrySet()) {
                            if (entry.getValue().isEmpty()) {
                                m.put(entry.getKey(), null);
                            } else {
                                m.put(entry.getKey(), entry.getValue().toByteArray());
                            }
                        }
                        newIR.args = m;
                    }

                    if (item.getResourceLimitsList() != null && item.getResourceLimitsList().size() > 0) {
                        ArrayList<ResourceLimit> rls = new ArrayList<>();
                        for (XchainOuterClass.ResourceLimit rl : item.getResourceLimitsList()) {
                            ResourceLimit r = new ResourceLimit();
                            if (rl.getTypeValue() != 0) {
                                r.type = rl.getTypeValue();
                            }
                            if (rl.getLimit() != 0) {
                                r.limit = rl.getLimit();
                            }
                            rls.add(r);
                        }
                        newIR.resource_limits = rls.toArray(new ResourceLimit[0]);
                    }

                    irs.add(newIR);
                }
                rt.contract_requests = irs.toArray(new InvokeRequest[0]);
            }

            if (t.getInitiatorSignsList() != null && t.getInitiatorSignsList().size() > 0) {
                ArrayList<SignatureInfo> sis = new ArrayList<>();
                for (XchainOuterClass.SignatureInfo s : t.getInitiatorSignsList()) {
                    SignatureInfo rs = new SignatureInfo();
                    rs.PublicKey = s.getPublicKey();
                    rs.Sign = s.getSign().toByteArray();
                    sis.add(rs);
                }
                rt.initiator_signs = sis.toArray(new SignatureInfo[0]);
            }

            if (t.getAuthRequireSignsList() != null && t.getAuthRequireSignsList().size() > 0) {
                ArrayList<SignatureInfo> sis = new ArrayList<>();
                for (XchainOuterClass.SignatureInfo s : t.getAuthRequireSignsList()) {
                    SignatureInfo rs = new SignatureInfo();
                    rs.PublicKey = s.getPublicKey();
                    rs.Sign = s.getSign().toByteArray();
                    sis.add(rs);
                }
                rt.auth_require_signs = sis.toArray(new SignatureInfo[0]);
            }

            if (t.getXuperSign() != null && t.hasXuperSign()) {
                XuperSignature xs = new XuperSignature();
                xs.signature = t.getXuperSign().getSignature().toByteArray();
                ArrayList<byte[]> pubs = new ArrayList<>();
                for (ByteString bs : t.getXuperSign().getPublicKeysList()) {
                    pubs.add(bs.toByteArray());
                }
                xs.public_keys = pubs.toArray(new byte[][]{});
                rt.xuper_sign = xs;
            }

            if (t.hasModifyBlock() && t.getModifyBlock() != null) {
                XchainOuterClass.ModifyBlock xmb = t.getModifyBlock();
                ModifyBlock mb = new ModifyBlock();
                if (xmb.getEffectiveTxid() != null && !xmb.getEffectiveTxid().isEmpty()) {
                    mb.effective_txid = xmb.getEffectiveTxid();
                }
                if (xmb.getMarked()) {
                    mb.marked = xmb.getMarked();
                }
                if (xmb.getEffectiveHeight() > 0) {
                    mb.effective_height = xmb.getEffectiveHeight();
                }
                if (xmb.getPublicKey() != null && !xmb.getPublicKey().isEmpty()) {
                    mb.public_key = xmb.getPublicKey();
                }
                if (xmb.getSign() != null && !xmb.getSign().isEmpty()) {
                    mb.sign = xmb.getSign();
                }
                rt.modify_block = mb;
            }

            result.tx = rt;
        }

        return JSON.toJSONString(result,
                SerializerFeature.NotWriteRootClassName,
                SerializerFeature.NotWriteDefaultValue,
                SerializerFeature.WriteMapNullValue
        );
    }

    public static class PreExecWithSelectUTXOResponse {
        public Header header;
        public String bcname;
        public InvokeResponse response;
        public UtxoOutput utxoOutput;
    }

    public static class Header {
        public String logid;
        public String from_node;
        public int error;
    }

    public static class InvokeResponse {
        public TxInputExt[] inputs;
        public TxOutputExt[] outputs;
        public byte[][] response;
        public long gas_used;
        public InvokeRequest[] requests;
        public ContractResponse[] responses;
        public TxInput[] utxoInputs;
        public TxOutput[] utxoOutputs;
    }

    public static class TxInputExt {
        public String bucket;
        public byte[] key;
        public byte[] ref_txid;
        public int ref_offset;
    }

    public static class TxOutputExt {
        public String bucket;
        public byte[] key;
        public byte[] value;
    }

    public static class InvokeRequest {
        public String module_name;
        public String contract_name;
        public String method_name;
        public Map<String, byte[]> args;
        public ResourceLimit[] resource_limits;
        public String amount;
    }

    public static class ResourceLimit {
        public int type;
        public long limit;
    }

    public static class ContractResponse {
        public int status;
        public String message;
        public byte[] body;
    }

    public static class TxInput {
        public byte[] ref_txid;
        public int ref_offset;
        public byte[] from_addr;
        public byte[] amount;
        public long frozen_height;
    }

    public static class TxOutput {
        public byte[] amount;
        public byte[] to_addr;
        public long frozen_height;
    }

    public static class UtxoOutput {
        public Header header;
        public Utxo[] utxoList;
        public String totalSelected;
    }

    public static class Utxo {
        public byte[] amount;
        public byte[] toAddr;
        public byte[] toPubkey;
        public byte[] refTxid;
        public int refOffset;
    }

    public static class TxStatus {
        public Header header;
        public String bcname;
        public byte[] txid;
        public int status;
        public long distance;
        public Transaction tx;
    }

    public static class Transaction {
        public byte[] txid;
        public byte[] blockid;
        public TxInput[] tx_inputs;
        public TxOutput[] tx_outputs;
        public byte[] desc;
        public boolean coinbase;
        public String nonce;
        public long timestamp;
        public int version;
        public boolean autogen;
        public TxInputExt[] tx_inputs_ext;
        public TxOutputExt[] tx_outputs_ext;
        public InvokeRequest[] contract_requests;
        public String initiator;
        public String[] auth_require;
        public SignatureInfo[] initiator_signs;
        public SignatureInfo[] auth_require_signs;
        public long received_timestamp;
        public XuperSignature xuper_sign;
        public ModifyBlock modify_block;
        //public HDInfo HD_info;
    }

    public static class HDInfo {
        public byte[] hd_public_key;
        public byte[] original_hash;
    }

    public static class ModifyBlock {
        public String effective_txid;
        public boolean marked;
        public long effective_height;
        public String public_key;
        public String sign;
    }

    public static class XuperSignature {
        public byte[][] public_keys;
        public byte[] signature;

    }

    public static class SignatureInfo {
        public String PublicKey;
        public byte[] Sign;
    }
}
