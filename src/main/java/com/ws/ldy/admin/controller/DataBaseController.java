package com.ws.ldy.admin.controller;

/**
 * TODO  数据库操作类/代码生成等处理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:03
 */
//@SuppressWarnings({"all"})
//@RestController
//@RequestMapping("/dataBase")
//@Api(value = "DataBaseController", tags = "数据库表查询")
//public class DataBaseController extends BaseController {
//
//    @Autowired
//    private DataBaseServiceImpl dataBaseServiceImpl;
//
//    @ApiOperation("查询所有表名")
//    @RequestMapping(value = "/findTable", method = RequestMethod.GET)
//    public Result<List<Dict>> findTable() {
//        List<String> tables = dataBaseServiceImpl.findTable();
//        //转为前台需要的树结构数据
//        List<Dict> tableList = new ArrayList<>();
//        tables.forEach(item -> tableList.add(Dict.create().set("name", item)));
//        return success(tableList);
//    }
//
//    @ApiOperation("查询指定表下使用字段内容")
//    @ApiImplicitParam(name = "tableName", value = "表名", required = false, paramType = "query")
//    @RequestMapping(value = "/findTableField", method = RequestMethod.GET)
//    public Result<List<Map<String, String>>> findTableField(@RequestParam(required = false) String tableName) {
//        List<Map<String, String>> tableField = dataBaseServiceImpl.findTableField(tableName);
//        return success(tableField);
//    }
//}