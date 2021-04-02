package com.ws.ldy.common.result;

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
    SYS_ERROR_DOES_NOT_EXIST(9900, "没有找到数据/数据不存在"),
    SYS_IS_IDEMPOTENT(9901, "重复请求"),
    SYS_BLACK_LIST_IP(9902, "该ip异常,禁止访问"),
    SYS_WHITE_LIST_NO_IP(9903, "该ip地址没有访问权限"),
    TEMPLATE_RESOLVING_ERROR(9904, "Html模板解析错误"),
    DB_EXECUTE_SQL_ERROR(9905, "执行sql时出错"),
    SYS_CONFIG_NO_LESS_THAN_ONE(9906, "配置数据不能少于一条"),


    // 参数错误 9999
    PARAM_ERROR(9999, "参数错误"),
    PARAM_SIGN_ERROR(9999, "验签: 参数验证失败"),
    PARAM_IS_NO_SIGN(9999, "验签: 没有 sign 或 timestamp 参数"),
    PARAM_TIME_OUT(9999, "验签: 请求超时"),                    // 请求超时,可能请求被拦截篡改参数后再请求
    PARAM_ERROR_JSR303(9999, "参数错误:不符合JSR 303规范"),     // jsr303错误,全局异常单独处理返回msg
    PARAM_MISSING(9999, "缺少参数"),
    PARAM_TYPE_DOES_NOT_MATCH(9999, " 参数类型不匹配"),
    PARAM_ID_REQUIRED_FALSE(9999, "添加不能传递ID"),
    PARAM_ID_REQUIRED_TRUE(9999, "没有主键ID"),
    PARAM_SAVE_TO_DB_MISSING(9999, " 参数不存在"),//数据保存到数据库时参数错误
    PARAM_SAVE_TO_DB_ID_REPEAT(9999, "主键ID重复"),//数据保存到数据库时参数错误

    // 登录
    LOGIN_ERROR_USER_PASSWORD(10001, "用户名或密码错误"),
    LOGIN_IS_NO_ACCOUNT(10001, "用户/账号不存在"),
    LOGIN_IS_NO_DISABLE(10001, "账户被禁用"),
    LOGIN_FAILED(10001, "登录失败"),
    LOGIN_THERE_ARE_MULTIPLE(10001, "存在多个账号信息,请选择登录"),

    // 接口/授权
    AUTHORITY_LOGIN_EXPIRED(10000, "登录过期"),
    AUTHORITY_DISABLE(10002, "该接口已被禁止访问"),
    AUTHORITY_JWT_SIGN_ERROR(10002, "JWT签名与本地计算签名不匹配"),
    AUTHORITY_JWT_PARSING_ERROR(10002, "JWT解析错误"),
    AUTHORITY_NO_PERMISSION(10002, "没有权限"),
    AUTHORITY_NO_TOKEN(10002, "没有TOKEN"),

    // 用户
    USER_PASSWORD_ERROR(10003, "原密码错误"),
    USER_NO_ROLE(10003, "当前用户没有角色"),
    USER_NO_MENU(10003, "当前用户没有菜单"),
    USER_IS_DOES_NOT_EXIST(10003, "用户信息不存在"),
    USER_ACCOUNT_IS_DUPLICATE(10003, "该账号已被注册"),
    USER_PHONE_IS_DUPLICATE(10003, "该手机号已绑定其他账号"),
    USER_SMS_CODE_ERROR(10003, "验证码错误"),
    USER_SMS_CODE_EXPIRED(10003, "验证码已过期"),
    USER_ACCOUNT_IS_NO_PHONE(10003, "账号不能为电话号码"),
    USER_IS_NO_PHONE(10003, "请输入正确的手机号"),
    USER_EXIST_MULTIPLE_ACCOUNTS(10003, "存在多个账号,请选择账号类型"),


    // 字典
    DICT_DUPLICATE(10010, "字典Code重复"),

    // 文件
    FILE_PATH_ERROR(10020, "路径错误或文件不存在"),
    FILE_DOWNLOAD_FAILED(10020, "文件下载失败"),
    FILE_UPLOAD_FAILED(10020, "文件上传失败"),

    // 短信
    SMS_INVALID(10030, "验证码无效"),
    SMS_EXPIRED(10030, "验证码已过期"),
    SMS_FAIL(10030, "发送失败"),

    // Websocket
    WEBSOCKET_CONFIG_ERROR(10040, "请检查webSocket配置信息是否配置"),

    // 微信sdk
    WX_GET_ACCESS_TOKEN_ERROR(10050, "获取微信公众号ACCESS_TOKEN失败"),
    WX_PAY_NO_OPENID(10050, "没有openId"),
    WX_PAY_FAILURE(10050, "交易失败"),
    WX_PAY_REPEAT(10051, "重复回调"),
    WX_APP_ERROR(10051, "调用sdk错误"),

    // 代码生成
    GENERATE_CODE_JDBC_ERROR(10060, "数据源连接信息错误"),

    // 订单
    ORDER_DOES_NOT_EXIST(10070, "订单不存在"),
    ORDER_BOUND(10070, "订单已绑定"),
    ORDER_FAIL(10070, "订单支付未成功"),

    // 宠物相关
    PETS_NO_DEFAULT_ERROR(10080, "设置默认宠物失败"),

    // 月费相关
    MONTH_FEE_NOT_FOUND(10090, "找不到支付费用信息"),

    // 宠物
    PET_TEMPORARY_ERROR(10100, "临时宠物数据条数宠物"),
    PET_T_BOUND(10100, "临时宠物订单不存或已绑定"),

    // 邀请
    INVITE_REPEAT(10110, "重复邀请"),
    INVITE_IS_NO_DEFAULT_PET(10110, "邀请人没有设置默认宠物"),
    INVITE_IS_NO_CONFIG(10110, "没有配置赠送时长"),

    // 申报
    DECLARE_AMOUNT_ERROR(10120, "实发金额不能大于申报金额"),
    DECLARE_STATE_ERROR(10120, "状态错误：订单已完成"),

    ;


    private Integer value;
    private String msg;

    RType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
