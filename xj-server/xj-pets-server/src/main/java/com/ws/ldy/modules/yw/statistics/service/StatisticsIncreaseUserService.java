package com.ws.ldy.modules.yw.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreaseUser;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreaseUserVO;

/**
 * 用户增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:16
 */
public interface StatisticsIncreaseUserService extends IService<StatisticsIncreaseUser> {


    StatisticsIncreaseUserVO findStatistics(Integer dayNum);

}

