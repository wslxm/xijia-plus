package com.ws.ldy.modules.sys.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.common.utils.id.IdUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.Pay;
import com.ws.ldy.modules.sys.pay.model.dto.EntPayDTO;
import com.ws.ldy.modules.sys.pay.model.dto.PayOrderDTO;
import com.ws.ldy.modules.sys.pay.model.dto.PayRefundDTO;
import com.ws.ldy.modules.sys.pay.model.entity.PayRecord;
import com.ws.ldy.modules.sys.pay.model.vo.PayOrderResultVO;
import com.ws.ldy.modules.sys.pay.model.vo.PayRecordVO;
import com.ws.ldy.modules.sys.pay.service.PayRecordService;
import com.ws.ldy.modules.sys.pay.service.PayService;
import com.ws.ldy.modules.third.pay.config.WxPayProperties;
import com.ws.ldy.modules.third.pay.model.dto.WxEntPayDTO;
import com.ws.ldy.modules.third.pay.model.dto.WxPayOrderDTO;
import com.ws.ldy.modules.third.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.modules.third.pay.model.vo.WxEntPayResultVO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayOrderNotifyResultVO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayOrderResultVO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayRefundResultVO;
import com.ws.ldy.modules.third.pay.service.XjEntPayService;
import com.ws.ldy.modules.third.pay.service.XjWxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 微信支付
 * @author wangsong
 * @date 2021/1/5 0005 9:13
 * @return
 * @version 1.0.0
 */
@Service
@Slf4j
public class WxPayServiceImpl implements PayService {

    // 微信支付配置
    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private XjWxPayService xjWxPayService;

    @Autowired
    private XjEntPayService xjEntPayService;

    @Autowired
    private PayRecordService payRecordService;


