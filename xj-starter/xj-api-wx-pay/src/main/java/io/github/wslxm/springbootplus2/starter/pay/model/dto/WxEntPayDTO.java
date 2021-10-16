package io.github.wslxm.springbootplus2.starter.pay.model.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;



/**
  * 企业打款参数
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/1/22 0022 10:45
  * @version 1.0.1
  */
@Data
@ToString
@SuppressWarnings("all")
public class WxEntPayDTO implements Serializable {

    private static final long serialVersionUID = -7716392252749859151L;

    private String openid;          // 必传 -- 用户openId
    private Integer amount;         // 必传 -- 金额（分）
    private String partnerTradeNo;  // 必传 -- 订单号
    private String description;     // 非必传 -- 企业付款备注（默认--理赔）
    private String checkName;       // 非必传 -- 是否验证真实项目(默认 NO_CHECK) NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
    private String reUserName;      // 非必传 -- 收款用户姓名
    // private String spbillCreateIp; //非必传 --  ip * 该IP可传用户端或者服务端的IP( 默认 request.getRemoteHost() 获取操作人ip)
}
