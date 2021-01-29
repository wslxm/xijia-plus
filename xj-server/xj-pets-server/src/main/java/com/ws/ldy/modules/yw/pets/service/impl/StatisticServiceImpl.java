package com.ws.ldy.modules.yw.pets.service.impl;


import com.ws.ldy.client.yw.pets.model.vo.NewLatestAnnouncementVO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapital;
import com.ws.ldy.modules.yw.pets.service.PetsCapitalService;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import com.ws.ldy.modules.yw.pets.service.PetsPetInfoService;
import com.ws.ldy.modules.yw.pets.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 统计相关
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/12/30 0030 10:26
 * @version 1.0.0
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private PetsDeclareService petsDeclareService;

    @Autowired
    private PetsPetInfoService petsPetInfoService;

    @Autowired
    private PetsCapitalService petsCapitalService;

    @Override
    public NewLatestAnnouncementVO newLatestAnnouncement() {
        // 1、帮助总次数 = 申请成功的数量
        Integer declareSuccessTotal = petsDeclareService.findSuccessTotal();
        // 2、分摊人数 = 当前正在总续费的人数
        Integer renewTotal = petsPetInfoService.renewTotal();
        // 3、互助总金额 = 已发放总额度
        PetsCapital capital = petsCapitalService.findCapital();
        BigDecimal moneyPayment = capital.getMoneyPayment();
        if (moneyPayment == null) {
            moneyPayment = new BigDecimal("0");
        }
        NewLatestAnnouncementVO vo = new NewLatestAnnouncementVO();
        vo.setRenewTotal(renewTotal);
        vo.setDeclareSuccessTotal(declareSuccessTotal);
        vo.setMoneyPayment(moneyPayment);
        return vo;
    }
}
