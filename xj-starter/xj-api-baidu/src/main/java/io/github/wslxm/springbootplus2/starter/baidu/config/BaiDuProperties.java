package io.github.wslxm.springbootplus2.starter.baidu.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云oss 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.1
 */
@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "baidu.text")
@Data
@Slf4j
public class BaiDuProperties {

    public String appId;
    public String apiKey;
    public String secretKey;

}

