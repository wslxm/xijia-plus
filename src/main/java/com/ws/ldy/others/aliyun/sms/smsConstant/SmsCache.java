package com.ws.ldy.others.aliyun.sms.smsConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证码 JVM 缓存，用于登录验证及其他验证
 * @author wangsong
 * @date 2020/9/30 0030 22:33
 * @return
 * @version 1.0.0
 */
public class SmsCache {


    /**
     * 手机验证码缓存, key=手机号
     * TODO 可深度优化, 相同手机号重复获取验证码,会导致下一个覆盖上一个, 可使用链表存储Value, 每次获取或者添加清理过期调数据
     */
    public static Map<String, SmsCode> smsCache = new HashMap<>();


    //TODO 应提供 通过手机号加验证码查询  验证码是否正确，是否过期方法

}
