package io.github.wslxm.springbootplus2.starter.wechat.app.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
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
    public WxMaService wxMaService(WxAppProperties wxAppProperties) {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(wxAppProperties.getAppId());
        config.setSecret(wxAppProperties.getSecret());
        //  config.setToken(wxAppProperties.getToken());
        //  config.setAesKey(wxAppProperties.getAesKey());
        //  config.setMsgDataFormat(a.getMsgDataFormat());
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }
}

