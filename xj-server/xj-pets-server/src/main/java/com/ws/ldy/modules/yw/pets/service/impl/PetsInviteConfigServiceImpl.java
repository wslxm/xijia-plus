package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.yw.pets.mapper.PetsInviteConfigMapper;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInviteConfig;
import com.ws.ldy.modules.yw.pets.service.PetsInviteConfigService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 邀请增加时长配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 19:37:23
 */
@Service
public class PetsInviteConfigServiceImpl extends BaseIServiceImpl<PetsInviteConfigMapper, PetsInviteConfig> implements PetsInviteConfigService {


    @Override
    public PetsInviteConfig findByTypeShare() {
        PetsInviteConfig petsInviteConfig = this.getOne(new LambdaQueryWrapper<PetsInviteConfig>()
                .eq(PetsInviteConfig::getType, Enums.Pet.InviteConfigType.INVITE_CONFIG_TYPE_1.getValue())
        );
        return petsInviteConfig;
    }
}
