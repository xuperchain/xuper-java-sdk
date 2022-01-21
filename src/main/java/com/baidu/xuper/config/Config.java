package com.baidu.xuper.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Config {
    private static Config singletonConfig;
    private static String confFilePath;
    private static InputStream confFileInputStream;

    private String endorseServiceHost;
    private ComplianceCheck complianceCheck;
    private String minNewChainAmount;
    private String crypto;
    
    private Config() {
    }

    public static void setConfigPath(String path) throws FileNotFoundException {
        confFilePath = path;
        setConfigInputStream(new FileInputStream(path));
    }
    public static void setConfigInputStream(InputStream inputStream) {
        confFileInputStream = inputStream;
    }
    public static boolean hasConfigFile() {
        return confFilePath != null || confFileInputStream != null;
    }

    public static Config getInstance() {
        if (singletonConfig != null) {
            return singletonConfig;
        }

        if (hasConfigFile()) {
            try {
                singletonConfig = getConfigFromYaml();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            singletonConfig = getDefaultConfig();
        }

        return singletonConfig;
    }

    private static Config getConfigFromYaml() throws Exception {
        Yaml yaml = new Yaml(new Constructor(Config.class));
        return yaml.load(confFileInputStream!=null?confFileInputStream:new FileInputStream(new File(confFilePath)));
    }

    private static Config getDefaultConfig() {
        singletonConfig = new Config();
        singletonConfig.minNewChainAmount = "100";
        singletonConfig.crypto = "xchain";
        singletonConfig.endorseServiceHost = "";

        ComplianceCheck c = new ComplianceCheck();
        c.isNeedComplianceCheck = false;
        c.isNeedComplianceCheckFee = false;
        c.complianceCheckEndorseServiceFee = 10;
        c.complianceCheckEndorseServiceFeeAddr = "XBbhR82cB6PvaLJs3D4uB9f12bhmKkHeX";
        c.complianceCheckEndorseServiceAddr = "TYyA3y8wdFZyzExtcbRNVd7ZZ2XXcfjdw";

        singletonConfig.complianceCheck = c;
        return singletonConfig;
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
