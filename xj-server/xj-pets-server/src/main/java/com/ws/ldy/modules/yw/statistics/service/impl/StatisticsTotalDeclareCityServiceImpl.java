package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsTotalDeclareCityMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalDeclareCity;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalDeclareCityVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsTotalDeclareCityService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 审报城市统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:19
 */
@Service
public class StatisticsTotalDeclareCityServiceImpl extends BaseIServiceImpl<StatisticsTotalDeclareCityMapper, StatisticsTotalDeclareCity> implements StatisticsTotalDeclareCityService {


    @Override
    public StatisticsTotalDeclareCityVO findStatistics() {
        // 查询所有数据
        List<StatisticsTotalDeclareCity> list = this.list(new LambdaQueryWrapper<StatisticsTotalDeclareCity>()
                .select(StatisticsTotalDeclareCity::getCity
                        ,StatisticsTotalDeclareCity::getCityDeclareMoney
                )
                .orderByDesc(StatisticsTotalDeclareCity::getCityDeclareMoney)
        );
        if (list.size() <= 0) {
            return null;
        }



        // 获取数据条数
        int num = 5;
        if(list.size() < 5){
            num = list.size();
        }

        // 2、总数折线图(前五)，默认总数降序
        List<String> citys = new ArrayList<>();
        List<BigDecimal> cityDeclareMoneys = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            citys.add(list.get(i).getCity());
            cityDeclareMoneys.add(list.get(i).getCityDeclareMoney());
        }

        // 返回
        StatisticsTotalDeclareCityVO vo = new StatisticsTotalDeclareCityVO();
        vo.setCitys(citys);
        vo.setCityDeclareMoneys(cityDeclareMoneys);
        return vo;
    }
}
