package com.ws.ldy.baseadmin.controller;

import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.baseadmin.generate.FieldCG;
import com.ws.ldy.baseadmin.generate.GenerateConfig;
import com.ws.ldy.baseadmin.generate.GenerateUtil;
import com.ws.ldy.baseadmin.generate.GenerationSevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/dataBase")
@Api(tags = {"Admin-GenerateCode"}, description = "代码生成器-只限于页面调用")
public class GenerateController extends BaseController {

    @Autowired
    private GenerationSevice generationSevice;

    /**
     * TODO   代码生成
     *
     * @param data      生成需要的表字段数据
     * @param tableName 数据库表名称 --> 处理为生成的代码文件名称，类名等
     * @param entryName 项目名称    --> 处理为生成的代码需要放置的
     * @return 预览文件URL地址
     * @param1 data [{ primarykeyId：true }] 表示为id主键
     * @param2 data [{ selfGrowth：true }] 表示为id主键自增
     * @param3 data [{ search：true }] 表示要为该字段添加搜索功能
     * @date 2019/11/20 16:26
     */
    @PostMapping("/codeGeneration/{type}")
    @ApiOperation("代码生成")
    public ResponseData GenerationSevice(@PathVariable Integer type, String data, String tableName) throws Exception {
        // 获得客户端发送请求的完整url, 并去除接口获得服务器url路径, 接口名发生变动请手动修改target
        String baseUrl = request.getRequestURL().toString().replace("/dataBase/codeGeneration/1", "").replace("/dataBase/codeGeneration/2", "");
        // 数据库字段相关数据解析成集合
        List<Map<String, Object>> dataList = GenerateUtil.getDataAnalysis(data);
        // 添加代码生成相关通用数据
        FieldCG.pathTp = baseUrl + GenerateConfig.PATH_TEMPLATE;  // 代码模板位置,resources/static
        FieldCG.entryName = GenerateConfig.entryName;             // 项目、模块实际名称
        FieldCG.packName = GenerateConfig.packName;               // 包名称
        FieldCG.tableName = tableName;                            // 数据库的表名称
        new FieldCG().dataProcessing();                           // 数据处理FieldCG （大小写，驼峰转换等等）

        if (type == 1) {
            //预览生成,生成的后缀名替换为 txt
            GenerateConfig.SUFFIX_JAVA = GenerateConfig.SUFFIX_JAVA_PREVIEW;
            GenerateConfig.SUFFIX_HTML = GenerateConfig.SUFFIX_HTML_PREVIEW;
            GenerationSevicePreview(dataList);
        } else {
            //实际生成
            //预览生成,生成的后缀名替换为.JAVA,.html
            GenerateConfig.SUFFIX_JAVA = GenerateConfig.SUFFIX_JAVA_CODE;
            GenerateConfig.SUFFIX_HTML = GenerateConfig.SUFFIX_HTML_CODE;
            generationCode(dataList, GenerateConfig.entryName, tableName);
        }
        System.err.println("代码生成成功，重启项目后可直接访问ip:端口+ /page/" + FieldCG.entryNameSmall + "_" + FieldCG.htmlNameLower + "_" + FieldCG.htmlNameLower );
        return ResponseData.success( GenerationSevice.pathMap);
    }

    /**
     * TODO    预览文件生成, 同时生成把生成的文件url路径保存到GenerationSeviceImp.lpathMap 参数
     *
     * @param fieldCG  生成文件相关信息（路径/包/类名等）
     * @param dataList 数据库字段数据
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:24
     */
    private void GenerationSevicePreview(List<Map<String, Object>> dataList) throws Exception {
        //文件生成
        generationSevice.buildEntity(dataList, GenerateConfig.PATH_PREVIEW);          //生成Entity
        generationSevice.buildController(dataList, GenerateConfig.PATH_PREVIEW);      //生成Controller
        generationSevice.buildService(dataList, GenerateConfig.PATH_PREVIEW);         //生成service
        generationSevice.buildServiceImpl(dataList, GenerateConfig.PATH_PREVIEW);     //生成serviceImpl
        generationSevice.buildDao(dataList, GenerateConfig.PATH_PREVIEW);                //生成dao
        //html
        generationSevice.buildMainHtml(dataList, GenerateConfig.PATH_PREVIEW);
        generationSevice.buildAddHtml(dataList, GenerateConfig.PATH_PREVIEW);
        generationSevice.buildUpdHtml(dataList, GenerateConfig.PATH_PREVIEW);
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
    private void generationCode(List<Map<String, Object>> dataList, String entryName, String tableName) throws Exception {
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
        generationSevice.buildEntity(dataList, GenerateConfig.path_entity);            //生成Entity
        generationSevice.buildController(dataList, GenerateConfig.path_controller);    //生成Controller
        generationSevice.buildService(dataList, GenerateConfig.path_service);           //生成service
        generationSevice.buildServiceImpl(dataList, GenerateConfig.path_service_impl);  //生成serviceImpl
        generationSevice.buildDao(dataList, GenerateConfig.path_dao);                   //生成dao
        //html entryNameSmall
        generationSevice.buildMainHtml(dataList, GenerateConfig.path_html_main + FieldCG.htmlNameLower + "/");
        generationSevice.buildAddHtml(dataList, GenerateConfig.path_html_add + FieldCG.htmlNameLower + "/");
        generationSevice.buildUpdHtml(dataList, GenerateConfig.path_html_upd + FieldCG.htmlNameLower + "/");
    }
}
