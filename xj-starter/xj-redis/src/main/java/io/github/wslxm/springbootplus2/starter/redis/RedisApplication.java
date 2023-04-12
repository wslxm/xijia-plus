package io.github.wslxm.springbootplus2.starter.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * redis 服务 (无需启动)
 *
 * @author wangsong
 * @date 2022/5/26 17:30
 */
@SpringBootApplication(scanBasePackages = {"io.github.wslxm.springbootplus2"}, exclude = {DataSourceAutoConfiguration.class})
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
