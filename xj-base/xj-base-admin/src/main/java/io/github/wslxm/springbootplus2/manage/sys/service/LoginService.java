package io.github.wslxm.springbootplus2.manage.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;

/**
 * 用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface LoginService extends IService<SysUser> {



    /**
     * 登录
     *
     * @param dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean login(LoginDTO dto);


}