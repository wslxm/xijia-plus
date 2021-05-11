package com.ws.ldy.modules.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.auth.util.MD5Util;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.Base;
import com.ws.ldy.modules.sys.admin.mapper.AdminUserMapper;
import com.ws.ldy.modules.sys.admin.model.dto.UserAdminDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminRoleUser;
import com.ws.ldy.modules.sys.admin.model.entity.AdminUser;
import com.ws.ldy.modules.sys.admin.model.vo.AdminUserVO;
import com.ws.ldy.modules.sys.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.sys.admin.service.AdminRoleService;
import com.ws.ldy.modules.sys.admin.service.AdminRoleUserService;
import com.ws.ldy.modules.sys.admin.service.AdminUserService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl extends BaseIServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRoleUserService adminRoleUserService;

    @Autowired
    private AdminAuthorityService adminAuthorityService;


    @Override
    public AdminUserVO findId(String id) {
        AdminUser user = this.getById(id);
        AdminUserVO userVO = user.convert(AdminUserVO.class);
        List<AdminRoleUser> roleUsers = adminRoleUserService.list(new LambdaQueryWrapper<AdminRoleUser>()
                .select(AdminRoleUser::getRoleId)
                .eq(AdminRoleUser::getUserId, id)
        );
        //保存角色id
        userVO.setRoles(roleUsers == null ? null : roleUsers.stream().map(AdminRoleUser::getRoleId).collect(Collectors.toList()));
        return userVO;
    }

    @Override
    public List<AdminUser> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }

    @Override
    @Transactional
    public Boolean insert(@RequestBody UserAdminDTO userAdminDto) {
        // 判重账号
        if (this.count(new LambdaUpdateWrapper<AdminUser>()
                .eq(AdminUser::getUsername, userAdminDto.getUsername())
                .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
        ) > 0) {
            throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
        }
        // 判重电话
        if (this.count(new LambdaUpdateWrapper<AdminUser>()
                .eq(AdminUser::getPhone, userAdminDto.getPhone())
                .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
        ) > 0) {
            throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
        }
        //
        AdminUser adminUser = userAdminDto.convert(AdminUser.class);
        adminUser.setPassword(MD5Util.encode(adminUser.getPassword()));
        adminUser.setDisable(0);  //默认启用状态
        adminUser.setRegTime(LocalDateTime.now());
        this.save(adminUser);
        if (userAdminDto.getRoles() != null) {
            //分配角色
            adminRoleService.updUserRole(adminUser.getId(), userAdminDto.getRoles());
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean upd(@RequestBody UserAdminDTO userAdminDto) {
        AdminUser adminUser = this.getById(userAdminDto.getId());
        //判重账号
        if (!adminUser.getUsername().equals(userAdminDto.getUsername())) {
            if (this.count(new LambdaUpdateWrapper<AdminUser>()
                    .eq(AdminUser::getUsername, userAdminDto.getUsername())
                    .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
            ) > 0) {
                throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
            }
        }
        //判重电话
        if (!adminUser.getPhone().equals(userAdminDto.getPhone())) {
            if (this.count(new LambdaUpdateWrapper<AdminUser>()
                    .eq(AdminUser::getPhone, userAdminDto.getPhone())
                    .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
            ) > 0) {
                throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
            }
        }
        this.updateById(userAdminDto.convert(AdminUser.class));
        //分配角色
        if (userAdminDto.getRoles() != null) {
            adminRoleService.updUserRole(userAdminDto.getId(), userAdminDto.getRoles());
        }
        return true;
    }


    //username = 手机号或者账号(手机号或账号不能重复)
    @Override
    public Boolean login(@RequestParam String username, @RequestParam String password) {
        // 1、判断账号
        AdminUser user = this.getOne(new LambdaQueryWrapper<AdminUser>()
                .and(i -> i.eq(AdminUser::getUsername, username)
                        .or().eq(AdminUser::getPhone, username))
        );
        if (user == null) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        // 2、判断密码
        if (!user.getPassword().equals(MD5Util.encode(password))) {
            throw new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD);
        }
        // 3、判断禁用
        if (!user.getDisable().equals(Base.Disable.V0.getValue())) {
            throw new ErrorException(RType.LOGIN_IS_NO_DISABLE);
        }
        // 登录成功
        // 4、获取权限列表,保存权限-未禁用,管理端(登录+认证的)
        List<String> authList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(user.getId());
        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setUsername(user.getUsername());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setType(JwtUtil.userType[0]);
        jwtUser.setHead(user.getHead());
        jwtUser.setPhone(user.getPhone());
        // 设置token有效期(分)
        jwtUser.setRefreshTime(60);
        jwtUser.setExpiration(5);
        // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
        jwtUser.setAuthList(authList);
        JwtUtil.createToken(jwtUser, response);
        // 6、刷新登录时间
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(user.getId());
        updAdminUser.setEndTime(LocalDateTime.now());
        this.updateById(updAdminUser);
        return true;
    }

    /**
     * 绑定微信公众号-openId
     * @param username
     * @param password
     * @param openId
     * @return
     */
    @Override
    public Boolean bindWeChatMq(String username, String password, String openId) {
        // 1、判断账号
        AdminUser user = this.getOne(new LambdaQueryWrapper<AdminUser>()
                .and(i -> i.eq(AdminUser::getUsername, username)
                        .or().eq(AdminUser::getPhone, username))
        );
        if (user == null) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        // 2、判断密码
        if (!user.getPassword().equals(MD5Util.encode(password))) {
            throw new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD);
        }
        //绑定
        return this.update(new LambdaUpdateWrapper<AdminUser>().eq(AdminUser::getWxOpenId, openId));
    }


    @Override
    public Boolean del(String userId) {
        adminRoleUserService.remove(new LambdaUpdateWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId,userId));
        return this.removeById(userId);
    }
}
