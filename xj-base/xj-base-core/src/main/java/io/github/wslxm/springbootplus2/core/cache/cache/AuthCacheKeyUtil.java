package io.github.wslxm.springbootplus2.core.cache.cache;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.wslxm.springbootplus2.core.utils.paramVerification.StringUtil;

/***
 * 权限 key 以 缓存key 处理/获取方法
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/8/25 0025 10:51
 * @version 1.0.0
 */
public class AuthCacheKeyUtil {


    /**
     * 获取缓存key（缓存数据库数据）
     * <P>
     *     项目启动或更新url权限时, 需要把url 当key进行缓存,用于权限认证, 由于path参数是动态参数, 把所有 path 的参数转为 $ 符号在进行缓存
     * </P>
     * @author wangsong
     * @param method
     * @param url
     * @date 2021/8/25 0025 11:02
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String getCacheKey(String method, String url) {
        String[] urlArr = url.split("/");
        StringBuffer urlStr = new StringBuffer();
        for (int i = 0; i < urlArr.length; i++) {
            String urlParam = urlArr[i];
            if (StringUtils.isNotBlank(urlParam)) {
                urlStr.append("{".equals(urlParam.substring(0, 1)) ? "/$" : "/" + urlParam);
            }
        }
        return method + ":" + urlStr.toString();
    }


    /**
     * 获取缓存key（请求请求时）
     * <P>
     *    接口请求时,获取权限url,  把 path 的数字参数转为 $ 号
     * </P>
     * @author wangsong
     * @param method
     * @param url
     * @date 2021/8/25 0025 11:01
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String getAuthCacheKey(String method, String url) {
        String[] urlArr = url.split("/");
        StringBuffer urlStr = new StringBuffer();
        for (int i = 0; i < urlArr.length; i++) {
            String urlParam = urlArr[i];
            if (StringUtils.isNotBlank(urlParam)) {
                urlStr.append(StringUtil.isInteger(urlParam) ? "/$" : "/" + urlParam);
            }
        }
        return method + ":" + urlStr.toString();
    }


    /**
     * 验证接口 url 唯一标识
     * <P>
     *   用于定义接口时的唯一性【同请求方式和接口名不能重复】,在项目启动时进行权限url扫描时会进行获取验证
     * </P>
     *
     * @param method 请求方式
     * @param url 请求url
     * @return
     */
    public static String getAuthKey(String method, String url) {
        return method + ":" + url;
    }

}
