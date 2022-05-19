package com.baidu.xuper.crypto.gm.sign;

import com.baidu.xuper.crypto.gm.utils.sm2.SM2Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

import java.security.PrivateKey;
import java.security.Signature;

public class Ecc {

    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
       return SM2Utils.sign(privateKey, data);
    }

}
