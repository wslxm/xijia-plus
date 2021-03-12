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
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.modules.third.aliyun.sms.model.SmsCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
//@Data
public class AliSmsUtil {

    public final String product = "Dysmsapi";
    public final String domain = "dysmsapi.aliyuncs.com";

    @Value("${aliyun.sms.accessKeyId}")
    public String accessKeyId;
    @Value("${aliyun.sms.accessKeySecret}")
    public String accessKeySecret;
    @Value("${aliyun.sms.signName}")
    public String signName;
    @Value("${aliyun.sms.templateCode}")
    public String templateCode;

    /**
     * 获取签名
     * @return
     */
    public String getSignName() {
        return signName;
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
    public boolean sendMsg(String phones, String SignName, String templateCode, String templateParam) {
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


    //===================================================================================
    //============================== 短信验证码start =====================================
    //===================================================================================

    /**
     * 手机验证码缓存, key=手机号
     */
    public Map<String, SmsCode> smsCache = new ConcurrentHashMap<>();
    /**
     * 短信验证码有效期(5分钟)
     */
    public final Long SMS_VALID_PERIOD = 1000 * 60 * 5L;

    public Map<String, SmsCode> getSmsCache() {
        return smsCache;
    }


    /**
     * 短信模板一.1：发送短信验证码 (发送6位数的验证码)
     * @param phone
     * @param type 1=正式短信 2=测试短信
     * @return
     * <P>
     *  正确code 返回200, data = 验证码
     *  错误code 不等于200, msg返回对应的错误信息
     * </P>
     */
    public R<String> sendCode(String phone) {
        // 拼接参数
        String code = RandomUtil.code(6);
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("code", code);
        // 发送短信
        boolean result = this.sendMsg(phone, signName, templateCode, JSON.toJSONString(mapParam));
        // 缓存验证码
        long time = System.currentTimeMillis() + SMS_VALID_PERIOD;
        smsCache.put(phone, new SmsCode(code, time));
        log.info("发送短信验证码成功: phone:{}  code:{}  result:{} ,过期时间:{} ", phone, code, result, new Date(time));
        // 返回
        return R.success(code);
    }

    /**
     * 短信模板一.2：发送短信验证码 (验证是否有效和过期)
     * @param phone
     * <P>
     *     正确code 返回200
     *     错误code 不等于200, msg返回对应的错误信息
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
                return R.error(RType.SMS_INVALID.getValue(), "验证码过期");
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
