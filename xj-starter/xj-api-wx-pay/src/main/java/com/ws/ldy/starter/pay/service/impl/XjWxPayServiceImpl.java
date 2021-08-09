package com.ws.ldy.starter.pay.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.utils.id.IdUtil;
import com.ws.ldy.starter.pay.model.dto.WxPayOrderDTO;
import com.ws.ldy.starter.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.starter.pay.model.vo.WxPayOrderNotifyResultVO;
import com.ws.ldy.starter.pay.model.vo.WxPayOrderResultVO;
import com.ws.ldy.starter.pay.model.vo.WxPayRefundResultVO;
import com.ws.ldy.starter.pay.service.XjWxPayService;
import com.ws.ldy.starter.pay.wxApi.WxPayApi;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public R<WxPayOrderResultVO> createOrder(WxPayOrderDTO dto) {
        // 默认支付方式
        if (StringUtils.isBlank(dto.getTradeType())) {
            dto.setTradeType("JSAPI");
        }
        // JSAPI 支付时必须传递openId
        if (dto.getTradeType().equals("JSAPI") && StringUtils.isBlank(dto.getOpenid())) {
            return R.error(RType.WX_PAY_NO_OPENID);
        }
        // 默认商品id(NATIVE支付必传)
        if (StringUtils.isBlank(dto.getProductId())) {
            dto.setProductId(IdUtil.getNo());
        }
        // 默认商品描叙
        if (StringUtils.isBlank(dto.getBody())) {
            dto.setBody("暂无商品描叙");
        }
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        //参数配置
        String body = dto.getBody();
        orderRequest.setNotifyUrl(dto.getNotifyUrl());
        orderRequest.setBody(body);
        orderRequest.setOpenid(dto.getOpenid());
        orderRequest.setOutTradeNo(dto.getOutTradeNo());
        orderRequest.setTotalFee(dto.getTotalFee());
        orderRequest.setTradeType(dto.getTradeType());
        orderRequest.setProductId(dto.getProductId());
        orderRequest.setSpbillCreateIp(request.getRemoteHost());
        try {
            WxPayOrderResultVO vo = null;
            if (orderRequest.getTradeType().equals("JSAPI")) {
                WxPayMpOrderResult result = wxPayApi.createOrder(orderRequest);
                vo = BeanDtoVoUtil.convert(result, WxPayOrderResultVO.class);
            } else if (orderRequest.getTradeType().equals("NATIVE")) {
                WxPayNativeOrderResult result = wxPayApi.createOrder(orderRequest);
                vo = BeanDtoVoUtil.convert(result, WxPayOrderResultVO.class);
            }
            return R.success(vo);
        } catch (WxPayException e) {
            log.debug(e.toString());
            return R.error(RType.WX_PAY_FAILURE.getValue(), RType.WX_PAY_FAILURE.getMsg()
                    + ":" + e.getReturnMsg()
                    + ":" + e.getErrCodeDes()
                    + ":" + e.getCustomErrorMsg()
            );
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
    public R<WxPayRefundResultVO> refund(WxPayRefundDTO dto) {
        WxPayRefundRequest refundRequest = new WxPayRefundRequest();
        refundRequest.setOutTradeNo(dto.getOutTradeNo());
        refundRequest.setOutRefundNo(dto.getOutRefundNo());
        refundRequest.setTotalFee(dto.getTotalFee());
        refundRequest.setRefundFee(dto.getRefundFee());
        refundRequest.setRefundDesc(dto.getRefundDesc());
        WxPayRefundResultVO vo = new WxPayRefundResultVO();
        try {
            WxPayRefundResult refund = wxPayApi.refund(refundRequest);
            BeanUtils.copyProperties(refund, vo);
            return R.success(vo);
        } catch (WxPayException e) {
            log.debug(e.toString());
            return R.error(RType.WX_PAY_FAILURE.getValue(),
                    RType.WX_PAY_FAILURE.getMsg()
                            + ":" + e.getReturnMsg()
                            + ":" + e.getCustomErrorMsg()
                            + ":" + e.getErrCodeDes());
        }
    }


    /**
     * 获取支付回调数据
     * @param xmlData
     * @return
     */
    @Override
    public R<WxPayOrderNotifyResultVO> parseOrderNotifyResult(String xmlData) {
        WxPayOrderNotifyResultVO vo = new WxPayOrderNotifyResultVO();
        try {
            WxPayOrderNotifyResult result = wxPayApi.parseOrderNotifyResult(xmlData);
            BeanUtils.copyProperties(result, vo);
        } catch (WxPayException e) {
            BeanUtils.copyProperties(e, vo);
        }
        return R.success(vo);
    }
}

