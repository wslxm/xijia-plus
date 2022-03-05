package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * mapperXml 生成
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/7/13 0013 11:52
 * @version 1.0.1
 */
@SuppressWarnings("all")
@Component
public class XjGenerationMapperXml extends BaseIServiceImpl implements XjGcSevice {


    /**
     * 生成Dao 对应的xml
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(GcConfig gcConfig, String keyName) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(gcConfig, keyName);
        List<DbFieldPO> dbFields = gcConfig.getDbFields();
        gcConfig.setTemplateParam("resultMap", resultXml(gcConfig, dbFields));
        gcConfig.setTemplateParam("columnList", columnXml(dbFields));
        gcConfig.setTemplateParam("xmlInsert", insertXml(gcConfig, dbFields));
        gcConfig.setTemplateParam("xmlUpd", updateXml(gcConfig, dbFields));

    }


    /**
     * 生成添加sql
     * @author wangsong
     * @date 2021/7/13 0013 11:26
     * @return java.lang.String
     * @version 1.0.1
     */
    private String insertXml(GcConfig gcConfig, List<DbFieldPO> data) {

        String tableName = gcConfig.getDefaultTemplateParam("tableName");

        StringBuffer insertList = new StringBuffer("            insert into " + tableName + "(");
        insertList.append("\r\n            <trim suffixOverrides=\",\">");
        // 拼接字段 key
        for (int i = 0; i < data.size(); i++) {
            DbFieldPO fieldMap = data.get(i);
            String type = fieldMap.getType();                                               // 字段类型
            String fieldNameHump = GenerateDataProcessing.getFieldName(gcConfig, fieldMap.getName());  // 字段名驼峰
            // 拼接--动态添加sql
            if (type.equals("varchar") || type.equals("text")) {
                insertList.append("\r\n                <if test=\"" + fieldNameHump + " != null and " + fieldNameHump + "  != ''\">");
            } else {
                insertList.append("\r\n                <if test=\"" + fieldNameHump + " != null\">");
            }
            // 字段名(第一个不拼接逗号)
            insertList.append("\r\n                    `" + fieldMap.getName() + "`,");
            insertList.append("\r\n               </if>");
        }
        insertList.append("\r\n            </trim>");

        // 拼接字段值
        insertList.append("\r\n            )values(");
        insertList.append("\r\n            <trim suffixOverrides=\",\">");
        for (int i = 0; i < data.size(); i++) {
            DbFieldPO fieldMap = data.get(i);
            // 字段类型日
            String type = fieldMap.getType();
            String fieldNameHump = GenerateDataProcessing.getFieldName(gcConfig, fieldMap.getName());
            // 拼接--动态添加sql
            if (type.equals("varchar") || type.equals("text")) {
                insertList.append("\r\n                <if test=\"" + fieldNameHump + " != null and " + fieldNameHump + " != ''\">");
            } else {
                insertList.append("\r\n                <if test=\"" + fieldNameHump + " != null\">");
            }
            // 字段值(第一个不拼接逗号)
            insertList.append("\r\n                    #{" + fieldNameHump + "},");
            insertList.append("\r\n                </if>");
        }
        insertList.append("\r\n           </trim>");
        // 加最后的括号
        insertList.append("\r\n           )");
        return insertList.toString();
    }


    /**
     * 生成编辑sql
     * @author wangsong
     * @date 2021/7/13 0013 11:26
     * @return java.lang.String
     * @version 1.0.1
     */
    private String updateXml(GcConfig gcConfig, List<DbFieldPO> data) {
        String tableName = gcConfig.getDefaultTemplateParam("tableName");
        StringBuffer updateList = new StringBuffer("            update " + tableName);
        updateList.append("\r\n            <set>");
        for (int i = 0; i < data.size(); i++) {
            DbFieldPO fieldMap = data.get(i);
            // 字段名
            String fieldName = fieldMap.getName();
            // 驼峰字段名
            String fieldNameHump = GenerateDataProcessing.getFieldName(gcConfig, fieldName);
            // 字段类型日
            String type = fieldMap.getType();
            if (!fieldName.toLowerCase().equals("id")) {
                // 拼接--动态编辑sql
                if (type.equals("varchar") || type.equals("text")) {
                    updateList.append("\r\n                <if test=\"" + fieldNameHump + " != null and " + fieldNameHump + " != ''\">");
                } else {
                    updateList.append("\r\n                <if test=\"" + fieldNameHump + " != null\">");
                }
                // 字段值( <set> 可以不处理最后的逗号 )
                updateList.append("\r\n                    `" + fieldName + "` =  #{" + fieldNameHump + "},");
                updateList.append("\r\n                </if>");
            }
        }
        updateList.append("\r\n            </set>");
        updateList.append("\r\n            where id = #{id}");
        return updateList.toString();
    }


    /**
     * 生成通用返回数据
     * @author wangsong
     * @date 2021/7/13 0013 11:26
     * @return java.lang.String
     * @version 1.0.1
     */
    private String resultXml(GcConfig gcConfig, List<DbFieldPO> data) {
        String tableName = gcConfig.getDefaultTemplateParam("tableName");

        StringBuffer resultMap = new StringBuffer();
        for (DbFieldPO fieldMap : data) {
            //字段名
            String fieldName = fieldMap.getName();
            //驼峰字段名
            String fieldNameHump = GenerateDataProcessing.getFieldName(gcConfig, fieldName);
            if ("id".equals(fieldName)) {
                resultMap.append("\r\n        <id column=\"" + fieldName + "\" property=\"" + fieldNameHump + "\"/>");
            } else {
                resultMap.append("\r\n        <result column=\"" + fieldName + "\" property=\"" + fieldNameHump + "\"/>");
            }
        }
        return resultMap.toString();
    }


    /**
     * 生成 xml和实体类 映射关系数据
     * @author wangsong
     * @date 2021/7/13 0013 11:26
     * @return java.lang.String
     * @version 1.0.1
     */
    private String columnXml(List<DbFieldPO> data) {
        StringBuffer columnList = new StringBuffer();
        for (DbFieldPO fieldMap : data) {
            //字段名
            String fieldName = fieldMap.getName();
            columnList.append("\r\n        " + fieldName + ",");
        }
        return columnList.toString().substring(0, columnList.toString().length() - 1);
    }
}
