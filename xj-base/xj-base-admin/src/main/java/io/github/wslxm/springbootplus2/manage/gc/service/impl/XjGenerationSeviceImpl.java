package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.PropUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gcimpl.*;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.util.FileDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author wangsong
 */
@Service
public class XjGenerationSeviceImpl extends BaseIServiceImpl implements XjGenerationSevice {

    /**
     * 保存预览文件返回的文件地址url
     */
    public static Map<String, String> pathMap = new HashMap<>();

    @Autowired
    private XjAdminDatasourceService adminDatasourceService;
    @Autowired
    private XjGenerationEntity xjGenerationEntity;
    @Autowired
    private XjGenerationDTO xjGenerationDTO;
    @Autowired
    private XjGenerationVO xjGenerationVO;
    @Autowired
    private XjGenerationQuery xjGenerationQuery;
    @Autowired
    private XjGenerationMapper xjGenerationMapper;
    @Autowired
    private XjGenerationMapperXml xjGenerationMapperXml;
    @Autowired
    private XjGenerationService xjGenerationService;
    @Autowired
    private XjGenerationServiceImpl xjGenerationServiceImpl;
    @Autowired
    private XjGenerationController xjGenerationController;
    @Autowired
    private XjGenerationHtmlMain xjGenerationHtmlMain;
    @Autowired
    private XjGenerationHtmlAdd xjGenerationHtmlAdd;
    @Autowired
    private XjGenerationHtmlUpd xjGenerationHtmlUpd;
    @Autowired
    private XjGenerationVueMain xjGenerationVueMain;
    @Autowired
    private XjGenerationVueAdd xjGenerationVueAdd;
    @Autowired
    private XjGenerationVueUpd xjGenerationVueUpd;

