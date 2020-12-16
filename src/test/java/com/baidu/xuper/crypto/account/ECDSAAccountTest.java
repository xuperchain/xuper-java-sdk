package com.baidu.xuper.crypto.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ECDSAAccountTest {
    @Test
    public void ECDSAAccountTest() {
        int[] strengthList = new int[]{1, 2, 3};
        int[] langList = new int[]{1, 2};

        for (int s : strengthList) {
            for (int l : langList) {
                ECDSAAccount ecdsaAccount = new ECDSAAccount();
                ecdsaAccount.createAccountWithMnemonic(s, l);
                assertNotNull(ecdsaAccount.address);
                assertNotNull(ecdsaAccount.mnemonic);
                assertNotNull(ecdsaAccount.jsonPrivateKey);
                assertNotNull(ecdsaAccount.jsonPublicKey);

                ECDSAAccount ecdsaAccount1 = new ECDSAAccount();
                ecdsaAccount1.createByMnemonic(ecdsaAccount.mnemonic, l);
                assertEquals(ecdsaAccount1.address, ecdsaAccount.address);
                assertEquals(ecdsaAccount1.mnemonic, ecdsaAccount.mnemonic);
                assertEquals(ecdsaAccount1.jsonPrivateKey, ecdsaAccount.jsonPrivateKey);
                assertEquals(ecdsaAccount1.jsonPublicKey, ecdsaAccount.jsonPublicKey);
            }
        }
    }

}
