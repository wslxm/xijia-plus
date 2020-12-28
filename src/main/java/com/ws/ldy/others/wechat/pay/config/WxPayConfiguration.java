package com.ws.ldy.others.wechat.pay.config;

import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@AllArgsConstructor
public class WxPayConfiguration {

    @Autowired
    private WxPay2Config wxPayConfig;

    /**
     * 这里sdk 需要提前加载，这里不使用CommandLineRunner 来执行
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() throws Exception {
        // 初始化配置数据
        wxPayConfig.run();
        // sdk
        com.github.binarywang.wxpay.config.WxPayConfig payConfig = new com.github.binarywang.wxpay.config.WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(WxPay2Config.appId));
        payConfig.setMchId(StringUtils.trimToNull(WxPay2Config.mchId));
        payConfig.setMchKey(StringUtils.trimToNull(WxPay2Config.mchKey));
        payConfig.setSubAppId(StringUtils.trimToNull(WxPay2Config.subAppId));
        payConfig.setSubMchId(StringUtils.trimToNull(WxPay2Config.subMchId));
        payConfig.setKeyPath(StringUtils.trimToNull(WxPay2Config.keyPath));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
