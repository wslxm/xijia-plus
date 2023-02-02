package io.github.wslxm.springbootplus2.starter.websocket.service;

import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.OnlineUserVO;

import java.util.Map;

/**
 * @author wangsong
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 */
public interface WebsocketService {

    /**
     * 发送消息
     *
     * @param dto
     * @version 1.0.0
     */
    void send(WebsocketMsgDTO dto);

    /**
     * 获取当前在线列表
     *
     * @return 用户map列表, ket = 用户id
     * @version 1.0.0
     */
    Map<Object, OnlineUserVO> getOnlineUsers();

    /**
     * 获取在线人数
     *
     * @return int
     * @version 1.0.0
     */
    Integer getOnlineCount();


    /**
     * 判断指定用户是否在线
     * @return true 在线 / false 不在线
     */
    boolean isOnline(String userId);

}


