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
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.admin.mapper.AdminUserMapper;
import com.ws.ldy.manage.admin.model.dto.AdminUserDTO;
import com.ws.ldy.manage.admin.model.entity.AdminRoleUser;
import com.ws.ldy.manage.admin.model.entity.AdminUser;
import com.ws.ldy.manage.admin.model.query.AdminUserQuery;
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
        LambdaQueryWrapper<AdminUser> queryWrapper = new LambdaQueryWrapper<AdminUser>()
                .orderByDesc(AdminUser::getCreateTime)
                .eq(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(query.getId()), AdminUser::getId, query.getId())
                .eq(query.getPosition() != null, AdminUser::getPosition, query.getPosition())
                .eq(query.getDisable() != null, AdminUser::getDisable, query.getDisable())
                .eq(query.getTerminal() != null, AdminUser::getTerminal, query.getTerminal())
                .like(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(query.getFullName()), AdminUser::getFullName, query.getFullName())
                .like(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(query.getUsername()), AdminUser::getUsername, query.getUsername())
                .like(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(query.getPhone()), AdminUser::getPhone, query.getPhone());
        if (query.getCurrent() <= 0) {
            // list
            IPage<AdminUserVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminUserVO.class));
        } else {
            // page
            return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), AdminUserVO.class);
        }
    }

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
    public Boolean insert(@RequestBody AdminUserDTO dto) {
        // 判重账号
        if (this.count(new LambdaUpdateWrapper<AdminUser>()
                .eq(AdminUser::getUsername, dto.getUsername())
                .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
        ) > 0) {
            throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
        }
        // 判重电话
        if (this.count(new LambdaUpdateWrapper<AdminUser>()
                .eq(AdminUser::getPhone, dto.getPhone())
                .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
        ) > 0) {
            throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
        }
        //
        AdminUser adminUser = dto.convert(AdminUser.class);
        adminUser.setPassword(MD5Util.encode(adminUser.getPassword()));
        adminUser.setDisable(0);  //默认启用状态
        adminUser.setRegTime(LocalDateTime.now());
        this.save(adminUser);
        if (dto.getRoles() != null) {
            //分配角色
            adminRoleService.updUserRole(adminUser.getId(), dto.getRoles());
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean upd(String id, AdminUserDTO dto) {

        //判重账号
        AdminUser adminUser = this.getOne(new LambdaQueryWrapper<AdminUser>().select(AdminUser::getUsername, AdminUser::getPhone).eq(AdminUser::getId, id));
        if (StringUtils.isNotBlank(dto.getUsername())) {
            if (!adminUser.getUsername().equals(dto.getUsername())) {
                if (this.count(new LambdaUpdateWrapper<AdminUser>()
                        .eq(AdminUser::getUsername, dto.getUsername())
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
                }
            }
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            //判重电话
            if (!adminUser.getPhone().equals(dto.getPhone())) {
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
        //分配角色
        if (dto.getRoles() != null) {
            adminRoleService.updUserRole(id, dto.getRoles());
        }
        return true;
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
        // jwtUser.setUsername(user.getUsername());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setType(JwtUtil.userType[0]);
        // jwtUser.setHead(user.getHead());
        // jwtUser.setPhone(user.getPhone());
        // 设置token有效期(分)
        // jwtUser.setRefreshTime(60);
        jwtUser.setExpiration(60);
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
        adminRoleUserService.remove(new LambdaUpdateWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
        return this.removeById(userId);
    }
}
