package io.github.wslxm.springbootplus2.manage.gc.template;

/**
 *  @author wangsong
 * 复选Html 模板配置
 */
public interface VueAddUpdTemplate {

    /**
     * 文本 {label}  {prop}
     */
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

    /**
     * 大文本 {label}  {prop}
     */
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


    /**
     * 数字 {label}  {prop}
     */
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

    /**
     * 单选 {label}  {prop} {dictCode}
     */
    String RADIO = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'radio',\n" +
            "                            dicData: this.dict.get({dictCode}),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    /**
     * 多选 {label}  {prop} {dictCode}
     */
    String CHECKBOX = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'checkbox',\n" +
            "                            dataType: 'string', // 字符串模式\n" +
            "                            dicData: this.dict.get({dictCode}),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    /**
     * 下拉选 {label}  {prop} {dictCode}
     */
    String SELECT = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'select',\n" +
            "                            dicData: this.dict.get({dictCode}),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";

    /**
     * 开关 {label}  {prop} {dictCode}
     */
    String SWITCH = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            type: 'switch',\n" +
            "                            dicData: this.dict.get({dictCode}),\n" +
            "                            span: 20,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label}\",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";


    /**
     * 时间日期 {label}  {prop}
     */
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


    /**
     * 文件上传 {label}  {prop}
     */
    String UPLOAD = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            span: 24,\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请上传 {label} \",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }],\n" +
            "                            dataType: 'string',  // 字符串模式\n" +
            "                            type: 'upload',\n" +
            "                            listType: 'picture-img',                // 图片格式, 单图-[picture-img]  多图-[picture-card]  缩略图-[picture] 普通文件空\n" +
            "                            action: baseUploadUrl + 'image/gc/',    // 上传地址 + 文件保存上传地址(详见接口描叙)\n" +
            "                            multiple: false,       // 文件多选\n" +
            "                            drag: true,            // 拖拽排序\n" +
            "                            limit: 1,              // 上传数量 1 个\n" +
            "                            //fileSize: 500,         // 上传大小 500 kb内\n" +
            "                            loadText: '上传中...',  // 上传中文字提示\n" +
            "                            tip: '只能上传jpg/png/gif文件',\n" +
            "                            propsHttp: {\n" +
            "                                res: 'data'\n" +
            "                            },\n" +
            "                            uploadBefore: (file, done) => {\n" +
            "                                // 文件上传前处理\n" +
            "                                done(file)\n" +
            "                            },\n" +
            "                            uploadAfter: (res, done) => {\n" +
            "                                this.$message.success('上传成功');\n" +
            "                                done()\n" +
            "                            },\n" +
            "                            uploadError(error, column) {\n" +
            "                                // 上传失败\n" +
            "                                this.$message.error(error);\n" +
            "                            },\n" +
            "                            uploadExceed(limit, files, fileList, column){\n" +
            "                                // 文件数量验证\n" +
            "                                this.$message.warning(`当前限制文件数量为 ${limit}, 当前共 ${files.length + fileList.length} `);\n" +
            "                            },\n" +
            "                        },\n";
}
