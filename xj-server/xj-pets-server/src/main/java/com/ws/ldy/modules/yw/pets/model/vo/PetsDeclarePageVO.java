package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 申报信息分页查询 额外返回参数
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-31 17:41:21
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclarePageVO 对象", description = "申报信息表")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetsDeclarePageVO extends BaseVo {

    private static final long serialVersionUID = -7878070232329058334L;


    @ApiModelProperty(notes = "申报金额", position = 1)
    private String declareMoney;

    @ApiModelProperty(notes = "实发金额", position = 1)
    private String paidInAmount;

    @ApiModelProperty(notes = "申请时间", position = 1)
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "审核通过时间", position = 2)
    private LocalDateTime passTime;

    @ApiModelProperty(notes = "状态", position = 3)
    private Integer state;

    @ApiModelProperty(notes = "支付渠道", position = 4)
    private Integer payChannel;

    @ApiModelProperty(notes = "审核备注", position = 5)
    private String remarks;


    @ApiModelProperty(notes = "用户姓名", position = 21)
    private String fullName;

    @ApiModelProperty(notes = "用户电话", position = 22)
    private String phone;

    // 宠物信息
    @ApiModelProperty(notes = "宠物名称", position = 23)
    private String nickname;

    @ApiModelProperty(notes = "宠物类型", position = 24)
    private String type;

    @ApiModelProperty(notes = "宠物品种", position = 25)
    private String breed;

    @ApiModelProperty(notes = "宠物头像 ", position = 26)
    private String headPic;

    @ApiModelProperty(notes = "宠物年龄 ", position = 27)
    private Double age;

    @ApiModelProperty(notes = "宠物性别 (字典 code)  ", position = 28)
    private Integer sex;

    @ApiModelProperty(notes = "支付时间", position = 29)
    private LocalDateTime payTime;

    @ApiModelProperty(notes = "支付订单号", position = 29)
    private String orderNo;
}
