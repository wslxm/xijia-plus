package io.github.wslxm.springbootplus2.starter.websocket.topic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;

/**
 * 发布者配置
 */
@Configuration
public class PubConfig {

    /**
     * 订阅发布的主题
     * @return
     */
    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue-websocket");
    }
}
