package com.baidu.xuper.crypto.xchain.sign;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequenceGenerator;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Ecc {
    static final String curveName = "P-256";
    static final X9ECParameters curve = NISTNamedCurves.getByName(curveName);
    static final ECDomainParameters domain = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH());

    static public byte[] sign(byte[] hash, BigInteger privateKey) throws IOException {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ECPrivateKeyParameters(privateKey, domain));
        BigInteger[] signature = signer.generateSignature(hash);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DERSequenceGenerator seq = new DERSequenceGenerator(baos);
        seq.addObject(new ASN1Integer(signature[0]));
        seq.addObject(new ASN1Integer(signature[1]));
        seq.close();
        return baos.toByteArray();
    }
}
