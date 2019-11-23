package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.controller.vo.FieldCG;
import com.ws.ldy.adminconsole.service.CodeGeneration;
import com.ws.ldy.admincore.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@SuppressWarnings("all")
@Service
public class CodeGenerationImpl  implements CodeGeneration {

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
     * @param data      数据
     * @param this.TABLE_NAME 数据库表名
     * @param path      生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    // 替换内容 包名（{code1}），数据库表名（{code2}） 字段 {code3} , @author  @WX-QQ  ，@date
    @Override
    public void buildEntity(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws Exception {
        this.mkdirFile(fieldCG.getPathFather() + path);
        String upPath = fieldCG.getPathFather() + path + fieldCG.getClassNameUp() + this.SUFFIX_NAME_JAVA;
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/Demo.tp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
        String line = null;
        String newLine = null;
        //数据拼接
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
        this.pathMap.put("entity", fieldCG.getClassNameUp() + this.SUFFIX_NAME_JAVA);
        System.out.println("entity-->" + upPath);
    }


    //  类名 Demo  / 小类名 demo  / 包名 {code1} / 驼峰模式项目名  {code2}
    @Override
    public void buildController(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws IOException {
        this.mkdirFile(fieldCG.getPathFather() + path);
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoController.tp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fieldCG.getPathFather() + path + fieldCG.getClassNameUp() + "Controller" + this.SUFFIX_NAME_JAVA));
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("{code2}", fieldCG.getEntryNameUp())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("controller", fieldCG.getClassNameUp() + "Controller" + this.SUFFIX_NAME_JAVA);
        System.out.println("controller--> ：" + fieldCG.getPathFather() + path + fieldCG.getClassNameUp() + "Controller" + this.SUFFIX_NAME_JAVA);
    }

