package com.ws.ldy.modules.third.wechat.mq.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 推送的微信公众信息的每一个 {{}} 值的内容和颜色封装
 * @author ws
 */
@Data
@ToString
public class WeChatTemplateMsg implements Serializable {
    private static final long serialVersionUID = 6635393516043102339L;
    /**
     * 消息
     */
    private String value;
    /**
     * 消息颜色
     */
    private String color;


    public WeChatTemplateMsg(String value) {
        this.value = value;
        this.color = "#173177";
    }

    public WeChatTemplateMsg(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
