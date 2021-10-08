package com.github.wslxm.springbootplus2.basepay.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wslxm.springbootplus2.core.enums.Admin;
import com.github.wslxm.springbootplus2.core.enums.Base;
import com.github.wslxm.springbootplus2.basepay.manage.model.entity.PayRecord;

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
public interface PayRecordService extends IService<PayRecord> {


    /**
     * 添加支付记录
     * @param money    交易金额-元
     * @param orderNo  订单号
     * @param tradeNo  本次交易的交易号（退款,支付）
     * @param requestData  第三方请求的请求数据
     * @param responseData 第三方请求的响应数据
     * @param payState     交易状态(成功/失败)
     * @param payType      交易类型(支付，退款)
     * @param payBusiness  业务类型(订单支付，月费支付,退款等等,业务系统自定义)
     * @return boolean
     */
    boolean addPayRecord(BigDecimal money,
                         String orderNo,
                         String tradeNo,
                         String requestData,
                         String responseData,
                         Base.PayState payState,
                         Base.PayType payType,
                         Admin.PayBusiness payBusiness
    );


    /**
     * 添加支付记录
     * @author wangsong
     * @param money    交易金额-元
     * @param orderNo  订单号
     * @param tradeNo  本次交易的交易号（退款,支付）
     * @param requestData  第三方请求的请求数据
     * @param responseData 第三方请求的响应数据
     * @param payState     交易状态(成功/失败)
     * @param payType      交易类型(支付，退款)
     * @param payBusiness  业务类型(订单支付，月费支付,退款等等,业务系统自定义)
     * @param platformFee  平台手续费
     * @param channelFee   渠道手续费
     * @param moneySurplus 剩余金额(如存在子商户, 则为子商户实际收入)
     * @date 2021/3/26 0026 10:59
     * @return boolean
     * @version 1.0.0
     */
    boolean addPayRecord(BigDecimal money,
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
    );

    /**
     * 交易号查询
     * @author wangsong
     * @date 2021/1/5 0005 15:14
     * @return
     * @version 1.0.0
     */
    PayRecord findOrderByTradeNo(String tradeNo);


    /**
     * 通过订单号编辑
     * @author wangsong
     * @date 2021/1/5 0005 15:14
     * @param tradeNo      *  交易号
     * @param state        *  支付状态
     * @param callbackData *  回调数据
     * @param errorRemarks 支付错误的描叙信息(可null)
     * @return
     * @version 1.0.0
     */
    public boolean updStateAndCallbackData(String tradeNo, Integer state, String callbackData, String errorRemarks);
}

