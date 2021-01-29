package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 缴费增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:13
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsIncreasePaymentVO 对象", description = "缴费增长每小时统计表")
public class StatisticsIncreasePaymentVO extends Convert {

    private static final long serialVersionUID = -503730050814840842L;

    @ApiModelProperty(notes = "时间", position = 0)
    private List<String> times;

    @ApiModelProperty(notes = "缴费金额", position = 1)
    private List<Double> paymentMoneys;

    @ApiModelProperty(notes = "首次金额", position = 2)
    private List<Double> firstMoneys;

    @ApiModelProperty(notes = "续费金额", position = 3)
    private List<Double> renewMoneys;

}

