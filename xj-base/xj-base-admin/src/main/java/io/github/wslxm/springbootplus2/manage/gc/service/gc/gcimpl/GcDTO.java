package io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl;

import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.GcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("all")
@Component
public class GcDTO extends BaseGcImpl implements GcSevice {

    /**
     * 模板key
     */
    public static final String KEY_NAME = "X-DTO";

    @Override
    public void run(GcConfig gcConfig) {
        // 数据拼接(所有字段)
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
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
            Object checked = fieldMap.getChecked();      // 兼容layui
            Object isChecked = fieldMap.getIsChecked();  // 兼容vue
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
            Boolean entitySwagger = Boolean.valueOf(gcConfig.getDefaultTemplateParam("entitySwagger"));
            if (entitySwagger) {
                // 字段注释信息-->  Swagger2 模式
                fields.append("\r\n    @ApiModelProperty(value = \"" + desc + "\" ,position = " + position++ + ")");
            } else {
                // 字段注释信息-->  doc 注释
                fields.append("\r\n    /** \r\n     * " + desc + " \r\n     */");
            }

            // 2、生成必填参数jsr验证(先判断是否为必填参数)
            String isNull = fieldMap.getIsNull();
            if (("NO").equals(isNull)) {
                String jsrModel = super.jsrModel(type, typeDetail, desc);
                if (jsrModel != null) {
                    fields.append("\r\n    " + jsrModel);
                }
            }
            // 3、生成字段
            fields.append("\r\n    " + super.jxModel(gcConfig, fieldName, type) + "\r\n");
        }
        // 数据保存到替换对象类,使模板中可以读取
        gcConfig.setTemplateParam("entitys", fields.toString());
        gcConfig.setTemplateParam("serialVersionUID", IdUtil.snowflakeId());
    }
}
