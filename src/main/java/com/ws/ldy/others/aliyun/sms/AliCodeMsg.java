package com.ws.ldy.others.aliyun.sms;

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
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云短信工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/1 0001 11:39
 * @version 1.0.0
 */
@Slf4j
public class AliCodeMsg {

    public static final String product = "Dysmsapi";
    public static final String domain = "dysmsapi.aliyuncs.com";
    public static final String accessKeyId = "LTAI4G3RgFKWyJywrqkpmkUT";//"LTAIcJF8Ho2NfwCW";
    public static final String accessKeySecret = "T3kCBpqWhIQcZ7idULg6XGn9Xyx9Nj";//"kxV52LrczgJBwro8paXd5qpVJajBHp";

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
        request.setSysAction("SendSms");                // 系统规定参数。取值：SendSms（非必填）
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
            System.out.println(respData);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }
}
