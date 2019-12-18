package com.baidu.xuperunion.api;

import com.baidu.xuperunion.pb.XchainGrpc;
import com.baidu.xuperunion.pb.XchainOuterClass;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class XuperClient {
    private final ManagedChannel channel;
    private final XchainGrpc.XchainBlockingStub blockingClient;
    private String chainName = "xuper";

    /**
     * @param target the address of xchain node, like 127.0.0.1:37101
     */
    public XuperClient(String target) {
        this(ManagedChannelBuilder.forTarget(target)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
    }

    private XuperClient(ManagedChannel channel) {
        this.channel = channel;
        blockingClient = XchainGrpc.newBlockingStub(channel);
    }

    public void close() {
        channel.shutdown();
    }

    /**
     * @param name name of chain
     * @return
     */
    public XuperClient setChainName(String name) {
        this.chainName = name;
        return this;
    }

    XchainGrpc.XchainBlockingStub getBlockingClient() {
        return blockingClient;
    }

    /**
     * @param from   from address
     * @param to     to address
     * @param amount transfer amount
     * @return
     * @throws Exception
     */
    public Transaction transfer(Account from, String to, BigInteger amount) throws Exception {
        Transaction tx = new Proposal()
                .setChainName(chainName)
                .setInitiator(from)
                .transfer(to, amount)
                .build(this)
                .sign()
                .send(this);
        return tx;
    }

    /**
     * @param from     the initiator of calling method
     * @param module   module of contract, usually wasm
     * @param contract contract name
     * @param method   contract method
     * @param args     contract method arguments
     * @return
     * @throws Exception
     */
    public Transaction invokeContract(Account from, String module, String contract, String method, Map<String, byte[]> args) throws Exception {
        Transaction tx = new Proposal()
                .setChainName(chainName)
                .setInitiator(from)
                .invokeContract(module, contract, method, args)
                .build(this)
                .sign()
                .send(this);
        return tx;
    }

    /**
     * @param from     the initiator of calling method
     * @param module   module of contract, usually wasm
     * @param contract contract name
     * @param method   contract method
     * @param args     contract method arguments
     * @return
     * @throws Exception
     */
    public Transaction queryContract(Account from, String module, String contract, String method, Map<String, byte[]> args) throws Exception {
        Transaction tx = new Proposal()
                .setChainName(chainName)
                .setInitiator(from)
                .invokeContract(module, contract, method, args)
                .build(this);
        return tx;
    }

    /**
     * @param from     the contract account to deploy contract
     * @param code     the binary of contract code
     * @param contract the name of contract
     * @param runtime  contract runtime c or go
     * @param initArgs initial argument of initialize method
     * @return
     * @throws Exception
     */
    public Transaction deployWasmContract(Account from, byte[] code, String contract, String runtime, Map<String, byte[]> initArgs) throws Exception {
        if (from.getContractAccount().isEmpty()) {
            throw new Exception("deploy contract must use contract account");
        }
        XchainOuterClass.WasmCodeDesc desc = XchainOuterClass.WasmCodeDesc.newBuilder()
                .setRuntime(runtime)
                .build();

        Gson gson = new Gson();
        byte[] initArgsJson = gson.toJson(initArgs).getBytes();

        Map<String, byte[]> args = new HashMap<>();
        args.put("account_name", from.getContractAccount().getBytes());
        args.put("contract_name", contract.getBytes());
        args.put("contract_code", code);
        args.put("contract_desc", desc.toByteArray());
        args.put("init_args", initArgsJson);
        return invokeContract(from, "xkernel", "", "Deploy", args);
    }

    /**
     * @param from        the use account to create contract account
     * @param accountName the name of contract account
     * @return
     * @throws Exception
     */
    public Transaction createContractAccount(Account from, String accountName) throws Exception {
        String desc = "{\"aksWeight\": {\"" + from.getAddress() + "\": 1.0}, \"pm\": {\"acceptValue\": 1.0, \"rule\": 1}}";
        Map<String, byte[]> args = new HashMap<>();
        args.put("account_name", accountName.getBytes());
        args.put("acl", desc.getBytes());
        return invokeContract(from, "xkernel", "", "NewAccount", args);
    }

    /**
     * Get balance of account
     *
     * @param account account name, can be contract account
     * @return
     * @throws Exception
     */
    public BigInteger getBalance(String account) throws Exception {
        XchainOuterClass.AddressStatus request = XchainOuterClass.AddressStatus.newBuilder()
                .setHeader(Common.newHeader())
                .setAddress(account)
                .addBcs(XchainOuterClass.TokenDetail.newBuilder().setBcname(chainName).build())
                .build();
        XchainOuterClass.AddressStatus response = blockingClient.getBalance(request);

        for (int i = 0; i < response.getBcsCount(); i++) {
            if (response.getBcs(i).getBcname().equals(chainName)) {
                return new BigInteger(response.getBcs(i).getBalance());
            }
        }
        Common.checkResponseHeader(response.getHeader(), "query balance");
        return BigInteger.valueOf(0);
    }

    /**
     * queryTx query transaction
     *
     * @param txid the id of transaction
     * @return
     * @throws Exception
     */
    public XchainOuterClass.Transaction queryTx(String txid) throws Exception {
        XchainOuterClass.TxStatus request = XchainOuterClass.TxStatus.newBuilder()
                .setHeader(Common.newHeader())
                .setBcname(chainName)
                .setTxid(ByteString.copyFrom(Hex.decode(txid)))
                .build();
        XchainOuterClass.TxStatus response = blockingClient.queryTx(request);
        Common.checkResponseHeader(response.getHeader(), "query transaction");
        return response.getTx();
    }

    /**
     * queryBlock get Block
     *
     * @param blockid the id of block
     * @return
     * @throws Exception
     */
    public XchainOuterClass.InternalBlock queryBlock(String blockid) throws Exception {
        XchainOuterClass.BlockID request = XchainOuterClass.BlockID.newBuilder()
                .setHeader(Common.newHeader())
                .setBcname(chainName)
                .setBlockid(ByteString.copyFrom(Hex.decode(blockid)))
                .setNeedContent(true)
                .build();
        XchainOuterClass.Block response = blockingClient.getBlock(request);
        Common.checkResponseHeader(response.getHeader(), "query transaction");
        return response.getBlock();
    }
}
