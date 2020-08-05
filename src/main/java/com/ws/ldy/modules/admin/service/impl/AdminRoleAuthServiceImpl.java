package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
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
import java.util.List;
import java.util.stream.Collectors;


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
        List<AdminRoleAuth> roleIds = adminRoleAuthMapper.findRoleId(roleId);
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(i -> i.getAuthId()).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有url,请求方式排序
        List<AdminAuthority> authorityList = adminAuthorityMapper.selectList(new LambdaQueryWrapper<AdminAuthority>().orderByAsc(AdminAuthority::getMethod));
        // 返回数据处理
        if (authorityList == null || authorityList.size() <= 0) {
            return null;
        }else{
            List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(authorityList, AdminAuthorityVO.class);
            adminAuthorityVOList.forEach(authVO -> {
                if (roleAuthIds.contains(authVO.getId())) {
                    authVO.setIsChecked(true);
                } else {
                    authVO.setIsChecked(false);
                }
            });
            return adminAuthorityVOList;
        }
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

