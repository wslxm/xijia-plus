package io.github.wslxm.springbootplus2.redis.controller;

import io.github.wslxm.springbootplus2.redis.lock.DggDistributedLock;
import io.github.wslxm.springbootplus2.redis.util.RedisUtil;
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


	@ApiOperation("redis 分布式锁测试")
	@GetMapping(value = "/redisson/{key}")
	public String redissonTest(@PathVariable("key") String lockKey) {
		if (lock == null) {
			lock = redissonClient.getLock("lockKey");
		}
		System.out.println(lock);
		try {
			lock.lock();
			Thread.sleep(500);
		} catch (Exception e) {
			return "获取锁失败";
		} finally {
			lock.unlock();
		}
		return "已解锁";
	}



	@ApiOperation("redis 分布式锁测试")
	@GetMapping(value = "/redissonTest2/{key}")
	public String redissonTest2(@PathVariable("key") String lockKey) {
		if (lock == null) {
			lock = redissonClient.getLock("lockKey");
		}
		System.out.println(lock);
		try {
			boolean b = lock.tryLock(0, 5, TimeUnit.SECONDS);
			if(b){
				log.info("获取锁成功");
			}else{
				log.info("获取锁失败");
			}
			Thread.sleep(500);
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
		return "已解锁";
	}


	@ApiOperation("获取分布式唯一编号")
	@GetMapping(value = "/getNo")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "noKey", value = "编号(如订单：order)", required = true, example = "order"),
			@ApiImplicitParam(name = "delta", value = "自增开始值", required = true, example = "1"),
	})
	@DggDistributedLock(lockName = "#noKey", tryLock = false, waitTime = 0L, leaseTime = 5L)
	public String getNo(String noKey, Long delta) {
		System.out.println("请求");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		return redisUtil.getNo(noKey, delta);
	}
}
