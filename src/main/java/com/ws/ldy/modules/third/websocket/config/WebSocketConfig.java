package com.ws.ldy.modules.third.websocket.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;


/**
 * WebSocket 配置类
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/6/30 0030 9:26
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebSocketConfig implements ServletContextInitializer {
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

    /**
     * 配置websocket内容传输大小，1M (如果太大会导致堆空间不足，每个连接都会占用 1M堆空间)
     * @author wangsong
     * @param servletContext
     * @date 2020/12/6 0006 13:49
     * @return void
     * @version 1.0.0
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.addListener(WebAppRootListener.class);
        servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize", "1024000");
        servletContext.setInitParameter("org.apache.tomcat.websocket.binaryBufferSize", "1024000");
    }
}
