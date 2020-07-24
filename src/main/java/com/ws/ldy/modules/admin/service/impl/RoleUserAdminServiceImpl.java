package com.ws.ldy.modules.admin.service.impl;

import com.ws.ldy.modules.admin.mapper.RoleUserAdminMapper;
import com.ws.ldy.modules.admin.model.entity.RoleUserAdmin;
import com.ws.ldy.modules.admin.model.entity.UserAdmin;
import com.ws.ldy.modules.admin.model.vo.UserAdminVo;
import com.ws.ldy.modules.admin.service.RoleUserAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class RoleUserAdminServiceImpl extends BaseIServiceImpl<RoleUserAdminMapper, RoleUserAdmin> implements RoleUserAdminService {

    @Override
    public List<RoleUserAdmin> findRoleId(Integer roleId) {
        return baseMapper.findRoleId(roleId);
    }

    @Override
    public List<UserAdminVo> roleUserChecked(List<UserAdmin> users, Integer roleId) {
        // 当前角色下的用户
        Map<Integer, Integer> roleUserMap = new HashMap<>(8);
        List<RoleUserAdmin> roleUsers = baseMapper.findRoleId(roleId);
        roleUsers.forEach(item -> roleUserMap.put(item.getUserId(), item.getRoleId()));
        //赋值选中状态
        List<UserAdminVo> userListVo = new ArrayList<>();
        for (UserAdmin user : users) {
            UserAdminVo userAdminVo = user.convert(UserAdminVo.class);
            if (roleUserMap.containsKey(user.getId())) {
                userAdminVo.setIsChecked(true);
            }
            userListVo.add(userAdminVo);
        }
        return userListVo;
    }

    /**
     * TODO   计算角色用户并添加和删除
     *
     * @param roleId
     * @param userIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 18:05
     */
    @Override
    public void updRoleUser(Integer roleId, Integer[] userIds) {
        //后台当前角色用户--判断添加
        Map<Integer, Integer> roleUserMap = new HashMap<>();
        List<RoleUserAdmin> roleUsers = baseMapper.findRoleId(roleId);
        baseMapper.findRoleId(roleId).forEach(item -> roleUserMap.put(item.getUserId(), item.getRoleId()));

        //前台传入角色用户--判断删除
        Map<Integer, Integer> roleUserIdsMap = new HashMap<>();

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
        List<Integer> roleUserIds = new ArrayList<>();
        for (RoleUserAdmin roleUser : roleUsers) {
            if (!roleUserIdsMap.containsKey(roleUser.getUserId())) {
                roleUserIds.add(roleUser.getId());
            }
        }
        if (addRoleUser.size() > 0) {
            this.saveBatch(addRoleUser);
        }
        if (roleUserIds.size() > 0) {
            this.removeByIds(roleUserIds);
        }
    }
}
