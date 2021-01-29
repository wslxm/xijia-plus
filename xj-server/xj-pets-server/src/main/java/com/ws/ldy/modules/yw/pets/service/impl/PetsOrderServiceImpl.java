package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.common.utils.IdUtil;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.pay.model.dto.PayOrderDTO;
import com.ws.ldy.modules.sys.pay.model.vo.PayOrderResultVO;
import com.ws.ldy.modules.sys.pay.model.vo.PayRecordVO;
import com.ws.ldy.modules.sys.pay.service.PayWalletFlowService;
import com.ws.ldy.modules.sys.pay.service.impl.WxPayServiceImpl;
import com.ws.ldy.modules.yw.pets.mapper.PetsOrderMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsCapitalDTO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsOrderDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapital;
import com.ws.ldy.modules.yw.pets.model.entity.PetsOrder;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.model.vo.CapitalFindFeeVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsMonthFeeVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderClientPageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderPageVO;
import com.ws.ldy.modules.yw.pets.service.*;
import com.ws.ldy.modules.yw.third.WxAppSubscribeSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
@Service
@Slf4j
public class PetsOrderServiceImpl extends BaseIServiceImpl<PetsOrderMapper, PetsOrder> implements PetsOrderService {

    @Autowired
    private PayWalletFlowService payWalletFlowService;

    @Autowired
    private PetsMonthFeeService petsMonthFeeService;

    @Autowired
    private PetsPetInfoService petsPetInfoService;

    @Autowired
    private PetsUserService petsUserService;

    @Autowired
    private PetsCapitalConfigService petsCapitalConfigService;

    @Autowired
    private PetsCapitalService petsCapitalService;

    // 微信支付
    @Autowired
    private WxPayServiceImpl wxPayService;

    // 微信订阅消息
    @Autowired
    private WxAppSubscribeSendUtil wxAppSubscribeSendUtil;


    @Override
    public Page<PetsOrderClientPageVO> findClientPage(Page<PetsOrder> page, String userId) {
        return baseMapper.findClientPage(page, userId);
    }

