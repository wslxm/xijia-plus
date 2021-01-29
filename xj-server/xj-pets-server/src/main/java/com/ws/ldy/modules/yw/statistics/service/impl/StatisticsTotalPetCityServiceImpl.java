package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsTotalPetCityMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalPetCity;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalPetCityVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalPetCityService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;
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
@Service
public class StatisticsTotalPetCityServiceImpl extends BaseIServiceImpl<StatisticsTotalPetCityMapper, StatisticsTotalPetCity> implements StatisticsTotalPetCityService {


    @Override
    public StatisticsTotalPetCityVO findStatistics() {
        // 查询所有数据
        List<StatisticsTotalPetCity> list = this.list(new LambdaQueryWrapper<StatisticsTotalPetCity>()
                .select(StatisticsTotalPetCity::getCityPetTotal
                      ,StatisticsTotalPetCity::getCityPetCatTotal
                      ,StatisticsTotalPetCity::getCityPetDogTotal
                      ,StatisticsTotalPetCity::getCity
                      ,StatisticsTotalPetCity::getProvince
                )
                .orderByDesc(StatisticsTotalPetCity::getCityPetTotal)
        );
        if (list.size() <= 0) {
            return null;
        }

        // 1、地图省份-数量（所有）
        Map<String, List<StatisticsTotalPetCity>> groupByMonth = list.stream().collect(
                Collectors.groupingBy(p -> p.getProvince()));
        List<String> provinces = new ArrayList<>();
        List<Long> provincesTotals = new ArrayList<>();
        groupByMonth.forEach((k,v)->{
            provinces.add(k);
            provincesTotals.add(v.stream().mapToLong(x -> x.getCityPetTotal()).sum());
        });


        // 获取数据条数
        int num = 5;
        if(list.size() < 5){
            num = list.size();
        }

        // 2、总数折线图(前五)，默认总数降序
        List<String> totalCitys = new ArrayList<>();
        List<Long> totalCityTotals = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            totalCitys.add(list.get(i).getCity());
            totalCityTotals.add(list.get(i).getCityPetTotal());
        }


        // 3、猫总数折线图(前五)  根据 PetCatTotal -降序
        Collections.sort(list, Comparator.comparing(StatisticsTotalPetCity::getCityPetCatTotal).reversed());
        List<String> catCitys = new ArrayList<>();
        List<Long> catCityTotals = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            catCitys.add(list.get(i).getCity());
            catCityTotals.add(list.get(i).getCityPetCatTotal());
        }


        // 4、狗总数折线图(前五)  根据 PetDogTotal -降序
        Collections.sort(list, Comparator.comparing(StatisticsTotalPetCity::getCityPetDogTotal).reversed());
        List<String> dogCitys = new ArrayList<>();
        List<Long> dogCityTotals = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            dogCitys.add(list.get(i).getCity());
            dogCityTotals.add(list.get(i).getCityPetDogTotal());
        }

        // 返回
        StatisticsTotalPetCityVO vo = new StatisticsTotalPetCityVO();
        vo.setTotalCitys(totalCitys);
        vo.setTotalCityTotals(totalCityTotals);
        vo.setCatCitys(catCitys);
        vo.setCatCityTotals(catCityTotals);
        vo.setDogCitys(dogCitys);
        vo.setDogCityTotals(dogCityTotals);
        vo.setProvinces(provinces);
        vo.setProvincesTotals(provincesTotals);
        return vo;
    }
}
