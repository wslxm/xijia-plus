package com.ws.ldy.modules.sys.gc.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.gc.config.GenerateConfig;
import com.ws.ldy.modules.sys.gc.model.dto.XjGenerateDto;
import com.ws.ldy.modules.sys.gc.model.entity.XjAdminDatasource;
import com.ws.ldy.modules.sys.gc.service.XjAdminDatasourceService;
import com.ws.ldy.modules.sys.gc.service.impl.XjGenerationServiceImpl;
import com.ws.ldy.modules.sys.gc.util.GenerateDataProcessing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/generate")
@Api(value = "XjGenerateController", tags = "base-gc--代码生成")
public class XjGenerateController extends BaseController<XjGenerationServiceImpl> {


    @Autowired
    private XjGenerationServiceImpl generationServiceImpl;

    @Autowired
    private XjAdminDatasourceService adminDatasourceService;

    /**
     *  预览代码生成 (查询预览代码，预览代码存放于File/code/src.... 目录下，前端可直接访问)
     *
     * @param generateDto 传递收据
     * @return 预览文件URL地址
     * @date 2019/11/20 16:26
     */
    @ApiOperation("生成预览代码")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<Map<String, String>> preview(@RequestBody XjGenerateDto generateDto) {
        // 表前缀和字段前缀配置
        if (StringUtils.isNotBlank(generateDto.getDataSourceId())) {
            XjAdminDatasource datasource = adminDatasourceService.getById(generateDto.getDataSourceId());
            GenerateConfig.TABLE_PREFIX = datasource.getDbPrefix();
            GenerateConfig.FIELD_PREFIX = datasource.getDbFieldPrefix();
        } else {
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
        // 3、代码模板位置,resources,
        GenerateConfig.dsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);

        // 生成html 的txt文件预览，html会被渲染成页面
        GenerateConfig.SUFFIX_JAVA_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_HTML_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_XML_PT = GenerateConfig.SUFFIX_TXT;
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        generationServiceImpl.buildEntity(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_ENTITY);           // 生成Entity
        generationServiceImpl.buildVO(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_VO);  //VO             // 生成Entity
        generationServiceImpl.buildDTO(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_DTO);  //DTO          // 生成Entity
        generationServiceImpl.buildController(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_CONTROLLER);     // 生成Controller
        generationServiceImpl.buildService(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE);           // 生成service
        generationServiceImpl.buildServiceImpl(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE_IMPL);  // 生成serviceImpl
        generationServiceImpl.buildMapper(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_MAPPER);             // 生成dao
        generationServiceImpl.buildMapperXml(dataList, GenerateConfig.BASE_PATH_XML_YL);                                             // 生成xml
        // // html
        // generationServiceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        // generationServiceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        // generationServiceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        generationServiceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        generationServiceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        generationServiceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/"
                + GenerateConfig.ROOT_MODULE + "_"
                + GenerateConfig.PACK_PATH_ZP + "_"
                + GenerateConfig.MODULE_NAME + "_"
                + GenerateConfig.TABLE_NAME_LOWER + "_"
                + GenerateConfig.TABLE_NAME_LOWER);

        return R.success(XjGenerationServiceImpl.pathMap);
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
    public R<Map<String, String>> generateCode(@RequestBody XjGenerateDto generateDto) {
        // 表前缀和字段前缀配置
        if (StringUtils.isNotBlank(generateDto.getDataSourceId())) {
            XjAdminDatasource datasource = adminDatasourceService.getById(generateDto.getDataSourceId());
            GenerateConfig.TABLE_PREFIX = datasource.getDbPrefix();
            GenerateConfig.FIELD_PREFIX = datasource.getDbFieldPrefix();
        } else {
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
        // 3、代码模板位置,resources,
        GenerateConfig.dsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);

        // 生成html 的txt文件预览，html会被渲染成页面
        GenerateConfig.SUFFIX_JAVA_PT = GenerateConfig.SUFFIX_JAVA;
        GenerateConfig.SUFFIX_HTML_PT = GenerateConfig.SUFFIX_HTML;
        GenerateConfig.SUFFIX_XML_PT = GenerateConfig.SUFFIX_XML;
        // 文件生成，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        generationServiceImpl.buildEntity(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY);           // 生成Entity
        generationServiceImpl.buildVO(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO);  //VO             // 生成Entity
        generationServiceImpl.buildDTO(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO);  //DTO          // 生成Entity
        generationServiceImpl.buildController(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER);     // 生成Controller
        generationServiceImpl.buildService(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE);           // 生成service
        generationServiceImpl.buildServiceImpl(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL);  // 生成serviceImpl
        generationServiceImpl.buildMapper(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_MAPPER);             // 生成dao
        generationServiceImpl.buildMapperXml(dataList, GenerateConfig.BASE_PATH_XML);                                            // 生成xml
        // html
        generationServiceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/");
        generationServiceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/");
        generationServiceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/");

        // 额外生成html 的txt文件预览，html会被渲染成页面
        // GenerateConfig.SUFFIX_HTML = GenerateConfig.SUFFIX_TXT;
        // generationServiceImpl.buildMainHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + GenerateConfig.TABLE_NAME_LOWER + "/");
        // generationServiceImpl.buildAddHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + GenerateConfig.TABLE_NAME_LOWER + "/");
        // generationServiceImpl.buildUpdHtml(dataList, GenerateConfig.BASE_PATH_HTML_TXT + GenerateConfig.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/"
                + GenerateConfig.ROOT_MODULE + "_"
                + GenerateConfig.PACK_PATH_ZP + "_"
                + GenerateConfig.MODULE_NAME + "_"
                + GenerateConfig.TABLE_NAME_LOWER + "_"
                + GenerateConfig.TABLE_NAME_LOWER);

        return R.success(XjGenerationServiceImpl.pathMap);
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
        // 3、代码模板位置,resources,
        GenerateConfig.dsField(tableName, null, GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);
        Map<String, String> mapPath = new HashMap<>();
        mapPath.put("entity", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY + GenerateConfig.TABLE_NAME_UP + ".java");
        mapPath.put("vo", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO + GenerateConfig.TABLE_NAME_UP + "VO.java");
        mapPath.put("dto", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO + GenerateConfig.TABLE_NAME_UP + "DTO.java");
        mapPath.put("controller", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER + GenerateConfig.TABLE_NAME_UP + "Controller.java");
        mapPath.put("service", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE + GenerateConfig.TABLE_NAME_UP + "Service.java");
        mapPath.put("serviceImpl", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL + GenerateConfig.TABLE_NAME_UP + "ServiceImpl.java");
        mapPath.put("mapper", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_MAPPER + GenerateConfig.TABLE_NAME_UP + "Mapper.java");
        mapPath.put("mapperXml", GenerateConfig.BASE_PATH_XML + GenerateConfig.TABLE_NAME_UP + "Mapper.xml");
        mapPath.put("htmlMain", GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/" + GenerateConfig.TABLE_NAME_LOWER + "Main.html");
        mapPath.put("htmlAdd", GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/" + GenerateConfig.TABLE_NAME_LOWER + "Add.html");
        mapPath.put("htmlUpd", GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/" + GenerateConfig.TABLE_NAME_LOWER + "Upd.html");
        mapPath.put("index", "/page/"
                + GenerateConfig.ROOT_MODULE + "_"
                + GenerateConfig.PACK_PATH_ZP + "_"
                + GenerateConfig.MODULE_NAME + "_"
                + GenerateConfig.TABLE_NAME_LOWER + "_"
                + GenerateConfig.TABLE_NAME_LOWER);
        return R.success(mapPath);
    }
}
