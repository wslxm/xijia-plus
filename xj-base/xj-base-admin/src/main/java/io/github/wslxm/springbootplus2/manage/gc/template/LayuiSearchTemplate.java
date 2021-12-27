package io.github.wslxm.springbootplus2.manage.gc.template;

/**
 *  @author wangsong
 * 搜索Html 模板配置
 */
public class LayuiSearchTemplate {


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
     * 搜索
     * {desc} 字段描叙
     * {id}   字段名
     */
    public static final String TABLE_SEARCH_ENUM_PT = " <div class=\"layui-inline\">\n" +
            "        <label class=\"layui-form-label\">{desc}:</label>\n" +
            "        <div class=\"layui-input-block\">\n" +
            "            <div class=\"layui-input-inline\">\n" +
            "                <select id=\"{id}\" lay-filter=\"{id}\">\n" +
            "                </select>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>";


    /**
     * 搜索值为字典时
     * {id}   字段名
     */
    public static final String RADIO_SEARCH_CODE_JS = "        $(\"#{id}\").html(Dict.getDictSelect(Enums.Base.Default, \"\", \"全部\", null)); ";

}
