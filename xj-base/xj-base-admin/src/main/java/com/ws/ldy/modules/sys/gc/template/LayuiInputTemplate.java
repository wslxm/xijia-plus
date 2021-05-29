package com.ws.ldy.modules.sys.gc.template;

/**
 * 搜索Html 模板配置
 */
public class LayuiInputTemplate {


    /**
     * html 添加页的每一个字段 （添加页+编辑页）
     */
    public static final String INPUT_HTML_PT = "    <div class=\"layui-form-item\">\n" +
            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
            "        <div class=\"layui-input-inline\">\n" +
            "            <input type=\"{inputType}\" id=\"{fieldId}\" name=\"{fieldName}\" lay-verify=\"required\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\" {integerVerification}>\n" +
            "        </div>\n" +
            "    </div>";


    /**
     *  html 添加页的每一个字段（添加页+编辑页）整数验证让输入框只能输入整数不能输入小数
     */
    public static final String INTEGER_VERIFICATION = "onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\"\n" +
            " onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\"";


}
