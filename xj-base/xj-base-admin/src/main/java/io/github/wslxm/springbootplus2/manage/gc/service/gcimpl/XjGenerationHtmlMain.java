package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.controller.XjGenerateController;
import io.github.wslxm.springbootplus2.manage.gc.template.LayuiMainTemplate;
import io.github.wslxm.springbootplus2.manage.gc.template.LayuiSearchTemplate;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationHtmlMain extends BaseIServiceImpl implements XjGenerationSevice {

    /**
     * 生成Html-main 主页
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(List<Map<String, Object>> dataList, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Html");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        // 数据表格字段
        StringBuffer fieldStr = new StringBuffer(" ");
        // 搜索条件html
        StringBuffer searchPtStr = new StringBuffer();
        StringBuffer searchJsStr = new StringBuffer();
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
            if (name.indexOf("Pic") != -1) {
                fieldStr.append(LayuiMainTemplate.TABLE_FIELD_PIC_PT
                        .replace("{fieldId}", name)
                        .replace("{fieldTitle}", desc)
                );
            } else if (name.indexOf("Code") != -1) {
                fieldStr.append(LayuiMainTemplate.TABLE_FIELD_ENUM_PT
                        .replaceAll("\\{fieldId}", name)
                        .replace("{fieldTitle}", desc)
                );
            } else {
                fieldStr.append(LayuiMainTemplate.TABLE_FIELD_PT
                        .replace("{fieldId}", name)
                        .replace("{fieldTitle}", desc)
                );
            }
            //
            Object search = fieldMap.get("search");
            //是否为搜索值
            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            if (name.indexOf("Code") != -1) {
                searchPtStr.append(LayuiSearchTemplate.TABLE_SEARCH_ENUM_PT
                        .replace("{id}", name)
                        .replace("{desc}", desc));
                searchJsStr.append(LayuiSearchTemplate.RADIO_SEARCH_CODE_JS
                        .replace("{id}", name));
            } else {
                // 搜索内容输入框
                // 1/ input  * {desc} 字段描叙  * {id}   字段名 * {name} 字段名
                searchPtStr.append(LayuiSearchTemplate.INPUT_SEARCH_PT
                        .replace("{id}", name)
                        .replace("{name}", name)
                        .replace("{desc}", desc));

            }
            // 搜索内容条件拼接
            SearchParamsStr.append("            params += \"&" + name + "=\" + $(\"#" + name + "\").val();");
            // 换行
            searchPtStr.append("\r\n");
            SearchParamsStr.append("\r\n");
        }
        // 数据保存
        GenerateConfig.LAYUI_FIELDS = fieldStr.toString().substring(0, fieldStr.length() - 1);
        GenerateConfig.LAYUI_SEARCH_PT_STR = searchPtStr.toString();
        GenerateConfig.LAYUI_SEARCH_PARAMS_STR = SearchParamsStr.toString();
        GenerateConfig.LAYUI_SEARCH_JS_STR = searchJsStr.toString();

        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerateController.pathMap.put("main",getBaseUrl(request) + "/" +brBwPath.get("path").toString());
    }

}
