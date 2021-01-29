package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.dto.PetsPetInfoDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoVO;

import java.time.LocalDateTime;

/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
public interface PetsPetInfoService extends IService<PetsPetInfo> {


    /**
     * 续费用户总数/  先计算续费宠物数，根据用户id去重
     * @author wangsong
     * @date 2020/12/30 0030 10:34
     * @return java.lang.Integer
     * @version 1.0.0
     */
    Integer renewTotal();


    /**
     * 支付成功绑定临时宠物
     * @author wangsong
     * @param isAutoRenew 是否自动续费
     * @param monthNum 支付月数
     * @param  petId 用户id
     * @date 2020/12/30 0030 10:34
     * @return 宠物id
     * @version 1.0.0
     */
    String bind(boolean isAutoRenew, Integer monthNum, String petId);


//    /**
//     * 续费/ =添加宠物-支付成功后
//     * @author wangsong
//     * @param isAutoRenew 是否自动续费
//     * @param monthNum 支付月数
//     * @param  petId 宠物id
//     * @date 2020/12/30 0030 10:34
//     * @return 宠物id
//     * @version 1.0.0
//     */
//    String renew(boolean isAutoRenew, Integer monthNum, String petId);


    /**
     * 设置用户的默认宠物
     * @author wangsong
     * @date 2020/12/30 0030 14:05
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean updIsDefault(String petId, Boolean isDefault);


    /**
     * 编辑（用户支付成功后 或 成功后到个人中心页进行资料编辑）
     * @author wangsong
     * @date 2020/12/30 0030 14:05
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(PetsPetInfoDTO dto);


    /**
     * 添加宠物
     * @param dto
     * @return
     */
    String insert(PetsPetInfoDTO dto);


//    /**
//     * 支付成功后立即绑定
//     * @author wangsong
//     * @param dto
//     * @date 2020/12/31 0031 14:12
//     * @return 宠物id
//     * @version 1.0.0
//     */
//    String updPetBind(@RequestBody @Validated UpdPetBindDTO dto);
//
//    /**
//     * 支付成功后没有立即绑定, 个人页进行绑定
//     * @author wangsong
//     * @param dto
//     * @date 2020/12/31 0031 14:12
//     * @return 宠物id
//     * @version 1.0.0
//     */
//    String updPetBindTwo(@RequestBody @Validated UpdPetBindTwoDTO dto);


    /**
     * 申报成功后个人宠物被帮助次数 +1, 其他人在续费的宠物帮助次数+1
     * @author wangsong
     * @param petId
     * @date 2020/12/31 0031 14:12
     * @return 宠物id
     * @version 1.0.0
     */
    Boolean updDeclareSuccess(String petId);


    /**
     * 获取指定用户的默认宠物数据
     * @author wangsong
     * @date 2020/12/31 0031 14:12
     * @return com.ws.ldy.common.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    PetsPetInfo findDefaultPet(String userId);


    /**
     * 增加指定宠物的时长
     * @author wangsong
     * @param petId
     * @param dayNum 增加天数
     * @date 2020/12/31 0031 14:12
     * @return com.ws.ldy.common.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    Boolean increaseDuration(String petId, Integer dayNum);


    /**
     * id 查询
     * @author wangsong
     * @param petId
     * @date 2020/12/31 0031 14:12
     * @return com.ws.ldy.common.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    PetsPetInfo findId(String petId);


    /**
     * 分页查询宠物信息
     * <P>
     *   统计报销次数，报销总金额
     * </P>
     * @param page
     * @return
     */
    Page<PetsPetInfoVO> findPage(Page<PetsPetInfo> page,
                                 String userId,
                                 String nickname,
                                 String type,
                                 LocalDateTime startJoinTime,
                                 LocalDateTime endJoinTime
    );
}

