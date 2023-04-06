package io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service.impl;

import io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service.LoginStrategy;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import org.springframework.stereotype.Service;


/**
 * 阿里云oss 文件上传
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:18
 */
@Service
public class PhonePasswordLoginStrategy implements LoginStrategy {


    @Override
    public boolean login(LoginDTO dto) {
        return true;
    }
}
