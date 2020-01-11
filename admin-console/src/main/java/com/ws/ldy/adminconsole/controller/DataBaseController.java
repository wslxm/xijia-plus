package com.ws.ldy.adminconsole.controller;

import cn.hutool.core.lang.Dict;
import com.ws.ldy.adminconsole.controller.vo.FieldCG;
import com.ws.ldy.adminconsole.service.impl.CodeGenerationImpl;
import com.ws.ldy.adminconsole.service.impl.DataBaseServiceImpl;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private CodeGenerationImpl codeGenerationImpl;

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


    /**
     * TODO   代码生成
     *
     * @param data      生成需要的表字段数据
     * @param tableName 数据库表名称 --> 处理为生成的代码文件名称，类名等
     * @param entryName 项目名称    --> 处理为生成的代码需要放置的
     * @return void
     * @param1 data [{ primarykeyId：true }] 表示为id主键
     * @param2 data [{ selfGrowth：true }] 表示为id主键自增
     * @param3 data [{ search：true }] 表示要为该字段添加搜索功能
     * @date 2019/11/20 16:26
     */
    @RequestMapping("/codeGeneration/{type}")
    @ResponseBody
    public Map<String, String> codeGeneration(@PathVariable Integer type, String data, String tableName, String entryName) throws Exception {

        // 模板位置
        final String pathTp = "/admin-console/src/main/resources/static/template";
        // 数据解析成集合,
        List<Map<String, Object>> dataList = codeGenerationImpl.getDataAnalysis(data);
        //FieldCG 数据保存和处理
        FieldCG fieldCG = new FieldCG();
        fieldCG.setEntryName(entryName);          // 项目实际名称
        fieldCG.setTableName(tableName);          // 数据库名称
        fieldCG.setPathTp(pathTp);                // 模板位置
        fieldCG.setPathFather(super.getPathFather(entryName));   // 父目录位置
        fieldCG.setPathDeploy(super.getPath());   // 父目录位置
        //数据处理FieldC内的数据
        fieldCG.dataProcessing();
        // 代码生成后的包名称
        fieldCG.setPackName("com.ws.ldy." + fieldCG.getEntryNameSmall());
        // dao，service 的factory代码位置    EntryNameSmall = admin-console -->  adminconsole
        fieldCG.setPathFactoryTp(entryName + "/src/main/java/com/ws/ldy/" + fieldCG.getEntryNameSmall() + "/factory");
        if (type == 1) {
            //生成预览文件,编译后的的服务器根目录,方便直接url查看
            String pathJava = super.getPath().replace(fieldCG.getPathFather(), "");
            fieldCG.setPathJava(pathJava + "/static/code/");              // 预览文件 Java 生成位置 编译后目录classes 目录下
            fieldCG.setPathHtml(pathJava + "/static/code/");              // 预览文件 html 生成位置 编译后目录
            //fieldCG.setPathJava( "/src/main/resources/static/code/");   // 预览文件 Java 生成位置 当前项目下目录
            //fieldCG.setPathHtml( "/src/main/resources/static/code/");   // 预览文件 html 生成位置 当前项目下目录
            CodeGenerationImpl.SUFFIX_NAME_JAVA = ".txt";     //生成java的文件后缀名
            CodeGenerationImpl.SUFFIX_NAME_HTML = ".txt";     //生成html生成的文件后缀名
            //文件生成
            codeGenerationImpl.buildEntity(dataList, fieldCG, fieldCG.getPathJava());          //生成Entity
            codeGenerationImpl.buildController(dataList, fieldCG, fieldCG.getPathJava());      //生成Controller
            codeGenerationImpl.buildService(dataList, fieldCG, fieldCG.getPathJava());         //生成service
            codeGenerationImpl.buildServiceImpl(dataList, fieldCG, fieldCG.getPathJava());     //生成serviceImpl
            codeGenerationImpl.buildDao(fieldCG, fieldCG.getPathJava());                       //生成dao
            codeGenerationImpl.buildDaoFactory(fieldCG, fieldCG.getPathJava());                 //追加dao，依赖注如信息
            codeGenerationImpl.buildServiceFactory(fieldCG, fieldCG.getPathJava());             //追加service,依赖注如信息
            //html
            codeGenerationImpl.buildMainHtml(dataList, fieldCG, fieldCG.getPathHtml());
            codeGenerationImpl.buildAddHtml(dataList, fieldCG, fieldCG.getPathHtml());
            codeGenerationImpl.buildUpdHtml(dataList, fieldCG, fieldCG.getPathHtml());
        } else {
            //生成文件，存在会覆盖
            fieldCG.setPathHtml(entryName + "/src/main/resources" + "/templates/" + fieldCG.getEntryNameLast() + "/"); // html 生成位置
            fieldCG.setPathJava(entryName + "/src/main/java" + "/com/ws/ldy/" + fieldCG.getEntryNameSmall() + "/");    // Java 生成位置
            CodeGenerationImpl.SUFFIX_NAME_JAVA = ".java";     //生成java的文件后缀名
            CodeGenerationImpl.SUFFIX_NAME_HTML = ".html";     //生成html生成的文件后缀名
            //admin 系统核心内容不允许生成,以免造成误操作
            if (tableName.equals("t_admin_user")
                    && tableName.equals("t_admin_role_user")
                    && tableName.equals("t_admin_role_menu")
                    && tableName.equals("t_admin_role")
                    && tableName.equals("t_admin_menu")
                    && tableName.equals("t_admin_authority")
            ) {
                //不可生成
                return null;
            }
            //文件生成
            codeGenerationImpl.buildEntity(dataList, fieldCG, fieldCG.getPathJava() + "entity/");            //生成Entity
            codeGenerationImpl.buildController(dataList, fieldCG, fieldCG.getPathJava() + "controller/");    //生成Controller
            codeGenerationImpl.buildService(dataList, fieldCG, fieldCG.getPathJava() + "service/");          //生成service
            codeGenerationImpl.buildServiceImpl(dataList, fieldCG, fieldCG.getPathJava() + "service/impl/"); //生成serviceImpl
            codeGenerationImpl.buildDao(fieldCG, fieldCG.getPathJava() + "dao/");                            //生成dao
            codeGenerationImpl.buildDaoFactory(fieldCG, fieldCG.getPathJava() + "factory/");                  //追加dao，依赖注如信息
            codeGenerationImpl.buildServiceFactory(fieldCG, fieldCG.getPathJava() + "factory/");              //追加service,依赖注如信息
            //html
            codeGenerationImpl.buildMainHtml(dataList, fieldCG, fieldCG.getPathHtml());
            codeGenerationImpl.buildAddHtml(dataList, fieldCG, fieldCG.getPathHtml());
            codeGenerationImpl.buildUpdHtml(dataList, fieldCG, fieldCG.getPathHtml());
        }
        //返回预览文件地址
        return codeGenerationImpl.getPathMap();
    }
}