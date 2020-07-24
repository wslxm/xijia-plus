package com.ws.ldy.others.websocket.service;

import com.ws.ldy.others.websocket.model.vo.OnlineUserVO;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 */
public interface WebsocketService {


    //================================================================================
    //================================================================================
    //===============================  监听方法 =======================================
    //================================================================================
    //================================================================================

    /**
     * 监听连接（有用户连接，立马到来执行这个方法），session 发生变化
     *
     * @param userId   用户id
     * @param username 用户名
     * @param session  当前用户会话
     * @return void
     * @date 2020/6/30 0030 9:28
     */
    public void onOpen(@PathParam("userId") String userId, @PathParam("username") String username, Session session);


    /**
     * 监听断开连接（有用户退出，会立马到来执行这个方法）
     *
     * @param userId   用户id
     * @param username 用户名
     * @param session  当前用户会话
     */
    public void onClose(@PathParam("userId") String userId, @PathParam("username") String username, Session session);


    /**
     * 异常停止
     *
     * @param userId   用户id
     * @param username 用户名
     * @param session  当前用户会话
     * @param error    异常信息
     */
    public void onError(@PathParam("userId") String userId, @PathParam("username") String username, Session session, Throwable error);

    /**
     * 监听消息发送（收到客户端的消息立即执行）
     *
     * @param userId   用户id
     * @param username 用户名
     * @param message  传递的消息内容, json数据( to=接收人用户Id  (目标ID,逗号分隔) || content=内容)
     * @param session  当前用户会话
     *
     *                 <p>
     *                 // 前端发送内容格式
     *                 ....
     *                 // 拼接参数
     *                 let message = { "content": content, "to": to };
     *                 // 发送数据
     *                 webSocket.send(JSON.stringify(message));
     *                 ....
     *                 </P>
     */
    public void onMessage(@PathParam("userId") String userId, @PathParam("username") String username, String message, Session session);


    //================================================================================
    //================================================================================
    //=======================  service方法(http接口调用操作) ============================
    //================================================================================
    //================================================================================

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
     * @param extras  扩暂发送内容
     * @return
     */
    public List<OnlineUserVO> send(String form, String username, String to, String content, String extras);
}


