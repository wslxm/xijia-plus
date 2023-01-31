package io.github.wslxm.springbootplus2.starter.websocket.topic;

import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.SendMsgVO;
import io.github.wslxm.springbootplus2.starter.websocket.server.WebsocketServer;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 订阅者
 */
@Component
public class WebsocketMsgListener implements MessageListener {


    private WebsocketServer websocketServer;

    public WebsocketMsgListener(WebsocketServer websocketServer) {
        this.websocketServer = websocketServer;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String messageStr = new String(message.getBody());
        // System.out.println("接收成功: " + messageStr);
        SendMsgVO sendMsgVO = JSON.parseObject(messageStr, SendMsgVO.class);
        // 发送消息
        websocketServer.send(sendMsgVO);
    }
}

