package com.ws.ldy.modules.sys.gc.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.gc.model.vo.XjTableVO;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.gc.model.vo.XjTableFieldVO;
import com.ws.ldy.modules.sys.gc.service.XjDataBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *   数据库操作类/代码生成等处理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:03
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping(BaseConstant.Sys.API + "/dataBase")
@Api(value = "XjDataBaseController", tags = "base-plus--查询数据库表数据", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class XjDataBaseController extends BaseController<XjDataBaseService> {


    @ApiOperation("查询所有表名")
    @RequestMapping(value = "/findTable", method = RequestMethod.GET)
    @ApiImplicitParam(name = "dataSourceId", value = "数据源Id (如果没有选择数据源,默认查询当前项目的数据源一)", required = false, paramType = "query", example = "")
    public R<List<XjTableVO>> findTable(String dataSourceId) {
        List<XjTableVO> tables = baseService.findTable(dataSourceId);
        return R.success(tables);
    }


    @ApiOperation("查询指定表下使用字段内容")
    @RequestMapping(value = "/findTableField", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名", required = false, paramType = "query"),
            @ApiImplicitParam(name = "dataSourceId", value = "数据源Id (如果没有选择数据源,默认查询当前项目的数据源一)", required = false, paramType = "query", example = "")
    })
    public R<List<XjTableFieldVO>> findTableField(@RequestParam(required = false) String tableName, String dataSourceId) {
        List<XjTableFieldVO> tableField = baseService.findTableField(tableName,dataSourceId);
        return R.success(tableField);
    }
}