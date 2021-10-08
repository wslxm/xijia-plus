package com.github.wslxm.springbootplus2.basepay.manage.model.dto;

import com.github.wslxm.springbootplus2.core.base.model.Convert;
import com.github.wslxm.springbootplus2.core.enums.Admin;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author ws
 */

@Data
@ToString(callSuper = true)
@ApiModel(value = "EntPayDTO 对象", description = "企业付款給商家")
@SuppressWarnings("all")
public class EntPayDTO extends Convert {
    private static final long serialVersionUID = -6694678961961236789L;


    private String openid;               // 必传 -- 用户openId
    private BigDecimal amount;           // 必传 -- 金额（元）
    private String orderNo;              // 必传 -- 订单号(申请打款订单)
    private Admin.PayBusiness payBusiness; // 必传 -- 业务类型（当前系统字典code）
    private String description;          // 非必传(微信支付参数) -- 企业付款备注（默认--理赔）
    private String checkName;            // 非必传(微信支付参数) -- 是否验证真实项目(默认 NO_CHECK) NO_CHECK：不校验真实姓名  FORCE_CHECK：强校验真实姓名
    private String reUserName;           // 非必传(微信支付参数) -- 收款用户姓名
    // private String spbillCreateIp;    // 非必传(微信支付参数) --  ip * 该IP可传用户端或者服务端的IP( 默认 request.getRemoteHost() 获取操作人ip)

}
