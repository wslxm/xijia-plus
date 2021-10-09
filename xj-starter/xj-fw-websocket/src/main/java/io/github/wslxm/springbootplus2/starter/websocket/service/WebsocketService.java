package io.github.wslxm.springbootplus2.starter.websocket.service;

import io.github.wslxm.springbootplus2.starter.websocket.model.vo.OnlineUserVO;

import java.util.List;

/**
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 */
public interface WebsocketService {

    /**
     * 获取当前在线人数
     *
     * @return
     */
    public Integer getOnlineCount();


    /**
     * 获取当前在线用户列表
     *
     * @return
     */
    public List<OnlineUserVO> getOnlineUsersList();

    /**
     * 发送消息
     *
     * @param form     发送人id
     * @param username 发送人用户名
     * @param to       接收人id（多个逗号分隔）
     * @param content  发送内容
     * @param extras   扩暂发送内容
     * @return
     */
    public Boolean send(String form, String username,String headPic, String to, String content, String extras);
}


