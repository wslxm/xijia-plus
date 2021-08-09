package com.ws.ldy.starter.wechat.mp.util;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送模板信息（ 在 WxChatTemplateMsgServiceImpl 拼接对应的模板参数信息）
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 17:29 
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Component
@Slf4j
public class WxMpTemplateMsgTestUtil {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 发送模板消息通知（测试模板）
     * @param openId 微信用户的openId
     * @param content 发送的内容
     */
    public void sendTest(String openId, String content) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId("P6llNez3CAcxEYzhQ_MDdFJGUifXfey15ZIX7MKiB3k")
                .url("")
                .build();
        // 每一个参数
        templateMessage.addData(new WxMpTemplateData("MSG", content, "#173177"));
        try {
            // 发送
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.info("模板信息发送失败：" +e.getMessage());
            log.debug(e.toString());
        }
    }
}
