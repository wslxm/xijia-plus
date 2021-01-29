package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.yw.pets.mapper.PetsPetInfoMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsPetInfoDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoVO;
import com.ws.ldy.modules.yw.pets.service.PetsPetInfoService;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.yw.third.WxAppSubscribeSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
@Service
public class PetsPetInfoServiceImpl extends BaseIServiceImpl<PetsPetInfoMapper, PetsPetInfo> implements PetsPetInfoService {


//    @Autowired
//    private PetsOrderService petsOrderService;
//
//    @Autowired
//    private PetsMonthFeeService petsMonthFeeService;

    @Autowired
    private PetsUserService petsUserService;

    // 微信订阅消息
    @Autowired
    private WxAppSubscribeSendUtil wxAppSubscribeSendUtil;

    @Override
    public Integer renewTotal() {
        // 计算未过期的宠物
        List<PetsPetInfo> petList = this.list(new LambdaQueryWrapper<PetsPetInfo>()
                .select(PetsPetInfo::getExpirationTime, PetsPetInfo::getId, PetsPetInfo::getUserId)
                .ge(PetsPetInfo::getExpirationTime, LocalDateTime.now())
        );
        if (petList.size() == 0) {
            return petList.size();
        }
        // 根据用户id去重
        petList = petList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(PetsPetInfo::getUserId))), ArrayList::new));
        return petList.size();
    }


