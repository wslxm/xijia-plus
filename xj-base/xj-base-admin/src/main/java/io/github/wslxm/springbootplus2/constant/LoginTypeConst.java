package io.github.wslxm.springbootplus2.constant;


/**
 * 登录类型常量
 *
 * @author wangsong
 * @date 2023/04/06
 */
public interface LoginTypeConst {
    /**
     * 账号+密码
     */
    String ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
    /**
     * 手机号+密码
     */
    String PHONE_PASSWORD = "PHONE_PASSWORD";
    /**
     * 账号or手机号 + 密码
     */
    String ACCOUNT_OR_PHONE_PASSWORD = "ACCOUNT_OR_PHONE_PASSWORD";
    /**
     * 手机号+验证码 (自行实现)
     */
    String PHONE_CODE = "PHONE_CODE";
}
