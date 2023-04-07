package io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service;

import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;

/**
 * 登录策略类
 * <p>
 * </P>
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:11
 */
public interface LoginStrategy {

    /**
     * 文件上传
     */
    SysUser login(LoginDTO dto);

}
