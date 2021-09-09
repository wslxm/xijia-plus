package com.ws.ldy.basepay.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.basepay.manage.model.dto.PayWalletFlowDTO;
import com.ws.ldy.basepay.manage.model.entity.PayWalletFlow;
import com.ws.ldy.core.enums.Admin;
import com.ws.ldy.core.enums.Base;

import java.math.BigDecimal;

/**
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
public interface PayWalletFlowService extends IService<PayWalletFlow> {


    /**
     * 添加流水
     * @param dto
     */
    Boolean addWalletFlow(PayWalletFlowDTO dto);


    /**
     * 添加平台总资产流水(不计算手续费)
     * @param walletType  支付/收入
     * @param money       金额(元)
     * @param payBusiness 业务类型
     * @param orderNo     订单号
     */
    Boolean addPlatformWalletFlow(String orderNo,
                                  BigDecimal money,
                                  BigDecimal moneyAfter,
                                  Base.WalletType walletType,
                                  Admin.PayBusiness payBusiness);

    /**
     * 添加用户/商家流水
     * @param userId       用户/商家id
     * @param walletType  支付/收入
     * @param money       收入支出金额(元)
     * @param moneyAfter  剩余总金额(元) 当前账号
     * @param payBusiness 业务类型
     * @param orderNo     订单号
     */
    Boolean addUserWalletFlow(String userId,
                              String orderNo,
                              BigDecimal money,
                              BigDecimal moneyAfter,
                              Base.WalletType walletType,
                              Admin.PayBusiness payBusiness);

    /**
     * 查询指定用户支出总额
     * @param userId
     */
    BigDecimal findUserTotalAmount(String userId);

}

