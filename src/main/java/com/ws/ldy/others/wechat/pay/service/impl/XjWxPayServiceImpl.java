package com.ws.ldy.others.wechat.pay.service.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.others.wechat.pay.config.WxPay2Config;
import com.ws.ldy.others.wechat.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.others.wechat.pay.model.dto.WxPayUnifiedOrderDTO;
import com.ws.ldy.others.wechat.pay.model.vo.WxPayMpOrderResultVO;
import com.ws.ldy.others.wechat.pay.model.vo.WxRefundResultVO;
import com.ws.ldy.others.wechat.pay.service.XjWxPayService;
import com.ws.ldy.others.wechat.pay.wxApi.WxPayApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * <pre>
 * 微信支付相关
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 */
@Service
public class XjWxPayServiceImpl implements XjWxPayService {

    @Autowired
    private WxPayApi wxPayApi;

    @Autowired
    private HttpServletRequest request;

    /**
     * 支付下单
     * @author wangsong
     * @param dto
     * @date 2020/12/27 0027 19:59
     * @return com.ws.ldy.others.wechat.pay.model.vo.WxPayMpOrderResultVO
     * @version 1.0.0
     */
    @Override
    public WxPayMpOrderResultVO createOrder(WxPayUnifiedOrderDTO dto) {
        if (StringUtils.isBlank(dto.getOpenid())) {
            throw new ErrorException(RType.WX_PAY_NO_OPENID);
        }
        // 默认支付方式
        if (StringUtils.isBlank(dto.getTradeType())) {
            dto.setTradeType("JSAPI");
        }
        // 默认请求ip
        if (StringUtils.isBlank(dto.getSpbillCreateIp())) {
            dto.setSpbillCreateIp(request.getRemoteHost());
        }
        // 默认商品描叙
        if (StringUtils.isBlank(dto.getBody())) {
            dto.setBody("暂无商品描叙");
        }
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        //参数配置
        String body = dto.getBody();
        orderRequest.setNotifyUrl(WxPay2Config.orderNotifyUrl);
        orderRequest.setBody(body);
        orderRequest.setOpenid(dto.getOpenid());
        orderRequest.setOutTradeNo(dto.getOutTradeNo());
        orderRequest.setTotalFee(dto.getTotalFee());
        orderRequest.setSpbillCreateIp(dto.getSpbillCreateIp());
        orderRequest.setTradeType(dto.getTradeType());
        try {
            WxPayMpOrderResult result = wxPayApi.createOrder(orderRequest);
            WxPayMpOrderResultVO vo = new WxPayMpOrderResultVO();
            BeanUtils.copyProperties(result, vo);
            return vo;
        } catch (WxPayException e) {
            e.printStackTrace();
            throw new ErrorException(RType.WX_PAY_FAILURE.getValue(), RType.WX_PAY_FAILURE.getMsg() + ":" + e.getReturnMsg());
        }
    }


    /**
     * 退款
     * @author wangsong
     * @param dto
     * @date 2020/12/27 0027 19:59
     * @return com.github.binarywang.wxpay.bean.result.WxRefundResultVO
     * @version 1.0.0
     */
    @Override
    public WxRefundResultVO refund(WxPayRefundDTO dto) {
        WxPayRefundRequest refundRequest = new WxPayRefundRequest();
        refundRequest.setOutTradeNo(dto.getOutTradeNo());
        refundRequest.setOutRefundNo(dto.getOutRefundNo());
        refundRequest.setTotalFee(dto.getTotalFee());
        refundRequest.setRefundFee(dto.getRefundFee());
        refundRequest.setRefundDesc(dto.getRefundDesc());
        WxRefundResultVO vo = new WxRefundResultVO();
        try {
            WxPayRefundResult refund = wxPayApi.refund(refundRequest);
            BeanUtils.copyProperties(refund, vo);
        } catch (WxPayException e) {
            BeanUtils.copyProperties(e, vo);
        }
        return vo;
    }
}

