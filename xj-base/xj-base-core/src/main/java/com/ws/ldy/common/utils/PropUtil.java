package com.ws.ldy.common.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 *
 * @author wangsong
 * @date 2019年5月31日 下午7:03:14
 */
public class PropUtil {


    public static String findByKey(String key) {
        String env = findByKey("spring.profiles.active", ".yml", null);
        if (env == null) {
            env = findByKey("spring.profiles.active", ".properties", null);
        }
        String val = findByKey(key, ".yml", null);
        if (val == null) {
            val = findByKey(key, ".yml", env);
        }
        if (val == null) {
            val = findByKey(key, ".properties", null);
        }
        if (val == null) {
            val = findByKey(key, ".properties", env);
        }
        return val;
    }


    /**
     * 读取配置文件
     */
    public static String findByKey(String key, String suffix, String env) {
        InputStream in = PropUtil.class.getClassLoader().getResourceAsStream("application" + suffix + (env == null ? "" : "-" + env));
        Properties prop = new Properties();
        String rw = null;
        try {
            prop.load(in);
            rw = prop.getProperty(key);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return rw;
    }
}
