package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.date.LocalDateTimeUtil;
import io.github.wslxm.springbootplus2.core.utils.PropUtil;
import io.github.wslxm.springbootplus2.file.util.FileDownloadUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GcTPConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateProperties;
import io.github.wslxm.springbootplus2.manage.gc.config.model.GcFilePath;
import io.github.wslxm.springbootplus2.manage.gc.constant.TpParamConstant;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.GenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.DatasourceVO;
import io.github.wslxm.springbootplus2.manage.gc.service.DatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.GenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl.*;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.pattern.GcIteratorPattern;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;
import io.github.wslxm.springbootplus2.manage.gc.util.GcReplacUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wangsong
 */
@Service
@Slf4j
public class XjGenerationSeviceImplI extends BaseServiceImpl implements GenerationSevice {

    @Autowired
    private DatasourceService adminDatasourceService;
    @Autowired
    private GenerateProperties generateProperties;
    @Autowired
    private GcIteratorPattern gcIteratorPattern;


    @Override
    public Map<String, String> preview(GenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, true);
        // ??????????????????(????????????????????????)?????????????????????????????????url???????????????generationServiceImplImp.pathMap ??????
        gcIteratorPattern.run(gcConfig);
        return gcConfig.getVisitPathMap();
    }


    @Override
    public Map<String, String> generateCode(GenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, false, false);
        gcIteratorPattern.run(gcConfig);
        return gcConfig.getVisitPathMap();
    }


    @Override
    public void generateCodeVue(GenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, false);
        // ??????vue??????
        gcIteratorPattern.run(gcConfig);
        // ????????????
        String basePath = "http://127.0.0.1:" + PropUtil.findByKey("server.port") + "/";
        List<String> paths = new ArrayList<>();
        paths.add(basePath + gcConfig.getVisitPathMap().get(GcVueMain.KEY_NAME));
        paths.add(basePath + gcConfig.getVisitPathMap().get(GcVueAdd.KEY_NAME));
        paths.add(basePath + gcConfig.getVisitPathMap().get(GcVueUpd.KEY_NAME));
        paths.add(basePath + gcConfig.getVisitPathMap().get(GcVuePid.KEY_NAME));
        // ??????????????????
        String zipName = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN);
        zipName = zipName.replaceAll("/", ".");
        // ??????
        FileDownloadUtil.downloadZip(paths, zipName, response);
    }


    @Override
    public void generateCodeJavaAndVue(GenerateDto generateDto) {
        GcConfig gcConfig = getGcConfig(generateDto, true, false);
        // ??????vue+java??????
        gcIteratorPattern.run(gcConfig);
        // ????????????
        // 1?????????????????????????????????
        // ??????????????????
        String projectName = gcConfig.getDefaultTemplateParam(TpParamConstant.PROJECT_NAME);
        String gcPath = GcTPConfig.PREVIEW_FILE_PATH + projectName;
        File srcFile = new File(gcPath);
        // 2??????????????????????????????
        File zipFile = new File(GcTPConfig.PREVIEW_FILE_PATH + "gc/zipFile.zip");
        // 3?????????
        File gcFile = ZipUtil.zip(zipFile, false, srcFile);
        // 4?????????
        String zipName = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_ZIP_NAME);
        zipName = zipName.replaceAll("/", ".") + ".zip";
        FileDownloadUtil.download(gcFile, zipName, response);
    }

    @Override
    public Map<String, String> getPath(String tableName, String dataSourceId) {
        // ??????????????????
        GenerateDto generateDto = new GenerateDto();
        generateDto.setTableName(tableName);
        generateDto.setDataSourceId(dataSourceId);
        generateDto.setTableComment("??????????????????");
        GcConfig gcConfig = getGcConfig(generateDto, false, false);
        // ????????????
        Map<String, String> mapPath = new LinkedHashMap<>(16);
        mapPath.put("X-Vue-Menu", GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), GcTPConfig.P_VUE_MEUN));
        mapPath.put(GcEntity.KEY_NAME, gcConfig.getTemplatePathMap().get(GcEntity.KEY_NAME).getPath());
        mapPath.put(GcVO.KEY_NAME, gcConfig.getTemplatePathMap().get(GcVO.KEY_NAME).getPath());
        mapPath.put(GcDTO.KEY_NAME, gcConfig.getTemplatePathMap().get(GcDTO.KEY_NAME).getPath());
        mapPath.put(GcQuery.KEY_NAME, gcConfig.getTemplatePathMap().get(GcQuery.KEY_NAME).getPath());
        mapPath.put(GcController.KEY_NAME, gcConfig.getTemplatePathMap().get(GcController.KEY_NAME).getPath());
        mapPath.put(GcIService.KEY_NAME, gcConfig.getTemplatePathMap().get(GcIService.KEY_NAME).getPath());
        mapPath.put(GcIServiceImpl.KEY_NAME, gcConfig.getTemplatePathMap().get(GcIServiceImpl.KEY_NAME).getPath());
        mapPath.put(GcMapper.KEY_NAME, gcConfig.getTemplatePathMap().get(GcMapper.KEY_NAME).getPath());
        mapPath.put(GcMapperXml.KEY_NAME, gcConfig.getTemplatePathMap().get(GcMapperXml.KEY_NAME).getPath());
        mapPath.put(GcVueMain.KEY_NAME, gcConfig.getTemplatePathMap().get(GcVueMain.KEY_NAME).getPath());
        mapPath.put(GcVueAdd.KEY_NAME, gcConfig.getTemplatePathMap().get(GcVueAdd.KEY_NAME).getPath());
        mapPath.put(GcVueUpd.KEY_NAME, gcConfig.getTemplatePathMap().get(GcVueUpd.KEY_NAME).getPath());
        mapPath.put(GcVuePid.KEY_NAME, gcConfig.getTemplatePathMap().get(GcVuePid.KEY_NAME).getPath());
        return mapPath;
    }


    /**
     * ????????????
     *
     * @param generateDto     ??????????????????
     * @param isPreview       ??????????????????????????? (????????? ???????????????????????? File/ ?????????,????????????????????????????????????????????????????????????????????????)
     * @param isPreviewSuffix ??????????????????  (????????? ???????????????????????????????????????????????? GcTPConfig.PREVIEW_SUFFIX ???????????????)
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2022/3/5 16:30
     */
    private GcConfig getGcConfig(GenerateDto generateDto, boolean isPreview, boolean isPreviewSuffix) {

        // ??????????????????
        GcConfig gcConfig = new GcConfig();
        gcConfig.setIsTree(generateDto.getIsTree());

        // ???????????????????????????
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");

        // ??????????????????
        if (generateDto.getData() != null) {
            // ??????????????????????????????
            gcConfig.setDbFields(GcDataUtil.getDataAnalysis(generateDto.getData()));
        }

        // ??????????????????
        Map<String, String> defaultTemplateParam = getDefaultTemplateParam(gcConfig, generateDto.getDataSourceId(), generateDto.getTableName(), generateDto.getTableComment());
        gcConfig.setDefaultTemplateParam(defaultTemplateParam);

        // ????????????
        // ????????????????????????,????????????
        String fatherPath = gcConfig.getDefaultTemplateParam(TpParamConstant.FATHER_PATH);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(fatherPath)) {
            fatherPath += "/";
        }
        // ???????????????????????????????????????????????????????????????????????????,
        // ???????????????????????????????????????, ????????????????????????????????????????????????????????????????????????????????????????????????????????????
        String previewFile = isPreview ? GcTPConfig.PREVIEW_FILE_PATH : fatherPath;

        // ???????????????????????????????????????????????? (?????????+????????????+????????????????????????)
        gcConfig.addTemplate(GcEntity.KEY_NAME, baseUrl + GcTPConfig.T_ENTITY, previewFile + GcTPConfig.P_ENTITY);
        gcConfig.addTemplate(GcVO.KEY_NAME, baseUrl + GcTPConfig.T_VO, previewFile + GcTPConfig.P_VO);
        gcConfig.addTemplate(GcDTO.KEY_NAME, baseUrl + GcTPConfig.T_DTO, previewFile + GcTPConfig.P_DTO);
        gcConfig.addTemplate(GcQuery.KEY_NAME, baseUrl + GcTPConfig.T_QUERY, previewFile + GcTPConfig.P_QUERY);
        gcConfig.addTemplate(GcController.KEY_NAME, baseUrl + GcTPConfig.T_CONTROLLER, previewFile + GcTPConfig.P_CONTROLLER);
        gcConfig.addTemplate(GcIService.KEY_NAME, baseUrl + GcTPConfig.T_SERVICE, previewFile + GcTPConfig.P_SERVICE);
        gcConfig.addTemplate(GcIServiceImpl.KEY_NAME, baseUrl + GcTPConfig.T_SERVICEIMPL, previewFile + GcTPConfig.P_SERVICE_IMPL);
        gcConfig.addTemplate(GcMapper.KEY_NAME, baseUrl + GcTPConfig.T_MAPPER, previewFile + GcTPConfig.P_MAPPER);
        gcConfig.addTemplate(GcMapperXml.KEY_NAME, baseUrl + GcTPConfig.T_MAPPER_XML, previewFile + GcTPConfig.P_MAPPER_XML);
        gcConfig.addTemplate(GcVueMain.KEY_NAME, baseUrl + GcTPConfig.T_VUE, previewFile + GcTPConfig.P_VUE);
        gcConfig.addTemplate(GcVueAdd.KEY_NAME, baseUrl + GcTPConfig.T_VUE_ADD, previewFile + GcTPConfig.P_VUE_ADD);
        gcConfig.addTemplate(GcVueUpd.KEY_NAME, baseUrl + GcTPConfig.T_VUE_UPD, previewFile + GcTPConfig.P_VUE_UPD);
        // ????????????????????????????????? (???????????????????????????tree????????????)
        if (generateDto.getIsTree()) {
            gcConfig.addTemplate(GcVO.KEY_NAME, baseUrl + GcTPConfig.T_TREE_VO, previewFile + GcTPConfig.P_VO);
            gcConfig.addTemplate(GcController.KEY_NAME, baseUrl + GcTPConfig.T_TREE_CONTROLLER, previewFile + GcTPConfig.P_CONTROLLER);
            gcConfig.addTemplate(GcIService.KEY_NAME, baseUrl + GcTPConfig.T_TREE_SERVICE, previewFile + GcTPConfig.P_SERVICE);
            gcConfig.addTemplate(GcIServiceImpl.KEY_NAME, baseUrl + GcTPConfig.T_TREE_SERVICEIMPL, previewFile + GcTPConfig.P_SERVICE_IMPL);
            gcConfig.addTemplate(GcVueMain.KEY_NAME, baseUrl + GcTPConfig.T_TREE_VUE, previewFile + GcTPConfig.P_VUE);
            gcConfig.addTemplate(GcVueAdd.KEY_NAME, baseUrl + GcTPConfig.T_TREE_VUE_ADD, previewFile + GcTPConfig.P_VUE_ADD);
            gcConfig.addTemplate(GcVuePid.KEY_NAME, baseUrl + GcTPConfig.T_TREE_VUE_PID, previewFile + GcTPConfig.P_VUE_PID);
        }
        // ???????????? ?????????????????????????????? ??? ???????????? ????????????
        for (GcFilePath gcFilePath : gcConfig.getTemplatePathMap().values()) {
            String path = GcReplacUtil.replaceParams(gcConfig.getDefaultTemplateParam(), gcConfig.getTemplateParam(), gcFilePath.getPath());
            if (isPreviewSuffix) {
                // ?????????????????????????????????, ????????????????????????
                path = path.substring(0, path.lastIndexOf("."));
                path += GcTPConfig.PREVIEW_SUFFIX;
            }
            gcFilePath.setPath(path);
        }

        try {
            // ??????????????? ?????????????????????????????????,????????????????????????????????????????????????????????????????????????????????????
            String projectName = gcConfig.getDefaultTemplateParam(TpParamConstant.PROJECT_NAME);
            String gcPath = GcTPConfig.PREVIEW_FILE_PATH + projectName;
            File srcFile = new File(gcPath);
            FileUtil.del(srcFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ??????????????????
        return gcConfig;
    }

    /**
     * ??????????????????
     * <p>
     * * 1???tableName;         // ???????????????????????????
     * * 2???tableComment;      // ?????????????????????
     * * 3???packName;          // ?????????????????????/?????? ??????java ????????????????????????: io.github.wslxm.baseadmin???
     * * 4???pathTp;            // ??????????????????
     *
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2022/3/5 16:41
     */
    private Map<String, String> getDefaultTemplateParam(GcConfig gcConfig, String dataSourceId, String tableName, String tableComment) {
        // ??????????????????
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dataSourceId)) {
            DatasourceVO dbConfig = adminDatasourceService.findId(dataSourceId);
            gcConfig.setDbDatasource(dbConfig);
            log.info(gcConfig.getDbDatasource().toString());
            // ?????????????????????
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
            // ???????????????+????????????????????????+ ??????????????????????????????
            log.info("?????????????????????{}", generateProperties.toString());
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
         * ??????????????????????????? ?????????????????????
         * tableNameUp = ????????????  test_data --> TestData
         * tableNameUp = ???????????? test_data --> testData
         * ??????yml ???????????????????????????, ???????????????????????? t_test_data --> TestData
         */
        String tableNameUp = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String tableNameLower = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
        String tablePrefixDefault = gcConfig.getDefaultTemplateParam(TpParamConstant.TABLE_PREFIX_DEFAULT);
        if (StringUtils.isNotBlank(tablePrefixDefault)) {
            // ????????????
            String prefix = tableName.substring(0, tablePrefixDefault.length());
            String newTableName = tableName;
            if (tablePrefixDefault.equals(prefix)) {
                // ????????????????????????????????? t_
                newTableName = tableName.substring(tablePrefixDefault.length());
            }
            tableNameUp = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, newTableName);
            tableNameLower = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, newTableName);
        }

        // ???????????????????????????
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_NAME, tableName);
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_COMMENT, tableComment);
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_NAME_UP, tableNameUp);
        gcConfig.setDefaultTemplateParam(TpParamConstant.TABLE_NAME_LOWER, tableNameLower);
        return gcConfig.getDefaultTemplateParam();
    }
}
