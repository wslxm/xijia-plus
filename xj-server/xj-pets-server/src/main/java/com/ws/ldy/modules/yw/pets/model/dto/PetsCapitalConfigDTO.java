package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * 互助资金抽成配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsCapitalConfigDTO 对象", description = "互助资金抽成配置表")
public class PetsCapitalConfigDTO extends BaseDto {

    private static final long serialVersionUID = -503730692434300935L;
    
    @ApiModelProperty(notes = "平台抽成比率( 如：0.18 = 18 % )" ,position = 0)
    @DecimalMin(value = "0",message = "平台抽成比率 必须>=0")
    @DecimalMax(value = "9999999999",message = "平台抽成比率 必须<=9999999999")
    private Double platformPercentage;

}

