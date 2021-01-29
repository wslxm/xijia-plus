package com.ws.ldy.modules.yw.pets.service.impl;

import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.yw.pets.mapper.PetsCapitalConfigMapper;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapitalConfig;
import com.ws.ldy.modules.yw.pets.model.vo.CapitalFindFeeVO;
import com.ws.ldy.modules.yw.pets.service.PetsCapitalConfigService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 互助资金抽成配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:52:46
 */
@Service
public class PetsCapitalConfigServiceImpl extends BaseIServiceImpl<PetsCapitalConfigMapper, PetsCapitalConfig> implements PetsCapitalConfigService {


    @Override
    public PetsCapitalConfig findCapitalConfig() {
        List<PetsCapitalConfig> list = this.list();
        if (list.size() <= 0) {
            throw new ErrorException(RType.SYS_CONFIG_NO_LESS_THAN_ONE);
        }
        return list.get(0);
    }

    @Override
    public CapitalFindFeeVO findFee(BigDecimal moneyTotal) {
        PetsCapitalConfig capitalConfig = this.findCapitalConfig();
        // 计算当前平台抽成（大于0.02才抽成）
        BigDecimal platformFee = new BigDecimal("0");
        if (moneyTotal.doubleValue() > 0.02) {
            platformFee = BigDecimalUtil.multiply(moneyTotal, new BigDecimal(capitalConfig.getPlatformPercentage() + ""));
            platformFee = BigDecimalUtil.parseUP(platformFee);
        }
        // 计算微信平台抽成(大于0.01才抽成)
        BigDecimal wxChannelFee = new BigDecimal("0");
        if (moneyTotal.doubleValue() > 0.01) {
            // 计算微信渠道手续费
            wxChannelFee = BigDecimalUtil.multiply(moneyTotal, new BigDecimal(capitalConfig.getWxPercentage() + ""));
            wxChannelFee = BigDecimalUtil.parseUP(wxChannelFee);
        }
        // 计算支付宝渠道手续费

        // 返回
        CapitalFindFeeVO vo = new CapitalFindFeeVO();
        vo.setPlatformFee(platformFee);
        vo.setWxChannelFee(wxChannelFee);
        return vo;
    }
}
