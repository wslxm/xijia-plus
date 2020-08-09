package com.ws.ldy.modules.admin.service.impl;

import com.ws.ldy.modules.admin.mapper.AdminUserMapper;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.service.AdminUserService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl extends BaseIServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {


    @Override
    public List<AdminUser> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }
}