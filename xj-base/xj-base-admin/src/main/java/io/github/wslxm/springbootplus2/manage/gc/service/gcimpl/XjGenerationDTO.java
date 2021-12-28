package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationDTO extends BaseGcImpl implements XjGcSevice {


    @Override
    public void run(List<DbFieldPO> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "DTO");
        //数据拼接(所有字段)
        this.generateParameters(data);
        // 数据保存到替换对象类,使模板中可以读取
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        XjGenerationSeviceImpl.pathMap.put("DTO",  brBwPath.get("path").toString());
    }


    private void generateParameters(List<DbFieldPO> data) {
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (DbFieldPO fieldMap : data) {
            // 未勾选的字段过滤
            Object checked = fieldMap.getChecked() ;      // 兼容layui
            Object isChecked = fieldMap.getIsChecked() ;  // 兼容vue
            if (checked !=null && !Boolean.parseBoolean(checked.toString())) {
                continue;
            }
            if (isChecked !=null && !Boolean.parseBoolean(isChecked.toString())) {
                continue;
            }
            String type = fieldMap.getType();
            String desc = fieldMap.getDesc();
            String fieldName =fieldMap.getName();
            String typeDetail = fieldMap.getTypeDetail();
            // 1、生成swagger注解
            fields.append("\r\n    @ApiModelProperty(value = \"" + desc + "\", position = " + (position++) + ")");
            // 2、生成必填参数jsr验证(先判断是否为必填参数)
            String isNull = fieldMap.getIsNull();
            if (("NO").equals(isNull)) {
                String jsrModel = super.jsrModel(type, typeDetail, desc);
                if(jsrModel!=null){
                    fields.append("\r\n    " + jsrModel);
                }
            }
            // 3、生成字段
            fields.append("\r\n    " + super.jxModel(fieldName, type) + "\r\n");
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
    }
}
