package io.github.wslxm.springbootplus2.manage.gc.template;

/**
 *  @author wangsong
 * 复选Html 模板配置
 */
public class LayuiMainTemplate {


    /**
     * 普通字段
     * {fieldTitle} 字段描叙
     * {fieldId} 字段名
     */
    public static final String TABLE_FIELD_PT = "\r\n                    {field: '{fieldId}', title: '{fieldTitle}'},";

    /**
     * 字典字段
     * {fieldTitle} 字段描叙
     * {fieldId} 字段名
     */
    public static final String TABLE_FIELD_ENUM_PT = "\r\n                    {field: '{fieldId}', title: '{fieldTitle}', templet: function (res) {\n" +
            "                            if( res.{fieldId} == null){\n" +
            "                                return \"\";\n" +
            "                            }\n" +
            "                            let codes = res.{fieldId}+\"\".split(\",\");\n" +
            "                            let html =\"\";\n" +
            "                            for (let i = 0; i < codes.length; i++) {\n" +
            "                                html += Dict.convert(Enums.Base.Default, codes[i]) + \"  \";\n" +
            "                            }\n" +
            "                            return html;\n" +
            "                         }\n" +
            "                    },";


    /**
     * 图片字段
     * {fieldTitle} 字段描叙
     * {fieldId} 字段名
     */

    public static final String TABLE_FIELD_PIC_PT = "\r\n                    {field: '{fieldId}', title: '{fieldId}', templet: function (res) {\n" +
            "                            if( res.{fieldId} == null){\n" +
            "                                return \"\";\n" +
            "                            }\n" +
            "                            let imgs = res.{fieldId}.split(\",\");\n" +
            "                            let html =\"\";\n" +
            "                            for (let i = 0; i < imgs.length; i++) {\n" +
            "                                html += \"<img src='\" + imgs[i] + \"'  style='height: 50px;width: 60px;margin-left: 10px'>\";\n" +
            "                            }\n" +
            "                            return html;\n" +
            "                        }\n" +
            "                    },";






}
