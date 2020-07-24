package com.ws.ldy.config.auth.springSecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.AuthorityAdminMapper;
import com.ws.ldy.modules.admin.mapper.UserAdminMapper;
import com.ws.ldy.modules.admin.model.entity.AuthorityAdmin;
import com.ws.ldy.modules.admin.model.entity.UserAdmin;
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
 * TODO 登录逻辑
 */
@Component
public class XiJiaUserDetailsServiceImpl implements UserDetailsService {

    // 用户表
    @Autowired
    private UserAdminMapper userAdminMapper;

    // 权限表
    @Autowired
    private AuthorityAdminMapper authorityAdminMapper;

    // 登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 账号查询（ 账号必须唯一）
        UserAdmin userAdmin = userAdminMapper.selectOne(new LambdaQueryWrapper<UserAdmin>().eq(UserAdmin::getUsername, username));
        if (userAdmin == null) {
            return null;
        }

        // 账号密码及禁用过期等， // 状态 false, springSecurity 将验证失败,并返回不同的异常,失败方法根据不同异常返回不同的提示信息
        SecurityUser userDetail = userAdmin.convert(SecurityUser.class);
        userDetail.setUsername(userAdmin.getUsername());
        userDetail.setPassword(userAdmin.getPassword());
        userDetail.setAccountNonExpired(true);       // 是否过期
        userDetail.setAccountNonLocked(true);        // 是否解锁
        userDetail.setCredentialsNonExpired(true);   // 凭据(密码)是否过期
        userDetail.setEnabled(userAdmin.getState() == 0);   // 是否禁用

        // 查询权限并添加权限到userDetail
        List<AuthorityAdmin> userIdRoleAuthority = authorityAdminMapper.findUserIdRoleAuthority(userAdmin.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (AuthorityAdmin authority : userIdRoleAuthority) {
            authorities.add(new SimpleGrantedAuthority(authority.getUrl()));
        }
        userDetail.setAuthorities(authorities);

        //最后登录时间刷新
        UserAdmin updUserAdmin = new UserAdmin();
        updUserAdmin.setId(userAdmin.getId());
        updUserAdmin.setEntTime(LocalDateTime.now());
        userAdminMapper.updateById(updUserAdmin);
        return userDetail;
    }
}
