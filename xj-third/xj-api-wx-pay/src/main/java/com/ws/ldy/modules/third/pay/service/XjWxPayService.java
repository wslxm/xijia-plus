package com.ws.ldy.modules.third.pay.service;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.pay.model.dto.WxPayOrderDTO;
import com.ws.ldy.modules.third.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayOrderNotifyResultVO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayOrderResultVO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayRefundResultVO;

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
    R<WxPayOrderResultVO> createOrder(WxPayOrderDTO dto);


    /**
     * 退款
     * @param dto
     * @return
     */
    R<WxPayRefundResultVO> refund(WxPayRefundDTO dto);


    /**
     * 支付回调参数
     * @param xmlData
     * @return
     */
    R<WxPayOrderNotifyResultVO> parseOrderNotifyResult(String xmlData);
}


