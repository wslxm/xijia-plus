package com.ws.ldy.modules.business.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.modules.business.pay.mapper.PayRecordMapper;
import com.ws.ldy.modules.business.pay.model.dto.PayRecordDTO;
import com.ws.ldy.modules.business.pay.model.entity.PayRecord;
import com.ws.ldy.modules.business.pay.service.PayRecordService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class XjPayRecordServiceImpl extends BaseIServiceImpl<PayRecordMapper, PayRecord> implements PayRecordService {


    @Override
    public Boolean insert(PayRecordDTO dto) {
        PayRecord payRecord = dto.convert(PayRecord.class);
        return this.save(payRecord);
    }

    @Override
    public PayRecord findOrderByTradeNo(String tradeNo) {
        PayRecord xjPayRecord = this.getOne(new LambdaQueryWrapper<PayRecord>().eq(PayRecord::getTradeNo, tradeNo));
        return xjPayRecord;
    }


    // 挂起当前事物，等待业务方法执行完毕（调用者不管有无事物，被调用者都创建新事物）
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updStateAndCallbackData(String tradeNo, Integer state, String callbackData) {
        boolean result = this.update(new LambdaUpdateWrapper<PayRecord>()
                .set(PayRecord::getPayState, state)
                .set(PayRecord::getCallbackData, callbackData)
                .eq(PayRecord::getTradeNo, tradeNo)
        );
        return result;
    }
}
