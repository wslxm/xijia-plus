package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.AdminAuthorityMapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleAuthMapper;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminRoleAuth;
import com.ws.ldy.modules.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.admin.service.AdminRoleAuthService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
@Service
public class AdminRoleAuthServiceImpl extends BaseIServiceImpl<AdminRoleAuthMapper, AdminRoleAuth> implements AdminRoleAuthService {

    @Autowired
    private AdminRoleAuthMapper adminRoleAuthMapper;
    @Autowired
    private AdminAuthorityMapper adminAuthorityMapper;


    @Override
    public List<AdminRoleAuth> findUserIdRoleAuthority(String userId) {
        return adminRoleAuthMapper.findUserIdRoleAuthority(userId);
    }


    @Override
    public List<AdminAuthorityVO> findRoleAuthorityChecked(String roleId) {
        // 获取当前角色url权限列表
        Map<String, String> map = new HashMap<>(8);
        if (roleId != null) {
            adminRoleAuthMapper.findRoleId(roleId).forEach(item -> map.put(item.getAuthId(), "0"));
        }
        // 获取所有url
        List<AdminAuthority> authorityList = adminAuthorityMapper.selectList(null);
        // 返回数据处理
        List<AdminAuthorityVO> adminAuthorityVOList = new ArrayList<>();
        if (authorityList == null && authorityList.size() <= 0) {
            return null;
        }
        authorityList.forEach(item -> {
            if (map.containsKey(item.getId())) {
                AdminAuthorityVO itemVo = item.convert(AdminAuthorityVO.class);
                itemVo.setIsChecked(true);
                adminAuthorityVOList.add(itemVo);

            } else {
                adminAuthorityVOList.add(item.convert(AdminAuthorityVO.class));
            }
        });
        return adminAuthorityVOList;
    }


    @Override
    public void roleUrlAuth(String roleId, String[] userIds) {
        //删除原数据
        this.remove(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, roleId));
        //添加新数据
        List<AdminRoleAuth> roleAuthList = new ArrayList<>();
        for (int i = 0; i < userIds.length; i++) {
            roleAuthList.add(new AdminRoleAuth(userIds[i], roleId));
        }
        if (roleAuthList.size() > 0) {
            this.saveBatch(roleAuthList, 1024);
        }
    }
}

