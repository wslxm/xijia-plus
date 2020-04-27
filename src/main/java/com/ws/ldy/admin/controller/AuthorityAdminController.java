package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.admin.model.dto.AuthorityAdminDto;
import com.ws.ldy.admin.model.entity.AuthorityAdmin;
import com.ws.ldy.admin.model.vo.AuthorityAdminVo;
import com.ws.ldy.admin.service.impl.AuthorityAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * TODO  接口管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping("/authorityAdmin")
@Api(value = "AuthorityAdminController", tags = "URL权限管理")
public class AuthorityAdminController extends BaseController {

    @Resource
    private AuthorityAdminServiceImpl authorityAdminService;
    @Resource
    private RoleAuthAdminServiceImpl roleAuthAdminService;

    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "数据id", required = false, paramType = "query"),
    })
    public Result<IPage<AuthorityAdminVo>> findPage(Integer id) {
        Page<AuthorityAdmin> authorityAdminPage = authorityAdminService.page(this.getPage(), new LambdaQueryWrapper<AuthorityAdmin>()
                .eq(id != null, AuthorityAdmin::getId, id)
        );
        IPage<AuthorityAdminVo> convert = authorityAdminPage.convert(item -> item.convert(AuthorityAdminVo.class));
        return success(convert);
    }


    @ApiOperation("添加/修改== 1 添加，2修改")
    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    public Result<Void> save(@PathVariable Integer type, @RequestBody AuthorityAdminDto authorityAdminDto) {
        if (type == 1) {
            authorityAdminService.save(authorityAdminDto.convert(AuthorityAdmin.class));
        } else {
            authorityAdminService.save(authorityAdminDto.convert(AuthorityAdmin.class));
        }
        return success();
    }


    @ApiOperation("批量删除/单删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<Void> delete(@RequestParam Integer[] ids) {
        authorityAdminService.removeByIds(Arrays.asList(ids));
        return success();
    }


    @ApiOperation("扫描权限：权限列表数据刷新")
    @RequestMapping(value = "/putAuthority", method = RequestMethod.PUT)
    public Result<Void> putAuthority() {
        authorityAdminService.putClass();
        return success();
    }


    @ApiOperation("查询所有,跟据角色赋予选中状态")
    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public Result<List<AuthorityAdminVo>> findList(Integer roleId) {
        List<AuthorityAdminVo> roleAuthorityChecked = roleAuthAdminService.findRoleAuthorityChecked(roleId);
        return success(roleAuthorityChecked);
    }
}
