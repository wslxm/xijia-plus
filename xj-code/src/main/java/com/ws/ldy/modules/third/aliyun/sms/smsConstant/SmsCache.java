package com.ws.ldy.modules.third.aliyun.sms.smsConstant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信验证码 JVM 缓存，用于登录验证及其他验证
 * @author wangsong
 * @date 2020/9/30 0030 22:33
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class SmsCache {


    /**
     * 手机验证码缓存, key=手机号
     * TODO 可深度优化, 相同手机号重复获取验证码,会导致下一个覆盖上一个, 可使用链表存储Value, 每次获取或者添加清理过期调数据
     */
    public static Map<String, SmsCode> smsCache = new ConcurrentHashMap<>();

    /**
     * 短信验证码有效期(5分钟)
     */
    public final static Long SMS_VALID_PERIOD = 1000 * 60 * 5L;

    public final static String SMS_INVALID = "SMS_INVALID";    // 无效
    public final static String SMS_EXPIRED = "SMS_EXPIRED";    // 过期
    public final static String SMS_OK = "OK";                  // 验证通过


    /**
     * 验证短信的验证码是否有效和过期
     * @param phone
     * @param code
     */
    public static String verifySMS(String phone, String code) {
        boolean result = SmsCache.smsCache.containsKey(phone);
        if (!result) {
            return SMS_INVALID;
        } else {
            SmsCode smsCode = SmsCache.smsCache.get(phone);
            if (!code.equals(smsCode.getCode())) {
                //验证码无效
                return SMS_INVALID;
            }
            Long expirationTime = smsCode.getTime();
            if (System.currentTimeMillis() > expirationTime) {
                // 验证码过期
                return SMS_EXPIRED;
            }
        }
        // 清除使用了的验证码
        SmsCache.smsCache.remove(phone);
        return SMS_OK;
    }


    //TODO 应提供 通过手机号加验证码查询  验证码是否正确，是否过期方法
}
