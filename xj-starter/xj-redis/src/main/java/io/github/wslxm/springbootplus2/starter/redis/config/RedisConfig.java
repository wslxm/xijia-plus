package io.github.wslxm.springbootplus2.starter.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.wslxm.springbootplus2.starter.redis.constant.CacheModeConst;
import io.github.wslxm.springbootplus2.starter.redis.lock.DistributedLockTemplate;
import io.github.wslxm.springbootplus2.starter.redis.lock.SingleDistributedLockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


/***
 *
 * 处理 redis缓存key 乱码, 以及保存对象数据设置为 json存储(默认二进制)
 *
 * <P>
 *  继承CachingConfigurerSupport, 为了自定义生成KEY的策略。可以不继承。
 *  注解 @EnableCaching  表示 启用缓存，这个注解很重要；可以使用
 * </P>
 * @author wangsong
 * @date 2021/3/2 0002 17:38
 * @return
 * @version 1.0.1
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 缓存模式
     * singlenode : 单节点模式, 数据直接挂载到指定的 db 中
     * multinode :  多节点模式, 数据挂载到指定的 db 下的子模块中, 同下方参数的 moduleName  值
     * <p>
     * 默认: 单节点模式， 如果开启 modularity 模块化模式后没有设置 moduleName 值, 那么模块名称默认使用 【no-project-name】
     */
    @Value("${spring.redis.cacheMode:singlenode}")
    protected String cacheMode;

    /**
     * 当前项目模块名称, 作用于 redis 前缀
     */
    @Value("${spring.application.name:no-project-name}")
    protected String moduleName;

    /**
     * 缓存对象 bean 配置
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // key 的序列化规则  默认单节点模式
        RedisSerializer<String> redisKeySerializer = new StringRedisSerializer();
        if (cacheMode.equals(CacheModeConst.MULTINODE)) {
            // 多节点模式
            redisKeySerializer = new RedisKeySerializer(moduleName);
        }
        template.setKeySerializer(redisKeySerializer);
        template.setHashKeySerializer(redisKeySerializer);

        // value 的序列化规则
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        /// om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // 解决 jackson2 无法反序列化 LocalDateTime 的问题
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 缓存管理器配置
     *
     * <P>缓存注解配置</P>
     *
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // key 的序列化规则
        RedisSerializer<String> redisKeySerializer = new StringRedisSerializer();

        // vulue 序列化规则
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 解决 jackson2 无法反序列化 LocalDateTime 的问题
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());

        String prefixCacheNameWith = "";
        // 多节点模式
        if (cacheMode.equals(CacheModeConst.MULTINODE)) {
            prefixCacheNameWith = moduleName + ":";
        }
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存的默认过期时间，也是使用Duration设置
                .entryTtl(Duration.ofMinutes(0))
                // .computePrefixWith(name -> ":")
                .prefixCacheNameWith(prefixCacheNameWith)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisKeySerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        log.info("redis 分布式缓存管理器注册成功, 可以使用缓存注解进行缓存数据");
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(config)
                .build();
    }


    /**
     * 分布式锁
     *
     * @param redissonClient
     * @author wangsong
     */
    @Bean
    @Lazy
    public DistributedLockTemplate distributedLockTemplate(RedissonClient redissonClient) {
        return new SingleDistributedLockTemplate(redissonClient);
    }

}

