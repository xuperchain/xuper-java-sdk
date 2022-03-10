package com.baidu.xuper.api;

import lombok.Data;

@Data
public class AddressTrans {

    @Data
    public class AddrInfo {
        private String addr;
        private String addrType;
    }

    /**
     * xChain address transfer to evm address:
     * xChain address can be xChain contract account, AK address, xChain contract name.
     *
     * @param xChainAddr
     * @return AddrInfo
     */
    public AddrInfo xChainToEvmAddress(String xChainAddr) {

        return null;
    }

    /**
     * evm address transfer to xChain address:
     * evmAddr can be evm contract account, AK address, xChain contract name.
     *
     * @param evmAddr
     * @return AddrInfo
     */
    public AddrInfo evmToXChainAddress(String evmAddr) {

        return null;
    }

    /**
     * transfer xChain address to evm address
     *
     * @param xChainAddr
     * @return
     */
    private String xChainAddrToEvmAddr(String xChainAddr) {

        return "";
    }

    /**
     * transfer xChain contract account to evm address
     *
     * @param xChainAddr
     * @return
     */
    private String xChainContractAddrToEvmAddr(String xChainAddr) {

        return "";
    }

    /**
     * transfer xChain contract name to evm address
     *
     * @param xChainAddr
     * @return String
     */
    private String xChainContractNameToEvmAddr(String xChainAddr) {

        return "";
    }

    /**
     * transfer evm address to xChain address
     *
     * @param evmAddr
     * @return String
     */
    private String evmAddrToXChainAddr(String evmAddr) {

        return "";
    }

    /**
     * transfer evm address to xChain contract account
     *
     * @param evmAddr
     * @return String
     */
    private String evmAddrToXChainContractAddr(String evmAddr) {

        return "";
    }

    /**
     * transfer evm address to xChain contract name
     *
     * @param evmAddr
     * @return String
     */
    private String evmAddrToXChainContractName(String evmAddr) {

        return "";
    }

    /**
     * determine whether it is a contract account
     *
     * @param addr
     * @return boolean
     */
    private boolean isContractAddr(String addr) {

        return false;
    }

    /**
     * determine whether it is a contract name
     *
     * @param name
     * @return boolean
     */
    private boolean isContractName(String name) {

        return false;
    }

}
