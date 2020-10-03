package com.ws.ldy.others.wechat.wxbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 微信公众配置信息 (yml 中)
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 17:57
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {

    /**
     * 多个公众号配置信息
     */
    private List<MpConfig> configs;

    @Data
    public static class MpConfig {
        /**
         * 设置微信公众号的appid
         */
        private String appId;

        /**
         * 设置微信公众号的app secret
         */
        private String secret;

        /**
         * 设置微信公众号的token
         */
        private String token;

        /**
         * 设置微信公众号的EncodingAESKey
         */
        private String aesKey;
    }
}
