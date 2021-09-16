package com.ws.ldy.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.auth.entity.JwtUser;
import com.ws.ldy.core.auth.util.JwtUtil;
import com.ws.ldy.core.auth.util.MD5Util;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.config.error.ErrorException;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.utils.validated.RegUtil;
import com.ws.ldy.manage.admin.mapper.AdminUserMapper;
import com.ws.ldy.manage.admin.model.dto.AdminUserDTO;
import com.ws.ldy.manage.admin.model.entity.AdminRoleUser;
import com.ws.ldy.manage.admin.model.entity.AdminUser;
import com.ws.ldy.manage.admin.model.query.AdminUserQuery;
import com.ws.ldy.manage.admin.model.vo.AdminRoleVO;
import com.ws.ldy.manage.admin.model.vo.AdminUserVO;
import com.ws.ldy.manage.admin.service.AdminAuthorityService;
import com.ws.ldy.manage.admin.service.AdminRoleService;
import com.ws.ldy.manage.admin.service.AdminRoleUserService;
import com.ws.ldy.manage.admin.service.AdminUserService;
import org.apache.commons.lang3.StringUtils;
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
    public IPage<AdminUserVO> list(AdminUserQuery query) {
        // 是否只查询当前登录人创建的用户
        String createUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        if (query.getCurrent() <= 0) {
            // list
            IPage<AdminUserVO> page = new Page<>();
            return page.setRecords(baseMapper.list(null, query, createUserId));
        } else {
            // page
            IPage<AdminUserVO> page = new Page<>(query.getCurrent(), query.getSize());
            return page.setRecords(baseMapper.list(page, query, createUserId));
        }
    }

    @Override
    @Transactional
    public String insert( AdminUserDTO dto) {
        // 判重账号
        if (this.count(new LambdaUpdateWrapper<AdminUser>()
                .eq(AdminUser::getUsername, dto.getUsername())
                .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
        ) > 0) {
            throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
        }
        // 判重电话
        if (StringUtils.isNotBlank(dto.getPhone())) {
            if (this.count(new LambdaUpdateWrapper<AdminUser>()
                    .eq(AdminUser::getPhone, dto.getPhone())
                    .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
            ) > 0) {
                throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
            }
        }
        AdminUser adminUser = dto.convert(AdminUser.class);
        adminUser.setPassword(MD5Util.encode(adminUser.getPassword()));
        adminUser.setRegTime(LocalDateTime.now());
        if (dto.getDisable() == null) {
            // 如果未设置状态,默认启用状态
            adminUser.setDisable(Base.Disable.V0.getValue());
        }
        // 判断当前是否登录(登录了添加创建人)
        R<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request, response);
        Boolean isLogin = jwtUserR.getCode().equals(RType.SYS_SUCCESS.getValue()) ? true : false;
        if (isLogin) {
            String userId = jwtUserR.getData().getUserId();
            adminUser.setCreateUser(userId);
        }
        this.save(adminUser);
        if (dto.getRoleIds() != null) {
            //分配角色
            adminRoleService.updUserRole(adminUser.getId(), dto.getRoleIds());
        }
        return adminUser.getId();
    }

    @Override
    @Transactional
    public Boolean upd(String id, AdminUserDTO dto) {
        AdminUser adminUser = this.getOne(new LambdaQueryWrapper<AdminUser>()
                .select(AdminUser::getUsername, AdminUser::getPhone)
                .eq(AdminUser::getId, id));
        if (StringUtils.isNotBlank(dto.getUsername())) {
            // 判重账号
            if (!dto.getUsername().equals(adminUser.getUsername())) {
                if (this.count(new LambdaUpdateWrapper<AdminUser>()
                        .eq(AdminUser::getUsername, dto.getUsername())
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
                }
            }
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            // 判重电话
            if (!dto.getPhone().equals(adminUser.getPhone())) {
                if (this.count(new LambdaUpdateWrapper<AdminUser>()
                        .eq(AdminUser::getPhone, dto.getPhone())
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
                }
            }
        }
        AdminUser entity = dto.convert(AdminUser.class);
        entity.setId(id);
        this.updateById(entity);

        // 角色信息重分配
        if (dto.getRoleIds() != null && dto.getRoleIds().size() > 0) {
            adminRoleService.updUserRole(id, dto.getRoleIds());
        }
        return true;
    }

    @Override
    public Boolean del(String userId) {
        adminRoleUserService.remove(new LambdaUpdateWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
        return this.removeById(userId);
    }

    @Override
    public AdminUserVO findId(String id) {
        AdminUserQuery query = new AdminUserQuery();
        query.setId(id);
        IPage<AdminUserVO> list = this.list(query);
        if (list.getRecords().size() == 0) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), RType.PARAM_ERROR.getMsg() + ":id");
        }
        AdminUserVO userVO = list.getRecords().get(0);
        // 保存角色id
        userVO.setRoleIds(userVO.getRoles() == null ? null : userVO.getRoles().stream().map(AdminRoleVO::getId).collect(Collectors.toList()));
        return userVO;
    }

    @Override
    public List<AdminUser> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }


    @Override
    public List<AdminUserVO> listKeyData(String searchName, String terminal) {
        List<AdminUser> list = this.list(new LambdaQueryWrapper<AdminUser>()
                .select(AdminUser::getUsername, AdminUser::getFullName, AdminUser::getPhone, AdminUser::getId)
                .eq(terminal != null, AdminUser::getTerminal, terminal)
                .orderByDesc(AdminUser::getCreateTime)
                .and(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(searchName),
                        i -> i.like(AdminUser::getFullName, searchName)
                                .or().like(AdminUser::getUsername, searchName)
                )
        );
        return BeanDtoVoUtil.listVo(list, AdminUserVO.class);
    }


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
        List<String> authList = adminAuthorityService.findByUserIdAuthority(user.getId());
        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setTerminal(user.getTerminal());
        jwtUser.setType(JwtUtil.userType[0]);
        // 设置token有效期(分)
        jwtUser.setExpiration(60);
        // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
        jwtUser.setAuthList(authList);
        JwtUtil.createToken(jwtUser, response);
        // 6、刷新最后登录时间
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(user.getId());
        updAdminUser.setEndTime(LocalDateTime.now());
        return this.updateById(updAdminUser);
    }


    @Override
    public Boolean updResetPassword(String id, String password) {
        return this.update(new LambdaUpdateWrapper<AdminUser>()
                .set(AdminUser::getPassword, MD5Util.encode(password))
                .eq(AdminUser::getId, id));
    }


    @Override
    public Boolean updByPassword(String oldPassword, String password) {
        AdminUser adminUser = this.getById(JwtUtil.getJwtUser(request).getUserId());
        if (!adminUser.getPassword().equals(MD5Util.encode(oldPassword))) {
            throw new ErrorException(RType.USER_PASSWORD_ERROR);
        }
        adminUser.setPassword(MD5Util.encode(password));
        return this.updateById(adminUser);
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
}
