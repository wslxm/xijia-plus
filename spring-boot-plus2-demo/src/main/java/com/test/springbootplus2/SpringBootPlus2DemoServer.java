package com.test.springbootplus2;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类
 *
 * 注解 @SpringBootApplication
 *    - exclude = DruidDataSourceAutoConfigure.class 为排除默认数据源, 启动时不配置数据源时不会报错
 *    - scanBasePackages = {} 扫描路径, 指定包下存在spring相关注解的类的将被spring bend容器管理

 * 注解 @MapperScan
 *   - mybatis-plus 扫描 mapper层
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

