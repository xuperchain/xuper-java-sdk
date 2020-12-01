package com.baidu.xuperunion.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;

public class Config {
    private static Config singletonConfig;

    private String endorseServiceHost;
    private ComplianceCheck complianceCheck;
    private String minNewChainAmount;
    private String crypto;

    private static final String confPath = "./conf";
    private static final String confName = "sdk.yaml";

    private Config() {
    }

    public static Config getInstance() throws Exception {
        if (singletonConfig != null) {
            return singletonConfig;
        }
        Yaml yaml = new Yaml(new Constructor(Config.class));
        return yaml.load(new FileInputStream(new File(confPath + "/" + confName)));
    }

    public String getEndorseServiceHost() {
        return endorseServiceHost;
    }

    public ComplianceCheck getComplianceCheck() {
        return complianceCheck;
    }

    public String getMinNewChainAmount() {
        return minNewChainAmount;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setEndorseServiceHost(String endorseServiceHost) {
        this.endorseServiceHost = endorseServiceHost;
    }

    public void setComplianceCheck(ComplianceCheck complianceCheck) {
        this.complianceCheck = complianceCheck;
    }

    public void setMinNewChainAmount(String minNewChainAmount) {
        this.minNewChainAmount = minNewChainAmount;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }

    public static class ComplianceCheck {
        private boolean isNeedComplianceCheck;
        private boolean isNeedComplianceCheckFee;
        private int complianceCheckEndorseServiceFee;
        private String complianceCheckEndorseServiceFeeAddr;
        private String complianceCheckEndorseServiceAddr;

        public boolean getIsNeedComplianceCheck() {
            return isNeedComplianceCheck;
        }

        public boolean getIsNeedComplianceCheckFee() {
            return isNeedComplianceCheckFee;
        }

        public int getComplianceCheckEndorseServiceFee() {
            return complianceCheckEndorseServiceFee;
        }

        public String getComplianceCheckEndorseServiceFeeAddr() {
            return complianceCheckEndorseServiceFeeAddr;
        }

        public String getComplianceCheckEndorseServiceAddr() {
            return complianceCheckEndorseServiceAddr;
        }

        public void setIsNeedComplianceCheck(boolean needComplianceCheck) {
            isNeedComplianceCheck = needComplianceCheck;
        }

        public void setIsNeedComplianceCheckFee(boolean needComplianceCheckFee) {
            isNeedComplianceCheckFee = needComplianceCheckFee;
        }

        public void setComplianceCheckEndorseServiceFee(int complianceCheckEndorseServiceFee) {
            this.complianceCheckEndorseServiceFee = complianceCheckEndorseServiceFee;
        }

        public void setComplianceCheckEndorseServiceFeeAddr(String complianceCheckEndorseServiceFeeAddr) {
            this.complianceCheckEndorseServiceFeeAddr = complianceCheckEndorseServiceFeeAddr;
        }

        public void setComplianceCheckEndorseServiceAddr(String complianceCheckEndorseServiceAddr) {
            this.complianceCheckEndorseServiceAddr = complianceCheckEndorseServiceAddr;
        }
    }
}
