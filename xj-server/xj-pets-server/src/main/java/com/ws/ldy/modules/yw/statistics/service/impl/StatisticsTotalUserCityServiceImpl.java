package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsTotalUserCityMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalUserCity;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalUserCityVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalUserCityService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:33
 */
@Service
public class StatisticsTotalUserCityServiceImpl extends BaseIServiceImpl<StatisticsTotalUserCityMapper, StatisticsTotalUserCity> implements StatisticsTotalUserCityService {


    @Override
    public StatisticsTotalUserCityVO findStatistics() {
        // 每个城市一条数据(取数据最多的前5条)
        List<StatisticsTotalUserCity> list = this.list(new LambdaQueryWrapper<StatisticsTotalUserCity>()
                .select(StatisticsTotalUserCity::getCity
                        , StatisticsTotalUserCity::getCityUserTotal
                )
                .orderByDesc(StatisticsTotalUserCity::getCityUserTotal)
                .last(" limit 0,10")
        );
        if (list.size() <= 0) {
            return null;
        }

        List<String> citys = new ArrayList<>();
        List<Long> cityUserTotals = new ArrayList<>();
        list.forEach(p -> {
            citys.add(p.getCity());
            cityUserTotals.add(p.getCityUserTotal());
        });

        StatisticsTotalUserCityVO vo = new StatisticsTotalUserCityVO();
        vo.setCitys(citys);
        vo.setCityUserTotals(cityUserTotals);
        return vo;
    }
}
