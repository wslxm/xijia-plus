package com.ws.ldy.modules.sys.gc.service.gcimpl;

import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.gc.config.GenerateConfig;
import com.ws.ldy.modules.sys.gc.controller.XjGenerateController;
import com.ws.ldy.modules.sys.gc.service.XjGenerationSevice;
import com.ws.ldy.modules.sys.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationVO extends BaseIServiceImpl implements XjGenerationSevice {


    @Override
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VO");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String fieldName = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            this.JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        XjGenerateController.pathMap.put("VO", brBwPath.get("path").toString());
    }


    //每一个字段(entity+dto+vo)
    private void JXModel(StringBuffer fields, String fieldName, String type) {
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
            fields.append("\r\n" + "    private LocalDateTime " + fieldName + ";");
        } else if (type.equals("double")) {
            //双精度小数 Double
            fields.append("\r\n" + "    private Double " + fieldName + ";");
        } else if (type.equals("float")) {
            //单精度小数 Float
            fields.append("\r\n" + "    private Float " + fieldName + ";");
        } else if (type.equals("decimal")) {
            //小数 decimal
            fields.append("\r\n" + "    private BigDecimal " + fieldName + ";");
        } else if (type.equals("tinyint")) {
            //小数 decimal
            fields.append("\r\n" + "    private Boolean " + fieldName + ";");
        }
        //每生成一次换一次行
        fields.append("\r\n");
    }
}
