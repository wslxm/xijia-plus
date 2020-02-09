package com.ws.ldy.admincore.common.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ws.ldy.admincore.common.error.ErrorException;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TODO  异常常量类
 * @author ws
 * @mail  1720696548@qq.com
 * @date  2020/2/9 0009 11:16
 * @return
 */

@Getter
@NoArgsConstructor
public enum  ErrorConstantEnum {


    //--------------------------系统相关错误- SYS_ --------------------------
    SYS_SUCCESS("200", "响应成功"),
    SYS_IS_NO_AUTHORIZATION ("401", "无权限"),
    SYS_IS_NO_VISIT ("403", "拒绝访问"),
    SYS_IS_NO_INTERFACE("404", "URL错误/找不到接口/找不到页面"),
    SYS_ERROR("500", "系统错误"),


    //--------------------------后台管理相关错误-ADMIN_ -----------------------
    ADMIN_IS_NO_LOGIN("10000101", "请登陆, 如前端返回:请查看调用接口是否为api或open开头"),


    //--------------------------前端API相关错误-API_ --------------------------
    API_IS_NO_LOGIN("20000101", "用户未登陆"),

    ;

    private String code;
    private String msg;

    ErrorConstantEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator
    public static ErrorConstantEnum getEnum(String code) {
        for (ErrorConstantEnum enum2 : ErrorConstantEnum.values()) {
            if (enum2.getCode().equals(code)) {
                return enum2;
            }
        }
        throw new ErrorException("500","枚举获取错误");
    }
}
