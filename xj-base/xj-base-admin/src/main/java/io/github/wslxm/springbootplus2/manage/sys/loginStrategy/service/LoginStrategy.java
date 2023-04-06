package io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service;

import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;

/**
 * 文件策略类 （目前支持 上传/删除）
 * <p>
 * 计划支持
 * - 本地存储
 * - 阿里云oss
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
    boolean login(LoginDTO dto);



}
