package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.mapper.AdminMenuMapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleMenuMapper;
import com.ws.ldy.modules.admin.model.entity.AdminMenu;
import com.ws.ldy.modules.admin.model.entity.AdminRoleMenu;
import com.ws.ldy.modules.admin.model.vo.AdminMenuVO;
import com.ws.ldy.modules.admin.service.AdminMenuService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AdminMenuServiceImpl extends BaseIServiceImpl<AdminMenuMapper, AdminMenu> implements AdminMenuService {

    //
    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;

    /**
     * =========================================================================
     * ========================   导航树结构菜单查询处理  ======================
     * =========================================================================
     */
    @Override
    public List<AdminMenuVO> getMenuTree() {

        // 获取当前代理角色所有菜单Id： key=菜单Id, value=0
        Map<String, String> roleMenuMap = new HashMap<>();
        // 查询用户所有角色，在查询角色下所有菜单id
        adminRoleMenuMapper.findUserIdRoleMenus(JwtUtil.getUserId(request.getHeader(BaseConstant.Sys.TOKEN))).forEach(item -> roleMenuMap.put(item.getMenuId(), item.getMenuId()));
        // 系统级  ==>  顶级菜单返回  ==>  root == 1
        List<AdminMenuVO> menuList = new LinkedList<>();
        // 查询所有菜单
        List<AdminMenu> adminMenuList = this.list(new LambdaQueryWrapper<AdminMenu>()
                .orderByAsc(AdminMenu::getSort)
                .eq(AdminMenu::getState, 0)
        );
        // 树结构菜单 ==> 递归添加
        List<AdminMenuVO> adminMenuVOS = BeanDtoVoUtil.listVoStream(adminMenuList, AdminMenuVO.class);
        adminMenuVOS.forEach(menuVo -> {
            if (menuVo.getRoot() != null && menuVo.getRoot() == 1 && roleMenuMap.containsKey(menuVo.getId())) {
                AdminMenuVO adminMenuVO = nextLowerNode(adminMenuVOS, menuVo, roleMenuMap);
                menuList.add(adminMenuVO);
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
    private AdminMenuVO nextLowerNode(List<AdminMenuVO> menuVos, AdminMenuVO menuVo, Map<String, String> roleMenuMap) {
        List<AdminMenuVO> menuList = new ArrayList<>();
        menuVos.forEach(menuVo2 -> {
            if (menuVo2.getPid().equals(menuVo.getId()) && roleMenuMap.containsKey(menuVo2.getId())) {
                //递归获取子节点
                AdminMenuVO adminMenuVO = nextLowerNode(menuVos, menuVo2, roleMenuMap);
                menuList.add(adminMenuVO);
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
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminMenuVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:39
     */
    @Override
    public List<AdminMenuVO> findIdOrRoleIdTree(String pId) {
        return findIdOrRoleIdTree(pId, null);
    }

    /**
     * 根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单，包括自身
     *
     * @param pId
     * @param roleId
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminMenuVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:39
     */
    @Override
    public List<AdminMenuVO> findIdOrRoleIdTree(String pId, String roleId) {
        // 获取指定角色所有菜单Id： key=菜单Id, value=0
        // List<AdminRoleMenu> roleMenus = adminRoleMenuMapper.findRoleId(roleId);
        Map<String, String> roleMenuMap = new HashMap<>();
        List<AdminRoleMenu> roleIdList = adminRoleMenuMapper.findRoleId(roleId);
        roleIdList.forEach(item -> roleMenuMap.put(item.getMenuId(), item.getMenuId()));
        // 查询菜单数据
        List<AdminMenuVO> adminMenuVOS = BeanDtoVoUtil.listVoStream(this.list(), AdminMenuVO.class);
        // 系统级  ==>  顶级菜单返回  ==>  root == 1
        List<AdminMenuVO> menuList = new LinkedList<>();
        // 树结构菜单 ==> 递归添加
        adminMenuVOS.forEach(menuVo -> {
            if (pId != null) {
                if (menuVo.getRoot()== 1 && pId.equals(menuVo.getId())) {
                    AdminMenuVO adminMenuVO = nextLowerIdNode(adminMenuVOS, menuVo, roleMenuMap);
                    this.setChecked(menuVo, roleMenuMap);
                    menuList.add(adminMenuVO);
                }
            } else {
                if (menuVo.getRoot() == 1) {
                    AdminMenuVO adminMenuVO = nextLowerIdNode(adminMenuVOS, menuVo, roleMenuMap);
                    this.setChecked(menuVo, roleMenuMap);
                    menuList.add(adminMenuVO);
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
    private AdminMenuVO nextLowerIdNode(List<AdminMenuVO> menuVos, AdminMenuVO menuVo, Map<String, String> roleMenuMap) {
        List<AdminMenuVO> menuList = new ArrayList<>();
        menuVos.forEach(menuVo2 -> {
            if (menuVo2.getPid().equals(menuVo.getId())) {
                //递归获取子节点
                AdminMenuVO adminMenuVO = nextLowerNode(menuVos, menuVo2, roleMenuMap);
                this.setChecked(menuVo2, roleMenuMap);
                menuList.add(adminMenuVO);
            }
        });
        menuVo.setMenus(menuList);
        return menuVo;
    }


    /**
     * 根据Pid 查询当前菜单下的所有菜单，包括自身, 返回List 列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminMenuVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:36
     */
    @Override
    public List<AdminMenuVO> findIdOrRoleIdList(String pId) {
        return findIdOrRoleIdList(pId, null);
    }

    /**
     * 根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单，包括自身, 返回List 列表
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminMenuVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:36
     */
    @Override
    public List<AdminMenuVO> findIdOrRoleIdList(String pId, String roleId) {
        // 获取指定角色所有菜单Id： key=菜单Id, value=0
        Map<String, String> roleMenuMap = new HashMap<>();
        if (roleId != null) {
            List<AdminRoleMenu> roleMenuList = adminRoleMenuMapper.findRoleId(roleId);
            if (roleMenuList != null && roleMenuList.size() > 0) {
                roleMenuList.forEach(item -> roleMenuMap.put(item.getMenuId(), item.getMenuId()));
            }
        }
        // 查询菜单数据
        List<AdminMenu> adminMenuList = this.list(new LambdaQueryWrapper<AdminMenu>().orderByAsc(AdminMenu::getSort));
        List<AdminMenuVO> adminMenuVOList = new ArrayList<>();
        if (adminMenuList != null && adminMenuList.size() > 0) {
            adminMenuVOList = BeanDtoVoUtil.listVoStream(adminMenuList, AdminMenuVO.class);
        }
        // 系统级  ==>  顶级菜单返回  ==>  root == 1
        List<AdminMenuVO> menuVoList = new LinkedList<>();
        if (pId == null || pId.equals("") || pId.equals("0")) {
            // 树结构菜单 ==> 递归添加  ==>  所有
            for (AdminMenuVO menuVo : adminMenuVOList) {
                //顶级菜单
                if (menuVo.getRoot().equals(1)) {
                    nextLowerIdNode(adminMenuVOList, menuVo, roleMenuMap, menuVoList);
                    this.setChecked(menuVo, roleMenuMap);
                    menuVoList.add(menuVo);
                }
            }
        } else {
            // 树结构菜单 ==> 递归添加  ==> 指定父Id下
            for (AdminMenuVO menuVo : adminMenuVOList) {
                if (menuVo.getId().equals(pId)) {
                    nextLowerIdNode(adminMenuVOList, menuVo, roleMenuMap, menuVoList);
                    this.setChecked(menuVo, roleMenuMap);
                    //顶级菜单
                    // if (menuVo.getRoot().equals(1) ) {
                    menuVoList.add(menuVo);
                    // }
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
    private void nextLowerIdNode(List<AdminMenuVO> menuVos, AdminMenuVO menuVo, Map<String, String> roleMenuMap, List<AdminMenuVO> menuVoList) {
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
    private void setChecked(AdminMenuVO menu, Map<String, String> roleMenuMap) {
        //权限判断设置选中状态
        if (roleMenuMap.containsKey(menu.getId())) {
            menu.setIsChecked(true);
        } else {
            menu.setIsChecked(false);
        }
    }
}