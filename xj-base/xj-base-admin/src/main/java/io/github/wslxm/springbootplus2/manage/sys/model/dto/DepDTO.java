package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.BaseDto;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
@Data
@ToString(callSuper = true)
public class DepDTO extends BaseDto {

    private static final long serialVersionUID = -603467428411871238L;

    /**
     * 父Id (顶级父id=0)"
     */
    @Length(min = 0, max = 32, message = "父Id  必须>=0 和 <=32位")
    private String pid;

    /**
     * 部门编码 (开始查询使用,不可重复)"
     */
    @Length(min = 0, max = 32, message = "部门编码  必须>=0 和 <=32位")
    private String code;

    /**
     * 部门/公司名称"
     */
    @Length(min = 0, max = 32, message = "部门/公司名称 必须>=0 和 <=32位")
    private String name;

    /**
     * 部门/公司描叙"
     */
    @Length(min = 0, max = 128, message = "部门/公司描叙 必须>=0 和 <=128位")
    private String desc;

    /**
     * 排序"
     */
    @Range(min = 0, max = 1215752191L, message = "排序 必须>=0 和 <=1215752191")
    private Integer sort;

    /**
     * 禁用(0-否 1-是)"
     */
    @Range(min = 0, max = 9L, message = "禁用 必须>=0 和 <=9")
    private Integer disable;

    /**
     * 级别(1-一级 2-二级 3-三级)"
     */
    @Range(min = 0, max = 9L, message = "级别 必须>=0 和 <=9")
    private Integer root;

}

