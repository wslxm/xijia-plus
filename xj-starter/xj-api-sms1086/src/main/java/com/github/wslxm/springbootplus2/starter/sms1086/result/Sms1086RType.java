package com.github.wslxm.springbootplus2.starter.sms1086.result;

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
public enum Sms1086RType {

    // ali | 1086 短信
    SMS_INVALID(10030, "验证码无效"),
    SMS_EXPIRED(10030, "验证码已过期"),
    SMS_FAIL(10030, "发送失败"),
    ;

    private Integer value;
    private String msg;

    Sms1086RType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
