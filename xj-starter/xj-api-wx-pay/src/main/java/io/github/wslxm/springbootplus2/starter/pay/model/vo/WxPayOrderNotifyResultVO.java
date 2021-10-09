package io.github.wslxm.springbootplus2.starter.pay.model.vo;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyCoupon;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
  * 支付回调参数
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/1/5 0005 14:52
  * @version 1.0.0
  */
@Data
@ToString
public class WxPayOrderNotifyResultVO extends WxBaseResurtVO {

    private static final long serialVersionUID = 5389718115223345496L;
    private String promotionDetail;
    private String deviceInfo;
    private String openid;
    private String isSubscribe;
    private String subOpenid;
    private String subIsSubscribe;
    private String tradeType;
    private String bankType;
    private Integer totalFee;
    private Integer settlementTotalFee;
    private String feeType;
    private Integer cashFee;
    private String cashFeeType;
    private Integer couponFee;
    private Integer couponCount;
    private List<WxPayOrderNotifyCoupon> couponList;
    private String transactionId;
    private String outTradeNo;
    private String attach;
    private String timeEnd;
    private String version;
    private String rateValue;
    private String signType;
}
