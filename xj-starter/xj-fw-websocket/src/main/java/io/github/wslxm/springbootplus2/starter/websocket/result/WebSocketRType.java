package io.github.wslxm.springbootplus2.starter.websocket.result;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 11:16
 * @return
 */

@SuppressWarnings("all")
@Getter
@NoArgsConstructor
public enum WebSocketRType {

    // Websocket
    WEBSOCKET_CONFIG_ERROR(10040, "请检查webSocket配置信息是否配置"),
    ;

    private Integer value;
    private String msg;

    WebSocketRType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
