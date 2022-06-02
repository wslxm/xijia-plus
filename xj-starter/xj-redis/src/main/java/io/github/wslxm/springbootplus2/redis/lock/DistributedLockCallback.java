package io.github.wslxm.springbootplus2.redis.lock;

/**
 * 分布式锁回调接口
 *
 * @Author: dgg-wxw
 * @Date: 2020/5/20 15:36
 */
public interface DistributedLockCallback<T> {

	/**
	 * 调用者必须在此方法中实现需要加分布式锁的业务逻辑
	 */
	T process() throws Throwable;

	/**
	 * 得到分布式锁名称
	 */
	String getLockName();

	/**
	 * 得到分布式锁占用时异常描述
	 */
	String getExceptionDesc();
}
