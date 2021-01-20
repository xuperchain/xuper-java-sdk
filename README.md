# xuper-java-sdk
The java sdk of xuperunion https://github.com/xuperchain/xuperunion

# Usage

## Add Dependency

If your're using Maven, just add the following dependency in `pom.xml`.

```xml
<dependency>
  <groupId>com.baidu.xuper</groupId>
  <artifactId>xuper-java-sdk</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Config file

If you use the endorsement feature, set the configuration file like this:

```
Config.setConfigPath("./conf/sdk.yaml");
```

The config file is here: src/main/java/com/baidu/xuper/conf/sdk.yaml.

Test net config file is here: src/main/java/com/baidu/xuper/conf/sdk.testnet.yaml.

## Create client

```java
XuperClient client = new XuperClient("127.0.0.1:37101");
```

## Import account keys

```java
// Import account from local keys
Account account = Account.create("./keys");
```

## Create account and mnemonic

```java
// Import account from local keys
Account account = Account.create(1, 2);
System.out.println(account.getAddress());
System.out.println(account.getMnemonic());
```

## Create contract account

```java
// The account name is XC1111111111111111@xuper
client.createContractAccount(account, "1111111111111111");
```

## Transfer xuper to contract account

```java
client.transfer(account, "XC1111111111111111@xuper", BigInteger.valueOf(1000000), "1");
```

## Query balance of account
```java
 BigInteger result = client.getBalance("XC1111111111111111@xuper");
```

## Query balance details of account

```java
 XuperClient.BalDetails[] result = client.getBalanceDetails("XC1111111111111111@xuper");
```

## Deploy contract using contract account

```java
// Using a contract account to deploy contract
account.setContractAccount("XC1111111111111111@xuper");
Map<String, byte[]> args = new HashMap<>();
args.put("creator", "icexin".getBytes());
String codePath = "./counter.wasm";
byte[] code = Files.readAllBytes(Paths.get(codePath));
// the runtime is c
client.deployWasmContract(account, code, "counter", "c", args);
```

## Invoke contract

```java
Map<String, byte[]> args = new HashMap<>();
args.put("key", "icexin".getBytes());
Transaction tx = client.invokeContract(account, "wasm", "counter", "increase", args);
System.out.println("txid: " + tx.getTxid());
System.out.println("response: " + tx.getContractResponse().getBodyStr());
System.out.println("gas: " + tx.getGasUsed());
```

## Get block by height

```java
XuperClient c = new XuperClient("127.0.0.1:37101");
XchainOuterClass.Block block = c.getBlockByHeight(100);
System.out.println("block:" + bbb);
```

## Get account by AK

```java
XchainOuterClass.AK2AccountResponse response = c.getAccountByAK("Sw5kwvaf3PAwozXxMdFuBrd9UiqXuXhVF");
System.out.println(response);
```

## Get account contracts

```java
XchainOuterClass.GetAccountContractsResponse response = c.getAccountContracts("XC1111111111111111@xuper");
System.out.println(response);
```

## Query UTXO record

```java
XchainOuterClass.UtxoRecordDetail detail = c.queryUtxoRecord("WEtfMgJHcWWLwtwuTsRZkmYutjZGENXd6", 2);
System.out.println(detail);
```

## Query account acl

```java
XchainOuterClass.AclStatus acl = c.queryAccountACL("XC1111111111111111@xuper");
System.out.println(acl);
```

## Query contract method acl

```java
XchainOuterClass.AclStatus acl = c.queryContractMethodACL("counter", "increase");
System.out.println(acl);
```

