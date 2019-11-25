package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.entity.RoleUserAdmin;
import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.service.RoleUserAdminService;
import com.ws.ldy.adminconsole.service.base.impl.BaseAdminConsoleServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class RoleUserAdminServiceImpl extends BaseAdminConsoleServiceImpl<RoleUserAdmin, Integer> implements RoleUserAdminService {


    @Override
    public List<RoleUserAdmin> findRoleId(Integer roleId) {
        return dao.roleUserDao.findRoleId(roleId);
    }

    @Override
    public List<UserAdmin> RoleUserChecked(List<UserAdmin> users, Integer roleId) {
        // 当前角色下的用户
        Map<Integer, Integer> roleUserMap = new HashMap<>(8);
        List<RoleUserAdmin> roleUsers = dao.roleUserDao.findRoleId(roleId);
        roleUsers.forEach(item -> roleUserMap.put(item.getUserId(), item.getRoleId()));
        //赋值选中状态
        for (UserAdmin user : users) {
            if (roleUserMap.containsKey(user.getId())) {
                user.setLAY_CHECKED(true);
            }
        }
        return users;
    }


    @Override
    public void updRoleUser(Integer roleId, Integer[] userIds) {
        //计算角色用户并添加和删除
        Map<Integer, Integer> roleUserMap = new HashMap<>(8);     //后台当前角色用户--判断添加
        Map<Integer, Integer> roleUserIdsMap = new HashMap<>(8);  //前台传入角色用户--判断删除
        List<RoleUserAdmin> roleUsers = dao.roleUserDao.findRoleId(roleId);
        roleUsers.forEach(item -> roleUserMap.put(item.getUserId(), item.getRoleId()));
        //计算添加，遍历传如数据，如发现后台不存在则添加
        List<RoleUserAdmin> addRoleUser = new ArrayList<>();
        if (userIds != null) {
            for (Integer userId : userIds) {
                if (!roleUserMap.containsKey(userId)) {
                    addRoleUser.add(new RoleUserAdmin(roleId, userId));
                }
                roleUserIdsMap.put(userId, roleId);
            }
        }
        //计算删除，遍历后台数据，如发现前台传入值不包含则删除
        List<RoleUserAdmin> delRoleUser = new ArrayList<>();
        for (RoleUserAdmin roleUser : roleUsers) {
            if (!roleUserIdsMap.containsKey(roleUser.getUserId())) {
                delRoleUser.add(roleUser);
            }
        }
        if (addRoleUser.size() > 0) {
            dao.roleUserDao.saveAll(addRoleUser);
        }
        if (delRoleUser.size() > 0) {
            dao.roleUserDao.deleteInBatch(delRoleUser);
        }
    }
}
