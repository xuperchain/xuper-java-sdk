package com.baidu.xuperunion.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class XuperClientTest {
    private Account account;
    private XuperClient client;

    @Before
    public void setUp() throws Exception {
        client = new XuperClient("127.0.0.1:37101");
        account = Account.create(getClass().getResource("keys").getPath());
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    @Ignore("no connection")
    @Test
    public void transfer() throws Exception {
        String txid = client.transfer(account, "XC1111111111111111@xuper", "1000000").getTxid();
        System.out.println("transfer " + txid);
    }

    @Ignore("no connection")
    @Test
    public void invokeContract() throws Exception {
        Map<String, byte[]> args = new HashMap<>();
        args.put("key", "icexin".getBytes());
        Transaction tx = client.invokeContract(account, "wasm", "counter", "increase", args);
        System.out.println("invoke txid: " + tx.getTxid());
        System.out.println("response: " + tx.getContractResponse().getBodyStr());
        System.out.println("gas: " + tx.getGasUsed());
    }

    @Ignore("no connection")
    @Test
    public void queryContract() throws Exception {
        Map<String, byte[]> args = new HashMap<>();
        args.put("key", "icexin".getBytes());
        Transaction tx = client.queryContract(account, "wasm", "counter", "increase", args);
        System.out.println("response: " + tx.getContractResponse().getBodyStr());
        System.out.println("gas: " + tx.getGasUsed());
    }

    @Ignore("no connection")
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

    @Ignore("no connection")
    @Test
    public void createContractAccount() throws Exception {
        Transaction tx = client.createContractAccount(account, "1111111111111111");
        System.out.println("create account " + tx.getTxid());
        // waiting create account tx confirmed
        sleep(4000);
    }

    @Ignore("no connection")
    @Test
    public void apiExample() throws Exception {
        createContractAccount();
        transfer();
        deployWasmContract();
        invokeContract();
        queryContract();
    }
}