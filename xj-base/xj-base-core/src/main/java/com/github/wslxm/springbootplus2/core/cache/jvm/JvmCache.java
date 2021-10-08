package com.github.wslxm.springbootplus2.core.cache.jvm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统jvm 缓存
 * <P>
 *    该缓存类主要储存系统常用数据, 不提供 id数据缓存
 *     -- 目前储存了 系统配置config，字典, 权限 等每次请求都会用来做验证的热点数据
 *     -- TODO 计划存入用户jwt 生成的 token 信息, 借此处理 重复登录,先登录的掉线, 强制退出, token 过长等问题
 *     -- TODO 计划加入redis 动态缓存切换, 集群时使用redis缓存， 单项目可直接使用jvm缓存
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/3/15 0015 9:08
 * @version 1.0.0
 */
public class JvmCache {

    /**
     * 缓存对象
     * <P>
     *    原存在缓存对象
     *    //   原  -->   private static List<AdminDictionaryVO> dictList = new ArrayList<>();
     *    //  当前系统的所有权限接口数据（key = uri ),用于登录验证用户接口权限避免去数据库查询接口数据
     *    //   原  -->   private static Map<String, AdminAuthority> authMap = new HashMap<>();
     *    //  当前系统的所有全局配置数据（key = uri ),用于页面快速资源和拦截器中快速获取对应配置
     *    //   原  -->   private static Map<String, XjAdminConfig> configMap = new HashMap<>();
     * </P>
     */
    private static final Map<String, Object> cacheMap = new ConcurrentHashMap<>();


    /**
     * 获取缓存--对象 + 集合  +  Map
     * @param key
     * @return
     */
    public static Object get(String key) {
        return cacheMap.get(key);
    }


    /**
     * 判断指定key 是否 存在缓存数据
     */
    public static boolean containsKey(String key) {
        return cacheMap.containsKey(key);
    }


    /**
     * 添加缓存
     * key
     * value
     */
    public static <T> void set(String key, Object obj) {
        cacheMap.put(key, obj);
    }


    /**
     * 删除指定keu 缓存
     * @param key
     */
    public static boolean del(String key) {
        cacheMap.remove(key);
        return true;
    }

    /**
     * 删除所有缓存
     */
    public static boolean delAll() {
        for (String key : cacheMap.keySet()) {
            cacheMap.remove(key);
        }
        return true;
    }
}
