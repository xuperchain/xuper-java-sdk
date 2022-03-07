package com.baidu.xuper.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressTransTest {

    static class InfoCase {
        String xChainAddress;
        String type;
        String evmAddr;

        static InfoCase[] cases() {
            InfoCase[] cases = new InfoCase[3];

            cases[0] = new InfoCase();
            cases[0].setxChainAddress("XC1111111111111113@xuper");
            cases[0].setType("contract-account");
            cases[0].setEvmAddr("3131313231313131313131313131313131313133");

            cases[1] = new InfoCase();
            cases[1].setxChainAddress("dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN");
            cases[1].setType("xchain");
            cases[1].setEvmAddr("93F86A462A3174C7AD1281BCF400A9F18D244E06");

            cases[2] = new InfoCase();
            cases[2].setxChainAddress("storagedata11");
            cases[2].setType("contract-name");
            cases[2].setEvmAddr("313131312D2D2D73746F72616765646174613131");

            return cases;

        }

        public String getxChainAddress() {
            return xChainAddress;
        }

        public void setxChainAddress(String xChainAddress) {
            this.xChainAddress = xChainAddress;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEvmAddr() {
            return evmAddr;
        }

        public void setEvmAddr(String evmAddr) {
            this.evmAddr = evmAddr;
        }
    }


    /**
     * xChain address transfer to evm address
     */
    @Test
    public void xChainToEvmAddress() {
        InfoCase[] cases = InfoCase.cases();
        for (InfoCase infoCase : cases) {
            AddressTrans addressTrans = AddressTrans.xChainToEvmAddress(infoCase.getxChainAddress());
            assertEquals(infoCase.getEvmAddr(), addressTrans.getAddr());
            assertEquals(infoCase.getType(), addressTrans.getAddrType());
        }
    }

    /**
     * evm address transfer to xChain address
     */
    @Test
    public void evmToXChainAddress() {
        InfoCase[] cases = InfoCase.cases();
        for (InfoCase infoCase : cases) {
            AddressTrans addressTrans = AddressTrans.evmToXChainAddress(infoCase.getEvmAddr());
            assertEquals(infoCase.getxChainAddress(), addressTrans.getAddr());
            assertEquals(infoCase.getType(), addressTrans.getAddrType());
        }
    }

}
