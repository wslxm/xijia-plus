package com.ws.ldy.others.websocket.model.vo;

import com.ws.ldy.others.base.model.convert.Convert;
import lombok.Data;

/**
 * TODO  在线列表信息返回
 */
@Data
public class OnlineUserVO extends Convert {

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
