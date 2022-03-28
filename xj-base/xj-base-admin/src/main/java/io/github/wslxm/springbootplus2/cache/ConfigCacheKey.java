package io.github.wslxm.springbootplus2.cache;

/***
 * 全局配置key
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/9/25 0025 17:22
 * @version 1.0.1
 */
@SuppressWarnings("all")
public class ConfigCacheKey {

    public static final String IS_AUTH = "is_auth";  // 是否开启接口权限控制
    public static final String IS_SIGN = "is_sign";  // 是否开启接口验签
    public static final String IS_SWAGGER = "is_swagger";  // 是否开启/关闭swagger文档
    public static final String MANAGE_LOGIN_EXPIRATION = "login_expiration_manage";  // 管理端登录有效期|单位分

}
