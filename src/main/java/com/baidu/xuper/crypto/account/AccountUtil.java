package com.baidu.xuper.crypto.account;

import com.baidu.xuper.crypto.wordlists.WordList;
import com.baidu.xuper.crypto.wordlists.Chinese;
import com.baidu.xuper.crypto.wordlists.English;

import java.math.BigInteger;

public class AccountUtil {

    public static class PrivatePubKey {
        public String CurvName;
        public BigInteger D;
        public BigInteger X;
        public BigInteger Y;
    }

    public static WordList getWordList(int language) {
        switch (language) {
            case 1:
                return Chinese.INSTANCE;
            case 2:
                return English.INSTANCE;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
    }

    public static byte[] bytesPad(byte[] data, int length) {
        return getBytes(data, length);
    }

    public static byte[] getBytes(byte[] data, int length) {
        byte[] result = new byte[length];
        int index = length - 1;
        for (int i = data.length - 1; i >= 0; i--) {
            result[index] = data[i];
            index--;
        }
        return result;
    }

}
