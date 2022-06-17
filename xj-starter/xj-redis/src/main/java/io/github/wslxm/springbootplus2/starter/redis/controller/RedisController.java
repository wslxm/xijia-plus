package io.github.wslxm.springbootplus2.starter.redis.controller;

import io.github.wslxm.springbootplus2.starter.redis.lock.XjDistributedLock;
import io.github.wslxm.springbootplus2.starter.redis.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * redis 测试类
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/6/2 11:57
 */
@Api(value = "RedisController", tags = "Redis  -->  Redis 测试")
@RequestMapping("/api/open/redis")
@RestController
@Slf4j
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisUtil redisUtil;

    static RLock lock = null;


    @ApiOperation(value = "redis 分布式锁加锁测试", notes = "该锁会一直等待到获取到锁为止")
    @GetMapping(value = "/redissonDistributedLockTest1/{key}")
    public Object redissonDistributedLockTest1(@PathVariable("key") String lockKey) {
        if (lock == null) {
            lock = redissonClient.getLock(lockKey);
        }
        System.out.println(lock);
        try {
            lock.lock();
            log.info("成功获取锁");
            Thread.sleep(500);
            lock.unlock();
            log.info("已释放锁");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "执行完成";
    }


    @ApiOperation(value = "redis 分布式锁加锁测试2", notes = "该锁在一定时间内没获取到就自动释放")
    @GetMapping(value = "/redissonDistributedLockTest2/{key}")
    public Object redissonDistributedLockTest2(@PathVariable("key") String lockKey) {
        if (lock == null) {
            lock = redissonClient.getLock(lockKey);
        }
        System.out.println(lock);
        try {
            boolean isLock = lock.tryLock(1000, 5000, TimeUnit.SECONDS);
            if (isLock) {
                log.info("成功获取锁");
                Thread.sleep(500);
                lock.unlock();
                log.info("已释放锁");
            } else {
                log.info("获取锁超时");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "执行完成";
    }


    @ApiOperation(value = "redis 分布式注解锁测试", notes = "" +
            "建议使用工具 Jmeter 进行测试,swagger单线程模式 和 浏览器直接调用狠难模拟 " +
            "\n另外当前锁5秒自动过期, 使用可视化界面查看数据是注意")
    @GetMapping(value = "/redissonDistributedLockTest3")
    @XjDistributedLock(lockName = "#lockKey", tryLock = true, waitTime = 0L, leaseTime = 5L)
    public Object redissonDistributedLockTest3(String lockKey) {
        log.info("成功访问到方法");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        log.info("访问方法结束");
        return "返回成功";
    }


    @ApiOperation("获取分布式唯一编号")
    @GetMapping(value = "/getNo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noKey", value = "编号(如订单：order)", required = true, example = "order"),
            @ApiImplicitParam(name = "delta", value = "自增开始值", required = true, example = "1"),
    })
    public Object getNo(String noKey, Long delta) {
        System.out.println("请求");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return redisUtil.getNo(noKey, delta);
    }
}
