package io.github.wslxm.springbootplus2.starter.pay.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 *  @author wangsong
 */
@Data
@ToString
public class WxEntPayResultVO extends WxBaseResurtVO {

    private static final long serialVersionUID = -1333690297694070839L;
    private String mchId;
    private String mchAppid;
    private String deviceInfo;
    private String partnerTradeNo;
    private String paymentNo;
    private String paymentTime;
}
