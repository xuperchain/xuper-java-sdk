package com.baidu.xuperunion.api;

import com.baidu.xuperunion.crypto.ECKeyPair;
import com.google.common.io.ByteStreams;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getAddress() {
        final String privateKey = "29079635126530934056640915735344231956621504557963207107451663058887647996601";
        final String expectAddress = "dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN";
        Account account = Account.create(ECKeyPair.create(new BigInteger(privateKey)));
        assertEquals(expectAddress, account.getAddress());
    }

    @Test
    public void createFromPath() throws Exception {
        byte[] address = ByteStreams.toByteArray(getClass().getResourceAsStream("keys/address"));
        String keyPath = Paths.get(getClass().getResource("keys").toURI()).toString();
        Account account = Account.create(keyPath);
        assertEquals(new String(address), account.getAddress());
    }

    @Test
    public void createAndSave() {
        Account a = Account.createAndSave("./test","test",1,1);
        assertNotNull(a.getAddress());
        assertNotNull(a.getKeyPair());
        assertNotNull(a.getMnemonic());

        Account a1 = Account.createAndSave("./test","test",1,2);
        assertNotNull(a1.getAddress());
        assertNotNull(a1.getKeyPair());
        assertNotNull(a1.getMnemonic());
    }

    @Test
    public void getFromFile() {
        String path = "./testFromFile";
        String pw = "test";
        Account a = Account.createAndSave(path,pw,1,1);
        Account a1 = Account.getAccountFromFile(path,pw);
        assertEquals(a.getAddress(),a1.getAddress());
        assertEquals(a.getKeyPair().getJSONPrivateKey(),a1.getKeyPair().getJSONPrivateKey());
    }

    @Test
    public void create() {
        Account a = Account.create(1,1);
        assertNotNull(a.getAddress());
        assertNotNull(a.getMnemonic());
        assertNotNull(a.getKeyPair().getJSONPrivateKey());

        Account a1 = Account.create(1,2);
        assertNotNull(a1.getAddress());
        assertNotNull(a1.getMnemonic());
        assertNotNull(a1.getKeyPair().getJSONPrivateKey());
    }

    @Test
    public void retrieve(){
        Account a = Account.create(1,1);
        Account a1 = Account.retrieve(a.getMnemonic(),1);
        assertEquals(a.getMnemonic(),a1.getMnemonic());
        assertEquals(a.getAddress(),a1.getAddress());
        assertEquals(a.getKeyPair().getJSONPrivateKey(),a1.getKeyPair().getJSONPrivateKey());
    }
}