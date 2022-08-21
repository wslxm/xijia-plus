package io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl;

import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.GcSevice;
import io.github.wslxm.springbootplus2.manage.gc.template.VueMainTemplate;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("all")
@Component
public class GcVueMain extends BaseGcImpl implements GcSevice {

    /**
     * 模板key
     */
    public static final String KEY_NAME = "X-Vue";


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
    public void run(GcConfig gcConfig){
        // 数据表格字段
        StringBuffer vueInfoColumns = new StringBuffer(" ");
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
        for (DbFieldPO dbFieldPO : dbFields) {
            // 未勾选的字段过滤
            if (!isChecked(dbFieldPO)) {
                continue;
            }
            Object vueFieldType = dbFieldPO.getVueFieldType();

            // 指定类型字段不生成到列表中，同时排除list接口查询
            List<Integer> vueFieldTypeArray = JSON.parseObject(gcConfig.getDefaultTemplateParam("vueFieldTypesArray"), List.class);
            if (vueFieldTypeArray.contains(vueFieldType+"")) {
                continue;
            }

            String name = GcDataUtil.getFieldName(gcConfig, dbFieldPO.getName());
            String newDesc = getDesc(dbFieldPO.getDesc());
            // 判断是否需要生成查询
            boolean isSearch = dbFieldPO.getIsSearch() == null ? false :  dbFieldPO.getIsSearch();


            Integer vueFieldTypeInt = (vueFieldType != null) ? Integer.parseInt(vueFieldType.toString()) : -1;
            if (vueFieldTypeInt.equals(Base.VueFieldType.V4.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V6.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V7.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V9.getValue())
            ) {
                vueInfoColumns.append(VueMainTemplate.TEXT_DICT
                        .replaceAll("\\{label}", newDesc)
                        .replace("{prop}", name)
                        .replace("{search}", isSearch + "")
                        .replace("{dictCode}", getDictCode(dbFieldPO.getDictCode()))
                );
            } else if (vueFieldTypeInt.equals(Base.VueFieldType.V5.getValue())
                    || vueFieldTypeInt.equals(Base.VueFieldType.V8.getValue())
            ) {
                vueInfoColumns.append(VueMainTemplate.TEXT_DICT_CHECKBOX
                        .replaceAll("\\{label}", newDesc)
                        .replace("{prop}", name)
                        .replace("{search}", isSearch + "")
                        .replace("{dictCode}", getDictCode(dbFieldPO.getDictCode()))
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
                        .replace("{search}", isSearch + "")
                );
            }
        }
        // 数据保存
        gcConfig.setTemplateParam("vueInfoColumns", vueInfoColumns.toString());
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, KEY_NAME));
    }

}