package com.ws.ldy.modules.third.pay.model.vo;

import lombok.Data;
import lombok.ToString;

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
