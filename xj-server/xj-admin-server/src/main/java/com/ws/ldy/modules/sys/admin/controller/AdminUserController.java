package com.ws.ldy.modules.sys.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.auth.util.MD5Util;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.admin.model.dto.UserAdminDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminUser;
import com.ws.ldy.modules.sys.admin.model.vo.AdminUserVO;
import com.ws.ldy.modules.sys.admin.service.AdminUserService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/adminUser")
@Api(value = "AdminUserController", tags = "base--用户管理")
public class AdminUserController extends BaseController<AdminUserService> {


    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation("个人信息")
    public R<AdminUserVO> findUser() {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(JwtUtil.getJwtUser(request).getUserId()), AdminUserVO.class));
    }


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<AdminUserVO>> findPage(
            @ApiParam(value = "数据Id", required = false) @RequestParam(required = false) Integer id,
            @ApiParam(value = "账号/手机号", required = false) @RequestParam(required = false) String username,
            @ApiParam(value = "姓名/用户名", required = false) @RequestParam(required = false) String fullName,
            @ApiParam(value = "职位:字典code 0=系统用户 1=销售", required = false) @RequestParam(required = false) Integer position,
            @ApiParam(value = "禁用(字典code )", required = false) @RequestParam(required = false) Integer disable) {
        Page<AdminUser> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminUser>()
                .orderByDesc(AdminUser::getCreateTime)
                .eq(id != null, AdminUser::getId, id)
                .eq(StringUtils.isNotBlank(fullName), AdminUser::getFullName, fullName)
                .eq(position != null, AdminUser::getPosition, position)
                .eq(disable != null, AdminUser::getDisable, disable)
                .like(StringUtils.isNotBlank(username), AdminUser::getUsername, username)
        );
        return R.success(BeanDtoVoUtil.pageVo(page, AdminUserVO.class));
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", required = false, paramType = "query"),
    })
    public R<AdminUserVO> findId(@RequestParam String id) {
        return R.success(baseService.findId(id));
    }


    @RequestMapping(value = "/findByRoleId", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定角色的用户列表", notes = "查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
    })
    public R<List<AdminUserVO>> findByRoleId(@RequestParam String roleId) {
        return R.success(BeanDtoVoUtil.listVo(baseService.findByRoleId(roleId), AdminUserVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Boolean> insert(@RequestBody UserAdminDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        return R.success(baseService.insert(dto));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public R<Boolean> upd(@RequestBody UserAdminDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.upd(dto));
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes = "")
    public R<Boolean> delByIds(@RequestParam String[] ids) {
        return R.successDelete(baseService.removeByIds(Arrays.asList(ids)));
    }


    @RequestMapping(value = "/updResetPassword", method = RequestMethod.PUT)
    @ApiOperation(value = "重置任意用户密码", notes = "")
    public R<Boolean> updResetPassword(@RequestParam String id, @RequestParam String password) {
        return R.successUpdate(baseService.update(new LambdaUpdateWrapper<AdminUser>()
                .set(AdminUser::getPassword, MD5Util.encode(password))
                .eq(AdminUser::getId, id)));
    }


    @RequestMapping(value = "/updByPassword", method = RequestMethod.PUT)
    @ApiOperation(value = "当前登录用户密码修改", notes = "判断原密码是否正确,不正确返回错误信息msg ,正确直接修改,密码进行MD5加密 --> val(前端输入密码值)+盐(后端规则指定)=最终密码）")
    public R<Boolean> updByPassword(@RequestParam String oldPassword, @RequestParam String password) {
        AdminUser adminUser = baseService.getById(JwtUtil.getJwtUser(request).getUserId());
        if (adminUser.getPassword().equals(MD5Util.encode(oldPassword))) {
            adminUser.setPassword(MD5Util.encode(password));
            return R.successUpdate(baseService.updateById(adminUser));
        } else {
            return R.error(RType.USER_PASSWORD_ERROR);
        }
    }


    /**
     * 登录
     * @return com.ws.ldy.common.result.Result<com.ws.ldy.admin.model.vo.LoginVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/3/30 0030 19:50
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号/手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
    })
    public R<Boolean> login(@RequestParam String username, @RequestParam String password) {
        baseService.login(username, password);
        return R.success(true);
    }


    @RequestMapping(value = "/bindWeChatMq", method = RequestMethod.POST)
    @ApiOperation(value = "微信公众号openId绑定", notes = "")
    public R<Boolean> bindWeChatMq(@RequestParam String username, @RequestParam String password, @RequestParam String openId) {
        return R.success(baseService.bindWeChatMq(username, password, openId));
    }
}
