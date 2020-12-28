package com.ws.ldy.others.wechat.pay.model.vo;


import com.ws.ldy.others.base.model.Convert;
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
public class WxPayMpOrderResultVO extends Convert {

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "时间戳")
    private String timeStamp;

    @ApiModelProperty(value = "随机字符串")
    private String nonceStr;

    // 由于package为java保留关键字，因此改为packageValue.
    @ApiModelProperty(value = "订单详情扩展字符串")
    private String packageVal;

    @ApiModelProperty(value = "签名方式")
    private String signType;

    @ApiModelProperty(value = "签名")
    private String paySign;
}
