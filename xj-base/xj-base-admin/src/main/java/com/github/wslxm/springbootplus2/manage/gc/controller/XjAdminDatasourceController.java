package com.github.wslxm.springbootplus2.manage.gc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wslxm.springbootplus2.core.base.controller.BaseController;
import com.github.wslxm.springbootplus2.core.constant.BaseConstant;
import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.manage.gc.model.dto.XjAdminDatasourceDTO;
import com.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import com.github.wslxm.springbootplus2.manage.gc.model.query.XjAdminDatasourceQuery;
import com.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO;
import com.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import com.github.wslxm.springbootplus2.manage.gc.util.JDBCPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 代码生成数据源维护表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/datasource")
@Api(value = "XjAdminDatasourceController", tags = "base-gc--代码生成--数据源维护")
public class XjAdminDatasourceController extends BaseController<XjAdminDatasourceService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<XjAdminDatasourceVO>> list(@ModelAttribute @Validated XjAdminDatasourceQuery query) {
        return R.successFind(baseService.list(query));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated XjAdminDatasourceDTO dto) {
        XjAdminDatasource xjDatasource = dto.convert(XjAdminDatasource.class);
        boolean b = baseService.save(xjDatasource);
        return R.successInsert(xjDatasource.getId());
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@RequestBody @Validated XjAdminDatasourceDTO dto) {
        return R.successUpdate(baseService.updateById(dto.convert(XjAdminDatasource.class)));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @PutMapping(value = "/{id}/updPwd")
    @ApiOperation(value = "修改/重置密码")
    public R<Boolean> updDbPwd(@PathVariable String id, @RequestParam String password) {
        XjAdminDatasource entity = new XjAdminDatasource();
        entity.setId(id);
        entity.setDbPassword(password);
        return R.successUpdate(baseService.updateById(entity));
    }


    @PostMapping(value = "/dataSourceTest")
    @ApiOperation("数据源连接测试")
    public R<Boolean> dataSourceTest(@RequestBody @Validated XjAdminDatasourceDTO dto) {
        // 主要没报错, 表示连接成功
        JDBCPool.getConn(dto.getDbUrl(), dto.getDbUsername(), dto.getDbPassword());
        return R.success(true);
    }
}
