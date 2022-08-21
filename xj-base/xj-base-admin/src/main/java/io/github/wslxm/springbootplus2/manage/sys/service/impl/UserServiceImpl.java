package io.github.wslxm.springbootplus2.manage.sys.service.impl;

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
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.UserMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.UserDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.User;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DepQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.query.UserQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.UserVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import io.github.wslxm.springbootplus2.manage.sys.service.DepService;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleUserService;
import io.github.wslxm.springbootplus2.manage.sys.service.UserService;
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
public class UserServiceImpl extends BaseIServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleUserService adminRoleUserService;

    @Autowired
    private ConfigService xjConfigService;

    @Autowired
    private DepService adminDepService;

    @Override
    public IPage<UserVO> findPage(UserQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(false);
        }
        // 是否只查询当前登录人创建的用户
        String createUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        IPage<UserVO> page = new Page<>(query.getCurrent(), query.getSize());
        page = page.setRecords(baseMapper.list(page, query, createUserId));

        // 公司/部门信息
        if (page.getRecords() != null && page.getRecords().size() > 0) {
            // 获取部门ids
            List<String> depIds = new ArrayList<>();
            for (UserVO userVO : page.getRecords()) {
                depIds.addAll(Arrays.asList(userVO.getDepIds().split(",")));
            }

            // 查询数据
            DepQuery depQuery = new DepQuery();
            depQuery.setIds(depIds);
            depQuery.setIsTree(false);
            List<DepVO> deps = adminDepService.list(depQuery);

            // 处理数据
            for (UserVO userVO : page.getRecords()) {
                if (userVO.getDepIds() != null) {
                    userVO.setDep(adminDepService.findNextDeps(deps, userVO.getDepIds()));
                }
            }
        }
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(UserDTO dto) {
        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), null);
        this.verifyRepeatPhone(dto.getPhone(), null);
        //
        User adminUser = dto.convert(User.class);
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
    public Boolean upd(String id, UserDTO dto) {
        User adminUser = this.getOne(new LambdaQueryWrapper<User>()
                .select(User::getUsername, User::getPhone)
                .eq(User::getId, id));
        ValidUtil.isTrue(adminUser == null, "没有找到数据");

        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), adminUser.getUsername());
        this.verifyRepeatPhone(dto.getPhone(), adminUser.getPhone());
        //
        User entity = dto.convert(User.class);
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
    public UserVO findId(String id) {
        // id查询数据
        UserQuery query = new UserQuery();
        query.setId(id);
        IPage<UserVO> list = this.findPage(query);
        if (list.getRecords().size() == 0) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), RType.PARAM_ERROR.getMsg() + ":id");
        }
        UserVO userVO = list.getRecords().get(0);

        // 角色id组装便于角色回显
        userVO.setRoleIds(userVO.getRoles() == null ? null : userVO.getRoles().stream().map(RoleVO::getId).collect(Collectors.toList()));
        // 公司/部门信息
        if (userVO.getDepIds() != null) {
            DepQuery depQuery = new DepQuery();
            depQuery.setIds(Arrays.asList(userVO.getDepIds().split(",")));
            depQuery.setIsTree(false);
            List<DepVO> deps = adminDepService.list(depQuery);
            userVO.setDep(adminDepService.findNextDeps(deps, userVO.getDepIds()));
        }

        return userVO;
    }

    @Override
    public List<User> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }


    @Override
    public List<UserVO> listKeyData(String searchName) {
        List<User> list = this.list(new LambdaQueryWrapper<User>()
                .select(User::getUsername, User::getFullName, User::getPhone, User::getId)
                .orderByDesc(User::getCreateTime)
                .and(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(searchName),
                        i -> i.like(User::getFullName, searchName)
                                .or().like(User::getUsername, searchName)
                )
        );
        return BeanDtoVoUtil.listVo(list, UserVO.class);
    }


    @Override
    public Boolean login(LoginDTO dto) {
        User user = loginUsernameOrPhone(dto.getUsername(), dto.getPassword());
        // 登录成功
        // 获取token 默认设置的有效期
        ConfigVO xjConfig = xjConfigService.findByCode(ConfigCacheKey.MANAGE_LOGIN_EXPIRATION);
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
        User updUser = new User();
        updUser.setId(user.getId());
        updUser.setEndTime(LocalDateTime.now());
        return this.updateById(updUser);
    }


    @Override
    public Boolean updResetPassword(String id, String password) {
        return this.update(new User(), new LambdaUpdateWrapper<User>()
                .set(User::getPassword, Md5Util.encode(password, id))
                .eq(User::getId, id));
    }


    @Override
    public Boolean updByPassword(String oldPassword, String password) {
        User adminUser = this.getById(JwtUtil.getJwtUser(request).getUserId());
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
    private User loginUsernameOrPhone(String username, String password) {
        // 1、判断账号
        List<User> users = this.list(new LambdaQueryWrapper<User>()
                .and(i -> i.eq(User::getUsername, username)
                        .or().eq(User::getPhone, username))
        );

        if (users.isEmpty()) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        User user = users.get(0);
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
                if (this.count(new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username)
                        .eq(User::getDeleted, Base.Deleted.V0.getValue())
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
                if (this.count(new LambdaQueryWrapper<User>()
                        .eq(User::getPhone, phone)
                        .eq(User::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
                }
            }
        }
    }
}
