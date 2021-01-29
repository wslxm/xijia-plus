package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.yw.pets.mapper.PetsPaymentBaseMapper;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPaymentBase;
import com.ws.ldy.modules.yw.pets.service.PetsPaymentBaseService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 缴费基数配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:21
 */
@Service
public class PetsPaymentBaseServiceImpl extends BaseIServiceImpl<PetsPaymentBaseMapper, PetsPaymentBase> implements PetsPaymentBaseService {


    @Override
    public Double findPaymentBase(Integer declareSuccessTotal) {
        List<PetsPaymentBase> petsPaymentBaseList = this.list(new LambdaQueryWrapper<PetsPaymentBase>().orderByAsc(PetsPaymentBase::getDeclareNum));
        // 默认比例1
        double paymentBase = 1;
        if (petsPaymentBaseList != null) {
            for (PetsPaymentBase petsPaymentBase : petsPaymentBaseList) {
                if (declareSuccessTotal >= petsPaymentBase.getDeclareNum()) {
                    paymentBase = petsPaymentBase.getRatio();
                }
            }
        }
        return paymentBase;
    }
}
