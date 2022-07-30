package io.github.wslxm.springbootplus2.manage.gc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.utils.Base64Util;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjAdminDatasourceDTO;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import io.github.wslxm.springbootplus2.manage.gc.model.query.XjAdminDatasourceQuery;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/datasource")
@Api(value = "XjAdminDatasourceController", tags = "base--gc--代码生成--数据源维护")
public class XjAdminDatasourceController extends BaseController<XjAdminDatasourceService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<XjAdminDatasourceVO>> list(@ModelAttribute @Validated XjAdminDatasourceQuery query) {
        return R.successFind(baseService.list(query));
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "id查询")
    public R<XjAdminDatasourceVO> findId(@PathVariable String id) {
        return R.successFind(baseService.findId(id));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated XjAdminDatasourceDTO dto) {
        XjAdminDatasource xjDatasource = dto.convert(XjAdminDatasource.class);
        xjDatasource.setDbPassword(Base64Util.encode(xjDatasource.getDbPassword()));
        boolean b = baseService.save(xjDatasource);
        return R.successInsert(xjDatasource.getId());
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id,@RequestBody @Validated XjAdminDatasourceDTO dto) {
        XjAdminDatasource entity = dto.convert(XjAdminDatasource.class);
        entity.setId(id);
        return R.successUpdate(baseService.updateById(entity));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @PutMapping(value = "/{id}/updPwd")
    @ApiOperation(value = "修改/重置密码")
    public R<Boolean> updDbPwd(@PathVariable String id, @RequestParam String password) {
        XjAdminDatasource entity = new XjAdminDatasource();
        entity.setId(id);
        entity.setDbPassword(Base64Util.encode(password));
        return R.successUpdate(baseService.updateById(entity));
    }


    @PostMapping(value = "/dataSourceTest/{id}")
    @ApiOperation("数据源连接测试")
    public R<Boolean> dataSourceTest(@PathVariable(required = false) String id, @RequestBody @Validated XjAdminDatasourceDTO dto) {
        String dbPassword = dto.getDbPassword();
        if (StringUtils.isNotBlank(id) && !id.equals("0")) {
            XjAdminDatasource xjAdminDatasource = baseService.getById(id);
            dbPassword = Base64Util.decrypt(xjAdminDatasource.getDbPassword());
        }
        // 主要没报错, 表示连接成功
        JdbcPool.getConn(dto.getDbUrl(), dto.getDbUsername(), dbPassword);
        return R.success(true);
    }
}
