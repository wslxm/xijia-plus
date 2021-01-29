package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.pets.model.dto.PetsHospitalDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsHospital;

/**
 * 医院表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:26:04
 */
public interface PetsHospitalService extends IService<PetsHospital> {


    public Boolean insert( PetsHospitalDTO dto) ;
}

