package com.ws.ldy.modules.third.sms1086.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 短信验证码缓存数据
 */
@Data
@ToString
@AllArgsConstructor
public class SmsCode {

    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间(时间戳)
     */
    private Long time;
}
