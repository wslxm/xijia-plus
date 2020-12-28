package com.ws.ldy.others.wechat.pay.config;

import com.ws.ldy.common.utils.ConsoleColors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 微信支付配置
 * <P> 这里 WxPay2Config  添加2的原因是 sdk 占用了WxPayConfig 这个名称 </P>
 * @author wangsong
 * @date 2020/12/26 0026 18:35
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("ALL")
@Data
@Slf4j
@Component
public class WxPay2Config { //implements CommandLineRunner // sdk 原文件名 WxPayConfiguration.java

    /**
     * 环境配置(true= 正式环境  false= 测试环境)
     */
    @Value("${xj.isWxPay:false}")
    private boolean isWxPay;

    // 基础测试
    public static String appId;    // 设置微信公众号或者小程序等的appid
    public static String mchId;    // 微信支付商户号
    public static String mchKey;   // 微信支付商户密钥
    public static String subAppId; // 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
    public static String subMchId; // 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
    public static String keyPath;  // apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
    // 业务参数
    public static String notifyBase;        // 回调地址base
    public static String orderNotifyUrl;    // 支付回调地址
    public static String refundNotifyUrl;   // 退款回调地址

    /**
     * 加载配置，此处被 sdk 调用
     */
    public void run() throws Exception {
        if (this.isWxPay) {
            // 正式环境(使用内网)
            appId = "wxf35282d71706e2f5";
            mchId = "1512069221";
            mchKey = "v6fi8r2wegYnhOqve52tLzJrYk8L6GxS";
            subAppId = "";
            subMchId = "";
            keyPath = "classpath:cert/apiclient_cert.p12";
            notifyBase = "http://xijia.plus/index";
        } else {
            // 测试环境(使用外网)
            appId = "wxf35282d71706e2f5";
            mchId = "1512069221";
            mchKey = "v6fi8r2wegYnhOqve52tLzJrYk8L6GxS";
            subAppId = "";
            subMchId = "";
            keyPath = "classpath:cert/apiclient_cert.p12";
            notifyBase = "http://xijia.plus/index";
        }
        // 回调接口
        orderNotifyUrl = notifyBase + "/api/pay/notify/order";
        refundNotifyUrl = notifyBase + "/api/pay/notify/refund";
        // 打印参数
        log.info(ConsoleColors.YELLOW_BRIGHT +
                "\r\n" +
                "|---  WxPay微信支付模块配置加载成功  ---| \r\n" +
                "|  isWxPay: {} \r\n" +
                "|  appId: {} \r\n" +
                "|  mchId : {} \r\n" +
                "|  mchKey : {} \r\n" +
                "|  subAppId : {} \r\n" +
                "|  subMchId : {} \r\n" +
                "|  keyPath : {} \r\n" +
                "|  notifyBase : {} \r\n" +
                "|  orderNotifyUrl : {} \r\n" +
                "|  refundNotifyUrl : {} \r\n" +
                "| ----------------------------------|"
                + ConsoleColors.RESET, isWxPay, appId, mchId, mchKey, subAppId, subMchId, keyPath, notifyBase, orderNotifyUrl, refundNotifyUrl);
    }
}
