package com.ws.ldy.manage.gc.service.gcimpl;

import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.manage.gc.config.GenerateConfig;
import com.ws.ldy.manage.gc.controller.XjGenerateController;
import com.ws.ldy.manage.gc.service.XjGenerationSevice;
import com.ws.ldy.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationEntity extends BaseIServiceImpl implements XjGenerationSevice {


    @Override
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            // 字段名称
            String fieldName = fieldMap.get("name").toString();
            // 字段类型
            String type = fieldMap.get("type").toString();
            if (GenerateConfig.entitySwagger) {
                // 字段注释信息-->  Swagger2 模式
                fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            } else {
                // 字段注释信息-->  doc 注释
                fields.append("\r\n    /** \r\n     * " + fieldMap.get("desc") + " \r\n     */");
            }
            // 字段名为id的 添加id主键注解, int 默认自增, bigint+varchar 默认雪花算法
            if ("id".equals(fieldName)) {
                // id生成策略
                if (type.equals("int")) {
                    fields.append("\r\n    @TableId(type = IdType.AUTO) //自增");
                } else if (type.equals("bigint")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                } else if (type.equals("varchar") || type.equals("char")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                }
            }
            // 字段对应数据库字段 ==> 处理 添加mysql 关键字映射，mysql关键字配置: GenerateConfig.KEYWORD_ARRAY
            if (GenerateConfig.KEYWORD_ARRAY.contains(fieldName)) {
                fields.append("\r\n    @TableField(value = \"`" + fieldName + "`\")");
            } else {
                fields.append("\r\n    @TableField(value = \"" + fieldName + "\")");
            }
            // 字段,转为驼峰模式
            fieldName = GenerateDataProcessing.getFieldName(fieldName);
            //字段
            JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        XjGenerateController.pathMap.put("entity", brBwPath.get("path").toString());
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
