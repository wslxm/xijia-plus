package com.ws.ldy.others.wechat.wxbase.util;

import com.ws.ldy.others.wechat.templatemsg.entity.WeChatTemplateMsg;
import com.ws.ldy.others.wechat.wxbase.constant.WxChatConstant;
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
@Component
public class TemplateMsgUtil {

    @Autowired
    private WeChetAccessTokenUtil weChetAccessTokenUtil;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送模版消息
     * openId     用户openId
     * templateId 模板Id   测试: "ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY"
     * data       模板参数
     * @param data
     */
    public String send(String openId, String templateId, Map<String, WeChatTemplateMsg> data) {
        String accessToken = weChetAccessTokenUtil.getToken();
        String url = WxChatConstant.Url.SEND_URL.replace("ACCESS_TOKEN", accessToken);
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("touser", openId);               // openId
        sendBody.put("url", "");                      // 点击模板信息跳转地址
        sendBody.put("topcolor", "#FF0000");          // 顶色
        sendBody.put("data", data);                   // 模板参数
        sendBody.put("template_id", templateId);      // 模板Id
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url, sendBody, String.class);
        System.out.println(forEntity.getBody());
        return forEntity.getBody();
    }
}
