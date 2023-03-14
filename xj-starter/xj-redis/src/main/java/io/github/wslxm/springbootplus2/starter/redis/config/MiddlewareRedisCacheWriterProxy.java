package io.github.wslxm.springbootplus2.starter.redis.config;


import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * @Description
 * @Author Mahz
 * @Date2021/8/18 14:16
 **/
public class MiddlewareRedisCacheWriterProxy implements RedisCacheWriter {

    RedisCacheWriter redisCacheWriter;

    RedisKeySerializer redisKeySerializer;

    public MiddlewareRedisCacheWriterProxy(RedisCacheWriter redisCacheWriter, RedisKeySerializer redisKeySerializer) {
        this.redisCacheWriter = redisCacheWriter;
        this.redisKeySerializer = redisKeySerializer;
    }

    @Override
    public void put(String name, byte[] key, byte[] value, Duration ttl) {
        redisCacheWriter.put(name, key, value, ttl);
    }

    @Override
    public byte[] get(String name, byte[] key) {
        return redisCacheWriter.get(name, key);
    }

    @Override
    public byte[] putIfAbsent(String name, byte[] key, byte[] value, Duration ttl) {
        return redisCacheWriter.putIfAbsent(name, key, value, ttl);
    }

    @Override
    public void remove(String name, byte[] key) {
        redisCacheWriter.remove(name, key);
    }

    @Override
    public void clean(String name, byte[] pattern) {
        pattern = redisKeySerializer.serialize(new String(pattern));
        redisCacheWriter.clean(name, pattern);
    }
}