    // {code1} 包名     {code2} 项目Base 文件名(项目名驼峰)      Demo 实体类名
    @Override
    public void buildService(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws IOException {
        this.mkdirFile(fieldCG.getPathFather() + path);
        String upPath = fieldCG.getPathFather() + path + fieldCG.getClassNameUp() + "Service" + this.SUFFIX_NAME_JAVA;
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoService.tp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("{code2}", fieldCG.getEntryNameUp())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("service", fieldCG.getClassNameUp() + "Service" + this.SUFFIX_NAME_JAVA);
        System.out.println("Service--> ：" + upPath);
    }


    // {code1} 包名     {code2} 项目Base 文件名      Demo 实体类名
    @Override
    public void buildServiceImpl(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws IOException {
        this.mkdirFile(fieldCG.getPathFather() + path);
        String upPath = fieldCG.getPathFather() + path + fieldCG.getClassNameUp() + "ServiceImpl" + this.SUFFIX_NAME_JAVA;
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoServiceImpl.tp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
        String line = null;
        String newLine = null;
        while ((line = br.readLine()) != null) {
            newLine = line.replace("Demo", fieldCG.getClassNameUp())
                    .replace("demo", fieldCG.getClassNameLower())
                    .replace("{code1}", fieldCG.getPackName())
                    .replace("{code2}", fieldCG.getEntryNameUp())
                    .replace("@author", "@author  " + author)
                    .replace("@WX-QQ", "@WX-QQ  " + wxqq)
                    .replace("@date", "@date  " + new Date());
            bw.write(newLine);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        this.pathMap.put("serviceImpl", fieldCG.getClassNameUp() + "ServiceImpl" + this.SUFFIX_NAME_JAVA);
        System.out.println("ServiceImpl--> ：" + upPath);

    }


    // {code1} 包名   Demo 实体类名
    @Override
    public void buildDao(FieldCG fieldCG, String path) throws IOException {
        this.mkdirFile(fieldCG.getPathFather() + path);
        String upPath = fieldCG.getPathFather() + path + fieldCG.getClassNameUp() + "Dao" + this.SUFFIX_NAME_JAVA;
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoDao.tp"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
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
        this.pathMap.put("dao", fieldCG.getClassNameUp() + "Dao" + this.SUFFIX_NAME_JAVA);
        System.out.println("dao--> ：" + upPath);
    }


    @Override
    public void buildDaoFactory(FieldCG fieldCG, String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathFactoryTp() + "/Dao" + fieldCG.getEntryNameUp() + "Factory.java"));
        String line = null;
        //判断是否存在
        boolean resutl = false;
        StringBuffer txtStr = new StringBuffer();
        while ((line = br.readLine()) != null) {
            if (line.indexOf(fieldCG.getClassNameUp()) != -1) {
                resutl = true;
            }
            txtStr.append(line + "\r\n");
        }
        br.close();
        String upPath = fieldCG.getPathFather() + path + "Dao" + fieldCG.getEntryNameUp() + "Factory" + this.SUFFIX_NAME_JAVA;
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
        String newtxtStr = null;
        if (resutl == false) {
            int index = txtStr.indexOf("{");
            String appStr = "\r\n    @Resource\r\n" + "    public " + fieldCG.getClassNameUp() + "Dao " + fieldCG.getClassNameLower() + "Dao;  //此为代码生成 \r\n ";
            String newstr = txtStr.toString();
            newtxtStr = newstr.substring(0, index + 1) + appStr + newstr.substring(index + 1, txtStr.length());
        } else {
            newtxtStr = txtStr.toString();
        }
        bw.write(newtxtStr);
        bw.newLine();
        bw.flush();
        bw.close();
        this.pathMap.put("daoFactory", "Dao" + fieldCG.getEntryNameUp() + "Factory" + this.SUFFIX_NAME_JAVA);
        System.out.println("daoFactory--> ：" + upPath);
    }


    @Override
    public void buildServiceFactory(FieldCG fieldCG, String path) throws IOException {
        // 获得大小开头类名
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathFactoryTp() + "/Service" + fieldCG.getEntryNameUp() + "Factory.java"));
        String line = null;
        //判断是否存在
        boolean resutl = false;
        StringBuffer txtStr = new StringBuffer();
        while ((line = br.readLine()) != null) {
            if (line.indexOf(fieldCG.getClassNameUp()) != -1) {
                resutl = true;
            }
            txtStr.append(line + "\r\n");
        }
        br.close();
        String upPath = fieldCG.getPathFather() + path + "Service" + fieldCG.getEntryNameUp() + "Factory" + this.SUFFIX_NAME_JAVA;
        //关闭输入流在所有输出流写如数据覆盖原文件
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
        String newtxtStr = null;
        if (resutl == false) {
            int index = txtStr.indexOf("{");
            String appStr = "\r\n    @Resource\r\n" + "    public " + fieldCG.getClassNameUp() + "ServiceImpl " + fieldCG.getClassNameLower() + "ServiceImpl;  //此为代码生成" + "\r\n";
            String newstr = txtStr.toString();
            newtxtStr = newstr.substring(0, index + 1) + appStr + newstr.substring(index + 1, txtStr.length());
        } else {
            newtxtStr = txtStr.toString();
        }
        bw.write(newtxtStr);
        bw.newLine();
        bw.flush();
        bw.close();
        this.pathMap.put("serviceFactory", "Service" + fieldCG.getEntryNameUp() + "Factory" + this.SUFFIX_NAME_JAVA);
        System.out.println("serviceFactory --> ：" + upPath);
    }


    // code1 项目名（html根路径）   //code2 显示字段  //code3 搜索字段  //{code4} 项目名全大写
    // code5  当前对应类的URL地址   //demo 请求url,小写开头类名
    @Override
    public void buildMainHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws IOException {
        StringBuffer fieldStr = new StringBuffer();
        for (Map<String, Object> fieldMap : dataList) {
            String name = fieldMap.get("name").toString();
            String desc = fieldMap.get("desc").toString();
            fieldStr.append("\r\n                   , {field: '" + name + "', title: '" + desc + "'}");
        }

        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoHtml.tp"));
        this.mkdirFile(fieldCG.getPathFather() + path + fieldCG.getHtmlNameLower());
        String upPath = fieldCG.getPathFather() + path + fieldCG.getHtmlNameLower() + "/" + fieldCG.getHtmlNameLower() + this.SUFFIX_NAME_HTML;
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
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
        this.pathMap.put("main", fieldCG.getHtmlNameLower() + "/" + fieldCG.getHtmlNameLower() + this.SUFFIX_NAME_HTML);
        System.out.println("main--> ：" + upPath);
    }


    //code1  字段div    / demo 类名小写
    @Override
    public void buildAddHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws IOException {
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

        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoHtmlAdd.tp"));
        this.mkdirFile(fieldCG.getPathFather() + path + fieldCG.getHtmlNameLower());
        String upPath = fieldCG.getPathFather() + path + fieldCG.getHtmlNameLower() + "/" + fieldCG.getHtmlNameLower() + "Add" + this.SUFFIX_NAME_HTML;
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
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
        this.pathMap.put("mainAdd", fieldCG.getHtmlNameLower() + "/" + fieldCG.getHtmlNameLower() + "Add" + this.SUFFIX_NAME_HTML);
        System.out.println("mainAdd--> ：" + upPath);
    }


    //code2  字段    //code3  回显值      //code5  id值获取代码   //code4  项目大写
    @Override
    public void buildUpdHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws IOException {
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
        BufferedReader br = new BufferedReader(new FileReader(fieldCG.getPathFather() + fieldCG.getPathTp() + "/DemoHtmlUpd.tp"));
        this.mkdirFile(fieldCG.getPathFather() + path + fieldCG.getHtmlNameLower());
        String upPath = fieldCG.getPathFather() + path + fieldCG.getHtmlNameLower() + "/" + fieldCG.getHtmlNameLower() + "Upd" + this.SUFFIX_NAME_HTML;
        BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
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
        this.pathMap.put("mainUpd", fieldCG.getHtmlNameLower() + "/" + fieldCG.getHtmlNameLower() + "Upd" + this.SUFFIX_NAME_HTML);
        System.out.println("mainUpd--> ：" + upPath);
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
}
