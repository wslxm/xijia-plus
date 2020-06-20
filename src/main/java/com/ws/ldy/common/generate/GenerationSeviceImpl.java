package com.ws.ldy.common.generate;

import com.ws.ldy.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class GenerationSeviceImpl extends BaseIServiceImpl {

    /**
     * 保存预览文件返回的文件地址url
     */
    public static Map<String, String> pathMap = new HashMap<>();


    /***
     * TODO 生成实体类
     *
     * @param data  数据
     * @param this.TABLE_NAME 数据库表名
     * @param path      生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    // 替换内容 包名（{code1}），数据库表名（{code2}） 字段 {code3} , @author  @WX-QQ  ，@date
    public void buildEntity(List<Map<String, Object>> data, String path) throws Exception {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            //字段名称
            String fieldName = GenerateUtil.getFieldName(fieldMap.get("name").toString());
            // 字段类型
            String type = fieldMap.get("type").toString();
            //是否为id主键
            String primarykeyId = GenerateUtil.getValue(fieldMap, "primarykeyId", "");
            // 字段注释信息--> 普通注释
            // fields.append("\r\n    /** " + fieldMap.get("desc") + " */");
            // 字段注释信息-->  Swagger2 模式
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\")");
            // 字段注释信息--> 普通注释
            // 添加id 主键注解
            if (primarykeyId.equals("true")) {
                fields.append("\r\n    @Id");
                //id是否自增长
                if (GenerateUtil.getValue(fieldMap, "selfGrowth", "").equals("true")) {
                    fields.append("\r\n    @GeneratedValue(strategy= GenerationType.IDENTITY)");
                }
                // 保存id主键数据类型，生成dao，service使用
                if (type.equals("int")) {
                    FieldCG.primaryKeyType = "Integer";
                } else if (type.equals("bigint")) {
                    FieldCG.primaryKeyType = "Long";
                } else if (type.equals("varchar") || type.equals("char")) {
                    FieldCG.primaryKeyType = "String";
                }
            }
            //添加mysql 关键字映射注解，mysql关键字配置: GenerateConfig.KEYWORD_ARRAY
            if (Arrays.asList(GenerateConfig.KEYWORD_ARRAY).contains(fieldName)) {
                fields.append("\r\n    @Column(name = \"`" + fieldName + "`\")");
            }
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
                fields.append("\r\n" + "    private Date " + fieldName + ";");
            } else if (type.equals("double")) {
                //双精度小数 Double
                fields.append("\r\n" + "    private Double " + fieldName + ";");
            } else if (type.equals("float")) {
                //单精度小数 Float
                fields.append("\r\n" + "    private Float " + fieldName + ";");
            }
        }
        // 数据保存
        FieldCG.fieldEntitys = fields.toString();
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        pathMap.put("entity", brBwPath.get("path").toString());
    }

    /**
     * TODO 生成Controller层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildController(List<Map<String, Object>> data, String path) throws Exception {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Controller");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("controller", brBwPath.get("path").toString());
    }


    /**
     * TODO 生成Service层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildService(List<Map<String, Object>> data, String path) throws Exception {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Service");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("service", brBwPath.get("path").toString());
    }


    // {code1} 包名     {code2} 项目Base 文件名      Demo 实体类名

    /**
     * TODO 生成ServiceImpl
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildServiceImpl(List<Map<String, Object>> data, String path) throws Exception {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "ServiceImpl");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("serviceImpl", brBwPath.get("path").toString());
    }


    /**
     * TODO 生成Dao
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildDao(List<Map<String, Object>> data, String path) throws Exception {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Dao");
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("dao", brBwPath.get("path").toString());
    }


    /**
     * TODO 生成Html-main 展页
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildMainHtml(List<Map<String, Object>> dataList, String path) throws Exception {
        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "Html");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");

        StringBuffer fieldStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            String name = fieldMap.get("name").toString();
            String desc = fieldMap.get("desc").toString();
            fieldStr.append("\r\n                   , {field: '" + name + "', title: '" + desc + "'}");
        }
        // 数据保存
        FieldCG.layuiFields = fieldStr.toString();
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // 文件url记录
        pathMap.put("main", brBwPath.get("path").toString());
    }


    //code1  字段div    / demo 类名小写

    /**
     * TODO 生成Html-Add 添加页
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildAddHtml(List<Map<String, Object>> dataList, String path) throws Exception {

        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "HtmlAdd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");


        String htmlAdd = " <div class=\"layui-form-item\">\n" +
                "        <label class=\"layui-form-label\">fieldTitle</label>\n" +
                "        <div class=\"layui-input-inline\">\n" +
                "            <input type=\"text\" id=\"fieldId\" name=\"fieldName\" lay-verify=\"required\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "        </div>\n" +
                "    </div>";
        StringBuffer fieldStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            String name = fieldMap.get("name").toString();
            String desc = fieldMap.get("desc").toString();
            String primarykeyId = GenerateUtil.getValue(fieldMap, "primarykeyId", ""); //是否id
            String selfGrowth = GenerateUtil.getValue(fieldMap, "selfGrowth", "");//是否自增
            if (!primarykeyId.equals("true")) {
                fieldStr.append("\r\n" + htmlAdd
                        .replace("fieldTitle", desc)
                        .replace("fieldId", name)
                        .replace("fieldName", name));
            }
        }
        // 数据保存
        FieldCG.addHtmls = fieldStr.toString();
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        // url保存
        pathMap.put("mainAdd", brBwPath.get("path").toString());
    }


    /**
     * TODO 生成Html-Upd 修改页
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildUpdHtml(List<Map<String, Object>> dataList, String path) throws Exception {

        Map<String, Object> brBwPath = GenerateUtil.getBrBwPath(path, "HtmlUpd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String htmlAdd = " <div class=\"layui-form-item\">\n" +
                "        <label class=\"layui-form-label\">fieldTitle</label>\n" +
                "        <div class=\"layui-input-inline\">\n" +
                "            <input type=\"text\" id=\"fieldId\" name=\"fieldName\" lay-verify=\"required\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "        </div>\n" +
                "    </div>";
        StringBuffer fieldStr = new StringBuffer();
        StringBuffer echoDisplay = new StringBuffer();
        String fieldId = "";
        for (Map<String, Object> fieldMap : dataList) {
            String name = fieldMap.get("name").toString();
            String desc = fieldMap.get("desc").toString();
            String primarykeyId = GenerateUtil.getValue(fieldMap, "primarykeyId", ""); //是否id
            String selfGrowth = GenerateUtil.getValue(fieldMap, "selfGrowth", "");//是否自增
            if (!primarykeyId.equals("true")) {
                echoDisplay.append("\r\n         $('#" + name + "').val(parent.data." + name + ");");
                fieldStr.append("\r\n" + htmlAdd
                        .replace("fieldTitle", desc)
                        .replace("fieldId", name)
                        .replace("fieldName", name));
            } else {
                fieldId = "data.field." + name + " = parent.data." + name + ";";
            }
        }
        // 数据保存
        FieldCG.updhtmls = fieldStr.toString();
        FieldCG.updBackfill = echoDisplay.toString();
        FieldCG.updId = fieldId;
        // 开始生成文件并进行数据替换
        GenerateUtil.replacBrBwWritee(brBwPath);
        pathMap.put("mainUpd", brBwPath.get("path").toString());
    }
}
