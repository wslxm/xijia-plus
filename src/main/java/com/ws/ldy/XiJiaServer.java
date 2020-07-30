package com.ws.ldy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ws.ldy.common.utils.ConsoleColors;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类
 * - exclude = DruidDataSourceAutoConfigure.class 为排除默认数据源
 * - @MapperScan 为mapper 扫包路径
 * - @EnableAdminServer 为监控Admin中心,访问地址为项目路径+ /bootAdmin, yml中配置
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2019/10/31 20:45
 * @version 1.0.0
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.ws.ldy.*.*.mapper"})
//@EnableAdminServer
public class XiJiaServer {

    public static void main(String[] args) {
        SpringApplication.run(XiJiaServer.class, args);
        ConsoleColors.getSuccessYellowBright();
    }
}
