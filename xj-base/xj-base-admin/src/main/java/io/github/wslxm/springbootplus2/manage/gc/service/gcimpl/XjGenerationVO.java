package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.controller.XjGenerateController;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationVO extends BaseGcImpl implements XjGenerationSevice {


    @Override
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VO");
        this.generateParameters(data);
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        XjGenerateController.pathMap.put("VO", getBaseUrl(request) + "/" + brBwPath.get("path").toString());
    }


    private void generateParameters(List<Map<String, Object>> data) {
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            // 通用字段过滤
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String type = fieldMap.get("type").toString();
            String desc = fieldMap.get("desc").toString();
            String fieldName = fieldMap.get("name").toString();
            String typeDetail = fieldMap.get("typeDetail").toString();
            // 1、生成swagger注解
            fields.append("\r\n    @ApiModelProperty(value = \"" + desc + "\",position = " + (position++) + ")");
            // 3、生成字段
            fields.append("\r\n    " + super.JXModel(fieldName, type) + "\r\n");
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
    }
}
