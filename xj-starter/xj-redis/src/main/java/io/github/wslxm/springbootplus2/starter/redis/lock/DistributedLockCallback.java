package io.github.wslxm.springbootplus2.starter.redis.lock;

/**
 * 分布式锁回调接口
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/6/3 0003 11:07
 * @version 1.0.0
 */
public interface DistributedLockCallback<T> {



    /**
     * 调用者必须在此方法中实现需要加分布式锁的业务逻辑
     * @author wangsong
     * @return T
     * @throws Throwable
     * @version 1.0.0
     */
    T process() throws Throwable;


    /**
     * 得到分布式锁名称
     * @author wangsong
     * @date 2022/8/22 0022 20:59
     * @return java.lang.String
     * @version 1.0.0
     */
    String getLockName();

    /**
     * 得到分布式锁占用时异常描述
     * @author wangsong
     * @date 2022/8/22 0022 20:59
     * @return java.lang.String
     * @version 1.0.0
     */
    String getExceptionDesc();
}
