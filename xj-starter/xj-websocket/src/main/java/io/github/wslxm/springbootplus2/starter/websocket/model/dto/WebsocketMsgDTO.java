package io.github.wslxm.springbootplus2.starter.websocket.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/***
 * websocket客服端-发送消息-入参参数
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/6/30 0030 18:24
 */
@Data
@NoArgsConstructor
public class WebsocketMsgDTO implements Serializable {

    private static final long serialVersionUID = 4019037058499751265L;
    /**
     * 发送人id
     */
    private String form;
    /**
     * 发送人用户名
     */
    private String username;
    /**
     * 接收人用户Id (目标ID,逗号分隔) (所有人使用-ALL)
     */
    private String to;
    /**
     * 发送内容
     */
    private String content;
    /**
     *  扩暂发送内容
     */
    private String extras;

}
