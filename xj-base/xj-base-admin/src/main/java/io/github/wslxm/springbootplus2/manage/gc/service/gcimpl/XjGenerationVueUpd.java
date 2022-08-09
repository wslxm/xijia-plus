package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("all")
@Component
public class XjGenerationVueUpd extends BaseGcImpl implements XjGcSevice {

    /**
     * 模板key
     */
    public static final String KEY_NAME = "X-VueUpd";


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
    public void run(GcConfig gcConfig) {
        StringBuffer vueUpdColumns = new StringBuffer("");
        StringBuffer vueAddColumnSlots = new StringBuffer("");
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
        for (DbFieldPO dbField : dbFields) {
            // 未勾选的字段过滤
            if (!isChecked(dbField)) {
                continue;
            }
            // 生成字段
            String vueColumn = jxVueColumns(
                    gcConfig,
                    dbField.getName(),
                    dbField.getType(),
                    dbField.getTypeDetail(),
                    getDesc(dbField.getDesc()),
                    dbField.getVueFieldType(),
                    dbField.getDictCode()
            );
            vueUpdColumns.append(vueColumn);

            // 生成字段 插槽
            String vueAddColumnSlot = jxVueColumnsSlot(gcConfig,dbField.getVueFieldType(), dbField.getName());
            vueAddColumnSlots.append(vueAddColumnSlot);
        }
        // 数据保存
        gcConfig.setTemplateParam("vueUpdColumns", vueUpdColumns.toString());
        gcConfig.setTemplateParam("vueUpdColumnSlots", vueAddColumnSlots.toString());
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, KEY_NAME));
    }
}
