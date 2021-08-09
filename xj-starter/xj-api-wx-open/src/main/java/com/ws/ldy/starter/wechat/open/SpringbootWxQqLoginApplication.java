package com.ws.ldy.starter.wechat.open;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
public class SpringbootWxQqLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWxQqLoginApplication.class, args);
    }

}
