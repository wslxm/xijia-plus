package com.ws.ldy.baseadmin.controller;

import com.ws.ldy.baseadmin.controller.vo.FieldCG;
import com.ws.ldy.baseadmin.service.impl.CodeGenerationImpl;
import com.ws.ldy.admincore.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * TODO  代码生成
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/dataBase")
public class GenerateController extends BaseController {
    /**
     * 预览代码生成路径(全一个目录)
     */
    private static String PATH_PREVIEW = "File/src/txt/";
    /**
     * 模块/项目名称（生成属性/接口名称,引入依赖使用）
     */
    private  static String entryName = "base-admin";
    /**
     * 包根路径，引入依赖使用
     */
    private  static String packName = "com.ws.ldy.baseadmin";
    /**
     * 实际代码生成路径
     */
    private static String path_entity = "File/src/main/java/com/ws/ldy/entity/";
    private static String path_controller = "File/src/main/java/com/ws/ldy/controller/";
    private static String path_service = "File/src/main/java/com/ws/ldy/service/";
    private static String path_service_impl = "File/src/main/java/com/ws/ldy/service/impl/";
    private static String path_dao = "File/src/main/java/com/ws/ldy/dao/";
    //html
    private static String path_html_main = "File/src/main/resources/templates/console/";
    private static String path_html_add = "File/src/main/resources/templates/console/";
    private static String path_html_upd = "File/src/main/resources/templates/console/";

    @Autowired
    private CodeGenerationImpl codeGenerationImpl;

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
    @ResponseBody
    @RequestMapping("/codeGeneration/{type}")
    public Map<String, String> codeGeneration(@PathVariable Integer type, String data, String tableName) throws Exception {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        // 获得服务器url路径
        String baseUrl = url.replace("/dataBase/codeGeneration/1", "").replace("/dataBase/codeGeneration/2", "");
        // 模板位置(默认读取resources/static下)
        final String pathTp = baseUrl + "/template";
        //final String pathTp =baseUrl+ "/base-admin/src/main/resources/static/template";
        // 数据解析成集合
        List<Map<String, Object>> dataList = codeGenerationImpl.getDataAnalysis(data);
        //FieldCG 数据保存和处理
        FieldCG fieldCG = new FieldCG();
        fieldCG.setPathTp(pathTp);                // 代码模板位置
        fieldCG.setEntryName(entryName);          // 项目、模块实际名称
        fieldCG.setPackName(this.packName);       // 包名称
        fieldCG.setTableName(tableName);          // 数据库的表名称
        fieldCG.dataProcessing();                 // 数据处理FieldCG （大小写，驼峰转换等）
        //fieldCG.setPathFather(super.getPathFather(entryName));   // 父目录位置
        //fieldCG.setPathDeploy(super.getPath());                  // 父目录位置
        //1/预览生成, 2/实际生成
        if (type == 1) {
            codeGenerationPreview(fieldCG, dataList);
        } else {
            codeGeneration(fieldCG, dataList, entryName, tableName);
        }
        //返回预览文件地址
        Map<String, String> pathMap = codeGenerationImpl.getPathMap();
        return pathMap;
    }

    /**
     * TODO    预览文件生成
     *
     * @param fieldCG  生成文件相关信息（路径/包/类名等）
     * @param dataList 数据库字段数据
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:24
     */
    public void codeGenerationPreview(FieldCG fieldCG, List<Map<String, Object>> dataList) throws Exception {
        //生成预览文件,编译后的的服务器根目录,方便直接url查看
        CodeGenerationImpl.SUFFIX_NAME_JAVA = ".txt";         // 预览 java 后缀名
        CodeGenerationImpl.SUFFIX_NAME_HTML = ".txt";         // 预览 html 后缀名
        //文件生成
        codeGenerationImpl.buildEntity(dataList, fieldCG, PATH_PREVIEW);          //生成Entity
        codeGenerationImpl.buildController(dataList, fieldCG, PATH_PREVIEW);      //生成Controller
        codeGenerationImpl.buildService(dataList, fieldCG, PATH_PREVIEW);         //生成service
        codeGenerationImpl.buildServiceImpl(dataList, fieldCG, PATH_PREVIEW);     //生成serviceImpl
        codeGenerationImpl.buildDao(fieldCG, PATH_PREVIEW);                       //生成dao
        //html
        codeGenerationImpl.buildMainHtml(dataList, fieldCG, PATH_PREVIEW);
        codeGenerationImpl.buildAddHtml(dataList, fieldCG, PATH_PREVIEW);
        codeGenerationImpl.buildUpdHtml(dataList, fieldCG, PATH_PREVIEW);
    }

    /**
     * TODO    实际生成
     *
     * @param fieldCG  生成文件相关信息（路径/包/类名等）
     * @param dataList 数据库字段数据
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:24
     */
    public void codeGeneration(FieldCG fieldCG, List<Map<String, Object>> dataList, String entryName, String tableName) throws Exception {
        //生成文件，存在会覆盖
        CodeGenerationImpl.SUFFIX_NAME_JAVA = ".java";  //  生成html生成的文件后缀名
        CodeGenerationImpl.SUFFIX_NAME_HTML = ".html";  //  生成html生成的文件后缀名
        //admin 系统核心内容不允许生成,以免造成误操作
        if (tableName.equals("t_admin_user")
                && tableName.equals("t_admin_role_user")
                && tableName.equals("t_admin_role_menu")
                && tableName.equals("t_admin_role")
                && tableName.equals("t_admin_menu")
                && tableName.equals("t_admin_authority")
        ) {
            //不可生成
            return;
        }
        //文件生成
        codeGenerationImpl.buildEntity(dataList, fieldCG, path_entity);            //生成Entity
        codeGenerationImpl.buildController(dataList, fieldCG, path_controller);    //生成Controller
        codeGenerationImpl.buildService(dataList, fieldCG, path_service);          //生成service
        codeGenerationImpl.buildServiceImpl(dataList, fieldCG, path_service_impl); //生成serviceImpl
        codeGenerationImpl.buildDao(fieldCG, path_dao);                            //生成dao
        //html entryNameSmall
        codeGenerationImpl.buildMainHtml(dataList, fieldCG, path_html_main + fieldCG.getHtmlNameLower() + "/");
        codeGenerationImpl.buildAddHtml(dataList, fieldCG, path_html_add + fieldCG.getHtmlNameLower() + "/");
        codeGenerationImpl.buildUpdHtml(dataList, fieldCG, path_html_upd + fieldCG.getHtmlNameLower() + "/");
    }
}
