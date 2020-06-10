package com.ws.ldy;

import com.ws.ldy.common.utils.ConsoleColors;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 20:45
 */
@SpringBootApplication
@MapperScan("com.ws.ldy.admin.mapper")
@EnableAdminServer
public class XiJiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiJiaApplication.class, args);
        ConsoleColors.getSuccessYellowBright();
        System.out.println("http://127.0.0.1:80/");
    }
}
