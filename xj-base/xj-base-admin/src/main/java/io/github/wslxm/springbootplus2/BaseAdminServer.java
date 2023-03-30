package io.github.wslxm.springbootplus2;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


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
@SpringBootApplication(scanBasePackages = {"io.github.wslxm.springbootplus2"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"io.github.wslxm.springbootplus2.*.*.mapper"})
public class BaseAdminServer {

    public static void main(String[] args) {
        SpringApplication.run(BaseAdminServer.class, args);
    }


}

