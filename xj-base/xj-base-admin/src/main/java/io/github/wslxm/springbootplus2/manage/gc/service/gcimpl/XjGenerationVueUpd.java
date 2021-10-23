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
public class XjGenerationVueUpd extends BaseIServiceImpl implements XjGenerationSevice {


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
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VueUpd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        StringBuffer vueUpdColumns = new StringBuffer("");
        for (Map<String, Object> fieldMap : dataList) {
            String name = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            // 判断是否选中
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            // 1
            String desc = "";
            if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
            } else {
                desc = fieldMap.get("desc").toString();
            }
            String type = fieldMap.get("type").toString();
            vueUpdColumns.append("                       {\n" +
                    "                            label: '" + desc + "',\n" +
                    "                            prop: '" + name + "',\n" +
                    "                            span: 20,\n" +
                    "                        },\n");
        }
        // 数据保存
        GenerateConfig.VUE_UPD_COLUMNS = vueUpdColumns.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        XjGenerateController.pathMap.put("mainUpd", getBaseUrl(request) + "/" + brBwPath.get("path").toString());
    }
}
