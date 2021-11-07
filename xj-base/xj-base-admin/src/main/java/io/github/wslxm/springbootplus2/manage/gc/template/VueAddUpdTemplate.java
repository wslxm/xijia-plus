package io.github.wslxm.springbootplus2.manage.gc.template;

/**
 * 复选Html 模板配置
 */
public interface VueAddUpdTemplate {

    //  文本 {label}  {prop}
    String INPUT = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            maxlength: {maxlength},\n" +
            "                            showWordLimit: true,\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请输入 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    //  大文本 {label}  {prop}
    String TEXTAREA = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'textarea',\n" +
            "                            maxlength: {maxlength},\n" +
            "                            showWordLimit: true,\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请输入 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    //  数字 {label}  {prop}
    String NUMBER = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'number',\n" +
            "                            minRows: 0,\n" +
            "                            maxRows: 99999999,\n" +
            "                            row: true,\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请输入 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    //  单选 {label}  {prop}
    String RADIO = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'radio',\n" +
            "                            dicData: this.dict.get(this.website.Dict.Base.Default),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    //  多选 {label}  {prop}
    String CHECKBOX = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'checkbox',\n" +
            "                            dataType: 'string', // 字符串模式\n" +
            "                            dicData: this.dict.get(this.website.Dict.Base.Default),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    //  下拉选 {label}  {prop}
    String SELECT = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'select',\n" +
            "                            dicData: this.dict.get(this.website.Dict.Base.Default),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    //  开关 {label}  {prop}
    String SWITCH = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'switch',\n" +
            "                            dicData: this.dict.get(this.website.Dict.Base.Default),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";


    //  时间日期 {label}  {prop}
    String DATETIME = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'datetime',\n" +
            "                            format: 'yyyy-MM-dd hh:mm:ss',\n" +
            "                            valueFormat: 'yyyy-MM-dd hh:mm:ss',\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";


    //  文件上传 {label}  {prop}
    String UPLOAD = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            dataType: 'string', // 字符串模式\n" +
            "                            type: 'upload',\n" +
            "                            listType: 'picture-img',                // 单图-[picture-img] 多图-[picture-card] 缩略图-[picture](不定义=附件)\n" +
            "                            action: baseUploadUrl + 'file/gc/',     // 上传地址(详见接口描叙,默认允许任意文件)\n" +
            "                            tip: '只能上传jpg/png文件，且不超过500kb',\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请上传 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }],\n" +
            "                            propsHttp: {\n" +
            "                                res: 'data'\n" +
            "                            },\n" +
            "                            uploadBefore: (file, done, loading, column) => {\n" +
            "                                // 文件上传前处理\n" +
            "                                done(file)\n" +
            "                            },\n" +
            "                            uploadAfter: (res, done, loading, column) => {\n" +
            "                                this.$message.success('上传成功')\n" +
            "                                done()\n" +
            "                            }\n" +
            "                        },\n";
}
