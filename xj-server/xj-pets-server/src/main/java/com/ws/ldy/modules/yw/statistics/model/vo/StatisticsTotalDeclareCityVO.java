package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;


/**
 * 审报城市统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:19
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsTotalDeclareCityVO 对象", description = "审报城市统计表(每小时一次)")
public class StatisticsTotalDeclareCityVO extends Convert {

    private static final long serialVersionUID = -503730078312697866L;
    
    @ApiModelProperty(notes = "城市" ,position = 0)
    private List<String> citys;

    @ApiModelProperty(notes = "金额" ,position = 1)
    private List<BigDecimal> cityDeclareMoneys;


}

