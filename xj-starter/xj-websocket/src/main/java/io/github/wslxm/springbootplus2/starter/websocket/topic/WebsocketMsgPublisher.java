package io.github.wslxm.springbootplus2.starter.websocket.topic;

import io.github.wslxm.springbootplus2.starter.websocket.model.vo.SendMsgVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发布者
 */
@Component
public class WebsocketMsgPublisher {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ChannelTopic topic;

    public void sendMsg(SendMsgVO sendMsgVO ) {
        redisTemplate.convertAndSend(topic.getTopic(),  sendMsgVO);
    }

}
