package com.ws.ldy.baseadmin.service.impl;

import com.ws.ldy.baseadmin.controller.vo.FieldCG;
import com.ws.ldy.baseadmin.service.CodeGeneration;
import com.ws.ldy.admincore.common.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@SuppressWarnings("all")
@Service
public class CodeGenerationImpl implements CodeGeneration {

    //生成文件的类注释信息，作者，qq,wx
    private final static String author = "wangsong";
    private final static String wxqq = "1720696548";
    //生成代码前初始化参数
    /**
     * 生成文件后缀名
     */
    public static String SUFFIX_NAME_JAVA = "";
    public static String SUFFIX_NAME_HTML = "";
    /**
     * mysql 关键字处理,如存在下方定义的关键字字段，实体类会自动处理
     */
    private static final String[] keywordArray = {"desc", "key", "value", "mysql", "info",
            "form", "sort", "icon", "unlock", "unLock"};


    /**
     * 保存预览文件返回的文件地址url
     */
    private Map<String, String> pathMap = new HashMap<>();

    public Map<String, String> getPathMap() {
        return pathMap;
    }

    /**
     * TODO json 数据处理成 List<Map<String, Object>>
     *
     * @param data
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @param1 data [{ primarykeyId：true }] 表示为id主键
     * @param2 data [{ selfGrowth：true }] 表示为id主键自增
     * @param3 data [{ search：true }] 表示要为该字段添加搜索功能
     * @date 2019/11/22 12:08
     */
    @Override
    public List<Map<String, Object>> getDataAnalysis(String data) {
        //所有字段数据处理成 List集 -->  每个字段名称，类型，描叙为 Map集
        List<Map<String, Object>> tableList = new ArrayList<>();
        List<Object> dataObjs = JsonUtil.getListJson(data, null);
        dataObjs.forEach(item -> tableList.add(JsonUtil.getMapJson(item.toString())));
        //System.out.println(tableList.toString());
        return tableList;
    }


    /***
     * TODO
     *
     * @param data  数据
     * @param this.TABLE_NAME 数据库表名
     * @param path      生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    // 替换内容 包名（{code1}），数据库表名（{code2}） 字段 {code3} , @author  @WX-QQ  ，@date
    @Override
    public void buildEntity(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws Exception {
        Map<String, Object> brBwPath = getBrBwPath(fieldCG, path, "", this.SUFFIX_NAME_JAVA);
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String line = null;
        String newLine = null;
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        for (Map<String, Object> fieldMap : data) {
            String fieldName = getFieldName(fieldMap.get("name").toString());
            String primarykeyId = getValue(fieldMap, "primarykeyId", "");
            String selfGrowth = getValue(fieldMap, "selfGrowth", "");
            String type = fieldMap.get("type").toString();
            //添加注释信息
            fields.append("\r\n    /** " + fieldMap.get("desc") + " */");
            //id 列主键注解处理
            if (primarykeyId.equals("true")) {
                fields.append("\r\n    @Id");
                //id自增
                if (selfGrowth.equals("true")) {
                    fields.append("\r\n    @GeneratedValue(strategy= GenerationType.IDENTITY)");
                }
            }
            //判断是否为mysql 关键字，是实体类字段添加相关映射注解
            if (Arrays.asList(keywordArray).contains(fieldName)) {
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
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("{code2}", fieldCG.getTableName())
                    .replace("{code3}", fields)
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("entity", brBwPath.get("path").toString());
        System.out.println("entity-->" + brBwPath.get("path").toString());
    }


    //  类名 Demo  / 小类名 demo  / 包名 {code1} / 驼峰模式项目名  {code2}
    @Override
    public void buildController(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws Exception {
        Map<String, Object> brBwPath = getBrBwPath(fieldCG,   path, "Controller", this.SUFFIX_NAME_JAVA);
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("controller", brBwPath.get("path").toString());
        System.out.println("controller--> ：" + brBwPath.get("path").toString());
    }

    // {code1} 包名     {code2} 项目Base 文件名(项目名驼峰)      Demo 实体类名
    @Override
    public void buildService(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws Exception {
        Map<String, Object> brBwPath = getBrBwPath(fieldCG,  path, "Service", this.SUFFIX_NAME_JAVA);
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");

        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("service", brBwPath.get("path").toString());
        System.out.println("Service--> ：" + brBwPath.get("path").toString());
    }


    // {code1} 包名     {code2} 项目Base 文件名      Demo 实体类名
    @Override
    public void buildServiceImpl(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws Exception {
        Map<String, Object> brBwPath = getBrBwPath(fieldCG,  path, "ServiceImpl", this.SUFFIX_NAME_JAVA);
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("serviceImpl", brBwPath.get("path").toString());
        System.out.println("ServiceImpl--> ：" + brBwPath.get("path").toString());

    }


    // {code1} 包名   Demo 实体类名
    @Override
    public void buildDao(FieldCG fieldCG, String path) throws Exception {
        Map<String, Object> brBwPath = getBrBwPath(fieldCG,  path, "Dao", this.SUFFIX_NAME_JAVA);
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("dao", brBwPath.get("path").toString());
        System.out.println("dao--> ：" + brBwPath.get("path").toString());
    }


    // code1 项目名（html根路径）   //code2 显示字段  //code3 搜索字段  //{code4} 项目名全大写
    // code5  当前对应类的URL地址   //demo 请求url,小写开头类名
    @Override
    public void buildMainHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws Exception {
        Map<String, Object> brBwPath = getBrBwPath(fieldCG, path, "Html", this.SUFFIX_NAME_HTML);
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");

        StringBuffer fieldStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            String name = fieldMap.get("name").toString();
            String desc = fieldMap.get("desc").toString();
            fieldStr.append("\r\n                   , {field: '" + name + "', title: '" + desc + "'}");
        }
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getEntryNameLast())
                    .replace("{code2}", fieldStr)
                    .replace("{code3}", "")
                    .replace("{code4}", fieldCG.getEntryName().toUpperCase().replace("-", "_"))
                    .replace("{code5}", fieldCG.getHtmlNameLower());  //当前类URL地址根

            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("main", brBwPath.get("path").toString());
        System.out.println("main--> ：" + brBwPath.get("path").toString());
    }


    //code1  字段div    / demo 类名小写
    @Override
    public void buildAddHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws Exception {

        Map<String, Object> brBwPath = getBrBwPath(fieldCG, path, "HtmlAdd", this.SUFFIX_NAME_HTML);
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
            String primarykeyId = getValue(fieldMap, "primarykeyId", ""); //是否id
            String selfGrowth = getValue(fieldMap, "selfGrowth", "");//是否自增
            if (!primarykeyId.equals("true")) {
                fieldStr.append("\r\n" + htmlAdd
                        .replace("fieldTitle", desc)
                        .replace("fieldId", name)
                        .replace("fieldName", name));
            }
        }

        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("demo", fieldCG.getClassNameLower())
                    .replace("{code2}", fieldStr)//当前类URL地址根
                    .replace("{code4}", fieldCG.getEntryName().toUpperCase().replace("-", "_"));
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("mainAdd", brBwPath.get("path").toString());
        System.out.println("mainAdd--> ：" + brBwPath.get("path").toString());
    }


