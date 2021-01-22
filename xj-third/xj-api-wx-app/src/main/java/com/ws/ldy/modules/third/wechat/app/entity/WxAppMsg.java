package com.ws.ldy.modules.third.wechat.app.entity;


import lombok.Data;
import lombok.ToString;

/**
 * 微信小程序订阅消息每个参数的 kay-value封装对象
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
