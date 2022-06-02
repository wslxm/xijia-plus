package io.github.wslxm.springbootplus2.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.wslxm.springbootplus2.redis.lock.DistributedLockTemplate;
import io.github.wslxm.springbootplus2.redis.lock.SingleDistributedLockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
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

    private Duration timeToLive = Duration.ZERO;


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
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		/// om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
				ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

		// 解决jackson2无法反序列化LocalDateTime的问题
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		om.registerModule(new JavaTimeModule());

		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		//使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(stringRedisSerializer);
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}


	/**
	 * 缓存管理器配置
	 *
	 * @param factory
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
//		if (!RedisUtil.isRedis()) {
//			// 缓存注解 使用 spring 缓存
//			log.info("已启用 spring 本地缓存");
//			return new ConcurrentMapCacheManager();
//		} else {
			// 缓存注解 使用 redis 缓存
			RedisSerializer<String> redisSerializer = new StringRedisSerializer();
			Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

			//解决查询缓存转换异常的问题
			ObjectMapper om = new ObjectMapper();
			om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
			/// om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
			om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
					ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
			jackson2JsonRedisSerializer.setObjectMapper(om);

			// 解决jackson2无法反序列化LocalDateTime的问题
			om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			om.registerModule(new JavaTimeModule());

			// 配置序列化（解决乱码的问题）
			RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
					.entryTtl(timeToLive)
					.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
					.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
					.disableCachingNullValues();
			log.info("已启用 redis 分布式缓存");

			return RedisCacheManager
					.builder(factory)
					.cacheDefaults(config)
					.build();

			//return new CustomizedRedisCacheManager(this.applicationName, this.nacosDataSource, this.container)
//			@Value("${spring.application.name}")
//			private String applicationName;
//		}
	}




//	@Primary
//	@Bean
//	@ConditionalOnMissingBean(RedisProperties.class)
//	public RedisProperties redisProperties() {
//		Map<Integer, RedisProperties> redisX = dggRedisProperties.getRedis();
//		RedisProperties p = redisX.get(DggRedisConfiguration.defaultRedis);
//		Objects.requireNonNull(p, String.format("'dgg.paas.redis' index %d not found", DggRedisConfiguration.defaultRedis));
//		return p;
//	}

//	@Bean
//	public <T> DggRedisTemplateContainer<T> dggRedisTemplateContainer() {
//		return new DggRedisTemplateContainer<>(dggRedisProperties.getRedis());
//	}

//	@Bean("redisTemplate")
//	public RedisTemplate configRedisTemplate() {
//		return dggRedisTemplateContainer().get(DggRedisConfiguration.defaultRedis);
//	}

//	@Bean("reactiveRedisTemplate")
//	public ReactiveRedisTemplate configReactiveRedisTemplate() {
//		return dggRedisTemplateContainer().getReactive(DggRedisConfiguration.defaultRedis);
//	}
//
//	@Bean("redissonClient")
//	@Lazy
//	public RedissonClient configRedissonClient() {
//		return dggRedisTemplateContainer().getRedisson(DggRedisConfiguration.defaultRedis);
//	}

	@Bean
	@Lazy
	public DistributedLockTemplate distributedLockTemplate(RedissonClient redissonClient) {
		return new SingleDistributedLockTemplate(redissonClient);
	}
}

