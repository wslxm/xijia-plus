package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.PropUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GcPathConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateProperties;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gcimpl.*;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private XjGenerationVueMain xjGenerationVueMain;
    @Autowired
    private XjGenerationVueAdd xjGenerationVueAdd;
    @Autowired
    private XjGenerationVueUpd xjGenerationVueUpd;

//    @Autowired
//    private GenerateConfig generateConfig;

    @Autowired
    private GenerateProperties generateProperties;


    @Override
    public Map<String, String> preview(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true);
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        xjGenerationEntity.run(gcConfig, "X-Entity");
        xjGenerationVO.run(gcConfig, "X-VO");
        xjGenerationDTO.run(gcConfig, "X-DTO");
        xjGenerationQuery.run(gcConfig, "X-Query");
        xjGenerationController.run(gcConfig, "X-Controller");
        xjGenerationService.run(gcConfig, "X-Service");
        xjGenerationServiceImpl.run(gcConfig, "X-ServiceImpl");
        xjGenerationMapper.run(gcConfig, "X-Mapper");
        xjGenerationMapperXml.run(gcConfig, "X-MapperXml");
        // vue 生成的预览
        xjGenerationVueMain.run(gcConfig, "X-Vue");
        xjGenerationVueAdd.run(gcConfig, "X-VueAdd");
        xjGenerationVueUpd.run(gcConfig, "X-VueUpd");
        return pathMap;
    }


    @Override
    public Map<String, String> generateCode(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, false);
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        xjGenerationEntity.run(gcConfig, "X-Entity");
        xjGenerationVO.run(gcConfig, "X-VO");
        xjGenerationDTO.run(gcConfig, "X-DTO");
        xjGenerationQuery.run(gcConfig, "X-Query");
        xjGenerationController.run(gcConfig, "X-Controller");
        xjGenerationService.run(gcConfig, "X-Service");
        xjGenerationServiceImpl.run(gcConfig, "X-ServiceImpl");
        xjGenerationMapper.run(gcConfig, "X-Mapper");
        xjGenerationMapperXml.run(gcConfig, "X-MapperXml");
        // vue 生成的预览
        xjGenerationVueMain.run(gcConfig, "X-Vue");
        xjGenerationVueAdd.run(gcConfig, "X-VueAdd");
        xjGenerationVueUpd.run(gcConfig, "X-VueUpd");
        return pathMap;
    }


    @Override
    public void generateCodeVue(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true);
        // vue 生成的预览
        xjGenerationVueMain.run(gcConfig, "X-Vue");
        xjGenerationVueAdd.run(gcConfig, "X-VueAdd");
        xjGenerationVueUpd.run(gcConfig, "X-VueUpd");
        // 开始下载
        List<String> paths = new ArrayList<>();
        pathMap.forEach((k, v) -> paths.add("http://127.0.0.1:" + PropUtil.findByKey("server.port") + "/" + v));
//        String zipName = GenerateConfig.ROOT_MODULE + "."
//                + GenerateConfig.MODULE_NAME + "."
//                + GenerateConfig.TABLE_NAME_LOWER + "."
//                + GenerateConfig.TABLE_NAME_LOWER;
        //  FileDownloadUtil.downloadZip(paths, zipName, response);
    }


    @Override
    public Map<String, String> getPath(String tableName) {
        // 请求地址，去除接口名
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 添加代码生成相关通用数据,
        // 1、包路径
        // 2、数据库的表名称
        // 3、代码模板位置,resources,
        // GenerateConfig.dsField(tableName, null, GenerateConfig.PACK_PATH,baseUrl + GcPathConfig.T_BASE_TEMPLATE);
        Map<String, String> mapPath = new HashMap<>(16);
        mapPath.put("entity", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_ENTITY));
        mapPath.put("vo", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_VO));
        mapPath.put("dto", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_DTO));
        mapPath.put("query", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_QUERY));
        mapPath.put("controller", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_CONTROLLER));
        mapPath.put("service", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_SERVICE));
        mapPath.put("serviceImpl", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_SERVICE_IMPL));
        mapPath.put("mapper", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_MAPPER));
        mapPath.put("mapperXml", GenerateDataProcessing.replacParamsPath(GcPathConfig.P_MAPPER_XML));
