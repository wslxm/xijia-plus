package com.ws.ldy.modules.yw.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.Java8MapSort;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.modules.yw.statistics.mapper.StatisticsIncreasePageMapper;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreasePage;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreasePageVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreasePageService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 页面统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:09
 */
@Service
public class StatisticsIncreasePageServiceImpl extends BaseIServiceImpl<StatisticsIncreasePageMapper, StatisticsIncreasePage> implements StatisticsIncreasePageService {


    @Override
    public StatisticsIncreasePageVO findStatistics(Integer dayNum) {
        // 查询n天内数据
        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
        time = LocalDateTimeUtil.getDayEnd(time);
        List<StatisticsIncreasePage> list = this.list(new LambdaQueryWrapper<StatisticsIncreasePage>()
                .select(StatisticsIncreasePage::getEndTime,
                        StatisticsIncreasePage::getPageUrl,
                        StatisticsIncreasePage::getPageName,
                        StatisticsIncreasePage::getPageTotal
                )
                .orderByAsc(StatisticsIncreasePage::getEndTime)
                .ge(StatisticsIncreasePage::getEndTime, time)
        );
        if (list.size() <= 0) {
            return null;
        }

        // 根据天统计
        Map<String, List<StatisticsIncreasePage>> groupByDay = list.stream().collect(
                Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMMdd(o.getEndTime()))
        );

        // 计算访问量
        List<String> times = new ArrayList<>();
        List<Long> pageTotals = new ArrayList<>();
        groupByDay.forEach((k, v) -> {
            times.add(k);
            pageTotals.add(v.stream().mapToLong(p -> p.getPageTotal()).sum());
        });

        StatisticsIncreasePageVO vo = new StatisticsIncreasePageVO();
        vo.setTimes(times);
        vo.setPageTotals(pageTotals);
        return vo;
    }


    @Override
    public Map<String, StatisticsIncreasePageVO> findStatisticsByDayOrYear(String name, String time, Integer type) {
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        if (type.equals(1)) {
            // 查询天（获取指定天0点到23:59:59）
            LocalDateTime dateTime = LocalDateTimeUtil.parse_yyyyMMdd(time);
            startTime = LocalDateTimeUtil.getDayStart(dateTime);
            endTime = LocalDateTimeUtil.getDayEnd(dateTime);
        } else if (type.equals(2)) {
            // 查询月
            time = time + "-01";
            LocalDateTime dateTime = LocalDateTimeUtil.parse_yyyyMMdd(time);
            startTime = LocalDateTimeUtil.getMonthOfFirst(dateTime, 0);
            endTime = LocalDateTimeUtil.getMonthOfLast(dateTime, 0);
        } else if (type.equals(3)) {
            // 查询年
            time = time + "-01-01";
            LocalDateTime dateTime = LocalDateTimeUtil.parse_yyyyMMdd(time);
            startTime = LocalDateTimeUtil.yearFirst(dateTime);
            endTime = LocalDateTimeUtil.yearLast(dateTime);
        }
        List<StatisticsIncreasePage> list = this.list(new LambdaQueryWrapper<StatisticsIncreasePage>()
                .select(StatisticsIncreasePage::getEndTime,
                        StatisticsIncreasePage::getPageUrl,
                        StatisticsIncreasePage::getPageName,
                        StatisticsIncreasePage::getPageTotal
                )
                .eq(StringUtils.isNotBlank(name), StatisticsIncreasePage::getPageName, name)
                .orderByAsc(StatisticsIncreasePage::getEndTime)
                .between(StatisticsIncreasePage::getEndTime, startTime, endTime)
        );
        if (list.size() <= 0) {
            return null;
        }
        Map<String, StatisticsIncreasePageVO> voMap = dataProcessing(list, type);
        return voMap;
    }


    @Override
    public Map<String, StatisticsIncreasePageVO> findStatisticsByOfLate(Integer type, Integer dayNum) {
        if (type.equals(2)) {
            dayNum = dayNum * 7;
        } else if (type.equals(1)) {
        }
        // 查询n天内数据
        LocalDateTime time = LocalDateTimeUtil.subtract(LocalDateTime.now(), dayNum, ChronoUnit.DAYS);
        time = LocalDateTimeUtil.getDayEnd(time);
        List<StatisticsIncreasePage> list = this.list(new LambdaQueryWrapper<StatisticsIncreasePage>()
                .select(StatisticsIncreasePage::getEndTime,
                        StatisticsIncreasePage::getPageUrl,
                        StatisticsIncreasePage::getPageName,
                        StatisticsIncreasePage::getPageTotal
                )
                .orderByAsc(StatisticsIncreasePage::getEndTime)
                .ge(StatisticsIncreasePage::getEndTime, time)
        );
        if (list.size() <= 0) {
            return null;
        }
        Map<String, StatisticsIncreasePageVO> voMap = dataProcessing(list, type);
        return voMap;
    }


    /**
     * 拼返回数据
     * @param list 数据库添加数据
     * @param type 1-拼小时数据（日搜索）  2-拼天数据(月搜索/周搜索)  3-拼月数据(年搜索)
     * @return
     */
    public Map<String, StatisticsIncreasePageVO> dataProcessing(List<StatisticsIncreasePage> list, Integer type) {
        // 1、根据页面名称统计
        Map<String, List<StatisticsIncreasePage>> groupByName = list.stream().collect(
                Collectors.groupingBy(o -> o.getPageName()));

        Map<String, StatisticsIncreasePageVO> mapVo = new HashMap<>();
        groupByName.forEach((k, v) -> {
            Map<String, List<StatisticsIncreasePage>> groupByTime = null;
            if (type.equals(1)) {
                // * 根据时统计
                groupByTime = v.stream().collect(
                        Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMMddHH(o.getEndTime()))
                );
            } else if (type.equals(2)) {
                // 根据天统计
                groupByTime = v.stream().collect(
                        Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMMdd(o.getEndTime()))
                );
            } else if (type.equals(3)) {
                // 根据月统计
                groupByTime = v.stream().collect(
                        Collectors.groupingBy(o -> LocalDateTimeUtil.parse_yyyyMM(o.getEndTime()))
                );
            }
            if (groupByTime != null) {
                // 根据map的key升序排序
                groupByTime = Java8MapSort.sortByKey(groupByTime, false);
                List<String> times = new ArrayList<>();
                List<Long> pageTotals = new ArrayList<>();
                groupByTime.forEach((k2, v2) -> {
                    times.add(k2);
                    pageTotals.add(v2.stream().mapToLong(p -> p.getPageTotal()).sum());
                });
                StatisticsIncreasePageVO vo = new StatisticsIncreasePageVO();
                vo.setPageTotals(pageTotals);
                vo.setTimes(times);
                mapVo.put(k, vo);
            }
        });
        return mapVo;
    }
}
