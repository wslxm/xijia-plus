//package com.github.wslxm.modules.third.aliyun.sms.util;
//
//import com.alibaba.fastjson.JSON;
//import com.github.wslxm.modules.third.aliyun.sms.smsConstant.SmsCache;
//import com.github.wslxm.modules.third.aliyun.sms.smsConstant.SmsCode;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 短信模板
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2020/9/30 0030 22:38
// * @version 1.0.0
// */
//@Slf4j
//@Component
//public class AilSmsTemplateUtil {
//
//
//    @Autowired
//    private SmsCache smsCache;
//
//
//    @Autowired
//    private AliSmsUtil aliSmsUtil;
//
//    /**
//     * 阿里云  --> 发送测试短信验证码 (随机6位数,  自动记录到 SmsCache 缓存中，有效期 5分钟)
//     * <p>
//     *     模板签名：   阿里云短信测试专用
//     *     模板code：  SMS_141915022
//     *     参数1：code 随机验证码
//     * </p>
//     */
//    public String sendTest(String phone) {
//
//    }
//}
