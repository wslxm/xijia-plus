package com.ws.ldy.others.wechat.pay.service;

import com.ws.ldy.others.wechat.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.others.wechat.pay.model.dto.WxPayUnifiedOrderDTO;
import com.ws.ldy.others.wechat.pay.model.vo.WxPayMpOrderResultVO;
import com.ws.ldy.others.wechat.pay.model.vo.WxRefundResultVO;
import org.springframework.web.bind.annotation.RequestBody;

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
    WxPayMpOrderResultVO createOrder(@RequestBody WxPayUnifiedOrderDTO dto);


    /**
     * 退款
     * @param dto
     * @return
     */
    WxRefundResultVO refund(@RequestBody WxPayRefundDTO dto);

}


