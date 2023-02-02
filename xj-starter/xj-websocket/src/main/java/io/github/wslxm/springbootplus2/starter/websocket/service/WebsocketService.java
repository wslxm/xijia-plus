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
     * 获取当前
     *
     * @return 用户map列表, ket = Id
     * @version 1.0.0
     */
    Map<Object, OnlineUserVO> getOnlineUsersList();


    /**
     * 获取在线人数
     *
     * @return int
     * @version 1.0.0
     */
    Integer getOnlineCount();


    /**
     * 判断指定用户是否在线
     *
     * @author wangsong
     * @email 1720696548@qq.com
     */
    Boolean isOnline(String userId);


    /**
     * 发送消息
     *
     * @param dto
     * @version 1.0.0
     */
    void send(WebsocketMsgDTO dto);

}


