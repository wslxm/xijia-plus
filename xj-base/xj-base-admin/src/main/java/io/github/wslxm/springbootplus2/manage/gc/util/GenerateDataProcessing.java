package io.github.wslxm.springbootplus2.manage.gc.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.core.utils.LocalDateTimeUtil;
import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.core.utils.json.JsonUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具类( 处理相关数据使用)
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@SuppressWarnings("all")
@Slf4j
public class GenerateDataProcessing {


    /**
     * json 数据处理成 List<Map<String, Object>> (处理代码生成前端传入数据)
     *
     * @param data
     * @param data [{ search：true }] 表示要为该字段添加搜索功能
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @date 2019/11/22 12:08
     */
    public static List<DbFieldPO> getDataAnalysis(String data) {
        //所有字段数据处理成 List集 -->  每个字段名称，类型，描叙为 Map集
        List<DbFieldPO> tableList = new ArrayList<>();
        List<Object> dataObjs = JsonUtil.parseList(data, null);
        dataObjs.forEach(item -> tableList.add(JsonUtil.parseObject(item.toString(), DbFieldPO.class)));
        //log.debug(tableList.toString());
        return tableList;
    }


    /**
     * 把下化线的字段映射成驼峰模式
     *
     * @return
     * @date 2019/11/20 19:22
     */
    public static String getFieldName(String field) {
        // 如果需要，去除字段前缀
        if (StringUtils.isNotBlank(GenerateConfig.FIELD_PREFIX)) {
            // 获取前缀
            String prefix = field.substring(0, GenerateConfig.FIELD_PREFIX.length());
            String newField = field;
            if (GenerateConfig.FIELD_PREFIX.equals(prefix)) {
                newField = field.substring(GenerateConfig.FIELD_PREFIX.length());
            }
            // 转小写处理为驼峰
            String lowerNewField = newField.toLowerCase();
            String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, lowerNewField);
            return fieldName;
        } else {
            // 先转为全小写，兼容数据库是全大写策略
            String lowerField = field.toLowerCase();
            // 变更为驼峰模式
            String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, lowerField);
            return fieldName;
        }
    }


    /***
     *  获取字段名为空处理（为空返回默认值default1）
     * @param objMap
     * @param key
     * @param default1
     * @date 2019/11/22 12:07
     * @return java.lang.String
     */
    public static String getValue(Map<String, Object> objMap, String key, String default1) {
        if (objMap.containsKey(key)) {
            return objMap.get(key).toString();
        } else {
            return default1;
        }
    }


    /**
     * 判断文件路径是否存在，不存在创建
     *
     * @param path
     * @return void
     * @date 2019/11/22 15:18
     */
    public static void mkdirFile(String path) {
        // 不存在创建文件夹
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 判断文件路径是否存在，存在删除
     *
     * @param path
     * @return void
     * @date 2019/11/22 15:18
     */
    public static void delFile(String path) {
        // 不存在创建文件夹
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }


    /**
     * 获得BufferedReader（根据url读取模版文档），BufferedWriter （写入文件流），path生成的件路径
     *
     * @param DsField =  生成的路径/包名/类名等属性
     * @param path    =  代码生成路径，从父项目跟目录开始的路径
     * @param name    =  Dao，Controller， Service 等等.对应模板 DemoDao，DemoController， DemoService 等等
     * @param suffix  =  this.SUFFIX_NAME_JAVA || this.SUFFIX_NAME_HTML
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:37
     */
    public static Map<String, Object> getBrBwPath(String path, String name) {
        Map<String, Object> brBw = new HashMap<>();
        // 生成路径=[路径 + 类名 + name + 后缀]
        String upPath = null;
        if (name.indexOf("Html") != -1) {
            /**
             * 生成 html
             */
            upPath = path + GenerateConfig.TABLE_NAME_LOWER + name.replace("Html", "") + GenerateConfig.SUFFIX_HTML_PT;
        } else if (name.indexOf("Vue") != -1) {
            /**
             * 生成 vue 代码
             */
            upPath = path + GenerateConfig.TABLE_NAME_LOWER + name.replace("Vue", "") + GenerateConfig.SUFFIX_VUE_PT;
        } else if (name.indexOf("Xml") != -1) {
            /**
             * 生成xml,  xml文件 去除文件名上的 Xml
             */
            String newName = name.substring(0, name.length() - 3);
            upPath = path + GenerateConfig.TABLE_NAME_UP + newName + GenerateConfig.SUFFIX_XML_PT;
        } else {
            /**
             * 生成 java 文件
             */
            upPath = path + GenerateConfig.TABLE_NAME_UP + name + GenerateConfig.SUFFIX_JAVA_PT;
        }
        // 检查目录,不存在添加
        mkdirFile(path);
        try {
            // 服务器模板路径（url+ 文件路径）+ 模板名称
            BufferedReader br = getUrlDetail(GenerateConfig.PATH_TP + "/Demo" + name + ".tp");
            BufferedWriter bw = new BufferedWriter(new FileWriter(upPath));
            brBw.put("br", br);
            brBw.put("bw", bw);
            brBw.put("path", upPath);
            // 打印参数区分
            brBw.put("name", name);
        } catch (Exception e) {
            log.debug(e.toString());
        }
        return brBw;
    }


    /**
     * 通过url 获取文件流
     *
     * @param urlStr
     * @param withSep
     * @return java.lang.String
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 21:01
     */
    // 获取链接地址的字符数据，wichSep是否换行标记
    public static BufferedReader getUrlDetail(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.connect();
        InputStream cin = httpConn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(cin, "UTF-8"));
        return br;
    }


    /**
     * ================================ 代码生成所有替换字段 ===========================================
     * =================================注释信息
     *  {author} ：      作者
     *  {email} ：       邮箱 || 联系方式
     *  {date} :         时间
     *  =================================原始数据
     *  {Demo} ：         类名/文件名大小（驼峰模式）
     *  {demo} ：         类名/文件名小写开头（驼峰模式）
     *  {htmlNameLower} ：html文件名称
     *  {packName} ：     生成代码的包名/路径 （从java 目录开始，如当前: io.github.wslxm.baseadmin）
     *  {tableName} ：    数据库表的实际名称
     *  {entryName} ：    项目名实际名称
     *  {entryNameUp} ：  项目名称-处理下划线(驼峰模式全大写开头)
     *  {entryNameSmall}: 项目名称-处理下划线(全转小写)
     *  {entryNameLast}:  项目名称 (下化线分隔，除第一个，拼接后面的全部单词,全小写,如只存在一个，则使用第一个)
     *  =================================代码生成方法内获得的处理数据
     *  {entitys} ：        entity 实体类所有字段数据
     *  {primary-key-type} ： 主键id 数据类型
     *  {layui-fields} ：   html主页layui数据表格所有字段数据
     *  {add-htmls} ：      html添加页，表单所有添加字段数据
     *  {upd-htmls} ：      html修改页，表单所有添加字段数据(无Id)
     *  {upd-id} ：         html修改页，添加表单id赋值
     *  {upd-backfill} ：   html修改页，打开提交回填数据赋值
     *
     */
    /**
     * 代码生成所有文件数据替换工具类
     *
     * @param brBwPath
     * @param DsField
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/23 0023 8:12
     */
    public static void replacBrBwWritee(Map<String, Object> brBwPath) {
        // 获取到getBrBwPath 方法拼装的数据
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        String line = null;
        String newLine = null;
        try {
            while ((line = br.readLine()) != null) {
                //注释信息
                newLine = line.replace("{author}", GenerateConfig.AUTHOR)
                        .replace("{email}", GenerateConfig.EMAIL)
                        .replace("{describe}", GenerateConfig.DESCRIBE)
                        // 模块根目录
                        .replace("{rootModule}", GenerateConfig.ROOT_MODULE)
                        // 模块一级目录
                        .replace("{moduleName}", GenerateConfig.MODULE_NAME)
                        // 模块子目录
                        //.replace("{packPathZp}", GenerateConfig.PACK_PATH_ZP)
                        .replace("{date}", LocalDateTimeUtil.parse(LocalDateTimeUtil.now()));

                //原始数据
                newLine = newLine
                        // 表名
                        .replace("{tableName}", GenerateConfig.TABLE_NAME)
                        // 表名大写开头驼峰
                        .replace("{tableNameUp}", GenerateConfig.TABLE_NAME_UP)
                        // 表名小写开头驼峰
                        .replace("{tableNameLower}", GenerateConfig.TABLE_NAME_LOWER)
                        // 表名小写开头驼峰
                        // .replace("{htmlNameLower}", DsField.TABLE_NAME_LOWER)
                        // 包路径
                        .replace("{packPath}", GenerateConfig.PACK_PATH)
                        //.replace("{entryName}", DsField.entryName)
                        // 数据表的注释
                        .replace("{tableComment}", GenerateConfig.TABLE_COMMENT);

                // 后端代码(替换数据)
                newLine = newLine.replace("{entitys}", GenerateConfig.FIELD_ENTITYS)
                        .replace("{findPageMybatisPlus}", GenerateConfig.FIND_PAGE_MYBATIS_PLUS)
                        //.replace("{swaggerRemark}", GenerateConfig.SWAGGER_REMARK)
                        //.replace("{findPageParam}", GenerateConfig.FIND_PAGE_PARAM)
                        // mapper - xml
                        .replace("{resultMap}", GenerateConfig.RESULT_MAP)
                        .replace("{columnList}", GenerateConfig.COLUMN_LIST)
                        .replace("{xmlInsert}", GenerateConfig.XML_INSERT)
                        .replace("{xmlUpd}", GenerateConfig.XML_UPD)
                        // entity/vo/dto的 serialVersionUID生成(雪花算法)
                        .replace("{serialVersionUID}", IdUtil.snowflakeId());

                // 前端 layui html(替换数据)
                newLine = newLine.replace("{layui-fields}", GenerateConfig.LAYUI_FIELDS)
                        .replace("{layui-search-pt-str}", GenerateConfig.LAYUI_SEARCH_PT_STR)
                        .replace("{layui-search-params-str}", GenerateConfig.LAYUI_SEARCH_PARAMS_STR)
                        .replace("{layui-search-js-str}", GenerateConfig.LAYUI_SEARCH_JS_STR)
                        // html add/upd code
                        .replace("{add-upd-introduce}", GenerateConfig.ADD_UPD_INTRODUCE)
                        .replace("{add-upd-htmls}", GenerateConfig.ADD_UPD_HTMLS)
                        .replace("{add-upd-js}", GenerateConfig.ADD_UPD_JS)
                        .replace("{add-upd-submit-js}", GenerateConfig.ADD_UPD_SUBMIT_JS);

                // 前端 vue (替换数据)
                newLine = newLine
                        .replace("{vue-info-columns}", GenerateConfig.VUE_INFO_COLUMNS)
                        .replace("{vue-add-columns}", GenerateConfig.VUE_ADD_COLUMNS)
                        .replace("{vue-add-columns-default}", GenerateConfig.VUE_ADD_COLUMNS_DEFAULT)
                        .replace("{vue-upd-columns}", GenerateConfig.VUE_UPD_COLUMNS)
                ;
                // 替换行
                bw.write(newLine);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            log.debug(e.toString());
        }
        log.debug(brBwPath.get("name") + " --> " + brBwPath.get("path").toString());
    }
}
