package com.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import com.github.wslxm.springbootplus2.manage.gc.controller.XjGenerateController;
import com.github.wslxm.springbootplus2.manage.gc.template.*;
import com.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import com.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import com.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;

import com.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationHtmlUpd extends BaseIServiceImpl implements XjGenerationSevice {


    /**
     * 生成Html-Upd 修改页
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(List<Map<String, Object>> dataList, String path) {

        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "HtmlUpd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        StringBuffer introduce = new StringBuffer();
        StringBuffer htmls = new StringBuffer();
        StringBuffer js = new StringBuffer();
        StringBuffer submitjs = new StringBuffer();
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
                if (name.indexOf("Pics") != -1) {

                    // 判断是否为图片
                    introduce.append("\r\n" + LayuiPicTemplate.ADD_UPD_PICS_INTRODUCE);
                    htmls.append("\r\n" + LayuiPicTemplate.ADD_UPD_PICS_HTML
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                    js.append("\r\n" + LayuiPicTemplate.ADD_UPD_PICS_JS
                            .replace("{fieldId}", name));
                    js.append("\r\n" + LayuiPicTemplate.UPD_PICS_ECHO_JS
                            .replaceAll("\\{fieldId}", name));
                    submitjs.append("\r\n" + LayuiPicTemplate.ADD_UPD_PICS_SUBMIT_JS
                            .replaceAll("\\{fieldId}", name));
                } else if (name.indexOf("Pic") != -1) {
                    // 判断是否为图片
                    htmls.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_HTML
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                    js.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_JS
                            .replace("$(\"#{fieldId}Show\").hide();\n", "")//编辑页不隐藏默认图片展示
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                    js.append("\r\n" + LayuiPicTemplate.ADD_UPD_PIC_SHOP_JS
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                } else if (name.indexOf("Codes") != -1) {
                    // 判断是否为多选
                    htmls.append("\r\n" + LayuiCheckboxTemplate.INPUT_CHECKBOX_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                    js.append("\r\n" + LayuiCheckboxTemplate.CHECKBOX_CODE_UPD_JS
                            .replaceAll("\\{fieldId}", name));
                    submitjs.append("\r\n" + LayuiCheckboxTemplate.CHECKBOX_SUBMIT_FOR
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                } else if (name.indexOf("Code") != -1) {
                    // 判断是否为单选
                    htmls.append("\r\n" + LayuiRadioTemplate.INPUT_RADIO_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                    js.append("\r\n" + LayuiRadioTemplate.RADIO_CODE_UPD_JS
                            .replaceAll("\\{fieldId}", name));
                } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                    // 时间字段
                    htmls.append("\r\n" + LayuiDateTemplate.DATE_HTML_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                    // 时间渲染js
                    js.append("\r\n" + LayuiDateTemplate.DATE_JS_PT
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name));
                } else if (type.equals("double") || type.equals("float") || type.equals("decimal")) {
                    // 小数
                    htmls.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                            .replace("{integerVerification}", "")
                            .replace("{inputType}", "number")
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                } else if (type.equals("int") || type.equals("bigint")) {
                    // 整数
                    htmls.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                            .replace("{integerVerification}", LayuiInputTemplate.INTEGER_VERIFICATION)
                            .replace("{inputType}", "number")
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                } else if (type.equals("text") || type.equals("longtext")) {
                    // 大字段使用textarea 输入框
                    htmls.append("\r\n" + LayuiInputTemplate.INPUT_TEXT_HTML_PT
                            .replaceAll("\\{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                } else {
                    //  其他按字符串处理
                    htmls.append("\r\n" + LayuiInputTemplate.INPUT_HTML_PT
                            .replace("{integerVerification}", "")
                            .replace("{inputType}", "text")
                            .replace("{fieldTitle}", desc)
                            .replace("{fieldId}", name)
                            .replace("{fieldName}", name));
                }
                // 数据回显
                js.append("\r\n         $('#" + name + "').val(parent.data." + name + ");");
                //submitjs.append("\r\n         data.field.id = parent.data.id;");
            }
        }
        // 数据保存
        GenerateConfig.ADD_UPD_INTRODUCE = introduce.toString();
        GenerateConfig.ADD_UPD_HTMLS = htmls.toString();
        GenerateConfig.ADD_UPD_JS = js.toString();
        GenerateConfig.ADD_UPD_SUBMIT_JS = submitjs.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        XjGenerateController.pathMap.put("mainUpd", getBaseUrl(request) + "/" + brBwPath.get("path").toString());
    }
}
