package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclareFindDeclareSuccessCityVO;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalDeclareCity;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalDeclareCityService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 审报城市统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:19
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsTotalDeclareCityTask {

    @Autowired
    private PetsDeclareService petsDeclareService;

    @Autowired
    private StatisticsTotalDeclareCityService statisticsTotalDeclareCityService;

    @Scheduled(cron = "20 1 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        log.info("===>  审报城市统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsAll();
        log.info("===>  审报城市统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }

    /**
     * 查询所有数据来进行统计
     * @author wangsong
     * @date 2021/1/19 0019 11:24
     * @return void
     * @version 1.0.0
     */
    public void statisticsAll() {
        // 1-查询
        List<PetsDeclareFindDeclareSuccessCityVO> declareSuccessCity = petsDeclareService.findDeclareSuccessCity();
        if (declareSuccessCity == null || declareSuccessCity.size() == 0) {
            return;
        }

        // 2、分组统计(根据城市名称)
        Map<String, List<PetsDeclareFindDeclareSuccessCityVO>> cityGroups = declareSuccessCity.stream().filter(p -> StringUtils.isNotBlank(p.getCity()))
                .collect(Collectors.groupingBy(PetsDeclareFindDeclareSuccessCityVO::getCity));

        // 3、数据收集
        List<StatisticsTotalDeclareCity> addList = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        cityGroups.forEach((k, v) -> {
            // 申报总金额
            double declareMoney = v.stream().mapToDouble(p -> p.getPaidInAmount().doubleValue()).sum();
            //
            StatisticsTotalDeclareCity declareCity = new StatisticsTotalDeclareCity();
            declareCity.setCity(k);
            declareCity.setCityDeclareMoney(new BigDecimal(declareMoney + ""));
            declareCity.setCityDeclareNum(Long.parseLong(v.size() + ""));
            declareCity.setTime(time);
            addList.add(declareCity);
        });

        // 4、删除所有
        statisticsTotalDeclareCityService.remove(new LambdaUpdateWrapper<StatisticsTotalDeclareCity>().ne(StatisticsTotalDeclareCity::getId, ""));

        // 5、添加更新
        statisticsTotalDeclareCityService.saveBatch(addList);
    }
}
