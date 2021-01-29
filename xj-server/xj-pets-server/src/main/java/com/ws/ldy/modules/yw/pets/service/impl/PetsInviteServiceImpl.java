package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.yw.pets.mapper.PetsInviteMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsInviteDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInvite;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInviteConfig;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.service.PetsInviteConfigService;
import com.ws.ldy.modules.yw.pets.service.PetsInviteService;
import com.ws.ldy.modules.yw.pets.service.PetsPetInfoService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 邀请表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:38
 */
@Service
public class PetsInviteServiceImpl extends BaseIServiceImpl<PetsInviteMapper, PetsInvite> implements PetsInviteService {


    @Autowired
    private PetsInviteConfigService petsInviteConfigService;

    @Autowired
    private PetsPetInfoService petsPetInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(PetsInviteDTO dto) {
        // 当前登录人=被邀请人
        JwtUser jwtUser = JwtUtil.getJwtUser(request);
        String inUserId = jwtUser.getUserId();
        String inName = jwtUser.getUsername();

        // 判断是否重复邀请
        PetsInvite petsInvite = this.getOne(new LambdaQueryWrapper<PetsInvite>()
                .eq(PetsInvite::getUserId, dto.getUserId())
                .eq(PetsInvite::getInUserId, inUserId)
        );
        if (petsInvite != null) {
            throw new ErrorException(RType.INVITE_REPEAT);
        }

        // 获取邀请人的默认宠物
        PetsPetInfo defaultPet = petsPetInfoService.findDefaultPet(dto.getUserId());
        if (defaultPet == null) {
            throw new ErrorException(RType.INVITE_IS_NO_DEFAULT_PET);
        }
        PetsInviteConfig petsInviteConfig = petsInviteConfigService.findByTypeShare();
        if (petsInviteConfig == null) {
            throw new ErrorException(RType.INVITE_IS_NO_CONFIG);
        }
        Integer dayNum = petsInviteConfig.getDayNum();

        //增加邀请人时长
        Boolean b = petsPetInfoService.increaseDuration(defaultPet.getId(), dayNum);

        // 添加记录
        PetsInvite addPetsInvite = new PetsInvite();
        addPetsInvite.setUserId(dto.getUserId());
        addPetsInvite.setName(dto.getName());
        addPetsInvite.setInUserId(inUserId);
        addPetsInvite.setInName(inName);
        addPetsInvite.setDayNum(dayNum);
        this.save(addPetsInvite);

        return true;
    }
}
