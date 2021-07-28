package com.ws.ldy.config.swagger;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 七牛云 oss 配置类
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "swagger")
@Data
@Slf4j
public class SwaggerProperties {

    private Map<String, Object> packages = new HashMap<>();
    private String author = "兮家小二";
    private String email = "1720696548@qq.com";
    private String url = "https://gitee.com/wslxm/spring-boot-plus2";
    private String termsOfServiceUrl = "127.0.0.1";
    private String version = "0.0.1";
    private String defaultKey = "TOKEN";
    private String defaultValue = "token";
    // 是否展示接口文档
    private Boolean isShow = true;
}

