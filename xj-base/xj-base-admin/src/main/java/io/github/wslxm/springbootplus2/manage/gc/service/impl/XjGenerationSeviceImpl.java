package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.LocalDateTimeUtil;
import io.github.wslxm.springbootplus2.core.utils.PropUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GcTPConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateProperties;
import io.github.wslxm.springbootplus2.manage.gc.config.model.GcFilePath;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gcimpl.*;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;
import io.github.wslxm.springbootplus2.manage.gc.util.GcReplacUtil;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.util.FileDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 *  @author wangsong
 */
@Service
public class XjGenerationSeviceImpl extends BaseIServiceImpl implements XjGenerationSevice {

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
    @Autowired
    private GenerateProperties generateProperties;


    @Override
    public Map<String, String> preview(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, true);
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
        return gcConfig.getVisitPathMap();
    }


    @Override
    public Map<String, String> generateCode(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, false, false);
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
        return gcConfig.getVisitPathMap();
    }


    @Override
    public void generateCodeVue(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, false);
        // vue 生成的预览
        xjGenerationVueMain.run(gcConfig, "X-Vue");
        xjGenerationVueAdd.run(gcConfig, "X-VueAdd");
        xjGenerationVueUpd.run(gcConfig, "X-VueUpd");
        // 开始下载
        List<String> paths = new ArrayList<>();
        gcConfig.getVisitPathMap().forEach((k, v) -> paths.add("http://127.0.0.1:" + PropUtil.findByKey("server.port") + "/" + v));
        String zipName = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN);
        zipName = zipName.replaceAll("/", ".");
        FileDownloadUtil.downloadZip(paths, zipName, response);
    }


    @Override
    public void generateCodeJavaAndVue(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, false);

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
        // 开始下载
        List<String> paths = new ArrayList<>();
        gcConfig.getVisitPathMap().forEach((k, v) -> paths.add("http://127.0.0.1:" + PropUtil.findByKey("server.port") + "/" + v));
        String zipName = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN);
        zipName = zipName.replaceAll("/", ".");
        FileDownloadUtil.downloadZip(paths, zipName, response);
    }

    @Override
    public Map<String, String> getPath(String tableName) {
        // 获取配置参数
        XjGenerateDto generateDto = new XjGenerateDto();
        generateDto.setTableName(tableName);
        generateDto.setTableComment("预览生成路径");
        GcConfig gcConfig = getGcConfig(generateDto, false, false);
        // 获取路径
        Map<String, String> mapPath = new LinkedHashMap<>(16);
        mapPath.put("X-Vue-Menu", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN));
        mapPath.put("X-Entity", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_ENTITY));
        mapPath.put("X-VO", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VO));
        mapPath.put("X-DTO", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_DTO));
        mapPath.put("X-Query", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_QUERY));
        mapPath.put("X-Controller", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_CONTROLLER));
        mapPath.put("X-Service", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_SERVICE));
        mapPath.put("X-ServiceImpl", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_SERVICE_IMPL));
        mapPath.put("X-Mapper", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_MAPPER));
        mapPath.put("X-MapperXml", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_MAPPER_XML));
        mapPath.put("X-Vue", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE));
        mapPath.put("X-VueAdd", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_ADD));
        mapPath.put("X-VueUpd", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_UPD));
        return mapPath;
    }


    /**
     * 数据处理
     *
     * @param generateDto 前端请求数据
     * @param isPreview   是否为生成预览地址 (如果是 生成的代码将放入 File/ 目录下)
     * @param isPreviewSuffix  是否修改后缀  (如果是 所有生成的文件名后缀将统一修改为 GcTPConfig.PREVIEW_SUFFIX 配置的后缀)
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2022/3/5 16:30
     */
    private GcConfig getGcConfig(XjGenerateDto generateDto, boolean isPreview, boolean isPreviewSuffix) {
        // 配置数据容器
        GcConfig gcConfig = new GcConfig();
        // 获取当前服务器地址
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // 获取预览地址 (判断是否生成预览，如果是预览文件将生成到指定目录下, 否则将直接生成到项目对应目录中)
        String previewFile = isPreview ? GcTPConfig.PREVIEW_FILE_PATH : "";

        // 处理数据相关
        if (generateDto.getData() != null) {
            // 处理请求的表指定数据
            gcConfig.setDbFields(GcDataUtil.getDataAnalysis(generateDto.getData()));
        }

        // 获取模板参数
        gcConfig.setDefaultTemplateParam(getDefaultTemplateParam(gcConfig, generateDto.getTableName(), generateDto.getTableComment()));

        // 处理模板地址和代码生成的地址配置 (模板名+模板地址+生成后的文件地址)
        gcConfig.addTemplate("X-Entity", baseUrl + GcTPConfig.T_ENTITY, previewFile + GcTPConfig.P_ENTITY);
        gcConfig.addTemplate("X-VO", baseUrl + GcTPConfig.T_VO, previewFile + GcTPConfig.P_VO);
        gcConfig.addTemplate("X-DTO", baseUrl + GcTPConfig.T_DTO, previewFile + GcTPConfig.P_DTO);
        gcConfig.addTemplate("X-Query", baseUrl + GcTPConfig.T_QUERY, previewFile + GcTPConfig.P_QUERY);
        gcConfig.addTemplate("X-Controller", baseUrl + GcTPConfig.T_CONTROLLER, previewFile + GcTPConfig.P_CONTROLLER);
        gcConfig.addTemplate("X-Service", baseUrl + GcTPConfig.T_SERVICE, previewFile + GcTPConfig.P_SERVICE);
        gcConfig.addTemplate("X-ServiceImpl", baseUrl + GcTPConfig.T_SERVICEIMPL, previewFile + GcTPConfig.P_SERVICE_IMPL);
        gcConfig.addTemplate("X-Mapper", baseUrl + GcTPConfig.T_MAPPER, previewFile + GcTPConfig.P_MAPPER);
        gcConfig.addTemplate("X-MapperXml", baseUrl + GcTPConfig.T_MAPPER_XML, previewFile + GcTPConfig.P_MAPPER_XML);
        gcConfig.addTemplate("X-Vue", baseUrl + GcTPConfig.T_VUE, previewFile + GcTPConfig.P_VUE);
        gcConfig.addTemplate("X-VueAdd", baseUrl + GcTPConfig.T_VUE_ADD, previewFile + GcTPConfig.P_VUE_ADD);
        gcConfig.addTemplate("X-VueUpd", baseUrl + GcTPConfig.T_VUE_UPD, previewFile + GcTPConfig.P_VUE_UPD);

        // 生成地址 文件路径上的动态参数 和 文件后缀 进行处理
        for (GcFilePath gcFilePath : gcConfig.getTemplatePathMap().values()) {
            String path = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), gcFilePath.getPath());
            if (isPreviewSuffix) {
                // 判断是否为生成预览文件, 预览文件替换后缀
                path = path.substring(0, path.lastIndexOf("."));
                path += GcTPConfig.PREVIEW_SUFFIX;
            }
            gcFilePath.setPath(path);
        }

        // 返回配置对象
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
        List<String> vueFieldTypesArray = Arrays.asList(generateProperties.getVueFieldTypes().split(","));

        // 开始放置默认参数
        // 加载yml配置信息 到 默认参数(模板中可直接读取)
        gcConfig.setDefaultTemplateParam("author", generateProperties.getAuthor());
        gcConfig.setDefaultTemplateParam("email", generateProperties.getEmail());
        gcConfig.setDefaultTemplateParam("describe", generateProperties.getDescribe());
        gcConfig.setDefaultTemplateParam("date", LocalDateTimeUtil.parse(LocalDateTime.now()));
        gcConfig.setDefaultTemplateParam("projectName", generateProperties.getProjectName());
        gcConfig.setDefaultTemplateParam("packPath", generateProperties.getPackPath());
        gcConfig.setDefaultTemplateParam("rootModule", generateProperties.getRootModule());
        gcConfig.setDefaultTemplateParam("moduleName", generateProperties.getModuleName());
        gcConfig.setDefaultTemplateParam("tablePrefixDefault", generateProperties.getTablePrefixDefault());
        gcConfig.setDefaultTemplateParam("fieldPrefixDefault", generateProperties.getFieldPrefixDefault());
        gcConfig.setDefaultTemplateParam("entitySwagger", generateProperties.getEntitySwagger() + "");
        gcConfig.setDefaultTemplateParam("fatherPath", generateProperties.getFatherPath());
        gcConfig.setDefaultTemplateParam("vueFieldTypesArray", JSON.toJSONString(vueFieldTypesArray));
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
