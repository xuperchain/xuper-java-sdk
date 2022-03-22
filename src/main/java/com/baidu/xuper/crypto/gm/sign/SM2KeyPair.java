package com.baidu.xuper.crypto.gm.sign;

import com.baidu.xuper.crypto.Common;
import com.baidu.xuper.crypto.gm.utils.sm2.SM2;

import com.baidu.xuper.crypto.xchain.sign.ECKeyPair;
import com.baidu.xuper.crypto.xchain.sign.Ecc;
import com.google.gson.Gson;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;

public class SM2KeyPair {

    BigInteger privateKey;
    ECPoint publicKey;
    public String jsonPublicKey;
    public String jsonPrivateKey;

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public ECPoint getPublicKey() {
        return publicKey;
    }


    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(ECPoint publicKey) {
        this.publicKey = publicKey;
    }


    public String getJSONPublicKey() {
        return jsonPublicKey;
    }

    public String getJSONPrivateKey() {
        return jsonPrivateKey;
    }

    public SM2KeyPair() {
    }

    private SM2KeyPair(BigInteger privateKey, ECPoint publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.jsonPublicKey = getEcdsaPublicKeyJsonFormat(publicKey);
        this.jsonPrivateKey = getEcdsaPrivateKeyJsonFormat(privateKey, publicKey);
    }


    /**
     * 公钥转json
     *
     * @param publicKey
     * @return String
     */
    static private String getEcdsaPublicKeyJsonFormat(ECPoint publicKey) {
        BigInteger x = publicKey.getAffineXCoord().toBigInteger();
        BigInteger y = publicKey.getAffineYCoord().toBigInteger();
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("Curvname", Common.curveGm);
        m.put("X", x);
        m.put("Y", y);
        Gson gson = new Gson();
        return gson.toJson(m);
    }

    /**
     * 私钥转json
     *
     * @param privateKey
     * @param publicKey
     * @return String
     */
    static private String getEcdsaPrivateKeyJsonFormat(BigInteger privateKey, ECPoint publicKey) {
        BigInteger x = publicKey.getAffineXCoord().toBigInteger();
        BigInteger y = publicKey.getAffineYCoord().toBigInteger();
        BigInteger d = privateKey;
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("Curvname", Common.curveGm);
        m.put("X", x);
        m.put("Y", y);
        m.put("D", d);
        Gson gson = new Gson();
        return gson.toJson(m);
    }


    public static SM2KeyPair create(byte[] seed) {
        SM2 sm2 = SM2.Instance();

        BigInteger k = new BigInteger(1, seed);
        BigInteger n = sm2.ecc_bc_spec.getN().subtract(BigInteger.ONE);
        k = k.mod(n);
        k = k.add(BigInteger.ONE);
        ECPrivateKeyParameters p = new ECPrivateKeyParameters(k, sm2.ecc_bc_spec);

        ECPoint q = sm2.ecc_bc_spec.getG().multiply(p.getD());
        ECPublicKeyParameters params = new ECPublicKeyParameters(q, sm2.ecc_bc_spec);
        return new SM2KeyPair(p.getD(), params.getQ());
    }


    public static SM2KeyPair create() {
        SM2 sm2 = SM2.Instance();

        AsymmetricCipherKeyPair keypair = sm2.ecc_key_pair_generator.generateKeyPair();
        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) keypair.getPrivate();
//        ECPublicKeyParameters pubParams = (ECPublicKeyParameters) keypair.getPublic();

        return create(privParams.getD());
    }

    /**
     * 通过私钥恢复
     *
     * @param privateKey 私钥
     * @return
     */
    public static SM2KeyPair create(BigInteger privateKey) {
        SM2 sm2 = SM2.Instance();
        ECPoint q = sm2.ecc_bc_spec.getG().multiply(privateKey);
        ECPublicKeyParameters params = new ECPublicKeyParameters(q, sm2.ecc_bc_spec);
        return new SM2KeyPair(privateKey, params.getQ());
    }

}
