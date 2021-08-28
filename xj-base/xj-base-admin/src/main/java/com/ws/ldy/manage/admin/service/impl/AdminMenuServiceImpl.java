package com.ws.ldy.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.auth.util.JwtUtil;
import com.ws.ldy.core.base.model.BaseVo;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.config.error.ErrorException;
import com.ws.ldy.core.enums.Admin;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.admin.mapper.AdminMenuMapper;
import com.ws.ldy.manage.admin.mapper.AdminRoleMenuMapper;
import com.ws.ldy.manage.admin.model.dto.AdminMenuDTO;
import com.ws.ldy.manage.admin.model.entity.AdminMenu;
import com.ws.ldy.manage.admin.model.entity.AdminRole;
import com.ws.ldy.manage.admin.model.entity.AdminRoleMenu;
import com.ws.ldy.manage.admin.model.query.AdminMenuQuery;
import com.ws.ldy.manage.admin.model.vo.AdminMenuVO;
import com.ws.ldy.manage.admin.service.AdminMenuService;
import com.ws.ldy.manage.admin.service.AdminRoleMenuService;
import com.ws.ldy.manage.admin.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminMenuServiceImpl extends BaseIServiceImpl<AdminMenuMapper, AdminMenu> implements AdminMenuService {

    //
    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;


    @Override
    public IPage<AdminMenuVO> list(AdminMenuQuery query) {
        LambdaQueryWrapper<AdminMenu> queryWrapper = new LambdaQueryWrapper<AdminMenu>()
                .eq(query.getTerminal() != null, AdminMenu::getTerminal, query.getTerminal())
                .orderByAsc(AdminMenu::getSort)
                .orderByAsc(AdminMenu::getId);
        if (query.getCurrent() <= 0) {
            // list
            IPage<AdminMenuVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminMenuVO.class));
        } else {
            // page
            IPage<AdminMenuVO> page = BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), AdminMenuVO.class);
            return page.setRecords(page.getRecords().stream().filter(i -> !i.getRoot().equals(Admin.MenuRoot.V3.getValue())).collect(Collectors.toList()));
        }
    }


    @Override
    public Boolean insert(AdminMenuDTO dto) {
        AdminMenu adminMenu = dto.convert(AdminMenu.class);
        this.save(adminMenu);
        // 添加菜单给超管默认分配该菜单
        AdminRole sysRole = adminRoleService.findSysRole();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(adminMenu.getId());
        return adminRoleMenuService.insert(sysRole.getId(), strings);
    }


    @Override
    public Boolean upd(String id, AdminMenuDTO dto) {
        // 判断是否修改了终端
        int count = 0;
        if (dto.getTerminal() != null) {
            count = this.count(new LambdaQueryWrapper<AdminMenu>().eq(AdminMenu::getId, id).eq(AdminMenu::getTerminal, dto.getTerminal()));
        }
        if (count < 1) {
            // 修改了终端,更新下级所有数据的终端 -> 查询所有下级数据
            List<String> menuIds = this.menuList(id, null, null).stream().map(BaseVo::getId).collect(Collectors.toList());
            if (!menuIds.isEmpty()) {
                //
                List<AdminMenu> updList = new ArrayList<>();
                for (String menuId : menuIds) {
                    AdminMenu updAdminMenu = new AdminMenu();
                    updAdminMenu.setId(menuId);
                    updAdminMenu.setTerminal(dto.getTerminal());
                    updList.add(updAdminMenu);
                }
                this.updateBatchById(updList);
            }
        }
        // 修改当前数据
        AdminMenu entity = dto.convert(AdminMenu.class);
        entity.setId(id);
        return this.updateById(entity);
    }


    @Override
    public List<String> del(String menuId) {
        List<String> menuIds = this.menuList(menuId, null, null).stream().map(BaseVo::getId).collect(Collectors.toList());
        if (!menuIds.isEmpty()) {
            this.removeByIds(menuIds);
            adminRoleMenuService.remove(new LambdaUpdateWrapper<AdminRoleMenu>().in(AdminRoleMenu::getMenuId, menuIds));
        }
        return menuIds;
    }


    /**
     * 查询 Tree 菜单 -->  不返回没有权限的数据
     * <P>
     *     1- 查询用户所有角色菜单数据（非禁用角色+ 非禁用菜单）, 没有返回错误提示
     *     2- 存在查询当前用户角色的所有菜单权限|  Sort排序 | 未禁用的，没有数据返回错误提示
     *     3- 递归把List数据处理成树菜单结构数据
     * <P>
     *   --return ：第一层数据为顶级菜单 root=1, 下级为 tree数据
     *
     * @author wangsong
     * @date 2020/8/5 0005 15:38
     * @return java.util.List<com.ws.ldy.modules.admin.model.vo.AdminMenuVO>
     * @version 1.0.0
     */
    @Override
    public List<AdminMenuVO> getMenuTree() {
        List<AdminRoleMenu> userRoleMenus = adminRoleMenuMapper.findByUserIdAndDisableFetchMenu(JwtUtil.getJwtUser(request).getUserId(), Base.Disable.V0.getValue());
        if (userRoleMenus == null || userRoleMenus.isEmpty()) {
            throw new ErrorException(RType.USER_NO_MENU);
        }
        List<String> roleMenuIdList = userRoleMenus.stream().map(AdminRoleMenu::getMenuId).collect(Collectors.toList());
        List<AdminMenu> adminMenuList = this.list(new LambdaQueryWrapper<AdminMenu>()
                .orderByAsc(AdminMenu::getSort)
                .eq(AdminMenu::getDisable, 0)
                .in(AdminMenu::getId, roleMenuIdList)
        );
        if (adminMenuList == null || adminMenuList.isEmpty()) {
            throw new ErrorException(RType.USER_NO_MENU);
        }
        List<AdminMenuVO> menuVOS = BeanDtoVoUtil.listVoStream(adminMenuList, AdminMenuVO.class);
        //return
        List<AdminMenuVO> menuList = new LinkedList<>();
        menuVOS.forEach(fatherMenuVo -> {
            if (fatherMenuVo.getRoot() == 1) {
                this.nextLowerNode(menuVOS, fatherMenuVo, roleMenuIdList);
                menuList.add(fatherMenuVo);
            }
        });
        return menuList;
    }


    /**
     * 菜单数遍历子节点(递归遍历)，不添加没有权限的数据 Tree
     * @param menuVos 所有节点
     * @param fatherMenuVo  当前菜单节点，遍历后得到的数据的父节点
     * @param roleMenuList  当前用户存在的菜单权限ID
     * @date 2019/11/13 15:20
     * @return void
     */
    //@formatter:off
    private void nextLowerNode(List<AdminMenuVO> menuVos, AdminMenuVO fatherMenuVo, List<String> roleMenuList) {
        menuVos.forEach(menuVo -> {
            if (menuVo.getPid().equals(fatherMenuVo.getId()) && roleMenuList.contains(menuVo.getId())) {
                if (fatherMenuVo.getMenus() == null) {
                    ArrayList<AdminMenuVO> menuVOS = new ArrayList<>();
                    menuVOS.add(menuVo);
                    fatherMenuVo.setMenus(menuVOS);
                } else {
                    fatherMenuVo.getMenus().add(menuVo);
                }
                // 递归
                this.nextLowerNode(menuVos, menuVo, roleMenuList);
            }
        });
    }


    //@formatter:on
    //=========================================================================
    //=========================================================================
    //=========================================================================

    /**
     * 根据父id 查询所有子节点数据（包括自己） , 根据角色 权限赋值isChecked = true||false
     *
     * @param pId 父id-非必传,没有获取所有
     * @param roleId 角色id-非必传, 没有所有 isChecked = false
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminMenuVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:36
     */
    @Override
    public List<AdminMenuVO> menuList(String pId, String roleId, Integer terminal) {
        List<AdminRoleMenu> userRoleMenus = roleId != null ? adminRoleMenuMapper.findRoleId(roleId) : new ArrayList<>();
        List<String> roleMenuIdList = !userRoleMenus.isEmpty() ? userRoleMenus.stream().map(AdminRoleMenu::getMenuId).collect(Collectors.toList()) : new ArrayList<>();
        List<AdminMenuVO> adminMenuVOList = BeanDtoVoUtil.listVoStream(this.list(new LambdaQueryWrapper<AdminMenu>()
                .eq(terminal != null, AdminMenu::getTerminal, terminal)
                .orderByAsc(AdminMenu::getSort)
        ), AdminMenuVO.class);
        // result
        List<AdminMenuVO> menuVoList = new LinkedList<>();
        if (pId == null) {
            adminMenuVOList.forEach(fatherMenuVo -> {
                if (fatherMenuVo.getRoot() == 1) {
                    menuVoList.add(fatherMenuVo);
                    this.setChecked(fatherMenuVo, roleMenuIdList);
                    this.nextLowerIdNodeListChecked(adminMenuVOList, fatherMenuVo, roleMenuIdList, menuVoList);
                }
            });
        } else {
            adminMenuVOList.forEach(fatherMenuVo -> {
                if (pId.equals(fatherMenuVo.getId())) {
                    menuVoList.add(fatherMenuVo);
                    this.setChecked(fatherMenuVo, roleMenuIdList);
                    this.nextLowerIdNodeListChecked(adminMenuVOList, fatherMenuVo, roleMenuIdList, menuVoList);
                }
            });
        }
        return menuVoList;
    }


    /**
     * 获取指定父节点下的子节点(递归遍历),权限 isChecked = true||false
     *
     * @param menuVos     所有节点
     * @param fatherMenuVo  当前节点
     * @param roleMenuIdList 选中角色权限
     * @param menuVoList 返回数据,列表
     * @return void
     * @date 2019/11/13 15:20
     */
    private void nextLowerIdNodeListChecked(List<AdminMenuVO> menuVos, AdminMenuVO fatherMenuVo, List<String> roleMenuIdList, List<AdminMenuVO> menuVoList) {
        menuVos.forEach(menuVo -> {
            if (menuVo.getPid().equals(fatherMenuVo.getId())) {
                menuVoList.add(menuVo);
                this.setChecked(menuVo, roleMenuIdList);
                this.nextLowerIdNodeListChecked(menuVos, menuVo, roleMenuIdList, menuVoList);
            }
        });
    }

    //=========================================================================
    //=========================================================================
    //=========================================================================
    /**
     *  根据 pid + roleId查询Tree 结构菜单 -->  返回无权限数据, 权限 isChecked = true||false 标识
     * <P>
     *    - 查询角色拥有的所有菜单，并把id放入 - roleMenuIdList,如果 roleId==null 或没有数据则 roleMenuIdList.size = 0 防止空指针异常
     *    - 查询所有菜单，存在 pid 只获取指定父级下的数据, 不存在获取所有数据 || 第一层数据= List <root == 1>的数据
     *    - return ：第一层数据为顶级菜单 root=1, 下级为 tree数据
     * </P>
     * @param pId 父Id
     * @param roleId 父Id
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminMenuVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/19 0019 20:39
     */
    @Override
    public List<AdminMenuVO> menuTree(String pId, String roleId, Integer terminal) {
        // 查询角色菜单
        List<AdminRoleMenu> userRoleMenus = roleId != null ? adminRoleMenuMapper.findRoleId(roleId) : new ArrayList<>();
        // 获取角色菜单的ids
        List<String> roleMenuIdList = !userRoleMenus.isEmpty() ? userRoleMenus.stream().map(AdminRoleMenu::getMenuId).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有菜单
        List<AdminMenuVO> adminMenuVOList = BeanDtoVoUtil.listVoStream(this.list(new LambdaQueryWrapper<AdminMenu>()
                .eq(terminal != null, AdminMenu::getTerminal, terminal)
                .orderByAsc(AdminMenu::getSort)
        ), AdminMenuVO.class);
        List<AdminMenuVO> menuList = new LinkedList<>();
        if (pId == null) {
            adminMenuVOList.forEach(menuVo -> {
                if (menuVo.getRoot() == 1) {
                    this.nextLowerIdNodeChecked(adminMenuVOList, menuVo, roleMenuIdList);
                    this.setChecked(menuVo, roleMenuIdList);
                    menuList.add(menuVo);
                }
            });
        } else {
            adminMenuVOList.forEach(menuVo -> {
                if (menuVo.getRoot() == 1 && pId.equals(menuVo.getId())) {
                    this.nextLowerIdNodeChecked(adminMenuVOList, menuVo, roleMenuIdList);
                    this.setChecked(menuVo, roleMenuIdList);
                    menuList.add(menuVo);
                }
            });
        }
        return menuList;
    }


    /**
     * 获取指定父节点下的子节点(递归遍历) 权限 isChecked = true||false  Tree
     *
     * @param menuVos     所有节点
     * @param fatherMenuVo      当前节点
     * @param roleMenuIdList 选中角色权限
     * @return void
     * @date 2019/11/13 15:20
     */
    //@formatter:off
    private void nextLowerIdNodeChecked(List<AdminMenuVO> menuVos, AdminMenuVO fatherMenuVo, List<String> roleMenuIdList) {
        menuVos.forEach(menuVo -> {
            if (menuVo.getPid().equals(fatherMenuVo.getId())) {
                if (fatherMenuVo.getMenus() == null) {
                    ArrayList<AdminMenuVO> adminMenuVOS = new ArrayList<>();
                    adminMenuVOS.add(menuVo);
                    fatherMenuVo.setMenus(adminMenuVOS);
                } else {
                    fatherMenuVo.getMenus().add(menuVo);
                }
                this.setChecked(menuVo, roleMenuIdList);
                this.nextLowerIdNodeChecked(menuVos, menuVo, roleMenuIdList);
            }
        });
    }
    //@formatter:on
    //=========================================================================
    //=========================================================================
    //=========================================================================

    /**
     * 判断并设置为选中状态
     */
    private void setChecked(AdminMenuVO menu, List<String> roleMenuIdList) {
        //权限判断设置选中状态
        if (roleMenuIdList.contains(menu.getId())) {
            menu.setIsChecked(true);
        } else {
            menu.setIsChecked(false);
        }
    }
}