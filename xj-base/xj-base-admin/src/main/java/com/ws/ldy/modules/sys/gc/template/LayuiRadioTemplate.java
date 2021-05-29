package com.ws.ldy.modules.sys.gc.template;

/**
 * 单选Html 模板配置
 */
public class LayuiRadioTemplate {


    /**
     *
     * {fieldName} 字段描叙
     * {fieldId} 字段名
     */
    public static final String INPUT_RADIO_PT = "    <div class=\"layui-form-item\">\n" +
            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
            "        <div id=\"{fieldId}\" class=\"layui-input-block\">\n" +
            "        </div>\n" +
            "    </div>";


    /**
     * Radio js部分， 直接获取字典code 数据（默认值 Base.Default，生成后自行创建枚举字段进行修改，主要用于生成单选内的每一项数据，使用字典动态生成
     * {fieldId}     字段名称
     * 编辑时需要 parent.data.{fieldId} 回显内容，添加时设置为null
     */
    public static final String RADIO_CODE_ADD_JS = "        $(\"#{fieldId}\").html(Dict.getDictRadio(Enums.Base.Default, \"{fieldId}\", null));";
    public static final String RADIO_CODE_UPD_JS = "        $(\"#{fieldId}\").html(Dict.getDictRadio(Enums.Base.Default, \"{fieldId}\", parent.data.{fieldId}));";

}
