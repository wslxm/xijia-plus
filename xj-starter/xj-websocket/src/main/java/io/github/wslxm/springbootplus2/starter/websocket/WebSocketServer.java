package io.github.wslxm.springbootplus2.starter.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * websocket
 * <P>
 *     这里因为我的 swagger 也在io.github.wslxm.springbootplus2 这个包下，就不配用单独配扫描了
 * </P>
 * @author wangsong
 * @date 2022/5/26 17:30
 * @return
 */
@SpringBootApplication(scanBasePackages = {"io.github.wslxm.springbootplus2"})
public class WebSocketServer {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketServer.class, args);
	}
}
