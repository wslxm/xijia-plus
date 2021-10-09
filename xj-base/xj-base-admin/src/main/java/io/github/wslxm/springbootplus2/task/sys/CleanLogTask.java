//package io.github.wslxm.task.sys;
//
//import io.github.wslxm.common.utils.LocalDateTimeUtil;
//import io.github.wslxm.modules.sys.gc.mapper.XjDataBaseMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//
///**
// * 清理请求日志数据
// * @author wangsong
// * @date 2020/12/29 0029 13:25
// * @return
// * @version 1.0.0
// */
//@Component
//@Configuration
//@EnableScheduling
//@Slf4j
//public class CleanLogTask {
//
//    /**
//     * 需要物理删除被逻辑删除的表名称
//     */
//    String table = "t_xj_admin_log";
//
//
//    /**
//     * 数据保留天数
//     */
//    Integer dayNum = 30;
//
//
//    @Autowired
//    private XjDataBaseMapper xjDataBaseMapper;
//
//
//    @Scheduled(cron = "0 0 6 1/1 * ?")
//    //@Scheduled(cron = "0/10 * * * * ?")
//    private void configureTasks() {
//        // 指定n天前
//        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
//        String updTime = LocalDateTimeUtil.parse(time);
//        xjDataBaseMapper.deleteByDayFront(table, updTime);
//        log.info("物理{} 表 删除{} 天前的数据 ", table, dayNum);
//    }
//}
