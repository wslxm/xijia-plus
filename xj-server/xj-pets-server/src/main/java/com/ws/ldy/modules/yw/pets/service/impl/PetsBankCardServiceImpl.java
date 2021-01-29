package com.ws.ldy.modules.yw.pets.service.impl;

import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.modules.yw.pets.mapper.PetsBankCardMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsBankCardDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsBankCard;
import com.ws.ldy.modules.yw.pets.service.PetsBankCardService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 银行卡管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 18:49:29
 */
@Service
public class PetsBankCardServiceImpl extends BaseIServiceImpl<PetsBankCardMapper, PetsBankCard> implements PetsBankCardService {


    @Override
    public boolean insert(PetsBankCardDTO dto) {
        int count = this.count();
        PetsBankCard petsBankCard = dto.convert(PetsBankCard.class);
        petsBankCard.setUserId(JwtUtil.getJwtUser(request).getUserId());
        petsBankCard.setIsDefault(count == 0);
        // 默认已绑定（如果要进行第三方验证/后续在说）
        petsBankCard.setIsBinding(true);
        return this.save(petsBankCard);
    }
}
