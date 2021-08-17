package com.ws.ldy.core.result;

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
public enum RType {

    //--------------------------系统相关错误- SYS_ --------------------------
    SYS_SUCCESS(200, "成功"),
    SYS_SUCCESS_FIND(200, "查询成功"),
    SYS_SUCCESS_INSERT(200, "添加成功"),
    SYS_SUCCESS_UPDATE(200, "编辑成功"),
    SYS_SUCCESS_DELETE(200, "删除成功"),
    //
    SYS_ERROR_CODE_400(400, "400错误"),
    SYS_ERROR_CODE_401(401, "401-禁止非法访问"),
    SYS_ERROR_CODE_403(403, "403-无权限访问"),
    SYS_ERROR_CODE_404(404, "404-找不到该请求"),
    SYS_ERROR_CODE_415(415, "415错误"),
    SYS_ERROR_CODE_500(500, "服务器错误"),
    // 系统层相关错误 9900 -- 10000
    SYS_IS_IDEMPOTENT(9901, "重复请求"),
    SYS_BLACK_LIST_IP(9902, "该ip异常,禁止访问"),
    SYS_WHITE_LIST_NO_IP(9903, "该ip地址没有访问权限"),
    DB_EXECUTE_SQL_ERROR(9905, "执行sql时出错"),

    // 参数错误 9999
    PARAM_ERROR(9999, "参数错误"),
    PARAM_ERROR_JSR303(9999, "参数错误:不符合JSR 303规范"),     // jsr303错误,全局异常单独处理返回msg
    PARAM_MISSING(9999, "缺少参数"),
    PARAM_SIGN_ERROR(9999, "验签: 参数验证失败"),
    PARAM_IS_NO_SIGN(9999, "验签: 没有 sign 或 timestamp 参数"),
    PARAM_TIME_OUT(9999, "验签: 请求超时"),                    // 请求超时,可能请求被拦截篡改参数后再请求
    PARAM_DECRYPTION_ERROR(9999, "参数解密错误"),
    PARAM_TYPE_DOES_NOT_MATCH(9999, " 参数类型不匹配"),
    PARAM_ID_REQUIRED_FALSE(9999, "添加不能传递ID"),
    PARAM_ID_REQUIRED_TRUE(9999, "没有主键ID"),
    PARAM_SAVE_TO_DB_MISSING(9999, " 参数不存在"),//数据保存到数据库时参数错误
    PARAM_SAVE_TO_DB_ID_REPEAT(9999, "主键ID重复"),//数据保存到数据库时参数错误

    // 注意，没有TOKEN可定义为10000，但登录页的接口要全部放行,不然可能会出现无尽至的重定向到登录页
    AUTHORITY_LOGIN_EXPIRED(10000, "登录过期"),
    AUTHORITY_NO_TOKEN(10002, "没有TOKEN"),
    AUTHORITY_DISABLE(10002, "禁止访问"),
    AUTHORITY_JWT_SIGN_ERROR(10002, "JWT签名与本地计算签名不匹配"),
    AUTHORITY_JWT_PARSING_ERROR(10002, "JWT解析错误"),
    AUTHORITY_NO_PERMISSION(10002, "没有权限"),

    // 登录注册
    LOGIN_ERROR_USER_PASSWORD(10001, "用户名或密码错误"),
    LOGIN_IS_NO_ACCOUNT(10001, "用户/账号不存在"),
    LOGIN_IS_NO_DISABLE(10001, "账户被禁用"),
    // 用户
    USER_PASSWORD_ERROR(10003, "原密码错误"),
    USER_NO_MENU(10003, "当前用户没有菜单"),
    USER_IS_DOES_NOT_EXIST(10003, "用户信息不存在"),
    USER_ACCOUNT_IS_DUPLICATE(10003, "该账号已被注册"),
    USER_PHONE_IS_DUPLICATE(10003, "该手机号已绑定其他账号"),
    // admin -字典
    DICT_DUPLICATE(10010, "字典Code重复"),
    // gc-代码生成
    GENERATE_CODE_JDBC_ERROR(10060, "数据源连接信息错误"),
    ;

    private Integer value;
    private String msg;

    RType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
