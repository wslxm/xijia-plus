package com.ws.ldy.modules.third.wechat.app.config;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaQrcodeServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信配置类〉
 *
 * @author yc
 * @create 2019/12/18 9:26
 * @since 1.0.0
 */
@Configuration
public class WxAppConfig {


    @Bean
    public WxMaQrcodeService wxMaQrcodeService(WxAppProperties xxAppProperties){
        WxMaService wxMaService = new WxMaServiceImpl();
        WxMaInMemoryConfig wxMaConfig = new WxMaInMemoryConfig();
        wxMaConfig.setAppid(xxAppProperties.getAppId());
        wxMaConfig.setSecret(xxAppProperties.getSecret());
        wxMaService.setWxMaConfig(wxMaConfig);
        return new WxMaQrcodeServiceImpl(wxMaService);
    }

}

