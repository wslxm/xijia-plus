package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.mapper.MenuAdminMapper;
import com.ws.ldy.modules.admin.mapper.RoleMenuAdminMapper;
import com.ws.ldy.modules.admin.model.entity.MenuAdmin;
import com.ws.ldy.modules.admin.model.entity.RoleMenuAdmin;
import com.ws.ldy.modules.admin.model.vo.MenuAdminVo;
import com.ws.ldy.modules.admin.service.MenuAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MenuAdminServiceImpl extends BaseIServiceImpl<MenuAdminMapper, MenuAdmin> implements MenuAdminService {

    //
    @Autowired
    private RoleMenuAdminMapper roleMenuAdminMapper;

    /**
     * =========================================================================
     * ========================   导航树结构菜单查询处理  ======================
     * =========================================================================
     */
    @Override
    public List<MenuAdminVo> getMenuTree() {

        // 获取当前代理角色所有菜单Id： key=菜单Id, value=0
        Map<String, String> roleMenuMap = new HashMap<>();
        // 查询用户所有角色，在查询角色下所有菜单id
        roleMenuAdminMapper.findUserIdRoleMenus(JwtUtil.getUserId(request.getHeader(BaseConstant.Sys.TOKEN))).forEach(item -> roleMenuMap.put(item.getMenuId(), item.getMenuId()));
        // 系统级  ==>  顶级菜单返回  ==>  root == 1
        List<MenuAdminVo> menuList = new LinkedList<>();
        // 查询所有菜单
        List<MenuAdmin> menuAdminList = this.list(new LambdaQueryWrapper<MenuAdmin>()
                .orderByAsc(MenuAdmin::getSort)
                .eq(MenuAdmin::getState, 0)
        );
        // 树结构菜单 ==> 递归添加
        List<MenuAdminVo> menuAdminVos = BeanDtoVoUtil.listVoStream(menuAdminList, MenuAdminVo.class);
        menuAdminVos.forEach(menuVo -> {
            if (menuVo.getRoot() != null && menuVo.getRoot().getValue() == 1 && roleMenuMap.containsKey(menuVo.getId())) {
                MenuAdminVo menuAdminVo = nextLowerNode(menuAdminVos, menuVo, roleMenuMap);
                menuList.add(menuAdminVo);
            }
        });
        return menuList;
    }


    /***
     * 菜单数遍历子节点(递归遍历)
     * @param menuVos 所有节点
     * @param menuVo  当前菜单节点
     * @param roleMenuMap  当前用户存在的菜单权限
     * @date 2019/11/13 15:20
     * @return void
     */
    private MenuAdminVo nextLowerNode(List<MenuAdminVo> menuVos, MenuAdminVo menuVo, Map<String, String> roleMenuMap) {
        List<MenuAdminVo> menuList = new ArrayList<>();
        menuVos.forEach(menuVo2 -> {
            if (menuVo2.getPid().equals(menuVo.getId()) && roleMenuMap.containsKey(menuVo2.getId())) {
                //递归获取子节点
                MenuAdminVo menuAdminVo = nextLowerNode(menuVos, menuVo2, roleMenuMap);
                menuList.add(menuAdminVo);
            }
        });
        // 保存该节点下的子节点
        menuVo.setMenus(menuList);
        // 返回当前节点
        return menuVo;
    }


    /**
     * 根据 pid 查询指定父菜单下的所有菜单，包括自身
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.admin.model.vo.MenuAdminVo>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:39
     */
    @Override
    public List<MenuAdminVo> findIdOrRoleIdTree(String pId) {
        return findIdOrRoleIdTree(pId, null);
    }

    /**
     * 根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单，包括自身
     *
     * @param pId
     * @param roleId
     * @return java.util.List<com.ws.ldy.admin.model.vo.MenuAdminVo>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:39
     */
    @Override
    public List<MenuAdminVo> findIdOrRoleIdTree(String pId, String roleId) {
        // 获取指定角色所有菜单Id： key=菜单Id, value=0
        // List<RoleMenuAdmin> roleMenus = roleMenuAdminMapper.findRoleId(roleId);
        Map<String, String> roleMenuMap = new HashMap<>();
        List<RoleMenuAdmin> roleIdList = roleMenuAdminMapper.findRoleId(roleId);
        roleIdList.forEach(item -> roleMenuMap.put(item.getMenuId(), item.getMenuId()));
        // 查询菜单数据
        List<MenuAdminVo> menuAdminVos = BeanDtoVoUtil.listVoStream(this.list(), MenuAdminVo.class);
        // 系统级  ==>  顶级菜单返回  ==>  root == 1
        List<MenuAdminVo> menuList = new LinkedList<>();
        // 树结构菜单 ==> 递归添加
        menuAdminVos.forEach(menuVo -> {
            if (pId != null) {
                if (menuVo.getRoot().getValue() == 1 && pId.equals(menuVo.getId())) {
                    MenuAdminVo menuAdminVo = nextLowerIdNode(menuAdminVos, menuVo, roleMenuMap);
                    this.setChecked(menuVo, roleMenuMap);
                    menuList.add(menuAdminVo);
                }
            } else {
                if (menuVo.getRoot().getValue() == 1) {
                    MenuAdminVo menuAdminVo = nextLowerIdNode(menuAdminVos, menuVo, roleMenuMap);
                    this.setChecked(menuVo, roleMenuMap);
                    menuList.add(menuAdminVo);
                }
            }
        });
        return menuList;
    }

    /**
     * 获取指定父节点下的子节点(递归遍历)
     *
     * @param menuVos     所有节点
     * @param menuVo      当前节点
     * @param roleMenuMap 选中角色权限
     * @return void
     * @date 2019/11/13 15:20
     */
    private MenuAdminVo nextLowerIdNode(List<MenuAdminVo> menuVos, MenuAdminVo menuVo, Map<String, String> roleMenuMap) {
        List<MenuAdminVo> menuList = new ArrayList<>();
        menuVos.forEach(menuVo2 -> {
            if (menuVo2.getPid().equals(menuVo.getId())) {
                //递归获取子节点
                MenuAdminVo menuAdminVo = nextLowerNode(menuVos, menuVo2, roleMenuMap);
                this.setChecked(menuVo2, roleMenuMap);
                menuList.add(menuAdminVo);
            }
        });
        menuVo.setMenus(menuList);
        return menuVo;
    }


    /**
     * 根据Pid 查询当前菜单下的所有菜单，包括自身, 返回List 列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.admin.model.vo.MenuAdminVo>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:36
     */
    @Override
    public List<MenuAdminVo> findIdOrRoleIdList(String pId) {
        return findIdOrRoleIdList(pId, null);
    }

    /**
     * 根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单，包括自身, 返回List 列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.admin.model.vo.MenuAdminVo>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:36
     */
    @Override
    public List<MenuAdminVo> findIdOrRoleIdList(String pId, String roleId) {
        // 获取指定角色所有菜单Id： key=菜单Id, value=0
        Map<String, String> roleMenuMap = new HashMap<>();
        if (roleId != null) {
            List<RoleMenuAdmin> roleMenuList = roleMenuAdminMapper.findRoleId(roleId);
            if (roleMenuList != null && roleMenuList.size() > 0) {
                roleMenuList.forEach(item -> roleMenuMap.put(item.getMenuId(), item.getMenuId()));
            }
        }
        // 查询菜单数据
        List<MenuAdmin> menuAdminList = this.list(new LambdaQueryWrapper<MenuAdmin>().orderByAsc(MenuAdmin::getSort));
        List<MenuAdminVo> menuAdminVoList = new ArrayList<>();
        if (menuAdminList != null && menuAdminList.size() > 0) {
            menuAdminVoList = BeanDtoVoUtil.listVoStream(menuAdminList, MenuAdminVo.class);
        }
        // 系统级  ==>  顶级菜单返回  ==>  root == 1
        List<MenuAdminVo> menuVoList = new LinkedList<>();
        if (pId != null && Integer.parseInt(pId) >= 0) {
            // 树结构菜单 ==> 递归添加  ==> 指定父Id下
            for (MenuAdminVo menuVo : menuAdminVoList) {
                if (menuVo.getId().equals(pId)) {
                    nextLowerIdNode(menuAdminVoList, menuVo, roleMenuMap, menuVoList);
                    this.setChecked(menuVo, roleMenuMap);
                    //顶级菜单
                    // if (menuVo.getRoot().equals(1) ) {
                    menuVoList.add(menuVo);
                    // }
                }
            }
        } else {
            // 树结构菜单 ==> 递归添加  ==>  所有
            for (MenuAdminVo menuVo : menuAdminVoList) {
                //顶级菜单
                if (menuVo.getRoot().getValue().equals(1)) {
                    nextLowerIdNode(menuAdminVoList, menuVo, roleMenuMap, menuVoList);
                    this.setChecked(menuVo, roleMenuMap);
                    menuVoList.add(menuVo);
                }
            }
        }
        return menuVoList;
    }

    /**
     * 获取指定父节点下的子节点(递归遍历)
     *
     * @param menuVos     所有节点
     * @param menuVo      当前节点
     * @param roleMenuMap 选中角色权限
     * @param menuVoList  最后的返回数据
     * @return void
     * @date 2019/11/13 15:20
     */
    private void nextLowerIdNode(List<MenuAdminVo> menuVos, MenuAdminVo menuVo, Map<String, String> roleMenuMap, List<MenuAdminVo> menuVoList) {
        menuVos.forEach(menuVo2 -> {
            if (menuVo2.getPid().equals(menuVo.getId())) {
                this.setChecked(menuVo2, roleMenuMap);
                menuVoList.add(menuVo2);
                //递归获取子节点
                nextLowerIdNode(menuVos, menuVo2, roleMenuMap, menuVoList);
            }
        });
    }


    /**
     * 判断并设置为选中状态
     */
    private void setChecked(MenuAdminVo menu, Map<String, String> roleMenuMap) {
        //权限判断设置选中状态
        if (roleMenuMap.containsKey(menu.getId())) {
            menu.setIsChecked(true);
        } else {
            menu.setIsChecked(false);
        }
    }
}