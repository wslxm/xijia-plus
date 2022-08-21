package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -33297418791559528L;


    /**
     * 父id
     */
    private String pid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路径
     */
    private String url;
    /**
     * 第二菜单url, 如url不满足前后端分离, 可使用第二路由
     */
    private String twoUrl;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单级别，(0、根目录,1、子目录, 2、菜单 3、页面
     */
    private Integer root;

    /**
     * 禁用 0-否，1-是
     */
    @TableField(value = "`disable`")
    private Integer disable;

}
