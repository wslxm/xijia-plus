package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;
import lombok.ToString;

/**
 * 菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
public class MenuDTO extends Convert {

    private static final long serialVersionUID = -33297418791559528L;
    /**
     * 目录级别(1，系统, 2、菜单, 3、页面)
     */
    private Integer root;
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
     * 禁用（0-否，1-是）
     */
    private Integer disable;
}
