package com.ws.ldy.taskscheduling.aspect;


import com.ws.ldy.taskscheduling.annotation.SynchronizedLock;
import com.ws.ldy.taskscheduling.annotation.TaskLock;
import com.ws.ldy.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class LockAspect {

    private static final String TASK_LOCK_KEY = "task-lock-";
    private static final String SYNCHRONIZED_KEY = "synchronized-key-";

    @Resource
    private RedisLock redisLock;


    /**
     * jvm 任务锁 (多个jvm只有一个jvm执行)
     * @author wangsong
     * @param proceed
     * @date 2021/7/27 0027 11:04
     * @return void
     * @version 1.0.0
     */
    @Around("execution(* *.*(..)) && @annotation(com.ws.ldy.taskscheduling.annotation.TaskLock)")
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
        //需要锁
        boolean lock = redisLock.lock(TASK_LOCK_KEY + taskLock.lockKed(), taskLock.expireTime() * 1000);
        if (lock) {
            log.info(taskLock.lockKed() + "获取锁成功");
            try {
                proceed.proceed();
                // 不释放,让其自动过期, 已次来保证定时任务绝对不被重复执行, 避免定时任务业务代码执行过快, 第二个jvm执行时锁已经被释放的情况发生
            } catch (Throwable e) {
                log.error("method:{},运行错误！", method, e);
            }
        } else {
            log.error(taskLock.lockKed() + "获取锁失败");
        }
    }


    /**
     * 方法锁 (多个jvm只有一个jvm执行, 如果其中一个jvm正在执行, 下一个执行进程的jvm需等待上一个线程执行完后才能执行)
     * @author wangsong
     * @param proceed
     * @date 2021/7/27 0027 11:04
     * @return void
     * @version 1.0.0
     */
    @Around("@annotation(com.ws.ldy.taskscheduling.annotation.SynchronizedLock)")
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
        // 获取锁,直到获取成功 (设置锁的有效期为5秒, 如果上一个线程5秒钟没有响应, 将账单释放锁让后面的线程重新获取)
        while (true) {
            boolean lock = redisLock.lock(SYNCHRONIZED_KEY + taskLock.lockKed(), 5 * 1000);
            if (lock) {
                log.info(taskLock.lockKed() + "获取锁成功");
                break;
            }
        }
        try {
            proceed.proceed();
            // 释放锁
            redisLock.unLock(SYNCHRONIZED_KEY + taskLock.lockKed());
        } catch (Throwable e) {
            // 释放锁
            redisLock.unLock(SYNCHRONIZED_KEY + taskLock.lockKed());
            log.error("method:{},运行错误！", method, e);
        }
    }
}