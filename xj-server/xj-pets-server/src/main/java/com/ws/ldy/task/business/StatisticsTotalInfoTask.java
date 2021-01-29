package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapital;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserFindUserCountVO;
import com.ws.ldy.modules.yw.pets.service.PetsCapitalService;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import com.ws.ldy.modules.yw.pets.service.PetsPetInfoService;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalInfo;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 平台基础数据统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:22
 */
@SuppressWarnings("all")
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsTotalInfoTask {

    @Autowired
    private StatisticsTotalInfoService statisticsTotalInfoService;
    @Autowired
    private PetsCapitalService petsCapitalService;
    @Autowired
    private PetsPetInfoService petsPetInfoService;
    @Autowired
    private PetsUserService petsUserService;
    @Autowired
    private PetsDeclareService petsDeclareService;

    @Scheduled(cron = "40 1 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        log.info("===>  平台基础数据统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsAll();
        log.info("===>  平台基础数据统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }


    /**
     * 统计所有数据来进行统计
     * @author wangsong
     * @date 2021/1/19 0019 11:25
     * @return void
     * @version 1.0.0
     */
    public void statisticsAll() {
        String dog = "DOG";
        String cat = "CAT";
        // 平台资金数据
        PetsCapital capital = petsCapitalService.findCapital();
        // 用户总数(男/女)
        PetsUserFindUserCountVO userCount = petsUserService.findUserCount();
        // 宠物总数(狗/猫)
        int petCount = petsPetInfoService.count();
        int petDogCount = petsPetInfoService.count(new LambdaQueryWrapper<PetsPetInfo>().eq(PetsPetInfo::getType, dog));
        int petCatCount = petsPetInfoService.count(new LambdaQueryWrapper<PetsPetInfo>().eq(PetsPetInfo::getType, cat));
        // 续费用户总数
        Integer renewCount = petsPetInfoService.renewTotal();
        // 申报成功总数
        Integer declareSuccessTotal = petsDeclareService.findSuccessTotal();
        //
        StatisticsTotalInfo totalInfo = new StatisticsTotalInfo();
        totalInfo.setMoneyTotal(capital.getMoneyTotal());         // 平台资金累积总金额
        totalInfo.setMoneyProfit(capital.getMoneyProfit());       // 平台收益总额度
        totalInfo.setMoneySurplus(capital.getMoneySurplus());     // 资金池剩余额度
        totalInfo.setMoneyPayment(capital.getMoneyPayment());     // 平台互助金已发放总金额
        totalInfo.setPetsTotal(Long.parseLong(petCount + ""));         // 平台宠物总数
        totalInfo.setPetDogTotal(Long.parseLong(petDogCount + ""));     // 平台狗宠物总数
        totalInfo.setPetCatTotal(Long.parseLong(petCatCount + ""));     // 平台猫宠物总数
        totalInfo.setUserTotal(Long.parseLong(userCount.getUserTotal() + ""));          // 平台用户总数
        totalInfo.setGirlUserTotal(Long.parseLong(userCount.getGirlUserTotal() + ""));  // 平台女性总数
        totalInfo.setBoyUserTotal(Long.parseLong(userCount.getBoyUserTotal() + ""));    // 平台男性总数
        totalInfo.setDeclareUserTotal(Long.parseLong(declareSuccessTotal + ""));        // 申报成功用户总数
        totalInfo.setRenewalUserTotal(Long.parseLong(renewCount + ""));                 // 平台正在续费用户总数
        totalInfo.setTime(LocalDateTime.now());                                            // 统计时间 ( 根据统计时间间隔 决定 数据条数)
        boolean result = statisticsTotalInfoService.save(totalInfo);
    }
}
