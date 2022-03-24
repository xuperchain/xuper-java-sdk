package com.baidu.xuper.crypto.xchain.sign;

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

public class ECKeyPair {
    private static final SecureRandom secureRandom;

    static {
        secureRandom = new SecureRandom();
    }

    public BigInteger privateKey;
    public ECPoint publicKey;
    public String jsonPublicKey;
    public String jsonPrivateKey;

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public ECPoint getPublicKey() {
        return publicKey;
    }

    public String getJSONPublicKey() {
        return jsonPublicKey;
    }

    public String getJSONPrivateKey() {
        return jsonPrivateKey;
    }

    public ECKeyPair() {
    }

    private ECKeyPair(BigInteger privateKey, ECPoint publicKey) {
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
        m.put("Curvname", Ecc.curveName);
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
        m.put("Curvname", Ecc.curveName);
        m.put("X", x);
        m.put("Y", y);
        m.put("D", d);
        Gson gson = new Gson();
        return gson.toJson(m);
    }

    public static ECKeyPair create(byte[] seed) {
        BigInteger k = new BigInteger(1, seed);
        BigInteger n = Ecc.domain.getN().subtract(BigInteger.ONE);
        k = k.mod(n);
        k = k.add(BigInteger.ONE);
        ECPrivateKeyParameters p = new ECPrivateKeyParameters(k, Ecc.domain);
        return create(p.getD());
    }

    public static ECKeyPair create() {
        return create(secureRandom);
    }

    public static ECKeyPair create(SecureRandom secureRandom) {
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keygenParams = new ECKeyGenerationParameters(Ecc.domain, secureRandom);
        generator.init(keygenParams);
        AsymmetricCipherKeyPair keypair = generator.generateKeyPair();
        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) keypair.getPrivate();
        ECPublicKeyParameters pubParams = (ECPublicKeyParameters) keypair.getPublic();
        return create(privParams.getD());
    }

    public static ECKeyPair create(BigInteger privateKey) {
        ECPoint q = Ecc.domain.getG().multiply(privateKey);
        ECPublicKeyParameters params = new ECPublicKeyParameters(q, Ecc.domain);
        return new ECKeyPair(privateKey, params.getQ());
    }
}
