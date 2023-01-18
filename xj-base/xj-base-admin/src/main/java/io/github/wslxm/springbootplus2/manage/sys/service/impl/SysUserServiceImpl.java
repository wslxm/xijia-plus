package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.auth.util.Md5Util;
import io.github.wslxm.springbootplus2.common.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.tree.TreeUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.SysUserMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.SysUserDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dep;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;
import io.github.wslxm.springbootplus2.manage.sys.model.query.SysUserQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import io.github.wslxm.springbootplus2.manage.sys.service.DepService;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleUserService;
import io.github.wslxm.springbootplus2.manage.sys.service.SysUserService;
import io.github.wslxm.springbootplus2.starter.redis.lock.XjDistributedLock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private DepService depService;

    @Override
    public IPage<SysUserVO> findPage(SysUserQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(false);
        }
        // 是否只查询当前登录人创建的用户
        String createUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        IPage<SysUserVO> page = new Page<>(query.getCurrent(), query.getSize());
        return page.setRecords(baseMapper.list(page, query, createUserId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @XjDistributedLock(lockName = "'xj-sys-user_'+ #dto.username +'_' + #dto.phone", waitTime = 5L)
    public String insert(SysUserDTO dto) {
        ValidUtil.isStrLen(dto.getPassword(), 1, 20, "密码必须大于1且小于20位");
        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), null);
        this.verifyRepeatPhone(dto.getPhone(), null);
        //
        SysUser adminUser = dto.convert(SysUser.class);
        adminUser.setId(IdUtil.getSnowflakeNextIdStr());
        adminUser.setPassword(Md5Util.encode(adminUser.getPassword(), adminUser.getId()));
        adminUser.setRegTime(LocalDateTime.now());
        if (dto.getDisable() == null) {
            // 如果未设置状态,默认启用状态
            adminUser.setDisable(Base.Disable.V0.getValue());
        }
        this.save(adminUser);
        if (dto.getRoleIds() != null) {
            // 用户角色分配
            roleUserService.updUserRole(adminUser.getId(), dto.getRoleIds());
        }
        return adminUser.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @XjDistributedLock(lockName = "'xj-sys-user_'+ #dto.username +'_' + #dto.phone", waitTime = 5L)
    public Boolean upd(String id, SysUserDTO dto) {
        SysUser adminUser = this.getOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUsername, SysUser::getPhone)
                .eq(SysUser::getId, id));
        ValidUtil.isTrue(adminUser == null, "没有找到数据");

        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), adminUser.getUsername());
        this.verifyRepeatPhone(dto.getPhone(), adminUser.getPhone());
        //
        SysUser entity = dto.convert(SysUser.class);
        entity.setId(id);
        this.updateById(entity);
        if (dto.getRoleIds() != null && dto.getRoleIds().size() > 0) {
            // 用户角色分配
            roleUserService.updUserRole(id, dto.getRoleIds());
        }
        return true;
    }

    @Override
    public Boolean del(String userId) {
        // 删除用户角色
        roleUserService.delByUserId(userId);
        return this.removeById(userId);
    }

    @Override
    public SysUserVO findId(String id) {
        // id查询数据
        SysUserQuery query = new SysUserQuery();
        query.setId(id);
        IPage<SysUserVO> list = this.findPage(query);
        if (list.getRecords().size() == 0) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), ResultType.PARAM_ERROR.getMsg() + ":id");
        }
        SysUserVO userVO = list.getRecords().get(0);

        // 角色id组装便于角色回显
        userVO.setRoleIds(userVO.getRoles() == null ? null : userVO.getRoles().stream().map(RoleVO::getId).collect(Collectors.toList()));
        // 公司/部门信息
        if (StringUtils.isNotBlank(userVO.getDepIds())) {
            String[] depIds = userVO.getDepIds().split(",");
            List<Dep> deps = depService.list(new LambdaQueryWrapper<Dep>().select(Dep::getId, Dep::getPid, Dep::getName));
            List<DepVO> depVOS = BeanDtoVoUtil.listVo(deps, DepVO.class);
            userVO.setDep(TreeUtil.fatherTree(depVOS, depIds[depIds.length - 1]));
            userVO.setDepNames(TreeUtil.fatherNames(depVOS, depIds[depIds.length - 1]));
        }
        return userVO;
    }

    @Override
    public List<SysUser> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }


    @Override
    public List<SysUserVO> listKeyData(String searchName) {
        List<SysUser> list = this.list(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUsername, SysUser::getFullName, SysUser::getPhone, SysUser::getId)
                .orderByDesc(SysUser::getCreateTime)
                .and(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(searchName),
                        i -> i.like(SysUser::getFullName, searchName)
                                .or().like(SysUser::getUsername, searchName)
                )
        );
        return BeanDtoVoUtil.listVo(list, SysUserVO.class);
    }


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


    @Override
    public Boolean updResetPassword(String id, String password) {
        ValidUtil.isStrLen(password, 1, 20, "密码必须大于1且小于20位");
        return this.update(new SysUser(), new LambdaUpdateWrapper<SysUser>()
                .set(SysUser::getPassword, Md5Util.encode(password, id))
                .eq(SysUser::getId, id));
    }


    @Override
    public Boolean updByPassword(String oldPassword, String password) {
        ValidUtil.isStrLen(password, 1, 20, "密码必须大于1且小于20位");
        SysUser adminUser = this.getById(JwtUtil.getJwtUser(request).getUserId());
        if (!adminUser.getPassword().equals(Md5Util.encode(oldPassword, adminUser.getId()))) {
            throw new ErrorException(ResultType.USER_PASSWORD_ERROR);
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


    /**
     * 验证账号是否重复
     *
     * @param username 新手机号
     * @return void
     * @author wangsong
     * @date 2021/9/30 0030 14:12
     * @version 1.0.1
     */
    private void verifyRepeatUsername(String username, String oldUserName) {
        if (StringUtils.isNotBlank(username)) {
            // 判重账号
            if (!username.equals(oldUserName)) {
                if (this.count(new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, username)
                        .eq(SysUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(ResultType.USER_ACCOUNT_IS_DUPLICATE);
                }
            }
        }
    }


    /**
     * 验证手机号是否重复
     *
     * @param phone    新手机号
     * @param oldPhone 原手机号(注册可不填)
     * @return void
     * @author wangsong
     * @date 2021/9/30 0030 14:11
     * @version 1.0.1
     */
    private void verifyRepeatPhone(String phone, String oldPhone) {
        if (StringUtils.isNotBlank(phone)) {
            // 判重电话
            if (!phone.equals(oldPhone)) {
                if (this.count(new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getPhone, phone)
                        .eq(SysUser::getDeleted, Base.Deleted.V0.getValue())
                ) > 0) {
                    throw new ErrorException(ResultType.USER_PHONE_IS_DUPLICATE);
                }
            }
        }
    }
}
