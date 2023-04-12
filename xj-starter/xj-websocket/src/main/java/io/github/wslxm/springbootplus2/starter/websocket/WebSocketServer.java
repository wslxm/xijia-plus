package io.github.wslxm.springbootplus2.starter.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * websocket 服务 (无需启动)
 */
@SpringBootApplication(scanBasePackages = {"io.github.wslxm.springbootplus2"}, exclude = {DataSourceAutoConfiguration.class})
public class WebSocketServer {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketServer.class, args);
	}
}
