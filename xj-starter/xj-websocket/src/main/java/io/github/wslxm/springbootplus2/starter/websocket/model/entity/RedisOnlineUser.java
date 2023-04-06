package io.github.wslxm.springbootplus2.starter.websocket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class RedisOnlineUser implements Serializable {
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
    private String sessionId;
    /**
     * 连接时间
     */
    private String createTime;

    /**
     * @param userId   用户id
     * @param username 用户名称
     * @param username 用户头像
     */
    public RedisOnlineUser(String userId, String username,String sessionId) {
        this.userId = userId;
        this.username = username;
        this.sessionId = sessionId;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createTime = df.format(LocalDateTime.now());
    }
}
