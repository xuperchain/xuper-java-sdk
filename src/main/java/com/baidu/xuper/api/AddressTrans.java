package com.baidu.xuper.api;

import com.baidu.xuper.crypto.Base58;
import com.baidu.xuper.crypto.xchain.hash.Hash;

import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;

public class AddressTrans {
    private static final String EVM_ADDRESS_FILLER = "-";
    private static final String CONTRACT_NAME_PREFIX = "1111";
    private static final String CONTRACT_ACCOUNT_PREFIX = "1112";
    private static final String ACCOUNT_PREFIX = "XC";
    private static final String ACCOUNT_BCNAME_SEP = "@";
    private static final String CONTRACT_NAME_REGEX = "^[a-zA-Z_]{1}[0-9a-zA-Z_.]+[0-9a-zA-Z_]$";

    private static final Integer ACCOUNT_SIZE = 16;
    private static final Integer CONTRACT_NAME_MAX_SIZE = 16;
    private static final Integer CONTRACT_NAME_MIN_SIZE = 4;

    /**
     * 地址类型
     */
    private static final String X_CHAIN_ADDR_TYPE = "xchain";
    /**
     * 合约名字地址类型
     */
    private static final String CONTRACT_NAME_TYPE = "contract-name";
    /**
     * 合约账户地址类型
     */
    private static final String CONTRACT_ACCOUNT_TYPE = "contract-account";

    private String addr;
    private String addrType;

    public String getAddr() {
        return addr;
    }

    public String getAddrType() {
        return addrType;
    }

    AddressTrans(String addr, String addrType) {
        this.addr = addr;
        this.addrType = addrType;
    }

    /**
     * xChain address transfer to evm address:
     * xChain address can be xChain contract account, AK address, xChain contract name.
     *
     * @param xChainAddr
     * @return AddrInfo
     */
    public static AddressTrans xChainToEvmAddress(String xChainAddr) {
        String addr = "";
        String addrType = "";
        if (isContractAddr(xChainAddr)) {
            addr = xChainContractAddrToEvmAddr(xChainAddr);
            addrType = CONTRACT_ACCOUNT_TYPE;
        } else if (isContractName(xChainAddr)) {
            addr = xChainContractNameToEvmAddr(xChainAddr);
            addrType = CONTRACT_NAME_TYPE;
        } else {
            addr = xChainAddrToEvmAddr(xChainAddr);
            addrType = X_CHAIN_ADDR_TYPE;
        }
        return new AddressTrans(addr, addrType);
    }

