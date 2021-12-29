package io.github.wslxm.springbootplus2.core.cache;

import io.github.wslxm.springbootplus2.core.cache.jvm.JvmCache;
import io.github.wslxm.springbootplus2.core.cache.redis.RedisCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存对象入口
 * <P>
 *     1、先判断是否启用redis, 如果使用了redis 默认使用redis进行缓存
 *     2、如果没有启用redis，则使用jvm缓存
 * </P>
 * @author wangsong
 * @date 2021/8/2 0002 10:00
 * @return
 * @version 1.0.1
 */
public class CacheUtil {


    /**
     * 获取缓存--对象 + 集合  +  Map
     * @param key
     * @param t
     * @return
     */
    public static <T> T get(String key, Class<T> t) {
        Object obj;
        if (RedisCache.isRedis()) {
            obj = RedisCache.get(key);
        } else {
            obj = JvmCache.get(key);
        }
        return obj == null ? null : (T) obj;

    }

    public static <T> List<T> getList(String key, Class<T> t) {
        Object obj;
        if (RedisCache.isRedis()) {
            obj = RedisCache.get(key);
        } else {
            obj = JvmCache.get(key);
        }
        return obj == null ? new ArrayList<>() : (List<T>) obj;
    }


    public static <T> Map<String, T> getMap(String key, Class<T> t) {
        Object obj;
        if (RedisCache.isRedis()) {
            obj = RedisCache.get(key);
        } else {
            obj = JvmCache.get(key);
        }
        return obj == null ? new HashMap<>(16) : (Map<String, T>) obj;
    }


    /**
     * 判断指定key 是否 存在缓存数据
     */
    public static boolean containsKey(String key) {
        boolean b;
        if (RedisCache.isRedis()) {
            b = RedisCache.hasKey(key);
        } else {
            b = JvmCache.containsKey(key);
        }
        return b;
    }


    /**
     * 添加缓存
     * key
     * value
     */
    public static <T> void set(String key, T t) {
        if (RedisCache.isRedis()) {
            RedisCache.set(key, t);
        } else {
            JvmCache.set(key, t);
        }
    }

    public static <T> void set(String key, List<T> list) {
        if (RedisCache.isRedis()) {
            RedisCache.set(key, list);
        } else {
            JvmCache.set(key, list);
        }
    }

    public static <T> void set(String key, Map<String, T> map) {
        if (RedisCache.isRedis()) {
            RedisCache.set(key, map);
        } else {
            JvmCache.set(key, map);
        }
    }

    /**
     * 删除指定keu 缓存
     * @param key
     */
    public static boolean del(String key) {
        if (RedisCache.isRedis()) {
            RedisCache.del(key);
        } else {
            JvmCache.del(key);
        }
        return true;
    }

    /**
     * 删除所有缓存
     */
    public static boolean delAll() {
        if (RedisCache.isRedis()) {
            RedisCache.delAll();
        } else {
            JvmCache.delAll();
        }
        return true;
    }
}
