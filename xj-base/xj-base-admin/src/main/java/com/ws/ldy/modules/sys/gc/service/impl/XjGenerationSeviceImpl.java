package com.ws.ldy.modules.sys.gc.service.impl;

import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.gc.config.DsField;
import com.ws.ldy.modules.sys.gc.config.GenerateConfig;
import com.ws.ldy.modules.sys.gc.service.XjGenerationSevice;
import com.ws.ldy.modules.sys.gc.template.*;
import com.ws.ldy.modules.sys.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationSeviceImpl extends BaseIServiceImpl implements XjGenerationSevice {

    /**
     * 保存预览文件返回的文件地址url
     */
    public static Map<String, String> pathMap = new HashMap<>();


    /***
     * 生成实体类
     *
     * @param data  数据
     * @param this.TABLE_NAME 数据库表名
     * @param path      生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    @Override
    public void buildEntity(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            // 字段名称
            String fieldName = fieldMap.get("name").toString();
            // 字段类型日4
            String type = fieldMap.get("type").toString();
            if (GenerateConfig.entitySwagger) {
                // 字段注释信息-->  Swagger2 模式
                fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            } else {
                // 字段注释信息-->  doc 注释
                fields.append("\r\n    /** \r\n     * " + fieldMap.get("desc") + " \r\n     */");
            }
            // 字段名为id的 添加id主键注解, int 默认自增, bigint+varchar 默认雪花算法
            if ("id".equals(fieldName)) {
                // id生成策略
                if (type.equals("int")) {
                    fields.append("\r\n    @TableId(type = IdType.AUTO) //自增");
                } else if (type.equals("bigint")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                } else if (type.equals("varchar") || type.equals("char")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                }
            }
            // 字段对应数据库字段 ==> 处理 添加mysql 关键字映射，mysql关键字配置: GenerateConfig.KEYWORD_ARRAY
            if (Arrays.asList(GenerateConfig.KEYWORD_ARRAY).contains(fieldName)) {
                fields.append("\r\n    @TableField(value = \"`" + fieldName + "`\")");
            } else {
                fields.append("\r\n    @TableField(value = \"" + fieldName + "\")");
            }
            // 字段,转为驼峰模式
            fieldName = GenerateDataProcessing.getFieldName(fieldName);
            //字段
            JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        DsField.FIELD_ENTITYS = fields.toString();
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        pathMap.put("entity", brBwPath.get("path").toString());
    }

    @Override
    public void buildDTO(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "DTO");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String fieldName = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            String isNull = fieldMap.get("isNull").toString();
            //
            String typeDetail = fieldMap.get("typeDetail").toString();
            /**
             *   必填字段添加 jsr303验证 -->   NO 代表必填,YES 非必填
             */
            if (("NO").equals(isNull)) {
                //字段
                if (type.equals("int")
                        || type.equals("bigint")) {
                    /**
                     * 整数
                     */
                    // 获取数据库注释
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    // 2 int(11)
                    if (typeDetail.indexOf("(") != -1) {
                        int length = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")")));
                        Double max = (Math.pow(10, length) - 1);
                        if (type.equals("int")) {
                            fields.append("\r\n" + "    @Range(min=0, max=" + new BigDecimal(max.toString()).intValue() + "L"
                                    + ",message = \"" + desc + " 必须>=0 和 <=" + new BigDecimal(max.toString()).intValue()
                                    + "\")");
                        } else {
                            fields.append("\r\n" + "    @Range(min=0, max=" + new BigDecimal(max.toString()).longValue() + "L"
                                    + ",message = \"" + desc + " 必须>=0 和 <=" + new BigDecimal(max.toString()).longValue()
                                    + "\")");

                        }
                    }
                } else if (type.equals("double")
                        || type.equals("float")
                        || type.equals("decimal")
                        || type.equals("float")) {//小数 decimal){
                    /**
                     * 小数
                     */
                    //1
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    //   fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空 \")");
                    //2 decimal(10,2)
                    if (typeDetail.indexOf("(") != -1) {
                        int length = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(",")));
                        Double max = Math.pow(10, length) - 1;
                        fields.append("\r\n" + "    @DecimalMin(value = \"0\""
                                + ",message = \"" + desc + " 必须>=0"
                                + "\")");
                        fields.append("\r\n" + "    @DecimalMax(value = \"" + new BigDecimal(max.toString()).longValue() + "\""
                                + ",message = \"" + desc + " 必须<=" + new BigDecimal(max.toString()).longValue()
                                + "\")");
                    }
                } else if (type.equals("varchar")
                        || type.equals("char")
                        || type.equals("text")
                        || type.equals("longtext")) {
                    /**
                     * 字符串
                     */
                    //1 varchar(32)
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    //   fields.append("\r\n" + "    @NotBlank(message = \"" + desc + " 不能为空\")");
                    //2
                    if (typeDetail.indexOf("(") != -1) {
                        String max = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                        fields.append("\r\n" + "    @Length(min=1, max=" + max
                                + ",message = \"" + desc + " 必须>=0 和 <=" + new BigDecimal(max.toString()).intValue()
                                + "位\")");
                    }
                } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                    /**
                     * 时间
                     */
                    //1
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    // 暂无
                    //   fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空\")");
                }
            }
            //生成字段
            JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        DsField.FIELD_ENTITYS = fields.toString();
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        pathMap.put("DTO", brBwPath.get("path").toString());
    }

    @Override
    public void buildVO(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VO");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String fieldName = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            this.JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        DsField.FIELD_ENTITYS = fields.toString();
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        pathMap.put("VO", brBwPath.get("path").toString());
    }


    //每一个字段(entity+dto+vo)
    private void JXModel(StringBuffer fields, String fieldName, String type) {
        //字段
        if (type.equals("int")) {
            //整数int
            fields.append("\r\n" + "    private Integer " + fieldName + ";");
        } else if (type.equals("bigint")) {
            //整数Long
            fields.append("\r\n" + "    private Long " + fieldName + ";");
        } else if (type.equals("varchar") || type.equals("char")) {
            //字符串
            fields.append("\r\n" + "    private String " + fieldName + ";");
        } else if (type.equals("text") || type.equals("longtext")) {
            //大文本、超大文本
            fields.append("\r\n" + "    private String " + fieldName + ";");
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            //时间
            fields.append("\r\n" + "    private LocalDateTime " + fieldName + ";");
        } else if (type.equals("double")) {
            //双精度小数 Double
            fields.append("\r\n" + "    private Double " + fieldName + ";");
        } else if (type.equals("float")) {
            //单精度小数 Float
            fields.append("\r\n" + "    private Float " + fieldName + ";");
        } else if (type.equals("decimal")) {
            //小数 decimal
            fields.append("\r\n" + "    private BigDecimal " + fieldName + ";");
        } else if (type.equals("tinyint")) {
            //小数 decimal
            fields.append("\r\n" + "    private Boolean " + fieldName + ";");
        }
        //每生成一次换一次行
        fields.append("\r\n");
    }


    /**
     * 生成Controller层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildController(List<Map<String, Object>> data, String path) {
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
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("bigint")) {
                //整数Long
                findPageParam.append("Long " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("varchar") || type.equals("char")) {
                //字符串
                findPageParam.append("String " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(StringUtils.isNotBlank(" + fieldName + ")," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("text") || type.equals("longtext")) {
                //大文本、超大文本
                findPageParam.append("String " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(StringUtils.isNotBlank(" + fieldName + ")," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                //时间
                findPageParam.append("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\") LocalDateTime " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("double")) {
                //双精度小数 Double
                findPageParam.append("Double " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("float")) {
                //单精度小数 Float
                findPageParam.append("Float " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            } else if (type.equals("decimal")) {
                //小数 decimal
                findPageParam.append("BigDecimal " + fieldName + ",");
                findPageMybatisPlus.append("                .eq(" + fieldName + " != null," + DsField.TABLE_NAME_UP + "::get" + fieldNameUp + "," + fieldName + ")");
            }
            findPageMybatisPlus.append("\r\n");
        }
        // log.debug(findPageParam.substring(0, findPageParam.length() - 1));
        // log.debug(findPageMybatisPlus.toString()); //
        DsField.FIND_PAGE_PARAM = findPageParam.substring(0, findPageParam.length() - 1);
        DsField.FIND_PAGE_MYBATIS_PLUS = findPageMybatisPlus.toString();
        DsField.SWAGGER_REMARK = swaggerRemark.toString();

        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("controller", brBwPath.get("path").toString());
    }


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
    public void buildService(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Service");
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("service", brBwPath.get("path").toString());
    }


    // {code1} 包名     {code2} 项目Base 文件名      Demo 实体类名

    /**
     * 生成ServiceImpl
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildServiceImpl(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "ServiceImpl");
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("serviceImpl", brBwPath.get("path").toString());
    }


    /**
     * 生成Dao
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildMapper(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Mapper");
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("mapper", brBwPath.get("path").toString());
    }


    /**
     * 生成Dao 对应的xml
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildMapperXml(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "MapperXml");
        StringBuffer resultMap = new StringBuffer();
        StringBuffer columnList = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            //字段名
            String fieldName = fieldMap.get("name").toString();
            //驼峰字段名
            String fieldNameHump = GenerateDataProcessing.getFieldName(fieldName);
            resultMap.append("\r\n               <result column=\"" + fieldName + "\" property=\"" + fieldNameHump + "\" />");
            columnList.append("\r\n               " + fieldName + ",");
        }
        DsField.RESULT_MAP = resultMap.toString();
        DsField.COLUMN_LIST = columnList.toString().substring(0, columnList.toString().length() - 1);
        // <result column="id" property="id" />
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("mapperXml", brBwPath.get("path").toString());
    }

    /**
     * 生成Html-main 主页
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildMainHtml(List<Map<String, Object>> dataList, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Html");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        // 数据表格字段
        StringBuffer fieldStr = new StringBuffer(" ");
        // 搜索条件html
        StringBuffer searchPtStr = new StringBuffer();
        // 搜索条件请求值url拼接
        StringBuffer SearchParamsStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            // 判断是否选中
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String name = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            //1
            String desc = "";
            if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
            } else {
                desc = fieldMap.get("desc").toString();
            }
            // 数据表格内容
            fieldStr.append("\r\n                    {field: '" + name + "', title: '" + desc + "'},");
            Object search = fieldMap.get("search");
            //是否为搜索值
            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            // 搜索内容输入框
            // 1/ input  * {desc} 字段描叙  * {id}   字段名 * {name} 字段名
            searchPtStr.append(LayuiSearchTemplate.INPUT_SEARCH_PT.replace("{id}", name).replace("{name}", name).replace("{desc}", desc));
            // 搜索内容条件拼接
            SearchParamsStr.append("            params += \"&" + name + "=\" + $(\"#" + name + "\").val();");
            // 换行
            searchPtStr.append("\r\n");
            SearchParamsStr.append("\r\n");
        }
        // 数据保存
        DsField.LAYUI_FIELDS = fieldStr.toString().substring(0, fieldStr.length() - 1);
        DsField.LAYUI_SEARCH_PT_STR = searchPtStr.toString();
        DsField.LAYUI_SEARCH_PARAMS_STR = SearchParamsStr.toString();

        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("main", brBwPath.get("path").toString());
    }


    //code1  字段div    / demo 类名小写

    /**
     * 生成Html-Add 添加页
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildAddHtml(List<Map<String, Object>> dataList, String path) {

        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "HtmlAdd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        //
        StringBuffer fieldStr = new StringBuffer();
        StringBuffer fieldDateJsStr = new StringBuffer();
        StringBuffer fieldPicJsStr = new StringBuffer();
        StringBuffer fieldRadioJsStr = new StringBuffer();
        StringBuffer fieldCheckboxJsStr = new StringBuffer();
        StringBuffer fieldCheckboxSubmitForStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            // 判断是否选中
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String name = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            if ("id".equals(name)) {
                continue;
            }
            //1
            String desc = "";
            if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
            } else {
                desc = fieldMap.get("desc").toString();
            }
            //  String primarykeyId = GenerateDataProcessing.getValue(fieldMap, "primarykeyId", ""); //是否id
            //  String selfGrowth = GenerateDataProcessing.getValue(fieldMap, "selfGrowth", "");//是否自增
            //  if (!primarykeyId.equals("true")) {     // }
            String type = fieldMap.get("type").toString();
            if (name.indexOf("Pic") != -1) {
                // 判断是否为图片
                fieldStr.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_HTML
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name)
                        .replace("{fieldName}", name));
                fieldPicJsStr.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_JS
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name));
            } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                // 时间字段
                fieldStr.append("\r\n" + LayuiDateTemplate.DATE_HTML_PT
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name)
                        .replace("{fieldName}", name)
                );
                // 时间渲染js
                fieldDateJsStr.append("\r\n" + LayuiDateTemplate.DATE_JS_PT
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name));

            } else if (name.indexOf("Codes") != -1) {
                // 判断是否为多选
                fieldStr.append("\r\n" + LayuiCheckboxTemplate.INPUT_CHECKBOX_PT
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name));
                fieldCheckboxJsStr.append("\r\n" + LayuiCheckboxTemplate.CHECKBOX_CODE_ADD_JS
                        .replaceAll("\\{fieldId}", name));
                fieldCheckboxSubmitForStr.append("\r\n" + LayuiCheckboxTemplate.CHECKBOX_SUBMIT_FOR
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name));
            } else if (name.indexOf("Code") != -1) {
                // 判断是否为单选
                fieldStr.append("\r\n" + LayuiRadioTemplate.INPUT_RADIO_PT
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name));
                fieldRadioJsStr.append("\r\n" + LayuiRadioTemplate.RADIO_CODE_ADD_JS
                        .replaceAll("\\{fieldId}", name));
            } else if (type.equals("double") || type.equals("float") || type.equals("decimal")) {
                // 小数
                fieldStr.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                        .replace("{integerVerification}", "")
                        .replace("{inputType}", "number")
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name)
                        .replace("{fieldName}", name));
            } else if (type.equals("int") || type.equals("bigint")) {
                // 整数
                fieldStr.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                        .replace("{integerVerification}", LayuiInputTemplate.INTEGER_VERIFICATION)
                        .replace("{inputType}", "number")
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name)
                        .replace("{fieldName}", name));
            } else {
                //  其他按字符串处理
                fieldStr.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                        .replace("{integerVerification}", "")
                        .replace("{inputType}", "text")
                        .replace("{fieldTitle}", desc)
                        .replace("{fieldId}", name)
                        .replace("{fieldName}", name));
            }
        }
        // 数据保存
        DsField.ADD_HTMLS = fieldStr.toString();
        DsField.ADD_UPD_DATE_JS = fieldDateJsStr.toString();
        DsField.ADD_UPD_PIC_JS = fieldPicJsStr.toString();
        DsField.ADD_UPD_RADIO_JS = fieldRadioJsStr.toString();
        DsField.ADD_UPD_CHECKBOX_JS = fieldCheckboxJsStr.toString();
        DsField.ADD_UPD_CHECKBOX_SUBMIT_FOR_JS = fieldCheckboxSubmitForStr.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // url保存
        pathMap.put("mainAdd", brBwPath.get("path").

                toString());
    }


    /**
     * 生成Html-Upd 修改页
     *
     * @param data    数据
     * @param DsField 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void buildUpdHtml(List<Map<String, Object>> dataList, String path) {

        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "HtmlUpd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        StringBuffer fieldStr = new StringBuffer();
        StringBuffer fieldDateJsStr = new StringBuffer();
        StringBuffer fieldPicJsStr = new StringBuffer();
        StringBuffer fieldPicShopJsStr = new StringBuffer();
        StringBuffer fieldRadioJsStr = new StringBuffer();
        StringBuffer fieldCheckboxJsStr = new StringBuffer();
        StringBuffer fieldCheckboxSubmitForStr = new StringBuffer();
        StringBuffer echoDisplay = new StringBuffer();
        String fieldId = "";
        for (Map<String, Object> fieldMap : dataList) {

            String name = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            if ("id".equals(name)) {
                fieldId = "data.field." + name + " = parent.data." + name + ";";
            } else {
                // 判断是否选中
                if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                    continue;
                }
                //1
                String desc = "";
                if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                    desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                } else {
                    desc = fieldMap.get("desc").toString();
                }
                String type = fieldMap.get("type").toString();
                if (name.indexOf("Pic") != -1) {
                    // 判断是否为图片
                    fieldStr.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_HTML
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                    fieldPicJsStr.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_JS
                            .replace("$(\"#{fieldId}Show\").hide();\n", "")//编辑页不隐藏默认图片展示
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                    fieldPicJsStr.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_SHOP_JS
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                    // 时间字段
                    fieldStr.append("\r\n" + LayuiDateTemplate.DATE_HTML_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                    // 时间渲染js
                    fieldDateJsStr.append("\r\n" + LayuiDateTemplate.DATE_JS_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));

                } else if (name.indexOf("Codes") != -1) {
                    // 判断是否为多选
                    fieldStr.append("\r\n" + LayuiCheckboxTemplate.INPUT_CHECKBOX_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                    fieldCheckboxJsStr.append("\r\n" + LayuiCheckboxTemplate.CHECKBOX_CODE_UPD_JS
                            .replaceAll("\\{fieldId}", name));
                    fieldCheckboxSubmitForStr.append("\r\n" + LayuiCheckboxTemplate.CHECKBOX_SUBMIT_FOR
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                } else if (name.indexOf("Code") != -1) {
                    // 判断是否为单选
                    fieldStr.append("\r\n" + LayuiRadioTemplate.INPUT_RADIO_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                    fieldRadioJsStr.append("\r\n" + LayuiRadioTemplate.RADIO_CODE_UPD_JS
                            .replaceAll("\\{fieldId}", name));
                } else if (type.equals("double") || type.equals("float") || type.equals("decimal")) {
                    // 小数
                    fieldStr.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                            .replace("{integerVerification}", "")
                            .replace("{inputType}", "number")
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                } else if (type.equals("int") || type.equals("bigint")) {
                    // 整数
                    fieldStr.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                            .replace("{integerVerification}", LayuiInputTemplate.INTEGER_VERIFICATION)
                            .replace("{inputType}", "number")
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                } else {
                    //  其他按字符串处理
                    fieldStr.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                            .replace("{integerVerification}", "")
                            .replace("{inputType}", "text")
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                }
                // 数据回显
                echoDisplay.append("\r\n         $('#" + name + "').val(parent.data." + name + ");");
            }
        }
        // 数据保存
        DsField.UPD_HTMLS = fieldStr.toString();
        DsField.UPD_BACKFILL = echoDisplay.toString();
        DsField.UPD_ID = fieldId;
        DsField.ADD_UPD_DATE_JS = fieldDateJsStr.toString();
        DsField.ADD_UPD_PIC_JS = fieldPicJsStr.toString();
        DsField.ADD_UPD_PIC_SHOP_JS = fieldPicShopJsStr.toString();
        DsField.ADD_UPD_RADIO_JS = fieldRadioJsStr.toString();
        DsField.ADD_UPD_CHECKBOX_JS = fieldCheckboxJsStr.toString();
        DsField.ADD_UPD_CHECKBOX_SUBMIT_FOR_JS = fieldCheckboxSubmitForStr.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        pathMap.put("mainUpd", brBwPath.get("path").toString());
    }
}
