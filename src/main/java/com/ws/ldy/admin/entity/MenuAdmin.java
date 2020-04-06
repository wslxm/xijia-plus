package com.ws.ldy.admin.entity;

import com.ws.ldy.base.entity.BaseAdminEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO  菜单  
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/14 20:49
 */
@Data
@Entity
@Table(name = "t_admin_menu")
@DynamicUpdate(value = true)
public class MenuAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = -33297418791559528L;
    /**
     * 数据库自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
