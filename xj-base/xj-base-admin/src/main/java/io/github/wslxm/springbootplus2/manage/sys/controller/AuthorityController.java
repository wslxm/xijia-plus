package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.AuthorityDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AuthorityQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.AuthorityVO;
import io.github.wslxm.springbootplus2.manage.sys.service.AuthorityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * base--sys--URL权限管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/authority")
public class AuthorityController extends BaseController<AuthorityService> {


    /**
     * 查询所有-接口管理
     * <p>
     * 查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1
     * </P>
     *
     * @param query
     * @author wangsong
     */
    @GetMapping(value = "/list")
    public Result<List<AuthorityVO>> list(@ModelAttribute AuthorityQuery query) {
        return Result.successFind(baseService.list(query));
    }


    /**
     * ID编辑
     *
     * @param id
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     * @date 2022/12/10 0010 14:14
     * @version 1.0.0
     */
    @PutMapping(value = "/{id}")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated AuthorityDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }


    /**
     * 扫描权限
     * <p>
     * 扫描权限列表数据, 1、存在变更接口描叙, 2、url变动会重新生成权限数据,角色原有的该接口权限会丢失,需重新分配 3、自动删除的多余接口
     * </P>
     *
     * @author wangsong
     */
    @PutMapping(value = "/refreshAuthority")
    public Result<Boolean> refreshAuthority() {
        return Result.success(baseService.refreshAuthDb());
    }
}
