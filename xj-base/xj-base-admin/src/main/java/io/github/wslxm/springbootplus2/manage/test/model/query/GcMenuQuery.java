package io.github.wslxm.springbootplus2.manage.test.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;

/**
 * 基础表--菜单 Query
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 17:38:58
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcMenuQuery 对象", description = "基础表--菜单")
public class GcMenuQuery extends BaseQuery {

    private static final long serialVersionUID = -768013672160301056L;
    
    @ApiModelProperty(value = "指定父id" ,position = 0)
    @Length(min=0, max=32,message = "指定父id 必须>=0 和 <=32位")
    private String pid;

    @ApiModelProperty(value = "菜单名" ,position = 1)
    @Length(min=0, max=32,message = "菜单名 必须>=0 和 <=32位")
    private String name;

    @ApiModelProperty(value = "禁用(0-启用 1-禁用)" ,position = 2)
    @Range(min=0, max=9L,message = "禁用 必须>=0 和 <=9")
    private Integer disable;

}

