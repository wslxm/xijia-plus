package io.github.wslxm.springbootplus2.manage.gc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.constant.NumberConst;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.utils.Base64Util;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.DatasourceDTO;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.Datasource;
import io.github.wslxm.springbootplus2.manage.gc.model.query.DatasourceQuery;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.DatasourceVO;
import io.github.wslxm.springbootplus2.manage.gc.service.DatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.util.JdbcPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/gc/datasource")
@Api(value = "DatasourceController", tags = "base--gc--代码生成--数据源维护")
public class DatasourceController extends BaseController<DatasourceService> {


    @GetMapping(value = "/findPage")
    @ApiOperation(value = "列表查询")
    public Result<IPage<DatasourceVO>> findPage(@ModelAttribute @Validated DatasourceQuery query) {
        return Result.successFind(baseService.findPage(query));
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "id查询")
    public Result<DatasourceVO> findId(@PathVariable String id) {
        return Result.successFind(baseService.findId(id));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public Result<String> insert(@RequestBody @Validated DatasourceDTO dto) {
        Datasource xjDatasource = dto.convert(Datasource.class);
        xjDatasource.setDbPassword(Base64Util.encode(xjDatasource.getDbPassword()));
        boolean b = baseService.save(xjDatasource);
        return Result.successInsert(xjDatasource.getId());
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated DatasourceDTO dto) {
        Datasource entity = dto.convert(Datasource.class);
        entity.setId(id);
        return Result.successUpdate(baseService.updateById(entity));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.removeById(id));
    }


    @PutMapping(value = "/{id}/updPwd")
    @ApiOperation(value = "修改/重置密码")
    public Result<Boolean> updDbPwd(@PathVariable String id, @RequestParam String password) {
        Datasource entity = new Datasource();
        entity.setId(id);
        entity.setDbPassword(Base64Util.encode(password));
        return Result.successUpdate(baseService.updateById(entity));
    }


    @PostMapping(value = "/dataSourceTest/{id}")
    @ApiOperation("数据源连接测试")
    public Result<Boolean> dataSourceTest(@PathVariable(required = false) String id, @RequestBody @Validated DatasourceDTO dto) {
        String dbPassword = dto.getDbPassword();
        if (StringUtils.isNotBlank(id) && !id.equals(NumberConst.ZERO)) {
            Datasource xjAdminDatasource = baseService.getById(id);
            dbPassword = Base64Util.decrypt(xjAdminDatasource.getDbPassword());
        }
        // 主要没报错, 表示连接成功
        JdbcPool.getConn(dto.getDbUrl(), dto.getDbUsername(), dbPassword);
        return Result.success(true);
    }
}
