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
 * @date 2022-05-14 23:53:03
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestDTO 对象", description = "代码生成测试表")
public class GcTestDTO extends BaseDto {

    private static final long serialVersionUID = -685483378388111360L;
    
    @ApiModelProperty(value = "名称 (文本)", position = 0)
    private String name;

    @ApiModelProperty(value = "年龄 (数字)", position = 1)
    private Double age;

    @ApiModelProperty(value = "性别 (单选--字典)", position = 2)
    private Integer sex;

    @ApiModelProperty(value = "爱好 (多选--字典)", position = 3)
    private String like;

    @ApiModelProperty(value = "城市 (下拉选--字典)", position = 4)
    private Integer city;

    @ApiModelProperty(value = "禁用 (开关--字典)", position = 5)
    private Integer disable;

    @ApiModelProperty(value = "头像 (文件上传)", position = 6)
    private String headUrl;

    @ApiModelProperty(value = "时间", position = 7)
    private LocalDateTime time;

    @ApiModelProperty(value = "更多信息(大文本)", position = 8)
    private String text;

    @ApiModelProperty(value = "更多信息(富文本)", position = 9)
    private String textTwo;

}

