package com.ws.ldy.lock.test.synchronizedLock;//package com.ws.ldy.taskscheduling.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ws
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class SynchronizedLockTaskTest {

    public final static String cron = "0/30 * * * * ?";

    //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


    @Autowired
    private SynchronizedServiceTest synchronizedServiceTest;

    @Scheduled(cron = SynchronizedLockTaskTest.cron)
    public void testSynchronizedLock() {
        // 使用多线程模拟 100个用户同时请求指定的方法, 是否排队执行
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    synchronizedServiceTest.test(finalI);
                }
            });
        }
    }
}

