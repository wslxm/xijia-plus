package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.pay.model.vo.PayOrderResultVO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsOrderDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsOrder;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderClientPageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderPageVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
public interface PetsOrderService extends IService<PetsOrder> {


    /**
     * 查询充值记录
     * @return
     */
    Page<PetsOrderClientPageVO> findClientPage(Page<PetsOrder> page, String userId);


    /**
     * 查询充值记录
     * @return
     */
    Page<PetsOrderPageVO> findPage(Page<PetsOrder> page,
                                   String fullName,
                                   String phone,
                                   Integer orderState,
                                   String orderNo);


//    /**
//     * 通过 OrderNo 查询订单信息
//     * @author wangsong
//     * @date 2020/12/30 0030 11:52
//     * @return com.ws.ldy.modules.pets.model.entity.PetsOrder
//     * @version 1.0.0
//     */
//    PetsOrder findOrderByOrderNo(String orderNo);
//
//
//    /**
//     * 根据 临时宠物Id 查询订单信息（只允许改宠物id 只存在一条订单数据）
//     * @author wangsong
//     * @date 2020/12/30 0030 11:52
//     * @return com.ws.ldy.modules.pets.model.entity.PetsOrder
//     * @version 1.0.0
//     */
//    PetsOrder findOrderByPetId(String petId);
//
//    /**
//     * 订单绑定宠物id及状态为已绑定
//     * @author wangsong
//     * @param petInfoId
//     * @date 2020/12/30 0030 13:46
//     * @return
//     * @version 1.0.0
//     */
//    Boolean bindPetId(String petInfoId, String orderId);


    /**
     * 创建订单, 发起支付, 返回支付需要参数信息
     * @author wangsong
     * @param dto
     * @date 2020/12/30 0030 15:24
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    PayOrderResultVO createOrder(PetsOrderDTO dto);


    /**
     * 微信支付回调
     * @param xmlData
     * @return
     */
    String orderCallback(@RequestBody String xmlData);
}

