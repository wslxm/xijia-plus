package com.ws.ldy.others.aliyun.sms;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

/**
 * 短信工具类（目前接入的是阿里）
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/4 0004 16:24 
 * @version 1.0.0
 */
// @formatter:off
public class SmsUtil {

    /**
     * 发送短信测试
     * @param args
     */
    public static void main(String[] args) {
        SmsUtil.loginAuthTest("17628689969");
    }

    /**
     * 登发送录短信验证码（测试）
     * @author wangsong
     * @param phones 电话号码，多个逗号分隔
     * @date 2020/8/4 0004 16:25
     * @return void
     * @version 1.0.0
     */
    public static  void loginAuthTest(String phones) {
        HashMap<String, String> param = new HashMap<String, String>() {{ put("code", RandomStringUtils.randomNumeric(6)); }};
        AliCodeMsg.sendMsg( phones, "验证码", "SMS_171540376"  , JSON.toJSONString(param) );
    }


    /**
     * 发送登录短信验证码
     * @author wangsong
     * @param phones 电话号码，多个逗号分隔
     * @date 2020/8/4 0004 16:25
     * @return void
     * @version 1.0.0
     */
    public static String loginAuth(String phones) {
        String code = RandomStringUtils.randomNumeric(6);
        HashMap<String, String> param = new HashMap<String, String>() {{ put("code",code);}};
        AliCodeMsg.sendMsg( phones, "验证码", "SMS_171540376" , JSON.toJSONString(param) );
        return code;
    }
}
