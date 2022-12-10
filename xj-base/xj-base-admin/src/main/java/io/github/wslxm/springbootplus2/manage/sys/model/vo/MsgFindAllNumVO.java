package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


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
public class MsgFindAllNumVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    /**
     * 已读数量
     */
    private Long haveReadNum;

    /**
     * 未读数量
     */
    private Long unreadNum;

    /**
     * 全部
     */
    private Long allNum;

}

