package com.baidu.xuper.api;

import com.baidu.xuper.config.Config;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class XendorserTest {

    @Test
    public void testcc() throws Exception {
//
        Config.setConfigPath("./conf/sdk.yaml");

        Account account = Account.getAccountFromFile("/Users/liminglei02/tmp/javasdk", "java");
        System.out.println(account.getAKAddress());
        XuperClient c = new XuperClient("192.168.151.133:33101");

        account.setContractAccount("XC1111111111111111@xuper");
        BigInteger balance = c.getBalance(account.getAddress());
        BigInteger balancee = c.getBalance(account.getAKAddress());
        BigInteger balance1 = c.getBalance("WEtfMgJHcWWLwtwuTsRZkmYutjZGENXd6");
        System.out.println("转账前addr sender余额：" + balance);
        System.out.println("转账前real sender余额：" + balancee);
        System.out.println("转账前to余额：" + balance1);
        
        Map<String, byte[]> args = new HashMap<>();
        args.put("creator", "icexin".getBytes());
    }
}
