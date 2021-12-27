package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.constant.FieldTypeConstant;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationEntity extends BaseGcImpl implements XjGcSevice {


    @Override
    public void run(List<DbFieldPO> data, String path) {
        // 获取文件内容
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "");
        // 处理参数
        this.generateParameters(data);
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 返回路径
        XjGenerationSeviceImpl.pathMap.put("entity", brBwPath.get("path").toString());
    }


    private void generateParameters(List<DbFieldPO> data) {
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (DbFieldPO fieldMap : data) {
            // 未勾选的字段过滤
            // 兼容layui
            Object checked = fieldMap.getChecked();
            // 兼容vue
            Object isChecked = fieldMap.getIsChecked();
            if (checked != null && !Boolean.parseBoolean(checked.toString())) {
                continue;
            }
            if (isChecked != null && !Boolean.parseBoolean(isChecked.toString())) {
                continue;
            }
            String type = fieldMap.getType();
            String desc = fieldMap.getDesc();
            String fieldName = fieldMap.getName();
            String typeDetail = fieldMap.getTypeDetail();

            // 1、生成注释
            if (GenerateConfig.entitySwagger) {
                // 字段注释信息-->  Swagger2 模式
                fields.append("\r\n    @ApiModelProperty(notes = \"" + desc + "\" ,position = " + position++ + ")");
            } else {
                // 字段注释信息-->  doc 注释
                fields.append("\r\n    /** \r\n     * " + desc + " \r\n     */");
            }

            // 2、处理id主键, 字段名为id的 添加id主键注解, 字段类型为 int 默认自增, bigint+varchar 默认雪花算法
            if ("id".equals(fieldName)) {
                // id生成策略
                if (type.equals(FieldTypeConstant.INT)) {
                    fields.append("\r\n    @TableId(type = IdType.AUTO) //自增");
                } else if (type.equals(FieldTypeConstant.BIGINT) || type.equals(FieldTypeConstant.VARCHAR)
                        || type.equals(FieldTypeConstant.CHAR)) {
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
            fields.append("\r\n    " + super.jxModel(fieldName, type) + "\r\n");
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
    }

}
