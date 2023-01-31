package io.github.wslxm.springbootplus2.starter.websocket.topic;

import io.github.wslxm.springbootplus2.starter.websocket.server.WebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 订阅者配置
 */
@Configuration
public class SubConfig {

    @Autowired
    private WebsocketServer websocketServer;

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new WebsocketMsgListener(websocketServer));
    }

    // 如果同时配置了key过期监听,请添加@Primary注解
    @Bean
    @Primary
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(messageListener(), new ChannelTopic("pubsub:queue-websocket"));
        return container;
    }
}
