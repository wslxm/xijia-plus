package com.ws.ldy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 导入改jar 后如果想启动某个服务不想配置数据源, 配置 exclude = DataSourceAutoConfiguration.class 即可
 * @author wangsong
 * @date 2021/1/23 0023 15:01
 * @return
 * @version 1.0.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RunServer {

    public static void main(String[] args) {
        SpringApplication.run(RunServer.class, args);
    }
}

