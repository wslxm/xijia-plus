package com.ws.ldy.admin.controller;

import com.ws.ldy.common.result.Result;
import com.ws.ldy.admin.entity.AuthorityAdmin;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.admin.service.impl.AuthorityAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleAuthAdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping("/authorityAdmin")
@Api(tags = {"Admin-Authority"}, description = "URL权限管理")
public class AuthorityAdminController extends BaseController {


    @Autowired
    private AuthorityAdminServiceImpl authorityAdminServiceImpl;
    @Autowired
    private RoleAuthAdminServiceImpl roleAuthAdminServiceImpl;


    @GetMapping("/findAll")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "数据id", required = false, paramType = "query"),
    })
    public Result findAll(Integer page, Integer limit) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("id", getString("id", ""));
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<AuthorityAdmin> authorityAdmins = authorityAdminServiceImpl.page(page, limit, param, sort);
        return Result.success(authorityAdmins.getContent(), authorityAdmins.getTotalPages());
    }


    /**
     * TODO
     *
     * @param type           t=1 添加，=2修改
     * @param authorityAdmin 对象数据
     * @return java.lang.String
     */
    @PostMapping("/save/{type}")
    @ApiOperation("添加/修改")
    public Result save(@PathVariable Integer type, AuthorityAdmin authorityAdmin) {
        if (type == 1) {
            authorityAdminServiceImpl.save(authorityAdmin);
        } else {
            authorityAdminServiceImpl.save(authorityAdmin);
        }
        return Result.success("success");
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除/单删除")
    public Result delete(Integer[] ids) {
        authorityAdminServiceImpl.deleteByIds(ids);
        return Result.success("success");
    }


    @GetMapping("/putAuthority")
    @ApiOperation("扫描权限：权限列表数据刷新，根据权限注解动态生成权限列表，无权限注解默认然后用户有权限访问")
    public Result putAuthority() {
        //获得到所有类
        // List<Class<?>> classByPackageName = ClassUtil.getClasses(PACKAGE_NAME);
        //保存
        authorityAdminServiceImpl.putClass();
        return Result.success("success");
    }


    @GetMapping("/findList")
    @ApiOperation("查询所有,跟据角色赋予选中状态")
    public Result findList(Integer roleId) {
        List<AuthorityAdmin> roleAuthorityChecked = roleAuthAdminServiceImpl.findRoleAuthorityChecked(roleId);
        return Result.success(roleAuthorityChecked);
    }
}
