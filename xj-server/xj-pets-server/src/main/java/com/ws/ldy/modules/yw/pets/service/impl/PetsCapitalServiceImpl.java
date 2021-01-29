package com.ws.ldy.modules.yw.pets.service.impl;

import com.ws.ldy.modules.yw.pets.mapper.PetsCapitalMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsCapitalDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapital;
import com.ws.ldy.modules.yw.pets.service.PetsCapitalService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 互助资金表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:52:40
 */
@Service
public class PetsCapitalServiceImpl extends BaseIServiceImpl<PetsCapitalMapper, PetsCapital> implements PetsCapitalService {

    @Override
    public PetsCapital findCapital() {
        List<PetsCapital> list = this.list();
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return new PetsCapital();
        }
    }

    // 加锁，防止资金表触发乐观锁 synchronized + 乐观锁双重验证
    @Override
    public synchronized BigDecimal updCapital(PetsCapitalDTO dto) {
        PetsCapital petsCapital = dto.convert(PetsCapital.class);
        boolean result = false;
        // 防止触发乐观锁导致资金操作失败
        while (true) {
            result = this.updateById(petsCapital);
            if (result) {
                break;
            }
        }
        return petsCapital.getMoneyTotal();
    }
}
