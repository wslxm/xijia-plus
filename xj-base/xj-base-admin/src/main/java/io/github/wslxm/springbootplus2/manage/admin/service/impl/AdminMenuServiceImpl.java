package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
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
import org.springframework.transaction.annotation.Transactional;

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
        boolean isTree = ObjectUtil.defaultIfNull(query.getIsTree(), false);
        boolean isBottomLayer = ObjectUtil.defaultIfNull(query.getIsBottomLayer(), true);
        boolean isNextAll = ObjectUtil.defaultIfNull(query.getIsNextAll(), true);
        String pId = query.getPid();
        String roleId = query.getRoleId();
        Integer root = query.getRoot();
        Integer disable = query.getDisable();
        // 判断是否只查询当前登录人存在的菜单
        String loginUserId = ObjectUtil.defaultIfNull(query.getIsLoginUser(), () -> JwtUtil.getJwtUser(request).getUserId(), null);

        // 1、查询菜单
        List<AdminMenuVO> menuVOList = baseMapper.list( loginUserId,disable);

        // 2、获取角色拥有的菜单id(没有角色id或没有 角色对应的菜单数据,创建空roleMenuIdList对象)
        List<AdminRoleMenu> userRoleMenus = roleId != null ? adminRoleMenuMapper.findRoleId(roleId) : new ArrayList<>();
        List<String> roleMenuIdList = !userRoleMenus.isEmpty() ? userRoleMenus.stream().map(AdminRoleMenu::getMenuId).collect(Collectors.toList()) : new ArrayList<>();

        // 3、是否需要最后一级数据,false 不需要, 过滤最后一级数据
        if (!isBottomLayer) {
            menuVOList = menuVOList.stream().filter(p -> (!p.getRoot().equals(Base.MenuRoot.V3.getValue()))).collect(Collectors.toList());
        }
        // 4、根据级别过滤(1-目录 2-菜单 3-页面)
        if (root != null) {
            menuVOList = menuVOList.stream().filter(p -> (p.getRoot() <= root)).collect(Collectors.toList());
        }


        // 增加path字段数据(vue路由)
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

        // 5、判断是否只查询指定pid 数据
        List<AdminMenuVO> pMenuListVO = new ArrayList<>();
        if (StringUtils.isNotBlank(pId)) {
            //  5.1、找到父级code数据(只有一条)
            for (AdminMenuVO p : menuVOList) {
                if (p.getId().equals(pId)) {
                    pMenuListVO.add(p);
                    break;
                }
            }
        } else {
            // 5.2、如果没有, 设置为父级字典数据为顶级pid=0的数据 (可能多条)
            for (AdminMenuVO p : menuVOList) {
                if (p.getRoot().equals(Base.MenuRoot.V1.getValue())) {
                    pMenuListVO.add(p);
                }
            }
        }
        // 没有找顶级id数据 或 没有菜单数据,返回空
        if (pMenuListVO.isEmpty()) {
            return pMenuListVO;
        }

        // 6、递归获取 子级数据, pDictListVO 为tree数据(tree),  diceIds为指定code层级下所有字典id收集(返回 list 需要)
        List<String> menuIds = new ArrayList<>();
        // 返回 tree (递归处理)
        for (AdminMenuVO pMenuVo : pMenuListVO) {
            menuIds.add(pMenuVo.getId());
            this.setChecked(pMenuVo, roleMenuIdList);
            if (isNextAll) {
                this.nextLowerIdNodeTreeChecked(menuVOList, pMenuVo, roleMenuIdList, menuIds, null);
            } else {
                this.nextLowerIdNodeTreeChecked(menuVOList, pMenuVo, roleMenuIdList, menuIds, 1);
            }
        }

        // 7、判断返回 tree | list
        if (isTree) {
            return pMenuListVO;
        } else {
            return menuVOList.stream().filter(i -> menuIds.contains(i.getId())).collect(Collectors.toList());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(AdminMenuDTO dto) {
        AdminMenu adminMenu = dto.convert(AdminMenu.class);
        adminMenu.setCreateUser(JwtUtil.getJwtUser(request).getUserId());
        this.save(adminMenu);

        // 添加菜单给超管默认分配该菜单
        AdminRole sysRole = adminRoleService.findSysRole();
        adminRoleMenuService.updRoleMenus(sysRole.getId(), CollUtil.newArrayList(adminMenu.getId()));
        return adminMenu.getId();
    }


    @Override
    public Boolean upd(String id, AdminMenuDTO dto) {
        // 修改当前数据
        AdminMenu entity = dto.convert(AdminMenu.class);
        entity.setId(id);
        return this.updateById(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> del(String id) {
        // 查询出所有子级菜单数据
        AdminMenuQuery query = new AdminMenuQuery();
        query.setPid(id);
        List<AdminMenuVO> menus = this.list(query);
        ValidUtil.isTrue(menus.isEmpty(), "没有找到要删除的数据");
        // 删除菜单
        List<String> menuIds = menus.stream().map(BaseVo::getId).collect(Collectors.toList());
        this.removeByIds(menuIds);
        // 删除角色菜单关联数据
        adminRoleMenuService.delBatchByMenuIds(menuIds);
        return menuIds;
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
     * @param pMenuVo   当前节点
     * @param roleMenuIdList 选中角色权限
     * @param ids                 收集指定code下所有字典数据 id
     * @param recursiveHierarchy  递归层级 1-获取下一级 2-获取下2级 ....以此类推, null不限层级直到没有下级
     * @return void
     * @date 2019/11/13 15:20
     */
    private void nextLowerIdNodeTreeChecked(List<AdminMenuVO> menuVos, AdminMenuVO pMenuVo, List<String> roleMenuIdList, List<String> ids, Integer recursiveHierarchy) {
        // 递归层级控制
        if (recursiveHierarchy != null) {
            if (recursiveHierarchy == 0) {
                return;
            }
            recursiveHierarchy--;
        }
        for (AdminMenuVO zMenuVo : menuVos) {
            if (zMenuVo.getPid().equals(pMenuVo.getId())) {
                if (pMenuVo.getMenus() == null) {
                    ArrayList<AdminMenuVO> adminMenuVos = new ArrayList<>();
                    adminMenuVos.add(zMenuVo);
                    pMenuVo.setMenus(adminMenuVos);
                } else {
                    pMenuVo.getMenus().add(zMenuVo);
                }
                // 获取ids
                ids.add(zMenuVo.getId());
                this.setChecked(zMenuVo, roleMenuIdList);
                this.nextLowerIdNodeTreeChecked(menuVos, zMenuVo, roleMenuIdList, ids, recursiveHierarchy);
            }
        }
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