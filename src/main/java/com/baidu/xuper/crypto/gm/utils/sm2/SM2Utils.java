package com.baidu.xuper.crypto.gm.utils.sm2;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.math.ec.ECPoint;

import java.io.IOException;
import java.math.BigInteger;

public class SM2Utils {

    public static byte[] sign(byte[] privateKey, byte[] sourceData) throws IOException
    {
        if (privateKey == null)
        {
            return null;
        }

        if (sourceData == null || sourceData.length == 0)
        {
            return null;
        }

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new  BigInteger(1,privateKey);
//        System.out.println("userD: " + userD.toString(16));

        ECPoint userKey = sm2.ecc_point_g.multiply(userD);
//        System.out.println("椭圆曲线点X: " + userKey.getXCoord().toBigInteger().toString(16));
//        System.out.println("椭圆曲线点Y: " + userKey.getYCoord().toBigInteger().toString(16));

        SM2Result sm2Result = new SM2Result();
        sm2.sm2Sign(sourceData, userD, userKey, sm2Result);
//        System.out.println("r: " + sm2Result.r.toString(16));
//        System.out.println("s: " + sm2Result.s.toString(16));

        ASN1Integer d_r = new ASN1Integer(sm2Result.r);
        ASN1Integer d_s = new ASN1Integer(sm2Result.s);
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERSequence sign = new DERSequence(v2);
        return sign.getEncoded();
    }
}
