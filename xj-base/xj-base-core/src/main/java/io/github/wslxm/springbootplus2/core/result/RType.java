package io.github.wslxm.springbootplus2.core.result;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.google.common.base.Enums;
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

@Getter
@NoArgsConstructor
public enum RType implements IEnum {

    // 系统相关 - 服务器内置状态码
    SYS_SUCCESS(200, "成功"),
    SYS_SUCCESS_DELETE(200, "删除成功"),
    SYS_SUCCESS_FIND(200, "查询成功"),
    SYS_SUCCESS_INSERT(200, "添加成功"),
    SYS_SUCCESS_UPDATE(200, "编辑成功"),
    SYS_ERROR_CODE_403(403, "403-无权限访问"),
    SYS_ERROR_CODE_404(404, "403-无权限访问"),
    SYS_ERROR_CODE_500(500, "服务器错误"),

    // 参数错误 [9901-9999]
    PARAM_MISSING(9998, "缺少请求参数"),
    PARAM_ERROR(9999, "请求参数错误"),
    PARAM_ANALYSIS_ERROR(9001, "参数解析错误"),

    // 网关层 [10000-10999]
    // 访问验证相关 [10000-10009]
    // 权限认证,注意，前端需拦截所有为10000的返回重定向到登录页
    AUTHORITY_LOGIN_EXPIRED(10000, "登录过期"),
    AUTHORITY_NO_TOKEN(10001, "没有TOKEN"),
    AUTHORITY_JWT_PARSING_ERROR(10002, "JWT解析失败,请求TOKEN错误"),
    AUTHORITY_JWT_SIGN_ERROR(10003, "JWT签名与本地计算签名不匹配"),
    AUTHORITY_DISABLE(10004, "账号已被禁用,禁止访问"),
    AUTHORITY_NO_PERMISSION(10005, "该账号没有当前操作权限"),
    AUTHORITY_BLACK_LIST_IP(10006, "该ip被列入黑名单,禁止访问"),
    AUTHORITY_WHITE_LIST_NO_IP(10007, "服务器开启了ip验证,请先配置请求ip为白名单在进行访问"),
    SYS_CURRENT_LIMIT(10008, "当前服务人数过多,请稍后重试!"),
    // 验签[10010-10019]
    PARAM_IS_NO_SIGN(10010, "验签失败: 缺少sign或timestamp参数"),
    PARAM_SIGN_ERROR(10011, "验签失败: 请求参数异常或格式错误"),
    PARAM_TIME_OUT(10012, "验签失败: 请求超时"),
    // sql [10020-10029]
    DB_EXECUTE_SQL_ERROR(10020, "DB-执行sql时出错"),
    PARAM_SAVE_TO_DB_MISSING(10021, " DB-缺少参数"),
    PARAM_SAVE_TO_DB_ID_REPEAT(10022, "DB-主键ID重复"),

    // 内部业务层 [8000-9000]
    // gc 模块[8001-8099]
    GENERATE_CODE_JDBC_ERROR(8001, "数据源连接信息错误"),
    // admin 模块[8101-8199]
    // 登录注册及用户相关
    LOGIN_ERROR_USER_PASSWORD(8101, "用户名或密码错误"),
    LOGIN_IS_NO_ACCOUNT(8102, "用户/账号不存在"),
    LOGIN_IS_NO_DISABLE(8103, "账户被禁用"),
    PARAM_DECRYPTION_ERROR(8104, "参数解密错误"),
    SYS_IS_IDEMPOTENT(8105, "重复请求"),
    USER_ACCOUNT_IS_DUPLICATE(8106, "该账号已被注册"),
    USER_IS_DOES_NOT_EXIST(8107, "用户信息不存在"),
    USER_NO_MENU(8108, "当前用户没有菜单"),
    USER_PASSWORD_ERROR(8109, "原密码错误"),
    USER_PHONE_IS_DUPLICATE(8110, "该手机号已绑定其他账号"),
    //字典
    DICT_DUPLICATE(8021, "字典Code重复"),
    ;
    private Integer value;
    private String msg;

    RType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
