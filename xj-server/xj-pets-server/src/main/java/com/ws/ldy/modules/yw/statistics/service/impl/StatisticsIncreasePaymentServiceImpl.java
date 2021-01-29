package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.Java8MapSort;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsIncreasePaymentMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreasePayment;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreasePaymentVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreasePaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 缴费增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:13
 */
@Service
public class StatisticsIncreasePaymentServiceImpl extends BaseIServiceImpl<StatisticsIncreasePaymentMapper, StatisticsIncreasePayment> implements StatisticsIncreasePaymentService {


    @Override
    public StatisticsIncreasePaymentVO findStatistics(Integer dayNum ,Integer type) {
        // 查询n天内数据
        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
        time = LocalDateTimeUtil.getDayEnd(time);
        List<StatisticsIncreasePayment> list = this.list(new LambdaQueryWrapper<StatisticsIncreasePayment>()
                .select(StatisticsIncreasePayment::getEndTime,
                        StatisticsIncreasePayment::getPaymentMoney,
                        StatisticsIncreasePayment::getPaymentNum,
                        StatisticsIncreasePayment::getFirstMoney,
                        StatisticsIncreasePayment::getRenewMoney
                )
                .orderByAsc(StatisticsIncreasePayment::getEndTime)
                .ge(StatisticsIncreasePayment::getEndTime, time)
        );
        if (list.size() <= 0) {
            return null;
        }
        Map<String, List<StatisticsIncreasePayment>> groupByTime = null;
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
        // 计算金额
        List<String> times = new ArrayList<>();
        List<Double> moneys = new ArrayList<>();
        List<Double> firstMoneys = new ArrayList<>();
        List<Double> renewMoneys = new ArrayList<>();
        // 根据map的key升序排序
        groupByTime = Java8MapSort.sortByKey(groupByTime, false);
        groupByTime.forEach((k, v) -> {
            times.add(k);
            moneys.add(v.stream().mapToDouble(p -> p.getPaymentMoney().doubleValue()).sum());
            firstMoneys.add(v.stream().mapToDouble(p -> p.getFirstMoney().doubleValue()).sum());
            renewMoneys.add(v.stream().mapToDouble(p -> p.getRenewMoney().doubleValue()).sum());
        });

        StatisticsIncreasePaymentVO vo = new StatisticsIncreasePaymentVO();
        vo.setTimes(times);
        vo.setPaymentMoneys(moneys);
        vo.setFirstMoneys(firstMoneys);
        vo.setRenewMoneys(renewMoneys);
        return vo;
    }
}
