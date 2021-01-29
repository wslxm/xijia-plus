package com.ws.ldy.modules.yw.pets.service.impl;

import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.yw.pets.mapper.PetsHospitalMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsHospitalDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsHospital;
import com.ws.ldy.modules.yw.pets.service.PetsHospitalService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 医院表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:26:04
 */
@Service
public class PetsHospitalServiceImpl extends BaseIServiceImpl<PetsHospitalMapper, PetsHospital> implements PetsHospitalService {


    @Override
    public Boolean insert(PetsHospitalDTO dto) {
        PetsHospital petsHospital = dto.convert(PetsHospital.class);
        petsHospital.setDisable(Enums.Base.Disable.DISABLE_0.getValue());
        Boolean result = this.save(petsHospital);
        return result;
    }
}
