package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import java.math.BigDecimal;

/**
 * 月费表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:53:43
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsMonthFeeDTO 对象", description = "月费表")
public class PetsMonthFeeDTO extends BaseDto {

    private static final long serialVersionUID = -503730931509628937L;
    
    @ApiModelProperty(notes = "月数( 1/3/6/12)" ,position = 0)
    @Range(min=0, max=999L,message = "月数 必须>=0 和 <=999")
    private Integer monthNum;

    @ApiModelProperty(notes = "月费-对应月数( 30/90/180/360)" ,position = 1)
    @DecimalMin(value = "0",message = "月费-对应月数 必须>=0")
    @DecimalMax(value = "9999999999",message = "月费-对应月数 必须<=9999999999")
    private BigDecimal monthFee;

    @ApiModelProperty(notes = "描叙" ,position = 2)
    @Length(min=1, max=256,message = "描叙 必须>=0 和 <=256位")
    private String monthDesc;

}