//        mapPath.put("vueIndex", "/views/"
//                + GenerateConfig.ROOT_MODULE + "/"
//                + GenerateConfig.MODULE_NAME + "/"
//                + GenerateConfig.TABLE_NAME_LOWER + "/"
//                + GenerateConfig.TABLE_NAME_LOWER);
        return mapPath;
    }



    /**
     * 数据处理
     *
     * @param generateDto 前端请求数据
     * @param isPreview   是否为生成预览地址
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2022/3/5 16:30
     */
    private GcConfig getGcConfig(XjGenerateDto generateDto, boolean isPreview) {
        // 数据容器
        GcConfig gcConfig = new GcConfig();

        // 获取当前服务器地址
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 获取预览地址(判断是否生成预览)
        String previewFile = isPreview ? GcPathConfig.PREVIEW_FILE_PATH : "";

        // 处理模板地址和代码生成的地址
        gcConfig.addTemplate("X-Entity", baseUrl + GcPathConfig.T_ENTITY, previewFile + GcPathConfig.P_ENTITY);
        gcConfig.addTemplate("X-VO", baseUrl + GcPathConfig.T_VO, previewFile + GcPathConfig.P_VO);
        gcConfig.addTemplate("X-DTO", baseUrl + GcPathConfig.T_DTO, previewFile + GcPathConfig.P_DTO);
        gcConfig.addTemplate("X-Query", baseUrl + GcPathConfig.T_QUERY, previewFile + GcPathConfig.P_QUERY);
        gcConfig.addTemplate("X-Controller", baseUrl + GcPathConfig.T_CONTROLLER, previewFile + GcPathConfig.P_CONTROLLER);
        gcConfig.addTemplate("X-Service", baseUrl + GcPathConfig.T_SERVICE, previewFile + GcPathConfig.P_SERVICE);
        gcConfig.addTemplate("X-ServiceImpl", baseUrl + GcPathConfig.T_SERVICEIMPL, previewFile + GcPathConfig.P_SERVICE_IMPL);
        gcConfig.addTemplate("X-Mapper", baseUrl + GcPathConfig.T_MAPPER, previewFile + GcPathConfig.P_MAPPER);
        gcConfig.addTemplate("X-MapperXml", baseUrl + GcPathConfig.T_MAPPER_XML, previewFile + GcPathConfig.P_MAPPER_XML);
        gcConfig.addTemplate("X-Vue", baseUrl + GcPathConfig.T_VUE, previewFile + GcPathConfig.P_VUE);
        gcConfig.addTemplate("X-VueAdd", baseUrl + GcPathConfig.T_VUE_ADD, previewFile + GcPathConfig.P_VUE_ADD);
        gcConfig.addTemplate("X-VueUpd", baseUrl + GcPathConfig.T_VUE_UPD, previewFile + GcPathConfig.P_VUE_UPD);
        // 处理请求的表指定数据
        gcConfig.setDbFields(GenerateDataProcessing.getDataAnalysis(generateDto.getData()));
        // 获取模板参数
        gcConfig.setDefaultTemplateParam(getDefaultTemplateParam(gcConfig, generateDto.getTableName(), generateDto.getTableComment()));
        // 菜单生成路径
//        System.err.println("预览代码成功生成到File/code/目录下,请查看, 菜单路径: + /page/"
//                + GenerateConfig.ROOT_MODULE + "_"
//                //+ GenerateConfig.PACK_PATH_ZP + "_"
//                + GenerateConfig.MODULE_NAME + "_"
//                + GenerateConfig.TABLE_NAME_LOWER + "_"
//                + GenerateConfig.TABLE_NAME_LOWER);
        return gcConfig;
    }

    /**
     * 处理默认参数
     * <p>
     * * 1、tableName;         // 数据库表的实际名称
     * * 2、tableComment;      // 数据库表的注释
     * * 3、packName;          // 生成代码的包名/路径 （从java 目录开始，如当前: io.github.wslxm.baseadmin）
     * * 4、pathTp;            // 代码模板路径
     *
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2022/3/5 16:41
     */
    private Map<String, String> getDefaultTemplateParam(GcConfig gcConfig, String tableName, String tableComment) {

        /**
         * 根据数据库表名生成 驼峰格式的命名
         * tableNameUp = 大写开头  test_data --> TestData
         * tableNameUp = 小写开头 test_data --> testData
         * 如果yml 中配置了数据库前缀, 去除表前缀，如： t_test_data --> TestData
         */
        String tableNameUp = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String tableNameLower = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
        String tablePrefixDefault = generateProperties.getTablePrefixDefault();
        if (StringUtils.isNotBlank(tablePrefixDefault)) {
            // 获取前缀
            String prefix = tableName.substring(0, tablePrefixDefault.length());
            String newTableName = tableName;
            if (tablePrefixDefault.equals(prefix)) {
                // 去掉数据库表的前缀，如 t_
                newTableName = tableName.substring(tablePrefixDefault.length());
            }
            tableNameUp = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, newTableName);
            tableNameLower = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, newTableName);
        }
        /**
         * 通过项目名+模板名获取包路径
         */
        String packFilePath = generateProperties.getPackPath().replaceAll("\\.", "\\/");

        /**
         * 获取通用字段和关键字
         */
        List<String> basefields = Arrays.asList(generateProperties.getBasefields().split(","));
        List<String> keywordArray = Arrays.asList(generateProperties.getKeywordArray().split(","));

        // 开始放置参数
        // 加载yml配置信息 到 默认参数(模板中可直接读取)
        gcConfig.setDefaultTemplateParam("author", generateProperties.getAuthor());
        gcConfig.setDefaultTemplateParam("email", generateProperties.getEmail());
        gcConfig.setDefaultTemplateParam("describe", generateProperties.getDescribe());
        gcConfig.setDefaultTemplateParam("projectName", generateProperties.getProjectName());
        gcConfig.setDefaultTemplateParam("packPath", generateProperties.getPackPath());
        gcConfig.setDefaultTemplateParam("rootModule", generateProperties.getRootModule());
        gcConfig.setDefaultTemplateParam("moduleName", generateProperties.getModuleName());
        gcConfig.setDefaultTemplateParam("tablePrefixDefault", generateProperties.getTablePrefixDefault());
        gcConfig.setDefaultTemplateParam("fieldPrefixDefault", generateProperties.getFieldPrefixDefault());
        gcConfig.setDefaultTemplateParam("entitySwagger", generateProperties.getEntitySwagger() + "");
        gcConfig.setDefaultTemplateParam("fatherPath", generateProperties.getFatherPath());
        gcConfig.setDefaultTemplateParam("basefields", JSON.toJSONString(basefields));
        gcConfig.setDefaultTemplateParam("keywordArray", JSON.toJSONString(keywordArray));
        // 加载数据库相关参数
        gcConfig.setDefaultTemplateParam("tableName", tableName);
        gcConfig.setDefaultTemplateParam("tableComment", tableComment);
        gcConfig.setDefaultTemplateParam("tableNameUp", tableNameUp);
        gcConfig.setDefaultTemplateParam("tableNameLower", tableNameLower);
        // 加载文件路径相关参数
        gcConfig.setDefaultTemplateParam("packFilePath", packFilePath);
        return gcConfig.getDefaultTemplateParam();
    }
}
