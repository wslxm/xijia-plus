package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.entity.PetsRiskManage;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclareFindNewDayListVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclareFindTotalUserIdVO;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import com.ws.ldy.modules.yw.pets.service.PetsRiskManageService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 风控扫描(每天一次)
 * @author wangsong
 * @date 2020/12/29 0029 19:25
 * @return
 * @version 1.0.0
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class PetsRiskManageTask {


    @Autowired
    private PetsRiskManageService petsRiskManageService;

    @Autowired
    private PetsDeclareService petsDeclareService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    private void configureTasks() {
        // 1、时间
        // ---查询连续n月内申报次数到2的
        int monthsNum = 2;
        LocalDateTime endStart = LocalDateTimeUtil.getTheHour(LocalDateTime.now());
        LocalDateTime startStart = LocalDateTimeUtil.subtract(LocalDateTime.now(), monthsNum, ChronoUnit.MONTHS);


        // 2、查询数据
        List<PetsDeclareFindNewDayListVO> newDayList = petsDeclareService.findNewDayList(startStart, endStart);
        if (newDayList == null || newDayList.size() == 0) {
            return;
        }

        // 3、分组统计
        Map<String, List<PetsDeclareFindNewDayListVO>> groupByUserIds = newDayList.stream().filter(p -> StringUtils.isNotBlank(p.getUserId()))
                .collect(Collectors.groupingBy(PetsDeclareFindNewDayListVO::getUserId));

        // 4、计算统计
        List<PetsRiskManage> petsRiskManages = new ArrayList<>();
        groupByUserIds.forEach((k, v) -> {
            if (v.size() > 1) {
                // 被扫描入风控
                PetsRiskManage riskManage = new PetsRiskManage();
                riskManage.setUserId(v.get(0).getUserId());
                riskManage.setWxName(v.get(0).getWxName());
                riskManage.setFullName(v.get(0).getFullName());
                riskManage.setPhone(v.get(0).getPhone());
                riskManage.setIdCardFront(v.get(0).getIdCard());

                // n月内数据
                BigDecimal declareMoney = BigDecimalUtil.parse(new BigDecimal(v.stream().mapToDouble(p -> p.getDeclareMoney().doubleValue()).sum()));
                riskManage.setDeclareMouth2Num(v.size());
                riskManage.setDeclareMouth2Money(declareMoney);

                // 总数据--> 查询该用户总审报次数和金额
                PetsDeclareFindTotalUserIdVO declareTotal = petsDeclareService.findTotalByUserId(v.get(0).getUserId());
                riskManage.setDeclareNum(declareTotal.getDeclareNum());
                riskManage.setDeclareMoney(declareTotal.getDeclareMoneyTotal());
                //
                petsRiskManages.add(riskManage);
            }
        });

        if (petsRiskManages.size() == 0) {
            return;
        }

        // 5、添加或编辑
        List<PetsRiskManage> addRiskManages = new ArrayList<>();
        List<PetsRiskManage> updRiskManages = new ArrayList<>();
        petsRiskManages.forEach(p -> {
            PetsRiskManage petsRiskManage = petsRiskManageService.getOne(new LambdaQueryWrapper<PetsRiskManage>()
                    .select(PetsRiskManage::getId)
                    .eq(PetsRiskManage::getUserId, p.getUserId()));
            if(petsRiskManage==null){
                //添加
                addRiskManages.add(p);

            }else{
                //更新
                petsRiskManage.setId(petsRiskManage.getId());
                updRiskManages.add(p);
            }
        });

        // 6、数据入库
        if(addRiskManages.size() >0){
            petsRiskManageService.saveBatch(addRiskManages);
        }
        if(updRiskManages.size() >0){
            petsRiskManageService.updateBatchById(updRiskManages);
        }
        log.info("===>  风控扫描并记录");
    }
}
