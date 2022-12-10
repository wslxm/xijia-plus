package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.SysUserDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.SysUserQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO;
import io.github.wslxm.springbootplus2.manage.sys.service.SysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * base--sys--用户管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/user")
public class SysUserController extends BaseController<SysUserService> {


    /**
     * 列表查询
     *
     * @param query
     * @return io.github.wslxm.springbootplus2.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO>>
     * @author wangsong
     */
    @GetMapping(value = "/findPage")
    public Result<IPage<SysUserVO>> findPage(@ModelAttribute @Validated SysUserQuery query) {
        return Result.successFind(baseService.findPage(query));
    }


    /**
     * ID查询
     *
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO>
     * @author wangsong
     */
    @GetMapping(value = "/{id}")
    public Result<SysUserVO> findId(@PathVariable String id) {
        return Result.success(baseService.findId(id));
    }


    /**
     * 添加
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     * @author wangsong
     */
    @PostMapping
    public Result<String> insert(@RequestBody @Validated SysUserDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }


    /**
     * ID编辑
     *
     * @param id
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PutMapping(value = "/{id}")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody SysUserDTO dto) {
        dto.setPassword(null);
        return Result.successUpdate(baseService.upd(id, dto));
    }


    /**
     * ID删除
     *
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }


    /**
     * 查询当前登录人的个人信息
     *
     * @return io.github.wslxm.springbootplus2.core.result.Result<io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO>
     * @author wangsong
     */
    @GetMapping(value = "/findUser")
    public Result<SysUserVO> findUser() {
        return Result.successFind(BeanDtoVoUtil.convert(baseService.findId(JwtUtil.getJwtUser(request).getUserId()), SysUserVO.class));
    }


    /**
     * 获取指定角色的用户列表
     * <p>
     * 查询所有用户
     * </P>
     *
     * @param roleId 角色Id
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO>>
     * @author wangsong
     */
    @GetMapping(value = "/findByRoleId")
    public Result<List<SysUserVO>> findByRoleId(@RequestParam String roleId) {
        return Result.success(BeanDtoVoUtil.listVo(baseService.findByRoleId(roleId), SysUserVO.class));
    }


    /**
     * 查询所有-只返回关键数据(姓名/昵称/电话/id)
     *
     * @param searchName 姓名or用户名
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO>>
     * @author wangsong
     */
    @GetMapping(value = "/list/keyData")
    public Result<List<SysUserVO>> listKeyData(@RequestParam(required = false) String searchName) {
        return Result.success(baseService.listKeyData(searchName));
    }


    /**
     * 用户登录
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PostMapping(value = "/login")
    public Result<Boolean> login(@RequestBody @Validated LoginDTO dto) {
        return Result.success(baseService.login(dto));
    }


    /**
     * 修改当前登录人的信息
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PutMapping(value = "/updUser")
    public Result<Boolean> updUser(@RequestBody SysUserDTO dto) {
        return Result.successUpdate(baseService.upd(JwtUtil.getJwtUser(request).getUserId(), dto));
    }


    /**
     * 修改当前登录人的密码
     * <p>
     * 判断原密码是否正确,不正确返回错误信息msg ,正确直接修改,密码进行MD5加密 --> val(前端输入密码值)+盐(后端规则指定)=最终密码）
     * </P>
     *
     * @param oldPassword
     * @param password
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PutMapping(value = "/updByPassword")
    public Result<Boolean> updByPassword(@RequestParam String oldPassword, @RequestParam String password) {
        return Result.successUpdate(baseService.updByPassword(oldPassword, password));
    }


    /**
     * 重置任意用户密码
     *
     * @param id
     * @param password
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PutMapping(value = "/{id}/resetPassword")
    public Result<Boolean> updResetPassword(@PathVariable String id, @RequestParam String password) {
        return Result.successUpdate(baseService.updResetPassword(id, password));
    }

}
