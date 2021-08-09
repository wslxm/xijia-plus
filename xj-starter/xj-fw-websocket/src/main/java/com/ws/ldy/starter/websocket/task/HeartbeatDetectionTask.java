package com.ws.ldy.starter.websocket.task;

import com.ws.ldy.starter.websocket.model.vo.SendMsgVO;
import com.ws.ldy.starter.websocket.server.WebsocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 心跳检测 (保证用户不掉线)
 * @author wangsong
 * @date 2021/1/14 0014 19:12
 * @return
 * @version 1.0.0
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class HeartbeatDetectionTask {


    @Autowired
    private WebsocketServer websocketServer;

    /**
     * 每分钟一次 【  0 1/1 * * * ? * 】
     * 测试 20 秒一次【  0/20 * * * * ? 】
     */
    @Scheduled(cron = "0/30 * * * * ?")
    private void configureTasks() {
        SendMsgVO vo = new SendMsgVO();
        vo.setMsgType(0);
        vo.setFrom("0");
        vo.setUsername("系统");
        vo.setHeadPic(null);
        vo.setTo("ALL");
        vo.setContent("心跳检测");
        vo.setExtras(null);
        vo.setOnlineNum(null);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        vo.setCreateTime(df.format(LocalDateTime.now()));
        websocketServer.send(vo);
    }
}
