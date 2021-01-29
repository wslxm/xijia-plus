package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInviteConfig;

/**
 * 邀请增加时长配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 19:37:23
 */
public interface PetsInviteConfigService extends IService<PetsInviteConfig> {


    /**
     * 查询分享邀请获取时长配置
     * @return
     */
    public PetsInviteConfig findByTypeShare();
}

