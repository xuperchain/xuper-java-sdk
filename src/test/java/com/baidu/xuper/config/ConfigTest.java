package com.baidu.xuper.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConfigTest {
    @Test
    public void testDefaultConfig() {
        Config c = Config.getInstance();
        assertFalse(Config.hasConfigFile());
        assertEquals(c.getCrypto(), "xchain");
        assertEquals(c.getMinNewChainAmount(), "100");
        assertFalse(c.getComplianceCheck().isNeedComplianceCheck());
        assertFalse(c.getComplianceCheck().isNeedComplianceCheckFee());
        assertEquals(c.getComplianceCheck().getComplianceCheckEndorseServiceFee(), 10);
        assertEquals(c.getComplianceCheck().getComplianceCheckEndorseServiceFeeAddr(), "XBbhR82cB6PvaLJs3D4uB9f12bhmKkHeX");
        assertEquals(c.getComplianceCheck().getComplianceCheckEndorseServiceAddr(), "TYyA3y8wdFZyzExtcbRNVd7ZZ2XXcfjdw");
    }

    @Test
    public void testConfigWithFile() {
//        String p = getClass().getResource("./conf/sdk.yaml").getPath();
//        Config.setConfigPath(p);
//        System.out.println(p);
//        Config c = Config.getInstance();
//        assertTrue(Config.hasConfigFile());
//        assertEquals(c.getCrypto(), "xchain");
//        assertEquals(c.getMinNewChainAmount(), "100");
//        assertTrue(c.getComplianceCheck().getIsNeedComplianceCheck());
//        assertTrue(c.getComplianceCheck().getIsNeedComplianceCheckFee());
//        assertEquals(c.getComplianceCheck().getComplianceCheckEndorseServiceFee(), 400);
//        assertEquals(c.getComplianceCheck().getComplianceCheckEndorseServiceFeeAddr(), "ejD5M7phBVW5vKsz9RY86ZwomjK5CHekK");
//        assertEquals(c.getComplianceCheck().getComplianceCheckEndorseServiceAddr(), "ejD5M7phBVW5vKsz9RY86ZwomjK5CHekK");
    }
}
