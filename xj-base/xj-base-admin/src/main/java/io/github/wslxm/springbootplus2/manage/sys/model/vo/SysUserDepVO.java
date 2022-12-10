package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

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
public class SysUserDepVO extends BaseVo {

    private static final long serialVersionUID = -603467428327985153L;

    /**
     * 父Id (顶级父id=0)"
     */
    private String pid;

    /**
     * 部门编码 (开始查询使用,不可重复)
     */
    private String code;

    /**
     * 部门/公司名称
     */
    private String name;

    /**
     * 部门/公司描叙
     */
    private String desc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 禁用(0-否 1-是)
     */
    private Integer disable;

    /**
     * 级别(1-一级 2-二级 3-三级)
     */
    private Integer root;

    /**
     * 下级数据
     */
    private SysUserDepVO dep;

    /**
     * 部门名称S, 只在第一级vo中存在值, 包含下级+下下级
     */
    private String depNames;

}

