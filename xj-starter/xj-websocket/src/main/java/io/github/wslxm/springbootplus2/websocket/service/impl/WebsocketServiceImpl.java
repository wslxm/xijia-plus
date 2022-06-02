package io.github.wslxm.springbootplus2.websocket.service.impl;

import io.github.wslxm.springbootplus2.websocket.service.WebsocketService;
import io.github.wslxm.springbootplus2.websocket.model.vo.OnlineUserVO;
import io.github.wslxm.springbootplus2.websocket.model.vo.SendMsgVO;
import io.github.wslxm.springbootplus2.websocket.server.WebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *  @author wangsong
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 *   /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 *
 * @ServerEndpoint: socket链接地址
 */
@Service
public class WebsocketServiceImpl implements WebsocketService {


    @Autowired
    private WebsocketServer websocketServer;

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
    public void send(String form, String username ,String to, String content, String extras) {
        // 发送消息
        websocketServer.send(new SendMsgVO(4, form, username,to, content, extras,websocketServer.getClientsSize()));
    }
}