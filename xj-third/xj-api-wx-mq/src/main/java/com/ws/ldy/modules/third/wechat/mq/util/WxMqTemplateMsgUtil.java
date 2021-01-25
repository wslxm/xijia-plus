package com.ws.ldy.modules.third.wechat.mq.util;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.modules.third.wechat.mq.config.WxMqUrl;
import com.ws.ldy.modules.third.wechat.mq.entity.WeChatTemplateMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送模板信息（ 在 WxChatTemplateMsgServiceImpl 拼接对应的模板参数信息）
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 17:29 
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Component
public class WxMqTemplateMsgUtil {

    @Autowired
    private WxMqAccessTokenUtil wxMqAccessTokenUtil;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送模版消息
     * openId     用户openId
     * templateId 模板Id   测试: "ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY"
     * data       模板参数
     * @param data
     */
    public R<String> sendMsg(String openId, String templateId, Map<String, WeChatTemplateMsg> data) {
        R<String> accessTokenData = wxMqAccessTokenUtil.getToken();
        if(!accessTokenData.getCode().equals(RType.SYS_SUCCESS.getValue())){
            return R.error(accessTokenData.getCode(),accessTokenData.getMsg());
        }
       String accessToken = accessTokenData.getData();
        String url = WxMqUrl.TemplateUrl.SEND_URL.replace("ACCESS_TOKEN", accessToken);
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("touser", openId);               // openId
        sendBody.put("url", "");                      // 点击模板信息跳转地址
        sendBody.put("topcolor", "#FF0000");          // 顶色
        sendBody.put("data", data);                   // 模板参数
        sendBody.put("template_id", templateId);      // 模板Id
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url, sendBody, String.class);
        System.out.println(forEntity.getBody());
        return R.success(forEntity.getBody());
    }


    /**
     * 发送模板消息通知（测试模板）
     * @param openId 微信用户的openId
     * @param content 发送的内容
     */
    public void sendTest(String openId, String content) {
        // 模板Id
        String templateId = "P6llNez3CAcxEYzhQ_MDdFJGUifXfey15ZIX7MKiB3k";
        // 模板参数,每一个put 对应一个模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        sendMag.put("MSG", new WeChatTemplateMsg(content));
        //sendMag.put("MSG", new WeChatTemplateMsg(content,"#173177"));
        // 发送
        this.sendMsg(openId, templateId, sendMag);
    }
}
