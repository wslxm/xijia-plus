package io.github.wslxm.springbootplus2.starter.websocket.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  @author wangsong
 *   在线列表信息返回
 */
@Data
public class OnlineUserVO implements Serializable {

    private static final long serialVersionUID = -5554343409782021987L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 连接时间
     */
    private String createTime;
}
