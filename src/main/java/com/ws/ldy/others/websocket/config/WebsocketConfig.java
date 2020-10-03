package com.ws.ldy.others.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * WebSocket 配置类
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/6/30 0030 9:26
 */
@Configuration
public class WebsocketConfig {

    /**
     * 服务器节点
     * <p>
     * 如果使用独立的servlet容器，而不是直接使用springboot的内置容器，就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
