package com.ws.ldy.common.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * <P>
 *     不区分 yml / properties,都可以读取， 不区分启动环境(自动读取 如：application.yml + application-dev.yml 配置, -dev 为当前启动的环境测试)
 *     1、先读取 application.yml
 *     2、在读取 application.当前启动环境.yml
 *     3、在读取 application.properties
 *     4、在读取 application.当前启动环境.yml
 *
 * </P>
 *
 * @author wangsong
 */
public class PropUtil {


    public static Object findByKey(String key) {
        Object env = findYmlByKey("spring.profiles.active", null, ".yml");
        if (env == null) {
            env = findPropertiesByKey("spring.profiles.active", null, ".properties");
        }
        Object val = findYmlByKey(key, null, ".yml");
        if (val == null) {
            val = findYmlByKey(key, env.toString(), ".yml");
        }
        if (val == null) {
            val = findPropertiesByKey(key, null, ".properties");
        }
        if (val == null) {
            val = findPropertiesByKey(key, env.toString(), ".properties");
        }
        return val;
    }


    /**
     * 获取 properties 配置
     * @param key
     * @param suffix
     * @param env
     * @return
     */
    public static Object findPropertiesByKey(String key, String env, String suffix) {
        String fileName = "application" + (env == null ? "" : "-" + env) + suffix;
        InputStream in = PropUtil.class.getClassLoader().getResourceAsStream(fileName);
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

    /**
     * 获取 yml 配置(可以返回list, yml可读取多层的Map数据或list数据)
     * @param key
     * @param suffix
     * @param env
     * @return
     */
    public static Object findYmlByKey(String key, String env, String suffix) {
        String fileName = "application" + (env == null ? "" : "-" + env) + suffix;
        Resource resource = new ClassPathResource(fileName);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties = yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }
}
