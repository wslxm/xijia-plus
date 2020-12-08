package com.ws.ldy.others.websocket.model.dto;

import com.ws.ldy.others.base.model.Convert;
import lombok.Data;


/***
 * websocket客服端-发送消息-入参参数
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/6/30 0030 18:24
 */
@Data
public class SendMsgDTO extends Convert {
    /**
     * 接收人用户Id (目标ID,逗号分隔) (所有人使用-ALL)
     */
    private String to;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 消息类型(1-心跳检测 2-消息内容(文字,base64图片表情等))
     */
    private Integer type;

    /**
     * @param to      接收人用户Id (目标ID,逗号分隔) (所有人使用-ALL)
     * @param content 发送内容
     * @param type    消息类型
     */
    public SendMsgDTO(String to, String content, Integer type) {
        this.to = to;
        this.type = type;
        this.content = content;
    }

    public SendMsgDTO() {
    }
}
