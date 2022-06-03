package io.github.wslxm.springbootplus2.starter.websocket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.websocket.Session;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 *   当前在线列表信息
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/1 0001 11:58 
 */
@Data
@AllArgsConstructor
public class OnlineUser implements Serializable {
    private static final long serialVersionUID = 6239537958223115071L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户会话 (使用该对象进行消息发送)
     */
    private Session session;
    /**
     * 连接时间
     */
    private String createTime;

    /**
     * @param userId   用户id
     * @param username 用户名称
     * @param username 用户头像
     * @param session  用户session 回话信息
     */
    public OnlineUser(String userId, String username,  Session session) {
        this.userId = userId;
        this.username = username;
        this.session = session;
        //
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createTime = df.format(LocalDateTime.now());
    }
}
