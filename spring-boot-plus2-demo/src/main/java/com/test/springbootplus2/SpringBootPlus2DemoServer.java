package com.test.springbootplus2;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类 (修改架构可直接使用该模块)
 * - exclude = DruidDataSourceAutoConfigure.class 为排除默认数据源
 * - @MapperScan 为mapper 扫包路径
 * - @EnableAdminServer 为监控Admin中心,访问地址为项目路径+ /bootAdmin, yml中配置
 * - @ServletComponentScan 为开启 Druid 监控平台
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2019/10/31 20:45
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.test.springbootplus2", "com.ws.ldy"}, exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.test.springbootplus2.*.*.mapper", "com.ws.ldy.*.*.mapper"})
public class SpringBootPlus2DemoServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlus2DemoServer.class, args);
    }
}

