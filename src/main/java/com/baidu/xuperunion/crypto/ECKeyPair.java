package com.baidu.xuperunion.crypto;

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

    private final BigInteger privateKey;
    private final ECPoint publicKey;
    private final String jsonPublicKey;
    private final String jsonPrivateKey;

    private ECKeyPair(BigInteger privateKey, ECPoint publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.jsonPublicKey = createJSONPublicKey(publicKey);
        this.jsonPrivateKey = createJSONPrivateKey(privateKey,publicKey);
    }

    public static ECKeyPair create(BigInteger privateKey) {
        ECPoint q = Ecc.domain.getG().multiply(privateKey);
        ECPublicKeyParameters params = new ECPublicKeyParameters(q, Ecc.domain);
        return new ECKeyPair(privateKey, params.getQ());
    }

    static private String createJSONPublicKey(ECPoint publicKey) {
        BigInteger x = publicKey.getAffineXCoord().toBigInteger();
        BigInteger y = publicKey.getAffineYCoord().toBigInteger();
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("Curvname", Ecc.curveName);
        m.put("X", x);
        m.put("Y", y);
        Gson gson = new Gson();
        return gson.toJson(m);
    }

    public static ECKeyPair create() {
        return create(secureRandom);
    }

    public static ECKeyPair create(byte[] seed) {
        BigInteger k = new BigInteger(1,seed);
        BigInteger n = Ecc.domain.getN().subtract(BigInteger.ONE);
        k = k.mod(n);
        k = k.add(BigInteger.ONE);
        ECPrivateKeyParameters p = new ECPrivateKeyParameters(k,Ecc.domain);
        return create(p.getD());
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

    /**
     * @return 公钥 json 字符串。
     */
    public String getJSONPublicKey() {
        return jsonPublicKey;
    }

    /**
     * @return 私钥 json 字符串。
     */
    public String getJSONPrivateKey() {
        return  jsonPrivateKey;
    }

    static private String createJSONPrivateKey(BigInteger privateKey, ECPoint publicKey) {
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

    public ECPoint getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    /**
     * @param hash 待签名数据。
     * @return 签名。
     * @throws Exception
     */
    public byte[] sign(byte[] hash) throws Exception {
        return Ecc.sign(hash, privateKey);
    }
}
