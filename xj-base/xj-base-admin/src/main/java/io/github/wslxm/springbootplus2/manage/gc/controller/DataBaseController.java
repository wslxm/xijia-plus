package io.github.wslxm.springbootplus2.manage.gc.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO;
import io.github.wslxm.springbootplus2.manage.gc.service.DataBaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * base--gc--代码生成--查询表数据
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:03
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/gc/dataBase")
public class DataBaseController extends BaseController<DataBaseService> {


    /**
     * 查询所有表名
     *
     * @param dataSourceId 数据源Id (如果没有选择数据源,默认查询当前项目的数据源一)
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO>>
     * @author wangsong
     * @date 2022/12/10 0010 14:09
     * @version 1.0.0
     */
    @GetMapping(value = "/table/list")
    public Result<List<TableVO>> findTable(String dataSourceId) {
        List<TableVO> tables = baseService.findTable(dataSourceId);
        return Result.success(tables);
    }


    /**
     * 查询指定表下所有字段内容
     *
     * @param tableName    表名
     * @param dataSourceId 数据源Id (如果没有选择数据源,默认查询当前项目的数据源一)
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO>>
     * @author wangsong
     * @date 2022/12/10 0010 14:09
     * @version 1.0.0
     */
    @GetMapping(value = "/table/field")
    public Result<List<TableFieldVO>> findTableField(@RequestParam(required = false) String tableName, String dataSourceId) {
        List<TableFieldVO> tableField = baseService.findTableField(tableName, dataSourceId);
        return Result.success(tableField);
    }
}