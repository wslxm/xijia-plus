package com.ws.ldy.admin.service.impl;

import com.ws.ldy.admin.entity.RoleAdmin;
import com.ws.ldy.admin.entity.RoleUserAdmin;
import com.ws.ldy.admin.service.RoleAdminService;
import com.ws.ldy.admin.vo.RoleAdminVo;
import com.ws.ldy.base.query.DeleteCriteria;
import com.ws.ldy.base.query.QueryCriteria;
import com.ws.ldy.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleAdminServiceImpl extends BaseServiceImpl<RoleAdmin, Integer> implements RoleAdminService {

    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminService;

    @Override
    public List<RoleAdminVo> findRoleChecked(String userId) {
        //查询所有角色
        List<RoleAdmin> roles = this.list();
        //查询用户当前角色
        List<RoleUserAdmin> roleUsers = roleUserAdminService.list(new QueryCriteria().eq("userId", userId));
        Map<Integer, Integer> roleUserMap = new HashMap<>();
        roleUsers.forEach(item -> roleUserMap.put(item.getRoleId(), 0));
        //返回数据
        List<RoleAdminVo> roleAdminVoList = new ArrayList<>();
        roles.forEach(role -> {
            RoleAdminVo roleVo = role.convert(RoleAdminVo.class);
            if (roleUserMap.containsKey(role.getId())) {
                roleVo.setChecked(true);
            } else {
                roleVo.setChecked(false);
            }
            roleAdminVoList.add(roleVo);
        });
        return roleAdminVoList;
    }


    @Override
    @Transactional
    public boolean updUserRole(Integer userId, Integer[] roleIds) {
        //删除原角色
        int result = roleUserAdminService.delete(new DeleteCriteria<RoleUserAdmin>().eq("user_id", userId));
        List<RoleUserAdmin> roleUserList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            roleUserList.add(new RoleUserAdmin(roleIds[i], userId));
        }
        return roleUserAdminService.saveAll(roleUserList);
    }
}