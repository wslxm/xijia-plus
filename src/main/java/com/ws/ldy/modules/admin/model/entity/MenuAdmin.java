package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.enums.admin.MenuRootEnum;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.Data;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@TableName(value = "t_admin_menu")
public class MenuAdmin extends BaseEntity {

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
    private MenuRootEnum root;

    /**
     * 是否禁用(0-否 1-是)
     */
    private Integer state;
}
