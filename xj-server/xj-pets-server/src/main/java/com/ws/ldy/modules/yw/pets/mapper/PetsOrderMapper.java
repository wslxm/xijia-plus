package com.ws.ldy.modules.yw.pets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.modules.yw.pets.model.entity.PetsOrder;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderClientPageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
public interface PetsOrderMapper extends BaseMapper<PetsOrder> {


    /**
     * 查询充值记录-用户端（带宠物信息）
     * @return
     */
    Page<PetsOrderClientPageVO> findClientPage(Page<PetsOrder> page, @Param("userId") String userId);


    /**
     * 查询充值记录
     * @return
     */
    Page<PetsOrderPageVO> findPage(Page<PetsOrder> page,
                                   @Param("fullName") String fullName,
                                   @Param("phone") String phone,
                                   @Param("orderState") Integer orderState,
                                   @Param("orderNo") String orderNo
    );
}

