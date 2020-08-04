package com.ws.ldy.config.auth.springSecurity.entity;

import com.ws.ldy.modules.admin.model.entity.AdminUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * SecurityUser 权限判断类 (用户信息子类)
 */
@Data
public class SecurityUser extends AdminUser implements UserDetails {

    private static final long serialVersionUID = 1L;


    //账户是否未过期,过期无法验证，在springSecurity 验证中自动调用
    boolean isAccountNonExpired;

    //指定用户是否解锁,锁定的用户无法进行身份验证，在springSecurity 验证中自动调用
    boolean isAccountNonLocked;

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证，在springSecurity 验证中自动调用
    boolean isCredentialsNonExpired;

    //是否可用 ,禁用的用户不能身份验证，在springSecurity 验证中自动调用
    boolean isEnabled;

    //登录用户名
    private String username;

    //登录密码
    private String password;

    //权限列表
    private Collection<? extends GrantedAuthority> authorities;
}
