package io.github.wslxm.springbootplus2.redis.lock;


import io.github.wslxm.springbootplus2.redis.config.error.RedisErrorException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * 实现分布式锁模板
 *
 * @Author: dgg-wxw
 * @Date: 2020/5/20 15:36
 */
@Slf4j
public class SingleDistributedLockTemplate implements DistributedLockTemplate {

	private RedissonClient redisson;

	public SingleDistributedLockTemplate() {
	}

	public SingleDistributedLockTemplate(RedissonClient redisson) {
		this.redisson = redisson;
	}

	@Override
	public <T> T lock(DistributedLockCallback<T> callback, boolean fairLock) throws Throwable {
		return lock(callback, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
	}

	@Override
	public <T> T lock(DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws Throwable {
		RLock lock = getLock(redisson, callback.getLockName(), fairLock);
		try {
			log.info("开始获取锁===========");
			lock.lock(leaseTime, timeUnit);
			return callback.process();
		} finally {
			if (lock != null && lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

	@Override
	public <T> T tryLock(DistributedLockCallback<T> callback, boolean fairLock) throws Throwable {
		return tryLock(callback, DEFAULT_WAIT_TIME, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
	}

	@Override
	public <T> T tryLock(DistributedLockCallback<T> callback,
	                     long waitTime,
	                     long leaseTime,
	                     TimeUnit timeUnit,
	                     boolean fairLock) throws Throwable {
		RLock lock = getLock(redisson, callback.getLockName(), fairLock);
		try {
			if (lock.tryLock(waitTime, leaseTime, timeUnit)) {
				return callback.process();
			} else {
				throw new RedisErrorException(callback.getExceptionDesc());
			}
		} catch (InterruptedException e) {
			throw new RedisErrorException("获取锁错误" + e.getMessage());
		} finally {
			if (lock != null && lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

	public static RLock getLock(RedissonClient redisson, String lockName, boolean fairLock) {
		RLock lock;
		if (fairLock) {
			lock = redisson.getFairLock(lockName);
		} else {
			lock = redisson.getLock(lockName);
		}
		return lock;
	}

}
