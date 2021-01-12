package com.ws.ldy.modules.third.wechat.pay.service;

import com.ws.ldy.modules.third.wechat.pay.model.vo.WxPayOrderNotifyResultVO;
import com.ws.ldy.modules.third.wechat.pay.model.dto.WxPayOrderDTO;
import com.ws.ldy.modules.third.wechat.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.modules.third.wechat.pay.model.vo.WxPayOrderResultVO;
import com.ws.ldy.modules.third.wechat.pay.model.vo.WxPayRefundResultVO;

/**
 * <pre>
 * 微信支付相关
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 */
public interface XjWxPayService {


    /**
     * 支付下单
     * @param dto
     * @return 调起支付的参数
     */
    WxPayOrderResultVO createOrder(WxPayOrderDTO dto);


    /**
     * 退款
     * @param dto
     * @return
     */
    WxPayRefundResultVO refund(WxPayRefundDTO dto);


    /**
     * 支付回调参数
     * @param xmlData
     * @return
     */
    WxPayOrderNotifyResultVO parseOrderNotifyResult(String xmlData);
}


