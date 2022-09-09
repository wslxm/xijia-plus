package io.github.wslxm.springbootplus2.manage.test.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseDto;

/**
 * 代码生成测试表 DTO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:05:45
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestDTO 对象", description = "代码生成测试表")
public class GcTestDTO extends BaseDto {

    private static final long serialVersionUID = -728157747253743616L;
    
    @ApiModelProperty(value = "颜色选择器" ,position = 0)
    private String color;

    @ApiModelProperty(value = "地址选择器" ,position = 1)
    private String map;

}

