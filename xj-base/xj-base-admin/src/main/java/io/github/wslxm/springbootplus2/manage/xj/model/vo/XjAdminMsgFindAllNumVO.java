package io.github.wslxm.springbootplus2.manage.xj.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAdminMsgFindAllNumVO 对象", description = "订单-->及时消息通知表")
public class XjAdminMsgFindAllNumVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "已读数量", position = 0)
    private Integer haveReadNum;

    @ApiModelProperty(notes = "未读数量", position = 1)
    private Integer unreadNum;

    @ApiModelProperty(notes = "全部", position = 2)
    private Integer allNum;

}

