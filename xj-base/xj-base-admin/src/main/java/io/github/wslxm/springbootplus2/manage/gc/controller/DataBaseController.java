package io.github.wslxm.springbootplus2.manage.gc.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO;
import io.github.wslxm.springbootplus2.manage.gc.service.DataBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据库操作类/代码生成等处理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:03
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/gc/dataBase")
@Api(value = "DataBaseController", tags = "base--gc--代码生成--查询表数据")
public class DataBaseController extends BaseController<DataBaseService> {


    @ApiOperation("查询所有表名")
    @GetMapping(value = "/table/list")
    @ApiImplicitParam(name = "dataSourceId", value = "数据源Id (如果没有选择数据源,默认查询当前项目的数据源一)", required = false, paramType = "query", example = "")
    public Result<List<TableVO>> findTable(String dataSourceId) {
        List<TableVO> tables = baseService.findTable(dataSourceId);
        return Result.success(tables);
    }


    @ApiOperation("查询指定表下所有字段内容")
    @GetMapping(value = "/table/field")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名", required = false, paramType = "query"),
            @ApiImplicitParam(name = "dataSourceId", value = "数据源Id (如果没有选择数据源,默认查询当前项目的数据源一)", required = false, paramType = "query", example = "")
    })
    public Result<List<TableFieldVO>> findTableField(@RequestParam(required = false) String tableName, String dataSourceId) {
        List<TableFieldVO> tableField = baseService.findTableField(tableName, dataSourceId);
        return Result.success(tableField);
    }
}