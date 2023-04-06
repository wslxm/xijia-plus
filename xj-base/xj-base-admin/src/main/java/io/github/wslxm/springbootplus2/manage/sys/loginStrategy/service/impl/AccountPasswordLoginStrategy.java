package io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service.impl;

import io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service.LoginStrategy;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 本地文件
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:19
 */
@Service
@Slf4j
public class AccountPasswordLoginStrategy implements LoginStrategy {

    @Override
    public boolean login(LoginDTO dto) {
        return true;
    }
}