    @Override
    public Map<String, String> preview(XjGenerateDto generateDto) {
        pathMap = new HashMap<>();
        // 处理数据
        List<DbFieldPO> dataList = getProcessingData(generateDto);
        // 预览生成的文件为txt格式,方便预览
        GenerateConfig.SUFFIX_JAVA_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_HTML_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_VUE_PT = GenerateConfig.SUFFIX_TXT;
        GenerateConfig.SUFFIX_XML_PT = GenerateConfig.SUFFIX_TXT;
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        xjGenerationEntity.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_ENTITY);
        xjGenerationVO.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_VO);
        xjGenerationDTO.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_DTO);
        xjGenerationQuery.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_Query);
        xjGenerationController.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_CONTROLLER);
        xjGenerationService.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE);
        xjGenerationServiceImpl.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_SERVICE_IMPL);
        xjGenerationMapper.run(dataList, GenerateConfig.BASE_PATH_JAVA_YL + GenerateConfig.PATH_MAPPER);
        xjGenerationMapperXml.run(dataList, GenerateConfig.BASE_PATH_XML_YL);
        // html, 生成的预览 html为txt 文件，html文件会被渲染成页面
        xjGenerationHtmlMain.run(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationHtmlAdd.run(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationHtmlUpd.run(dataList, GenerateConfig.BASE_PATH_HTML_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        // vue 生成的预览
        xjGenerationVueMain.run(dataList, GenerateConfig.BASE_PATH_VUE_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationVueAdd.run(dataList, GenerateConfig.BASE_PATH_VUE_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationVueUpd.run(dataList, GenerateConfig.BASE_PATH_VUE_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        System.err.println("预览代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/"
                + GenerateConfig.ROOT_MODULE + "_"
                //+ GenerateConfig.PACK_PATH_ZP + "_"
                + GenerateConfig.MODULE_NAME + "_"
                + GenerateConfig.TABLE_NAME_LOWER + "_"
                + GenerateConfig.TABLE_NAME_LOWER);
        return pathMap;
    }


    @Override
    public Map<String, String> generateCode(XjGenerateDto generateDto) {
        pathMap = new HashMap<>();
        // 处理数据
        List<DbFieldPO> dataList = getProcessingData(generateDto);
        // 生成的文件为对应代码格式,直接生成到目录
        GenerateConfig.SUFFIX_JAVA_PT = GenerateConfig.SUFFIX_JAVA;
        GenerateConfig.SUFFIX_VUE_PT = GenerateConfig.SUFFIX_VUE;
        GenerateConfig.SUFFIX_HTML_PT = GenerateConfig.SUFFIX_HTML;
        GenerateConfig.SUFFIX_XML_PT = GenerateConfig.SUFFIX_XML;
        // 文件生成，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        xjGenerationEntity.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_ENTITY);
        xjGenerationVO.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_VO);
        xjGenerationDTO.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO);
        xjGenerationQuery.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_Query);
        xjGenerationController.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_CONTROLLER);
        xjGenerationService.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE);
        xjGenerationServiceImpl.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_SERVICE_IMPL);
        xjGenerationMapper.run(dataList, GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_MAPPER);
        xjGenerationMapperXml.run(dataList, GenerateConfig.BASE_PATH_XML);
        // html
        xjGenerationHtmlMain.run(dataList, GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationHtmlAdd.run(dataList, GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationHtmlUpd.run(dataList, GenerateConfig.BASE_PATH_HTML + GenerateConfig.TABLE_NAME_LOWER + "/");
        System.err.println("代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/"
                + GenerateConfig.ROOT_MODULE + "_"
                // + GenerateConfig.PACK_PATH_ZP + "_"
                + GenerateConfig.MODULE_NAME + "_"
                + GenerateConfig.TABLE_NAME_LOWER + "_"
                + GenerateConfig.TABLE_NAME_LOWER);
        return pathMap;
    }


    @Override
    public void generateCodeVue(XjGenerateDto generateDto) {
        pathMap = new HashMap<>();
        // 处理数据
        List<DbFieldPO> dataList = getProcessingData(generateDto);
        GenerateConfig.SUFFIX_VUE_PT = GenerateConfig.SUFFIX_VUE;
        // vue 生成的预览
        xjGenerationVueMain.run(dataList, GenerateConfig.BASE_PATH_VUE_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationVueAdd.run(dataList, GenerateConfig.BASE_PATH_VUE_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        xjGenerationVueUpd.run(dataList, GenerateConfig.BASE_PATH_VUE_TXT_YL + GenerateConfig.TABLE_NAME_LOWER + "/");
        // 开始下载
        List<String> paths = new ArrayList<>();
        pathMap.forEach((k, v) -> paths.add("http://127.0.0.1:" + PropUtil.findByKey("server.port") + "/" + v));
        String zipName = GenerateConfig.ROOT_MODULE + "."
                + GenerateConfig.MODULE_NAME + "."
                + GenerateConfig.TABLE_NAME_LOWER + "."
                + GenerateConfig.TABLE_NAME_LOWER;
        FileDownloadUtil.downloadZip(paths, zipName, response);
    }


    @Override
    public Map<String, String> getPath(String tableName) {
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
        mapPath.put("query", GenerateConfig.BASE_PATH_JAVA + GenerateConfig.PATH_DTO + GenerateConfig.TABLE_NAME_UP + "Query.java");
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
                // + GenerateConfig.PACK_PATH_ZP + "_"
                + GenerateConfig.MODULE_NAME + "_"
                + GenerateConfig.TABLE_NAME_LOWER + "_"
                + GenerateConfig.TABLE_NAME_LOWER);
        mapPath.put("vueIndex", "/views/"
                + GenerateConfig.ROOT_MODULE + "/"
                + GenerateConfig.MODULE_NAME + "/"
                + GenerateConfig.TABLE_NAME_LOWER + "/"
                + GenerateConfig.TABLE_NAME_LOWER);
        return mapPath;
    }


    /**
     * 数据处理并返回
     *
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2021/10/26 0026 1:52
     * @version 1.0.0
     */
    private List<DbFieldPO> getProcessingData(XjGenerateDto generateDto) {
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
        List<DbFieldPO> dataList = GenerateDataProcessing.getDataAnalysis(generateDto.getData());
        // 获取地址,用于读取模板位置 (请求地址，去除接口名）
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 初始化代码生成相关通用数据,如： 1、包路径 2、数据库的表名称 3、代码模板位置,resources,
        GenerateConfig.dsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GenerateConfig.PATH_TEMPLATE);
        return dataList;
    }
}
