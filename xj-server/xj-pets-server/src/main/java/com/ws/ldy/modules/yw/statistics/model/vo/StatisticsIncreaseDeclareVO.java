package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 申报统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:49:50
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsIncreaseDeclareVO 对象", description = "申报统计每小时增长表")
public class StatisticsIncreaseDeclareVO extends Convert {

    private static final long serialVersionUID = -503729954354237450L;

    @ApiModelProperty(notes = "申报金额", position = 0)
    private List<Double> declareMoneys;

    @ApiModelProperty(notes = "时间(每天一条数据)", position = 2)
    private List<String> times;

}

