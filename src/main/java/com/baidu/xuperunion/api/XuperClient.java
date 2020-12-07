package com.baidu.xuperunion.api;

import com.baidu.xuperunion.config.Config;
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
    private Account platformAccount;

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

    public XuperClient(String target, Account platformAccount) {
        this(ManagedChannelBuilder.forTarget(target)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
        this.platformAccount = platformAccount;
    }

    private XuperClient(ManagedChannel channel) {
        this.channel = channel;
        blockingClient = XchainGrpc.newBlockingStub(channel);
    }


    public Account getPlatformAccount() {
        return platformAccount;
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
     */
    public Transaction transfer(Account from, String to, BigInteger amount, String fee) {
        Proposal p = new Proposal()
                .setChainName(chainName)
                .setFee(fee);

        if (Config.getInstance().getComplianceCheck().getIsNeedComplianceCheck()) {
            p.addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr());
            if (this.platformAccount != null) {
                p.addAuthRequire(this.platformAccount.getAddress());
            }
        }
        p.setInitiator(from);
        return p.transfer(to, amount).build(this).sign().send(this);
    }

    /**
     * @param from     the initiator of calling method
     * @param module   module of contract, usually wasm
     * @param contract contract name
     * @param method   contract method
     * @param args     contract method arguments
     * @return
     */
    public Transaction invokeContract(Account from, String module, String contract, String method, Map<String, byte[]> args) {
        Proposal p = new Proposal().setChainName(chainName);
        if (Config.getInstance().getComplianceCheck().getIsNeedComplianceCheck()) {
            p.addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr());
        }
        p.setInitiator(from);
        return p.invokeContract(module, contract, method, args).build(this).sign().send(this);
    }

    /**
     * @param from     the initiator of calling method
     * @param module   module of contract, usually wasm
     * @param contract contract name
     * @param method   contract method
     * @param args     contract method arguments
     * @return
     */
    public Transaction queryContract(Account from, String module, String contract, String method, Map<String, byte[]> args) {
        Transaction tx = new Proposal()
                .setChainName(chainName)
                .setInitiator(from)
                .addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr())
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
     */
    public Transaction deployWasmContract(Account from, byte[] code, String contract, String runtime, Map<String, byte[]> initArgs) {
        if (from.getContractAccount().isEmpty()) {
            throw new RuntimeException("deploy contract must use contract account");
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
     */
    public Transaction createContractAccount(Account from, String accountName) {
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
     */
    public BigInteger getBalance(String account) {
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
     * Get balance unfrozen balance and frozen balance of account
     *
     * @param account account name, can be contract account
     * @return balance
     */
    public BalDetails[] getBalanceDetails(String account) {
        XchainOuterClass.AddressBalanceStatus request = XchainOuterClass.AddressBalanceStatus.newBuilder()
                .setHeader(Common.newHeader())
                .setAddress(account)
                .addTfds(XchainOuterClass.TokenFrozenDetails.newBuilder().setBcname(chainName).build())
                .build();
        XchainOuterClass.AddressBalanceStatus response = blockingClient.getBalanceDetail(request);

        XchainOuterClass.TokenFrozenDetails tfds = response.getTfds(0);

        Gson g = new Gson();

        BalDetails[] balDetails = new BalDetails[tfds.getTfdCount()];
        for (int i = 0; i < tfds.getTfdCount(); i++) {
            XchainOuterClass.TokenFrozenDetail tfd = tfds.getTfd(i);
            balDetails[i] = new BalDetails(tfd.getBalance(), tfd.getIsFrozen());
        }

        return balDetails;
    }

    public static class BalDetails {
        private String balance;
        private Boolean isFrozen;

        BalDetails(String bal, Boolean f) {
            balance = bal;
            isFrozen = f;
        }

        public String getBalance() {
            return balance;
        }

        public Boolean getFrozen() {
            return isFrozen;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setFrozen(Boolean frozen) {
            isFrozen = frozen;
        }
    }

    /**
     * queryTx query transaction
     *
     * @param txid the id of transaction
     * @return
     */
    public XchainOuterClass.Transaction queryTx(String txid) {
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
     */
    public XchainOuterClass.InternalBlock queryBlock(String blockid) {
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

    /**
     * getSystemStatus get system status contains all blockchains
     *
     * @return instance of SystemsStatus
     */
    public XchainOuterClass.SystemsStatus getSystemStatus() {
        XchainOuterClass.CommonIn request = XchainOuterClass.CommonIn.newBuilder()
                .setHeader(Common.newHeader())
                .build();
        XchainOuterClass.SystemsStatusReply response = blockingClient.getSystemStatus(request);
        Common.checkResponseHeader(response.getHeader(), "query system status");
        return response.getSystemsStatus();
    }

    /**
     * getBlockchainStatus get the status of given blockchain
     *
     * @param chainName the name of blockchain
     * @return instance of BCStatus
     */
    public XchainOuterClass.BCStatus getBlockchainStatus(String chainName) {
        XchainOuterClass.BCStatus request = XchainOuterClass.BCStatus.newBuilder()
                .setHeader(Common.newHeader())
                .setBcname(chainName)
                .build();
        XchainOuterClass.BCStatus bcs = blockingClient.getBlockChainStatus(request);
        Common.checkResponseHeader(bcs.getHeader(), "query blockchain status");
        return bcs;
    }
}
