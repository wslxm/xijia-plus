//package io.github.wslxm.springbootplus2.core.cache.redis;
//
//
//import io.github.wslxm.springbootplus2.core.utils.PropUtil;
//import io.github.wslxm.springbootplus2.core.utils.bean.SpringContextUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Set;
//
//@SuppressWarnings("all")
//@Slf4j
//public class RedisCache {
//
//
//    //  @Resource
//    //  private RedisTemplate<String, Object> redisTemplate();
//
//
//    /**
//     * 判断 redisTemplate 是否存在
//     * @return true 是 flase 否
//     */
//    public static Boolean isRedis() {
//        if (SpringContextUtil.getBean("redisTemplate") == null) {
//            return false;
//        }
//        // 获取yml 的redis 配置
//        Object redisHost = PropUtil.findByKey("spring.redis.host");
//        if (redisHost == null || "".equals(redisHost.toString())) {
//            return false;
//        }
//        return true;
//    }
//
//
//    //======================== 使用下方方法需先判断 redisTemplate 是否存在,存在才使用 =================================
//
//    /**
//     * 普通缓存获取
//     *
//     * @param key 键
//     * @return 值
//     */
//    public static Object get(String key) {
//        return key == null ? null : ((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).opsForValue().get(key);
//    }
//
//    /**
//     * 普通缓存放入
//     *
//     * @param key   键
//     * @param value 值
//     * @return true成功 false失败
//     */
//    public static boolean set(String key, Object value) {
//        try {
//            ((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).opsForValue().set(key, value);
//            return true;
//        } catch (Exception e) {
//            log.error(e.toString());
//            return false;
//        }
//    }
//
//
//    /**
//     * 删除缓存
//     *
//     * @param key 可以传一个值 或多个
//     */
//    public static void del(String... key) {
//        if (key != null && key.length > 0) {
//            if (key.length == 1) {
//                ((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).delete(key[0]);
//            } else {
//                ((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).delete(CollectionUtils.arrayToList(key));
//            }
//        }
//    }
//
//
//    /**
//     * 删除所有缓存
//     *
//     * @param key 可以传一个值 或多个
//     */
//    public static void delAll() {
//        Set<String> keys = (((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).keys("*"));
//        ((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).delete(keys);
//    }
//
//
//    /**
//     * 判断key是否存在
//     *
//     * @param key 键
//     * @return true 存在 false不存在
//     */
//    public static boolean hasKey(String key) {
//        try {
//            return ((RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate")).hasKey(key);
//        } catch (Exception e) {
//            log.error(e.toString());
//            return false;
//        }
//    }
//
//
//    /**
//     * 普通缓存放入并设置时间
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
//     * @return true成功 false 失败
//     */
////    public boolean set(String key, Object value, long time) {
////        try {
////            if (time > 0) {
////                getRedisTemplate().opsForValue().set(key, value, time, TimeUnit.SECONDS);
////            } else {
////                set(key, value);
////            }
////            return true;
////        } catch (Exception e) {
////            log.error(e.toString());
////            return false;
////        }
////    }
//}
