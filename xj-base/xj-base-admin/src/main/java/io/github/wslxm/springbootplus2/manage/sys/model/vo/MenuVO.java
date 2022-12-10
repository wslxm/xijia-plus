package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
public class MenuVO extends BaseVo {

    private static final long serialVersionUID = -33297418791559528L;


    /**
     * 指定父id
     */
    private String pid;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单url
     */
    private String url;
    /**
     * 路径-vue路由字段
     */
    private String pathx;
    /**
     * 第二菜单url, 如url不满足前后端分离, 可使用第二路由
     */
    private String twoUrl;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 目录级别(1，系统, 2、一级菜单 ，3，二级菜单, 4、页面)
     */
    private Integer root;
    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;
    /**
     * 当前页面权限id
     */
    private Integer authority;
    /**
     * 当前节点的子节点，获取菜单树数据使用
     */
    private List<MenuVO> menus;
    /**
     * 是否选中（是否有权限，前台复选框默认选中需要值）
     */
    private Boolean isChecked;
}
