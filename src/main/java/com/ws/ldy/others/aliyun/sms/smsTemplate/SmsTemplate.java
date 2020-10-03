package com.ws.ldy.others.aliyun.sms.smsTemplate;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.common.utils.RandomUtil;
import com.ws.ldy.others.aliyun.sms.smsConstant.SmsCache;
import com.ws.ldy.others.aliyun.sms.smsConstant.SmsCode;
import com.ws.ldy.others.aliyun.sms.util.AliSmsUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信模板 （外部使用此API 调用即可）
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 22:38
 * @version 1.0.0
 */
@Slf4j
public class SmsTemplate {

    /**
     * 阿里云  --> 发送测试短信验证码 (随机6位数,  自动记录到 SmsCache 缓存中，有效期 5分钟)
     * <p>
     *     模板签名：   阿里云短信测试专用
     *     模板code：  SMS_141915022
     *     参数1：code 随机验证码
     * </p>
     */
    public static String sendTest(String phone) {
        // 拼接参数
        String code = RandomUtil.code(6);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("code", code);
        // 发送短信
        boolean result = AliSmsUtil.sendMsg(phone, "阿里云短信测试专用", "SMS_141915022", JSON.toJSONString(mapParam));
        // 缓存验证码
        long time = System.currentTimeMillis() + 60 * 1000 * 5;
        SmsCache.smsCache.put(phone, new SmsCode(code, time));
        log.info("发送短信验证码成功: phone{}  code:{}  result:{} ,过期时间:{} ", phone, code, result, new Date(time));
        // 返回
        return code;
    }
}
