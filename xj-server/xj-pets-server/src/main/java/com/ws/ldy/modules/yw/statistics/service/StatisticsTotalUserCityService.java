package com.ws.ldy.modules.yw.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsTotalUserCity;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsTotalUserCityVO;

/**
 * 用户城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:33
 */
public interface StatisticsTotalUserCityService extends IService<StatisticsTotalUserCity> {


    /**
     * 用户城市分布
     */
    public StatisticsTotalUserCityVO findStatistics();
}

