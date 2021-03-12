package com.ws.ldy.modules.third.sms1086.util;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.modules.third.sms1086.model.SmsCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1086 短信服务接口
 * <P>
 *   # yml 短信配置
 *   sms1086:
 *      signName: 致上联品
 *      username: zhishanglianpin
 *      password:
 * </P>
 * @author wangsong
 * @date 2021/3/9 0009 19:08
 * @return
 * @version 1.0.0
 */
@Component
@Slf4j
public class Sms1086Util {

    /**
     * 签名，注册的公司名或 简写,必须和账号绑定的一致
     */
    @Value("${sms1086.signName}")
    private String signName;
    /**
     *  账号
     */
    @Value("${sms1086.username}")
    private String username;
    /**
     *  密码
     */
    @Value("${sms1086.password}")
    private String password;

    /**
     * 发送短信
     * mobiles：手机号,最多支持600个,逗号分隔
     * content  任意内容
     * 参数文档： http://www.1086sms.com/jszc/MassApi.html
     */
    public R<String> sendMsg(String mobiles, String content) {
        URL url = null;
        try {
            String strUrl = "http://api.sms1086.com/api/Send.aspx" +
                    "?username=" + URLEncoder.encode(username, "GB2312")
                    + "&password=" + URLEncoder.encode(password, "GB2312")
                    + "&mobiles=" + URLEncoder.encode(mobiles, "GB2312")
                    + "&content=" + URLEncoder.encode(content + "【" + signName + "】", "GB2312");
            url = new URL(strUrl);
            URLConnection UConn = url.openConnection();
            BufferedReader breader = new BufferedReader(new InputStreamReader(UConn.getInputStream()));
            String str = breader.readLine();
            while (str != null) {
                str = URLDecoder.decode(str, "GB2312");
                String[] strs = str.split("&");
                if (strs[0].replace("result=", "").trim().equals("0")) {
                    log.info("发送短信成功: phone:{}  content:{}  result:{}  ", mobiles, content, str);
                    return R.error(RType.SYS_SUCCESS);
                } else {
                    log.info("短信发送失败。失败原因：{}", strs[1].replace("description=", ""));
                }
                System.out.print(str);
                str = breader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error(RType.SMS_FAIL);
    }

    //===================================================================================
    //============================== 短信验证码start =====================================
    //===================================================================================


    /**
     * 手机验证码缓存, key=手机号
     */
    public Map<String, SmsCode> smsCache = new ConcurrentHashMap<>();
    /**
     * 短信验证码有效期(5分钟- 1000L  * 60 * 5)
     */
    public final Long SMS_VALID_PERIOD = 1000L * 60 * 5;

    public Map<String, SmsCode> getSmsCache() {
        return smsCache;
    }


    /**
     * 1、发送短信验证码 -> 发送
     * <P>
     *     mobiles：手机号,只支持1个
     *     content：如：6位验证码
     * </P>
     * 参数文档： http://www.1086sms.com/jszc/MassApi.html
     */
    public R<String> sendCode(String mobiles) {
        URL url = null;
        String code = RandomUtil.code(6);
        try {
            String strUrl = "http://api.sms1086.com/api/verifycode.aspx" +
                    "?username=" + URLEncoder.encode(username, "GB2312")
                    + "&password=" + URLEncoder.encode(password, "GB2312")
                    + "&mobiles=" + URLEncoder.encode(mobiles, "GB2312")
                    + "&content=" + URLEncoder.encode("【" + signName + "】验证码:" + code, "GB2312");
            url = new URL(strUrl);
            URLConnection uConn = url.openConnection();
            BufferedReader breader = new BufferedReader(new InputStreamReader(uConn.getInputStream()));
            String str = breader.readLine();
            while (str != null) {
                str = URLDecoder.decode(str, "GB2312");
                String[] strs = str.split("&");
                if (strs[0].replace("result=", "").trim().equals("0")) {
                    // 成功缓存验证码到jvm
                    long time = System.currentTimeMillis() + SMS_VALID_PERIOD;
                    smsCache.put(mobiles, new SmsCode(code, time));
                    log.info("发送短信验证码成功: phone:{}  code:{}  result:{} ,过期时间:{} ", mobiles, code, str, new Date(time));
                    return R.success(code);
                } else {
                    log.info("短信发送失败。失败原因：{}", strs[1].replace("description=", ""));
                }
                str = breader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error(RType.SMS_FAIL);

        // 模拟发送
//        String code = "123456";
//        long time = System.currentTimeMillis() + SMS_VALID_PERIOD;
//        smsCache.put(mobiles, new SmsCode(code, time));
//        log.info("发送短信验证码成功: phone:{}  code:{}  result:{} ,过期时间:{} ", mobiles, code, "模拟发送成功", new Date(time));
//        return R.success(code);
    }


    /**
     * 2：发送短信验证码 -> 验证是否有效和过期)
     * @param phone
     * <P>
     *    正确code 返回200
     *    错误code 不等于200, msg返回对应的错误信息
     * </P>
     */
    public R<String> verifySMS(String phone, String code) {
        boolean result = smsCache.containsKey(phone);
        if (!result) {
            return R.error(RType.SMS_INVALID.getValue(), "验证码无效");
        } else {
            SmsCode smsCode = smsCache.get(phone);
            if (!code.equals(smsCode.getCode())) {
                //验证码无效
                return R.error(RType.SMS_INVALID.getValue(), "验证码错误或已使用");
            }
            Long expirationTime = smsCode.getTime();
            if (System.currentTimeMillis() > expirationTime) {
                // 验证码过期
                return R.error(RType.SMS_EXPIRED.getValue(), "验证码过期");
            }
        }
        // 清除使用过的验证码
        smsCache.remove(phone);
        return R.success("ok");
    }
    //===================================================================================
    //============================== 短信验证码start ======================================
    //===================================================================================
}
