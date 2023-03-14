package io.github.wslxm.springbootplus2.starter.redis.config;


import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 为redis key 统一加上前缀
 */
public class RedisKeySerializer extends StringRedisSerializer {

    /**:
     * 编码格式
     */
    private final Charset charset = StandardCharsets.UTF_8;

    /**
     * 前缀
     */
    private String prefixKey;

    public RedisKeySerializer(String prefixKey) {
        this.prefixKey = prefixKey + ":";
    }


    @Override
    public String deserialize(@Nullable byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset).replaceFirst(prefixKey, ""));
    }

    @Override
    public byte[] serialize(@Nullable String string) {
        return (string == null ? null : (prefixKey + string).getBytes(charset));
    }

}

