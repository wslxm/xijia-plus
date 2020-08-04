package com.ws.ldy.modules.admin.service.impl;

import com.ws.ldy.modules.admin.mapper.AdminRoleUserMapper;
import com.ws.ldy.modules.admin.model.entity.AdminRoleUser;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.model.vo.AdminUserVO;
import com.ws.ldy.modules.admin.service.AdminRoleUserService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class AdminRoleUserServiceImpl extends BaseIServiceImpl<AdminRoleUserMapper, AdminRoleUser> implements AdminRoleUserService {

    @Override
    public List<AdminRoleUser> findRoleId(String roleId) {
        return baseMapper.findRoleId(roleId);
    }

    @Override
    public List<AdminUserVO> roleUserChecked(List<AdminUser> users, String roleId) {
        // 当前角色下的用户
        Map<String, String> roleUserMap = new HashMap<>(8);
        List<AdminRoleUser> roleUsers = baseMapper.findRoleId(roleId);
        roleUsers.forEach(item -> roleUserMap.put(item.getUserId(), item.getRoleId()));
        //赋值选中状态
        List<AdminUserVO> userListVo = new ArrayList<>();
        for (AdminUser user : users) {
            AdminUserVO adminUserVO = user.convert(AdminUserVO.class);
            if (roleUserMap.containsKey(user.getId())) {
                adminUserVO.setIsChecked(true);
            }
            userListVo.add(adminUserVO);
        }
        return userListVo;
    }

    /**
     *    计算角色用户并添加和删除
     *
     * @param roleId
     * @param userIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 18:05
     */
    @Override
    public void updRoleUser(String roleId, String[] userIds) {
        //后台当前角色用户--判断添加
        Map<String, String> roleUserMap = new HashMap<>();
        List<AdminRoleUser> roleUsers = baseMapper.findRoleId(roleId);
        baseMapper.findRoleId(roleId).forEach(item -> roleUserMap.put(item.getUserId(), item.getRoleId()));

        //前台传入角色用户--判断删除
        Map<String, String> roleUserIdsMap = new HashMap<>();

        //计算添加，遍历传如数据，如发现后台不存在则添加
        List<AdminRoleUser> addRoleUser = new ArrayList<>();
        if (userIds != null) {
            for (String userId : userIds) {
                if (!roleUserMap.containsKey(userId)) {
                    addRoleUser.add(new AdminRoleUser(roleId, userId));
                }
                roleUserIdsMap.put(userId, roleId);
            }
        }

        //计算删除，遍历后台数据，如发现前台传入值不包含则删除
        List<String> roleUserIds = new ArrayList<>();
        for (AdminRoleUser roleUser : roleUsers) {
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
