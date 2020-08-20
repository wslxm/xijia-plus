package com.ws.ldy.others.generatecode.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.others.generatecode.config.GenerateConfig;
import com.ws.ldy.others.generatecode.model.vo.TableFieldVO;
import com.ws.ldy.others.generatecode.model.vo.TableVO;
import com.ws.ldy.others.generatecode.service.DataBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/dataBase")
@Api(value = "DataBaseController", tags = "数据库表查询", description = BaseConstant.InterfaceType.PC_ADMIN)
public class DataBaseController extends BaseController<DataBaseService> {


    @ApiOperation("查询所有表名")
    @RequestMapping(value = "/findTable", method = RequestMethod.GET)
    public R<List<TableVO>> findTable() {
        List<TableVO> tables = baseService.findTable();
        return R.success(tables);
    }

    @ApiOperation("查询指定表下使用字段内容")
    @ApiImplicitParam(name = "tableName", value = "表名", required = false, paramType = "query")
    @RequestMapping(value = "/findTableField", method = RequestMethod.GET)
    public R<List<TableFieldVO>> findTableField(@RequestParam(required = false) String tableName) {
        List<TableFieldVO> tableField = baseService.findTableField(tableName);
        for (TableFieldVO tableFieldVO : tableField) {
            // 判断是否为通用字段
            if (GenerateConfig.BASE_FIELDS.contains(tableFieldVO.getName())) {
                tableFieldVO.setIsChecked(false);
            } else {
                tableFieldVO.setIsChecked(true);
            }
            // 判断空串
//            if ("".equals(tableFieldVO.getDefaultVal())) {
//                tableFieldVO.setIsChecked(false);
//            } else {
//                tableFieldVO.setIsChecked(true);
//            }
        }
        return R.success(tableField);
    }
}