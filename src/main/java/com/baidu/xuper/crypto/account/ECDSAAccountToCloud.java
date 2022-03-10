package com.baidu.xuper.crypto.account;

import lombok.Data;

@Data
public class ECDSAAccountToCloud {
    public String address;
    public String jsonEncryptedPrivateKey;
    public String encryptedMnemonic;
    public String password;
}
