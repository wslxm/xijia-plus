package io.github.wslxm.springbootplus2.core.result;

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

    SYS_SUCCESS(200, "成功"),
    SYS_SUCCESS_FIND(200, "查询成功"),
    SYS_SUCCESS_INSERT(200, "添加成功"),
    SYS_SUCCESS_UPDATE(200, "编辑成功"),
    SYS_SUCCESS_DELETE(200, "删除成功"),
    SYS_ERROR_CODE_403(403, "403-无权限访问"),
    SYS_ERROR_CODE_500(500, "服务器错误"),
    SYS_IS_IDEMPOTENT(9901, "重复请求"),
    // 参数错误 9999
    PARAM_ERROR(9999, "参数错误"),
    PARAM_MISSING(9999, "缺少参数"),
    // 参数保存数据库错误
    PARAM_SAVE_TO_DB_MISSING(9998, " 参数不存在"),//数据保存到数据库时参数错误
    PARAM_SAVE_TO_DB_ID_REPEAT(9998, "主键ID重复"),//数据保存到数据库时参数错误
    DB_EXECUTE_SQL_ERROR(9998, "执行sql时出错"),
    // 参数验签错误
    PARAM_SIGN_ERROR(9997, "验签失败: 请求参数异常或格式错误"),
    PARAM_IS_NO_SIGN(9997, "验签失败: 缺少sign或timestamp参数"),
    PARAM_TIME_OUT(9997, "验签失败: 请求超时"),  // 请求超时,可能请求被拦截篡改参数后再请求
    PARAM_DECRYPTION_ERROR(9997, "参数解密错误"),
    // 权限认证,注意，前端需拦截所有为10000的返回重定向到登录页
    AUTHORITY_LOGIN_EXPIRED(10000, "登录过期"),
    AUTHORITY_NO_TOKEN(10001, "没有TOKEN"),
    AUTHORITY_DISABLE(10001, "禁止访问"),
    AUTHORITY_JWT_SIGN_ERROR(10001, "JWT签名与本地计算签名不匹配"),
    AUTHORITY_JWT_PARSING_ERROR(10001, "JWT解析错误"),
    AUTHORITY_NO_PERMISSION(10001, "没有权限"),
    AUTHORITY_BLACK_LIST_IP(10001, "该ip异常,禁止访问"),
    AUTHORITY_WHITE_LIST_NO_IP(10001, "该ip地址没有访问权限"),
    // 登录注册及用户相关
    LOGIN_ERROR_USER_PASSWORD(10002, "用户名或密码错误"),
    LOGIN_IS_NO_ACCOUNT(10002, "用户/账号不存在"),
    LOGIN_IS_NO_DISABLE(10002, "账户被禁用"),
    USER_PASSWORD_ERROR(10002, "原密码错误"),
    USER_NO_MENU(10002, "当前用户没有菜单"),
    USER_IS_DOES_NOT_EXIST(10002, "用户信息不存在"),
    USER_ACCOUNT_IS_DUPLICATE(10002, "该账号已被注册"),
    USER_PHONE_IS_DUPLICATE(10002, "该手机号已绑定其他账号"),
    // admin -字典
    DICT_DUPLICATE(10003, "字典Code重复"),
    // gc-代码生成
    GENERATE_CODE_JDBC_ERROR(10004, "数据源连接信息错误"),
    ;

    private Integer value;
    private String msg;

    RType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
