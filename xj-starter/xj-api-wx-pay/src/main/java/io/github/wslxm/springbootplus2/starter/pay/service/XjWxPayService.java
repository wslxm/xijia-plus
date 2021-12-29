package io.github.wslxm.springbootplus2.starter.pay.service;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.pay.model.dto.WxPayOrderDTO;
import io.github.wslxm.springbootplus2.starter.pay.model.dto.WxPayRefundDTO;
import io.github.wslxm.springbootplus2.starter.pay.model.vo.WxPayOrderNotifyResultVO;
import io.github.wslxm.springbootplus2.starter.pay.model.vo.WxPayOrderResultVO;
import io.github.wslxm.springbootplus2.starter.pay.model.vo.WxPayRefundResultVO;


/**
 *  @author wangsong
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


