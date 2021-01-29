package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:07
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsOrderVO 对象", description = "支付订单/缴费管理表")
public class PetsOrderPageVO extends BaseVo {

    private static final long serialVersionUID = -503731032412000274L;
    
    @ApiModelProperty(notes = "用户id" ,position = 0)
    private String userId;

    @ApiModelProperty(notes = "宠物id" ,position = 1)
    private String petId;

    @ApiModelProperty(notes = "月费表Id", position = 1)
    private String monthFeeId;

    @ApiModelProperty(notes = "支付金额" ,position = 2)
    private BigDecimal money;

    @ApiModelProperty(notes = "当前支付基数 (基数根据申报次数变动)" ,position = 3)
    private Double paymentBase;

    @ApiModelProperty(notes = "支付渠道( 字典code)" ,position = 4)
    private Integer payChannel;

    @ApiModelProperty(notes = "订单状态( 字典code 0-待支付*默认 1-支付失败 2-支付成功/待绑定宠物  3-已绑定宠物 4-订单失效)" ,position = 5)
    private Integer orderState;

    @ApiModelProperty(notes = "订单号" ,position = 6)
    private String orderNo;

    @ApiModelProperty(notes = "业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等)" ,position = 9)
    private Integer businessType;

    @ApiModelProperty(notes = "业务描叙" ,position = 10)
    private String businessDesc;

    @ApiModelProperty(notes = "支付时间" ,position = 11)
    private LocalDateTime payTime;


    //  用户信息
    @ApiModelProperty(notes = "用户姓名" ,position = 12)
    private String fullName;

    @ApiModelProperty(notes = "用户电话" ,position = 13)
    private String phone;

    //  宠物信息
    @ApiModelProperty(notes = "宠物名称" ,position = 14)
    private String petNickname;
    @ApiModelProperty(notes = "宠物类别" ,position = 15)
    private String petType;
    @ApiModelProperty(notes = "宠物品种" ,position = 16)
    private String petBreed;
}

