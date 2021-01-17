package com.ws.ldy.modules.yw.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pay.model.dto.PayRecordDTO;
import com.ws.ldy.modules.yw.pay.model.entity.PayRecord;

/**
 * 第三方支付记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
public interface PayRecordService extends IService<PayRecord> {


    /**
     * 添加支付记录
     * @author wangsong
     * @param dto
     * @date 2021/1/5 0005 10:49
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean insert(PayRecordDTO dto);


    /**
     * 交易号查询
     * @author wangsong
     * @date 2021/1/5 0005 15:14
     * @return
     * @version 1.0.0
     */
    PayRecord findOrderByTradeNo(String tradeNo);


    /**
     * 订单号编辑
     * @author wangsong
     * @date 2021/1/5 0005 15:14
     * @return
     * @version 1.0.0
     */

    public boolean updStateAndCallbackData(String tradeNo, Integer state, String callbackData);
}

