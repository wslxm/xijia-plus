package com.ws.ldy.taskscheduling.aspect;


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
public class TaskLockAspect {

    private static final String LOCK_VALUE = "taskLock-";

    @Resource
    private RedisLock redisLock;


    @Around("execution(* *.*(..)) && @annotation(com.ws.ldy.taskscheduling.annotation.TaskLock)")
    public void cacheLockPoint(ProceedingJoinPoint proceed) {
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
        }else{
            //需要锁
            boolean lock = redisLock.lock(LOCK_VALUE + taskLock.lockKed(), taskLock.expireTime() * 1000);
            if (lock) {
                log.info(taskLock.lockKed() + "获取锁成功");
                try {
                    proceed.proceed();
                    // 不释放,让其自动过期,已次来保证定时任务绝对不被重复执行, 避免定时任务业务代码执行过快, 第二个jvm执行时锁已经被释放的情况发送
                } catch (Throwable e) {
                    log.error("method:{},运行错误！", method, e);
                }
            } else {
                log.error(taskLock.lockKed() + "获取锁失败");
            }
        }
    }
}