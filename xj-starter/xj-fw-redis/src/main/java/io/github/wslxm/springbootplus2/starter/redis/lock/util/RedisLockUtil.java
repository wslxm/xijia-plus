package io.github.wslxm.springbootplus2.starter.redis.lock.util;

import io.github.wslxm.springbootplus2.starter.redis.error.RedisErrorException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;


/**
 *  @author wangsong
 */
@Component
public class RedisLockUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 判断redis 是否启用
     * @return true 是 flase 否
     */
    public static Boolean isRedis() {
        // 获取yml 的redis 配置
        Object redisHost = RedisPropUtil.findByKey("spring.redis.host");
        if (redisHost == null || "".equals(redisHost)) {
            return false;
        }
        return true;
    }


    /**
     * 获取一个redis分布锁, 100% 只有一个线程能获取到锁
     * <P>
     *     connection.setNX   :  设置一个永久对象,value值为锁的过期时间
     *     oldTime < nowTime  :  获取原锁时间判断原锁是否已过期
     *     connection.getSet  ： 新线程获得锁并设置过期时间 且 再次获取原锁的过期时间
     *     oldValue==null ... :  判断key 的原锁是否存在 和在此 原锁是否已过期 （可能同时有多个线程进入,需要再次判断）
     * </P>
     *
     * @param lockKey        锁住的key
     * @param lockExpireMils 锁住的时长(毫秒)。如果超时未解锁，视为加锁线程死亡，其他线程可夺取锁
     * @return
     */
    @SuppressWarnings("all")
    public boolean lock(String lockKey, long lockExpireMils) {
        if (!isRedis()) {
            throw new RedisErrorException(10009, "获取分布式锁[" + lockKey + "]失败");
        }
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long nowTime = System.currentTimeMillis();
            // 设置一个永久对象,value值为锁的过期时间
            Boolean acquire = connection.setNX(lockKey.getBytes(), String.valueOf(nowTime + lockExpireMils + 1).getBytes());
            if (acquire) {
                // 线程没有被锁,创建锁成功
                return Boolean.TRUE;
            } else {
                // 线程已被锁住,获取原来的锁
                byte[] value = connection.get(lockKey.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    // 获取原锁时间判断 判断 原锁是否已过期
                    long oldTime = Long.parseLong(new String(value));
                    if (oldTime < nowTime) {
                        // 新线程获得锁并设置过期时间并再次返回原锁的过期时间
                        byte[] oldValue = connection.getSet(lockKey.getBytes(), String.valueOf(nowTime + lockExpireMils + 1).getBytes());
                        // 判断key 的原锁是否存在 或在执行中 原锁是否已过期 (可能同时有多个线程进入,需要再次判断)
                        return oldValue == null ? false : Long.parseLong(new String(oldValue)) < nowTime;
                    }
                }
            }
            return Boolean.FALSE;
        });
    }


    /**
     * 释放锁 其实就是将该key删除
     *
     * @return
     */
    @SuppressWarnings("all")
    public Boolean unLock(String lockKey) {
        return redisTemplate.delete(lockKey);
    }
}
