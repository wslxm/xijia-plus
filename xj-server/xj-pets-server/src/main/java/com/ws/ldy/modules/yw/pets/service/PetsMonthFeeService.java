package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.entity.PetsMonthFee;
import com.ws.ldy.modules.yw.pets.model.vo.PetsMonthFeeVO;

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
public interface PetsMonthFeeService extends IService<PetsMonthFee> {


    /**
     * 获取当前登录用户应支付的月费金额
     * @author wangsong
     * @date 2020/12/30 0030 14:34
     * @return java.util.List<com.ws.ldy.modules.pets.model.entity.PetsMonthFee>
     * @version 1.0.0
     */
    public List<PetsMonthFee> findList();


    /**
     * 获取当前登录用户 指定产品的的需支付金额
     * @author wangsong
     * @date 2020/12/30 0030 14:34
     * @return java.util.List<com.ws.ldy.modules.pets.model.entity.PetsMonthFee>
     * @version 1.0.0
     */
    public PetsMonthFeeVO findPayFeeById(String monthFeeId,String userId);
}

