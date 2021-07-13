package com.ws.ldy.modules.sys.gc.service.gcimpl;

import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.gc.config.GenerateConfig;
import com.ws.ldy.modules.sys.gc.controller.XjGenerateController;
import com.ws.ldy.modules.sys.gc.service.XjGenerationSevice;
import com.ws.ldy.modules.sys.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationController extends BaseIServiceImpl implements XjGenerationSevice {


    /**
     * 生成Service层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Controller");
        // 参数拼接(所有字段)
        StringBuffer findPageParam = new StringBuffer(" ");//添加一个空，防止没有条件时空指针异常
        // MybatisPlus搜索条件数据拼接
        StringBuffer findPageMybatisPlus = new StringBuffer(" ");
        // swagger注释信息拼接
        StringBuffer swaggerRemark = new StringBuffer(" ");

        // 处理参数
        for (Map<String, Object> fieldMap : data) {
            String fieldName = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            String desc = fieldMap.get("desc").toString();
            Object search = fieldMap.get("search");
            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            swaggerRemark.append("           @ApiImplicitParam(name = \"" + fieldName + "\", value = \"" + desc + "\", required = false, paramType = \"query\",example = \"\"),\r\n");
            //每个字段前内容
            //findPageParam.append("            @ApiParam(value = \"" + desc + "\",required = false) @RequestParam(required = false) ");
            findPageParam.append("\r\n                                            @RequestParam(required = false) ");
            //首字母大写
            String fieldNameUp = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            //字段
            if (type.equals("int")) {    // !=null StringUtils.isNotBlank(account)
                //整数int
                //方法参数
                findPageParam.append("Integer " + fieldName + ",");
                //mybatis-plus 参数
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("bigint")) {
                //整数Long
                findPageParam.append("Long " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("varchar") || type.equals("char")) {
                //字符串
                findPageParam.append("String " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(StringUtils.isNotBlank(" + fieldName + ")," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("text") || type.equals("longtext")) {
                //大文本、超大文本
                findPageParam.append("String " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(StringUtils.isNotBlank(" + fieldName + ")," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                //时间
                findPageParam.append("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\") LocalDateTime " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("double")) {
                //双精度小数 Double
                findPageParam.append("Double " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("float")) {
                //单精度小数 Float
                findPageParam.append("Float " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("decimal")) {
                //小数 decimal
                findPageParam.append("BigDecimal " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            }
            findPageMybatisPlus.append("\r\n");
        }
        // log.debug(findPageParam.substring(0, findPageParam.length() - 1));
        // log.debug(findPageMybatisPlus.toString()); //
        GenerateConfig.FIND_PAGE_PARAM = findPageParam.substring(0, findPageParam.length() - 1);
        GenerateConfig.FIND_PAGE_MYBATIS_PLUS = findPageMybatisPlus.toString();
        GenerateConfig.SWAGGER_REMARK = swaggerRemark.toString();

        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerateController.pathMap.put("controller", brBwPath.get("path").toString());
    }
}
