package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.dto.PetsCapitalDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapital;

import java.math.BigDecimal;

/**
 * 互助资金表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:52:40
 */
public interface PetsCapitalService extends IService<PetsCapital> {


    /**
     * 查询资金表,如果不存在，返回空对象
     * @author wangsong
     * @date 2020/12/30 0030 10:55
     * @return com.ws.ldy.modules.pets.model.entity.PetsCapital
     * @version 1.0.0
     */
    PetsCapital findCapital();


    /**
     * 编辑资金表
     * @author wangsong
     * @date 2020/12/30 0030 10:55
     * @return 返回平台总金额 （平台资金累积总金额）
     * @version 1.0.0
     */
    BigDecimal updCapital(PetsCapitalDTO dto);


//    /**
//     * 企业打款成功--资金/流水
//     * @param petsDeclare
//     * @return
//     */
//    public Boolean entPayCapitalAndWalletFlow(PetsDeclare petsDeclare);
}

