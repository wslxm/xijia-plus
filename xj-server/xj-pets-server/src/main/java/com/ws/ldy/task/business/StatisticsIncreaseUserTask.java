package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreaseUser;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户增长每小时统计表（必须n小时执行一次，否则查询多条值为0的数据）
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:16
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsIncreaseUserTask {


    @Autowired
    private StatisticsIncreaseUserService statisticsIncreaseUserService;

    @Autowired
    private PetsUserService petsUserService;


    @Scheduled(cron = "0 1 0/1 * * ?")
   // @Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        log.info("===>  用户增长每小时统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsTime();
        log.info("===>  用户增长每小时统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
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
        List<StatisticsIncreaseUser> list = statisticsIncreaseUserService.list(new LambdaQueryWrapper<StatisticsIncreaseUser>()
                .select(StatisticsIncreaseUser::getStartTime, StatisticsIncreaseUser::getEndTime)
                .orderByDesc(StatisticsIncreaseUser::getEndTime)
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
        List<PetsUser> petsUsers = petsUserService.list(new LambdaQueryWrapper<PetsUser>()
                .select(PetsUser::getWxGender)
                .between(PetsUser::getCreateTime, startTime, endTime)
        );

        // 3、数据收集
        StatisticsIncreaseUser increaseUser = new StatisticsIncreaseUser();
        increaseUser.setStartTime(startTime);
        increaseUser.setEndTime(endTime);
        if (petsUsers.size() == 0) {
            increaseUser.setUserTotal(0L);
            increaseUser.setBoyUserTotal(0L);
            increaseUser.setGirlUserTotal(0L);
        } else {
            Map<Integer, List<PetsUser>> userGroupWxGenders = petsUsers.stream().filter(p -> p.getWxGender() != null)
                    .collect(Collectors.groupingBy(PetsUser::getWxGender));
            increaseUser.setUserTotal(Long.parseLong(petsUsers.size() + ""));
            increaseUser.setBoyUserTotal(userGroupWxGenders.containsKey(1) ? userGroupWxGenders.get(1).size() : 0L);
            increaseUser.setGirlUserTotal(userGroupWxGenders.containsKey(2) ? userGroupWxGenders.get(2).size() : 0L);
        }

        // 4、添加
        statisticsIncreaseUserService.save(increaseUser);
    }
}
