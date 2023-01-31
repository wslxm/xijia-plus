package io.github.wslxm.springbootplus2.starter.websocket.server;

import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.model.entity.OnlineUser;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.OnlineUserVO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.SendMsgVO;
import io.github.wslxm.springbootplus2.starter.websocket.topic.WebsocketMsgPublisher;
import io.github.wslxm.springbootplus2.starter.websocket.util.WebsocketSpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangsong
 * Websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * 同一个账号重复登录, 会被挤下线
 * </P>
 * @ServerEndpoint: socket链接地址
 */
@ServerEndpoint("/websocket/{userId}/{username}")
@Slf4j
@Component
public class WebsocketServer {


    /**
     * 所有用户信息(session + userId + username + createTime  --> 以用户的id为key, 通过用户key来获取用户session进行消息发送)
     */
    private static Map<String, OnlineUser> clients = new ConcurrentHashMap<>();

    /**
     * 获取在线人数
     *
     * @return
     */
    public Map<String, OnlineUser> getClients() {
        return clients;
    }

    /**
     * 获取在线人数
     *
     * @return
     */
    public Integer getClientsSize() {
        return clients.size();
    }

    /**
     * 监听连接（有用户连接，立马到来执行这个方法），session 发生变化
     *
     * @param userId   用户id
     * @param username 用户名
     * @param session  当前用户会话
     * @return void
     * @date 2020/6/30 0030 9:28
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, @PathParam("username") String username, Session session) {
        WebsocketMsgPublisher websocketMsgPublisher = WebsocketSpringContextUtil.getBean(WebsocketMsgPublisher.class);
        // 判断账号是否重复登录
        if (clients.containsKey(userId)) {
            // 被迫下线提示
            String content = "websocket 被迫下线提示, 您的账号在其他地方登录, 及时消息服务将无法正常使用";
            SendMsgVO sendMsgVO = new SendMsgVO(2, userId, username, userId, content, null, clients.size());
            websocketMsgPublisher.sendMsg(sendMsgVO);
            // 踢下线
            try {
                clients.get(userId).getSession().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log.info("重复登录,原用户被迫下线！sessionId：{} userId：{} userName：{} 当前在线人数:{}", session.getId(), userId, username, clients.size());
        } else {
            log.info("有新连接加入！sessionId：{} userId：{} userName：{} 当前在线人数:{}", session.getId(), userId, username, clients.size() + 1);
        }
        // 保存新用户id,用户名,session会话,登录时间
        clients.put(userId, new OnlineUser(userId, username, session));

        // 告诉所有人,我上线了
        String content = "系统消息: " + username + " 上线了";
        SendMsgVO sendMsgVO = new SendMsgVO(1, userId, username, "ALL", content, null, clients.size());

        websocketMsgPublisher.sendMsg(sendMsgVO);

        // 给自己发一条消息：告诉自己现在都有谁在线
        content = JSON.toJSONString(getOnlineUsers());
        sendMsgVO = new SendMsgVO(3, userId, username, userId, content, null, clients.size());
        websocketMsgPublisher.sendMsg(sendMsgVO);
    }


    /**
     * 监听断开连接（有用户退出，会立马到来执行这个方法）
     *
     * @param userId   用户id
     * @param username 用户名
     * @param session  当前用户会话
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId, @PathParam("username") String username, Session session) {
        WebsocketMsgPublisher websocketMsgPublisher = WebsocketSpringContextUtil.getBean(WebsocketMsgPublisher.class);
        // 所有在线用户中去除下线用户
        clients.remove(userId);
        // 告诉所有人(ALL 表示所有人),我下线了
        String content = "系统消息: " + username + " 下线了";
        SendMsgVO sendMsgVO = new SendMsgVO(2, userId, username, "ALL", content, null, clients.size());
        websocketMsgPublisher.sendMsg(sendMsgVO);

        // 日志
        log.info(username + ":已离线！ 当前在线人数" + clients.size());
    }


    /**
     * 异常停止
     *
     * @param userId   用户id
     * @param username 用户名
     * @param session  当前用户会话
     * @param e        异常信息
     */
    @OnError
    public void onError(@PathParam("userId") String userId, @PathParam("username") String username, @PathParam("headPic") String headPic, Session session, Throwable e) {
        log.info("服务端发生了错误" + e);
    }


    /**
     * 监听消息发送（收到客户端的消息立即执行）
     *
     * @param userId   用户id
     * @param username 用户名
     * @param message  传递的消息内容, json数据( to=接收人用户Id  (目标ID,逗号分隔) || content=内容  || content=消息类型)
     * @param session  当前用户会话
     *                 <p>
     *                 // 前端发送内容格式
     *                 ....
     *                 // 拼接参数
     *                 let message = {"content": "测试发送消息", "to": "ALL"}
     *                 ....
     */
    @OnMessage
    public void onMessage(@PathParam("userId") String userId, @PathParam("username") String username, String message, Session session) {
        WebsocketMsgPublisher websocketMsgPublisher = WebsocketSpringContextUtil.getBean(WebsocketMsgPublisher.class);
        // 请求参数（接收人+发送内容）
        try {
            WebsocketMsgDTO sendMsgDTO = JSON.parseObject(message, WebsocketMsgDTO.class);
            log.info("服务器接收到发送消息请求,发送人id={},用户名={}, 接收发送消息={}", userId, username, message);
            // 发送消息
            String content = sendMsgDTO.getContent();
            SendMsgVO sendMsgVO = new SendMsgVO(4, userId, username, sendMsgDTO.getTo(), content, null, clients.size());
            websocketMsgPublisher.sendMsg(sendMsgVO);
        } catch (Exception e) {
            // 给发送人 通知发送错误信息
            log.error("发送的消息格式错误");
            // 发送消息
            String content = "发送的消息格式错误";
            SendMsgVO sendMsgVO = new SendMsgVO(4, userId, username, userId, content, null, clients.size());
            websocketMsgPublisher.sendMsg(sendMsgVO);
        }
    }


    /**
     * 消息发送（ 遍历用户Id , 在通过sendMsg方法发送消息）
     *
     * @param sendMsg：消息内容
     */
    public void send(SendMsgVO sendMsg) {
        List all = Arrays.asList("all", "ALL");
        if (all.contains(sendMsg.getTo())) {
            // 发送消息给所有人
            Set<String> userIds = clients.keySet();
            for (String userId : userIds) {
                this.sendMsg(userId, sendMsg);
            }
        } else {
            // 发送消息给指定人
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
    private Boolean sendMsg(String userId, SendMsgVO sendMsg) {
        // 判断用户是否在线, 在线发送消息推送
        if (clients.containsKey(userId)) {
            try {
                clients.get(userId).getSession().getBasicRemote().sendText(JSON.toJSONString(sendMsg));
                if (sendMsg.getMsgType() != 0) {
                    log.info("websocket用户ID:{} 已连接当前服务: 成功推送信息, 消息：{} ", userId, JSON.toJSONString(sendMsg.toString()));
                }
                return true;
            } catch (IOException e) {
                log.error(e.toString());
                log.info(userId, sendMsg.getUsername() + "上线的时候通知所有人发生了错误");
                return false;
            }
        } else {
            if (sendMsg.getMsgType() != 0) {
                log.info("websocket用户ID:{} 未连接当前服务: 推送信息失败, 消息：{} ", userId, JSON.toJSONString(sendMsg.toString()));
            }
        }
        return false;
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
            OnlineUserVO vo = new OnlineUserVO();
            BeanUtils.copyProperties(onlineUsers, vo);
            onlineUsersVOList.add(vo);
        }
        return onlineUsersVOList;
    }
}