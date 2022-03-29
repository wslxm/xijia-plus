package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminMenuMapper;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminRoleMenuMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminMenuDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminMenu;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRole;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleMenu;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminMenuQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminMenuVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminMenuService;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleMenuService;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class AdminMenuServiceImpl extends BaseIServiceImpl<AdminMenuMapper, AdminMenu> implements AdminMenuService {

    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;


    @Override
    public List<AdminMenuVO> list(AdminMenuQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(false);
        }
        if (query.getIsTree() == null) {
            query.setIsTree(false);
        }
        if (query.getIsBottomLayer() == null) {
            query.setIsBottomLayer(true);
        }
        // 判断是否只查询当前登录人存在的菜单
        String loginUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        Integer terminal = query.getTerminal();
        //
        String pId = query.getPid();
        String roleId = query.getRoleId();
        Boolean isTree = query.getIsTree();
        Boolean isBottomLayer = query.getIsBottomLayer();
        Integer root = query.getRoot();
        Integer disable = query.getDisable();

        // 1、查询菜单
        List<AdminMenuVO> menuVOList = baseMapper.list(terminal, loginUserId,disable);

        // 2、获取角色拥有的菜单id(没有角色id或没有 角色对应的菜单数据,创建空roleMenuIdList对象)
        List<AdminRoleMenu> userRoleMenus = roleId != null ? adminRoleMenuMapper.findRoleId(roleId) : new ArrayList<>();
        List<String> roleMenuIdList = !userRoleMenus.isEmpty() ? userRoleMenus.stream().map(AdminRoleMenu::getMenuId).collect(Collectors.toList()) : new ArrayList<>();

        // 3、是否需要最后一级数据,false 不需要, 过滤最后一级数据
        if (isBottomLayer != null && !isBottomLayer) {
            menuVOList = menuVOList.stream().filter(p -> (!p.getRoot().equals(Base.MenuRoot.V3.getValue()))).collect(Collectors.toList());
        }
        // 4、根据级别过滤(1-目录 2-菜单 3-页面)
        if (root != null) {
            menuVOList = menuVOList.stream().filter(p -> (p.getRoot() <= root)).collect(Collectors.toList());
        }
        // 5、pid，存在pid, 过滤其他数据
        if (StringUtils.isNotBlank(pId)) {
            menuVOList = menuVOList.stream().filter(p -> (p.getId().equals(pId) || p.getPid().equals(pId))).collect(Collectors.toList());
        }

        // 增加path字段数据
        for (AdminMenuVO menuVo : menuVOList) {
            if (StringUtils.isNotBlank(menuVo.getUrl())) {
                String[] urls = menuVo.getUrl().split("/");
                StringBuilder urlsStr = new StringBuilder();
                for (int i = 1; i < urls.length; i++) {
                    urlsStr.append(urls[i]);
                }
                menuVo.setPathx(urlsStr.toString());
            }
        }

        // 6、result(判断返回tree还是list)
        List<AdminMenuVO> resMenuVoList = new LinkedList<>();
        if (isTree != null && isTree) {
            // 返回 tree (递归处理)
            for (AdminMenuVO menuVo : menuVOList) {
                // 是顶级菜单 或 用户传递的指定的pid时,进行递归往下找
                boolean isPid = false;
                boolean isRootOne = false;
                if (pId != null) {
                    // 当前id是否为用户传递的pid
                    isPid = menuVo.getId().equals(pId);
                } else {
                    // 是否为顶级菜单
                    isRootOne = menuVo.getRoot().equals(Base.MenuRoot.V1.getValue());
                }
                if (isRootOne || isPid) {
                    this.nextLowerIdNodeTreeChecked(menuVOList, menuVo, roleMenuIdList);
                    this.setChecked(menuVo, roleMenuIdList);
                    resMenuVoList.add(menuVo);
                }
            }
        } else {
            // 返回 list (循环处理)
            for (AdminMenuVO menuVo : menuVOList) {
                this.setChecked(menuVo, roleMenuIdList);
                resMenuVoList.add(menuVo);
            }
        }
        return resMenuVoList;
    }


    @Override
    public String insert(AdminMenuDTO dto) {
        AdminMenu adminMenu = dto.convert(AdminMenu.class);
        adminMenu.setCreateUser(JwtUtil.getJwtUser(request).getUserId());
        this.save(adminMenu);
        // 添加菜单给超管默认分配该菜单
        AdminRole sysRole = adminRoleService.findSysRole();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(adminMenu.getId());
        boolean b = adminRoleMenuService.insert(sysRole.getId(), strings);
        return adminMenu.getId();
    }


    @Override
    public Boolean upd(String id, AdminMenuDTO dto) {
        // 判断是否修改了终端, 修改了终端同时, 同时更新下级所有数据的终端
        if (dto.getTerminal() != null) {
            int count = this.count(new LambdaQueryWrapper<AdminMenu>().eq(AdminMenu::getId, id).eq(AdminMenu::getTerminal, dto.getTerminal()));
            if (count < 1) {
                // 查询所有下级数据
                AdminMenuQuery query = new AdminMenuQuery();
                query.setTerminal(null);
                query.setPid(id);
                query.setRoleId(null);
                query.setIsTree(null);
                query.setIsBottomLayer(null);
                List<AdminMenuVO> menus = this.list(query);
                if (!menus.isEmpty()) {
                    List<String> menuIds = menus.stream().map(BaseVo::getId).collect(Collectors.toList());
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
        }
        // 修改当前数据
        AdminMenu entity = dto.convert(AdminMenu.class);
        entity.setId(id);
        return this.updateById(entity);
    }


    @Override
    public List<String> del(String id) {
        AdminMenuQuery query = new AdminMenuQuery();
        query.setTerminal(null);
        query.setPid(id);
        query.setRoleId(null);
        query.setIsTree(null);
        query.setIsBottomLayer(null);
        List<AdminMenuVO> menus = this.list(query);
        if (!menus.isEmpty()) {
            List<String> menuIds = menus.stream().map(BaseVo::getId).collect(Collectors.toList());
            if (!menuIds.isEmpty()) {
                this.removeByIds(menuIds);
                // 生成角色菜单关联数据
                adminRoleMenuService.remove(new LambdaUpdateWrapper<AdminRoleMenu>().in(AdminRoleMenu::getMenuId, menuIds));
            }
            return menuIds;
        }
        return new ArrayList<>();
    }


    @Override
    public List<AdminMenuVO> findTree() {
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
        List<AdminMenuVO> menuVos = BeanDtoVoUtil.listVoStream(adminMenuList, AdminMenuVO.class);
        // return
        List<AdminMenuVO> menuList = new LinkedList<>();
        menuVos.forEach(fatherMenuVo -> {
            if (fatherMenuVo.getRoot() == 1) {
                this.nextLowerNode(menuVos, fatherMenuVo, roleMenuIdList);
                menuList.add(fatherMenuVo);
            }
        });
        return menuList;
    }


    /**
     * 菜单数遍历子节点(递归遍历)，不添加没有权限的数据 Tree
     *
     * @param menuVos      所有节点
     * @param fatherMenuVo 当前菜单节点，遍历后得到的数据的父节点
     * @param roleMenuList 当前用户存在的菜单权限ID
     * @return void
     * @date 2019/11/13 15:20
     */
    private void nextLowerNode(List<AdminMenuVO> menuVos, AdminMenuVO fatherMenuVo, List<String> roleMenuList) {
        menuVos.forEach(menuVo -> {
            if (menuVo.getPid().equals(fatherMenuVo.getId()) && roleMenuList.contains(menuVo.getId())) {
                if (fatherMenuVo.getMenus() == null) {
                    ArrayList<AdminMenuVO> nextMenuVos = new ArrayList<>();
                    nextMenuVos.add(menuVo);
                    fatherMenuVo.setMenus(nextMenuVos);
                } else {
                    fatherMenuVo.getMenus().add(menuVo);
                }
                // 递归
                this.nextLowerNode(menuVos, menuVo, roleMenuList);
            }
        });
    }


    /**
     * 获取指定父节点下的子节点(递归遍历) 权限 isChecked = true||false  Tree
     *
     * @param menuVos        所有节点
     * @param fatherMenuVo   当前节点
     * @param roleMenuIdList 选中角色权限
     * @return void
     * @date 2019/11/13 15:20
     */
    private void nextLowerIdNodeTreeChecked(List<AdminMenuVO> menuVos, AdminMenuVO fatherMenuVo, List<String> roleMenuIdList) {
        menuVos.forEach(menuVo -> {
            if (menuVo.getPid().equals(fatherMenuVo.getId())) {
                if (fatherMenuVo.getMenus() == null) {
                    ArrayList<AdminMenuVO> adminMenuVos = new ArrayList<>();
                    adminMenuVos.add(menuVo);
                    fatherMenuVo.setMenus(adminMenuVos);
                } else {
                    fatherMenuVo.getMenus().add(menuVo);
                }
                this.setChecked(menuVo, roleMenuIdList);
                this.nextLowerIdNodeTreeChecked(menuVos, menuVo, roleMenuIdList);
            }
        });
    }

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