package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.PropUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GcPathConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gcimpl.*;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import io.github.wslxm.springbootplus2.manage.gc.util.TemplateParamsReplace;
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
    private XjGenerationVueMain xjGenerationVueMain;
    @Autowired
    private XjGenerationVueAdd xjGenerationVueAdd;
    @Autowired
    private XjGenerationVueUpd xjGenerationVueUpd;

    @Autowired
    private GenerateConfig generateConfig;

    @Override
    public Map<String, String> preview(XjGenerateDto generateDto) {
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        GenerateConfig.dsField(generateDto.getTableName(),generateDto.getTableComment(), GenerateConfig.PACK_PATH,baseUrl + GcPathConfig.T_BASE_TEMPLATE);
        pathMap = new HashMap<>(16);
        // 处理数据
        List<DbFieldPO> dataList = getProcessingData(generateDto);
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        String previewFile = "File/";
        xjGenerationEntity.run(dataList, baseUrl + GcPathConfig.T_ENTITY, previewFile + GcPathConfig.P_ENTITY, GcPathConfig.SUFFIX_TXT);
        xjGenerationVO.run(dataList, baseUrl + GcPathConfig.T_VO, previewFile + GcPathConfig.P_VO, GcPathConfig.SUFFIX_TXT);
        xjGenerationDTO.run(dataList,baseUrl +  GcPathConfig.T_DTO, previewFile + GcPathConfig.P_DTO, GcPathConfig.SUFFIX_TXT);
        xjGenerationQuery.run(dataList,baseUrl + GcPathConfig.T_QUERY, previewFile + GcPathConfig.P_QUERY, GcPathConfig.SUFFIX_TXT);
        xjGenerationController.run(dataList, baseUrl + GcPathConfig.T_CONTROLLER, previewFile + GcPathConfig.P_CONTROLLER, GcPathConfig.SUFFIX_TXT);
        xjGenerationService.run(dataList, baseUrl + GcPathConfig.T_SERVICE, previewFile + GcPathConfig.P_SERVICE, GcPathConfig.SUFFIX_TXT);
        xjGenerationServiceImpl.run(dataList, baseUrl + GcPathConfig.T_SERVICEIMPL, previewFile + GcPathConfig.P_SERVICE_IMPL, GcPathConfig.SUFFIX_TXT);
        xjGenerationMapper.run(dataList, baseUrl + GcPathConfig.T_MAPPER, previewFile + GcPathConfig.P_MAPPER, GcPathConfig.SUFFIX_TXT);
        xjGenerationMapperXml.run(dataList, baseUrl + GcPathConfig.T_MAPPER_XML, previewFile + GcPathConfig.P_MAPPER_XML, GcPathConfig.SUFFIX_TXT);
        // vue 生成的预览
        xjGenerationVueMain.run(dataList,baseUrl +  GcPathConfig.T_VUE, previewFile + GcPathConfig.P_VUE, GcPathConfig.SUFFIX_TXT);
        xjGenerationVueAdd.run(dataList, baseUrl + GcPathConfig.T_VUE_ADD, previewFile + GcPathConfig.P_VUE_ADD, GcPathConfig.SUFFIX_TXT);
        xjGenerationVueUpd.run(dataList,baseUrl +  GcPathConfig.T_VUE_UPD, previewFile + GcPathConfig.P_VUE_UPD, GcPathConfig.SUFFIX_TXT);
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
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        GenerateConfig.dsField(generateDto.getTableName(),generateDto.getTableComment(), GenerateConfig.PACK_PATH,baseUrl + GcPathConfig.T_BASE_TEMPLATE);
        pathMap = new HashMap<>(16);
        // 处理数据
        List<DbFieldPO> dataList = getProcessingData(generateDto);

        // 文件生成，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        // 预览文件生成(服务器可访问文件)，同时生成把生成的文件url路径保存到generationServiceImplImp.lpathMap 参数
        xjGenerationEntity.run(dataList, GcPathConfig.T_ENTITY, GcPathConfig.P_ENTITY, GcPathConfig.SUFFIX_JAVA);
        xjGenerationVO.run(dataList, GcPathConfig.T_VO, GcPathConfig.P_VO, GcPathConfig.SUFFIX_JAVA);
        xjGenerationDTO.run(dataList, GcPathConfig.T_DTO, GcPathConfig.P_DTO, GcPathConfig.SUFFIX_JAVA);
        xjGenerationQuery.run(dataList, GcPathConfig.T_QUERY, GcPathConfig.P_QUERY, GcPathConfig.SUFFIX_JAVA);
        xjGenerationController.run(dataList, GcPathConfig.T_CONTROLLER, GcPathConfig.P_CONTROLLER, GcPathConfig.SUFFIX_JAVA);
        xjGenerationService.run(dataList, GcPathConfig.T_SERVICE, GcPathConfig.P_SERVICE, GcPathConfig.SUFFIX_JAVA);
        xjGenerationServiceImpl.run(dataList, GcPathConfig.T_SERVICEIMPL, GcPathConfig.P_SERVICE_IMPL, GcPathConfig.SUFFIX_JAVA);
        xjGenerationMapper.run(dataList, GcPathConfig.T_MAPPER, GcPathConfig.P_MAPPER, GcPathConfig.SUFFIX_JAVA);
        xjGenerationMapperXml.run(dataList, GcPathConfig.T_MAPPER_XML, GcPathConfig.P_MAPPER_XML, GcPathConfig.SUFFIX_XML);
        // vue 生成的预览
        xjGenerationVueMain.run(dataList, GcPathConfig.T_VUE, GcPathConfig.P_VUE, GcPathConfig.SUFFIX_VUE);
        xjGenerationVueAdd.run(dataList, GcPathConfig.T_VUE_ADD, GcPathConfig.P_VUE_ADD, GcPathConfig.SUFFIX_VUE);
        xjGenerationVueUpd.run(dataList, GcPathConfig.T_VUE_UPD, GcPathConfig.P_VUE_UPD, GcPathConfig.SUFFIX_VUE);
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
        pathMap = new HashMap<>(16);
        // 处理数据
        List<DbFieldPO> dataList = getProcessingData(generateDto);
        // vue 生成的预览
        xjGenerationVueMain.run(dataList, GcPathConfig.T_VUE, GcPathConfig.P_VUE, GcPathConfig.SUFFIX_VUE);
        xjGenerationVueAdd.run(dataList, GcPathConfig.T_VUE_ADD, GcPathConfig.P_VUE_ADD, GcPathConfig.SUFFIX_VUE);
        xjGenerationVueUpd.run(dataList, GcPathConfig.T_VUE_UPD, GcPathConfig.P_VUE_UPD, GcPathConfig.SUFFIX_VUE);
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
        GenerateConfig.dsField(tableName, null, GenerateConfig.PACK_PATH,baseUrl + GcPathConfig.T_BASE_TEMPLATE);
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
        GenerateConfig.dsField(generateDto.getTableName(), generateDto.getTableComment(), GenerateConfig.PACK_PATH, baseUrl + GcPathConfig.T_BASE_TEMPLATE);
        return dataList;
    }


}
