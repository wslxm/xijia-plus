package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.RoleDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.RoleQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleService;
import org.springframework.web.bind.annotation.*;

/**
 * base--sys--角色管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/role")
public class RoleController extends BaseController<RoleService> {


    /**
     * 列表查询
     *
     * @param query
     * @return io.github.wslxm.springbootplus2.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage < io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO>>
     * @author wangsong
     */
    @GetMapping(value = "/findPage")
    public Result<IPage<RoleVO>> findPage(@ModelAttribute RoleQuery query) {
        return Result.success(baseService.findPage(query));
    }


    /**
     * 添加
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     * @author wangsong
     */
    @PostMapping
    public Result<String> insert(@RequestBody RoleDTO dto) {
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
    public Result<Boolean> upd(@PathVariable String id, @RequestBody RoleDTO dto) {
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


}
