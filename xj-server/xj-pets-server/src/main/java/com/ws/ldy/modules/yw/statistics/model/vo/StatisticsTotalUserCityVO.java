package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 用户城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:33
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsTotalUserCityVO 对象", description = "用户城市总量表(每小时一次)")
public class StatisticsTotalUserCityVO extends Convert {

    private static final long serialVersionUID = -503730135531393034L;
    
    @ApiModelProperty(notes = "城市名" ,position = 0)
    private List<String> citys;

    @ApiModelProperty(notes = "当前城市人总数" ,position = 1)
    private List<Long> cityUserTotals;

//    @ApiModelProperty(notes = "男性城市人总数( 预留字段)" ,position = 2)
//    private Long cityBoyUserTotal;
//
//    @ApiModelProperty(notes = "女性城市人总数( 预留字段)" ,position = 3)
//    private Long cityGirlUserTotal;
//
//    @ApiModelProperty(notes = "最后统计时间( 城市数 决定 数据条数)" ,position = 4)
//    private LocalDateTime time;

}

