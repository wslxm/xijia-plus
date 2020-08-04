package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleMapper;
import com.ws.ldy.modules.admin.model.entity.AdminRole;
import com.ws.ldy.modules.admin.model.entity.AdminRoleUser;
import com.ws.ldy.modules.admin.model.vo.AdminRoleVO;
import com.ws.ldy.modules.admin.service.AdminRoleService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminRoleServiceImpl extends BaseIServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleUserServiceImpl roleUserAdminService;

    @Override
    public List<AdminRoleVO> findRoleChecked(String userId) {
        //查询所有角色
        List<AdminRole> roles = this.list();
        //查询用户当前角色
        List<AdminRoleUser> roleUsers = roleUserAdminService.list(new QueryWrapper<AdminRoleUser>().eq("user_id", userId));
        Map<String, String> roleUserMap = new HashMap<>();
        roleUsers.forEach(item -> roleUserMap.put(item.getRoleId(), "0"));
        //返回数据
        List<AdminRoleVO> adminRoleVOList = new ArrayList<>();
        roles.forEach(role -> {
            AdminRoleVO roleVo = role.convert(AdminRoleVO.class);
            if (roleUserMap.containsKey(role.getId())) {
                roleVo.setIsChecked(true);
            } else {
                roleVo.setIsChecked(false);
            }
            adminRoleVOList.add(roleVo);
        });
        return adminRoleVOList;
    }


    @Override
    @Transactional
    public boolean updUserRole(String userId, String[] roleIds) {
        //删除原角色所有权限数据
        boolean result = roleUserAdminService.remove(new QueryWrapper<AdminRoleUser>().eq("user_id", userId));
        if (roleIds.length <= 0) {
            return true;
        }
        List<AdminRoleUser> roleUserList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            roleUserList.add(new AdminRoleUser(roleIds[i], userId));
        }
        return roleUserAdminService.saveBatch(roleUserList);
    }
}