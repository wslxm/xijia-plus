package com.ws.ldy.modules.third.aliyun.sms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.ConsoleColors;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.third.aliyun.sms.smsConstant.SmsCache;
import com.ws.ldy.modules.third.aliyun.sms.smsTemplate.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/1 0001 11:39
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Slf4j
@Component
public class AliSmsUtil {

    public static final String product = "Dysmsapi";
    public static final String domain = "dysmsapi.aliyuncs.com";

    // 默认配置,如果yml配置了，将覆盖默认配置
    public static String accessKeyId = "";
    public static String accessKeySecret = "";


    @Value("${aliyun.sms.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.sms.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }


    /**
     * 打印配置信息
     */
    public void println() {
        System.out.println();
        log.info(ConsoleColors.YELLOW_BRIGHT +
                "\r\n" +
                "|---      阿里云SMS配置    ---| \r\n" +
                "|  accessKeyId: {} \r\n" +
                "|  accessKeySecret: {} \r\n" +
                "| ----------------------------------|"
                + ConsoleColors.RESET, accessKeyId, accessKeySecret);
    }


    /**
     * 发送短信
     * @author wangsong
     * @param phones 电话号，多个逗号分隔
     * @param SignName  短信签名，控制台管理页面查看
     * @param templateCode  短信模板ID，控制台管理页面查看
     * @param templateParam  短信模板变量对应的实际值，JSON格式。String 否	{"code":"1111"}
     * @date 2020/8/4 0004 16:27
     * @return boolean
     * @version 1.0.0
     */
    public static boolean sendMsg(String phones, String SignName, String templateCode, String templateParam) {
        DefaultProfile profile = DefaultProfile.getProfile(product, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysDomain(domain);
        request.setSysMethod(MethodType.POST);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");   // 系统规定参数。取值：SendSms（非必填）
        request.putQueryParameter("RegionId", "cn-hangzhou");  // 区域（非必填）
        request.putQueryParameter("PhoneNumbers", phones);           // 国内短信：11位手机号码,多个逗号分隔
        request.putQueryParameter("SignName", SignName);             // 短信签名名称。控制台签名管理页面签名名称一列查看。
        request.putQueryParameter("TemplateCode", templateCode);     // 短信模板ID，请在控制台模板管理页面模板CODE一列查看。
        request.putQueryParameter("TemplateParam", templateParam);   // 短信模板变量对应的实际值，JSON格式。String 否	{"code":"1111"}
        request.putQueryParameter("SmsUpExtendCode", null);    // 上行短信扩展码 | SmsUpExtendCode	String	否	90999
        request.putQueryParameter("OutId", null);              // 外部流水扩展字段 | OutId	String	否	abcdefgh
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject respData = JSON.parseObject(response.getData());
            //Object message = respData.get("Code"); OK 表示发送成功
            log.info("发送短信返回数据: {}", respData);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 发送6位数的验证码
     * @param phone
     */
    public static String sendTest(String phone) {
        String code = SmsTemplate.sendTest(phone);
        System.out.println(code);
        return code;
    }


    /**
     * 验证短信的验证码是否有效和过期
     * @param phone
     */
    public static Boolean verifySMS(String phone, String code) {
        String result = SmsCache.verifySMS(phone, code);
        if (result.equals(SmsCache.SMS_INVALID)) {
            throw new ErrorException(RType.SMS_INVALID);
        } else if (result.equals(SmsCache.SMS_EXPIRED)) {
            throw new ErrorException(RType.SMS_EXPIRED);
        } else if (result.equals(SmsCache.SMS_OK)) {
            return true;
        }
        return true;
    }

    /**
     * 测试
     * @author wangsong
     * @param args
     * @date 2020/9/22 0022 18:44
     * @return void
     * @version 1.0.0
     */
    public static void main(String[] args) {
        String code = SmsTemplate.sendTest("17628689969");
        System.out.println(code);
    }
}
