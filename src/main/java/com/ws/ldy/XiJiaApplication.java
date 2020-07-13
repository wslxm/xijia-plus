package com.ws.ldy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ws.ldy.common.utils.ConsoleColors;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 20:45
 * 说明1: exclude = DruidDataSourceAutoConfigure.class 为排除默认数据源
 * 说明2:@MapperScan 为mapper 扫包路径
 * 说明3:@EnableAdminServer 为监控Admin中心,访问地址为项目路径+ /bootAdmin, yml中配置
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.ws.ldy.*.mapper")
//@EnableAdminServer
public class XiJiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiJiaApplication.class, args);
        ConsoleColors.getSuccessYellowBright();
    }
}
