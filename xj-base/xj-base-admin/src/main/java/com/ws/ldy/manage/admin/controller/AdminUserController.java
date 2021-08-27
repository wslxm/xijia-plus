package com.ws.ldy.manage.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ws.ldy.core.auth.util.JwtUtil;
import com.ws.ldy.core.auth.util.MD5Util;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.admin.model.dto.AdminUserDTO;
import com.ws.ldy.manage.admin.model.entity.AdminUser;
import com.ws.ldy.manage.admin.model.query.AdminUserQuery;
import com.ws.ldy.manage.admin.model.vo.AdminUserVO;
import com.ws.ldy.manage.admin.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/user")
@Api(value = "AdminUserController", tags = "base--用户管理")
public class AdminUserController extends BaseController<AdminUserService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<AdminUserVO>> list(@ModelAttribute AdminUserQuery query) {
        return R.success(baseService.list(query));
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public R<AdminUserVO> findId(@PathVariable String id) {
        return R.success(baseService.findId(id));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<Boolean> insert(@RequestBody AdminUserDTO dto) {
        return R.success(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation("ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody AdminUserDTO dto) {
        dto.setPassword(null);
        return R.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }

    @PutMapping(value = "/{id}/resetPassword")
    @ApiOperation(value = "重置任意用户密码")
    public R<Boolean> updResetPassword(@PathVariable String id, @RequestParam String password) {
        return R.successUpdate(baseService.update(new LambdaUpdateWrapper<AdminUser>()
                .set(AdminUser::getPassword, MD5Util.encode(password))
                .eq(AdminUser::getId, id)));
    }


    @GetMapping(value = "/findByRoleId")
    @ApiOperation(value = "获取指定角色的用户列表", notes = "查询所有用户")
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public R<List<AdminUserVO>> findByRoleId(@RequestParam String roleId) {
        return R.success(BeanDtoVoUtil.listVo(baseService.findByRoleId(roleId), AdminUserVO.class));
    }


    @GetMapping(value = "/list/keyData")
    @ApiOperation(value = "查询所有-只返回关键数据(姓名/昵称/电话/id)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName", value = "姓名or用户名", required = true, paramType = "query", example = ""),
            @ApiImplicitParam(name = "terminal", value = "终端(不传查所有)", required = false, paramType = "query", example = "")
    })
    public R<List<AdminUserVO>> listKeyData(
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String terminal) {
        return R.success(baseService.listKeyData(searchName, terminal));
    }

    //==============================================================================================
    //========================================== 当前用户操作 ========================================
    //==============================================================================================

    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号/手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
    })
    public R<Boolean> login(@RequestParam String username, @RequestParam String password) {
        baseService.login(username, password);
        return R.success(true);
    }


    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation("查询当前登录人的个人信息")
    public R<AdminUserVO> findUser() {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(JwtUtil.getJwtUser(request).getUserId()), AdminUserVO.class));
    }


    @PutMapping(value = "/updByPassword")
    @ApiOperation(value = "修改当前登录人的密码", notes = "判断原密码是否正确,不正确返回错误信息msg ,正确直接修改,密码进行MD5加密 --> val(前端输入密码值)+盐(后端规则指定)=最终密码）")
    public R<Boolean> updByPassword(@RequestParam String oldPassword, @RequestParam String password) {
        AdminUser adminUser = baseService.getById(JwtUtil.getJwtUser(request).getUserId());
        if (adminUser.getPassword().equals(MD5Util.encode(oldPassword))) {
            adminUser.setPassword(MD5Util.encode(password));
            return R.successUpdate(baseService.updateById(adminUser));
        } else {
            return R.error(RType.USER_PASSWORD_ERROR);
        }
    }
    //   @PostMapping(value = "/bindWeChatMq")
    //   @ApiOperation(value = "微信公众号openId绑定")
    //   public R<Boolean> bindWeChatMq(@RequestParam String username, @RequestParam String password, @RequestParam String openId) {
    //       return R.success(baseService.bindWeChatMq(username, password, openId));
    //   }
}
