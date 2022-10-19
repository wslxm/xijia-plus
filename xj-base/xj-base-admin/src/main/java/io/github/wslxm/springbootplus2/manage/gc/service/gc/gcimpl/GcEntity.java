package io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl;

import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateProperties;
import io.github.wslxm.springbootplus2.manage.gc.constant.FieldTypeConstant;
import io.github.wslxm.springbootplus2.manage.gc.constant.TpParamConstant;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.GcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("all")
@Component
public class GcEntity extends BaseGcImpl implements GcSevice {

    /**
     * 模板key
     */
    public static final String KEY_NAME = "X-Entity";

    @Autowired
    private GenerateProperties generateProperties;


    @Override
    public void run(GcConfig gcConfig) {
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
        //数据拼接(所有字段)
        this.generateParameters(gcConfig, dbFields);
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, KEY_NAME));
    }


    private void generateParameters(GcConfig gcConfig, List<DbFieldPO> data) {

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
            Boolean entitySwagger = Boolean.valueOf(gcConfig.getDefaultTemplateParam(TpParamConstant.ENTITY_SWAGGER));
            if (entitySwagger) {
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
            List<String> keywordArray = JSON.parseObject(gcConfig.getDefaultTemplateParam("keywordArray"), List.class);
            // 3、字段对应数据库字段 ==> 处理 添加mysql 关键字映射，mysql关键字配置: GenerateConfig.KEYWORD_ARRAY
            if (keywordArray.contains(fieldName)) {
                fields.append("\r\n    @TableField(value = \"`" + fieldName + "`\")");
            } else {
                fields.append("\r\n    @TableField(value = \"" + fieldName + "\")");
            }

            // 4、生成字段
            fields.append("\r\n    " + super.jxModel(gcConfig,fieldName, type,false) + "\r\n");
        }
        // 数据保存到替换对象类,使模板中可以读取
        gcConfig.setTemplateParam("entitys", fields.toString());
        gcConfig.setTemplateParam("serialVersionUID", IdUtil.snowflakeId());
    }

}
