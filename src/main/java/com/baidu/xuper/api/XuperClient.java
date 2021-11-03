package com.baidu.xuper.api;

import com.baidu.xuper.config.Config;
import com.baidu.xuper.pb.XchainGrpc;
import com.baidu.xuper.pb.XchainOuterClass;
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
    private final XendorserClient xendorserClient;

    private String chainName = "xuper";

    private final String evmContract = "evm";
    private final String xkernelModule = "xkernel";
    private final String evmJSONEncoded = "jsonEncoded";
    private final String evmJSONEncodedTrue = "true";
    private final String argsInput = "input";
    private final String xkernelDeployMethod = "Deploy";
    private final String xkernelNewAccountMethod = "NewAccount";
    private final String argAccountName = "account_name";
    private final String argContractName = "contract_name";
    private final String argContractCode = "contract_code";
    private final String argContractDesc = "contract_desc";
    private final String argInitArgs = "init_args";
    private final String argContractAbi = "contract_abi";

    /**
     * @param target the address of xchain node, like 127.0.0.1:37101
     */
    public XuperClient(String target) {
        this(target,4194304);
    }

    /**
     * @param target the address of xchain node, like 127.0.0.1:37101
     * @param maxInboundMessageSize Sets the maximum message size allowed to be received on the channel, like 52428800 (50M)
     */
    public XuperClient(String target,Integer maxInboundMessageSize) {
        this(ManagedChannelBuilder.forTarget(target)
                .maxInboundMessageSize(maxInboundMessageSize)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
    }


    private XuperClient(ManagedChannel channel) {
        this.channel = channel;
        blockingClient = XchainGrpc.newBlockingStub(channel);
        if (Config.hasConfigFile()) {
            xendorserClient = new XendorserClient(Config.getInstance().getEndorseServiceHost());
        } else {
            xendorserClient = null;
        }
    }

    public void close() {
        channel.shutdown();
        if (xendorserClient != null) {
            xendorserClient.close();
        }
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

    public XendorserClient getXendorserClient() {
        return xendorserClient;
    }

    /**
     * @param from   from address
     * @param to     to address
     * @param amount transfer amount
     * @return
     */
    public Transaction transfer(Account from, String to, BigInteger amount, String fee) {
        return transfer(from,to,amount,fee,null);
    }

    /**
     * @param from   from address
     * @param to     to address
     * @param amount transfer amount
     * @param desc transfer desc
     * @return
     */
    public Transaction transfer(Account from, String to, BigInteger amount, String fee,String desc) {
        Proposal p = new Proposal()
                .setChainName(chainName)
                .setFee(fee);
        if ((desc!=null)&&(!desc.equals(""))){
            p.setDesc(desc);
        }

        if (Config.getInstance().getComplianceCheck().getIsNeedComplianceCheck()) {
            p.addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr());
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
        return new Proposal()
                .setChainName(chainName)
                .setInitiator(from)
                .addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr())
                .invokeContract(module, contract, method, args)
                .preExec(this);
    }

    /**
     * deploy wasm contract
     * @param from     the contract account to deploy contract
     * @param code     the binary of contract code
     * @param contract the name of contract
     * @param runtime  contract runtime c or go
     * @param initArgs initial argument of initialize method
     * @return
     */
    public Transaction deployWasmContract(Account from, byte[] code, String contract, String runtime, Map<String, byte[]> initArgs) {
        return deployContract(from,code,contract,runtime,initArgs,"wasm");
    }


    /**
     * deploy native contract
     * @param from     the contract account to deploy contract
     * @param code     the binary of contract code
     * @param contract the name of contract
     * @param runtime  contract runtime c or go
     * @param initArgs initial argument of initialize method
     * @return
     */
    public Transaction deployNativeContract(Account from, byte[] code, String contract, String runtime, Map<String, byte[]> initArgs) {
        return deployContract(from,code,contract,runtime,initArgs,"native");
    }

    /**
     *
     * @param from     the contract account to deploy contract
     * @param code     the binary of contract code
     * @param contract the name of contract
     * @param runtime  contract runtime c or go
     * @param initArgs initial argument of initialize method
     * @param contractType contract type  wasm or native
     * @return
     */
    private Transaction deployContract(Account from, byte[] code, String contract, String runtime, Map<String, byte[]> initArgs,String contractType) {
        if (from.getContractAccount().isEmpty()) {
            throw new RuntimeException("deploy contract must use contract account");
        }

        if (contractType.isEmpty()){
            contractType="wasm";
        }

        // XchainOuterClass.NativeCodeDesc desc = XchainOuterClass.NativeCodeDesc.newBuilder().build();
        XchainOuterClass.WasmCodeDesc desc = XchainOuterClass.WasmCodeDesc.newBuilder()
                .setRuntime(runtime)
                .setContractType(contractType)
                .build();
        Gson gson = new Gson();
        byte[] initArgsJson = gson.toJson(initArgs).getBytes();

        Map<String, byte[]> args = new HashMap<>();
        args.put(argAccountName, from.getContractAccount().getBytes());
        args.put(argContractName, contract.getBytes());
        args.put(argContractCode, code);
        args.put(argContractDesc, desc.toByteArray());
        args.put(argInitArgs, initArgsJson);
        return invokeContract(from, xkernelModule, "", xkernelDeployMethod, args);
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
        return invokeContract(from, xkernelModule, "", xkernelNewAccountMethod, args);
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

    /**
     * @param from     the account of create contract.
     * @param bin      evm contract bin byte array.
     * @param abi      evm contract abi byte array.
     * @param contract contract name.
     * @param initArgs constructor args.
     * @return transaction.
     */
    public Transaction deployEVMContract(Account from, byte[] bin, byte[] abi, String contract, Map<String, String> initArgs) {
        if (from.getContractAccount().isEmpty()) {
            throw new RuntimeException("deploy contract must use contract account");
        }
        XchainOuterClass.WasmCodeDesc desc = XchainOuterClass.WasmCodeDesc.newBuilder()
                .setContractType(evmContract)
                .build();

        Map<String, byte[]> evmArgs = this.convertToXuper3EVMArgs(initArgs);

        Gson gson = new Gson();
        byte[] initArgsJson = gson.toJson(evmArgs).getBytes();

        Map<String, byte[]> args = new HashMap<>();
        args.put(argAccountName, from.getContractAccount().getBytes());
        args.put(argContractName, contract.getBytes());
        args.put(argContractCode, bin);
        args.put(argContractDesc, desc.toByteArray());
        args.put(argInitArgs, initArgsJson);
        args.put(argContractAbi, abi);
        return invokeContract(from, xkernelModule, "", xkernelDeployMethod, args);
    }

    /**
     * @param from     the initiator of calling method.
     * @param contract contract name.
     * @param method   contract method.
     * @param args     method args.
     * @return transaction.
     */
    public Transaction queryEVMContract(Account from, String contract, String method, Map<String, String> args) {
        Map<String, byte[]> evmArgs = this.convertToXuper3EVMArgs(args);

        return new Proposal()
                .setChainName(chainName)
                .setInitiator(from)
                .addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr())
                .invokeContract(evmContract, contract, method, evmArgs)
                .preExec(this);
    }

    /**
     * @param from     the initiator of calling method.
     * @param contract contract name.
     * @param method   contract method.
     * @param args     contract method args.
     * @param amount   amount of transfer to contract when call payable method.
     * @return transaction.
     */
    public Transaction invokeEVMContract(Account from, String contract, String method, Map<String, String> args, BigInteger amount) {
        Proposal p = new Proposal().setChainName(chainName);
        if (Config.getInstance().getComplianceCheck().getIsNeedComplianceCheck()) {
            p.addAuthRequire(Config.getInstance().getComplianceCheck().getComplianceCheckEndorseServiceAddr());
        }
        p.setInitiator(from);

        Map<String, byte[]> evmArgs = this.convertToXuper3EVMArgs(args);

        if (amount == null || amount.compareTo(BigInteger.ZERO) == 0) {
            return p.invokeContract(evmContract, contract, method, evmArgs).build(this).sign().send(this);
        }
        return p.transfer(contract, amount).invokeContract(evmContract, contract, method, evmArgs).build(this).sign().send(this);
    }

    private Map<String, byte[]> convertToXuper3EVMArgs(Map<String, String> initArgs) {
        Map<String, Object> args = new HashMap<>();
        if (initArgs != null) {
            for (Map.Entry<String, String> entry : initArgs.entrySet()) {
                args.put(entry.getKey(), entry.getValue());
            }
        }

        Gson gson = new Gson();
        byte[] initArgsJson = gson.toJson(args).getBytes();

        Map<String, byte[]> evmArgs = new HashMap<>();
        evmArgs.put(argsInput, initArgsJson);
        evmArgs.put(evmJSONEncoded, evmJSONEncodedTrue.getBytes());

        return evmArgs;
    }
}
