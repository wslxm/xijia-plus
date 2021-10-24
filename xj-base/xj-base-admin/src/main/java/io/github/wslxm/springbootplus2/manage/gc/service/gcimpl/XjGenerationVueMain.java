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
public class XjGenerationVueMain extends BaseIServiceImpl implements XjGenerationSevice {

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
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Vue");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        // 数据表格字段
        StringBuffer vueInfoColumns = new StringBuffer(" ");
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
            // 获取字段名
            String name = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            // 获取字段描叙
            String desc = "";
            if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
            } else {
                desc = fieldMap.get("desc").toString();
            }
            vueInfoColumns.append("                {\n" +
                    "                    label: '" + desc + "',\n" +
                    "                    prop: '" + name + "',\n" +
                    "                }, \r\n");
        }
        // 数据保存
        GenerateConfig.VUE_INFO_COLUMNS = vueInfoColumns.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerateController.pathMap.put("vueMain",  brBwPath.get("path").toString());
    }

}
