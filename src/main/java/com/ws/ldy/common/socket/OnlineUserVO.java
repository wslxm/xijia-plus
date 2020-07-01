package com.ws.ldy.common.socket;

import com.ws.ldy.base.convert.Convert;
import lombok.Data;

/**
 * TODO  在线用户接收端返回参数
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
