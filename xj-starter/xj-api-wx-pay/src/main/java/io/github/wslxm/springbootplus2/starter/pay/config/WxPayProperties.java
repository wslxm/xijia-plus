package io.github.wslxm.springbootplus2.starter.pay.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 微信支付配置
 * <P> 这里 WxPay2Config  添加2的原因是 sdk 占用了WxPayConfig 这个名称 </P>
 * @author wangsong
 * @date 2020/12/26 0026 18:35
 * @return
 * @version 1.0.1
 */
@Configuration
@ConfigurationProperties(prefix = "wx.pay")
@Data
@Slf4j
public class WxPayProperties {

    /**
     * 基础配置: 设置微信公众号或者小程序等的appid
     */
    private String appId;
    /**
     * 基础配置: 微信支付商户号
     */
    private String mchId;
    /**
     * 基础配置: 微信支付商户密钥
     */
    private String mchKey;
    /**
     *  基础配置: 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
     */
    private String subAppId;
    /**
     * 基础配置: 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
     */
    private String subMchId;
    /**
     * 基础配置:  apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;
    /**
     * 基础配置: 业务参数: 回调地址 base
     */
    private String notifyBase;
    /**
     *  基础配置: 业务参数: 支付回调接口
     */
    private String orderNotifyUrl;
    /**
     * 基础配置: 业务参数: 退款回调接口
     */
    private String refundNotifyUrl;

}
