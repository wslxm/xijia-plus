package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPageVisit;
import com.ws.ldy.modules.yw.pets.service.PetsPageVisitService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalPage;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalPageService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 关键页访问统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:26
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsTotalPageTask {


    @Autowired
    private PetsPageVisitService petsPageVisitService;
    @Autowired
    private StatisticsTotalPageService statisticsTotalPageService;


    @Scheduled(cron = "0 2 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        log.info("===>  关键页访问统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsAll();
        log.info("===>  关键页访问统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }


    /**
     * 统计所有数据来进行统计
     * @author wangsong
     * @date 2021/1/19 0019 11:25
     * @return void
     * @version 1.0.0
     */
    public void statisticsAll() {
        // 1、查询所有访问数据
        List<PetsPageVisit> list = petsPageVisitService.list(new LambdaQueryWrapper<PetsPageVisit>()
                .select(PetsPageVisit::getPageName, PetsPageVisit::getPageUrl,PetsPageVisit::getCreateTime)
        );
        if (list.size() == 0) {
            return;
        }
        // 2、分组统计(根据页面名称)
        Map<String, List<PetsPageVisit>> pageGroupNameMap = list.stream().filter(p -> StringUtils.isNotBlank(p.getPageName()))
                .collect(Collectors.groupingBy(PetsPageVisit::getPageName));

        // 3、数据收集
        LocalDateTime time = LocalDateTime.now();
        List<StatisticsTotalPage> addList = new ArrayList<>();
        pageGroupNameMap.forEach((k, v) -> {
            StatisticsTotalPage totalPage = new StatisticsTotalPage();
            totalPage.setPageUrl(v.get(0).getPageUrl());
            totalPage.setPageName(k);
            // 获取指定时间内数据(今天)
            LocalDateTime dayStartTime = LocalDateTimeUtil.getDayStart(LocalDateTimeUtil.now());
            List<PetsPageVisit> dayTotal = v.stream().filter(p -> LocalDateTimeUtil.isAfter(p.getCreateTime(), dayStartTime)).collect(Collectors.toList());
            // 7 天内
            LocalDateTime day7StartTime = LocalDateTimeUtil.getDayStart(LocalDateTimeUtil.subtract(LocalDateTime.now(), 7, ChronoUnit.DAYS));  //LocalDateTimeUtil.getDayStart(LocalDateTimeUtil.now());
            List<PetsPageVisit> day7Total = v.stream().filter(p -> LocalDateTimeUtil.isAfter(p.getCreateTime(), day7StartTime)).collect(Collectors.toList());
            // 30 天内
            LocalDateTime day30StartTime = LocalDateTimeUtil.getDayStart(LocalDateTimeUtil.subtract(LocalDateTime.now(), 30, ChronoUnit.DAYS));  //LocalDateTimeUtil.getDayStart(LocalDateTimeUtil.now());
            List<PetsPageVisit> day30Total = v.stream().filter(p -> LocalDateTimeUtil.isAfter(p.getCreateTime(), day30StartTime)).collect(Collectors.toList());
            //
            totalPage.setPageDayTotal(Long.parseLong(dayTotal.size() + ""));
            totalPage.setPageDay7Total(Long.parseLong(day7Total.size() + ""));
            totalPage.setPageDay30Total(Long.parseLong(day30Total.size() + ""));
            //
            totalPage.setPageTotal(Long.parseLong(v.size() + ""));
            totalPage.setTime(time);
            addList.add(totalPage);
        });

        // 4、删除所有
        statisticsTotalPageService.remove(new LambdaUpdateWrapper<StatisticsTotalPage>().ne(StatisticsTotalPage::getId, ""));

        // 5、添加更新
        statisticsTotalPageService.saveBatch(addList);
    }
}
