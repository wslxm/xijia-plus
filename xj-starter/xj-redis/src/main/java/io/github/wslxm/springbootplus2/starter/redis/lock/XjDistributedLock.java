package io.github.wslxm.springbootplus2.starter.redis.lock;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


/**
  * 分布式锁注解
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2022/6/3 0003 11:08
  * @version 1.0.0
  */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface XjDistributedLock {

	/**
	 * 锁的名称。（支持 SpEL 表达式）
	 */
	@AliasFor("lockName")
	String value() default "";

	/**
	 * 锁的名称。（支持 SpEL 表达式）
	 */
	@AliasFor("value")
	String lockName() default "";

	/**
	 * 是否使用公平锁。
	 * 公平锁即先来先得。
	 */
	boolean fairLock() default false;

	/**
	 * 是否使用尝试锁。 为 false 时会一直等待到获取到锁为止
	 */
	boolean tryLock() default true;

	/**
	 * 最长等待时间。
	 * 该字段只有当tryLock()返回true才有效。
	 */
	long waitTime() default 0L;

	/**
	 * 锁超时时间。
	 * 超时时间过后，锁自动释放。
	 * 建议：
	 * 尽量缩简需要加锁的逻辑。
	 */
	long leaseTime() default 5L;

	/**
	 * 时间单位。默认为秒。
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	/**
	 * 分布式锁占用时异常描述
	 */
	String exceptionDesc() default "分布式锁生效，请稍后重试...";

	/**
	 * Spring Expression Language (SpEL) expression used for making the method
	 * caching conditional.
	 * <p>Default is {@code "1"}, meaning the method result is always cached.
	 * <p>The SpEL expression evaluates against a dedicated context that provides the
	 * following meta-data:
	 * <ul>
	 * <li>{@code #root.method}, {@code #root.target}, and {@code #root.caches} for
	 * references to the {@link java.lang.reflect.Method method}, target object, and
	 * affected cache(s) respectively.</li>
	 * <li>Shortcuts for the method name ({@code #root.methodName}) and target class
	 * ({@code #root.targetClass}) are also available.
	 * <li>Method arguments can be accessed by index. For instance the second argument
	 * can be accessed via {@code #root.args[1]}, {@code #p1} or {@code #a1}. Arguments
	 * can also be accessed by name if that information is available.</li>
	 * </ul>
	 */
	String condition() default "true";
}
