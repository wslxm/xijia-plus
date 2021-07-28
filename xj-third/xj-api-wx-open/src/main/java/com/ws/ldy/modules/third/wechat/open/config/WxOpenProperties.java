package com.ws.ldy.modules.third.wechat.open.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * wx 登陆常量配置类
 * 示例配置：
 * ``````
 * ## 登录处理回调地址
 * wechat:
 *   open:
 *     ## 回调地址-页面
 *     callbackUrl: https://portal.clzytech.com/portal/loginback?method=weichat
 *     ## 自己的appID和appKey
 *     appId: wx000000000000000
 *     appSecret: 0000000000000000000000000
 * ``````
 */
@Configuration
@ConfigurationProperties(prefix = "wechat.open")
@Data
@ToString
public class WxOpenProperties {

	private String appId;
	private String appSecret;
	private String callbackUrl;

}

