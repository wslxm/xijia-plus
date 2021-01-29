package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.Java8MapSort;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsIncreaseUserMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreaseUser;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreaseUserVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreaseUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:16
 */
@Service
public class StatisticsIncreaseUserServiceImpl extends BaseIServiceImpl<StatisticsIncreaseUserMapper, StatisticsIncreaseUser> implements StatisticsIncreaseUserService {



    @Override
    public StatisticsIncreaseUserVO findStatistics(Integer dayNum) {
        // 查询n天内的数据
        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
        time = LocalDateTimeUtil.getDayEnd(time);
        List<StatisticsIncreaseUser> list = this.list(new LambdaQueryWrapper<StatisticsIncreaseUser>()
                .select(StatisticsIncreaseUser::getEndTime,
                        StatisticsIncreaseUser::getUserTotal
                )
                .orderByAsc(StatisticsIncreaseUser::getEndTime)
                .ge(StatisticsIncreaseUser::getEndTime, time)
        );
        if (list.size() <= 0) {
            return null;
        }

        // 根据天统计
        Map<String, List<StatisticsIncreaseUser>> groupByTime = list.stream().collect(
                Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMMdd(o.getEndTime()))
        );

        // 根据map的key升序排序
        groupByTime = Java8MapSort.sortByKey(groupByTime, false);
        // 计算人数
        List<String> times = new ArrayList<>();
        List<Long> userTotals = new ArrayList<>();
        groupByTime.forEach((k, v) -> {
            times.add(k);
            userTotals.add(v.stream().mapToLong(p -> p.getUserTotal().longValue()).sum());
        });

        StatisticsIncreaseUserVO vo = new StatisticsIncreaseUserVO();
        vo.setUserTotals(userTotals);
        vo.setTimes(times);
        return vo;
    }
}
