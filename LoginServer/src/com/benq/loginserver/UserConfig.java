package com.benq.loginserver;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Henry.Wu on 2015/8/31.
 */

public class UserConfig {

    protected static final Logger logger = LoggerFactory.getLogger(UserConfig.class);
    public static String Cfg_LoginServer = System.getProperty("user.dir") + "/config/login_server.xml";

    protected static XMLConfiguration config;

    public static XMLConfiguration getConfig() {
        if (config == null) {
            try {
                config = new XMLConfiguration(UserConfig.Cfg_LoginServer);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }

            if (config == null) {
                logger.error("load config file error");
            }
        }

        return config;
    }
}
