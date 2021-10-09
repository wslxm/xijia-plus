package io.github.wslxm.springbootplus2.manage.gc.template;

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
            "        $(\"#{fieldId}Show\").attr(\"title\", parent.data.{fieldId});";


    //=================================================================================
    //=================================================================================
    //============================= 多图上传 ==============================================
    //=================================================================================
    //=================================================================================
    //=================================================================================

    /**
     * 多图上传插件js 引入
     */
    public static final String ADD_UPD_PICS_INTRODUCE = "    <!-- 文件上传插件 -->\n" +
            "    <link rel=\"stylesheet\" href=\"/components/cropper/css/cropper.css\" media=\"all\">\n" +
            "    <link rel=\"stylesheet\" href=\"/components/cropper/css/publish.css\" media=\"all\">\n" +
            "    <script src=\"/components/cropper/js/croppers.js\"></script>\n" +
            "    <script src=\"/components/cropper/js/move.js\"></script>\n" +
            "    <script src=\"/components/cropper/js/publishImg.js\"></script>\n" +
            "    <script src=\"/components/cropper/js/cropper.js\"></script>\n" +
            "    <!-- 文件上传插件 -->";


    /**
     * 多图上传插件html
     * {fieldId}
     */
    public static final String ADD_UPD_PICS_HTML = "     <div id=\"{fieldId}Img\" class=\"layui-form-item\">\n" +
            "        <!-- 文件上传插件 -->\n" +
            "        <div class=\"layui-tab layui-tab-card\">\n" +
            "            <div class=\"layui-form\" style=\"padding: 20px 0 0 0;\">\n" +
            "                <div class=\"layui-form-item\">\n" +
            "                    <label class=\"layui-form-label\">{fieldTitle} </label>\n" +
            "                    <div class=\"layui-input-inline\">\n" +
            "                        <button type=\"button\" class=\"layui-btn\" id=\"{fieldId}AddZmImg\">多图片上传</button>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <ul id=\"{fieldId}ImgZmList\" style=\"width: 860px;position: relative;margin: 10px auto;min-height: 140px; border: none;\"></ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div id=\"cropperdiv\"></div>\n" +
            "        <!-- 文件上传插件 -->\n" +
            "    </div>";


    /**
     * 多图上传插件js 引入
     * {fieldId}
     */
    public static final String ADD_UPD_PICS_JS = "        // 多图片上传( addZmImg 支持图片删除,剪辑重传)\n" +
            "        layui.upload.render({\n" +
            "            elem: '#{fieldId}AddZmImg'\n" +
            "            , url: uploadPath + '?filePath=image/generate/'\n" +
            "            , multiple: true\n" +
            "            , headers: {\"TOKEN\": getGlobalHeaders()}\n" +
            "            , before: function (obj) {\n" +
            "                //预读本地文件示例，不支持ie8\n" +
            "                // obj.preview(function (index, file, result) {\n" +
            "                //     $('#{fieldId}ImgZmList').append('<li style=\"position:relative\"><img name=\"{fieldId}ImgZmList\" src=\"' + result + '\"width=\"150\" height=\"113\"><!--<div class=\"title_cover\" name=\"{fieldId}ImgZmName\" onclick=\"divClick(this)\"></div>--><div " +
            "class=\"img_edit " +
            "layui-icon\" " +
            "onclick=\"croppers_pic(this)\">\uE642</div><div class=\"img_close\" onclick=\"deleteElement(this)\">X</div></li>');\n" +
            "                //     form.render();\n" +
            "                //     imgMove(\"{fieldId}ImgZmList\");\n" +
            "                // });\n" +
            "            }\n" +
            "            , done: function (result) {\n" +
            "                //上传完毕\n" +
            "                //layer.msg(res.msg);\n" +
            "                console.log(\"上传文件：\" + result.data + \"  结果:\" + result.msg);\n" +
            "\n" +
            "                $('#{fieldId}ImgZmList').append('<li style=\"position:relative\"><img name=\"{fieldId}ImgZmList\" src=\"' + result.data +\n" +
            "                    '\"width=\"150\" height=\"113\"><!--<div class=\"title_cover\" name=\"{fieldId}ImgZmName\" onclick=\"divClick(this)\"></div>--><div class=\"img_edit layui-icon\" onclick=\"croppers_pic(this)\">\uE642</div><div class=\"img_close\" onclick=\"deleteElement(this)" +
            "\">X</div></li>');\n" +
            "                layui.form.render();\n" +
            "                imgMove(\"{fieldId}ImgZmList\");\n" +
            "            }\n" +
            "        });";


    /**
     * 多图上传提交参数处理(逗号分隔)
     * {fieldId}
     */
    public static final String ADD_UPD_PICS_SUBMIT_JS = "                data.field.{fieldId} =  getPicSort(\"{fieldId}ImgZmList\");\n;";


    /**
     * 编辑回显
     *  {fieldId}
     */
    public static final String UPD_PICS_ECHO_JS ="       if (parent.data.{fieldId} != null && parent.data.{fieldId} !== '') {\n" +
            "              let {fieldId}Array = parent.data.{fieldId}.split(\",\");\n" +
            "              let {fieldId}ImgHtml = \"\";\n" +
            "              for (let i = 0; i < {fieldId}Array.length; i++) {\n" +
            "                  {fieldId}ImgHtml += '<li style=\"position:relative\"><img name=\"{fieldId}ImgZmList\" src=\"' + {fieldId}Array[i] +\n" +
            "                          '\"width=\"150\" height=\"113\"><!--<div class=\"title_cover\" name=\"{fieldId}ImgZmName\" onclick=\"divClick(this)\"></div>--><div class=\"img_edit layui-icon\" onclick=\"croppers_pic(this)\">\uE642</div><div class=\"img_close\" onclick=\"deleteElement" +
            "(this)" +
            "\">X</div></li>';\n" +
            "              }\n" +
            "              $('#{fieldId}ImgZmList').html({fieldId}ImgHtml);\n" +
            "              //可移动\n" +
            "              imgMove(\"{fieldId}ImgZmList\");\n" +
            "        }";


}
