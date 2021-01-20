package com.ws.ldy.modules.third.wechat.pay.model.dto;


import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@SuppressWarnings("all")
public class WxPayOrderDTO extends Convert {

    private static final long serialVersionUID = 1794442166403619513L;
    private String outTradeNo;  // 必传 -- 商户订单号
    private Integer totalFee;   // 必传 -- 金额(单位分)
    private String openid;      // 必传 -- 用户openid (交易类型 JSAPI 时必传)
    private String notifyUrl;   // 必传 -- 回调地址
    private String tradeType;      // 非必传 -- 交易类型(默认*JSAPI=公众号/公众号h5/小程序支付 | NATIVE --Native支付 | APP --app支付 |  MWEB --H5支付
    private String body;           // 非必传 -- 商品描述( 不传默认=暂无商品描叙 )
    private String spbillCreateIp; // 非必传 -- 客户终端IP( 未传递时默认使用 request.getRemoteHost()获取客户ip )

}
