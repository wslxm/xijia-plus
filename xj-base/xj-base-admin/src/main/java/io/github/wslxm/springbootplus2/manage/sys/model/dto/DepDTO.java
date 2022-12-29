package io.github.wslxm.springbootplus2.manage.sys.model.dto;

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
 * base--sys--组织机构 DTO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-29 09:57:30
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "DepDTO 对象", description = "base--sys--组织机构")
public class DepDTO extends BaseDto {

    private static final long serialVersionUID = -768259926085734400L;
    
    @ApiModelProperty(value = "父Id (顶级父id=0)" ,position = 0)
    @Length(min=0, max=32,message = "父Id  必须>=0 和 <=32位")
    private String pid;

    @ApiModelProperty(value = "公司/部门名称" ,position = 1)
    @Length(min=0, max=32,message = "公司/部门名称 必须>=0 和 <=32位")
    private String name;

    @ApiModelProperty(value = "公司/部门描叙" ,position = 2)
    @Length(min=0, max=128,message = "公司/部门描叙 必须>=0 和 <=128位")
    private String desc;

    @ApiModelProperty(value = "部门编码 (便于查询使用,不可重复)" ,position = 3)
    @Length(min=0, max=32,message = "部门编码  必须>=0 和 <=32位")
    private String code;

    @ApiModelProperty(value = "排序" ,position = 4)
    @Range(min=0, max=1215752191L,message = "排序 必须>=0 和 <=1215752191")
    private Integer sort;

    @ApiModelProperty(value = "禁用(0-否 1-是)" ,position = 5)
    @Range(min=0, max=9L,message = "禁用 必须>=0 和 <=9")
    private Integer disable;

}

