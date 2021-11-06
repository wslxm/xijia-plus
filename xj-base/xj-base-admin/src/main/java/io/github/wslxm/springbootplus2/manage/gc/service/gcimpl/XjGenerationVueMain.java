package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.template.VueMainTemplate;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationVueMain extends BaseGcImpl implements XjGcSevice {

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
    public void run(List<DbFieldPO> dataList, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Vue");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        // 数据表格字段
        StringBuffer vueInfoColumns = new StringBuffer(" ");
        for (DbFieldPO fieldMap : dataList) {
            // 未勾选的字段过滤
            if (isChecked(fieldMap)) {
                continue;
            }
            String name = GenerateDataProcessing.getFieldName(fieldMap.getName());
            String newDesc = getDesc(fieldMap.getDesc());
            // 判断是否需要生成查询
            boolean searchBoolean = fieldMap.getSearch() != null ? fieldMap.getSearch() : false;
            Object vueFieldType = fieldMap.getVueFieldType();
            Integer vueFieldTypeInt = (vueFieldType != null) ? Integer.parseInt(vueFieldType.toString()) : -1;
            if (vueFieldTypeInt.equals(Base.VueFieldType.V4.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V6.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V7.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V9.getValue())
            ) {
                vueInfoColumns.append(VueMainTemplate.TEXT_DICT
                        .replaceAll("\\{label}", newDesc)
                        .replace("{prop}", name)
                        .replace("{search}", searchBoolean + "")
                );
            } else if (vueFieldTypeInt.equals(Base.VueFieldType.V5.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V8.getValue())
            ) {
                vueInfoColumns.append(VueMainTemplate.TEXT_DICT_CHECKBOX
                        .replaceAll("\\{label}", newDesc)
                        .replace("{prop}", name)
                        .replace("{search}", searchBoolean + "")
                );
            } else if (vueFieldTypeInt.equals(Base.VueFieldType.V13.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V14.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V15.getValue())
            ) {
                vueInfoColumns.append(VueMainTemplate.IMG
                        .replaceAll("\\{label}", newDesc)
                        .replace("{prop}", name)
                );
            } else {
                vueInfoColumns.append(VueMainTemplate.TEXT
                        .replaceAll("\\{label}", newDesc)
                        .replace("{prop}", name)
                        .replace("{search}", searchBoolean + "")
                );
            }
        }
        // 数据保存
        GenerateConfig.VUE_INFO_COLUMNS = vueInfoColumns.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerationSeviceImpl.pathMap.put("vueMain", brBwPath.get("path").toString());
    }

}
