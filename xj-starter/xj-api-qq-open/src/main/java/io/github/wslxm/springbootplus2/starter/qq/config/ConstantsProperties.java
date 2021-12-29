package io.github.wslxm.springbootplus2.starter.qq.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * qq 登陆常量配置类
 * @author wangsong
 */

@Configuration
@ConfigurationProperties(prefix = "qq")
@Data
@ToString
public class ConstantsProperties {

	private String appId;
	private String appSecret;
	private String redirectUrl;
}

