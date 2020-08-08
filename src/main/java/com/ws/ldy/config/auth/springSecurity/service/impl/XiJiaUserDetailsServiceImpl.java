package com.ws.ldy.config.auth.springSecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.AdminAuthorityMapper;
import com.ws.ldy.modules.admin.mapper.AdminUserMapper;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.config.auth.springSecurity.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  登录逻辑
 */
@Component
public class XiJiaUserDetailsServiceImpl implements UserDetailsService {

    // 用户表
    @Autowired
    private AdminUserMapper adminUserMapper;

    // 权限表
    @Autowired
    private AdminAuthorityMapper adminAuthorityMapper;

    // 登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 账号查询（ 账号必须唯一）
        AdminUser adminUser = adminUserMapper.selectOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
        if (adminUser == null) {
            return null;
        }
        // 账号密码及禁用过期等， // 状态 false, springSecurity 将验证失败,并返回不同的异常,失败方法根据不同异常返回不同的提示信息
        SecurityUser userDetail = adminUser.convert(SecurityUser.class);
        userDetail.setUsername(adminUser.getUsername());
        userDetail.setPassword(adminUser.getPassword());
        userDetail.setAccountNonExpired(true);       // 是否过期
        userDetail.setAccountNonLocked(true);        // 是否解锁
        userDetail.setCredentialsNonExpired(true);   // 凭据(密码)是否过期
        userDetail.setEnabled(adminUser.getDisable() == 0);   // 是否禁用
        // 查询权限并添加权限到userDetail( 角色没有禁用的)
        List<AdminAuthority> userIdRoleAuthority = adminAuthorityMapper.findUserIdRoleAuthorityNoDisable(adminUser.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (AdminAuthority authority : userIdRoleAuthority) {
            authorities.add(new SimpleGrantedAuthority(authority.getUrl()));
        }
        userDetail.setAuthorities(authorities);

        // 最后登录时间刷新
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(adminUser.getId());
        updAdminUser.setEntTime(LocalDateTime.now());
        adminUserMapper.updateById(updAdminUser);
        return userDetail;
    }
}
