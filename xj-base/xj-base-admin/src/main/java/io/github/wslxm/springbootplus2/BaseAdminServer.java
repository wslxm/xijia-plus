package io.github.wslxm.springbootplus2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类 (修改架构可直接使用该模块)
 *
 * <p>
 * 注解 @SpringBootApplication 的参数 exclude = DruidDataSourceAutoConfigure.class 为排除默认数据源
 * - @MapperScan 为mapper 扫包路径
 * - @EnableAdminServer 为监控Admin中心,访问地址为项目路径+ /bootAdmin, yml中配置
 * - @ServletComponentScan 为开启 Druid 监控平台
 * </p>
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2019/10/31 20:45
 */
@SpringBootApplication(scanBasePackages = {"io.github.wslxm.springbootplus2"})
@MapperScan({"io.github.wslxm.springbootplus2.*.*.mapper"})
public class BaseAdminServer {

    public static void main(String[] args) {
        SpringApplication.run(BaseAdminServer.class, args);
    }
}

