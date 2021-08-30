package com.ws.ldy.manage.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.admin.model.dto.AdminMenuDTO;
import com.ws.ldy.manage.admin.model.entity.AdminMenu;
import com.ws.ldy.manage.admin.model.query.AdminMenuQuery;
import com.ws.ldy.manage.admin.model.vo.AdminMenuVO;

import java.util.List;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminMenuService extends IService<AdminMenu> {

    IPage<AdminMenuVO> list(AdminMenuQuery query);

    /**
     * 添加(超管默认分配改菜单)
     */
    String insert(AdminMenuDTO dto);


    Boolean upd(String id, AdminMenuDTO dto);

    /**
     * 删除菜单并删除菜单的所有下级 以及 菜单和角色的绑定关系数据
     */
    List<String> del(String menuId);

    /**
     * 获取导航树菜单列表
     */
    List<AdminMenuVO> getMenuTree();

    /**
     * 根据父id 查询所有子节点数据（包括自己 List列表） , 根据角色权限赋值isChecked = true||false
     * @param pId 父id
     * @param roleId 角色id
     * @param terminal 终端
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminMenu>
     * @date 2019/11/15 16:18
     */
    List<AdminMenuVO> menuList(String pId, String roleId, Integer terminal);

    /**
     * 根据父id 查询所有子节点数据（包括自己 Tree数据）, 根据角色权限赋值isChecked = true||false
     * @param pId 父id
     * @param roleId 角色id
     * @param terminal 终端
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminMenu>
     * @date 2019/11/15 16:18
     */
    List<AdminMenuVO> menuTree(String pId, String roleId, Integer terminal);

}