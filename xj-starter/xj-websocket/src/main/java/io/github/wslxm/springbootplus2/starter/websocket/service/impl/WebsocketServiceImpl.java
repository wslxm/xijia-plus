package io.github.wslxm.springbootplus2.starter.websocket.service.impl;

import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.OnlineUserVO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.SendMsgVO;
import io.github.wslxm.springbootplus2.starter.websocket.server.WebsocketServer;
import io.github.wslxm.springbootplus2.starter.websocket.service.WebsocketService;
import io.github.wslxm.springbootplus2.starter.websocket.topic.WebsocketMsgPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author wangsong
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 * @ServerEndpoint: socket链接地址
 */
@Service
public class WebsocketServiceImpl implements WebsocketService {


    @Autowired
    private WebsocketServer websocketServer;

    @Autowired
    private WebsocketMsgPublisher msgPublisher;

    /**
     * 获取当前在线人数
     *
     * @return
     */
    @Override
    public Integer getOnlineCount() {
        return websocketServer.getClientsSize();
    }

    /**
     * 获取当前在线用户信息
     *
     * @return
     */
    @Override
    public List<OnlineUserVO> getOnlineUsersList() {
        return websocketServer.getOnlineUsers();
    }

    /**
     * 发送消息 (单向通知发送，不可回复)
     *
     * @param form     发送人id
     * @param username 发送人用户名
     * @param to       接收人id（多个逗号分隔）,所有人使用-ALL)
     * @param content  发送内容
     * @param content  扩暂发送内容
     * @return
     */
    @Override
    public void send(WebsocketMsgDTO dto) {
        SendMsgVO sendMsgVO = new SendMsgVO();
        sendMsgVO.setMsgType(4);
        sendMsgVO.setFrom(dto.getForm());
        sendMsgVO.setUsername(dto.getUsername());
        sendMsgVO.setTo(dto.getTo());
        sendMsgVO.setContent(dto.getContent());
        sendMsgVO.setExtras(dto.getExtras());
        sendMsgVO.setOnlineNum(websocketServer.getClientsSize());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sendMsgVO.setCreateTime(df.format(LocalDateTime.now()));
        // 发送 redis 订阅消息
        msgPublisher.sendMsg(sendMsgVO);
    }


    @Override
    public Boolean isOnline(String userId) {
        return websocketServer.getClients().containsKey(userId);
    }
}