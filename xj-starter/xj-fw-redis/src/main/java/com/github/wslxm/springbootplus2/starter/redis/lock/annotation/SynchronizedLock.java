package com.github.wslxm.springbootplus2.starter.redis.lock.annotation;


import java.lang.annotation.*;


/**
 * 分布式锁 ，同Synchronized, 多个jvm 排队执行
 * <P>
 *     适用于代码中需要用到 Synchronized 的方法, 改用 @SynchronizedLock 可让代码在分布式部署环境下实现代码同步
 * </P>
 * @author wangsong
 * @date 2021/7/27 0027 11:02
 * @return
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SynchronizedLock {

    /**
     * redis 锁的 key
     */
    String lockKed() default "";

    /**
     * key在redis里存在的时间。默认10秒=
     *   锁的有效期设置请小于定时任务时间， 如定时任务10秒执行一次，那么 expireTime < 10
     *   锁的有效期在服务器没有时间差的情况下建议 > 1秒 (可使用默认10, 定时任务每次执行 >10 可使用默认)
     *   如两个jvm中时间存在一定细微差异，可能导致定时任务执行的时间有一点细微的不一致情况发生，该时间尽量 > 服务器时间差 + N秒
     */
    long expireTime() default 10;
}
