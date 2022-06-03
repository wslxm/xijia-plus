package io.github.wslxm.springbootplus2.starter.redis.lock;


import io.github.wslxm.springbootplus2.starter.redis.config.error.RedisErrorException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;


/**
 *  实现分布式锁模板
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/6/3 0003 11:08
 * @version 1.0.0
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
        // 创建锁
        RLock lock = getLock(redisson, callback.getLockName(), fairLock);
        // 获取锁
        boolean isLock = lock.tryLock(waitTime, leaseTime, timeUnit);
        if (isLock) {
            T process = null;
            try {
                // 获取锁成功，执行业务
                process = callback.process();
            } finally {
                // 不管业务是否成功执行，完成后都将释放锁
                if (lock != null && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
            // 返回执行结果
            return process;
        } else {
            // 获取锁失败,抛出异常
            throw new RedisErrorException(callback.getExceptionDesc());
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
