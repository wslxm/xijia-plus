package com.ws.ldy.modules.third.websocket.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;

/**
 *   在线列表信息返回
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
