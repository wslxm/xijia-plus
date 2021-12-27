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
public class XjGenerationVueUpd extends BaseGcImpl implements XjGcSevice {


    /**
     * 生成Html-Upd 修改页
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(List<DbFieldPO> dataList, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "VueUpd");
        BufferedReader br = (BufferedReader) brBwPath.get("br");
        BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
        StringBuffer vueUpdColumns = new StringBuffer("");
        for (DbFieldPO fieldMap : dataList) {
            // 未勾选的字段过滤
            if (isChecked(fieldMap)) {
                continue;
            }
            // 生成字段
            String vueColumn = jxVueColumns(fieldMap.getName(),
                    fieldMap.getType(),
                    fieldMap.getTypeDetail(),
                    getDesc(fieldMap.getDesc()),
                    fieldMap.getVueFieldType());
            vueUpdColumns.append(vueColumn);
        }
        // 数据保存
        GenerateConfig.VUE_UPD_COLUMNS = vueUpdColumns.toString();
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        XjGenerationSeviceImpl.pathMap.put("vueMainUpd", brBwPath.get("path").toString());
    }
}
