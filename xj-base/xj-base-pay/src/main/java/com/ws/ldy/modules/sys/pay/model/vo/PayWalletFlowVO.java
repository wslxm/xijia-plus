package com.ws.ldy.modules.sys.pay.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsWalletFlowVO 对象", description = "账单/流水/支付流水表")
public class PayWalletFlowVO extends BaseVo {

    private static final long serialVersionUID = -503731388604878857L;

    @ApiModelProperty(notes = "账号id=用户id/ 平台为0*默认", position = 0)
    private String userId;

    @ApiModelProperty(notes = "订单号", position = 1)
    private String orderNo;

    @ApiModelProperty(notes = "交易后总金额( 元)", position = 4)
    private BigDecimal moneyAfter;

    @ApiModelProperty(notes = "金额( 不论支出收入都是正数)", position = 5)
    private BigDecimal money;

    @ApiModelProperty(notes = "流水类型(1-收入 2-支出)", position = 6)
    private Integer walletType;

    @ApiModelProperty(notes = "业务类型( 字段code)", position = 7)
    private Integer businessType;

    @ApiModelProperty(notes = "业务描叙", position = 8)
    private String businessDesc;

}

