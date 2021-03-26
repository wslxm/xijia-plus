package com.ws.ldy.modules.sys.pay.model.dto;


import com.ws.ldy.enums.Pay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@ApiModel(value = "PayOrderDTO 对象", description = "支付下单需要用到的参数")
public class PayOrderDTO implements Serializable {

    private static final long serialVersionUID = -3811735459657950970L;
    /*** 请求必传 */
    @ApiModelProperty(value = "订单总金额(单位元)")
    private BigDecimal moneyTotal;
    @ApiModelProperty(value = "平台手续费(单位元)-没有可传0")
    private BigDecimal platformFee;
    @ApiModelProperty(value = "支付渠道手续费(单位元)-没有可传0, 传0可能会影响计算商家的实际收入")
    private BigDecimal channelFee;
    @ApiModelProperty(value = "商户订单号(如不传将自动生成并在响应信息中会返回)")
    private String orderNo;
    @ApiModelProperty(value = "商品描述(建议传递, 不传默认=暂无商品描叙")
    private String body;
    @ApiModelProperty(value = "业务类型")
    private Pay.PayBusiness payBusiness;
    /*** 请求必传 */

    /*** 微信支付额外必传 */
    @ApiModelProperty(value = "用户openid")
    private String wxOpenid;
    @ApiModelProperty(value = "默认JSAPI -- 交易类型( 默认= JSAPI=公众号/公众号h5/小程序支付 | NATIVE --Native支付 | APP --app支付 |  MWEB --H5支付")
    private String wxTradeType;
    /*** 微信支付必传 */
}
