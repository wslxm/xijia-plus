package com.ws.ldy.modules.yw.statistics.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * 宠物城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:29
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsTotalPetCityDTO 对象", description = "宠物城市总量表(每小时一次)")
public class StatisticsTotalPetCityDTO extends BaseDto {

    private static final long serialVersionUID = -503730117370056725L;

    @ApiModelProperty(notes = "省" ,position = 0)
    @Length(min=1, max=32,message = "城市名 必须>=1 和 <=32位")
    private  String province;

    @ApiModelProperty(notes = "城市名" ,position = 0)
    @Length(min=1, max=32,message = "城市名 必须>=0 和 <=32位")
    private String city;

    @ApiModelProperty(notes = "当前城市宠物总数" ,position = 1)
    @Range(min=0, max=7766279631452241920L,message = "当前城市宠物总数 必须>=0 和 <=7766279631452241920")
    private Long cityPetTotal;

    @ApiModelProperty(notes = "当前城市猫总数" ,position = 2)
    @Range(min=0, max=7766279631452241920L,message = "当前城市猫总数 必须>=0 和 <=7766279631452241920")
    private Long cityPetCatTotal;

    @ApiModelProperty(notes = "当前城市狗总数" ,position = 3)
    @Range(min=0, max=7766279631452241920L,message = "当前城市狗总数 必须>=0 和 <=7766279631452241920")
    private Long cityPetDogTotal;

    @ApiModelProperty(notes = "最后统计时间( 城市数 决定 数据条数)" ,position = 4)
    private LocalDateTime time;

}

