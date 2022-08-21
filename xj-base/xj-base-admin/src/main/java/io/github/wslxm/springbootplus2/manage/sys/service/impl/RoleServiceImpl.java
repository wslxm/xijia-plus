package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.manage.sys.mapper.RoleMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.RoleDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Role;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleUser;
import io.github.wslxm.springbootplus2.manage.sys.model.query.RoleQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;
import io.github.wslxm.springbootplus2.manage.sys.service.AuthorityService;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleMenuService;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleService;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class RoleServiceImpl extends BaseIServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 超级管理员角色 code（勿修改）
     */
    private final static String ROLE_SYS = "SYS";

    @Autowired
    private AuthorityService adminAuthorityService;
    @Autowired
    private RoleService adminRoleService;
    @Autowired
    private RoleUserService roleUserService;
    @Autowired
    private RoleMenuService adminRoleMenuService;
//    @Autowired
//    private RoleAuthService adminRoleAuthService;


    @Override
    public IPage<RoleVO> findPage(RoleQuery query) {

        // 是否只查询当前登录人创建的角色
        String loginUserId = ObjectUtil.defaultIfNull(query.getIsLoginUser(), () -> JwtUtil.getJwtUser(request).getUserId(), null);
        Boolean isUserIdChecked = ObjectUtil.defaultIfNull(query.getIsUserIdChecked(), false);
        String userId = query.getUserId();

        // 是否查询所有数据,使用 IsChecked 标记，将queryUserId单独存放起来
        if (isUserIdChecked) {
            query.setUserId(null);
        }
        // 查询
        IPage<RoleVO> page = new Page<>(query.getCurrent(), query.getSize());
        page = page.setRecords(baseMapper.list(page, query, loginUserId));
        if (page.getRecords() == null || page.getRecords().size() == 0) {
            return page;
        }

        // 处理指定用户当前拥有的角色
        if (isUserIdChecked && StringUtils.isNotBlank(userId)) {
            List<RoleUser> roleUsers = roleUserService.list(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
            if (roleUsers != null && roleUsers.size() > 0) {
                List<String> userRoleIds = roleUsers.stream().map(RoleUser::getRoleId).collect(Collectors.toList());
                page.getRecords().forEach((p) -> p.setIsChecked(userRoleIds.contains(p.getId())));
            }
        }
        return page;
    }


    /**
     * 添加角色-默认有所有URL 权限
     *
     * @param dto
     * @return java.lang.Boolean
     * @author wangsong
     * @date 2020/9/19 0019 10:56
     * @version 1.0.1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(RoleDTO dto) {
        this.isCodeRepeat(dto.getCode(),null);

        Role role = dto.convert(Role.class);
        this.save(role);
        // 给角色分配菜单权限
        adminRoleMenuService.updRoleMenus(role.getId(), dto.getMenuIds());
        return role.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upd(String id, RoleDTO dto) {
        this.isCodeRepeat(dto.getCode(),id);

        Role role = dto.convert(Role.class);
        role.setId(id);
        boolean b = this.updateById(role);

        // 编辑入口必传菜单，如果没传可能是在操作启用禁用等操作,不对菜单做处理
        if (dto.getMenuIds() != null && dto.getMenuIds().size() > 0) {
            // 给角色分配菜单权限(先删除后添加)
            adminRoleMenuService.updRoleMenus(role.getId(), dto.getMenuIds());
        }
        return b;
    }


    @Override
    public Role findSysRole() {
        return this.getOne(new LambdaQueryWrapper<Role>().eq(Role::getCode, ROLE_SYS));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean del(String roleId) {
        // 删除角色和角色相关的关系表
        roleUserService.delByRoleId(roleId);
        adminRoleMenuService.delByRoleId(roleId);
        // adminRoleAuthService.delByRoleId(roleId);
        return this.removeById(roleId);
    }


    /**
     * 角色code重复验证
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2022/8/20 0020 14:33
     * @version 1.0.0
     */
    private void isCodeRepeat(String code, String excludeId) {
        long count = this.count(new LambdaQueryWrapper<Role>()
                .eq(Role::getCode, code)
                .ne(Role::getId, excludeId)
        );
        if (count > 0) {
            throw new ErrorException("角色code已存在");
        }
    }
}