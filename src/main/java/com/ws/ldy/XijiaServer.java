package com.ws.ldy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


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
@EnableZuulProxy      // zuul
@ServletComponentScan // 过滤器
@EnableAdminServer    // springbootAdmin
public class XijiaServer {

    public static void main(String[] args) {
        SpringApplication.run(XijiaServer.class, args);
    }

}
