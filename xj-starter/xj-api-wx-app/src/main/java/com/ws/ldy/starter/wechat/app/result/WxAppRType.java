package com.ws.ldy.starter.wechat.app.result;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 11:16
 * @return
 */

@SuppressWarnings("all")
@Getter
@NoArgsConstructor
public enum WxAppRType {

    // wx-pay 微信sdk
    WX_PAY_NO_OPENID(10050, "没有openId"),
    WX_APP_ERROR(10051, "调用sdk错误"),
    ;

    private Integer value;
    private String msg;

    WxAppRType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
