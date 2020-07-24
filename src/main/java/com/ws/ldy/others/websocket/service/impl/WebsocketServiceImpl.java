package com.ws.ldy.others.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.common.utils.JsonUtil;
import com.ws.ldy.others.websocket.model.dto.SendMsgDTO;
import com.ws.ldy.others.websocket.model.entity.OnlineUser;
import com.ws.ldy.others.websocket.model.vo.OnlineUserVO;
import com.ws.ldy.others.websocket.model.vo.SendMsgVO;
import com.ws.ldy.others.websocket.service.WebsocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 *
 * @ServerEndpoint: socket链接地址
 */
@ServerEndpoint("/websocket/{userId}/{username}")
@Service
@Slf4j
public class WebsocketServiceImpl implements WebsocketService {
    /**
     * 在线人数  //使用原子类AtomicInteger, --->  static并发会产生线程安全问题，    //public  static Integer onlineNumber = 0;
     */
    public static AtomicInteger onlineNumber = new AtomicInteger(0);
    /**
     * 所有用户信息(session + userId + username + createTime  --> 以用户的id为key, 通过用户key来获取用户session进行消息发送)
     */
    public static Map<String, OnlineUser> clients = new ConcurrentHashMap<>();

    //================================================================================
    //================================================================================
    //===============================  监听方法 =======================================
    //================================================================================
    //================================================================================

    /**
     * 监听连接（有用户连接，立马到来执行这个方法），session 发生变化
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, @PathParam("username") String username, Session session) {
        // 自增1
        onlineNumber.getAndIncrement();
        // 保存新用户id,用户名,session会话,登录时间
        clients.put(userId, new OnlineUser(userId, username, session));

        // 告诉所有人,我上线了
        String content = "系统消息:" + username + " 上线了";
        this.send(new SendMsgVO(1, userId, username, "ALL", content, null));

        // 给自己发一条消息：告诉自己现在都有谁在线
        this.send(new SendMsgVO(3, userId, username, userId, JSON.toJSONString(getOnlineUsers()), null));
        log.info("有新连接加入！sessionId：{} userId：{} userName：{} 当前在线人数:{}", session.getId(), userId, username, onlineNumber);
    }


    /**
     * 监听断开连接（有用户退出，会立马到来执行这个方法）
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId, @PathParam("username") String username, Session session) {
        // 自减1
        onlineNumber.getAndDecrement();
        // 所有在线用户中去除下线用户
        clients.remove(userId);
        // 告诉所有人,我下线了
        String content = "系统消息:" + username + " 下线了";
        this.send(new SendMsgVO(2, userId, username, "ALL", content, null));
        // 日志
        log.info(username + ":已离线！ 当前在线人数" + onlineNumber);
    }

    /**
     * 异常停止
     */
    @OnError
    public void onError(@PathParam("userId") String userId, @PathParam("username") String username, Session session, Throwable error) {
        error.printStackTrace();
        log.info("服务端发生了错误" + error.getMessage());
    }

    /**
     * 监听消息发送（收到客户端的消息立即执行）
     */
    @OnMessage
    public void onMessage(@PathParam("userId") String userId, @PathParam("username") String username, String message, Session session) {
        log.info("服务器接收到发送消息请求,发送人id={},用户名={}, 接收发送消息={}", userId, username, message);
        // 请求参数（接收人+发送内容）
        SendMsgDTO sendMsgDTO = JsonUtil.parseEntity(message, SendMsgDTO.class);
        // 发送消息
        this.send(new SendMsgVO(4, userId, username, sendMsgDTO.getTo(), sendMsgDTO.getContent(), null));
    }


    /**
     * 消息发送（ 遍历用户Id , 在通过sendMsg方法发送消息）
     *
     * @param sendMsg：消息内容
     */
    private void send(SendMsgVO sendMsg) {
        if ("ALL".equals(sendMsg.getTo()) || "all".equals(sendMsg.getTo())) {
            // 发送消息给所有人
            Set<String> userIds = clients.keySet();
            for (String userId : userIds) {
                this.sendMsg(userId, sendMsg);
            }
        } else {
            //发送消息给指定人
            String[] userIds = sendMsg.getTo().split(",");
            for (String userId : userIds) {
                this.sendMsg(userId, sendMsg);
            }
        }
    }


    /**
     * 消息发送(最后发送, 在send方法中循环用户Id 列表依次发送消息给指定人)
     * <p>
     * // 消息发送（同步:getBasicRemote 异步:getAsyncRemote）
     * </P>
     *
     * @param userId  消息接收人ID , onlineUsers 的 key
     * @param sendMsg 消息内容
     */
    private void sendMsg(String userId, SendMsgVO sendMsg) {
        // 判断用户是否在线, 在线发送消息推送
        if (clients.containsKey(userId)) {
            try {
                clients.get(userId).getSession().getBasicRemote().sendText(JSON.toJSONString(sendMsg));
            } catch (IOException e) {
                e.printStackTrace();
                log.info(userId, sendMsg.getUsername() + "上线的时候通知所有人发生了错误");
            }
        }
    }

    /**
     * 获取当前在线列表
     * <p>
     * 获取当前在线列表, 把onlineUsers 转到 OnlineUsersVO返回
     * </p>
     *
     * @return
     */
    public synchronized List<OnlineUserVO> getOnlineUsers() {
        List<OnlineUserVO> onlineUsersVOList = new ArrayList<>();
        for (OnlineUser onlineUsers : clients.values()) {
            onlineUsersVOList.add(onlineUsers.convert(OnlineUserVO.class));
        }
        return onlineUsersVOList;
    }


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
    public Integer getOnlineCount() {
        return onlineNumber.get();
    }


    /**
     * 获取当前在线用户信息
     *
     * @return
     */
    public List<OnlineUserVO> getOnlineUsersList() {
        return getOnlineUsers();
    }

    /**
     * 发送消息 (单向通知发送，不可回复)
     *
     * @param form     发送人id
     * @param username 发送人用户名
     * @param to       接收人id（多个逗号分隔）
     * @param content  发送内容
     * @param content  发送内容
     * @param content  扩暂发送内容
     * @return
     */
    public List<OnlineUserVO> send(String form, String username, String to, String content, String extras) {
        // 发送消息
        this.send(new SendMsgVO(4, form, username, to, content, extras));
        return getOnlineUsers();
    }
}