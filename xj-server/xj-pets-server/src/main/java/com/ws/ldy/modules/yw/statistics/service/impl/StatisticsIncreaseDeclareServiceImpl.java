package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.Java8MapSort;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsIncreaseDeclareMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreaseDeclare;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreaseDeclareVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreaseDeclareService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 申报统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:49:50
 */
@Service
public class StatisticsIncreaseDeclareServiceImpl extends BaseIServiceImpl<StatisticsIncreaseDeclareMapper, StatisticsIncreaseDeclare> implements StatisticsIncreaseDeclareService {


    @Override
    public StatisticsIncreaseDeclareVO findStatistics(Integer dayNum, Integer type) {
        // 查询n天内的数据
        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
        time = LocalDateTimeUtil.getDayEnd(time);
        List<StatisticsIncreaseDeclare> list = this.list(new LambdaQueryWrapper<StatisticsIncreaseDeclare>()
                .select(StatisticsIncreaseDeclare::getEndTime,
                        StatisticsIncreaseDeclare::getDeclareMoney,
                        StatisticsIncreaseDeclare::getDeclareNum
                )
                .orderByAsc(StatisticsIncreaseDeclare::getEndTime)
                .ge(StatisticsIncreaseDeclare::getEndTime, time)
        );
        if (list.size() <= 0) {
            return null;
        }

        Map<String, List<StatisticsIncreaseDeclare>> groupByTime = null;
        if (type.equals(1)) {
            // 根据时统计
            groupByTime = list.stream().collect(
                    Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMMddHH(o.getEndTime()))
            );
        } else if (type.equals(2)) {
            // 根据天统计
            groupByTime = list.stream().collect(
                    Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMMdd(o.getEndTime()))
            );
        }else{
            return null;
        }
        groupByTime = Java8MapSort.sortByKey(groupByTime, false);
        // 计算金额
        List<String> times = new ArrayList<>();
        List<Double> declareMoneys = new ArrayList<>();
        groupByTime.forEach((k, v) -> {
            times.add(k);
            declareMoneys.add(v.stream().mapToDouble(p -> p.getDeclareMoney().doubleValue()).sum());
        });
        StatisticsIncreaseDeclareVO vo = new StatisticsIncreaseDeclareVO();
        vo.setDeclareMoneys(declareMoneys);
        vo.setTimes(times);
        return vo;
    }
}
