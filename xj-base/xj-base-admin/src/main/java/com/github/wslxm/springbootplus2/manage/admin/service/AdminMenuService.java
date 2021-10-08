package com.github.wslxm.springbootplus2.manage.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wslxm.springbootplus2.manage.admin.model.dto.AdminMenuDTO;
import com.github.wslxm.springbootplus2.manage.admin.model.entity.AdminMenu;
import com.github.wslxm.springbootplus2.manage.admin.model.query.AdminMenuQuery;
import com.github.wslxm.springbootplus2.manage.admin.model.vo.AdminMenuVO;

import java.util.List;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminMenuService extends IService<AdminMenu> {


    List<AdminMenuVO> list(AdminMenuQuery query);

    /**
     * 添加(超管默认分配改菜单)
     */
    String insert(AdminMenuDTO dto);


    Boolean upd(String id, AdminMenuDTO dto);

    /**
     * 删除菜单并删除菜单的所有下级 以及 菜单和角色的绑定关系数据, 返回ids 让前端可以判断删除了多少条数据
     */
    List<String> del(String menuId);

    /**
     * 获取导航树菜单列表
     */
    List<AdminMenuVO> findTree();


}