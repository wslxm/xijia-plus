package com.ws.ldy.admin.controller;

import cn.hutool.core.lang.Dict;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.admin.service.impl.DataBaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO  数据库操作类/代码生成等处理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:03
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/dataBase")
@Api(value = "DataBaseController", tags = "数据库表查询")
public class DataBaseController extends BaseController {


    @Autowired
    private DataBaseServiceImpl dataBaseServiceImpl;


    @GetMapping("/findTable")
    @ApiOperation("查询所有表名")
    public Result findTable() {
        List<String> tables = dataBaseServiceImpl.findTable();
        //转为前台需要的树结构数据
        List<Dict> tableList = new ArrayList<>();
        tables.forEach(item -> tableList.add(Dict.create().set("name", item)));
        return success(tableList);
    }


    @GetMapping("/findTableField")
    @ApiOperation("查询指定表下使用字段内容")
    public Result findTableField(String tableName) {
        List<Map<String, String>> tableField = dataBaseServiceImpl.findTableField(tableName);
        return success(tableField);
    }
}