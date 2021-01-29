package com.ws.ldy.modules.yw.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreaseDeclare;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreaseDeclareVO;

/**
 * 申报统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:49:50
 */
public interface StatisticsIncreaseDeclareService extends IService<StatisticsIncreaseDeclare> {


    /**
     * 申报统计-按天
     * @return
     */
    public StatisticsIncreaseDeclareVO findStatistics(Integer dayNum, Integer type);
}

