package io.github.wslxm.springbootplus2.redis.test;

import io.github.wslxm.springbootplus2.redis.lock.DggDistributedLock;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class RedisService {


	@DggDistributedLock(lockName = "#noKey", tryLock = true, waitTime = 0L, leaseTime = 5L)
	public String redissonTest2(@PathVariable("key") String noKey) {
//		if(lock == null){
//			lock =  redissonClient.getLock("lockKey");
//		}
//		System.out.println(lock);
		try {
//			boolean b = lock.tryLock(1, 5000, TimeUnit.MILLISECONDS);
//			if (b) {
//				log.info("获取锁成功");
//			} else {
//				log.info("获取锁失败");
//			}
			Thread.sleep(1000);
		} catch (Exception e) {
//			log.info("获取锁失败");
		} finally {
//			lock.unlock();
//			log.info("释放锁");
		}
		System.out.println("执行成功");
		return "锁测试";
	}
}
