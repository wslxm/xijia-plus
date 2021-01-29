package com.ws.ldy.modules.yw.statistics.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(value = "StatisticsTotalDeclareCityDTO 对象", description = "审报城市统计表(每小时一次)")
public class StatisticsTotalDeclareCityDTO extends BaseDto {

    private static final long serialVersionUID = -503730078342057987L;
    
    @ApiModelProperty(notes = "城市名" ,position = 0)
    @Length(min=1, max=32,message = "城市名 必须>=0 和 <=32位")
    private String city;

    @ApiModelProperty(notes = "当前城市申报总金额" ,position = 1)
    @Range(min=0, max=7766279631452241920L,message = "当前城市申报总金额 必须>=0 和 <=7766279631452241920")
    private BigDecimal cityDeclareMoney;

    @ApiModelProperty(notes = "当前城市申报总数" ,position = 2)
    @Range(min=0, max=7766279631452241920L,message = "当前城市申报总数 必须>=0 和 <=7766279631452241920")
    private Long cityDeclareNum;

    @ApiModelProperty(notes = "最后统计时间 (根据城市 决定 数据条数)" ,position = 3)
    private LocalDateTime time;

}

