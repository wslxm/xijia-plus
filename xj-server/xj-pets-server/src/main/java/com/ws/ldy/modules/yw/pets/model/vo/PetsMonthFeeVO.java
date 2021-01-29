package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * 月费表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:43
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsMonthFeeVO 对象", description = "月费表")
public class PetsMonthFeeVO extends BaseVo {

    private static final long serialVersionUID = -503730931484463108L;

    @ApiModelProperty(notes = "月数( 1/3/6/12)", position = 0)
    private Integer monthNum;

    @ApiModelProperty(notes = "月费-对应月数( 30/90/180/360)", position = 1)
    private BigDecimal monthFee;

    @ApiModelProperty(notes = "描叙", position = 2)
    private String monthDesc;

    @ApiModelProperty(notes = "当前基数(用户端,支付时获取月费信息计算,同时根据基数调整 monthFee 金额)", position = 2)
    private Double paymentBase;

}

