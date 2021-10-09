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
public class XjGenerationEntity extends BaseGcImpl implements XjGenerationSevice {


    @Override
    public void run(List<Map<String, Object>> data, String path) {
        // 获取文件内容
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "");
        // 处理参数
        this.generateParameters(data);
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 返回路径
        XjGenerateController.pathMap.put("entity", getBaseUrl(request) + "/" + brBwPath.get("path").toString());
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

            // 1、生成注释
            if (GenerateConfig.entitySwagger) {
                // 字段注释信息-->  Swagger2 模式
                fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            } else {
                // 字段注释信息-->  doc 注释
                fields.append("\r\n    /** \r\n     * " + fieldMap.get("desc") + " \r\n     */");
            }

            // 2、处理id主键, 字段名为id的 添加id主键注解, 字段类型为 int 默认自增, bigint+varchar 默认雪花算法
            if ("id".equals(fieldName)) {
                // id生成策略
                if (type.equals("int")) {
                    fields.append("\r\n    @TableId(type = IdType.AUTO) //自增");
                } else if (type.equals("bigint") || type.equals("varchar") || type.equals("char")) {
                    fields.append("\r\n    @TableId(type = IdType.ASSIGN_ID) //雪花算法");
                }
            }

            // 3、字段对应数据库字段 ==> 处理 添加mysql 关键字映射，mysql关键字配置: GenerateConfig.KEYWORD_ARRAY
            if (GenerateConfig.KEYWORD_ARRAY.contains(fieldName)) {
                fields.append("\r\n    @TableField(value = \"`" + fieldName + "`\")");
            } else {
                fields.append("\r\n    @TableField(value = \"" + fieldName + "\")");
            }

            // 4、生成字段
            fields.append("\r\n    " + super.JXModel(fieldName, type) + "\r\n");
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
    }

}
