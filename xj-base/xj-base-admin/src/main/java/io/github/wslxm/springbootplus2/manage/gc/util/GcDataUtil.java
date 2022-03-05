package io.github.wslxm.springbootplus2.manage.gc.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.core.utils.json.JsonUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GcTPConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.model.GcFilePath;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具类( 处理前端菜单的表字段相关数据使用)
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@SuppressWarnings("all")
@Slf4j
public class GcDataUtil {


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
        // 字段前缀，如果需要，去除字段前缀
        String fieldPrefix = gcConfig.getDefaultTemplateParam("fieldPrefix");
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
}
