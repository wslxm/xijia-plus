package io.github.wslxm.springbootplus2.redis.test;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 心跳检测 (保证用户不掉线)
 *
 * @author wangsong
 * @version 1.0.1
 * @date 2021/1/14 0014 19:12
 * @return
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class RedisTask {


	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private RedisService redisService;


	/**
	 * 每分钟一次 【  0 1/1 * * * ? * 】
	 * 测试 20 秒一次【  0/20 * * * * ? 】
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	private void test2() {

		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			final int index = i;
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					redisService.redissonTest2("order1");
					System.out.println(Thread.currentThread().getName() + " 当前线程");
				}
			});
		}

	}


}
