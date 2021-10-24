package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.controller.XjGenerateController;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationVueAdd extends BaseIServiceImpl implements XjGenerationSevice {

    /**
     * 生成Html-Add 添加页
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(List<Map<String, Object>> dataList, String path) {

        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VueAdd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        //
        StringBuffer vueAddColumnsDefault = new StringBuffer("");
        StringBuffer vueAddColumns = new StringBuffer("");
        //
        for (Map<String, Object> fieldMap : dataList) {
            // 未勾选的字段过滤
            Object checked = fieldMap.get("checked");      // 兼容layui
            Object isChecked = fieldMap.get("isChecked");  // 兼容vue
            if (checked !=null && !Boolean.parseBoolean(checked.toString())) {
                continue;
            }
            if (isChecked !=null && !Boolean.parseBoolean(isChecked.toString())) {
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
            vueAddColumnsDefault.append("                    " + name + ": " + null + ",\n");
            vueAddColumns.append("                        {\n" +
                    "                            label: '" + desc + "',\n" +
                    "                            prop: '" + name + "',\n" +
                    "                            span: 20,\n" +
                    "                        },\n");
        }
        // 数据保存
        GenerateConfig.VUE_ADD_COLUMNS_DEFAULT = vueAddColumnsDefault.toString();
        GenerateConfig.VUE_ADD_COLUMNS = vueAddColumns.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // url保存
        XjGenerateController.pathMap.put("vueMainAdd", brBwPath.get("path").toString());
    }


//        if (name.indexOf("Pics") != -1) {
//        // 判断是否为图片
//    } else if (name.indexOf("Pic") != -1) {
//        // 判断是否为图片
//    } else if (name.indexOf("Codes") != -1) {
//        // 判断是否为多选
//    } else if (name.indexOf("Code") != -1) {
//        // 判断是否为单选
//    } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
//        // 时间字段
//    } else if (type.equals("double") || type.equals("float") || type.equals("decimal")) {
//        // 小数
//    } else if (type.equals("int") || type.equals("bigint")) {
//        // 整数
//    } else if (type.equals("text") || type.equals("longtext")) {
//        // 大字段使用textarea 输入框
//    } else {
//        //  其他按普通字符串处理(input输入))
//    }
}
