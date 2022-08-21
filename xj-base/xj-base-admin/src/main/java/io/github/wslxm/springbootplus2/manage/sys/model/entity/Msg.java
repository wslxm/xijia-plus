package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-09-23 10:40:23
 */
@Data
@ToString(callSuper = true)
@TableName("t_sys_msg")
@ApiModel(value = "Msg 对象", description = "订单-->及时消息通知表")
public class Msg extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "消息接收人" ,position = 0)
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(notes = "内容( 根据消息类型区分数据-string || json)" ,position = 1)
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(notes = "通知终端: 1-用户端信息 2-管理端消息" ,position = 2)
    @TableField(value = "user_type")
    private Integer userType;

    @ApiModelProperty(notes = "消息类型:  1-系统通知  2-订单业务通知 " ,position = 3)
    @TableField(value = "msg_type")
    private Integer msgType;

    @ApiModelProperty(notes = "是否已读(0-未读 1-已读)" ,position = 4)
    @TableField(value = "is_read")
    private Integer isRead;

}

