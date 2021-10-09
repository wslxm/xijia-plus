package io.github.wslxm.springbootplus2.basepay.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Admin;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.basepay.manage.mapper.PayRecordMapper;
import io.github.wslxm.springbootplus2.basepay.manage.model.entity.PayRecord;
import io.github.wslxm.springbootplus2.basepay.manage.service.PayRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 第三方支付记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
@Service
public class PayRecordServiceImpl extends BaseIServiceImpl<PayRecordMapper, PayRecord> implements PayRecordService {



    /**
     * 添加支付记录
     */
    @Override
    public boolean addPayRecord(BigDecimal money,
                                String orderNo,
                                String tradeNo,
                                String requestData,
                                String responseData,
                                Base.PayState payState,
                                Base.PayType payType,
                                Admin.PayBusiness payBusiness
    ) {
        return this.addPayRecord(money, orderNo, tradeNo, requestData, responseData, payState, payType, payBusiness,
                new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"));
    }


    @Override
    public boolean addPayRecord(BigDecimal money,
                                String orderNo,
                                String tradeNo,
                                String requestData,
                                String responseData,
                                Base.PayState payState,
                                Base.PayType payType,
                                Admin.PayBusiness payBusiness,
                                BigDecimal platformFee,
                                BigDecimal channelFee,
                                BigDecimal moneySurplus
    ) {
        PayRecord record = new PayRecord();
        record.setMoneyTotal(money);
        record.setPlatformFee(platformFee);
        record.setChannelFee(channelFee);
        record.setMoneySurplus(moneySurplus);
        record.setOrderNo(orderNo);
        record.setTradeNo(tradeNo);
        record.setRequestData(requestData);
        record.setResponseData(responseData);
        record.setPayState(payState.getValue());
        record.setPayType(payType.getValue());
        record.setPayChannel(Base.PayChannel.V2.getValue());
        record.setBusinessType(payBusiness.getValue());
        record.setBusinessDesc(payBusiness.getDesc());
        record.setCallbackData(null);
        return this.save(record);
    }


    @Override
    public PayRecord findOrderByTradeNo(String tradeNo) {
        PayRecord xjPayRecord = this.getOne(new LambdaQueryWrapper<PayRecord>().eq(PayRecord::getTradeNo, tradeNo));
        return xjPayRecord;
    }


    // 挂起当前事物，等待业务方法执行完毕（调用者不管有无事物，被调用者都创建新事物）
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updStateAndCallbackData(String tradeNo, Integer state, String callbackData, String errorRemarks) {
        boolean result = this.update(new LambdaUpdateWrapper<PayRecord>()
                .set(PayRecord::getPayState, state)
                .set(PayRecord::getCallbackData, callbackData)
                .set(StringUtils.isNotBlank(errorRemarks), PayRecord::getErrorRemarks, errorRemarks)
                .eq(PayRecord::getTradeNo, tradeNo)
        );
        return result;
    }
}
