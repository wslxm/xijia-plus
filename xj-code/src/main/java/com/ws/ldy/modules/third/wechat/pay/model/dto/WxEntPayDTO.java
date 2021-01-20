package com.ws.ldy.modules.third.wechat.pay.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;


/**
 * @author ws
 */
@Data
@ToString
@SuppressWarnings("all")
public class WxEntPayDTO extends Convert {

    private static final long serialVersionUID = -7716392252749859151L;
    private String openid;        // 必传 -- 用户openId
    private Integer amount;       // 必传 -- 金额（分）
    private String partnerTradeNo;// 必传 -- 订单号
    private String description; // 非必传 -- 企业付款备注（默认--理赔）
    private String checkName;   // 非必传 -- 是否验证真实项目(默认 NO_CHECK) NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
    private String reUserName;  // 非必传 -- 收款用户姓名
    // private String spbillCreateIp; //非必传 --  ip * 该IP可传用户端或者服务端的IP( 默认 request.getRemoteHost() 获取操作人ip)
}
