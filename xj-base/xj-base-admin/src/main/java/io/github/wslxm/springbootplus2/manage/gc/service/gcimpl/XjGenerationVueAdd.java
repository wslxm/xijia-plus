package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationVueAdd extends BaseGcImpl implements XjGcSevice {

    /**
     * 生成Html-Add 添加页
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(GcConfig gcConfig, String keyName){
        StringBuffer vueAddColumnsDefault = new StringBuffer("");
        StringBuffer vueAddColumns = new StringBuffer("");
        //
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
        for (DbFieldPO fieldMap : dbFields) {
            // 未勾选的字段过滤
            if (!isChecked(fieldMap)) {
                continue;
            }
            // 不生成id
            if ("id".equals(fieldMap.getName())) {
                continue;
            }
            // 生成字段默认值
            String name = GcDataUtil.getFieldName(gcConfig, fieldMap.getName());
            vueAddColumnsDefault.append("                    " + name + ": " + null + ",\n");
            // 生成字段
            String vueColumn = jxVueColumns(
                    gcConfig,
                    fieldMap.getName(),
                    fieldMap.getType() ,
                    fieldMap.getTypeDetail(),
                    getDesc(fieldMap.getDesc()),
                    fieldMap.getVueFieldType());
            vueAddColumns.append(vueColumn);
        }
        // 数据保存
        gcConfig.setTemplateParam("vueAddColumnsDefault",vueAddColumnsDefault.toString());
        gcConfig.setTemplateParam("vueAddColumns",vueAddColumns.toString());
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, keyName));
    }
}
