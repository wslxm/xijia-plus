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
 *  Entity
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
@TableName("t_sys_address")
@ApiModel(value = "Address 对象", description = "")
public class Address extends BaseEntity {

    private static final long serialVersionUID = -1639085709435052032L;
    
    @ApiModelProperty(notes = "父级挂接id" ,position = 0)
    @TableField(value = "pid")
    private String pid;

    @ApiModelProperty(notes = "区划名称" ,position = 1)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "区划编码" ,position = 2)
    @TableField(value = "code")
    private String code;

    @ApiModelProperty(notes = "备注" ,position = 3)
    @TableField(value = "remark")
    private String remark;

    @ApiModelProperty(notes = "级次id 0:省/自治区/直辖市 1:市级 2:县级" ,position = 4)
    @TableField(value = "level")
    private Integer level;

}

