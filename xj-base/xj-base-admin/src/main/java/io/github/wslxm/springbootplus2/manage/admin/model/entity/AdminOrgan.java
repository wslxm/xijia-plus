package io.github.wslxm.springbootplus2.manage.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  ws
 * @email  1720696548@qq.com
 * @date  2021-09-30 16:10:57
 */
@Data
@ToString(callSuper = true)
@TableName("t_admin_organ")
@ApiModel(value = "AdminOrgan 对象", description = "基础表--组织机构")
public class AdminOrgan extends BaseEntity {

    private static final long serialVersionUID = -603467428239904776L;
    
    /** 
     * 父Id (顶级父id=0) 
     */
    @TableField(value = "pid")
    private String pid;

    /** 
     * 部门编码 (开始查询使用,不可重复) 
     */
    @TableField(value = "code")
    private String code;

    /** 
     * 部门/公司名称 
     */
    @TableField(value = "`name`")
    private String name;

    /** 
     * 部门/公司描叙 
     */
    @TableField(value = "`desc`")
    private String desc;

    /** 
     * 排序 
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /** 
     * 禁用(0-否 1-是) 
     */
    @TableField(value = "`disable`")
    private Integer disable;

    /**
     * 级别(1-一级 2-二级 3-三级)
     */
    private Integer root;

}

