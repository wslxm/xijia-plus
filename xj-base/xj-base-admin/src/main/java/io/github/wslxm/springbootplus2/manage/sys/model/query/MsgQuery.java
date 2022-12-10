package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;


import lombok.Data;
import lombok.ToString;

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
public class MsgQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    /**
     * 是否已读(0-未读 1-已读)
     */
    private Integer isRead;

    /**
     * 是否只查询当前登录人的信息, 默认true
     */
    private Boolean isLoginUser;

    /**
     * 查询指定消息集
     */
    private String msgTypes;

    /**
     * 排除查询指定消息集
     */
    private String noMsgTypes;


}

