package com.ws.ldy.starter.pay.model.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * 支付下单参数
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/1/22 0022 10:47
 * @version 1.0.0
 */
@Data
@ToString(callSuper = true)
@SuppressWarnings("all")
public class WxPayOrderDTO implements Serializable {

    private static final long serialVersionUID = 1794442166403619513L;
    private String outTradeNo;     // 必传 -- 交易号
    private Integer totalFee;      // 必传 -- 金额(单位分)
    private String openid;         // 必传 -- 用户openid (交易类型 JSAPI 时必传)
    private String notifyUrl;      // 必传 -- 回调地址
    private String tradeType;      // 非必传 -- 交易类型(默认*JSAPI=公众号/公众号h5/小程序支付 | NATIVE --Native支付 | APP --app支付 |  MWEB --H5支付
    private String body;           // 非必传 -- 商品描述( 不传默认=暂无商品描叙 )
    private String productId;      // 非必传 -- 商品Id(Native支付参数,默认生成随机生成)
    //private String spbillCreateIp; // 非必传 -- 客户终端IP( 未传递时默认使用 request.getRemoteHost()获取客户ip )

}
