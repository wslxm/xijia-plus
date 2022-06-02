//package io.github.wslxm.starter.redis.lock.locktest.task;
//
//import io.github.wslxm.starter.redis.lock.annotation.TaskLock;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//@Configuration
//@EnableScheduling
//@Slf4j
//public class TaskTest {
//
//
//    public final static String cron = "0/2 * * * * ?";
//
//
//    @Scheduled(cron = TaskTest.cron)
//    @TaskLock(lockKed = "task1", expireTime = 1)
//    public void executeTask1() {
//        log.info( LocalDateTime.now()+ "hello world!" + "--B" );
//    }
//
//
//    @Scheduled(cron = TaskTest.cron)
//    @TaskLock(lockKed = "task1", expireTime = 1)
//    public void executeTask2() {
//        log.info( LocalDateTime.now()+ "hello world!" + "--A" );
//    }
//
//    @Scheduled(cron = TaskTest.cron)
//    @TaskLock(lockKed = "task1", expireTime = 1)
//    public void executeTask3() {
//        log.info( LocalDateTime.now()+ "hello world!" + "--C" );
//    }
//}
