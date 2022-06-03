package io.github.wslxm.springbootplus2.starter.redis.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.Cache;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * 缓存注解 SpEL 表达式计算上下文根对象
 *
 * @Author: dgg-wxw
 * @Date: 2020/6/19 14:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionRootObject {
	/**
	 * 有效的缓存集合
	 */
	private Collection<? extends Cache> caches;
	/**
	 * 目标方法
	 */
	private Method method;
	/**
	 * 目标方法名
	 */
	private String methodName;
	/**
	 * 方法参数
	 */
	private Object[] args;
	/**
	 * 目标对象
	 */
	private Object target;
	/**
	 * 目标对象 Class 类型
	 */
	private Class<?> targetClass;
}
