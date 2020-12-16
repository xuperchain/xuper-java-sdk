package com.baidu.xuper.api;

import com.baidu.xuper.crypto.ECKeyPair;
import com.google.common.io.ByteStreams;
import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        deleteDir(new File("./test"));
        deleteDir(new File("./test1"));
        deleteDir(new File("./test2"));
        deleteDir(new File("./test3"));

        Account a = Account.createAndSave("./test", "test", 1, 1);
        assertNotNull(a.getAddress());
        assertNotNull(a.getKeyPair());
        assertNotNull(a.getMnemonic());

        Account a1 = Account.createAndSave("./test1", "test", 1, 2);
        assertNotNull(a1.getAddress());
        assertNotNull(a1.getKeyPair());
        assertNotNull(a1.getMnemonic());

        Account a2 = Account.createAndSave("./test2", "test", 2, 1);
        assertNotNull(a2.getAddress());
        assertNotNull(a2.getKeyPair());
        assertNotNull(a2.getMnemonic());

        Account a3 = Account.createAndSave("./test3", "test", 2, 2);
        assertNotNull(a3.getAddress());
        assertNotNull(a3.getKeyPair());
        assertNotNull(a3.getMnemonic());


        deleteDir(new File("./test"));
        deleteDir(new File("./test1"));
        deleteDir(new File("./test2"));
        deleteDir(new File("./test3"));
    }

    @Test
    public void getFromFile() {
        String path = "./testFromFile";
        deleteDir(new File(path));
        String pw = "test";
        Account a = Account.createAndSave(path, pw, 1, 1);
        Account a1 = Account.getAccountFromFile(path, pw);
        assertEquals(a.getAddress(), a1.getAddress());
        assertEquals(a.getKeyPair().getJSONPrivateKey(), a1.getKeyPair().getJSONPrivateKey());
        deleteDir(new File(path));
    }

    @Test
    public void create() {
        Account a = Account.create(1, 1);
        assertNotNull(a.getAddress());
        assertNotNull(a.getMnemonic());
        assertNotNull(a.getKeyPair().getJSONPrivateKey());

        Account a1 = Account.create(1, 2);
        assertNotNull(a1.getAddress());
        assertNotNull(a1.getMnemonic());
        assertNotNull(a1.getKeyPair().getJSONPrivateKey());
    }

    @Test
    public void retrieve() {
        Account a = Account.create(1, 1);
        Account a1 = Account.retrieve(a.getMnemonic(), 1);
        assertEquals(a.getMnemonic(), a1.getMnemonic());
        assertEquals(a.getAddress(), a1.getAddress());
        assertEquals(a.getKeyPair().getJSONPrivateKey(), a1.getKeyPair().getJSONPrivateKey());
    }

    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}