    @Override
    public Page<PetsOrderPageVO> findPage(Page<PetsOrder> page,
                                          String fullName,
                                          String phone,
                                          Integer orderState,
                                          String orderNo) {
        return baseMapper.findPage(page, fullName, phone, orderState, orderNo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayOrderResultVO createOrder(PetsOrderDTO dto) {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        // 1、查询计算需支付的金额（月费信息）
        PetsMonthFeeVO petsMonthFee = petsMonthFeeService.findPayFeeById(dto.getMonthFeeId(), userId);

        // 是否首次支付(统计使用)
        int count = this.count(new LambdaQueryWrapper<PetsOrder>().eq(PetsOrder::getUserId, userId));

        // 2、创建订单
        PetsOrder order = new PetsOrder();
        order.setOrderNo(IdUtil.timestampRandom());
        order.setUserId(userId);
        order.setPetId(dto.getPetId());
        order.setMonthFeeId(dto.getMonthFeeId());
        order.setMoney(petsMonthFee.getMonthFee());
        order.setPaymentBase(petsMonthFee.getPaymentBase());
        order.setPayChannel(Enums.Pay.PayChannel.PAY_CHANNEL_2.getValue());
        order.setOrderState(Enums.Pet.OrderState.ORDER_STATE_0.getValue());
        order.setBusinessType(Enums.Pay.PayBusiness.PAY_BUSINESS_1.getValue());
        order.setBusinessDesc(petsMonthFee.getMonthDesc());
        order.setIsAutoRenew(dto.getIsAutoRenew());
        order.setIsFirst(count == 0);
        this.save(order);

        // 3、计算平台抽成金额 和 渠道手续费金额
        CapitalFindFeeVO capitalFindFee = petsCapitalConfigService.findFee(order.getMoney());

        // 4、向微信发起支付订单
        String wxOrderId = petsUserService.findWxOrderId(userId);
        PayOrderDTO payDto = new PayOrderDTO();
        payDto.setPlatformFee(capitalFindFee.getPlatformFee());
        payDto.setChannelFee(capitalFindFee.getWxChannelFee());
        payDto.setMoneyTotal(order.getMoney());
        payDto.setOrderNo(order.getOrderNo());
        payDto.setBody(petsMonthFee.getMonthDesc());
        payDto.setWxOpenid(wxOrderId);
        payDto.setWxTradeType("JSAPI");
        PayOrderResultVO orderVO = wxPayService.createOrder(payDto);
        // 返回
        orderVO.setOrderNo(order.getOrderNo());
        // 模拟成功
        // PayRecordVO payRecordVO = BeanDtoVoUtil.convert(payDto, PayRecordVO.class);
        // paySuccess(payRecordVO);
        return orderVO;
    }


    /**
     * 微信支付回调
     * @param xmlData
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String orderCallback(String xmlData) {
        //解析参数
        log.info("\r\n 支付回调参数：{}" + xmlData);
        R<PayRecordVO> payRecordVOR = wxPayService.orderCallback(xmlData);
        if (payRecordVOR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 支付成功
            this.paySuccess(payRecordVOR.getData());
            return WxPayNotifyResponse.success("ok");
        } else {
            // 支付失败
            if (payRecordVOR.getData() == null) {
                // 重复回调
                return WxPayNotifyResponse.fail("fail");
            }
            this.payFail(payRecordVOR.getData().getOrderNo());
            return WxPayNotifyResponse.fail("fail");
        }
    }


    /**
     * 支付成功业务逻辑
     * @param payRecordVO
     * @return
     */
    private Boolean paySuccess(PayRecordVO payRecordVO) {
        // 1、查询订单信息
        String orderNo = payRecordVO.getOrderNo();
        PetsOrder petsOrder = this.getOne(new LambdaQueryWrapper<PetsOrder>().eq(PetsOrder::getOrderNo, orderNo));
        // 2、查询月费信息
        PetsMonthFeeVO petsMonthFee = petsMonthFeeService.findPayFeeById(petsOrder.getMonthFeeId(), petsOrder.getUserId());
        // 3、订单绑定宠物,给宠物添加时长(新绑定或续费)
        String petId = petsPetInfoService.bind(petsOrder.getIsAutoRenew(), petsMonthFee.getMonthNum(), petsOrder.getPetId());
        // 4、修改订单为支付成功
        boolean result = this.update(new LambdaUpdateWrapper<PetsOrder>()
                .set(PetsOrder::getOrderState, Enums.Pet.OrderState.ORDER_STATE_3.getValue())
                .set(PetsOrder::getPayTime, LocalDateTime.now())
                .eq(PetsOrder::getOrderNo, orderNo)
        );
        // 5、成功添加平台资金及 平台流水+用户流水等信息
        this.orderCapitalAndWalletFlow(payRecordVO, petsOrder);
        // 6、发送支付成功订阅消息通知
        PetsUser user = petsUserService.findId(petsOrder.getUserId());
        wxAppSubscribeSendUtil.payMsg(user.getWxOpenId(),
                user.getWxName(),
                petsOrder.getBusinessDesc(),
                petsOrder.getMoney(),
                LocalDateTimeUtil.parse(LocalDateTime.now())
        );
        return true;
    }

    /**
     * 支付失败业务逻辑
     * @param orderNo
     * @return
     */
    private Boolean payFail(String orderNo) {
        // 修改支付状态(失败)
        boolean result = this.update(new LambdaUpdateWrapper<PetsOrder>()
                .set(PetsOrder::getOrderState, Enums.Pet.OrderState.ORDER_STATE_1.getValue())
                .set(PetsOrder::getPayTime, LocalDateTime.now())
                .eq(PetsOrder::getOrderNo, orderNo)
        );
        return result;
    }


    /**
     * 用户支付成功回调处理--资金/流水
     * @param payRecordVO
     * @param order
     * @return
     */
    private Boolean orderCapitalAndWalletFlow(PayRecordVO payRecordVO, PetsOrder order) {
        // 资金计算
        PetsCapital capital = petsCapitalService.findCapital();
        // 平台资金累积总金额-增加 /平台收益总额度-增加 /资金池剩余额度-增加
        BigDecimal moneyTotal = BigDecimalUtil.add(capital.getMoneyTotal(), order.getMoney());
        BigDecimal moneyProfit = BigDecimalUtil.add(capital.getMoneyProfit(), payRecordVO.getPlatformFee());
        BigDecimal moneySurplus = BigDecimalUtil.add(capital.getMoneySurplus(), payRecordVO.getMoneySurplus());
        //
        PetsCapitalDTO capitalDto = new PetsCapitalDTO();
        capitalDto.setMoneyTotal(moneyTotal);
        capitalDto.setMoneyProfit(moneyProfit);
        capitalDto.setMoneySurplus(moneySurplus);
        capitalDto.setId(capital.getId());
        capitalDto.setVersion(capital.getVersion());
        // 修改资金,返回账号剩余总金额
        BigDecimal moneyAfter = petsCapitalService.updCapital(capitalDto);

        // 添加用户流水
        payWalletFlowService.addUserWalletFlow(order.getUserId(), order.getOrderNo(), order.getMoney(), new BigDecimal("0"),
                Enums.Pet.WalletType.WALLET_TYPE_2, Enums.Pay.PayBusiness.PAY_BUSINESS_1
        );
        // 添加平台总资产记录
        payWalletFlowService.addPlatformWalletFlow(order.getOrderNo(), order.getMoney(), moneyAfter,
                Enums.Pet.WalletType.WALLET_TYPE_1, Enums.Pay.PayBusiness.PAY_BUSINESS_1
        );
        return true;
    }
}
