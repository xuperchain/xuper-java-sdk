package com.baidu.xuper.crypto.account;

import lombok.Data;

@Data
public class ECDSAInfo {
    public byte[] entropyByte;
    public String mnemonic;
    public String address;
}
