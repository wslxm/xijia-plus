package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.AuthorityAdminMapper;
import com.ws.ldy.modules.admin.mapper.RoleAuthAdminMapper;
import com.ws.ldy.modules.admin.model.entity.AuthorityAdmin;
import com.ws.ldy.modules.admin.model.entity.RoleAuthAdmin;
import com.ws.ldy.modules.admin.model.vo.AuthorityAdminVo;
import com.ws.ldy.modules.admin.service.RoleAuthAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
@Service
public class RoleAuthAdminServiceImpl extends BaseIServiceImpl<RoleAuthAdminMapper, RoleAuthAdmin> implements RoleAuthAdminService {

    @Autowired
    private RoleAuthAdminMapper roleAuthAdminMapper;
    @Autowired
    private AuthorityAdminMapper authorityAdminMapper;


    @Override
    public List<RoleAuthAdmin> findUserIdRoleAuthority(Integer userId) {
        return roleAuthAdminMapper.findUserIdRoleAuthority(userId);
    }


    @Override
    public List<AuthorityAdminVo> findRoleAuthorityChecked(Integer roleId) {
        // 获取当前角色url权限列表
        Map<Integer, Integer> map = new HashMap<>(8);
        if (roleId != null) {
            roleAuthAdminMapper.findRoleId(roleId).forEach(item -> map.put(item.getAuthId(), 0));
        }
        // 获取所有url
        List<AuthorityAdmin> authorityList = authorityAdminMapper.selectList(null);
        // 返回数据处理
        List<AuthorityAdminVo> authorityAdminVoList = new ArrayList<>();
        if (authorityList == null && authorityList.size() <= 0) {
            return null;
        }
        authorityList.forEach(item -> {
            if (map.containsKey(item.getId())) {
                AuthorityAdminVo itemVo = item.convert(AuthorityAdminVo.class);
                itemVo.setIsChecked(true);
                authorityAdminVoList.add(itemVo);

            } else {
                authorityAdminVoList.add(item.convert(AuthorityAdminVo.class));
            }
        });
        return authorityAdminVoList;
    }


    @Override
    public void roleUrlAuth(Integer roleId, Integer[] userIds) {
        //删除原数据
        this.remove(new LambdaQueryWrapper<RoleAuthAdmin>().eq(RoleAuthAdmin::getRoleId, roleId));
        //添加新数据
        List<RoleAuthAdmin> roleAuthList = new ArrayList<>();
        for (int i = 0; i < userIds.length; i++) {
            roleAuthList.add(new RoleAuthAdmin(userIds[i], roleId));
        }
        if (roleAuthList.size() > 0) {
            this.saveBatch(roleAuthList, 1024);
        }
    }
}

