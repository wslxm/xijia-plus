package com.ws.ldy.task.sys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * MYSQL 数据备份
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/12/29 0029 13:27
 * @version 1.0.0
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class MysqlDataBackup {




    @Scheduled(cron = "0 0 0/1 * * ?")
    private void configureTasks() {

        // TODO MYSQL 数据备份
        log.info("===> 清理逻辑删除数据");
    }
}
