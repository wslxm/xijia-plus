package com.ws.ldy.modules.third.wechat.mq.config;

import com.ws.ldy.common.utils.ConsoleColors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众配置信息 (yml 中)
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 17:57
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "wx.mq")
@Data
@Slf4j
public class WxMqProperties {


    public String appId;   // 设置微信公众号的appid
    public String secret;  // 设置微信公众号的app secret
    public String token;    // 设置微信公众号的token
    public String aesKey;   // 设置微信公众号的EncodingAESKey


    public void println() {
//            if (isWxMp) {
//                //正式环境
//                appId = "wxa997b110821cbfd6";
//                secret = "a5cee82a40995afc5bc14d17058908c1";
//                token = "123456789";
//                aesKey = "123456789";
//            } else {
//                //测试环境
//                appId = "wxa997b110821cbfd6";
//                secret = "a5cee82a40995afc5bc14d17058908c1";
//                token = "123456789";
//                aesKey = "123456789";
//            }
        log.info(ConsoleColors.YELLOW_BRIGHT +
                "\r\n" +
                "|--- 微信MP(公众号相关)配置加载成功 ---| \r\n" +
                "|  isWxMp: {} \r\n" +
                "|  appId: {} \r\n" +
                "|  secret: {} \r\n" +
                "|  token: {} \r\n" +
                "|  aesKey: {} \r\n" +
                "| ----------------------------------|"
                + ConsoleColors.RESET, appId, secret, token, aesKey);
    }
}
