package com.lenovo.spider.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 项目配置
 *
 * @author 王崟平-WangYinPing
 */
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static Map<String, String> configMap = new HashMap<String, String>();
    private static String configFileName = "config.properties";

    static {
        Properties prop = new Properties();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + configFileName);
        } catch (FileNotFoundException e) {
            try {
                inStream = new FileInputStream(System.getProperty("user.dir") + "/resources/" + configFileName);
            } catch (FileNotFoundException e1) {
                inStream = Config.class.getResourceAsStream("/" + configFileName);
                if (inStream == null) {
                    log.error("FileNotFoundException:" + configFileName + " not find");
                }
            }
        }
        try {
            prop.load(inStream);
            Set<Entry<Object, Object>> entrySet = prop.entrySet();
            for (Entry<Object, Object> entry : entrySet) {
                configMap.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (IOException e) {
            log.error("IOException:" + configFileName + " read error");
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                log.error("inStream.close IOException", e);
            }
        }
    }

    public static String get(String key) {
        return configMap.get(key);
    }

}
