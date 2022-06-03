package io.github.wslxm.springbootplus2.starter.redis.test;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 分布式锁测试
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/6/3 0003 8:55
 * @version 1.0.0
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

//		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//		for (int i = 0; i < 2; i++) {
//			final int index = i;
//			cachedThreadPool.execute(new Runnable() {
//				@Override
//				public void run() {
//					String order1 = redisService.redissonTest2("order1");
//					System.out.println(Thread.currentThread().getName() + " 当前线程");
//				}
//			});
//		}
    }
}
