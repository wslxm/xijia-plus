package io.github.wslxm.springbootplus2.starter.redis.lock.aspect;


import io.github.wslxm.springbootplus2.starter.redis.error.RedisErrorException;
import io.github.wslxm.springbootplus2.starter.redis.lock.annotation.SynchronizedLock;
import io.github.wslxm.springbootplus2.starter.redis.lock.annotation.TaskLock;
import io.github.wslxm.springbootplus2.starter.redis.lock.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Slf4j
@Component
public class LockAspect {

    private static final String TASK_LOCK_KEY = "task-lock-";
    private static final String SYNCHRONIZED_KEY = "synchronized-key-";

    @Resource
    private RedisLockUtil redisLockUtil;


    /**
     * jvm 任务锁
     *  多个jvm的相同方法, 那个jvm先抢锁就谁执行, 其他线程不执行
     *  ----
     *  // 不释放,让其自动过期, 已次来保证定时任务绝对不被重复执行,
     *  // 避免定时任务业务代码执行过快, 第二个jvm执行时锁已经被释放的情况发生
     *
     * @author wangsong
     * @param proceed
     * @date 2021/7/27 0027 11:04
     * @return void
     * @version 1.0.0
     */
    @Around("execution(* *.*(..)) && @annotation(io.github.wslxm.springbootplus2.starter.redis.lock.annotation.TaskLock)")
    public void taskLockPoint(ProceedingJoinPoint proceed) {
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        Method method = signature.getMethod();
        TaskLock taskLock = method.getAnnotation(TaskLock.class);
        if (taskLock == null || "".equals(taskLock.lockKed())) {
            //不需要锁
            if (taskLock == null) {
                log.info("taskLock Annotation null");
            } else {
                log.info(taskLock.lockKed() + "is no lockKey");
            }
            return;
        }
        boolean isLock = false;
        try {
            isLock = redisLockUtil.lock(TASK_LOCK_KEY + taskLock.lockKed(), taskLock.expireTime() * 1000);
        } catch (RedisErrorException e) {
            // 没有启用redis,直接运行
            log.info("没有启用redis,获取分布式锁[{}]失败,程序将直接运行,请注意如果集群部署将可能出现任务重复执行", taskLock.lockKed());
            try {
                proceed.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        if (isLock) {
            log.info("获取分布式锁{}成功,程序将开始运行", taskLock.lockKed());
            try {
                proceed.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            log.error(taskLock.lockKed() + "获取锁失败,程序终止");
        }
    }


    /**
     * jvm锁
     */
    private Lock lock = new ReentrantLock();


    /**
     * 方法锁(synchronized)
     * 如果方法正在被其中一个 jvm正在执行, 下一个执行指定方法的jvm 需等待上一个线程执行完后才能执行)
     * @author wangsong
     * @param proceed
     * @date 2021/7/27 0027 11:04
     * @return void
     * @version 1.0.0
     */
    @Around("@annotation(io.github.wslxm.springbootplus2.starter.redis.lock.annotation.SynchronizedLock)")
    public void synchronizedLockPoint(ProceedingJoinPoint proceed) {
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        Method method = signature.getMethod();
        SynchronizedLock taskLock = method.getAnnotation(SynchronizedLock.class);
        if (taskLock == null || "".equals(taskLock.lockKed())) {
            //不需要锁
            if (taskLock == null) {
                log.info("synchronizedLock Annotation null");
            } else {
                log.info(taskLock.lockKed() + "is no lockKey");
            }
            return;
        }
        boolean isLock = false;
        while (true) {
            try {
                // 获取分布式锁, 如果没有启动redis, 返回 RedisErrorException 异常
                // 获取锁,直到获取成功 (设置锁的有效期为5秒, 如果上一个线程5秒钟没有响应, 将自动释放锁让后面的线程重新获取)
                isLock = redisLockUtil.lock(SYNCHRONIZED_KEY + taskLock.lockKed(), 5 * 1000);
                if (isLock) {
                    break;
                }
            } catch (RedisErrorException e) {
                // 没有启用redis,使用lock锁, 同 synchronized
                log.info("没有启用redis,获取分布式锁[{}]失败,程序将自动使用jvm 的 Lock 进行运行", taskLock.lockKed());
                lock.lock();
                try {
                    proceed.proceed();
                } catch (Throwable e1) {
                    log.error("method:{},运行错误！", method, e1);
                } finally {
                    // 释放锁
                    lock.unlock();
                }
                break;
            }
        }
        // 使用redis 分布式锁，100%能获取(锁5秒内没释放将自动释放)
        if (isLock) {
            try {
                log.info("获取分布式锁{}成功,程序将开始运行", taskLock.lockKed());
                proceed.proceed();
            } catch (Throwable e) {
                log.error("method:{},运行错误！", method, e);
            } finally {
                // 释放锁
                redisLockUtil.unLock(SYNCHRONIZED_KEY + taskLock.lockKed());
            }
        }
    }
}