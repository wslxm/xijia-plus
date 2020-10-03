package com.ws.ldy.modules.admin.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(value = "AdminMsgDTO 对象", description = "订单-->及时消息通知表")
public class AdminMsgDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "消息接收人" ,position = 0)
    @Length(min=1, max=32,message = "消息接收人 必须小于32位")
    private String userId;

    @ApiModelProperty(notes = "内容( 根据消息类型区分数据-str || json)" ,position = 1)
    private String content;

    @ApiModelProperty(notes = "通知终端: 1-用户端信息 2-管理端消息" ,position = 2)
    @Range(min=0, max=9L,message = "通知终端: 1-用户端信息 2-管理端消息 必须小于9")
    private Integer userType;

    @ApiModelProperty(notes = "消息类型:  1-系统通知  2-订单业务通知 " ,position = 3)
    @Range(min=0, max=2147483647L,message = "消息类型:  1-系统通知  2-订单业务通知  必须小于2147483647")
    private Integer msgType;

    @ApiModelProperty(notes = "是否已读(0-未读 1-已读)" ,position = 4)
    @Range(min=0, max=9L,message = "是否已读 必须小于9")
    private Integer isRead;

}

