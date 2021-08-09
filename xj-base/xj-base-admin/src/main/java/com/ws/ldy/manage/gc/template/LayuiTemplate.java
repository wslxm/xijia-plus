package com.ws.ldy.manage.gc.template;//package com.ws.ldy.modules.sys.gc.template;
//
///**
// * 搜索Html 模板配置
// */
//public class LayuiTemplate {
//
//
//    /**
//     * html 添加页的每一个字段  min="1" max="10"
//     */
//    public static final String INPUT_HTML_PT = "    <div class=\"layui-form-item\">\n" +
//            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
//            "        <div class=\"layui-input-inline\">\n" +
//            "            <input type=\"{inputType}\" id=\"{fieldId}\" name=\"{fieldName}\" lay-verify=\"required\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\" {integerVerification}>\n" +
//            "        </div>\n" +
//            "    </div>";
//
//    /**
//     * 整数验证--让输入框number 只能输入整数
//     */
//    public static final String INTEGER_VERIFICATION = "onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\"\n" +
//            " onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\"";
//
//
//    /**
//     * 搜索
//     * {desc} 字段描叙
//     * {id}   字段名
//     * {name} 字段名
//     */
//    public static final String INPUT_SEARCH_PT = "    <div class=\"layui-inline\">\n" +
//            "        <label class=\"layui-form-label\">{desc}:</label>\n" +
//            "        <div class=\"layui-input-block\">\n" +
//            "            <input type=\"text\" id=\"{id}\" name=\"{name}\" placeholder=\"请输入{desc}...\" autocomplete=\"off\" class=\"layui-input\">\n" +
//            "        </div>\n" +
//            "    </div>";
//
//
//    /**
//     * 时间日期选择器Html 部分
//     * {fieldTitle} 字段描叙
//     * {fieldId}   字段名
//     * {fieldName} 字段名
//     */
//    public static final String DATE_HTML_PT = "    <div class=\"layui-form-item\">\n" +
//            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
//            "        <div class=\"layui-inline\">\n" +
//            "            <div class=\"layui-input-inline\">\n" +
//            "                <input type=\"text\" class=\"layui-input\" id=\"{fieldId}\" name=\"{fieldName}\" placeholder=\"yyyy-MM-dd HH:mm:ss\">\n" +
//            "            </div>\n" +
//            "        </div>\n" +
//            "    </div>";
//
//
//    /**
//     * 时间日期选择器js 部分
//     * {fieldTitle} 字段描叙
//     * {fieldId} 字段名
//     */
//    public static final String DATE_JS_PT = "         //{fieldTitle}\n" +
//            "         layui.laydate.render({\n" +
//            "              elem: '#{fieldId}'\n" +
//            "              ,type: 'datetime'\n" +
//            "              ,trigger: 'click'\n" +
//            "              ,position : 'fixed'\n" +
//            "         });";
//
//
//    /**
//     * 图片上传 HTML 部分
//     * {fieldTitle}
//     * {fieldId}
//     * {fieldName}
//     */
//    public static final String ADD_UPD_PIC_HTML = "    <div class=\"layui-form-item\">\n" +
//            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
//            "        <span class=\"layui-input-inline\">\n" +
//            "            <button type=\"button\" class=\"layui-btn layui-btn-danger\" id=\"{fieldId}Upload\" style=\"width: 180px\"><i class=\"layui-icon\">\uE67C</i>上传图片</button>\n" +
//            "            <!-- 预览图,上传图片后展示 --><!--圆  style='border-radius: 30px;width: 35px;height: 35px;'-->\n" +
//            "            <img src=\"\" title=\"\" id=\"{fieldId}Show\" style=\"height: 180px; margin-top: 5%\">\n" +
//            "            <!-- 隐藏input,最后添加数据 -->\n" +
//            "            <input type=\"text\" id=\"{fieldId}\" name=\"{fieldName}\" lay-verify=\"{fieldId}\" placeholder=\"请输入\" autocomplete=\"off\" hidden>\n" +
//            "        </span>\n" +
//            "        <!-- 提示 -->\n" +
//            "        <font class=\"layui-word-aux\">\n" +
//            "            上传图片必须小于 3M\n" +
//            "        </font>\n" +
//            "    </div>";
//
//
//    /**
//     * 图片上传 js 部分
//     * {fieldTitle}
//     * {fieldId}
//     */
//    public static final String ADD_UPD_PIC_JS = "        // {fieldTitle} 图片默认隐藏\n" +
//            "        $(\"#{fieldId}Show\").hide();\n" +
//            "        // 文件上传--设定文件大小限制\n" +
//            "        layui.upload.render({\n" +
//            "            elem: '#{fieldId}Upload'\n" +
//            "            , url: uploadPath + '?filePath=image/generate/' // 改成您自己的上传接口\n" +
//            "            , size: 3072 // 限制文件大小，单位 KB\n" +
//            "            , headers: {\"TOKEN\": getGlobalHeaders()}\n" +
//            "            , done: function (res) {\n" +
//            "                //如果上传失败\n" +
//            "                if (res.code !== 200) {\n" +
//            "                    return layer.msg('上传失败-' + res.msg);\n" +
//            "                }\n" +
//            "                // 显示图片\n" +
//            "                $(\"#{fieldId}Show\").attr(\"src\", res.data);\n" +
//            "                $(\"#{fieldId}Show\").attr(\"title\", res.date);\n" +
//            "                $(\"#{fieldId}Show\").show();\n" +
//            "                // 赋值input\n" +
//            "                $(\"#{fieldId}\").val(res.data);\n" +
//            "                console.log(res);\n" +
//            "                layer.msg('上传成功');\n" +
//            "            }\n" +
//            "        });";
//
//
//    /**
//     * 图片回显
//     * {fieldId}
//     */
//    public static final String ADD_UPD_PIC_SHOP_JS = "        $(\"#{fieldId}Show\").attr(\"src\", parent.data.{fieldId});\n" +
//            "        $(\"#{fieldId}Show\").attr(\"title\", parent.data.{fieldId});" ;
//
//
//
//  //  $("#isSkip").html(Dict.getDictRadio(Enums.Xj.BannerIsSkip));
//
//}
