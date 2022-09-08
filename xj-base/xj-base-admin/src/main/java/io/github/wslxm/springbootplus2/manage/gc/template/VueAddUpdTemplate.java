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
            "                            precision: 2,  //保留小数位,\n" +
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
            "                            filterable:true,\n" +
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
     * 时间-小时选择器 {label}  {prop}
     * 09:00 | 09:30 | 10:00
     *
     */
    String TIME = "                        {\n" +
            "                            label: \"{label}\",\n" +
            "                            prop: \"{prop}\",\n" +
            "                            type: \"time\",\n" +
            "                            span: 20,\n" +
            "                            pickerOptions: {\n" +
            "                                start: '06:00',\n" +
            "                                step: '00:30',\n" +
            "                                end: '23:00'\n" +
            "                            },\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请输入 {label} \",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";


    /**
     * 级联选择器 {label}  {prop}
     */
    String CASCADER = "                        {\n" +
            "                            label: '{label}',\n" +
            "                            prop: '{prop}',\n" +
            "                            span: 20,\n" +
            "                            type: \"cascader\",\n" +
            "                            dataType: 'string',\n" +
            "                            filterable: true, \n" +
            "                            dicData: this.defaultDic.dicData,   // 自行替换字典数据  \n" +
            "                            props: {\n" +
            "                                value: \"id\",\n" +
            "                                label: \"name\",\n" +
            "                                children: \"children\"\n" +
            "                            },\n" +
            "                            rules: [{\n" +
            "                                required: true,\n" +
            "                                message: \"请选择 {label} \",\n" +
            "                                trigger: \"blur\"\n" +
            "                            }]\n" +
            "                        },\n";


    /**
     * 文件上传
     * {label}
     * {prop}
     * {listType} ==> 单图/视频-[picture-img] |  多图/视频-[picture-card] |  缩略图-[picture] | 任意文件空
     * {limit} ==> 上传数量
     * {accept} ==> 文件上传格式 -> 图片默认: image/png, image/jpeg, image/jpg, image/gif  | 视频默认: video/mp4 | 任意文件不限制
     * {tip} ==> 文件上传提示  -> 【图片: 只能上传 jpg/png/gif 格式的图片】 【视频：只能上传mp4格式的视频】【任意文件：无】
     * {fileType} ==> 文件类型, 分类图片/视频/任意文件的存储路径
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
            "                            dataType: 'string', \n" +
            "                            accept: {accept},  \n" +
            "                            type: 'upload',\n" +
            "                            listType: '{listType}', \n" +
            "                            action: baseUploadUrl + 'file/gc/{fileType}/',   // 上传地址 + 文件保存上传地址(详见接口描叙)\n" +
            "                            multiple: true,          // 文件多选\n" +
            "                            drag: true,              // 拖拽排序\n" +
            "                            limit: {limit},                // 上传数量 1 个\n" +
            "                            //fileSize: 500,         // 上传大小 500 kb内\n" +
            "                            tip: '{tip}',\n" +
            "                            loadText: '上传中...',   \n" +
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
