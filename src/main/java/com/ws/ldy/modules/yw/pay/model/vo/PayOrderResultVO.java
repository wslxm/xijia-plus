package com.ws.ldy.modules.yw.pay.model.vo;


import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信创建订单响应信息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/12/27 0027 9:32
 * @version 1.0.0
 */
@Data
@ApiModel(value = "WxPayMpOrderResultVO 对象", description = "jsapi 前端调起支付需要的参数")
public class PayOrderResultVO extends Convert {

    private static final long serialVersionUID = -1106986618454127461L;

    @ApiModelProperty(value = "支付订单号")
    private String orderNo;

    @ApiModelProperty(value = "微信支付返回 -- 应用id")
    private String appId;

    @ApiModelProperty(value = "微信支付返回 -- 时间戳")
    private String timeStamp;

    @ApiModelProperty(value = "微信支付返回 -- 随机字符串")
    private String nonceStr;

    @ApiModelProperty(value = "微信支付返回 -- 订单详情扩展字符串")
    private String packageValue;

    @ApiModelProperty(value = "微信支付返回 -- 签名方式")
    private String signType;

    @ApiModelProperty(value = "微信支付返回 -- 签名")
    private String paySign;
}