    /**
     * 支付
     * @author wangsong
     * @param dto
     * @version 1.0.0
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<PayOrderResultVO> createOrder(PayOrderDTO dto) {
        // 交易号/订单号
        String tradeNo = dto.getTransactionNo();
        String orderNo = dto.getOrderNo();
        // 是否传递平台手续费
        if (dto.getMoneyTotal() == null) {
            dto.setMoneyTotal(new BigDecimal("0"));
        }
        // 1、创建微信订单
        WxPayOrderDTO wxOrderDto = new WxPayOrderDTO();
        // 交易号
        Integer totalFee = BigDecimalUtil.multiply100(new BigDecimal(dto.getMoneyTotal() + "")).intValue();
        wxOrderDto.setOutTradeNo(tradeNo);
        wxOrderDto.setTotalFee(totalFee);
        wxOrderDto.setBody(dto.getBody());
        wxOrderDto.setOpenid(dto.getWxOpenid());
        wxOrderDto.setTradeType(dto.getWxTradeType());
        wxOrderDto.setNotifyUrl(wxPayProperties.getNotifyBase() + wxPayProperties.getOrderNotifyUrl());
        R<WxPayOrderResultVO> wxPayOrderResultData = xjWxPayService.createOrder(wxOrderDto);
        if (!wxPayOrderResultData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            throw new ErrorException(wxPayOrderResultData.getCode(), wxPayOrderResultData.getMsg());
        }
        WxPayOrderResultVO wxPayOrderResult = wxPayOrderResultData.getData();

        // 2、处理支付返回前端数据,让前端调起微信
        PayOrderResultVO vo = BeanDtoVoUtil.convert(wxPayOrderResult, PayOrderResultVO.class);
        vo.setOrderNo(orderNo);

        // 计算剩余金额 -->   总金额 - 渠道手续费和-平台手续费
        BigDecimal moneySurplus = BigDecimalUtil.subtract(BigDecimalUtil.subtract(dto.getMoneyTotal(), dto.getChannelFee()), dto.getPlatformFee());
        // 3、记录与第三方的成功发起支付的信息
        boolean b = payRecordService.addPayRecord(
                dto.getMoneyTotal(),
                orderNo,
                tradeNo,
                JSON.toJSONString(wxOrderDto),
                JSON.toJSONString(wxPayOrderResult),
                Pay.PayState.V0,
                Pay.PayType.V1,
                dto.getPayBusiness(),
                dto.getPlatformFee(),
                dto.getChannelFee(),
                moneySurplus
        );
        return R.success(vo);
    }


    /**
     * 支付回调
     * <P>
     *    微信回调频率： 15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计 24h4m)
     * </P>
     * @author wangsong
     * @param xmlData
     * @version 1.0.0
     */
    @Override
    public R<PayRecordVO> orderCallback(String xmlData) {
        R<WxPayOrderNotifyResultVO> wxPayOrderNotifyResultVOData = xjWxPayService.parseOrderNotifyResult(xmlData);
        if (!wxPayOrderNotifyResultVOData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(wxPayOrderNotifyResultVOData.getCode(), wxPayOrderNotifyResultVOData.getMsg(), null, "微信支付回调参数获取失败");
        }
        WxPayOrderNotifyResultVO wxPayOrderNotifyResultVO = wxPayOrderNotifyResultVOData.getData();
        // 交易记录查询
        PayRecord payRecord = payRecordService.findOrderByTradeNo(wxPayOrderNotifyResultVO.getOutTradeNo());
        if (payRecord == null) {
            log.info("微信支付回调：交易订单不存在,交易号={}", wxPayOrderNotifyResultVO.getOutTradeNo());
            return R.error(RType.WX_PAY_FAILURE, null, "微信支付回调：交易订单不存在,交易号=" + wxPayOrderNotifyResultVO.getOutTradeNo());
        }
        // 回调判重
        if (!payRecord.getPayState().equals(Pay.PayState.V0.getValue())
                && !payRecord.getPayState().equals(Pay.PayState.V1.getValue())
        ) {
            log.info("微信支付回调：回调重复执行,交易号={}", wxPayOrderNotifyResultVO.getOutTradeNo());
            return R.error(RType.WX_PAY_REPEAT, null, "微信支付回调：重复执行回调,交易号=" + wxPayOrderNotifyResultVO.getOutTradeNo());
        }
        // 记录回调数据(不管支付成功还是失败先记录支付回调信息,避免业务异常无迹可寻)
        String outTradeNo = wxPayOrderNotifyResultVO.getOutTradeNo();
        String callbackData = JSON.toJSONString(wxPayOrderNotifyResultVO);
        payRecordService.updStateAndCallbackData(outTradeNo, Pay.PayState.V1.getValue(), callbackData, null);

        // 判断是否正常接收回调
        Integer moneyTotal = BigDecimalUtil.multiply100(payRecord.getMoneyTotal()).intValue();
        if (!moneyTotal.equals(wxPayOrderNotifyResultVO.getTotalFee())
                || !"SUCCESS".equals(wxPayOrderNotifyResultVO.getReturnCode())
                || !"SUCCESS".equals(wxPayOrderNotifyResultVO.getResultCode())
        ) {
            String errorRemarks = "支付异常:";
            if (!moneyTotal.equals(wxPayOrderNotifyResultVO.getTotalFee())) {
                errorRemarks += "微信支付金额异常";
            } else if (!"SUCCESS".equals(wxPayOrderNotifyResultVO.getReturnCode())) {
                errorRemarks += "微信支付签名验证/参数格式校验失败";
            } else if (!"SUCCESS".equals(wxPayOrderNotifyResultVO.getResultCode())) {
                errorRemarks += "微信端业务结果错误";
            }
            // 支付失败
            log.info("{},交易号={}", errorRemarks, wxPayOrderNotifyResultVO.getOutTradeNo());
            Boolean result = payRecordService.updStateAndCallbackData(outTradeNo, Pay.PayState.V2.getValue(), callbackData, errorRemarks);
            return R.success(BeanDtoVoUtil.convert(payRecord, PayRecordVO.class));
        } else {
            // 支付成功
            Boolean result = payRecordService.updStateAndCallbackData(outTradeNo, Pay.PayState.V3.getValue(), callbackData, null);
            return R.success(BeanDtoVoUtil.convert(payRecord, PayRecordVO.class));
        }
    }



