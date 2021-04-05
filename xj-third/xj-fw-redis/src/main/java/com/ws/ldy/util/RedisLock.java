package com.ws.ldy.util;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;


@Component
public class RedisLock {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long nowTime = System.currentTimeMillis();
            Boolean acquire = connection.setNX(lockKey.getBytes(), String.valueOf(nowTime + lockExpireMils + 1).getBytes());
            if (acquire) {
                return Boolean.TRUE;
            } else {
                byte[] value = connection.get(lockKey.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long oldTime = Long.parseLong(new String(value));
                    if (oldTime < nowTime) {
                        byte[] oldValue = connection.getSet(lockKey.getBytes(), String.valueOf(nowTime + lockExpireMils + 1).getBytes());
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
