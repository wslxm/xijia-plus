package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AdminMenu;
import com.ws.ldy.modules.admin.model.vo.AdminMenuVO;

import java.util.List;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminMenuService extends IService<AdminMenu> {


    /**
     *    获取导航树菜单列表
     *
     * @return
     * @date 2019/11/13 14:45
     */
    public List<AdminMenuVO> getMenuTree();

    /**
     *   根据父id+角色Id 查询所有子节点数据  ==>>> 树结构列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminMenu>
     * @date 2019/11/15 16:18
     */
    public List<AdminMenuVO> findIdOrRoleIdTree(String pId);

    public List<AdminMenuVO> findIdOrRoleIdTree(String pId, String roleId);

    /**
     *   根据父id+角色Id  查询所有子节点数据 ==>>> list 列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminMenu>
     * @date 2019/11/15 16:18
     */
    public List<AdminMenuVO> findIdOrRoleIdList(String pId);

    public List<AdminMenuVO> findIdOrRoleIdList(String pId, String roleId);

}