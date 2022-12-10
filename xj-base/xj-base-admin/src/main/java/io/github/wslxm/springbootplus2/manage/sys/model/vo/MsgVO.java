package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 订单-->及时消息通知表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@Data
@ToString(callSuper = true)
public class MsgVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    /**
     * 消息接收人"
     */
    private String userId;

    /**
     * 内容( 根据消息类型区分数据-str || json)"
     */
    private String content;

    /**
     * 通知终端: 1-用户端信息 2-管理端消息"
     */
    private Integer userType;

    /**
     * 消息类型:  1-系统通知  2-订单业务通知 "
     */
    private Integer msgType;

    /**
     * 是否已读(0-未读 1-已读)"
     */
    private Integer isRead;

    /**
     * 时间"
     */
    private LocalDateTime createTime;

}

