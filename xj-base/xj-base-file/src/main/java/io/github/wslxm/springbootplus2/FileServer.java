package io.github.wslxm.springbootplus2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件服务  (无需启动)
 * @author wangsong
 * @date 2022/5/26 17:30
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FileServer {

    public static void main(String[] args) {
        SpringApplication.run(FileServer.class, args);
    }
}
