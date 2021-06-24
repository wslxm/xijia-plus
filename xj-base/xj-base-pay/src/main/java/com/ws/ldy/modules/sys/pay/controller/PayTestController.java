package com.ws.ldy.modules.sys.pay.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.id.IdUtil;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.enums.Pay;
import com.ws.ldy.modules.sys.pay.model.dto.EntPayDTO;
import com.ws.ldy.modules.sys.pay.model.dto.PayOrderDTO;
import com.ws.ldy.modules.sys.pay.model.dto.PayRefundDTO;
import com.ws.ldy.modules.sys.pay.model.vo.PayOrderResultVO;
import com.ws.ldy.modules.sys.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 *  支付测试
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiOpen + "/pay/test")
@Api(value = "PayTestController", tags = "pay  -->  支付测试")
public class PayTestController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ApiOperation(value = "下单", notes = "")
    public R<PayOrderResultVO> createOrder(@RequestParam String openId) {
        PayOrderDTO dto = new PayOrderDTO();
        dto.setMoneyTotal(new BigDecimal("1"));
        dto.setPlatformFee(null);
        dto.setChannelFee(null);
        dto.setOrderNo(IdUtil.getNo());
        dto.setPayBusiness(Pay.PayBusiness.V1);
        dto.setBody("下单测试");
        dto.setWxTradeType("JSAPI");
        dto.setWxOpenid(openId);
        return payService.createOrder(dto);
    }

    @RequestMapping(value = "/entPay", method = RequestMethod.POST)
    @ApiOperation(value = "打款测试", notes = "")
    public R<Boolean> entPay(@RequestParam String openId) {
        EntPayDTO entPayDTO = new EntPayDTO();
        entPayDTO.setOpenid(openId);
        entPayDTO.setAmount(new BigDecimal("0.01"));
        entPayDTO.setOrderNo(IdUtil.getNo());
        entPayDTO.setPayBusiness(Pay.PayBusiness.V2);
        entPayDTO.setDescription("企业打款");
        // entPayDTO.setCheckName();
        // entPayDTO.setReUserName();
        return payService.entPay(entPayDTO);
    }


    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ApiOperation(value = "退款测试", notes = "")
    public R<Boolean> refund() {
        PayRefundDTO refundDTO = new PayRefundDTO();
        refundDTO.setOrderNo(IdUtil.getNo());  //订单号(支付订单生成)
        refundDTO.setOutTradeNo(IdUtil.getNo()); //原交易号(支付订单生成)
        refundDTO.setOutRefundNo(IdUtil.getNo());  //退款号
        refundDTO.setTotalFee(new BigDecimal("1"));
        refundDTO.setRefundFee(new BigDecimal("0.01"));
        refundDTO.setPayBusiness(Pay.PayBusiness.V2);
        refundDTO.setRefundDesc("退款描叙");
        return payService.refund(refundDTO);
    }
}
