package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.manage.sys.loginStrategy.context.LoginChannelContext;
import io.github.wslxm.springbootplus2.manage.sys.loginStrategy.service.LoginStrategy;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import io.github.wslxm.springbootplus2.manage.sys.service.LoginService;
import io.github.wslxm.springbootplus2.manage.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author wangsong
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private HttpServletResponse response;

    /**
     * 登录渠道
     */
    @Autowired
    private LoginChannelContext loginChannelContext;

    @Override
    public boolean login(LoginDTO dto) {
        // 登录
        LoginStrategy channel = loginChannelContext.getChannel(dto.getChannel());
        SysUser user = channel.login(dto);

        // 登录成功
        ConfigVO xjConfig = configService.findByCode(ConfigCacheKey.MANAGE_LOGIN_EXPIRATION);
        Integer expiration = xjConfig != null ? Integer.parseInt(xjConfig.getContent()) : 60;

        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setType(JwtUtil.userType[0]);
        // 设置token有效期(分)
        jwtUser.setExpiration(expiration);
        JwtUtil.createToken(jwtUser, response);

        // 6、刷新最后登录时间
        SysUser updUser = new SysUser();
        updUser.setId(user.getId());
        updUser.setEndTime(LocalDateTime.now());
        return sysUserService.updateById(updUser);
    }
}
