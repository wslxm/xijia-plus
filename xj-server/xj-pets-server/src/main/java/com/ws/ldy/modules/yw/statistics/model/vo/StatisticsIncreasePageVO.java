package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 页面统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:09
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsIncreasePageVO 对象", description = "页面统计每小时增长表")
public class StatisticsIncreasePageVO extends Convert {

    private static final long serialVersionUID = -503730036403212305L;
    
    @ApiModelProperty(notes = "统计开始时间( 根据时间间隔 * 页面名称 决定 数据条数)" ,position = 0)
    private List<String> times;

    @ApiModelProperty(notes = "页面访问数" ,position = 2)
    private List<Long> pageTotals;
}

