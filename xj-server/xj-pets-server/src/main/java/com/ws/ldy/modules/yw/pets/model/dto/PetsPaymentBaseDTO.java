package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * 缴费基数配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:21
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsPaymentBaseDTO 对象", description = "缴费基数配置表")
public class PetsPaymentBaseDTO extends BaseDto {

    private static final long serialVersionUID = -503731090817683463L;
    
    @ApiModelProperty(notes = "报销次数" ,position = 0)
    @Range(min=0, max=1215752191L,message = "报销次数 必须>=0 和 <=1215752191")
    private Integer declareNum;

    @ApiModelProperty(notes = "基数比率( 金额 * 比率 )" ,position = 1)
    @DecimalMin(value = "0",message = "基数比率 必须>=0")
    @DecimalMax(value = "9999999999",message = "基数比率 必须<=9999999999")
    private Double ratio;

}

