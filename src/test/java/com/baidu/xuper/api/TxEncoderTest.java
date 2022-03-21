package com.baidu.xuper.api;

import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import com.baidu.xuper.pb.XchainOuterClass;
import com.google.common.io.ByteStreams;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TxEncoderTest {
    final String privateKey = "29079635126530934056640915735344231956621504557963207107451663058887647996601";
    Account account;

    @Before
    public void setUp() throws Exception {
        account = Account.create(ECKeyPair.create(new BigInteger(privateKey)));
    }

    @Ignore
    @Test
    public void makeTransferTx() throws Exception {
        XuperClient client = new XuperClient("127.0.0.1:37101");
        Transaction tx = new Proposal()
                .setChainName("xuper")
                .setInitiator(account)
                .setDesc("transfer from test")
                .transfer(account.getAddress(), BigInteger.valueOf(10))
                .build(client)
                .sign();
        FileOutputStream f = new FileOutputStream("/tmp/transfer.pb");
        f.write(tx.getRawTx().toByteArray());
        f.close();
    }

    @Ignore
    @Test
    public void makeInvokeTx() throws Exception {
        XuperClient client = new XuperClient("127.0.0.1:37101");
        Map<String, byte[]> args = new HashMap<>();
        Transaction tx = new Proposal()
                .setChainName("xuper")
                .setInitiator(account)
                .invokeContract("wasm", "counter", "increase", args)
                .build(client)
                .sign();
        FileOutputStream f = new FileOutputStream("/tmp/invoke.pb");
        f.write(tx.getRawTx().toByteArray());
        f.close();
    }

    private XchainOuterClass.Transaction getTxpb(String name) throws IOException {
        byte[] pb = ByteStreams.toByteArray(getClass().getResourceAsStream(name));
        return XchainOuterClass.Transaction.parseFrom(pb);
    }

    @Test
    public void makeTxDigest() throws IOException {
        byte[] digest = TxEncoder.makeTxDigest(getTxpb("transfer.pb"));
        assertEquals("f0395d3b4f13a0a832a1f19719d40713dfe5f6785768deabc29ea3069b7855ab", Hex.toHexString(digest));

        digest = TxEncoder.makeTxDigest(getTxpb("invoke.pb"));
        assertEquals("db80c69ebfb61efd0d8e4d5fb2acab64fa91507978a2cb9b0d447158732cbb40", Hex.toHexString(digest));
    }

    @Test
    public void makeTxID() throws IOException {
        byte[] txid = TxEncoder.makeTxID(getTxpb("transfer.pb"));
        assertEquals("d53f49a512558e215ba52dad1b67cfbb23836fbdc8070f403faa670e171e4aeb", Hex.toHexString(txid));

        txid = TxEncoder.makeTxID(getTxpb("invoke.pb"));
        assertEquals("657abe49d57f6083d3d5ac278d324c441ee102abab03999934e1f4c56822b31c", Hex.toHexString(txid));
    }
}