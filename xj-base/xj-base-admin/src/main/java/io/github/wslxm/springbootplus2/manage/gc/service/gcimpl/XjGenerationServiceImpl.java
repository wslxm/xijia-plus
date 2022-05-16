package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("all")
@Component
public class XjGenerationServiceImpl extends BaseIServiceImpl implements XjGcSevice {


    /**
     * 生成ServiceImpl
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(GcConfig gcConfig, String keyName) {
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
        this.generateParameters(gcConfig, dbFields);
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, keyName));
    }


    private void generateParameters(GcConfig gcConfig, List<DbFieldPO> data) {
        // MybatisPlus搜索条件数据拼接
        StringBuffer findPageMybatisPlus = new StringBuffer("");

        // 富文本字段 查询时排除该字段
        StringBuffer excludeReturn = new StringBuffer("");
        // 处理参数
        for (DbFieldPO fieldMap : data) {
            String fieldName = fieldMap.getName();
            String type = fieldMap.getType();
            String desc = fieldMap.getDesc();
            Object search = fieldMap.getIsSearch();
            Integer vueFieldType = fieldMap.getVueFieldType();
            String tableNameUp = gcConfig.getDefaultTemplateParam("tableNameUp");

            // 指定类型字段不生成到列表中，同时排除list接口查询
            List<String> vueFieldTypeArray = JSON.parseObject(gcConfig.getDefaultTemplateParam("vueFieldTypesArray"), List.class);
            if (vueFieldTypeArray.contains(vueFieldType+"")) {
                if (excludeReturn.toString().equals("")) {
                    excludeReturn.append("                ");
                    excludeReturn.append(".select("+tableNameUp+".class, info -> !\"" + fieldName + "\".equals(info.getColumn())");
                } else {
                    excludeReturn.append("\r\n");
                    excludeReturn.append("                         ");
                    excludeReturn.append(" && !\"" + fieldName + "\".equals(info.getColumn())");
                }
            }

            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            // 字段映射成驼峰模式,在首字母大写
            fieldName = GcDataUtil.getFieldName(gcConfig, fieldName);
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);


            //字段  // !=null StringUtils.isNotBlank(account)
            findPageMybatisPlus.append("                ");
            if (type.equals("int") || type.equals("bigint") || type.equals("tinyint")) {
                // 整数 int/long/tinyint
                findPageMybatisPlus.append(".eq(query.get" + fieldName + "() != null, " + tableNameUp + "::get" + fieldName + ", query.get" + fieldName + "())");
            } else if (type.equals("double") || type.equals("float") || type.equals("decimal")) {
                // 单精度小数 Float / 双精度小数 Double / decimal等
                findPageMybatisPlus.append(".eq(query.get" + fieldName + "() != null, " + tableNameUp + "::get" + fieldName + ", query.get" + fieldName + "())");
            } else if (type.equals("varchar") || type.equals("char") || type.equals("text") || type.equals("longtext")) {
                // 字符串 / 大文本、超大文本
                findPageMybatisPlus.append(".eq(StringUtils.isNotBlank(query.get" + fieldName + "()), " + tableNameUp + "::get" + fieldName + ", query.get" + fieldName + "())");
            } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                // 时间
                findPageMybatisPlus.append(".eq(query.get" + fieldName + "() != null, " + tableNameUp + "::get" + fieldName + ", query.get" + fieldName + "())");
            }
            findPageMybatisPlus.append("\r\n");
        }

        gcConfig.setTemplateParam("findPageMybatisPlus", excludeReturn.toString() + ")\r\n" + findPageMybatisPlus.toString());
    }
}
