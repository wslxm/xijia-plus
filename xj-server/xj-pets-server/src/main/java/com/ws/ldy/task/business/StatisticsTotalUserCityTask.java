package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalUserCity;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalUserCityService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:33
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsTotalUserCityTask {


    @Autowired
    private PetsUserService petsUserService;
    @Autowired
    private StatisticsTotalUserCityService statisticsTotalUserCityService;

    @Scheduled(cron = "40 2 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        log.info("===>  用户城市总量统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsAll();
        log.info("===>  用户城市总量统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }


    /**
     * 查询所有数据来进行统计
     * @author wangsong
     * @date 2021/1/19 0019 11:24
     * @return void
     * @version 1.0.0
     */
    public void statisticsAll() {
        // 1、查询用户数据(有城市数据的)
        List<PetsUser> userList = petsUserService.list(new LambdaQueryWrapper<PetsUser>()
                .select(PetsUser::getProvince, PetsUser::getCity)
                .ne(PetsUser::getCity, "")
        );
        if (userList.size() == 0) {
            return;
        }

        // 2、根据城市分组（过滤）
        Map<String, List<PetsUser>> groupByCity = userList.stream().filter(p -> StringUtils.isNotBlank(p.getCity()))
                .collect(Collectors.groupingBy(PetsUser::getCity));


        // 3、录入最新统计数据
        List<StatisticsTotalUserCity> addList = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        groupByCity.forEach((k, v) -> {
            StatisticsTotalUserCity userCity = new StatisticsTotalUserCity();
            userCity.setCity(k);
            userCity.setCityUserTotal(Long.parseLong(v.size() + ""));
            userCity.setCityBoyUserTotal(0L);
            userCity.setCityGirlUserTotal(0L);
            userCity.setTime(time);
            addList.add(userCity);
        });

        // 4、删除所有
        statisticsTotalUserCityService.remove(new LambdaUpdateWrapper<StatisticsTotalUserCity>().ne(StatisticsTotalUserCity::getId, ""));

        // 5、添加新统计数据
        statisticsTotalUserCityService.saveBatch(addList);
    }
}
