package com.ws.ldy.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.ldy.admin.mapper.RoleAdminMapper;
import com.ws.ldy.admin.model.entity.RoleAdmin;
import com.ws.ldy.admin.model.entity.RoleUserAdmin;
import com.ws.ldy.admin.model.vo.RoleAdminVo;
import com.ws.ldy.admin.service.RoleAdminService;
import com.ws.ldy.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleAdminServiceImpl extends BaseIServiceImpl<RoleAdminMapper, RoleAdmin> implements RoleAdminService {

    @Resource
    private RoleUserAdminServiceImpl roleUserAdminService;

    @Override
    public List<RoleAdminVo> findRoleChecked(String userId) {
        //查询所有角色
        List<RoleAdmin> roles = this.list();
        //查询用户当前角色
        List<RoleUserAdmin> roleUsers = roleUserAdminService.list(new QueryWrapper<RoleUserAdmin>().eq("user_id", userId));
        Map<Integer, Integer> roleUserMap = new HashMap<>();
        roleUsers.forEach(item -> roleUserMap.put(item.getRoleId(), 0));
        //返回数据
        List<RoleAdminVo> roleAdminVoList = new ArrayList<>();
        roles.forEach(role -> {
            RoleAdminVo roleVo = role.convert(RoleAdminVo.class);
            if (roleUserMap.containsKey(role.getId())) {
                roleVo.setIsChecked(true);
            } else {
                roleVo.setIsChecked(false);
            }
            roleAdminVoList.add(roleVo);
        });
        return roleAdminVoList;
    }


    @Override
    @Transactional
    public boolean updUserRole(Integer userId, Integer[] roleIds) {
        //删除原角色
        boolean result = roleUserAdminService.remove(new QueryWrapper<RoleUserAdmin>().eq("user_id", userId));
        if (roleIds.length <= 0) {
            return true;
        }
        List<RoleUserAdmin> roleUserList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            roleUserList.add(new RoleUserAdmin(roleIds[i], userId));
        }
        return roleUserAdminService.saveBatch(roleUserList);
    }
}