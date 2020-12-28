package com.ws.ldy.others.wechat.mq.service.impl;

import com.ws.ldy.others.wechat.mq.entity.WeChatTemplateMsg;
import com.ws.ldy.others.wechat.mq.service.WxChatTemplateMsgService;
import com.ws.ldy.others.wechat.mq.util.TemplateMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众消息模板 --> 数据处理后向微信公众号推送消息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/9 0009 13:44
 * @version 1.0.0
 */
@Service
public class WxChatTemplateMsgServiceImpl implements WxChatTemplateMsgService {


    @Autowired
    private TemplateMsgUtil templateMsgUtil;


    /**
     * 发送模板消息通知（测试模板）
     * @param openId 微信用户的openId
     * @param content 发送的内容
     */
    @Override
    public void sendTest(String openId, String content) {
        // 模板Id
        String templateId = "P6llNez3CAcxEYzhQ_MDdFJGUifXfey15ZIX7MKiB3k";
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        sendMag.put("MSG", new WeChatTemplateMsg(content));
        //sendMag.put("MSG", new WeChatTemplateMsg(content,"#173177"));
        // 发送
        templateMsgUtil.send(openId, templateId, sendMag);
    }
}
