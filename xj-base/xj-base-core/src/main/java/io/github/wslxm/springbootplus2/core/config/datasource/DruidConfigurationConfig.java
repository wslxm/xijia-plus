package io.github.wslxm.springbootplus2.core.config.datasource;//package io.github.wslxm.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源监控  ip+端口/druid/sys_login.html， 账号：admin/密码：123456
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/18 20:43
 */
@Configuration
public class DruidConfigurationConfig {



    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }


    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 设置ip白名单
        //servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        //servletRegistrationBean.addInitParameter("deny", "192.168.0.19");
        // 设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
}
