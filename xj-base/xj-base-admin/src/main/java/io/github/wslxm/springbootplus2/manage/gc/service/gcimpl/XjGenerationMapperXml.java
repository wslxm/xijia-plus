package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import io.github.wslxm.springbootplus2.manage.gc.util.TemplateParamsReplace;
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
    public void run(List<DbFieldPO> data,String templatesPath, String path,String suffix) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(templatesPath,path,suffix);

        GenerateConfig.RESULT_MAP = resultXml(data);
        GenerateConfig.COLUMN_LIST = columnXml(data);
        GenerateConfig.XML_INSERT = insertXml(data);
        GenerateConfig.XML_UPD = updateXml(data);
        // 开始生成文件并进行数据替换
        TemplateParamsReplace.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerationSeviceImpl.pathMap.put("mapperXml",  brBwPath.get("path").toString());
    }


    /**
     * 生成添加sql
     * @author wangsong
     * @date 2021/7/13 0013 11:26
     * @return java.lang.String
     * @version 1.0.1
     */
    private String insertXml(List<DbFieldPO> data) {
        StringBuffer insertList = new StringBuffer("            insert into " + GenerateConfig.TABLE_NAME + "(");
        insertList.append("\r\n            <trim suffixOverrides=\",\">");
        // 拼接字段 key
        for (int i = 0; i < data.size(); i++) {
            DbFieldPO fieldMap = data.get(i);
            String type = fieldMap.getType();                                               // 字段类型
            String fieldNameHump = GenerateDataProcessing.getFieldName(fieldMap.getName());  // 字段名驼峰
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
            String fieldNameHump = GenerateDataProcessing.getFieldName(fieldMap.getName());
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
    private String updateXml(List<DbFieldPO> data) {
        StringBuffer updateList = new StringBuffer("            update " + GenerateConfig.TABLE_NAME);
        updateList.append("\r\n            <set>");
        for (int i = 0; i < data.size(); i++) {
            DbFieldPO fieldMap = data.get(i);
            // 字段名
            String fieldName = fieldMap.getName();
            // 驼峰字段名
            String fieldNameHump = GenerateDataProcessing.getFieldName(fieldName);
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
    private String resultXml(List<DbFieldPO> data) {
        StringBuffer resultMap = new StringBuffer();
        for (DbFieldPO fieldMap : data) {
            //字段名
            String fieldName = fieldMap.getName();
            //驼峰字段名
            String fieldNameHump = GenerateDataProcessing.getFieldName(fieldName);
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
