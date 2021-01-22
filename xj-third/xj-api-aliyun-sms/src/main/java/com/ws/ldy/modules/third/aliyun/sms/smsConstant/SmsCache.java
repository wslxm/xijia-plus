package com.ws.ldy.modules.third.aliyun.sms.smsConstant;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;

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

    /**
     * 验证短信的验证码是否有效和过期
     * @param phone
     * @param code
     */
    public static R<String>  verifySMS(String phone, String code) {
        boolean result = SmsCache.smsCache.containsKey(phone);
        if (!result) {
            R.error(RType.SMS_INVALID.getValue(), "验证码无效:该电话号没有未使用的验证码") ;
        } else {
            SmsCode smsCode = SmsCache.smsCache.get(phone);
            if (!code.equals(smsCode.getCode())) {
                //验证码无效
                R.error(RType.SMS_INVALID.getValue(), "验证码错误或已使用") ;
            }
            Long expirationTime = smsCode.getTime();
            if (System.currentTimeMillis() > expirationTime) {
                // 验证码过期
                R.error(RType.SMS_INVALID.getValue(), "验证码过期") ;
            }
        }
        // 清除使用过的验证码
        SmsCache.smsCache.remove(phone);
        return R.success("ok");
    }


    //TODO 应提供 通过手机号加验证码查询  验证码是否正确，是否过期方法
}
