package io.github.wslxm.springbootplus2.common.cache;

/***
 * 全局配置 key
 *
 * <P>
 *     对应全局配置中的 code 值,用于在缓存中快速获取指定配置
 * </P>
 *
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/9/25 0025 17:22
 * @version 1.0.1
 */
public class ConfigCacheKey {
    /**
     * 是否开启接口验签
     */
    public static final String IS_SIGN = "is_sign";
    /**
     * 是否开启/关闭swagger文档
     */
    public static final String IS_SWAGGER = "is_swagger";
    /**
     * 管理端登录有效期|单位分
     */
    public static final String MANAGE_LOGIN_EXPIRATION = "login_expiration_manage";

}
