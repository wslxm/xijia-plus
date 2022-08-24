package io.github.wslxm.springbootplus2.starter.redis.test;

import io.github.wslxm.springbootplus2.starter.redis.lock.XjDistributedLock;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * redis 分布式锁 测试
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/8/22 0022 20:56
 * @version 1.0.0
 */
@Service
public class RedisService {

    @XjDistributedLock(lockName = "#noKey", tryLock = true, waitTime = 0L, leaseTime = 5L)
    public String redissonTest2(@PathVariable("key") String noKey) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        } finally {
        }
        System.out.println("执行成功");
        return "锁测试";
    }
}
