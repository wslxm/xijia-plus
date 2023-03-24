package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;

/**
 *  Query
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2023-03-24 10:04:20
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AddressQuery 对象", description = "")
public class AddressQuery extends BaseQuery {

    private static final long serialVersionUID = -1639085709929979904L;
    
    @ApiModelProperty(value = "父级挂接id" ,position = 0)
    @Length(min=0, max=32,message = "父级挂接id 必须>=0 和 <=32位")
    private String pid;

    @ApiModelProperty(value = "区划名称" ,position = 1)
    @Length(min=0, max=32,message = "区划名称 必须>=0 和 <=32位")
    private String name;

    @ApiModelProperty(value = "区划编码" ,position = 2)
    @Length(min=0, max=32,message = "区划编码 必须>=0 和 <=32位")
    private String code;

    @ApiModelProperty(value = "级次id 0:省/自治区/直辖市 1:市级 2:县级" ,position = 3)
    @Range(min=0, max=9L,message = "级次id 0 必须>=0 和 <=9")
    private Integer level;

}

