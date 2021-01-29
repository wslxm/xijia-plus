package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPaymentBase;

/**
 * 缴费基数配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:21
 */
public interface PetsPaymentBaseService extends IService<PetsPaymentBase> {


    /**
     * 获取支付基数
     * @author wangsong
     * @param declareSuccessTotal
     * @date 2020/12/30 0030 14:38
     * @return java.lang.Double
     * @version 1.0.0
     */
    Double findPaymentBase(Integer declareSuccessTotal);

}

