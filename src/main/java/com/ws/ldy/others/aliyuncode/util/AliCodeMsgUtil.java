package com.ws.ldy.others.aliyuncode.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 阿里云短信工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/1 0001 11:39
 * @version 1.0.0
 */
@Slf4j
public class AliCodeMsgUtil {


    public static final String product = "Dysmsapi";
    public static final String domain = "dysmsapi.aliyuncs.com";
    public static final String accessKeyId = "LTAI4G3RgFKWyJywrqkpmkUT";//"LTAIcJF8Ho2NfwCW";
    public static final String accessKeySecret = "T3kCBpqWhIQcZ7idULg6XGn9Xyx9Nj";//"kxV52LrczgJBwro8paXd5qpVJajBHp";


    /**
     * 发送短信
     * @author wangsong
     * @param phones
     * @param parms
     * @date 2020/8/1 0001 11:51
     * @return boolean
     * @version 1.0.0
     */
    public static boolean sendMsg(String phones, Map<String, String> parms) {
        DefaultProfile profile = DefaultProfile.getProfile(product, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");  // 请求接口地址（必填）
        request.setSysVersion("2017-05-25");            //
        request.setSysAction("SendSms");                // 系统规定参数。取值：SendSms（非必填）
        request.putQueryParameter("RegionId", "cn-hangzhou");     // 区域（非必填）
        request.putQueryParameter("PhoneNumbers", "17628689968"); //  国内短信：11位手机号码,多个逗号分隔
        request.putQueryParameter("SignName", "1");     // 短信签名名称。控制台签名管理页面签名名称一列查看。
        request.putQueryParameter("TemplateCode", "1");  //短信模板ID，请在控制台模板管理页面模板CODE一列查看。
        // TemplateParam	String	否	{"code":"1111"}
        // 短信模板变量对应的实际值，JSON格式。
        request.putQueryParameter("TemplateParam", "1");
        // SmsUpExtendCode	String	否	90999
        // 上行短信扩展码，无特殊需要此字段的用户请忽略此字段。
        request.putQueryParameter("SmsUpExtendCode", "1");
        // OutId	String	否	abcdefgh
        // 外部流水扩展字段。
        request.putQueryParameter("OutId", "1");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) {
        // AliyunSmsEnum.CONFIRM_ORDER.send("17628689969", "2020-5-5 00:00:00");
        System.out.println("发送完成");
    }
}



//
//        说明 必须是已添加、并通过审核的短信签名；且发送国际/港澳台消息时，请使用国际/港澳台短信模版。
//        AccessKeyId	String	否	LTAIP00vvvvvvvvv
//        主账号AccessKey的ID。
//
//        Action	String	否	SendSms
//        系统规定参数。取值：SendSms。


//


//
//        说明 如果JSON中需要带换行符，请参照标准的JSON协议处理。
//        返回数据
//        名称	类型	示例值	描述
//        BizId	String	900619746936498440^0
//        发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
//
//        Code	String	OK
//        请求状态码。
//
//        返回OK代表请求成功。
//        其他错误码详见错误码列表。
//        Message	String	OK
//        状态码的描述。
//
//        RequestId	String	F655A8D5-B967-440B-8683-DAD6FF8DE990
//        请求ID。