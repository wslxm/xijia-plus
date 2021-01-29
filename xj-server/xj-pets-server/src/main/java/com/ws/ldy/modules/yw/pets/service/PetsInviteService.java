package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.dto.PetsInviteDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInvite;

/**
 * 邀请表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:38
 */
public interface PetsInviteService extends IService<PetsInvite> {

    /**
     * 邀请添加
     * @author wangsong
     * @param dto
     * @date 2020/12/31 0031 16:30
     * @return void
     * @version 1.0.0
     */
    Boolean insert(PetsInviteDTO dto);

}

