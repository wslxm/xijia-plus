package com.ws.ldy.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.base.model.entity.BaseAdminEntity;
import lombok.Data;

/**
 * TODO  菜单  
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/14 20:49
 */
@Data
@TableName(value = "t_admin_menu")
public class MenuAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = -33297418791559528L;
    /**
     * 数据库自增id
     */
    @TableId(type = IdType.AUTO) //自增
    private int id;
    /** 父id  */
    private int pid;

    /** 菜单名称 */
    private String name;

    /** 菜单路径 */
    private String url;

    /** 菜单图标 */
    private String icon;

    /** 排序 */
    private int sort;

    /** 菜单级别，(0、根目录,1、子目录, 2、菜单 3、页面 */
    private int root;
}
