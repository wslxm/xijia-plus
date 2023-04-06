package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.auth.util.Md5Util;
import io.github.wslxm.springbootplus2.common.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.SysUserMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import io.github.wslxm.springbootplus2.manage.sys.service.DepService;
import io.github.wslxm.springbootplus2.manage.sys.service.LoginService;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangsong
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements LoginService {

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private DepService depService;

    @Override
    public Boolean login(LoginDTO dto) {
        ValidUtil.isStrLen(dto.getPassword(), 1, 20, "密码必须大于1且小于20位");
        SysUser user = loginUsernameOrPhone(dto.getUsername(), dto.getPassword());
        // 登录成功
        // 获取token 默认设置的有效期
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
        return this.updateById(updUser);
    }


    /**
     * 手机号和电话号登录验证，失败自动进入全局异常,成功返回用户信息
     *
     * @param username 账号
     * @param password 密码
     * @return boolean
     * @author wangsong
     * @date 2021/9/30 0030 14:18
     * @version 1.0.1
     */
    private SysUser loginUsernameOrPhone(String username, String password) {
        // 1、判断账号
        List<SysUser> users = this.list(new LambdaQueryWrapper<SysUser>()
                .and(i -> i.eq(SysUser::getUsername, username)
                        .or().eq(SysUser::getPhone, username))
        );

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
