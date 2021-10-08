package com.github.wslxm.springbootplus2.starter.wechat.mp.config;


import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 微信公众号相关服务自动注册.
 *
 * @author someone
 */
@Configuration
public class WxMpServiceAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService(WxMpConfigStorage configStorage,WxMpProperties wxMpProperties) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(WxMpProperties wxMpProperties) {
        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(wxMpProperties.getAppId());
        wxMpConfigStorage.setSecret(wxMpProperties.getSecret());
        wxMpConfigStorage.setToken(wxMpProperties.getToken());
        wxMpConfigStorage.setAesKey(wxMpProperties.getAesKey());
        return wxMpConfigStorage;
    }
}
