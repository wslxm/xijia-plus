package com.ws.ldy.others.generatecode.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.others.generatecode.config.GenerateConfig;
import com.ws.ldy.others.generatecode.model.DsField;
import com.ws.ldy.others.generatecode.model.dto.GenerateDto;
import com.ws.ldy.others.generatecode.model.entity.XjDatasource;
import com.ws.ldy.others.generatecode.service.XjDatasourceService;
import com.ws.ldy.others.generatecode.service.impl.GenerationSeviceImpl;
import com.ws.ldy.others.generatecode.util.GenerateDataProcessing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/generate")
@Api(value = "GenerateController", tags = "v-1.0 -- 代码生成", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class GenerateController extends BaseController<GenerationSeviceImpl> {


    @Autowired
    private GenerationSeviceImpl generationSeviceImpl;

    @Autowired
    private XjDatasourceService xjDatasourceService;
    /**
     *  预览代码生成 (查询预览代码，预览代码存放于File/code/src.... 目录下，前端可直接访问)
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成预览代码")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<Map<String, String>> preview(@RequestBody GenerateDto generateDto) {
        // 表前缀和字段前缀配置
        if(StringUtils.isNotBlank(generateDto.getDataSourceId())){
            XjDatasource datasource = xjDatasourceService.getById(generateDto.getDataSourceId());
            GenerateConfig.TABLE_PREFIX = datasource.getDbPrefix();
            GenerateConfig.FIELD_PREFIX = datasource.getDbFieldPrefix();
        }else{
            GenerateConfig.TABLE_PREFIX = GenerateConfig.TABLE_PREFIX_DEFAULT;
            GenerateConfig.FIELD_PREFIX = GenerateConfig.FIELD_PREFIX_DEFAULT;
        }
        // 解析数据库字段数据
        List<Map<String, Object>> dataList = GenerateDataProcessing.getDataAnalysis(generateDto.getData());
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources/static,
        new DsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);

        // 生成html 的txt文件预览，html会被渲染成页面
        GenerateConfig.SUFFIX_JAVA_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_HTML_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_XML_PT = GenerateConfig.SUFFIX_TXT;
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationSeviceImplImp.lpathMap 参数
        generationSeviceImpl.buildEntity(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_ENTITY);           // 生成Entity
        generationSeviceImpl.buildVO(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_VO);  //VO             // 生成Entity
        generationSeviceImpl.buildDTO(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_DTO);  //DTO          // 生成Entity
        generationSeviceImpl.buildController(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_CONTROLLER);     // 生成Controller
        generationSeviceImpl.buildService(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE);           // 生成service
        generationSeviceImpl.buildServiceImpl(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE_IMPL);  // 生成serviceImpl
        generationSeviceImpl.buildMapper(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_MAPPER);                   // 生成dao
        generationSeviceImpl.buildMapperXml(dataList, GenerateConfig.BASE_PATH_XML_YL + GenerateConfig.PATH_MAPPER);
//        // html
//        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + DsField.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/" + GenerateConfig.PACK_PATH_ZP + "_" + GenerateConfig.MODULE_NAME + "_" + DsField.TABLE_NAME_LOWER + "_" + DsField.TABLE_NAME_LOWER);

        return R.success(GenerationSeviceImpl.pathMap);
    }


    /**
     * 代码生成对应路径
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成代码")
    @RequestMapping(value = "/generateCode", method = RequestMethod.POST)
    public R<Map<String, String>> generateCode(@RequestBody GenerateDto generateDto) {
        // 表前缀和字段前缀配置
        if(StringUtils.isNotBlank(generateDto.getDataSourceId())){
            XjDatasource datasource = xjDatasourceService.getById(generateDto.getDataSourceId());
            GenerateConfig.TABLE_PREFIX = datasource.getDbPrefix();
            GenerateConfig.FIELD_PREFIX = datasource.getDbFieldPrefix();
        }else{
            GenerateConfig.TABLE_PREFIX = GenerateConfig.TABLE_PREFIX_DEFAULT;
            GenerateConfig.FIELD_PREFIX = GenerateConfig.FIELD_PREFIX_DEFAULT;
        }
        // 解析数据库字段数据
        List<Map<String, Object>> dataList = GenerateDataProcessing.getDataAnalysis(generateDto.getData());
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources/static,
        new DsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);

        // 生成html 的txt文件预览，html会被渲染成页面
        GenerateConfig.SUFFIX_JAVA_PT = GenerateConfig.SUFFIX_JAVA;
        GenerateConfig.SUFFIX_HTML_PT = GenerateConfig.SUFFIX_HTML;
        GenerateConfig.SUFFIX_XML_PT = GenerateConfig.SUFFIX_XML;
        // 文件生成，同时生成把生成的文件url路径保存到generationSeviceImplImp.lpathMap 参数
        generationSeviceImpl.buildEntity(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY);           // 生成Entity
        generationSeviceImpl.buildVO(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO);  //VO             // 生成Entity
        generationSeviceImpl.buildDTO(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO);  //DTO          // 生成Entity
        generationSeviceImpl.buildController(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER);     // 生成Controller
        generationSeviceImpl.buildService(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE);           // 生成service
        generationSeviceImpl.buildServiceImpl(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL);  // 生成serviceImpl
        generationSeviceImpl.buildMapper(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_MAPPER);              // 生成dao
        generationSeviceImpl.buildMapperXml(dataList, GenerateConfig.BASE_PATH_XML + GenerateConfig.PATH_MAPPER);            // 生成dao
        // html
        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/");
        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/");

        // 额外生成html 的txt文件预览，html会被渲染成页面
//        GenerateConfig.SUFFIX_HTML = GenerateConfig.SUFFIX_TXT;
//        generationSeviceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + DsField.TABLE_NAME_LOWER + "/");
//        generationSeviceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + DsField.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/" + GenerateConfig.PACK_PATH_ZP + "_" + GenerateConfig.MODULE_NAME + "_" + DsField.TABLE_NAME_LOWER + "_" + DsField.TABLE_NAME_LOWER);

        return R.success(GenerationSeviceImpl.pathMap);
    }


    /**
     * 代码生成路径查询(代码生成时前端确认生成路径无误后再生成代码)
     */
    @ApiOperation("代码生成路径")
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    public R<Map<String, String>> getPath(String tableName) {
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources/static,
        new DsField(tableName, null, GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);
        Map<String, String> mapPath = new HashMap<>();
        mapPath.put("entity", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY + DsField.TABLE_NAME_UP + ".java");
        mapPath.put("vo", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO + DsField.TABLE_NAME_UP + "VO.java");
        mapPath.put("dto", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO + DsField.TABLE_NAME_UP + "DTO.java");
        mapPath.put("controller", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER + DsField.TABLE_NAME_UP + "Controller.java");
        mapPath.put("service", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE + DsField.TABLE_NAME_UP + "Service.java");
        mapPath.put("serviceImpl", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL + DsField.TABLE_NAME_UP + "ServiceImpl.java");
        mapPath.put("mapper", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_MAPPER + DsField.TABLE_NAME_UP + "Mapper.java");
        mapPath.put("mapperXml", GenerateConfig.BASE_PATH_XML + GenerateConfig.PATH_MAPPER + "/" + DsField.TABLE_NAME_UP + "Mapper.xml");
        mapPath.put("htmlMain", GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/" + DsField.TABLE_NAME_LOWER + "Main.html");
        mapPath.put("htmlAdd", GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/" + DsField.TABLE_NAME_LOWER + "Add.html");
        mapPath.put("htmlUpd", GenerateConfig.BASE_PATH_HTML + DsField.TABLE_NAME_LOWER + "/" + DsField.TABLE_NAME_LOWER + "Upd.html");
        mapPath.put("index", "/page/" + GenerateConfig.PACK_PATH_ZP + "_" + GenerateConfig.MODULE_NAME + "_" + DsField.TABLE_NAME_LOWER + "_" + DsField.TABLE_NAME_LOWER);
        return R.success(mapPath);
    }
}
