package com.baidu.xuperunion.api;

import com.baidu.xuperunion.crypto.ECKeyPair;
import com.baidu.xuperunion.pb.XchainOuterClass;
import com.google.protobuf.ByteString;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

public class Transaction {
    static public final int txVersion = 1;
    private final Proposal proposal;
    private XchainOuterClass.Transaction.Builder txBuilder;
    private XchainOuterClass.Transaction pbtx;
    private byte[] txdigest;
    private ContractResponse contractResponse;
    private long gasUsed;

    Transaction(XchainOuterClass.PreExecWithSelectUTXOResponse response, Proposal proposal) throws Exception {
        XchainOuterClass.InvokeResponse invokeResponse = response.getResponse();
        if (invokeResponse.getResponseCount() != 0) {
            this.contractResponse = new ContractResponse(invokeResponse.getResponses(0));
            if (this.contractResponse.getStatus() >= 400) {
                throw new Exception("contract error status:"
                        + this.contractResponse.getStatus()
                        + " message:" + this.contractResponse.getMessage());
            }
        }
        this.gasUsed = invokeResponse.getGasUsed();

        XchainOuterClass.UtxoOutput utxos = response.getUtxoOutput();

        Account initiator = proposal.initiator;
        XchainOuterClass.Transaction.Builder txBuilder = XchainOuterClass.Transaction.newBuilder()
                .setNonce(Common.newNonce())
                .setTimestamp(System.nanoTime())
                .setVersion(txVersion)
                .setInitiator(initiator.getAddress())
                .addAuthRequire(initiator.getAuthRequireId());

        if (!utxos.getTotalSelected().isEmpty()) {
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
            BigInteger need = BigInteger.valueOf(0);
            BigInteger total = new BigInteger(utxos.getTotalSelected());
            if (proposal.to != null && proposal.amount != null) {
                need = need.add(proposal.amount);
                XchainOuterClass.TxOutput out = XchainOuterClass.TxOutput.newBuilder()
                        .setToAddr(ByteString.copyFromUtf8(proposal.to))
                        .setAmount(ByteString.copyFrom(need.toByteArray()))
                        .build();
                txBuilder.addTxOutputs(out);
            }
            // add fee output
            if (this.gasUsed > 0) {
                BigInteger gasUsed = BigInteger.valueOf(this.gasUsed);
                need = need.add(gasUsed);
                XchainOuterClass.TxOutput out = XchainOuterClass.TxOutput.newBuilder()
                        .setToAddr(ByteString.copyFromUtf8("$"))
                        .setAmount(ByteString.copyFrom(gasUsed.toByteArray()))
                        .build();
                txBuilder.addTxOutputs(out);
            }

            // make change
            if (total.compareTo(need) > 0) {
                XchainOuterClass.TxOutput out = XchainOuterClass.TxOutput.newBuilder()
                        .setToAddr(ByteString.copyFromUtf8(initiator.getPayableAddress()))
                        .setAmount(ByteString.copyFrom(total.subtract(need).toByteArray()))
                        .build();
                txBuilder.addTxOutputs(out);
            }
        }

        // add utxos generated from contract
        txBuilder.addAllTxInputs(invokeResponse.getUtxoInputsList());
        txBuilder.addAllTxOutputs(invokeResponse.getUtxoOutputsList());

        // add xmodel inputs and outputs
        txBuilder.addAllTxInputsExt(invokeResponse.getInputsList());
        txBuilder.addAllTxOutputsExt(invokeResponse.getOutputsList());
        txBuilder.addAllContractRequests(invokeResponse.getRequestsList());

        this.proposal = proposal;
        this.txBuilder = txBuilder;
        this.pbtx = txBuilder.build();
    }

    public Transaction sign() throws Exception {
        sign(proposal.initiator);
        return this;
    }

    public Transaction sign(Account singer) throws Exception {
        if (txdigest == null) {
            TxEncoder enc = new TxEncoder();
            txdigest = enc.makeTxDigest(txBuilder.build());
        }

        ECKeyPair keyPair = singer.getKeyPair();
        byte[] sig = keyPair.sign(txdigest);

        XchainOuterClass.SignatureInfo siginfo = XchainOuterClass.SignatureInfo.newBuilder()
                .setPublicKey(keyPair.getJSONPublicKey())
                .setSign(ByteString.copyFrom(sig))
                .build();

        txBuilder.addAuthRequireSigns(siginfo);
        if (singer.getAddress().equals(proposal.initiator.getAddress())) {
            txBuilder.addInitiatorSigns(siginfo);
        }

        return this;
    }

    public Transaction debugPrint() {
        System.out.println(this.txBuilder.build().toString());
        return this;
    }

    public Transaction send(XuperClient client) throws Exception {
        XchainOuterClass.Transaction tx = txBuilder.build();
        TxEncoder enc = new TxEncoder();
        byte[] txid = enc.makeTxID(tx);
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
}
