package com.ws.ldy.modules.gc.util;

/**
 * 搜索Html 模板配置
 */
public class LayuiTemplate {

    /**
     * 单选
     * {desc} 字段描叙
     * {id}   字段名
     * {name} 字段名
     */
    public static String INPUT_PT = "    <div class=\"layui-inline\">\n" +
            "        <label class=\"layui-form-label\">{desc}:</label>\n" +
            "        <div class=\"layui-input-block\">\n" +
            "            <input type=\"text\" id=\"{id}\" name=\"{name}\" placeholder=\"请输入{desc}...\" autocomplete=\"off\" class=\"layui-input\">\n" +
            "        </div>\n" +
            "    </div>";

    /**
     * 下拉选择框
     * {desc} 字段描叙
     * {id}   字段名
     * {name} 字段名
     */
    public static String selectPt = "< div class=\"layui-inline\">\n" +
            "            <label class=\"layui-form-label\">{desc}</label>\n" +
            "            <div class=\"layui-input-block\">\n" +
            "                <select id=\"{id}\"name=\"{name}\">\n" +
            "                    <option value=\"0\">不限</option>\n" +
            "                    <option value=\"1\">搜索值一</option>\n" +
            "                    <option value=\"2\">搜索值二</option>\n" +
            "                </select>\n" +
            "            </div>\n" +
            "        </div>";


    /**
     * 时间段选择
     * {desc} 字段描叙
     * {id}   字段名
     * {name} 字段名
     */
//    public String datePt = "< div class=\"layui-inline\">\n" +
//            "            <label class=\"layui-form-label\">{desc}</label>\n" +
//            "            <div class=\"layui-input-block\">\n" +
//            "                <select id=\"{id}\"name=\"{name}\">\n" +
//            "                    <option value=\"0\">不限</option>\n" +
//            "                    <option value=\"1\">搜索值一</option>\n" +
//            "                    <option value=\"2\">搜索值二</option>\n" +
//            "                </select>\n" +
//            "            </div>\n" +
//            "        </div>";
}
