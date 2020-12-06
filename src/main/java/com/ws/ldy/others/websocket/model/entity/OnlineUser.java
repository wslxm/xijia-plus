package com.ws.ldy.others.websocket.model.entity;

import com.ws.ldy.others.base.model.Convert;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.websocket.Session;
import java.time.LocalDateTime;

/***
 *   当前在线列表信息
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/1 0001 11:58 
 */
@Data
@AllArgsConstructor
public class OnlineUser extends Convert {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户头像
     */
    private String headPic;
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
    public OnlineUser(String userId, String username,String  headPic, Session session) {
        this.userId = userId;
        this.username = username;
        this.headPic = headPic;
        this.session = session;
        this.createTime = LocalDateTimeUtil.parse(LocalDateTime.now());
    }
}
