package com.ws.ldy.modules.third.wechat.app.entity;


import lombok.Data;
import lombok.ToString;

/**
 * 推送的微信公众信息的每一个 {{}} 值的内容和颜色封装
 */
@Data
@ToString
public class WxAppMsg {
    /**
     * 消息
     */
    private String value;


    public WxAppMsg(String value) {
        this.value = value;
    }
}
