package com.ws.ldy.modules.sys.gc.template;

/**
 * 搜索Html 模板配置
 */
public class LayuiPicTemplate {


    /**
     * 图片上传 HTML 部分（add+upd 页面）
     * {fieldTitle}
     * {fieldId}
     * {fieldName}
     */
    public static final String ADD_UPD_PIC_HTML = "    <div class=\"layui-form-item\">\n" +
            "        <label class=\"layui-form-label\">{fieldTitle}</label>\n" +
            "        <span class=\"layui-input-inline\">\n" +
            "            <button type=\"button\" class=\"layui-btn layui-btn-danger\" id=\"{fieldId}Upload\" style=\"width: 180px\"><i class=\"layui-icon\">\uE67C</i>上传图片</button>\n" +
            "            <!-- 预览图,上传图片后展示 --><!--圆  style='border-radius: 30px;width: 35px;height: 35px;'-->\n" +
            "            <img src=\"\" title=\"\" id=\"{fieldId}Show\" style=\"height: 180px; margin-top: 5%\">\n" +
            "            <!-- 隐藏input,最后添加数据 -->\n" +
            "            <input type=\"text\" id=\"{fieldId}\" name=\"{fieldName}\" lay-verify=\"{fieldId}\" placeholder=\"请输入\" autocomplete=\"off\" hidden>\n" +
            "        </span>\n" +
            "        <!-- 提示 -->\n" +
            "        <font class=\"layui-word-aux\">\n" +
            "            上传图片必须小于 3M\n" +
            "        </font>\n" +
            "    </div>";


    /**
     * 图片上传 js 部分（add+upd 页面）
     * {fieldTitle}
     * {fieldId}
     */
    public static final String ADD_UPD_PIC_JS = "        // {fieldTitle} 图片默认隐藏\n" +
            "        $(\"#{fieldId}Show\").hide();\n" +
            "        // 文件上传--设定文件大小限制\n" +
            "        layui.upload.render({\n" +
            "            elem: '#{fieldId}Upload'\n" +
            "            , url: uploadPath + '?filePath=image/generate/' // 改成您自己的上传接口\n" +
            "            , size: 3072 // 限制文件大小，单位 KB\n" +
            "            , headers: {\"TOKEN\": getGlobalHeaders()}\n" +
            "            , done: function (res) {\n" +
            "                //如果上传失败\n" +
            "                if (res.code !== 200) {\n" +
            "                    return layer.msg('上传失败-' + res.msg);\n" +
            "                }\n" +
            "                // 显示图片\n" +
            "                $(\"#{fieldId}Show\").attr(\"src\", res.data);\n" +
            "                $(\"#{fieldId}Show\").attr(\"title\", res.date);\n" +
            "                $(\"#{fieldId}Show\").show();\n" +
            "                // 赋值input\n" +
            "                $(\"#{fieldId}\").val(res.data);\n" +
            "                console.log(res);\n" +
            "                layer.msg('上传成功');\n" +
            "            }\n" +
            "        });";



    /**
     * 图片回显（在upd 页面）
     * {fieldId}
     */
    public static final String ADD_UPD_PIC_SHOP_JS = "        $(\"#{fieldId}Show\").attr(\"src\", parent.data.{fieldId});\n" +
            "        $(\"#{fieldId}Show\").attr(\"title\", parent.data.{fieldId});" ;

}
