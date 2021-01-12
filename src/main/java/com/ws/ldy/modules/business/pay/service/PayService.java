package com.ws.ldy.modules.business.pay.service;

import com.ws.ldy.modules.business.pay.model.dto.EntPayDTO;
import com.ws.ldy.modules.business.pay.model.dto.PayOrderDTO;
import com.ws.ldy.modules.business.pay.model.vo.EntPayResultVO;
import com.ws.ldy.modules.business.pay.model.vo.PayOrderResultVO;

/**
 * 支付 Service
 */
public interface PayService {


    /**
     * 用户--创建订单
     * @author wangsong
     * @date 2021/1/5 0005 9:14
     * @return void
     * @version 1.0.0
     */
    public PayOrderResultVO createOrder(PayOrderDTO dto);


    /**
     * 用户--微信支付回调
     * @author wangsong
     * @date 2021/1/5 0005 9:14
     * @return void
     * @version 1.0.0
     */
    public String orderCallback(String xmlData);


    /**
     * 企业打款
     * @date 2021/1/5 0005 9:14
     * @return void
     * @version 1.0.0
     */
    public EntPayResultVO entPay(EntPayDTO entPayDTO);

}
