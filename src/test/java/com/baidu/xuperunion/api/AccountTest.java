package com.baidu.xuperunion.api;

import com.baidu.xuperunion.crypto.ECKeyPair;
import com.google.common.io.ByteStreams;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

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
}