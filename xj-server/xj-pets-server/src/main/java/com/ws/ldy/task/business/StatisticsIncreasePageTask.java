package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPageVisit;
import com.ws.ldy.modules.yw.pets.service.PetsPageVisitService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreasePage;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreasePageService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 页面统计每小时增长表( 统计数量= 页面*统计时间)
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:09
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsIncreasePageTask {


    @Autowired
    private StatisticsIncreasePageService statisticsIncreasePageService;

    @Autowired
    private PetsPageVisitService petsPageVisitService;

    @Scheduled(cron = "20 0 0/1 * * ?")
   // @Scheduled(cron = "0/10 * * * * ?")
    private void configureTasks() {
        log.info("===>  页面统计每小时增长: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsTime();
        log.info("===>  页面统计每小时增长: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }


    /**
     * 统计指定时间内的数据
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2021/1/19 0019 14:46
     * @version 1.0.0
     */
    public void statisticsTime() {
        // 获取所有页面名称
        List<PetsPageVisit> pageVisitNames = petsPageVisitService.list(new LambdaQueryWrapper<PetsPageVisit>()
                .select(PetsPageVisit::getPageName, PetsPageVisit::getPageUrl)
                .groupBy(PetsPageVisit::getPageName));

        // 1、获取开始时间和结束时间
        List<StatisticsIncreasePage> list = statisticsIncreasePageService.list(new LambdaQueryWrapper<StatisticsIncreasePage>()
                .select(StatisticsIncreasePage::getStartTime, StatisticsIncreasePage::getEndTime)
                .orderByDesc(StatisticsIncreasePage::getEndTime)
                .last(" limit 0,1")
        );
        // 开始时间 (上一次的结束时间，如果为第一次统计,指定开始时间)
        LocalDateTime startTime = list.size() == 0
                ? LocalDateTimeUtil.parse("2021-01-01 00:00:00")
                : list.get(0).getEndTime();
        // 结束时间（分/秒设置为0）
        LocalDateTime endTime = LocalDateTimeUtil.getTheHour(LocalDateTime.now());
        if(endTime.equals(startTime)){
            return;
        }

        // 2、查询数据
        List<PetsPageVisit> pageVisits = petsPageVisitService.list(new LambdaQueryWrapper<PetsPageVisit>()
                .select(PetsPageVisit::getPageName)
                .between(PetsPageVisit::getCreateTime, startTime, endTime)
        );

        // 3、根据页面统计
        Map<String, List<PetsPageVisit>> pageGroupNameMap = pageVisits.stream().filter(p -> StringUtils.isNotBlank( p.getPageName()) )
                .collect(Collectors.groupingBy(PetsPageVisit::getPageName));


        // 4、数据收集
        List<StatisticsIncreasePage> statisticsIncreasePage = new ArrayList<>();
        for (PetsPageVisit pageVisitName : pageVisitNames) {
            StatisticsIncreasePage increasePage = new StatisticsIncreasePage();
            increasePage.setStartTime(startTime);
            increasePage.setEndTime(endTime);
            increasePage.setPageName(pageVisitName.getPageName());
            increasePage.setPageUrl(pageVisitName.getPageUrl());
            if (pageVisits.size() == 0) {
                increasePage.setPageTotal(0L);
            } else {
                boolean res = pageGroupNameMap.containsKey(pageVisitName.getPageName());
                increasePage.setPageTotal(res ? pageGroupNameMap.get(pageVisitName.getPageName()).size() : 0L);
            }
            statisticsIncreasePage.add(increasePage);
        }

        // 5、添加
        statisticsIncreasePageService.saveBatch(statisticsIncreasePage);
    }
}
