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

    private ECKeyPair(BigInteger privateKey, ECPoint publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.jsonPublicKey = createJSONPublicKey(publicKey);
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

    public ECKeyPair create() {
        return create(secureRandom);
    }

    public ECKeyPair create(SecureRandom secureRandom) {
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keygenParams = new ECKeyGenerationParameters(Ecc.domain, secureRandom);
        generator.init(keygenParams);
        AsymmetricCipherKeyPair keypair = generator.generateKeyPair();
        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) keypair.getPrivate();
        ECPublicKeyParameters pubParams = (ECPublicKeyParameters) keypair.getPublic();
        return create(privParams.getD());
    }

    public String getJSONPublicKey() {
        return jsonPublicKey;
    }

    public ECPoint getPublicKey() {
        return publicKey;
    }

    public byte[] sign(byte[] hash) throws Exception {
        return Ecc.sign(hash, privateKey);
    }
}
