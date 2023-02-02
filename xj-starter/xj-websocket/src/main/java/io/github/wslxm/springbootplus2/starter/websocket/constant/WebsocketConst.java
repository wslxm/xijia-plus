package io.github.wslxm.springbootplus2.starter.websocket.constant;

/**
 * 常量类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2023/2/2 0002 10:17 
 * @version 1.0.0
 */
public interface WebsocketConst {

    /**
     * 消息类型
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2023/2/2 0002 10:21
     * @version 1.0.0
     */
    interface MsgType {
        /**
         * 心跳检测
         */
        int V0 = 0;
        /**
         * 代表普通消息通知
         */
        int V1 = 1;
    }
}
