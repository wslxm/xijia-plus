package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
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
    public void run(List<DbFieldPO> dataList, String path) {

        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VueAdd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        //
        StringBuffer vueAddColumnsDefault = new StringBuffer("");
        StringBuffer vueAddColumns = new StringBuffer("");
        //
        for (DbFieldPO fieldMap : dataList) {
            // 未勾选的字段过滤
            if (isChecked(fieldMap)) {
                continue;
            }
            // 不生成id
            if ("id".equals(fieldMap.getName())) {
                continue;
            }
            // 生成字段默认值
            String name = GenerateDataProcessing.getFieldName(fieldMap.getName());
            vueAddColumnsDefault.append("                    " + name + ": " + null + ",\n");
            // 生成字段
            String vueColumn = JXVueColumns(fieldMap.getName(), getDesc(fieldMap.getDesc()), fieldMap.getVueFieldType());
            vueAddColumns.append(vueColumn);
        }
        // 数据保存
        GenerateConfig.VUE_ADD_COLUMNS_DEFAULT = vueAddColumnsDefault.toString();
        GenerateConfig.VUE_ADD_COLUMNS = vueAddColumns.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // url保存
        XjGenerationSeviceImpl.pathMap.put("vueMainAdd", brBwPath.get("path").toString());
    }
}
