package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.manage.sys.mapper.RoleUserMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleUser;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserRolesVO;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updUserRole(String userId, List<String> roleIds) {
        //删除原角色所有权限数据
        this.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
        if (roleIds == null || roleIds.size() <= 0) {
            return true;
        }
        List<RoleUser> roleUserList = new ArrayList<>();
        roleIds.forEach(roleId -> roleUserList.add(new RoleUser(roleId, userId)));
        return this.saveBatch(roleUserList, 1024);
    }


    @Override
    public boolean delByUserId(String userId) {
        return this.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
    }

    @Override
    public boolean delByRoleId(String roleId) {
        return this.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getRoleId, roleId));
    }

    @Override
    public Map<String, List<SysUserRolesVO>> findUserRoles(List<String> userIds) {
        List<SysUserRolesVO> userRoles = baseMapper.findUserRoles(userIds);
        if (userRoles != null && userRoles.size() > 0) {
            Map<String, List<SysUserRolesVO>> userRolesMaps = userRoles.stream().collect(Collectors.groupingBy(SysUserRolesVO::getUserId));
            return userRolesMaps;
        } else {
            return new HashMap<>();
        }
    }
}