    //code2  字段    //code3  回显值      //code5  id值获取代码   //code4  项目大写
    @Override
    public void buildUpdHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws Exception {

        Map<String, Object> brBwPath = getBrBwPath(fieldCG, path, "HtmlUpd", this.SUFFIX_NAME_HTML);
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
            String primarykeyId = getValue(fieldMap, "primarykeyId", ""); //是否id
            String selfGrowth = getValue(fieldMap, "selfGrowth", "");//是否自增
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
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line
                    .replace("demo", fieldCG.getClassNameLower())//当前类URL地址根
                    .replace("{code2}", fieldStr)
                    .replace("{code3}", echoDisplay)
                    .replace("{code5}", fieldId)
                    .replace("{code4}", fieldCG.getEntryName().toUpperCase().replace("-", "_"));
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("mainUpd", brBwPath.get("path").toString());
        System.out.println("mainUpd--> ：" + brBwPath.get("path").toString());
    }


    /**
     * TODO  存在下化线的字段映射成驼峰模式
     *
     * @return
     * @date 2019/11/20 19:22
     */
    private String getFieldName(String field) {
        String[] fields = field.split("_");
        if (fields.length > 1) {
            String fieldUp = fields[0];
            for (int i = 0; i < fields.length; i++) {
                if (i > 0) {
                    fieldUp += fields[i].substring(0, 1).toUpperCase() + fields[i].substring(1);
                }
            }
            return fieldUp;
        } else {
            return fields[0];
        }
    }


    /***
     * TODO  获取字段名为空处理
     * @param objMap
     * @param key
     * @param default1
     * @date 2019/11/22 12:07
     * @return java.lang.String
     */
    public String getValue(Map<String, Object> objMap, String key, String default1) {
        if (objMap.containsKey(key)) {
            return objMap.get(key).toString();
        } else {
            return default1;
        }
    }


    /**
     * TODO  判断文件路径是否存在，不存在创建
     *
     * @param path
     * @return void
     * @date 2019/11/22 15:18
     */
    public void mkdirFile(String path) {
        // 不存在创建文件夹
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * TODO  获得BufferedReader（读取模版），BufferedWriter （写入文件流），path生成的件路径
     *
     * @param FieldCG = 生成的路径/包名/类名等属性
     * @param path    =  代码生成路径，从父项目跟目录开始的路径
     * @param name    = Dao，Controller， Service 等等.对应模板 DemoDao，DemoController， DemoService 等等
     * @param suffix  =  this.SUFFIX_NAME_JAVA || this.SUFFIX_NAME_HTML
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:37
     */
    public Map<String, Object> getBrBwPath(FieldCG fieldCG, String path, String name, String suffix) throws Exception {
        Map<String, Object> brBw = new HashMap<>();
        // 路径 + 类名 + name + 后缀
        String upPath = null;
        if (name.indexOf("Html") != -1) {
            upPath = path + fieldCG.getHtmlNameLower() + name + suffix;
        } else {
            upPath = path + fieldCG.getClassNameUp() + name + suffix;
        }
        //检查目录,不存在添加
        this.mkdirFile(path);
        // 服务器模板路径（url+ 文件路径）+ 模板名称
        BufferedReader br = getUrlDetail(fieldCG.getPathTp() + "/Demo" + name + ".tp");
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
        brBw.put("br", br);
        brBw.put("bw", bw);
        brBw.put("path", upPath);
        return brBw;
    }


    /**
     * TODO  通过url 获取文件流
     *
     * @param urlStr
     * @param withSep
     * @return java.lang.String
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:01
     */
    //获取链接地址的字符数据，wichSep是否换行标记
    public static BufferedReader getUrlDetail(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.connect();
        InputStream cin = httpConn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(cin, "UTF-8"));
        return br;
    }
}
