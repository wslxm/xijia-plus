package com.ws.ldy.others.wechat.mq.service;

/**
 * 向微信公众号推送消息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/9 0009 13:44
 * @version 1.0.0
 */
public interface WxChatTemplateMsgService {

    /**
     * 向一个用户推送消息（测试）
     * @param openId
     * @param
     */
    void sendTest(String openId, String content);


}
