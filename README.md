# xuper-java-sdk
The java sdk of xuperunion https://github.com/xuperchain/xuperunion

# Usage

## Create client

```java
XuperClient client = new XuperClient("127.0.0.1:37101");
```

## Import account keys

```java
// Import account from local keys
Account account = Account.create("./keys");
```

## Create contract account

```java
// The account name is XC1111111111111111@xuper
client.createContractAccount(account, "1111111111111111");
```

## Transfer xuper to contract account

```java
client.transfer(account, "XC1111111111111111@xuper", BigInteger.valueOf(1000000));
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


