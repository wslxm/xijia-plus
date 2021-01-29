package com.ws.ldy.modules.yw.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreasePage;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreasePageVO;

import java.util.Map;

/**
 * 页面统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:09
 */
public interface StatisticsIncreasePageService extends IService<StatisticsIncreasePage> {


    /**
     * 页面访问量统计
     * @param dayNum
     * @return
     */
    StatisticsIncreasePageVO findStatistics(Integer dayNum);


    /**
     *  年月日搜索
     * @param name
     * @param time
     * @param type
     * @return
     */
    Map<String, StatisticsIncreasePageVO> findStatisticsByDayOrYear(  String name,
                                                       String time,
                                                       Integer type);


    /**
     * 查询最近
     * @param type
     * @return
     */
    Map<String, StatisticsIncreasePageVO> findStatisticsByOfLate(Integer type,
                                                                    Integer dayNum);
}

