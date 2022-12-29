package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;

/**
 * base--sys--组织机构 Entity
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
@TableName("t_sys_dep")
@ApiModel(value = "Dep 对象", description = "base--sys--组织机构")
public class Dep extends BaseEntity {

    private static final long serialVersionUID = -768259926039597056L;
    
    @ApiModelProperty(notes = "父Id (顶级父id=0)" ,position = 0)
    @TableField(value = "pid")
    private String pid;

    @ApiModelProperty(notes = "公司/部门名称" ,position = 1)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "公司/部门描叙" ,position = 2)
    @TableField(value = "`desc`")
    private String desc;

    @ApiModelProperty(notes = "部门编码 (便于查询使用,不可重复)" ,position = 3)
    @TableField(value = "code")
    private String code;

    @ApiModelProperty(notes = "排序" ,position = 4)
    @TableField(value = "`sort`")
    private Integer sort;

    @ApiModelProperty(notes = "禁用(0-否 1-是)" ,position = 5)
    @TableField(value = "`disable`")
    private Integer disable;

}

