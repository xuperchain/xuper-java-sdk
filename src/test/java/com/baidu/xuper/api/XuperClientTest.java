package com.baidu.xuper.api;

import com.baidu.xuper.config.Config;
import com.baidu.xuper.pb.XchainOuterClass;
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
    public void deployEVMContract() {
        String abi = "[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"num\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"constant\":false,\"inputs\":[],\"name\":\"retrieve\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"store\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"storepay\",\"outputs\":[],\"payable\":true,\"stateMutability\":\"payable\",\"type\":\"function\"}]";
        String bin = "608060405234801561001057600080fd5b5060405161016c38038061016c8339818101604052602081101561003357600080fd5b810190808051906020019092919050505080600081905550506101118061005b6000396000f3fe60806040526004361060305760003560e01c80632e64cec11460355780636057361d14605d5780638995db74146094575b600080fd5b348015604057600080fd5b50604760bf565b6040518082815260200191505060405180910390f35b348015606857600080fd5b50609260048036036020811015607d57600080fd5b810190808035906020019092919050505060c8565b005b60bd6004803603602081101560a857600080fd5b810190808035906020019092919050505060d2565b005b60008054905090565b8060008190555050565b806000819055505056fea265627a7a723158209500c3e12321b837819442c0bc1daa92a4f4377fc7b59c41dbf9c7620b2f961064736f6c63430005110032";

        Map<String, String> args = new HashMap<>();
        args.put("num", "5889");
        Transaction transaction = client.deployEVMContract(account, bin.getBytes(), abi.getBytes(), "storage", args);
        System.out.println("tx id:" + transaction.getTxid());
    }

    @Test
    public void invokeEVMContract() {
        Map<String, String> args = new HashMap<>();
        args.put("num", "5888");
        Transaction transaction = client.invokeEVMContract(account, "storage", "storagepay", args, BigInteger.ONE);
        System.out.println("tx id:" + transaction.getTxid());
    }

    @Test
    public void queryEVMContract() {
        Transaction transaction = client.queryEVMContract(account, "storage", "storagepay", null);
        System.out.println("tx message:" + transaction.getContractResponse().getMessage());
        System.out.println("tx bidy:" + transaction.getContractResponse().getBodyStr());
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
