package com.baidu.xuperunion.api;

import com.baidu.xuperunion.config.Config;
import com.baidu.xuperunion.pb.XchainOuterClass;
import org.bouncycastle.util.encoders.Hex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNoException;

public class XuperClientTest {
    private Account account;
    private XuperClient client;

    @Before
    public void setUp() {
        try {
            String p = getClass().getResource("./conf/sdk.yaml").getPath();
            Config.setConfigPath(p);
            
            client = new XuperClient("127.0.0.1:37101");
            // test connection
            client.getBlockingClient().getSystemStatus(XchainOuterClass.CommonIn.newBuilder().build());
            String keyPath = Paths.get(getClass().getResource("keys").toURI()).toString();
            account = Account.create(keyPath);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void transfer() throws Exception {
        String txid = client.transfer(account, "XC1111111111111111@xuper", BigInteger.valueOf(1000000), "0").getTxid();
        System.out.println("transfer " + txid);
    }

    @Test
    public void getBalance() throws Exception {
        Account bob = Account.create();
        BigInteger amount = BigInteger.valueOf(100);
        Transaction tx = client.transfer(account, bob.getAddress(), amount, "0");
        System.out.println("transfer to bob " + tx.getTxid());

        BigInteger result = client.getBalance(bob.getAddress());
        assertEquals(amount.longValue(), result.longValue());
    }

    @Test
    public void invokeContract() throws Exception {
        Map<String, byte[]> args = new HashMap<>();
        args.put("key", "icexin".getBytes());
        Transaction tx = client.invokeContract(account, "wasm", "counter", "increase", args);
        System.out.println("invoke txid: " + tx.getTxid());
        System.out.println("response: " + tx.getContractResponse().getBodyStr());
        System.out.println("gas: " + tx.getGasUsed());
    }

    @Test
    public void queryContract() throws Exception {
        Map<String, byte[]> args = new HashMap<>();
        args.put("key", "icexin".getBytes());
        Transaction tx = client.queryContract(account, "wasm", "counter", "increase", args);
        System.out.println("response: " + tx.getContractResponse().getBodyStr());
        System.out.println("gas: " + tx.getGasUsed());
    }

    @Test
    public void deployWasmContract() throws Exception {
        account.setContractAccount("XC1111111111111111@xuper");
        Map<String, byte[]> args = new HashMap<>();
        args.put("creator", "icexin".getBytes());
        String codePath = getClass().getResource("counter.wasm").getFile();
        byte[] code = Files.readAllBytes(Paths.get(codePath));
        Transaction tx = client.deployWasmContract(account, code, "counter", "c", args);
        System.out.println("deploy contract " + tx.getTxid());
    }

    @Test
    public void createContractAccount() throws Exception {
        Transaction tx = client.createContractAccount(account, "1111111111111111");
        System.out.println("create account " + tx.getTxid());
        // waiting create account tx confirmed
        sleep(4000);
    }

    @Test
    public void queryTx() throws Exception {
        String txid = client.transfer(account, "XC1111111111111111@xuper", BigInteger.valueOf(10), "0").getTxid();
        XchainOuterClass.Transaction tx = client.queryTx(txid);
        assertEquals(txid, Hex.toHexString(tx.getTxid().toByteArray()));
    }

    @Test
    public void queryBlock() throws Exception {
        String txid = client.transfer(account, "XC1111111111111111@xuper", BigInteger.valueOf(10), "0").getTxid();
        // waiting for confirmed
        sleep(4000);
        XchainOuterClass.Transaction tx = client.queryTx(txid);
        assertEquals(txid, Hex.toHexString(tx.getTxid().toByteArray()));

        String blockid = Hex.toHexString(tx.getBlockid().toByteArray());
        XchainOuterClass.InternalBlock block = client.queryBlock(blockid);
        System.out.println(Hex.toHexString(block.getPreHash().toByteArray()));
        assertEquals(blockid, Hex.toHexString(block.getBlockid().toByteArray()));
    }

    @Test
    public void getSystemStatus() throws Exception {
        XchainOuterClass.SystemsStatus status = client.getSystemStatus();
        assertEquals(1, status.getBcsStatusCount());
        System.out.println("blockchain count:" + status.getBcsStatusCount());
        // 遍历所有的链
        for (int i = 0; i < status.getBcsStatusCount(); i++) {
            XchainOuterClass.BCStatus bcs = status.getBcsStatusList().get(i);
            // 打印链名
            System.out.println("blockchain " + i + ", name=" + bcs.getBcname());
            // 链上当前主干高度
            System.out.println("---- Height: " + bcs.getMeta().getTrunkHeight());
            // 链上最新的块ID
            System.out.println("---- TipBlockId: " + Hex.toHexString(bcs.getMeta().getTipBlockid().toByteArray()));
            // 链上创世块ID
            System.out.println("---- RootBlockId: " + Hex.toHexString(bcs.getMeta().getRootBlockid().toByteArray()));
        }
    }

    @Test
    public void getBlockchainStatus() throws Exception {
        XchainOuterClass.BCStatus bcs = client.getBlockchainStatus("xuper");
        assertEquals("xuper", bcs.getBcname());
        // 打印链名
        System.out.println("blockchain name=" + bcs.getBcname());
        // 链上当前主干高度
        System.out.println("---- Height: " + bcs.getMeta().getTrunkHeight());
        // 链上最新的块ID
        System.out.println("---- TipBlockId: " + Hex.toHexString(bcs.getMeta().getTipBlockid().toByteArray()));
        // 链上创世块ID
        System.out.println("---- RootBlockId: " + Hex.toHexString(bcs.getMeta().getRootBlockid().toByteArray()));
    }

    @Test
    public void apiExample() throws Exception {
        createContractAccount();
        transfer();
        deployWasmContract();
        invokeContract();
        queryContract();
        queryTx();
        queryBlock();
        getBalance();
        getSystemStatus();
    }
}
