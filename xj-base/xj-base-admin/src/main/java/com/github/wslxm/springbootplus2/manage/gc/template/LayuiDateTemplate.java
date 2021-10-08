package com.github.wslxm.springbootplus2.manage.gc.template;

/**
 * 搜索Html 模板配置
 */
public class LayuiDateTemplate {


    /**
     * 搜索
     * {desc} 字段描叙
     * {id}   字段名
     * {name} 字段名
     */
    public static final String INPUT_SEARCH_PT = "    <div class=\"layui-inline\">\n" +
            "        <label class=\"layui-form-label\">{desc}:</label>\n" +
            "        <div class=\"layui-input-block\">\n" +
            "            <input type=\"text\" id=\"{id}\" name=\"{name}\" placeholder=\"请输入{desc}...\" autocomplete=\"off\" class=\"layui-input\">\n" +
            "        </div>\n" +
            "    </div>";


    /**
     * 时间日期选择器Html 部分
     * {fieldTitle} 字段描叙
     * {fieldId}   字段名
     * {fieldName} 字段名
     */
    public static final String DATE_HTML_PT = "    <div class=\"layui-form-item\">\n" +
            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
            "        <div class=\"layui-inline\">\n" +
            "            <div class=\"layui-input-inline\">\n" +
            "                <input type=\"text\" class=\"layui-input\" id=\"{fieldId}\" name=\"{fieldName}\" placeholder=\"yyyy-MM-dd HH:mm:ss\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";


    /**
     * 时间日期选择器js 部分
     * {fieldTitle} 字段描叙
     * {fieldId} 字段名
     */
    public static final String DATE_JS_PT = "         //{fieldTitle}\n" +
            "         layui.laydate.render({\n" +
            "              elem: '#{fieldId}'\n" +
            "              ,type: 'datetime'\n" +
            "              ,trigger: 'click'\n" +
            "              ,position : 'fixed'\n" +
            "         });";

}
