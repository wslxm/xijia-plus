package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.auth.util.Md5Util;
import io.github.wslxm.springbootplus2.common.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminUserMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminUserDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminUser;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminDepQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminUserQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDepVO;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminDepService;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleUserService;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminUserService;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class AdminUserServiceImpl extends BaseIServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminRoleUserService adminRoleUserService;

    @Autowired
    private XjAdminConfigService xjAdminConfigService;

    @Autowired
    private AdminDepService adminDepService;

    @Override
    public IPage<AdminUserVO> findPage(AdminUserQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(false);
        }
        // 是否只查询当前登录人创建的用户
        String createUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        IPage<AdminUserVO> page = new Page<>(query.getCurrent(), query.getSize());
        page = page.setRecords(baseMapper.list(page, query, createUserId));

        // 公司/部门信息
        if (page.getRecords() != null && page.getRecords().size() > 0) {
            // 获取部门ids
            List<String> depIds = new ArrayList<>();
            for (AdminUserVO userVO : page.getRecords()) {
                depIds.addAll(Arrays.asList(userVO.getDepIds().split(",")));
            }

            // 查询数据
            AdminDepQuery depQuery = new AdminDepQuery();
            depQuery.setIds(depIds);
            depQuery.setIsTree(false);
            List<AdminDepVO> deps = adminDepService.list(depQuery);

            // 处理数据
            for (AdminUserVO userVO : page.getRecords()) {
                if (userVO.getDepIds() != null) {
                    userVO.setDep(adminDepService.findNextDeps(deps, userVO.getDepIds()));
                }
            }
        }
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(AdminUserDTO dto) {
        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), null);
        this.verifyRepeatPhone(dto.getPhone(), null);
        //
        AdminUser adminUser = dto.convert(AdminUser.class);
        adminUser.setId(IdUtil.snowflakeId());
        adminUser.setPassword(Md5Util.encode(adminUser.getPassword(), adminUser.getId()));
        adminUser.setRegTime(LocalDateTime.now());
        if (dto.getDisable() == null) {
            // 如果未设置状态,默认启用状态
            adminUser.setDisable(Base.Disable.V0.getValue());
        }
        this.save(adminUser);
        if (dto.getRoleIds() != null) {
            // 用户角色分配
            adminRoleUserService.updUserRole(adminUser.getId(), dto.getRoleIds());
        }
        return adminUser.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upd(String id, AdminUserDTO dto) {
        AdminUser adminUser = this.getOne(new LambdaQueryWrapper<AdminUser>()
                .select(AdminUser::getUsername, AdminUser::getPhone)
                .eq(AdminUser::getId, id));
        ValidUtil.isTrue(adminUser == null, "没有找到数据");

        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), adminUser.getUsername());
        this.verifyRepeatPhone(dto.getPhone(), adminUser.getPhone());
        //
        AdminUser entity = dto.convert(AdminUser.class);
        entity.setId(id);
        this.updateById(entity);
        if (dto.getRoleIds() != null && dto.getRoleIds().size() > 0) {
            // 用户角色分配
            adminRoleUserService.updUserRole(id, dto.getRoleIds());
        }
        return true;
    }

    @Override
    public Boolean del(String userId) {
        // 删除用户角色
        adminRoleUserService.delByUserId(userId);
        return this.removeById(userId);
    }

    @Override
    public AdminUserVO findId(String id) {
        // id查询数据
        AdminUserQuery query = new AdminUserQuery();
        query.setId(id);
        IPage<AdminUserVO> list = this.findPage(query);
        if (list.getRecords().size() == 0) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), RType.PARAM_ERROR.getMsg() + ":id");
        }
        AdminUserVO userVO = list.getRecords().get(0);

        // 角色id组装便于角色回显
        userVO.setRoleIds(userVO.getRoles() == null ? null : userVO.getRoles().stream().map(AdminRoleVO::getId).collect(Collectors.toList()));
        // 公司/部门信息
        if (userVO.getDepIds() != null) {
            AdminDepQuery depQuery = new AdminDepQuery();
            depQuery.setIds(Arrays.asList(userVO.getDepIds().split(",")));
            depQuery.setIsTree(false);
            List<AdminDepVO> deps = adminDepService.list(depQuery);
            userVO.setDep(adminDepService.findNextDeps(deps, userVO.getDepIds()));
        }

        return userVO;
    }

    @Override
    public List<AdminUser> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }


    @Override
    public List<AdminUserVO> listKeyData(String searchName) {
        List<AdminUser> list = this.list(new LambdaQueryWrapper<AdminUser>()
                .select(AdminUser::getUsername, AdminUser::getFullName, AdminUser::getPhone, AdminUser::getId)
                .orderByDesc(AdminUser::getCreateTime)
                .and(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(searchName),
                        i -> i.like(AdminUser::getFullName, searchName)
                                .or().like(AdminUser::getUsername, searchName)
                )
        );
        return BeanDtoVoUtil.listVo(list, AdminUserVO.class);
    }


    @Override
    public Boolean login(String username, String password) {
        AdminUser user = loginUsernameOrPhone(username, password);
        // 登录成功
        // 获取token 默认设置的有效期
        XjAdminConfigVO xjAdminConfig = xjAdminConfigService.findByCode(ConfigCacheKey.MANAGE_LOGIN_EXPIRATION);
        Integer expiration = xjAdminConfig != null ? Integer.parseInt(xjAdminConfig.getContent()) : 60;

        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setType(JwtUtil.userType[0]);
        // 设置token有效期(分)
        jwtUser.setExpiration(expiration);
        JwtUtil.createToken(jwtUser, response);
        // 6、刷新最后登录时间
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(user.getId());
        updAdminUser.setEndTime(LocalDateTime.now());
        return this.updateById(updAdminUser);
    }


    @Override
    public Boolean updResetPassword(String id, String password) {
        return this.update(new AdminUser(), new LambdaUpdateWrapper<AdminUser>()
                .set(AdminUser::getPassword, Md5Util.encode(password, id))
                .eq(AdminUser::getId, id));
    }


    @Override
    public Boolean updByPassword(String oldPassword, String password) {
        AdminUser adminUser = this.getById(JwtUtil.getJwtUser(request).getUserId());
        if (!adminUser.getPassword().equals(Md5Util.encode(oldPassword, adminUser.getId()))) {
            throw new ErrorException(RType.USER_PASSWORD_ERROR);
        }
        adminUser.setPassword(Md5Util.encode(password, adminUser.getId()));
        return this.updateById(adminUser);
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
    private AdminUser loginUsernameOrPhone(String username, String password) {
        // 1、判断账号
        List<AdminUser> users = this.list(new LambdaQueryWrapper<AdminUser>()
                .and(i -> i.eq(AdminUser::getUsername, username)
                        .or().eq(AdminUser::getPhone, username))
        );

        if (users.isEmpty()) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        AdminUser user = users.get(0);
        // 2、判断密码
        if (!user.getPassword().equals(Md5Util.encode(password, user.getId()))) {
            throw new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD);
        }
        // 3、判断禁用
        if (!user.getDisable().equals(Base.Disable.V0.getValue())) {
            throw new ErrorException(RType.LOGIN_IS_NO_DISABLE);
        }
        return user;
    }


    /**
     * 验证账号是否重复
     *
     * @param username    新手机号
     * @return void
     * @author wangsong
     * @date 2021/9/30 0030 14:12
     * @version 1.0.1
     */
    private void verifyRepeatUsername(String username, String oldUserName) {
        if (StringUtils.isNotBlank(username)) {
            // 判重账号
            if (!username.equals(oldUserName)) {
                if (this.count(new LambdaQueryWrapper<AdminUser>()
                        .eq(AdminUser::getUsername, username)
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
                }
            }
        }
    }


    /**
     * 验证手机号是否重复
     *
     * @param phone       新手机号
     * @param oldPhone    原手机号(注册可不填)
     * @return void
     * @author wangsong
     * @date 2021/9/30 0030 14:11
     * @version 1.0.1
     */
    private void verifyRepeatPhone(String phone, String oldPhone) {
        if (StringUtils.isNotBlank(phone)) {
            // 判重电话
            if (!phone.equals(oldPhone)) {
                if (this.count(new LambdaQueryWrapper<AdminUser>()
                        .eq(AdminUser::getPhone, phone)
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
                }
            }
        }
    }
}
