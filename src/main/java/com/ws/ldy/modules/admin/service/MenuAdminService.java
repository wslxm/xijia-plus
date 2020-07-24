package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.MenuAdmin;
import com.ws.ldy.modules.admin.model.vo.MenuAdminVo;

import java.util.List;

/**
 * TODO  菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface MenuAdminService  extends IService<MenuAdmin> {


    /**
     * TODO   获取导航树菜单列表
     *
     * @return
     * @date 2019/11/13 14:45
     */
    public List<MenuAdminVo> getMenuTree();

    /**
     * TODO  根据父id+角色Id 查询所有子节点数据  ==>>> 树结构列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.MenuAdmin>
     * @date 2019/11/15 16:18
     */
    public List<MenuAdminVo> findIdOrRoleIdTree(Integer pId);

    public List<MenuAdminVo> findIdOrRoleIdTree(Integer pId, Integer roleId);

    /**
     * TODO  根据父id+角色Id  查询所有子节点数据 ==>>> list 列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.MenuAdmin>
     * @date 2019/11/15 16:18
     */
    public List<MenuAdminVo> findIdOrRoleIdList(Integer pId);

    public List<MenuAdminVo> findIdOrRoleIdList(Integer pId, Integer roleId);

}