    /**
     * 企业打款
     * @author wangsong
     * @param entPayDTO
     * @version 1.0.0
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> entPay(EntPayDTO entPayDTO) {
        // 是否传递订单号（未传递生成）
        String orderNo = entPayDTO.getOrderNo();
        String tradeNo = IdUtil.getNo();
        // 1、发起打款（如失败直接抛出异常信息,成功才返回）
        Integer amount = BigDecimalUtil.multiply100(entPayDTO.getAmount()).intValue();
        WxEntPayDTO wxEntPayDTO = new WxEntPayDTO();
        wxEntPayDTO.setOpenid(entPayDTO.getOpenid());
        wxEntPayDTO.setAmount(amount);
        wxEntPayDTO.setPartnerTradeNo(tradeNo);
        wxEntPayDTO.setDescription(entPayDTO.getDescription());
        wxEntPayDTO.setCheckName(entPayDTO.getCheckName());
        wxEntPayDTO.setReUserName(entPayDTO.getReUserName());
        R<WxEntPayResultVO> wxEntPayResultVOData = xjEntPayService.entPay(wxEntPayDTO);
        WxEntPayResultVO wxEntPayResultVO = wxEntPayResultVOData.getData();
        // 判断成功失败
        if (!wxEntPayResultVOData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 企业打款失败
            boolean b = payRecordService.addPayRecord(
                    entPayDTO.getAmount(),
                    orderNo,
                    tradeNo,
                    JSON.toJSONString(wxEntPayDTO),
                    JSON.toJSONString(wxEntPayResultVOData.getMsg()),
                    Pay.PayState.V2,
                    Pay.PayType.V4,
                    entPayDTO.getPayBusiness());
            return R.error(wxEntPayResultVOData.getCode(), wxEntPayResultVOData.getMsg(), false, "企业打款失败");
        } else {
            // 企业打款成功
            boolean b = payRecordService.addPayRecord(
                    entPayDTO.getAmount(),
                    orderNo,
                    tradeNo,
                    JSON.toJSONString(wxEntPayDTO),
                    JSON.toJSONString(wxEntPayResultVO),
                    Pay.PayState.V3,
                    Pay.PayType.V4,
                    entPayDTO.getPayBusiness());
            return R.success(true);
        }
    }


    /**
     * 微信退款 (支付订单退款原路返回)
     * @author wangsong
     * @param refundDTO
     * @version 1.0.0
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> refund(PayRefundDTO refundDTO) {
        // 1、处理退款所需参数,发起退款
        WxPayRefundDTO wxPayRefundDTO = new WxPayRefundDTO();
        wxPayRefundDTO.setOutTradeNo(refundDTO.getOutTradeNo());
        wxPayRefundDTO.setOutRefundNo(refundDTO.getOutRefundNo());
        wxPayRefundDTO.setTotalFee(BigDecimalUtil.multiply100(refundDTO.getTotalFee()).intValue());
        wxPayRefundDTO.setRefundFee(BigDecimalUtil.multiply100(refundDTO.getRefundFee()).intValue());
        wxPayRefundDTO.setRefundDesc(refundDTO.getRefundDesc());
        R<WxPayRefundResultVO> wxPayRefundResultVOR = xjWxPayService.refund(wxPayRefundDTO);
        WxPayRefundResultVO wxPayRefundResultVO = wxPayRefundResultVOR.getData();
        if (!wxPayRefundResultVOR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 退款失败
            boolean b = payRecordService.addPayRecord(
                    refundDTO.getRefundFee(),
                    refundDTO.getOrderNo(),
                    refundDTO.getOutRefundNo(),
                    JSON.toJSONString(refundDTO),
                    JSON.toJSONString(wxPayRefundResultVOR.getMsg()),
                    Pay.PayState.V2,
                    Pay.PayType.V3,
                    refundDTO.getPayBusiness());
            return R.error(wxPayRefundResultVOR.getCode(), wxPayRefundResultVOR.getMsg(), false, "退款失败");
        } else {
            // 退款成功
            boolean b = payRecordService.addPayRecord(
                    refundDTO.getRefundFee(),
                    refundDTO.getOrderNo(),
                    refundDTO.getOutRefundNo(),
                    JSON.toJSONString(refundDTO),
                    JSON.toJSONString(wxPayRefundResultVO),
                    Pay.PayState.V3,
                    Pay.PayType.V3,
                    refundDTO.getPayBusiness());
            return R.success(true);
        }
    }
}
