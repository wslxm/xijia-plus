package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 宠物城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:29
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsTotalPetCityVO 对象", description = "宠物城市总量表(每小时一次)")
public class StatisticsTotalPetCityVO extends Convert {

    private static final long serialVersionUID = -503730117344890897L;

    //=================================总数
    @ApiModelProperty(notes = "总--城市名", position = 1)
    private List<String> totalCitys;

    @ApiModelProperty(notes = "总--宠物总数", position = 1)
    private List<Long> totalCityTotals;


    //=================================猫
    @ApiModelProperty(notes = "猫--城市名", position = 2)
    private List<String> catCitys;

    @ApiModelProperty(notes = "猫--总数", position = 2)
    private List<Long> catCityTotals;



    //=================================狗
    @ApiModelProperty(notes = "狗--城市名", position = 3)
    private List<String> dogCitys;

    @ApiModelProperty(notes = "狗--总数", position = 3)
    private List<Long> dogCityTotals;


    //=================================地图数据
    @ApiModelProperty(notes = "地图--省份", position = 4)
    private List<String> provinces;

    @ApiModelProperty(notes = "地图--数量", position = 4)
    private List<Long> provincesTotals;

}

