package io.github.wslxm.springbootplus2.starter.websocket.service;

import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.OnlineUserVO;

import java.util.List;

/**
 * @author wangsong
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 */
public interface WebsocketService {

    /**
     * 获取当前在线用户列表
     *
     * @return java.util.List<io.github.wslxm.springbootplus2.websocket.model.vo.OnlineUserVO>
     * @version 1.0.0
     */
    List<OnlineUserVO> getOnlineUsersList();


    /**
     * 发送消息
     *
     * @param dto
     * @version 1.0.0
     */
    void send(WebsocketMsgDTO dto);


    /**
     * 判断指定用户是否在线
     *
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2023/1/17 0017 16:30
     * @version 1.0.0
     */
    Boolean isOnline(String userId);
}


