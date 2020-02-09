package com.ws.ldy.baseadmin.controller;

import cn.hutool.core.lang.Dict;
import com.ws.ldy.baseadmin.service.impl.DataBaseServiceImpl;
import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("/dataBase")
public class DataBaseController extends BaseController {

    @Autowired
    private DataBaseServiceImpl dataBaseServiceImpl;
    /**
     * TODO  查询当前连接数据库所有表名
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 12:56
     */
    @RequestMapping("/findTable")
    @ResponseBody
    public ResponseData findTable() {
        List<String> tables =dataBaseServiceImpl.findTable();
        //转为前台需要的树结构数据
        List<Dict> tableList = new ArrayList<>();
        tables.forEach(item -> tableList.add(Dict.create().set("name", item)));
        return ResponseData.success(tableList);
    }


    /**
     * TODO   根据表名查询改表所有字段及类型，备注
     *
     * @param name 数据库表名
     * @return java.util.List<cn.hutool.core.lang.Dict>
     * @date 2019/11/20 12:56
     */
    @RequestMapping("/findTableField")
    @ResponseBody
    public ResponseData findTableField(String name) {
        List<Map<String, String>> tableField = dataBaseServiceImpl.findTableField(name);
        return ResponseData.success(tableField);
    }


}