package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.github.wslxm.springbootplus2.core.utils.tree.BaseTree;

/**
 * base--sys--组织机构 VO
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
@ApiModel(value = "DepVO 对象", description = "base--sys--组织机构")
public class DepVO extends BaseTree<DepVO,String>  {

    private static final long serialVersionUID = -768259926148648960L;
    
    @ApiModelProperty(value = "父Id (顶级父id=0)" ,position = 0)
    private String pid;

    @ApiModelProperty(value = "公司/部门名称" ,position = 1)
    private String name;

    @ApiModelProperty(value = "公司/部门描叙" ,position = 2)
    private String desc;

    @ApiModelProperty(value = "部门编码 (便于查询使用,不可重复)" ,position = 3)
    private String code;

    @ApiModelProperty(value = "排序" ,position = 4)
    private Integer sort;

    @ApiModelProperty(value = "禁用(0-否 1-是)" ,position = 5)
    private Integer disable;

}

