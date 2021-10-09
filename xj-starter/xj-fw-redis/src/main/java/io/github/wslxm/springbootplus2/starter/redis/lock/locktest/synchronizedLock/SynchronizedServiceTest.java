//package io.github.wslxm.starter.redis.lock.locktest.synchronizedLock;
//
//import io.github.wslxm.starter.redis.lock.annotation.SynchronizedLock;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
///***
// * 模拟请求方法
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2021/7/27 0027 11:55
// * @version 1.0.0
// */
//@Service
//@Slf4j
//public class SynchronizedServiceTest {
//
//
//    /**
//     * 模拟每个用户执行该方法耗时 n 毫秒
//     * @author wangsong
//     * @param i
//     * @date 2021/7/27 0027 11:56
//     * @return void
//     * @version 1.0.0
//     */
//    @SynchronizedLock(lockKed = "test")
//    public void test(Integer i) {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info(LocalDateTime.now() + "--hello world!--" + i);
//    }
//}
