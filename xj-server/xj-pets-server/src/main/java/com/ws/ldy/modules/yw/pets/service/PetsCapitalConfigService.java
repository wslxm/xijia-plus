package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapitalConfig;
import com.ws.ldy.modules.yw.pets.model.vo.CapitalFindFeeVO;

import java.math.BigDecimal;

/**
 * 互助资金抽成配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:46
 */
public interface PetsCapitalConfigService extends IService<PetsCapitalConfig> {


    /**
     * 获取平台抽成配置
     * @author wangsong
     * @date 2021/1/5 0005 11:39
     * @return com.ws.ldy.modules.pets.model.entity.PetsCapitalConfig
     * @version 1.0.0
     */
    public PetsCapitalConfig findCapitalConfig();



    /**
     * 计算抽成/ 和手续费金额
     * @param moneyTotal 支付总金额 元
     * @author wangsong
     * @date 2021/1/5 0005 11:39
     * @return com.ws.ldy.modules.pets.model.entity.PetsCapitalConfig
     * @version 1.0.0
     */
    public CapitalFindFeeVO findFee(BigDecimal moneyTotal);
}

