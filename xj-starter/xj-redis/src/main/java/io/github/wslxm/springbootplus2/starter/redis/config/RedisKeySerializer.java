package io.github.wslxm.springbootplus2.starter.redis.config;


import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * 为redis key 统一加上前缀
 */
public class RedisKeySerializer implements RedisSerializer<String> {

    /**:
     * 编码格式
     */
    private final Charset charset;

    /**
     * 前缀
     */
    private String prefixKey;

    public RedisKeySerializer(String prefixKey) {
        this(Charset.forName("UTF8"), prefixKey);
    }

    public RedisKeySerializer(Charset charset, String prefixKey) {
        this.prefixKey = prefixKey + ":";
        this.charset = charset;
    }

    /**
     * Serialize the given object to binary data.
     *
     * @param s object to serialize. Can be {@literal null}.
     * @return the equivalent binary data. Can be {@literal null}.
     */
    @Override
    public byte[] serialize(String cacheKey) throws SerializationException {
        String key = prefixKey + cacheKey;
        return key.getBytes(charset);
    }

    /**
     * Deserialize an object from the given binary data.
     *
     * @param bytes object binary representation. Can be {@literal null}.
     * @return the equivalent object instance. Can be {@literal null}.
     */
    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        String cacheKey = new String(bytes, charset);
        int indexOf = cacheKey.indexOf(prefixKey);
        if (indexOf == -1) {
            cacheKey = prefixKey + cacheKey;
        }
        return (cacheKey.getBytes() == null ? null : cacheKey);
    }
}