    /**
     * evm address transfer to xChain address:
     * evmAddr can be evm contract account, AK address, xChain contract name.
     *
     * @param evmAddr
     * @return AddrInfo
     */
    public static AddressTrans evmToXChainAddress(String evmAddr) {
        String addr = "";
        String addrType = "";

        byte[] addr1 = Hex.decode(evmAddr);
        String addr2 = "";
        try {
            addr2 = new String(addr1, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (addr2.startsWith(CONTRACT_ACCOUNT_PREFIX)) {
            addr = evmAddrToXChainContractAddr(evmAddr);
            addrType = CONTRACT_ACCOUNT_TYPE;
        } else if (addr2.substring(0,4).equals(CONTRACT_NAME_PREFIX)) {
            addr = evmAddrToXChainContractName(evmAddr);
            addrType = CONTRACT_NAME_TYPE;
        } else {
            addr = evmAddrToXChainAddr(evmAddr);
            addrType = X_CHAIN_ADDR_TYPE;
        }

        return new AddressTrans(addr, addrType);
    }

    /**
     * transfer xChain address to evm address
     *
     * @param xChainAddr
     * @return
     */
    private static String xChainAddrToEvmAddr(String xChainAddr) {
        byte[] rawAddr = new byte[0];
        try {
            rawAddr = Base58.decode(xChainAddr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rawAddr.length < 21) {
            System.out.println("xChainAddr is not a valid address");
        }
        byte[] ripemd160Hash = Arrays.copyOfRange(rawAddr, 1, 21);
        byte[] addr = Arrays.copyOf(ripemd160Hash, ripemd160Hash.length);
        return Hex.toHexString(addr).toUpperCase();
    }

    /**
     * transfer xChain contract account to evm address
     *
     * @param xChainAddr
     * @return
     */
    private static String xChainContractAddrToEvmAddr(String xChainAddr) {
        String contractAccountValid = "1112" + xChainAddr.substring(2, 18);

        byte[] addr = Arrays.copyOf(contractAccountValid.getBytes(), contractAccountValid.getBytes().length);
        return Hex.toHexString(addr).toUpperCase();
    }

    /**
     * transfer xChain contract name to evm address
     *
     * @param contractName
     * @return String
     */
    private static String xChainContractNameToEvmAddr(String contractName) {

        String prefixStr = "";
        for (Integer i = 0; i < 20 - contractName.length() - 4; i++) {
            prefixStr += EVM_ADDRESS_FILLER;
        }

        contractName = prefixStr + contractName;
        contractName = CONTRACT_NAME_PREFIX + contractName;

        byte[] addr = Arrays.copyOf(contractName.getBytes(), contractName.getBytes().length);
        return Hex.toHexString(addr).toUpperCase();
    }

    /**
     * transfer evm address to xChain address
     *
     * @param evmAddr
     * @return String
     */
    private static String evmAddrToXChainAddr(String evmAddr) {
        byte[] addr = Hex.decode(evmAddr);
        byte[] bufVersion = new byte[]{1};
        byte[] addressBytes = new byte[bufVersion.length + addr.length + 4];
        System.arraycopy(bufVersion, 0, addressBytes, 0, bufVersion.length);
        System.arraycopy(addr, 0, addressBytes, bufVersion.length, addr.length);
        byte[] checkCode = Hash.doubleSha256(addressBytes, 0, bufVersion.length + addr.length);
        System.arraycopy(checkCode, 0, addressBytes, bufVersion.length + addr.length, 4);
        return Base58.encode(addressBytes);
    }

    /**
     * transfer evm address to xChain contract account
     *
     * @param evmAddr
     * @return String
     */
    private static String evmAddrToXChainContractAddr(String evmAddr) {
        byte[] addrDecode = Hex.decode(evmAddr);
        String addr = "";
        try {
            addr = new String(addrDecode, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ACCOUNT_PREFIX + addr.substring(4) + "@xuper";
    }

    /**
     * transfer evm address to xChain contract name
     *
     * @param evmAddr
     * @return String
     */
    private static String evmAddrToXChainContractName(String evmAddr) {
        byte[] addrDecode = Hex.decode(evmAddr);
        String addr = "";
        try {
            addr = new String(addrDecode, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int prefixIndex = addr.lastIndexOf("-");


        return addr.substring(prefixIndex + 1);
    }

    /**
     * determine whether it is a contract account
     *
     * @param addr xChain地址
     * @return boolean
     */
    public static boolean isContractAddr(String addr) {
        if (addr.isEmpty()) {
            return false;
        }

        // 前缀匹配
        if (!addr.startsWith(ACCOUNT_PREFIX)) {
            return false;
        }

        String prefix = addr.split(ACCOUNT_BCNAME_SEP)[0];
        prefix = prefix.substring(ACCOUNT_PREFIX.length());
        if (prefix.length() != ACCOUNT_SIZE) {
            return false;
        }

        // prefix每个字符是否在0-9之间
        for (int i = 0; i < ACCOUNT_SIZE; i++) {
            char c = prefix.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }

        return true;
    }

    /**
     * determine whether it is a contract name
     *
     * @param contractName xchain合约名
     * @return boolean
     */
    public static boolean isContractName(String contractName) {
        Integer contractSize = contractName.length();
        if (contractSize > CONTRACT_NAME_MAX_SIZE || contractSize < CONTRACT_NAME_MIN_SIZE) {
            return false;
        }

        return contractName.matches(CONTRACT_NAME_REGEX);
    }

}
