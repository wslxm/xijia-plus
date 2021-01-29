package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.pets.mapper.PetsPetInfoMapper;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoFindCityVO;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalPetCity;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalPetCityService;
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
 * 宠物城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:29
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsTotalPetCityTask {


    @Autowired
    private StatisticsTotalPetCityService statisticsTotalPetCityService;


    @Autowired
    private PetsPetInfoMapper petsPetInfoMapper;


    @Scheduled(cron = "20 2 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        log.info("===>  宠物城市总量统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsAll();
        log.info("===>  宠物城市总量统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
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
        // 1、查询宠物城市
        List<PetsPetInfoFindCityVO> petsCity = petsPetInfoMapper.findPetsCity();

        // 2、宠物根据城市分组（过滤）
        Map<String, List<PetsPetInfoFindCityVO>> petsGroupCityMap = petsCity.stream().filter(p -> StringUtils.isNotBlank(p.getCity()))
                .collect(Collectors.groupingBy(PetsPetInfoFindCityVO::getCity));

        // 3、数据收集
        LocalDateTime time = LocalDateTime.now();
        List<StatisticsTotalPetCity> addList = new ArrayList<>();
        petsGroupCityMap.forEach((k, v) -> {
            Map<String, List<PetsPetInfoFindCityVO>> petsGroupTypeMap = v.stream().collect(Collectors.groupingBy(PetsPetInfoFindCityVO::getType));
            StatisticsTotalPetCity petCity = new StatisticsTotalPetCity();
            petCity.setCity(k);
            petCity.setProvince(v.get(0).getProvince());
            petCity.setCityPetTotal(Long.parseLong(v.size() + ""));
            petCity.setCityPetCatTotal(petsGroupTypeMap.containsKey(cat) ? petsGroupTypeMap.get(cat).size() : 0L);
            petCity.setCityPetDogTotal(petsGroupTypeMap.containsKey(dog) ? petsGroupTypeMap.get(dog).size() : 0L);
            petCity.setTime(time);
            addList.add(petCity);
        });

        // 4、删除所有
        statisticsTotalPetCityService.remove(new LambdaUpdateWrapper<StatisticsTotalPetCity>().ne(StatisticsTotalPetCity::getId, ""));

        // 5、添加更新
        statisticsTotalPetCityService.saveBatch(addList);
    }
}
