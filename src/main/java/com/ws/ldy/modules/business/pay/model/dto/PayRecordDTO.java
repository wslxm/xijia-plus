package com.ws.ldy.modules.business.pay.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 第三方支付记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjPayRecordDTO 对象", description = "第三方支付记录表")
public class PayRecordDTO extends BaseDto {

    private static final long serialVersionUID = -506257672354533384L;

    @ApiModelProperty(notes = "交易订单号", position = 0)
    private String orderNo;

    @ApiModelProperty(notes = "交易流水号( 支付/退款/放款等)", position = 1)
    private String tradeNo;

    @ApiModelProperty(notes = "平台手续费(元)", position = 2)
    private BigDecimal platformFee;

    @ApiModelProperty(notes = "第三方手续费(元), 为0时,手续费累加在 platform_fee", position = 3)
    private BigDecimal channelFee;

    @ApiModelProperty(notes = "交易总金额( 元)", position = 4)
    private BigDecimal moneyTotal;

    @ApiModelProperty(notes = "剩余金额( 如存在子商户, 则为子商户实际收入)", position = 4)
    private BigDecimal moneySurplus;

    @ApiModelProperty(notes = "交易状态( 0-已发起 1-回调成功(临时状态) 2-交易失败 3-交易成功 )", position = 5)
    private Integer payState;

    @ApiModelProperty(notes = "支付渠道( 字典code 如1-支付宝 2-微信 3-银行卡 等)", position = 6)
    private Integer payChannel;

    @ApiModelProperty(notes = "支付类型( 字典code 如 1-支付 2-充值 3-退款 等)", position = 7)
    private Integer payType;

    @ApiModelProperty(notes = "业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等)", position = 8)
    private Integer businessType;

    @ApiModelProperty(notes = "业务描叙( 追踪具体业务)", position = 9)
    private String businessDesc;

    @ApiModelProperty(notes = "请求数据", position = 10)
    private String requestData;

    @ApiModelProperty(notes = "响应数据", position = 11)
    private String responseData;

    @ApiModelProperty(notes = "回调数据", position = 12)
    private String callbackData;

}

