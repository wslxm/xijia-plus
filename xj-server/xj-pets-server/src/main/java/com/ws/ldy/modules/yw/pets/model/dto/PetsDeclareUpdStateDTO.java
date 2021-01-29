package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-31 17:41:21
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclareDTO 对象", description = "申报信息表")
public class PetsDeclareUpdStateDTO extends BaseDto {

    private static final long serialVersionUID = -504558292220121097L;

    @ApiModelProperty(notes = " * 是否通过 true-通过 false-不通过", position = 2)
    @NotNull
    private Boolean isPass;

    @ApiModelProperty(notes = "true传 --- 实发金额", position = 4)
    @DecimalMin("0.01")
    private BigDecimal paidInAmount;

    @ApiModelProperty(notes = "false传 --- 错误原因(字典code)", position = 5)
    private Integer errorType;


    @ApiModelProperty(notes = "备注意见", position = 6)
    private String remarks;
}
