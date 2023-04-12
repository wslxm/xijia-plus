package io.github.wslxm.springbootplus2.manage.sys.service;


import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;

/**
 * 用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface LoginService {


    /**
     * 登录
     *
     * @param dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    boolean login(LoginDTO dto);

}