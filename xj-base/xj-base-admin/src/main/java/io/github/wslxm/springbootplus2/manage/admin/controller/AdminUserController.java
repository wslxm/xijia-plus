package io.github.wslxm.springbootplus2.manage.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.common.annotation.XjSecret;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminUserDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminUserQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/user")
@Api(value = "AdminUserController", tags = "base--用户管理")
public class AdminUserController extends BaseController<AdminUserService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<AdminUserVO>> list(@ModelAttribute @Validated AdminUserQuery query) {
        return R.successFind(baseService.list(query));
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public R<AdminUserVO> findId(@PathVariable String id) {
        return R.success(baseService.findId(id));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated AdminUserDTO dto) {
        return R.successInsert(baseService.insert(dto));
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


    @GetMapping(value = "/findUser")
    @ApiOperation("查询当前登录人的个人信息")
    public R<AdminUserVO> findUser() {
        return R.successFind(BeanDtoVoUtil.convert(baseService.findId(JwtUtil.getJwtUser(request).getUserId()), AdminUserVO.class));
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
            @ApiImplicitParam(name = "searchName", value = "姓名or用户名", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "terminal", value = "终端(不传查所有)", required = false, paramType = "query", example = "")
    })
    public R<List<AdminUserVO>> listKeyData(@RequestParam(required = false) String searchName, @RequestParam(required = false) String terminal) {
        return R.success(baseService.listKeyData(searchName, terminal));
    }


    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号/手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "terminal", value = "终端", required = false, paramType = "query"),
    })
    public R<Boolean> login(@RequestParam String username, @XjSecret @RequestParam String password, Integer terminal) {
        return R.success(baseService.login(username, password, terminal));
    }


    @PutMapping(value = "/updUser")
    @ApiOperation(value = "修改当前登录人的信息")
    public R<Boolean> updUser(@RequestBody AdminUserDTO dto) {
        return R.successUpdate(baseService.upd(JwtUtil.getJwtUser(request).getUserId(), dto));
    }


    @PutMapping(value = "/updByPassword")
    @ApiOperation(value = "修改当前登录人的密码", notes = "判断原密码是否正确,不正确返回错误信息msg ,正确直接修改,密码进行MD5加密 --> val(前端输入密码值)+盐(后端规则指定)=最终密码）")
    public R<Boolean> updByPassword(@RequestParam String oldPassword, @RequestParam String password) {
        return R.successUpdate(baseService.updByPassword(oldPassword, password));
    }


    @PutMapping(value = "/{id}/resetPassword")
    @ApiOperation(value = "重置任意用户密码")
    public R<Boolean> updResetPassword(@PathVariable String id, @RequestParam String password) {
        return R.successUpdate(baseService.updResetPassword(id, password));
    }


    //   @PostMapping(value = "/bindWeChatMq")
    //   @ApiOperation(value = "微信公众号openId绑定")
    //   public R<Boolean> bindWeChatMq(@RequestParam String username, @RequestParam String password, @RequestParam String openId) {
    //       return R.success(baseService.bindWeChatMq(username, password, openId));
    //   }
}
