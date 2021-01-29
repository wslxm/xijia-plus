package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.yw.pets.model.entity.PetsDeclare;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreaseDeclare;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreaseDeclareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 申报统计每小时增长
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:49:50
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsIncreaseDeclareTask {

    @Autowired
    private StatisticsIncreaseDeclareService statisticsIncreaseDeclareService;


    @Autowired
    private PetsDeclareService petsDeclareService;


    @Scheduled(cron = "0 0 0/1 * * ?")
    // @Scheduled(cron = "0/10 * * * * ?")
    private void configureTasks() {
        log.info("===>  申报统计每小时增长: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsTime();
        log.info("===>  申报统计每小时增长: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }


    /**
     * 统计指定时间内的数据
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2021/1/19 0019 14:46
     * @version 1.0.0
     */
    public void statisticsTime() {
        // 1、获取开始时间和结束时间
        List<StatisticsIncreaseDeclare> list = statisticsIncreaseDeclareService.list(new LambdaQueryWrapper<StatisticsIncreaseDeclare>()
                .select(StatisticsIncreaseDeclare::getStartTime, StatisticsIncreaseDeclare::getEndTime)
                .orderByDesc(StatisticsIncreaseDeclare::getEndTime)
                .last(" limit 0,1")
        );
        // 开始时间 (上一次的结束时间，如果为第一次统计,指定开始时间)
        LocalDateTime startTime = list.size() == 0
                ? LocalDateTimeUtil.parse("2021-01-01 00:00:00")
                : list.get(0).getEndTime();
        // 结束时间（分/秒设置为0）
        LocalDateTime endTime = LocalDateTimeUtil.getTheHour(LocalDateTime.now());
        if (endTime.equals(startTime)) {
            return;
        }

        // 2、查询数据（申报成功）
        List<PetsDeclare> petsDeclares = petsDeclareService.list(new LambdaQueryWrapper<PetsDeclare>()
                .select(PetsDeclare::getPaidInAmount)
                .between(PetsDeclare::getPayTime, startTime, endTime)
                .in(PetsDeclare::getState, Enums.Pet.DeclareState.DECLARE_STATE_3.getValue())
        );

        // 3、数据收集
        StatisticsIncreaseDeclare increaseDeclare = new StatisticsIncreaseDeclare();
        increaseDeclare.setStartTime(startTime);
        increaseDeclare.setEndTime(endTime);
        if (petsDeclares.size() == 0) {
            increaseDeclare.setDeclareNum(0L);
            increaseDeclare.setDeclareMoney(new BigDecimal("0"));
        } else {
            BigDecimal paidInAmount = BigDecimalUtil.parse(new BigDecimal(petsDeclares.stream().mapToDouble(p -> p.getPaidInAmount().doubleValue()).sum()));
            increaseDeclare.setDeclareNum(Long.parseLong(petsDeclares.size() + ""));
            increaseDeclare.setDeclareMoney(new BigDecimal(paidInAmount + ""));
        }

        // 4、添加
        statisticsIncreaseDeclareService.save(increaseDeclare);
    }
}
