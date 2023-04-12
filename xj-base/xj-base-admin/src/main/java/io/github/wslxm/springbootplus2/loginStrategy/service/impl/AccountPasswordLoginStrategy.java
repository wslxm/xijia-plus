package io.github.wslxm.springbootplus2.loginStrategy.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.common.auth.util.Md5Util;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.core.utils.Base64Util;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.loginStrategy.service.LoginStrategy;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.AccountPasswordLoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;
import io.github.wslxm.springbootplus2.manage.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号+密码登录
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:19
 */
@Service
@Slf4j
public class AccountPasswordLoginStrategy implements LoginStrategy {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public SysUser login(LoginDTO dto) {
        AccountPasswordLoginDTO loginDTO = JSON.parseObject(JSON.toJSONString(dto.getData()), AccountPasswordLoginDTO.class);
        ValidUtil.isNull(loginDTO, "没有获取到请求参数");
        String username = loginDTO.getUsername();
        String password = Base64Util.decrypt(loginDTO.getPassword());
        ValidUtil.isBlank(username, "账号不能为空");
        ValidUtil.isStrLen(username, 1, 20, "账号必须大于1且小于20位");
        ValidUtil.isBlank(password, "密码不能为空");
        ValidUtil.isStrLen(password, 1, 20, "密码必须大于1且小于20位");
        return this.accountLogin(username, password);
    }


    /**
     * 账号登录
     *
     * @param account 账号
     * @param password 密码
     * @return boolean
     * @author wangsong
     * @date 2021/9/30 0030 14:18
     * @version 1.0.1
     */
    private SysUser accountLogin(String account, String password) {
        // 1、判断账号
        List<SysUser> users = sysUserService.list(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, account));

        if (users.isEmpty()) {
            throw new ErrorException(ResultType.LOGIN_IS_NO_ACCOUNT);
        }
        SysUser user = users.get(0);
        // 2、判断密码
        if (!user.getPassword().equals(Md5Util.encode(password, user.getId()))) {
            throw new ErrorException(ResultType.LOGIN_ERROR_USER_PASSWORD);
        }
        // 3、判断禁用
        if (!user.getDisable().equals(Base.Disable.V0.getValue())) {
            throw new ErrorException(ResultType.LOGIN_IS_NO_DISABLE);
        }
        return user;
    }
}