//    @Override
//    public String bind(boolean isAutoRenew, Integer monthNum, String userId) {
//        // 1、计算时间， 添加一点临时数据，尽管看起来没什么用
//        // 获取绑定时间
//        LocalDateTime time = LocalDateTime.now();
//        // 计算过期时间（指定月后的今天23:59:59.99）
//        LocalDateTime expirationTime = LocalDateTimeUtil.plus(time, monthNum, ChronoUnit.MONTHS);
//        PetsPetInfo petsPetInfo = new PetsPetInfo();
//        petsPetInfo.setExpirationTime(expirationTime);
//        petsPetInfo.setApplyTime(time);
//        petsPetInfo.setJoinTime(time);
//        // 互助次数/ 是否自动续费
//        petsPetInfo.setCumulativeTime(0);
//        petsPetInfo.setIsAutoRenew(isAutoRenew);
//        petsPetInfo.setUserId(userId);
//        boolean result = this.save(petsPetInfo);
//        return petsPetInfo.getId();
//    }


    @Override
    public String bind(boolean isAutoRenew, Integer monthNum, String petId) {
        // 1、查询宠物信息
        PetsPetInfo petsPetInfo = this.getOne(new LambdaQueryWrapper<PetsPetInfo>().eq(PetsPetInfo::getId, petId));
        LocalDateTime time = LocalDateTime.now();

        // 2、计算过期时间
        LocalDateTime expirationTime = petsPetInfo.getExpirationTime();
        if (expirationTime == null) {
            // 新添加，今天开始计算
            expirationTime = LocalDateTime.now();
        }
        expirationTime = LocalDateTimeUtil.plus(expirationTime, monthNum, ChronoUnit.MONTHS);


        // 3、判断当前是否连续续费( 当前时间 < (到期时间+1天后的时间) = true 连续续费 )
        // ---- 0、判断是否为首次支付,是 applyTime 设置为当前时间
        // ---- 1、获取续费前到期时间 +1天的时间
        // ---- 2、判断到期时间是否大于当前时间(true 连续续费, false= 非连续续费)  // isBefore --> t1 < t2 = true
        // ---- 3、非连续续费刷新重续费时间为当前时间
        LocalDateTime applyTime = petsPetInfo.getApplyTime();
        LocalDateTime joinTime = petsPetInfo.getJoinTime();
        if (petsPetInfo.getExpirationTime() == null) {
            applyTime = time;
            joinTime = time;
        } else {
            LocalDateTime addOneExpirationTime = LocalDateTimeUtil.plus(petsPetInfo.getExpirationTime(), 1, ChronoUnit.DAYS);
            boolean isContinuous = LocalDateTimeUtil.isBefore(LocalDateTime.now(), addOneExpirationTime);
            if (!isContinuous) {
                applyTime = time;
            }
        }


        // 4、编辑宠物信息
        PetsPetInfo updPetsPetInfo = new PetsPetInfo();
        updPetsPetInfo.setId(petsPetInfo.getId());
        updPetsPetInfo.setApplyTime(applyTime);
        updPetsPetInfo.setJoinTime(joinTime);
        updPetsPetInfo.setExpirationTime(expirationTime);
        updPetsPetInfo.setIsAutoRenew(isAutoRenew);
        boolean result = this.updateById(updPetsPetInfo);
        return petsPetInfo.getId();
    }


    @Override
    @Transactional(rollbackFor = ErrorException.class)
    public Boolean updIsDefault(String petId, Boolean isDefault) {
        // 清除当前用户所有默认token
        this.update(new LambdaUpdateWrapper<PetsPetInfo>()
                .set(PetsPetInfo::getIsDefault, false)
                .eq(PetsPetInfo::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
        // 设置当前宠物为默认
        PetsPetInfo updPetInfo = new PetsPetInfo();
        updPetInfo.setId(petId);
        updPetInfo.setIsDefault(isDefault);
        boolean result = this.updateById(updPetInfo);
        if (!result) {
            // 回滚删除操作
            throw new ErrorException(RType.PETS_NO_DEFAULT_ERROR);
        }
        return true;
    }


    @Override
    public Boolean upd(PetsPetInfoDTO dto) {
        PetsPetInfo petsPetInfo = dto.convert(PetsPetInfo.class);
        boolean result = this.updateById(petsPetInfo);
        return result;
    }


    @Override
    @Transactional
    public String insert(PetsPetInfoDTO dto) {
        //
        String userId = JwtUtil.getJwtUser(request).getUserId();
        // 宠物数量
        int petNum = this.count(new LambdaQueryWrapper<PetsPetInfo>().eq(PetsPetInfo::getUserId, userId));
        //
        PetsPetInfo petsPetInfo = dto.convert(PetsPetInfo.class);
        petsPetInfo.setUserId(userId);
        petsPetInfo.setIsAutoRenew(false);
        petsPetInfo.setIsDefault(petNum == 0);
        petsPetInfo.setCumulativeTime(0);
        petsPetInfo.setHelpNum(0);
        petsPetInfo.setInHelpNum(0);
        boolean res = this.save(petsPetInfo);
        // 编辑主人省市区
        petsUserService.updAddress(dto.getProvince(), dto.getCity(), dto.getArea(), userId);

        // 发送订阅消息-->   // 审核内容  // 审核时间     // 审核结果    // 备注
        String wxOrderId = petsUserService.findWxOrderId(userId);
        String checkContent = "宠物认证";
        String checkTime = LocalDateTimeUtil.parse(LocalDateTimeUtil.now());
        String checkResult = "通过";
        String remark = "宠物认证通过";
        wxAppSubscribeSendUtil.checkMsg(wxOrderId, checkContent, checkTime, checkResult, remark);
        return petsPetInfo.getId();
    }


//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String updPetBind(UpdPetBindDTO dto) {
//        // 查询订单信息
//        PetsOrder petsOrder = petsOrderService.findOrderByOrderNo(dto.getOrderNo());
//        String petId = null;
//        if (dto.getUpdType().equals(1)) {
//            // 1 - 绑定原宠物(必传id)
//            if (StringUtils.isBlank(dto.getOldPetId()) || StringUtils.isBlank(dto.getOldPetId())) {
//                throw new ErrorException(RType.PARAM_MISSING);
//            }
//            petId = dto.getOldPetId();
//            this.bindOldPets(petsOrder, petId);
//        } else if (dto.getUpdType().equals(2)) {
//            // 2 - 添加新宠物
//            PetsPetInfo petInfo = dto.convert(PetsPetInfo.class);
//            this.bindNewPets(petsOrder, petInfo);
//            petId = petInfo.getId();
//        }
//        // 编辑主人省市区
//        String userId = JwtUtil.getJwtUser(request).getUserId();
//        petsUserService.updAddress(dto.getProvince(), dto.getCity(), dto.getArea(), userId);
//        return petId;
//    }


//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String updPetBindTwo(UpdPetBindTwoDTO dto) {
//        // 查询订单信息
//        PetsOrder petsOrder = petsOrderService.findOrderByPetId(dto.getPetId());
//        String petId = null;
//        if (dto.getUpdType().equals(1)) {
//            // 1 - 绑定原宠物(必传id)
//            if (StringUtils.isBlank(dto.getOldPetId()) || StringUtils.isBlank(dto.getOldPetId())) {
//                throw new ErrorException(RType.PARAM_MISSING);
//            }
//            petId = dto.getOldPetId();
//            this.bindOldPets(petsOrder, petId);
//        } else if (dto.getUpdType().equals(2)) {
//            // 2 - 添加新宠物
//            PetsPetInfo petInfo = dto.convert(PetsPetInfo.class);
//            this.bindNewPets(petsOrder, petInfo);
//            petId = petInfo.getId();
//        }
//        // 编辑主人省市区
//        String userId = JwtUtil.getJwtUser(request).getUserId();
//        petsUserService.updAddress(dto.getProvince(), dto.getCity(), dto.getArea(), userId);
//        return petId;
//    }

    @Override
    public Boolean updDeclareSuccess(String petId) {
        baseMapper.addOneHelpNum(petId);
        baseMapper.addOneInHelpNum(petId);
        return true;
    }


    @Override
    public PetsPetInfo findDefaultPet(String userId) {
        PetsPetInfo petsPetInfo = this.getOne(new LambdaQueryWrapper<PetsPetInfo>()
                .eq(PetsPetInfo::getUserId, userId)
                .eq(PetsPetInfo::getIsDefault, true)
        );
        return petsPetInfo;
    }


    @Override
    public Boolean increaseDuration(String petId, Integer dayNum) {
        PetsPetInfo petsPetInfo = this.getOne(new LambdaQueryWrapper<PetsPetInfo>().eq(PetsPetInfo::getId, petId));
        // 重新计算过期时间（+ n天）
        LocalDateTime expirationTime = petsPetInfo.getExpirationTime();
        expirationTime = LocalDateTimeUtil.plus(expirationTime, dayNum, ChronoUnit.DAYS);
        //
        boolean result = this.update(new LambdaUpdateWrapper<PetsPetInfo>()
                .set(PetsPetInfo::getExpirationTime, expirationTime)
                .eq(PetsPetInfo::getId, petId)
        );
        return result;
    }

    @Override
    public PetsPetInfo findId(String petId) {
        return this.getById(petId);
    }

    @Override
    public Page<PetsPetInfoVO> findPage(Page<PetsPetInfo> page,
                                        String userId,
                                        String nickname,
                                        String type,
                                        LocalDateTime startJoinTime,
                                        LocalDateTime endJoinTime
    ) {
        return baseMapper.findPage(page, userId, nickname, type, startJoinTime, endJoinTime);
    }


//    /**
//     * 绑定原宠物
//     * @return
//     */
//    private Boolean bindOldPets(PetsOrder order, String oldPetId) {
//        // 是否绑定
//        if (order.getOrderState().equals(Enums.Pet.OrderState.ORDER_STATE_3.getValue())) {
//            throw new ErrorException(RType.ORDER_BOUND);
//        }
//        // 是否支付成功
//        if (!order.getOrderState().equals(Enums.Pet.OrderState.ORDER_STATE_2.getValue())) {
//            throw new ErrorException(RType.ORDER_FAIL);
//        }
//        // 查询月费信息
//        PetsMonthFeeVO payFee = petsMonthFeeService.findPayFeeById(order.getMonthFeeId(), order.getUserId());
//        // 增加原宠物时长
//        this.renew(order.getIsAutoRenew(), payFee.getMonthNum(), oldPetId);
//        // 删除临时宠物
//        this.removeById(order.getPetId());
//        // 更新订单为绑定宠物状态及 绑定新宠物id
//        petsOrderService.bindPetId(oldPetId, order.getId());
//        //
//        return true;
//    }
//
//
//    /**
//     * 绑定新宠物
//     * @return
//     */
//    private Boolean bindNewPets(PetsOrder order, PetsPetInfo petsPetInfo) {
//        // 是否绑定
//        if (order.getOrderState().equals(Enums.Pet.OrderState.ORDER_STATE_3.getValue())) {
//            throw new ErrorException(RType.ORDER_BOUND);
//        }
//        // 是否支付成功
//        if (!order.getOrderState().equals(Enums.Pet.OrderState.ORDER_STATE_2.getValue())) {
//            throw new ErrorException(RType.ORDER_FAIL);
//        }
//        String userId = JwtUtil.getJwtUser(request).getUserId();
//        // 1、查询当前用户是否存在其他默认宠物
//        int petDefaulCount = this.count(new LambdaQueryWrapper<PetsPetInfo>()
//                .eq(PetsPetInfo::getUserId, userId)
//                .eq(PetsPetInfo::getIsDefault, false)
//        );
//        // 查询月费信息
//        PetsMonthFeeVO payFee = petsMonthFeeService.findPayFeeById(order.getMonthFeeId(), userId);
//        // 获取绑定时间
//        LocalDateTime time = LocalDateTime.now();
//        // 计算过期时间（指定月后的今天23:59:59.99）
//        LocalDateTime expirationTime = LocalDateTimeUtil.plus(time, payFee.getMonthNum(), ChronoUnit.MONTHS);
//        //
//        petsPetInfo.setId(order.getPetId());
//        petsPetInfo.setExpirationTime(expirationTime);
//        petsPetInfo.setApplyTime(time);
//        petsPetInfo.setJoinTime(time);
//        petsPetInfo.setIsDefault(petDefaulCount == 0);
//        this.updateById(petsPetInfo);
//        // 更新订单为绑定宠物状态
//        petsOrderService.bindPetId(petsPetInfo.getId(), order.getId());
//        return true;
//    }
}


