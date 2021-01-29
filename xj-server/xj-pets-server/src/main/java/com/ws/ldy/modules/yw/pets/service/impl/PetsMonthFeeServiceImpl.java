package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.yw.pets.mapper.PetsMonthFeeMapper;
import com.ws.ldy.modules.yw.pets.model.entity.PetsMonthFee;
import com.ws.ldy.modules.yw.pets.model.vo.PetsMonthFeeVO;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import com.ws.ldy.modules.yw.pets.service.PetsMonthFeeService;
import com.ws.ldy.modules.yw.pets.service.PetsPaymentBaseService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 月费表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:43
 */
@Service
public class PetsMonthFeeServiceImpl extends BaseIServiceImpl<PetsMonthFeeMapper, PetsMonthFee> implements PetsMonthFeeService {

    @Autowired
    private PetsDeclareService petsDeclareService;

    @Autowired
    private PetsPaymentBaseService petsPaymentBaseService;

    @Override
    public List<PetsMonthFee> findList() {
        // 当前用户申报成功次数
        String userId = JwtUtil.getJwtUser(request).getUserId();
        Integer declareSuccessTotal = petsDeclareService.findSuccessTotalByUserId(userId);
        // 获取支付基数
        Double paymentBase = petsPaymentBaseService.findPaymentBase(declareSuccessTotal);
        // 获取并计算实际支付
        List<PetsMonthFee> petsMonthFeeList = this.list(new LambdaQueryWrapper<PetsMonthFee>().orderByAsc(PetsMonthFee::getMonthNum));
        for (PetsMonthFee petsMonthFee : petsMonthFeeList) {
            BigDecimal monthFee = BigDecimalUtil.multiply(petsMonthFee.getMonthFee(), new BigDecimal(paymentBase + ""));
            petsMonthFee.setMonthFee(monthFee);
        }
        return petsMonthFeeList;
    }


    @Override
    public PetsMonthFeeVO findPayFeeById(String monthFeeId,String userId) {
        // 当前用户申报成功次数
        Integer declareSuccessTotal = petsDeclareService.findSuccessTotalByUserId(userId);
        // 获取支付基数
        Double paymentBase = petsPaymentBaseService.findPaymentBase(declareSuccessTotal);
        // 获取并计算实际支付
        PetsMonthFee petsMonthFee = this.getOne(new LambdaQueryWrapper<PetsMonthFee>()
                .eq(PetsMonthFee::getId, monthFeeId)
        );
        if (petsMonthFee == null) {
            throw new ErrorException(RType.MONTH_FEE_NOT_FOUND);
        }
        // 金额根据基数变动
        if (paymentBase != null) {
            BigDecimal monthFee = BigDecimalUtil.multiply(petsMonthFee.getMonthFee(), new BigDecimal(paymentBase + ""));
            petsMonthFee.setMonthFee(monthFee);
        }
        PetsMonthFeeVO petsMonthFeeVO = petsMonthFee.convert(PetsMonthFeeVO.class);
        petsMonthFeeVO.setPaymentBase(paymentBase);
        return petsMonthFeeVO;
    }
}
