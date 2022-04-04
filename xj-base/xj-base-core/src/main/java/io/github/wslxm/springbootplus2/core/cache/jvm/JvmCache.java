//package io.github.wslxm.springbootplus2.core.cache.jvm;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 系统jvm 缓存
// * <P>
// *    该缓存类主要储存系统常用数据, 不提供 id数据缓存
// *     -- 目前储存了 系统配置config，字典, 权限 等每次请求都会用来做验证的热点数据
// * </P>
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2021/3/15 0015 9:08
// * @version 1.0.1
// */
//public class JvmCache {
//
//    /**
//     * 缓存对象
//     * <P>
//     *    原存在缓存对象
//     *    //   原  -->   private static List<AdminDictionaryVO> dictList = new ArrayList<>();
//     *    //  当前系统的所有权限接口数据（key = uri ),用于登录验证用户接口权限避免去数据库查询接口数据
//     *    //   原  -->   private static Map<String, AdminAuthority> authMap = new HashMap<>();
//     *    //  当前系统的所有全局配置数据（key = uri ),用于页面快速资源和拦截器中快速获取对应配置
//     *    //   原  -->   private static Map<String, XjAdminConfig> configMap = new HashMap<>();
//     * </P>
//     */
//    private static final Map<String, Object> CACHE_MAP = new ConcurrentHashMap<>();
//
//
//    /**
//     * 获取缓存--对象 + 集合  +  Map
//     * @param key
//     * @return
//     */
//    public static Object get(String key) {
//        return CACHE_MAP.get(key);
//    }
//
//
//    /**
//     * 判断指定key 是否 存在缓存数据
//     */
//    public static boolean containsKey(String key) {
//        return CACHE_MAP.containsKey(key);
//    }
//
//
//    /**
//     * 添加缓存
//     * key
//     * value
//     */
//    public static <T> void set(String key, Object obj) {
//        CACHE_MAP.put(key, obj);
//    }
//
//
//    /**
//     * 删除指定keu 缓存
//     * @param key
//     */
//    public static boolean del(String key) {
//        CACHE_MAP.remove(key);
//        return true;
//    }
//
//    /**
//     * 删除所有缓存
//     */
//    public static boolean delAll() {
//        for (String key : CACHE_MAP.keySet()) {
//            CACHE_MAP.remove(key);
//        }
//        return true;
//    }
//}
