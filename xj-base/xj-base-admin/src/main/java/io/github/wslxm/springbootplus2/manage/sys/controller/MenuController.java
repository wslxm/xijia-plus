package io.github.wslxm.springbootplus2.manage.sys.controller;


import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.MenuDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.MenuQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MenuVO;
import io.github.wslxm.springbootplus2.manage.sys.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * base--sys--菜单管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/menu")
public class MenuController extends BaseController<MenuService> {


    /**
     * 列表查询(不支持分页)
     * <p>
     * 根据sort正序排序返回
     * </P>
     *
     * @param query
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.MenuVO>>
     * @author wangsong
     */
    @GetMapping(value = "/tree")
    public Result<List<MenuVO>> tree(@ModelAttribute MenuQuery query) {
        return Result.success(baseService.tree(query));
    }


    /**
     * 菜单添加
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     * @author wangsong
     */
    @PostMapping
    public Result<String> insert(@RequestBody MenuDTO dto) {
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
    public Result<Boolean> upd(@PathVariable String id, @RequestBody MenuDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }


    /**
     * ID删除
     * <p>
     * 同时删除当前菜单和当前菜单下的所有子菜单
     * </P>
     *
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < java.lang.String>>
     * @author wangsong
     */
    @DeleteMapping(value = "/{id}")
    public Result<List<String>> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }


    /**
     * 左导航菜单
     * <p>
     * 当前用户对应的角色菜单数据, 树结构数据,无限级,不限制层次,根据sort字段正序排序,sort越小越靠前
     * </P>
     *
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.MenuVO>>
     * @author wangsong
     */
    @GetMapping(value = "/findTree")
    public Result<List<MenuVO>> findTree() {
        return Result.successFind(baseService.findTree());
    }
}
