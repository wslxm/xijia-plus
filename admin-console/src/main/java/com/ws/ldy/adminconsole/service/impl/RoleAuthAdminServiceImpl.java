package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.dao.AuthorityAdminDao;
import com.ws.ldy.adminconsole.dao.RoleAuthAdminDao;
import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.entity.RoleAuthAdmin;
import com.ws.ldy.adminconsole.service.RoleAuthAdminService;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
@Service
public class RoleAuthAdminServiceImpl extends BaseServiceApiImpl<RoleAuthAdmin, Integer> implements RoleAuthAdminService {

    @Autowired
    private RoleAuthAdminDao roleAuthAdminDao;
    @Autowired
    private AuthorityAdminDao authorityAdminDao;


    @Override
    public List<RoleAuthAdmin> findUserIdRoleAuthority(Integer userId) {
        return roleAuthAdminDao.findUserIdRoleAuthority(userId);
    }


    @Override
    public List<AuthorityAdmin> findRoleAuthorityChecked(Integer roleId) {
        List<RoleAuthAdmin> list = roleAuthAdminDao.findRoleId(roleId);
        Map<Integer, Integer> map = new HashMap<>(8);
        list.forEach(item -> map.put(item.getAuthId(), 0));
        List<AuthorityAdmin> authorityList = authorityAdminDao.findAll();
        for (AuthorityAdmin authority : authorityList) {
            if (map.containsKey(authority.getId())) {
                authority.setLAY_CHECKED(true);
            }
        }
        return authorityList;
    }

    @Override
    public void roleUrlAuth(Integer roleId, Integer[] userIds) {
        //计算角色用户并添加和删除
        Map<Integer, Integer> roleUserMap = new HashMap<>(8);     //后台当前角色用户--判断添加
        Map<Integer, Integer> roleUserIdsMap = new HashMap<>(8);  //前台传入角色用户--判断删除
        List<RoleAuthAdmin> roleUsers = roleAuthAdminDao.findRoleId(roleId);
        roleUsers.forEach(item -> roleUserMap.put(item.getAuthId(), item.getRoleId()));
        //计算添加，遍历传如数据，如发现后台不存在则添加
        List<RoleAuthAdmin> addRoleUser = new ArrayList<>();
        if (userIds != null) {
            for (Integer userId : userIds) {
                if (!roleUserMap.containsKey(userId)) {
                    addRoleUser.add(new RoleAuthAdmin(roleId, userId));
                }
                roleUserIdsMap.put(userId, roleId);
            }
        }
        //计算删除，遍历后台数据，如发现前台传入值不包含则删除
        List<RoleAuthAdmin> delRoleUser = new ArrayList<>();
        for (RoleAuthAdmin roleAuth : roleUsers) {
            if (!roleUserIdsMap.containsKey(roleAuth.getAuthId())) {
                delRoleUser.add(roleAuth);
            }
        }
        if (addRoleUser.size() > 0) {
            roleAuthAdminDao.saveAll(addRoleUser);
        }
        if (delRoleUser.size() > 0) {
            roleAuthAdminDao.deleteInBatch(delRoleUser);
        }
    }
}

