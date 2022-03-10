package com.baidu.xuper.config;

import java.io.File;
import java.io.FileInputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import lombok.Data;

@Data
public class Config {
    private static Config singletonConfig;
    private static String confFilePath;

    private String endorseServiceHost;
    private ComplianceCheck complianceCheck;
    private String minNewChainAmount;
    private String crypto;
    private Integer txVersion;

    private Config() {
    }

    public static void setConfigPath(String path) {
        confFilePath = path;
    }

    public static boolean hasConfigFile() {
        return confFilePath != null;
    }

    public static Config getInstance() {
        if (singletonConfig != null) {
            return singletonConfig;
        }

        if (confFilePath != null) {
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
        return yaml.load(new FileInputStream(new File(confFilePath)));
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
        singletonConfig.txVersion = 1;
        return singletonConfig;
    }

    @Data
    public static class ComplianceCheck {
        private boolean isNeedComplianceCheck;
        private boolean isNeedComplianceCheckFee;
        private int complianceCheckEndorseServiceFee;
        private String complianceCheckEndorseServiceFeeAddr;
        private String complianceCheckEndorseServiceAddr;
    }
}
