//package com.ws.ldy.starter.redis.lock.locktest.synchronizedLock;//package com.ws.ldy.taskscheduling.task;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author ws
// */
//@Configuration
//@EnableScheduling
//@Slf4j
//public class SynchronizedLockTaskTest {
//
//    //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
//    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//
//    public SynchronizedLockTaskTest(){
//        log.info("SynchronizedLockTaskTest 定时任务已加载");
//    }
//
//    @Autowired
//    private SynchronizedServiceTest synchronizedServiceTest;
//
//    @Scheduled(cron = "0/30 * * * * ?")
//    public void testSynchronizedLock() {
//        // 使用多线程模拟 100个用户同时请求指定的方法, 是否排队执行
//        for (int i = 0; i < 2; i++) {
//            int finalI = i;
//            cachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    synchronizedServiceTest.test(finalI);
//                }
//            });
//        }
//    }
//}
//
