package com.ws.ldy.config.idempotent.util;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Token 生成与判断
 * @author wangsong
 * @date: 2019年4月27日 下午10:04:39
 */
public class XJIdempotentUtils {

    /**
     *幂等id有效期
     */
    private final static Integer TOKEN_TIME = 60 * 60;

    /**
     * 缓存对象 ==>  幂等Token=有效期
     */
    @Resource
    private static Map<String, Long> cacheMap = new HashMap<>();

    /**
     *  将token存入在 jvm || Memcached || redis
     * @return
     */
    public static String saveApiIdempotent() {
        //token = token+时间戳
        String idempotentToken = "token" + System.currentTimeMillis();
        cacheMap.put(idempotentToken, System.currentTimeMillis() + TOKEN_TIME);
        return idempotentToken;
    }

    /**
     *  判断token是否有效 / 是否为重复调用
     * @param tokenKey
     * @return Ext
     */
    public static boolean verificationApiIdempotent(String tokenKey) {
        Long expiredTime = cacheMap.get(tokenKey);
        //判断token是否为空
        if (expiredTime == null) {
            return false;
        }
        // token 获取成功后 删除缓存
        cacheMap.remove(tokenKey);
        // 判断是否过期
        if ((expiredTime - System.currentTimeMillis()) > 0) {
            return false;
        }
        // 获取成功未过期
        return true;
    }


    // TODO 清理cacheMap中未使用的过期幂等token
}