package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
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
import io.github.wslxm.springbootplus2.manage.gc.constant.TpParamConstant;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gcimpl.*;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;
import io.github.wslxm.springbootplus2.manage.gc.util.GcReplacUtil;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.util.FileDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 *  @author wangsong
 */
@Service
@Slf4j
public class XjGenerationSeviceImpl extends BaseIServiceImpl implements XjGenerationSevice {

    @Autowired
    private XjAdminDatasourceService adminDatasourceService;
    @Autowired
    private GenerateProperties generateProperties;
    @Autowired
    private GcIteratorPattern gcIteratorPattern;


    @Override
    public Map<String, String> preview(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, true);
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.pathMap 参数
        gcIteratorPattern.run(gcConfig);
        return gcConfig.getVisitPathMap();
    }


    @Override
    public Map<String, String> generateCode(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, false, false);
        gcIteratorPattern.run(gcConfig);
        return gcConfig.getVisitPathMap();
    }


    @Override
    public void generateCodeVue(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, false);
        // 生成vue文件
        gcIteratorPattern.run(gcConfig);
        // 开始下载
        String basePath = "http://127.0.0.1:" + PropUtil.findByKey("server.port") + "/";
        List<String> paths = new ArrayList<>();
        paths.add(basePath + gcConfig.getVisitPathMap().get(XjGenerationVueMain.KEY_NAME));
        paths.add(basePath + gcConfig.getVisitPathMap().get(XjGenerationVueAdd.KEY_NAME));
        paths.add(basePath + gcConfig.getVisitPathMap().get(XjGenerationVueUpd.KEY_NAME));
        // 下载后的名字
        String zipName = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN);
        zipName = zipName.replaceAll("/", ".");
        // 下载
        FileDownloadUtil.downloadZip(paths, zipName, response);
    }


    @Override
    public void generateCodeJavaAndVue(XjGenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, false);
        // 生成vue+java文件
        gcIteratorPattern.run(gcConfig);
        // 开始下载
        // 1、获取需要被压缩的文件
        // 代码生成路径
        String projectName = gcConfig.getDefaultTemplateParam(TpParamConstant.PROJECT_NAME);
        String gcPath = GcTPConfig.PREVIEW_FILE_PATH + projectName;
        File srcFile = new File(gcPath);
        // 2、获取压缩后保存文件
        File zipFile = new File(GcTPConfig.PREVIEW_FILE_PATH + "gc/zipFile.zip");
        // 3、压缩
        File gcFile = ZipUtil.zip(zipFile, false, srcFile);
        // 4、下载
        String zipName = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_ZIP_NAME);
        zipName = zipName.replaceAll("/", ".") + ".zip";
        FileDownloadUtil.download(gcFile, zipName, response);
    }

    @Override
    public Map<String, String> getPath(String tableName, String dataSourceId) {
        // 获取配置参数
        XjGenerateDto generateDto = new XjGenerateDto();
        generateDto.setTableName(tableName);
        generateDto.setDataSourceId(dataSourceId);
        generateDto.setTableComment("预览生成路径");
        GcConfig gcConfig = getGcConfig(generateDto, false, false);
        // 获取路径
        Map<String, String> mapPath = new LinkedHashMap<>(16);
        mapPath.put("X-Vue-Menu", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN));
        mapPath.put(XjGenerationEntity.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationEntity.KEY_NAME).getPath());
        mapPath.put(XjGenerationVO.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationVO.KEY_NAME).getPath());
        mapPath.put(XjGenerationDTO.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationDTO.KEY_NAME).getPath());
        mapPath.put(XjGenerationQuery.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationQuery.KEY_NAME).getPath());
        mapPath.put(XjGenerationController.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationController.KEY_NAME).getPath());
        mapPath.put(XjGenerationService.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationService.KEY_NAME).getPath());
        mapPath.put(XjGenerationServiceImpl.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationServiceImpl.KEY_NAME).getPath());
        mapPath.put(XjGenerationMapper.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationMapper.KEY_NAME).getPath());
        mapPath.put(XjGenerationMapperXml.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationMapperXml.KEY_NAME).getPath());
        mapPath.put(XjGenerationVueMain.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationVueMain.KEY_NAME).getPath());
        mapPath.put(XjGenerationVueAdd.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationVueAdd.KEY_NAME).getPath());
        mapPath.put(XjGenerationVueUpd.KEY_NAME, gcConfig.getTemplatePathMap().get(XjGenerationVueUpd.KEY_NAME).getPath());
        return mapPath;
    }


    /**
     * 数据处理
     *
     * @param generateDto 前端请求数据
     * @param isPreview   是否为生成预览地址 (如果是 生成的代码将放入 File/ 目录下,如果不是生成到当前对应代码目录或配置的绝对路径中)
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

        // 处理数据相关
        if (generateDto.getData() != null) {
            // 处理请求的表指定数据
            gcConfig.setDbFields(GcDataUtil.getDataAnalysis(generateDto.getData()));
        }

        // 获取模板参数
        Map<String, String> defaultTemplateParam = getDefaultTemplateParam(gcConfig, generateDto.getDataSourceId(), generateDto.getTableName(), generateDto.getTableComment());
        gcConfig.setDefaultTemplateParam(defaultTemplateParam);

        // 生成位置
        // 判断是否生成预览，如果是预览文件将生成到指定目录下,
        // 判断是否生成预览，如果不是, 判断是否配置绝对生成路径，没有生成到当前项目下，配置了生成到指定磁盘目录
        String fatherPath = gcConfig.getDefaultTemplateParam(TpParamConstant.FATHER_PATH);
        String previewFile = isPreview ? GcTPConfig.PREVIEW_FILE_PATH : fatherPath + "/";


        // 处理模板地址和代码生成的地址配置 (模板名+模板地址+生成后的文件地址)
        gcConfig.addTemplate(XjGenerationEntity.KEY_NAME, baseUrl + GcTPConfig.T_ENTITY, previewFile + GcTPConfig.P_ENTITY);
        gcConfig.addTemplate(XjGenerationVO.KEY_NAME, baseUrl + GcTPConfig.T_VO, previewFile + GcTPConfig.P_VO);
        gcConfig.addTemplate(XjGenerationDTO.KEY_NAME, baseUrl + GcTPConfig.T_DTO, previewFile + GcTPConfig.P_DTO);
        gcConfig.addTemplate(XjGenerationQuery.KEY_NAME, baseUrl + GcTPConfig.T_QUERY, previewFile + GcTPConfig.P_QUERY);
        gcConfig.addTemplate(XjGenerationController.KEY_NAME, baseUrl + GcTPConfig.T_CONTROLLER, previewFile + GcTPConfig.P_CONTROLLER);
        gcConfig.addTemplate(XjGenerationService.KEY_NAME, baseUrl + GcTPConfig.T_SERVICE, previewFile + GcTPConfig.P_SERVICE);
        gcConfig.addTemplate(XjGenerationServiceImpl.KEY_NAME, baseUrl + GcTPConfig.T_SERVICEIMPL, previewFile + GcTPConfig.P_SERVICE_IMPL);
        gcConfig.addTemplate(XjGenerationMapper.KEY_NAME, baseUrl + GcTPConfig.T_MAPPER, previewFile + GcTPConfig.P_MAPPER);
        gcConfig.addTemplate(XjGenerationMapperXml.KEY_NAME, baseUrl + GcTPConfig.T_MAPPER_XML, previewFile + GcTPConfig.P_MAPPER_XML);
        gcConfig.addTemplate(XjGenerationVueMain.KEY_NAME, baseUrl + GcTPConfig.T_VUE, previewFile + GcTPConfig.P_VUE);
        gcConfig.addTemplate(XjGenerationVueAdd.KEY_NAME, baseUrl + GcTPConfig.T_VUE_ADD, previewFile + GcTPConfig.P_VUE_ADD);
        gcConfig.addTemplate(XjGenerationVueUpd.KEY_NAME, baseUrl + GcTPConfig.T_VUE_UPD, previewFile + GcTPConfig.P_VUE_UPD);

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

        try {
            // 每次生成前 清空该生成模块下原文件,不处理非改模块的其他代码，下载文件默认生成在预览文件目录
            String projectName = gcConfig.getDefaultTemplateParam(TpParamConstant.PROJECT_NAME);
            String gcPath = GcTPConfig.PREVIEW_FILE_PATH + projectName;
            File srcFile = new File(gcPath);
            FileUtil.del(srcFile);
        } catch (Exception e) {
            e.printStackTrace();
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
    private Map<String, String> getDefaultTemplateParam(GcConfig gcConfig, String dataSourceId, String tableName, String tableComment) {
        // 加载配置信息
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dataSourceId)) {
            XjAdminDatasourceVO dbConfig = adminDatasourceService.findId(dataSourceId);
            gcConfig.setDbDatasource(dbConfig);
            log.info(gcConfig.getDbDatasource().toString());
            // 数据数据库配置
            String packFilePath = dbConfig.getPackPath().replaceAll("\\.", "\\/");
            List<String> basefields = Arrays.asList(dbConfig.getBaseFields().split(","));
            List<String> keywordArray = Arrays.asList(dbConfig.getKeywordArray().split(","));
            List<String> vueFieldTypesArray = Arrays.asList(dbConfig.getVueFieldTypes().split(","));
            //
            gcConfig.setDefaultTemplateParam(TpParamConstant.AUTHOR, dbConfig.getAuthor());
            gcConfig.setDefaultTemplateParam(TpParamConstant.EMAIL, dbConfig.getEmail());
            gcConfig.setDefaultTemplateParam(TpParamConstant.DESCRIBE, dbConfig.getDescribe());
            gcConfig.setDefaultTemplateParam(TpParamConstant.PROJECT_NAME, dbConfig.getProjectName());
            gcConfig.setDefaultTemplateParam(TpParamConstant.PACK_PATH, dbConfig.getPackPath());
            gcConfig.setDefaultTemplateParam(TpParamConstant.ROOT_MODULE, dbConfig.getRootModule());
            gcConfig.setDefaultTemplateParam(TpParamConstant.MODULE_NAME, dbConfig.getModulesName());
            gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_PREFIX_DEFAULT, dbConfig.getDbTablePrefix());
            gcConfig.setDefaultTemplateParam(TpParamConstant.FIELD_PREFIX_DEFAULT, dbConfig.getDbFieldPrefix());
            gcConfig.setDefaultTemplateParam(TpParamConstant.ENTITY_SWAGGER, dbConfig.getEntitySwagger() + "");
            gcConfig.setDefaultTemplateParam(TpParamConstant.FILTER_CRUD, dbConfig.getFilterCrud() + "");
            gcConfig.setDefaultTemplateParam(TpParamConstant.FATHER_PATH, dbConfig.getFatherPath());
            gcConfig.setDefaultTemplateParam(TpParamConstant.VUE_FIELD_TYPES_ARRAY, JSON.toJSONString(vueFieldTypesArray));
            gcConfig.setDefaultTemplateParam(TpParamConstant.BASE_FIELDS, JSON.toJSONString(basefields));
            gcConfig.setDefaultTemplateParam(TpParamConstant.KEYWORD_ARRAY, JSON.toJSONString(keywordArray));
            gcConfig.setDefaultTemplateParam(TpParamConstant.PACK_FILE_PATH, packFilePath);
        } else {
            // 通过项目名+模板名获取包路径+ 获取通用字段和关键字
            log.info("当前配置信息：{}", generateProperties.toString());
            String packFilePath = generateProperties.getPackPath().replaceAll("\\.", "\\/");
            List<String> basefields = Arrays.asList(generateProperties.getBasefields().split(","));
            List<String> keywordArray = Arrays.asList(generateProperties.getKeywordArray().split(","));
            List<String> vueFieldTypesArray = Arrays.asList(generateProperties.getVueFieldTypes().split(","));
            //
            gcConfig.setDefaultTemplateParam(TpParamConstant.AUTHOR, generateProperties.getAuthor());
            gcConfig.setDefaultTemplateParam(TpParamConstant.EMAIL, generateProperties.getEmail());
            gcConfig.setDefaultTemplateParam(TpParamConstant.DESCRIBE, generateProperties.getDescribe());
            gcConfig.setDefaultTemplateParam(TpParamConstant.PROJECT_NAME, generateProperties.getProjectName());
            gcConfig.setDefaultTemplateParam(TpParamConstant.PACK_PATH, generateProperties.getPackPath());
            gcConfig.setDefaultTemplateParam(TpParamConstant.ROOT_MODULE, generateProperties.getRootModule());
            gcConfig.setDefaultTemplateParam(TpParamConstant.MODULE_NAME, generateProperties.getModuleName());
            gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_PREFIX_DEFAULT, generateProperties.getTablePrefixDefault());
            gcConfig.setDefaultTemplateParam(TpParamConstant.FIELD_PREFIX_DEFAULT, generateProperties.getFieldPrefixDefault());
            gcConfig.setDefaultTemplateParam(TpParamConstant.ENTITY_SWAGGER, generateProperties.getEntitySwagger() + "");
            gcConfig.setDefaultTemplateParam(TpParamConstant.FILTER_CRUD, generateProperties.getFilterCrud() + "");
            gcConfig.setDefaultTemplateParam(TpParamConstant.FATHER_PATH, generateProperties.getFatherPath());
            gcConfig.setDefaultTemplateParam(TpParamConstant.VUE_FIELD_TYPES_ARRAY, JSON.toJSONString(vueFieldTypesArray));
            gcConfig.setDefaultTemplateParam(TpParamConstant.BASE_FIELDS, JSON.toJSONString(basefields));
            gcConfig.setDefaultTemplateParam(TpParamConstant.KEYWORD_ARRAY, JSON.toJSONString(keywordArray));
            gcConfig.setDefaultTemplateParam(TpParamConstant.PACK_FILE_PATH, packFilePath);
        }
        gcConfig.setDefaultTemplateParam(TpParamConstant.DATE, LocalDateTimeUtil.parse(LocalDateTime.now()));


        /**
         * 根据数据库表名生成 驼峰格式的命名
         * tableNameUp = 大写开头  test_data --> TestData
         * tableNameUp = 小写开头 test_data --> testData
         * 如果yml 中配置了数据库前缀, 去除表前缀，如： t_test_data --> TestData
         */
        String tableNameUp = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String tableNameLower = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
        String tablePrefixDefault = gcConfig.getDefaultTemplateParam(TpParamConstant.TABLE_PREFIX_DEFAULT);
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

        // 加载数据库相关参数
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_NAME, tableName);
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_COMMENT, tableComment);
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_NAME_UP, tableNameUp);
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_NAME_LOWER, tableNameLower);
        return gcConfig.getDefaultTemplateParam();
    }
}
