package com.ws.ldy.common.result;

import com.baomidou.mybatisplus.core.enums.IEnum;
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
public enum RType implements IEnum<Integer> {

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


    // 参数错误
    PARAM_ERROR(9999, "参数错误"),
    PARAM_ERROR_JSR303(9999, "参数错误:不符合JSR 303规范"),//jsr303错误,全局异常单独处理返回msg
    PARAM_MISSING(9999, "缺少参数"),
    PARAM_TYPE_DOES_NOT_MATCH(9999, "参数类型不匹配"),
    PARAM_ID_REQUIRED_FALSE(9999, "添加不能传递Id"),
    PARAM_ID_REQUIRED_TRUE(9999, "编辑必须传递Id"),

    // 登录
    LOGIN_ERROR_USER_PASSWORD(10001, "用户名或密码错误"),
    LOGIN_IS_NO_ACCOUNT(10001, "用户/账号不存在"),
    LOGIN_IS_NO_DISABLE(10001, "账户被禁用"),
    LOGIN_FAILED(10001, "登录失败"),


    // 接口/授权
    AUTHORITY_LOGIN_EXPIRED(10000, "登录过期"),
    AUTHORITY_DISABLE(10002, "该接口已被禁止访问"),
    AUTHORITY_JWT_SIGN_ERROR(10002, "JWT签名与本地计算签名不匹配"),
    AUTHORITY_JWT_PARSING_ERROR(10002, "JWT解析错误"),
    AUTHORITY_NO_PERMISSION(10002, "没有权限"),

    // 用户
    USER_PASSWORD_ERROR(10003, "原密码错误"),
    USER_NO_ROLE(10003, "当前用户没有角色"),
    USER_NO_MENU(10003, "当前用户没有菜单"),

    // 字典
    DICT_DUPLICATE(10004, "字典重复"),

    // 文件
    FILE_PATH_ERROR(10005, "路径错误或文件不存在"),
    FILE_DOWNLOAD_FAILED(10005, "文件下载失败"),
    FILE_UPLOAD_FAILED(10005, "文件上传失败"),

    // websocket
    WEBSOCKET_CONFIG_ERROR(10006, "请检查webSocket配置信息是否配置"),
    ;


    private Integer value;
    private String msg;

    RType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
