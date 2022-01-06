package com.baidu.xuper.api;

import com.baidu.xuper.config.Config;
import com.baidu.xuper.crypto.ECKeyPair;
import com.baidu.xuper.pb.XchainOuterClass;
import com.baidu.xuper.pb.XendorserOuterClass;
import com.google.protobuf.ByteString;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transaction {
    static public final int txVersion = 1;
    private final Proposal proposal;
    private XchainOuterClass.Transaction.Builder txBuilder;
    private XchainOuterClass.Transaction pbtx;
    private byte[] txdigest;
    private ContractResponse contractResponse;
    private long gasUsed;

    /**
     * used to build a transaction from a protobuf tx
     * NOTE: the contractResponse and gasUsed will be null
     *
     * @param chainName the name of chain
     * @param tx        the protobuf transaction
     */
    public Transaction(String chainName, XchainOuterClass.Transaction tx) {
        proposal = new Proposal().setChainName(chainName);
        txBuilder = tx.toBuilder();
        pbtx = tx;
    }

    Transaction(XchainOuterClass.InvokeRPCResponse rpcResponse, Proposal proposal) {
        this.proposal = proposal;
        this.gasUsed = rpcResponse.getResponse().getGasUsed();
        if (rpcResponse.getResponse().getResponseCount() != 0) {
            this.contractResponse = new ContractResponse(rpcResponse.getResponse().getResponses(rpcResponse.getResponse().getResponseCount() - 1));
            if (this.contractResponse.getStatus() >= 400) {
                throw new RuntimeException("contract error status:"
                        + this.contractResponse.getStatus()
                        + " message:" + this.contractResponse.getMessage());
            }
        }
    }

    Transaction(XchainOuterClass.PreExecWithSelectUTXOResponse response, Proposal proposal, XuperClient client) throws Exception {
        XchainOuterClass.InvokeResponse invokeResponse = response.getResponse();
        this.proposal = proposal;
        this.gasUsed = invokeResponse.getGasUsed();
        if (invokeResponse.getResponseCount() != 0) {
            this.contractResponse = new ContractResponse(invokeResponse.getResponses(invokeResponse.getResponseCount() - 1));
            if (this.contractResponse.getStatus() >= 400) {
                throw new Exception("contract error status:"
                        + this.contractResponse.getStatus()
                        + " message:" + this.contractResponse.getMessage());
            }
        }
        try {
            if (!Config.hasConfigFile() || !Config.getInstance().getComplianceCheck().getIsNeedComplianceCheck()) {
                genRealTxOnly(response, proposal);
                return;
            }

            XchainOuterClass.Transaction complianceCheckTx = null;
            if (Config.getInstance().getComplianceCheck().getIsNeedComplianceCheckFee()) {
                complianceCheckTx = genComplianceCheckTx(response);
                genRealTx(response, complianceCheckTx);
            } else {
                genRealTxOnly(response, proposal);
            }

            XchainOuterClass.Transaction t = txBuilder.build();
            byte[] txid = TxEncoder.makeTxID(t);
            txBuilder.setTxid(ByteString.copyFrom(txid));
            this.pbtx = this.txBuilder.build();
            XchainOuterClass.SignatureInfo sigInfo = complianceCheck(this.pbtx, complianceCheckTx, client);
            this.txBuilder.addAuthRequireSigns(sigInfo);
            this.pbtx = this.txBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 没有背书检查时，生成完整的交易。
    private void genRealTxOnly(XchainOuterClass.PreExecWithSelectUTXOResponse response, Proposal proposal) {
        XchainOuterClass.UtxoOutput utxos = response.getUtxoOutput();
        XchainOuterClass.InvokeResponse invokeResponse = response.getResponse();

        Account initiator = proposal.initiator;
        XchainOuterClass.Transaction.Builder txBuilder = XchainOuterClass.Transaction.newBuilder()
                .setNonce(Common.newNonce())
                .setTimestamp(Common.getTimestamp())
                .setVersion(txVersion)
                .setInitiator(initiator.getAKAddress());

        if (proposal.desc != null) {
            txBuilder.setDesc(ByteString.copyFromUtf8(proposal.desc));
        }

        if (proposal.authRequire != null) {
            txBuilder.addAllAuthRequire(proposal.authRequire);
        }

        if (!utxos.getTotalSelected().isEmpty()) {
            makeUtxos(proposal, utxos, initiator, txBuilder, gasUsed);
        }

        // add utxos generated from contract
        txBuilder.addAllTxInputs(invokeResponse.getUtxoInputsList());
        txBuilder.addAllTxOutputs(invokeResponse.getUtxoOutputsList());

        // add xmodel inputs and outputs
        txBuilder.addAllTxInputsExt(invokeResponse.getInputsList());
        txBuilder.addAllTxOutputsExt(invokeResponse.getOutputsList());
        txBuilder.addAllContractRequests(invokeResponse.getRequestsList());

        this.txBuilder = txBuilder;
        this.pbtx = txBuilder.build();
    }

    private void genRealTx(XchainOuterClass.PreExecWithSelectUTXOResponse response, XchainOuterClass.Transaction complianceCheckTx) {

        BigInteger totalSelected = new BigInteger("0");
        ArrayList<XchainOuterClass.Utxo> utxoList = new ArrayList<>();
        for (int i = 0; i < complianceCheckTx.getTxOutputsList().size(); i++) {
            XchainOuterClass.TxOutput txOutput = complianceCheckTx.getTxOutputs(i);
            if (txOutput.getToAddr().toStringUtf8().equals(this.proposal.initiator.getAKAddress())) {
                utxoList.add(XchainOuterClass.Utxo.newBuilder()
                        .setAmount(ByteString.copyFrom(new BigInteger(txOutput.getAmount().toByteArray()).toString().getBytes()))
                        .setToAddr(txOutput.getToAddr())
                        .setRefTxid(complianceCheckTx.getTxid())
                        .setRefOffset(i)
                        .build());
                totalSelected = totalSelected.add(new BigInteger(txOutput.getAmount().toByteArray()));
            }
        }

        XchainOuterClass.UtxoOutput utxoOutput = XchainOuterClass.UtxoOutput.newBuilder()
                .addAllUtxoList(utxoList)
                .setTotalSelected(totalSelected.toString())
                .build();

        BigInteger totalNeed = new BigInteger("0");
        if (this.proposal.amount != null) {
            totalNeed = this.proposal.amount;
        }

        if (this.proposal.fee != null && !this.proposal.fee.isEmpty()) {
            totalNeed = totalNeed.add(new BigInteger(this.proposal.fee));
        }

        if (gasUsed > 0) {
            totalNeed = totalNeed.add(BigInteger.valueOf(getGasUsed()));
        }

        BigInteger selfAmount = totalSelected.subtract(totalNeed);
        XchainOuterClass.TxOutput[] txOutputs = genMultiTxOutputs(selfAmount.toString());
        XchainOuterClass.TxInput[] txInputs = genPureTxInputs(utxoOutput);

        XchainOuterClass.Transaction.Builder txBuilder = XchainOuterClass.Transaction.newBuilder()
                .setNonce(Common.newNonce())
                .setVersion(txVersion)
                .setCoinbase(false)
                .setTimestamp(Common.getTimestamp())
                .addAllTxInputs(Arrays.asList(txInputs))
                .addAllTxOutputs(Arrays.asList(txOutputs))
                .setInitiator(this.proposal.initiator.getAKAddress())
                .addAllTxInputsExt(response.getResponse().getInputsList())
                .addAllTxOutputsExt(response.getResponse().getOutputsList())
                .addAllContractRequests(response.getResponse().getRequestsList());

        if (proposal.desc != null) {
            txBuilder.setDesc(ByteString.copyFromUtf8(proposal.desc));
        }

        if (this.proposal.authRequire != null) {
            txBuilder.addAllAuthRequire(this.proposal.authRequire);
        } else {
            txBuilder.addAuthRequire(this.proposal.initiator.getAuthRequireId());
        }

        this.txBuilder = txBuilder;
        this.pbtx = txBuilder.build();
    }

    private XchainOuterClass.TxInput[] genPureTxInputs(XchainOuterClass.UtxoOutput utxoOutputs) {
        XchainOuterClass.TxInput[] result = new XchainOuterClass.TxInput[utxoOutputs.getUtxoListCount()];
        for (int i = 0; i < utxoOutputs.getUtxoListCount(); i++) {
            XchainOuterClass.Utxo utxo = utxoOutputs.getUtxoList(i);

            // 背书服务返回的数据为 string，此处需要将 utxo.GetAmount() 转成 string，然后再转成 Biginteger。
            BigInteger amount = new BigInteger(utxo.getAmount().toStringUtf8());

            result[i] = XchainOuterClass.TxInput.newBuilder()
                    .setRefTxid(utxo.getRefTxid())
                    .setRefOffset(utxo.getRefOffset())
                    .setFromAddr(utxo.getToAddr())
                    .setAmount(ByteString.copyFrom(toByteArray(amount)))
                    .build();
        }

        return result;
    }

    private XchainOuterClass.TxOutput[] genMultiTxOutputs(String selfAmount) {
        String selfAddr = this.proposal.initiator.getAKAddress();
        ArrayList<XchainOuterClass.TxOutput> txOutputs = new ArrayList<>();

        // 添加目标转账 output
        if (this.proposal.amount != null) {
            txOutputs.add(XchainOuterClass.TxOutput.newBuilder()
                    .setToAddr(ByteString.copyFrom(this.proposal.to.getBytes()))
                    .setAmount(ByteString.copyFrom(this.proposal.amount.toByteArray()))
                    .build());
        }

        // 自己的转账地址接收差额部分的转回的txOutput
        txOutputs.add(XchainOuterClass.TxOutput.newBuilder()
                .setToAddr(ByteString.copyFrom(selfAddr.getBytes()))
                .setAmount(ByteString.copyFrom(new BigInteger(selfAmount).toByteArray()))
                .build());


        // 如果矿工手续费不是空
        if (this.gasUsed > 0 || this.proposal.fee != null) {
            BigInteger allFee = BigInteger.valueOf(this.gasUsed);
            if (this.proposal.fee != null) {
                allFee = allFee.add(new BigInteger(this.proposal.fee));
            }

            if (allFee.compareTo(BigInteger.ZERO) > 0) {
                txOutputs.add(XchainOuterClass.TxOutput.newBuilder()
                        .setToAddr(ByteString.copyFrom("$".getBytes()))
                        .setAmount(ByteString.copyFrom(allFee.toByteArray()))
                        .build());
            }
        }
        return txOutputs.toArray(new XchainOuterClass.TxOutput[txOutputs.size()]);
    }

    private XchainOuterClass.SignatureInfo complianceCheck(XchainOuterClass.Transaction tx, XchainOuterClass.Transaction fee, XuperClient client) {
        try {
            XendorserOuterClass.EndorserRequest.Builder builder = XendorserOuterClass.EndorserRequest.newBuilder();
            if (fee != null) {
                builder.setFee(fee);
            }

            String gs = JsonUtils.TxStatus2Json(XchainOuterClass.TxStatus.newBuilder()
                    .setBcname(this.proposal.chainName)
                    .setTx(tx)
                    .build());

            XendorserOuterClass.EndorserResponse r = client.getXendorserClient().getBlockingClient().endorserCall(builder
                    .setBcName(this.proposal.chainName)
                    .setRequestData(ByteString.copyFrom(gs.getBytes()))
                    .setRequestName("ComplianceCheck")
                    .build());

            return r.getEndorserSign();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private XchainOuterClass.TxInput[] genTxInput(XchainOuterClass.UtxoOutput utxoOutputs) {
        XchainOuterClass.TxInput[] result = new XchainOuterClass.TxInput[utxoOutputs.getUtxoListCount()];
        for (int i = 0; i < utxoOutputs.getUtxoListCount(); i++) {
            XchainOuterClass.Utxo utxo = utxoOutputs.getUtxoList(i);
            result[i] = XchainOuterClass.TxInput.newBuilder().setRefTxid(utxo.getRefTxid())
                    .setRefOffset(utxo.getRefOffset())
                    .setFromAddr(utxo.getToAddr())
                    .setAmount(utxo.getAmount())
                    .build();
        }
        return result;
    }

    private XchainOuterClass.TxOutput getDeltaTxOutput(XchainOuterClass.UtxoOutput o, BigInteger totalNeed, String accountAddr) {
        BigInteger utxoTotal;
        if (o.getTotalSelected().isEmpty()) {
            utxoTotal = new BigInteger("0");
        } else {
            utxoTotal = new BigInteger(o.getTotalSelected());
        }
        if (utxoTotal.compareTo(totalNeed) > 0) {
            BigInteger delta = utxoTotal.subtract(totalNeed);
            return XchainOuterClass.TxOutput.newBuilder()
                    .setToAddr(ByteString.copyFromUtf8(accountAddr))
                    .setAmount(ByteString.copyFrom(delta.toByteArray()))
                    .build();

        } else {
            return null;
        }
    }

    private XchainOuterClass.TxOutput[] genTxOutput(String to, String fee, int amount) {

        ArrayList<XchainOuterClass.TxDataAccount> accounts = new ArrayList<>();

        if (!to.isEmpty()) {
            accounts.add(XchainOuterClass.TxDataAccount.newBuilder().setAddress(to)
                    .setAmount(amount + "")
                    .build());
        }

        if (!fee.equals("0")) {
            accounts.add(XchainOuterClass.TxDataAccount.newBuilder().setAddress(to)
                    .setAddress("$")
                    .setAmount(amount + "")
                    .build());
        }

        ArrayList<XchainOuterClass.TxOutput> result = new ArrayList<>();
        for (XchainOuterClass.TxDataAccount acc : accounts) {
            BigInteger a = new BigInteger(acc.getAmount());
            int cmpRes = a.compareTo(BigInteger.ZERO);
            if (cmpRes < 0) {
                throw new RuntimeException("Invalid negative number");
            }
            if (cmpRes == 0) {
                continue;
            }

            result.add(XchainOuterClass.TxOutput.newBuilder()
                    .setAmount(ByteString.copyFrom(a.toByteArray()))
                    .setToAddr(acc.getAddressBytes())
                    .setFrozenHeight(acc.getFrozenHeight())
                    .build());

        }

        int size = result.size();
        return result.toArray(new XchainOuterClass.TxOutput[size]);
    }

    private XchainOuterClass.Transaction genComplianceCheckTx(XchainOuterClass.PreExecWithSelectUTXOResponse response) {
        try {
            BigInteger totalNeed = new BigInteger(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceFee() + "");
            XchainOuterClass.TxInput[] txInputs = genTxInput(response.getUtxoOutput());
            XchainOuterClass.TxOutput deltaTxOutput = getDeltaTxOutput(response.getUtxoOutput(), totalNeed, this.proposal.initiator.getAKAddress());

            XchainOuterClass.TxOutput[] txOutputs = genTxOutput(
                    Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceFeeAddr(),
                    "0",
                    Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceFee());

            if (deltaTxOutput != null) {
                List<XchainOuterClass.TxOutput> txOutputsTemp = Arrays.asList(txOutputs);
                List<XchainOuterClass.TxOutput> arrList = new ArrayList<>(txOutputsTemp);
                arrList.add(deltaTxOutput);
                int size = arrList.size();
                txOutputs = arrList.toArray(new XchainOuterClass.TxOutput[size]);
            }

            XchainOuterClass.Transaction.Builder builder = XchainOuterClass.Transaction.newBuilder();
            builder.setNonce(Common.newNonce())
                    .setVersion(txVersion)
                    .setCoinbase(false)
                    .addAllTxInputs(Arrays.asList(txInputs))
                    .addAllTxOutputs(Arrays.asList(txOutputs))
                    .setInitiator(this.proposal.initiator.getAKAddress())
                    .setTimestamp(Common.getTimestamp());

            XchainOuterClass.Transaction tx = builder.build();
            byte[] bytes = TxEncoder.makeTxDigest(tx);

            byte[] signBytes = this.proposal.initiator.getKeyPair().sign(bytes);

            XchainOuterClass.SignatureInfo signatureInfo = XchainOuterClass.SignatureInfo.newBuilder()
                    .setPublicKey(this.proposal.initiator.getKeyPair().getJSONPublicKey())
                    .setSign(ByteString.copyFrom(signBytes))
                    .build();
            builder.addInitiatorSigns(signatureInfo);

            byte[] txID = TxEncoder.makeTxID(builder.build());
            builder.setTxid(ByteString.copyFrom(txID));
            return builder.build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void makeUtxos(Proposal proposal, XchainOuterClass.UtxoOutput utxos, Account initiator, XchainOuterClass.Transaction.Builder txBuilder, long gas) {
        // add utxo inputs
        for (int i = 0; i < utxos.getUtxoListCount(); i++) {
            XchainOuterClass.Utxo utxo = utxos.getUtxoList(i);
            XchainOuterClass.TxInput input = XchainOuterClass.TxInput.newBuilder()
                    .setFromAddr(utxo.getToAddr())
                    .setRefTxid(utxo.getRefTxid())
                    .setRefOffset(utxo.getRefOffset())
                    .setAmount(utxo.getAmount())
                    .build();
            txBuilder.addTxInputs(input);
        }

        // add utxo outputs
        BigInteger need = BigInteger.valueOf(gas);
        if (proposal.fee != null && !proposal.fee.isEmpty()) {
            need = new BigInteger(proposal.fee);
        }
        BigInteger total = new BigInteger(utxos.getTotalSelected());
        if (proposal.to != null && proposal.amount != null) {
            need = need.add(proposal.amount);
            XchainOuterClass.TxOutput out = XchainOuterClass.TxOutput.newBuilder()
                    .setToAddr(ByteString.copyFromUtf8(proposal.to))
                    .setAmount(ByteString.copyFrom(proposal.amount.toByteArray()))
                    .build();
            txBuilder.addTxOutputs(out);
        }

        BigInteger allFee = BigInteger.valueOf(0);
        if (gas > 0) {
            allFee = allFee.add(BigInteger.valueOf(gas));
        }
        if (proposal.fee != null) {
            allFee = allFee.add(new BigInteger(proposal.fee));
        }
        // add fee output
        if (allFee.compareTo(BigInteger.ZERO) > 0) {
            XchainOuterClass.TxOutput out = XchainOuterClass.TxOutput.newBuilder()
                    .setToAddr(ByteString.copyFromUtf8("$"))
                    .setAmount(ByteString.copyFrom(allFee.toByteArray()))
                    .build();
            txBuilder.addTxOutputs(out);
        }

        // make change
        if (total.compareTo(need) > 0) {
            XchainOuterClass.TxOutput out = XchainOuterClass.TxOutput.newBuilder()
                    .setToAddr(ByteString.copyFromUtf8(initiator.getAKAddress()))
                    .setAmount(ByteString.copyFrom(total.subtract(need).toByteArray()))
                    .build();
            txBuilder.addTxOutputs(out);
        }
    }

    public Transaction sign() {
        sign(proposal.initiator);
        return this;
    }

    public Transaction sign(Account singer) {
        try {
            txdigest = TxEncoder.makeTxDigest(pbtx);
            ECKeyPair keyPair = singer.getKeyPair();
            byte[] sig = keyPair.sign(txdigest);

            XchainOuterClass.SignatureInfo siginfo = XchainOuterClass.SignatureInfo.newBuilder()
                    .setPublicKey(keyPair.getJSONPublicKey())
                    .setSign(ByteString.copyFrom(sig))
                    .build();

            txBuilder.addAuthRequireSigns(siginfo);
            if (singer.getAKAddress().equals(pbtx.getInitiator())) {
                txBuilder.addInitiatorSigns(siginfo);
            }
            pbtx = txBuilder.build();
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Transaction debugPrint() {
        System.out.println(this.txBuilder.build().toString());
        return this;
    }

    public Transaction send(XuperClient client) {
        byte[] txid = TxEncoder.makeTxID(pbtx);
        txBuilder.setTxid(ByteString.copyFrom(txid));
        pbtx = txBuilder.build();

        XchainOuterClass.TxStatus request = XchainOuterClass.TxStatus.newBuilder()
                .setHeader(Common.newHeader())
                .setBcname(proposal.chainName)
                .setTx(pbtx)
                .setTxid(pbtx.getTxid())
                .build();
        XchainOuterClass.CommonReply response = client.getBlockingClient().postTx(request);
        Common.checkResponseHeader(response.getHeader(), "PostTx");
        return this;
    }

    public String getTxid() {
        return Hex.toHexString(pbtx.getTxid().toByteArray());
    }

    public ContractResponse getContractResponse() {
        return contractResponse;
    }

    public long getGasUsed() {
        return gasUsed;
    }

    public XchainOuterClass.Transaction getRawTx() {
        return pbtx;
    }

    // unsigned
    private byte[] toByteArray(BigInteger bi) {
        byte[] array = bi.toByteArray();
        if (array[0] == 0) {
            byte[] tmp = new byte[array.length - 1];
            System.arraycopy(array, 1, tmp, 0, tmp.length);
            array = tmp;
        }
        return array;
    }
}
