package com.baidu.xuper.crypto;

import com.baidu.xuper.crypto.wordlists.Chinese;
import com.baidu.xuper.crypto.wordlists.English;
import com.baidu.xuper.crypto.wordlists.WordList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Common {
    public static final String curveNist = "P-256";
    public static final String curveGm = "SM2-P-256";
    public static final Integer nist = 1;
    public static final Integer gm = 2;

    public static byte[] readFileWithBASE64Decode(String path) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));

            Base64.Decoder decoder = Base64.getDecoder();
            return decoder.decode(fileBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
