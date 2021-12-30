package io.github.wslxm.springbootplus2.manage.xj.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
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
@ApiModel(value = "XjAdminMsgQuery 对象", description = "订单-->及时消息通知表")
public class XjAdminMsgQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "是否已读(0-未读 1-已读)", position = 1)
    private Integer isRead;

    @ApiModelProperty(value = "是否只查询当前登录人的信息, 默认true", position = 2)
    private Boolean isLoginUser ;

    @ApiModelProperty(value = "查询指定消息集", position = 3)
    private String msgTypes;

    @ApiModelProperty(value = "排除查询指定消息集", position = 4)
    private String noMsgTypes;


}

