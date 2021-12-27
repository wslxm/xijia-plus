package io.github.wslxm.springbootplus2.basepay.manage.model.dto;


import io.github.wslxm.springbootplus2.core.enums.Admin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  @author wangsong
 */
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
    @ApiModelProperty(value = "商户订单号")
    private String orderNo;
    @ApiModelProperty(value = "商户交易号/tradeNo")
    private String transactionNo;
    @ApiModelProperty(value = "商品描述(建议传递, 不传默认=暂无商品描叙")
    private String body;
    @ApiModelProperty(value = "业务类型（当前系统字典code）")
    private Admin.PayBusiness payBusiness;
    /*** 请求必传 */

    /*** 微信jsapi 支付额外必传 */
    @ApiModelProperty(value = "用户openid")
    private String wxOpenid;
    @ApiModelProperty(value = "默认JSAPI -- 交易类型( 默认= JSAPI=公众号/公众号h5/小程序支付 | NATIVE --Native支付 | APP --app支付 |  MWEB --H5支付")
    private String wxTradeType;
    /*** 微信支付必传 */
}
