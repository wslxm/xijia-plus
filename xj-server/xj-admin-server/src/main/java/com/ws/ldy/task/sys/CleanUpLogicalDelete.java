//package com.ws.ldy.task.sys;
//
//import com.ws.ldy.common.utils.LocalDateTimeUtil;
//import com.ws.ldy.modules.sys.gc.mapper.XjDataBaseMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 清理逻辑删除数据 (逻辑删除数据保留7天)
// * @author wangsong
// * @date 2020/12/29 0029 13:25
// * @return
// * @version 1.0.0
// */
//@Component
//@Configuration
//@EnableScheduling
//@Slf4j
//public class CleanUpLogicalDelete {
//
//
//    /**
//     * 需要物理删除被逻辑删除的表名称
//     */
//    List<String> tables = new ArrayList<String>() {{
//        // 系统核心表
////        add("t_admin_authority");
//        add("t_admin_dictionary");
//        add("t_admin_menu");
////        add("t_admin_role");
////        add("t_admin_role_auth");
////        add("t_admin_role_menu");
////        add("t_admin_role_user");
////        add("t_admin_user");
//        // 系统增强表
////        add("t_xj_admin_banner");
////        add("t_xj_admin_blacklist");
////        add("t_xj_admin_config");
////        add("t_xj_admin_datasource");
////        add("t_xj_admin_help");
////        add("t_xj_admin_log");
////        add("t_xj_admin_msg");
//    }};
//
//    /**
//     * 逻辑删除数据保留天数
//     */
//    Integer dayNum = 7;
//
//
//    @Autowired
//    private XjDataBaseMapper xjDataBaseMapper;
//
//
//    @Scheduled(cron = "0 0 6 1/1 * ?")
//    private void configureTasks() {
//        // 指定n天前
//        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
//        String updTime = LocalDateTimeUtil.convertLDTToStr(time);
//        tables.forEach(table -> {
//            xjDataBaseMapper.deleteByTable(table, updTime);
//            log.info("物理删除逻辑删除数据成功, tables： {} ", table);
//        });
//    }
//}
