package io.github.wslxm.springbootplus2.manage.gc.util;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.core.utils.LocalDateTimeUtil;
import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.core.utils.json.JsonUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GcPathConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.model.GcFilePath;
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
        // log.debug(tableList.toString());
        return tableList;
    }



    /**
     * 把下化线的字段映射成驼峰模式
     *
     * @return
     * @date 2019/11/20 19:22
     */
    public static String getFieldName(GcConfig gcConfig, String field) {

        String fieldPrefix = gcConfig.getDefaultTemplateParam("fieldPrefix");

        // 如果需要，去除字段前缀
        if (StringUtils.isNotBlank(fieldPrefix)) {
            // 获取前缀
            String prefix = field.substring(0, fieldPrefix.length());
            String newField = field;
            if (fieldPrefix.equals(prefix)) {
                newField = field.substring(fieldPrefix.length());
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
    public static Map<String, Object> getBrBwPath(GcConfig gcConfig, String key) {
        // 获取指定对象的配置
        GcFilePath gcFilePath = gcConfig.getTemplatePathMap().get(key);
        //
        String path = gcFilePath.getPath();
        String templatePath = gcFilePath.getTemplatePath();
        String fileName = gcFilePath.getName();
        // 判断是否为生成预览文件, 预览文件替换后缀
        if (path.substring(0, GcPathConfig.PREVIEW_FILE_PATH.length()).indexOf(GcPathConfig.PREVIEW_FILE_PATH) != -1) {
            path = path.substring(0, path.lastIndexOf("."));
            path += GcPathConfig.PREVIEW_SUFFIX;
        }
        // 对路径上的参数进行取参
        path = replacParamsPath(gcConfig, path);
        // 获取路径并创建目录
        String pathFile = path.substring(0, path.lastIndexOf("/"));
        mkdirFile(pathFile);
        Map<String, Object> brBw = new HashMap<>();
        try {
            // 获取代码模版文件
            BufferedReader br = getUrlDetail(templatePath);
            // 生成保存生成后的代码文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            brBw.put("br", br);
            brBw.put("bw", bw);
            // 保存生成后文件访问地址
            gcConfig.addVisitPath(fileName, path);
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
     * 参数替换
     *
     * @param path
     */
    public static String replacParamsPath(GcConfig gcConfig, String path) {
        Map<String, String> defaultTemplateParam = gcConfig.getDefaultTemplateParam();
        for (String key : defaultTemplateParam.keySet()) {
            path = path.replace(key, defaultTemplateParam.get(key));
        }
        Map<String, String> templateParam = gcConfig.getTemplateParam();
        for (String key : templateParam.keySet()) {
            path = path.replace(key, templateParam.get(key));
        }
        return path;
    }
}
