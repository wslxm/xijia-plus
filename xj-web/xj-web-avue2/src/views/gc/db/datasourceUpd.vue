<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
            <template slot-scope="{disabled,size}" slot="dbTest">
                <div>
                    <el-button @click="dbTest" size="small">点击测试</el-button>
                </div>
            </template>
        </avue-form>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                obj: {},
                isIdFind: true,
                initSuccess: false,
                labelWidth: 130,
            }
        },
        props: {
            closeDialog: [],
            uri: {},
            rowData: {},
        },
        computed: {
            option() {
                return {
                    submitBtn: true,
                    emptyBtn: true,
                    submitText: '提交',
                    emptyText: "关闭",
                    group: [
                        {
                            icon: 'el-icon-edit',
                            label: '数据连接信息',
                            collapse: true,
                            prop: 'group1',
                            column: [

                                {
                                    label: 'db 标题',
                                    prop: 'dbTitle',
                                    maxlength: 64,
                                    showWordLimit: true,
                                    span: 20,
                                    rules: [{
                                        required: true,
                                        message: "请输入 db -标题",
                                        trigger: "blur"
                                    }],
                                    labelTip: "自定义标题，便于生成代码选择数据源",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: 'db 地址',
                                    prop: 'dbUrl',
                                    maxlength: 128,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 db 连接地址",
                                        trigger: "blur"
                                    }],
                                    labelTip: "数据源连接地址,如：127.0.0.1:3306",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: 'db 库名',
                                    prop: 'dbName',
                                    maxlength: 64,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 db 库名",
                                        trigger: "blur"
                                    }],
                                    labelTip: "数据库的库名称,如：xijia-plus",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: 'db 账号',
                                    prop: 'dbUsername',
                                    maxlength: 32,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 db 账号",
                                        trigger: "blur"
                                    }],
                                    labelTip: "数据库连接 账号",
                                    labelWidth: this.labelWidth,
                                },
                                // {
                                //     label: 'db 密码',
                                //     prop: 'dbPassword',
                                //     maxlength: 32,
                                //     showWordLimit: true,
                                //     span: 10,
                                //     rules: [{
                                //         required: true,
                                //         message: "请输入 db 密码",
                                //         trigger: "blur"
                                //     }],
                                //     labelTip: "数据库连接 密码",
                                //     labelWidth: this.labelWidth,
                                // },
                                {
                                    label: "测试连接",
                                    prop: 'dbTest',
                                    span: 20,
                                    labelWidth: this.labelWidth,
                                },
                            ],
                        },
                        {
                            icon: 'el-icon-edit',
                            label: '作者及代码生成描述信息',
                            collapse: false,
                            prop: 'group2',
                            column: [
                                {
                                    label: '作者',
                                    prop: 'author',
                                    maxlength: 32,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 作者",
                                        trigger: "blur"
                                    }],
                                    labelTip: "代码生成后的注释信息",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: '邮箱',
                                    prop: 'email',
                                    maxlength: 32,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 邮箱",
                                        trigger: "blur"
                                    }],
                                    labelTip: "代码生成后的注释信息",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: '描述信息',
                                    prop: 'describe',
                                    maxlength: 128,
                                    showWordLimit: true,
                                    span: 20,
                                    rules: [{
                                        required: true,
                                        message: "请输入 描述信息",
                                        trigger: "blur"
                                    }],
                                    labelTip: "代码生成后的注释信息",
                                    labelWidth: this.labelWidth,
                                },


                            ]
                        }
                        ,
                        {
                            icon: 'el-icon-edit',
                            label: '项目/模块/包地址,生成路径配置',
                            collapse: false,
                            prop: 'group3',
                            column: [
                                {
                                    label: '项目模块地址',
                                    prop: 'projectName',
                                    maxlength: 64,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 项目名/路径，如：xj-server/xj-test-server",
                                        trigger: "blur"
                                    }],
                                    labelTip: "项目名/路径,如：xj-server/xj-test-server, 定义规范后默认拼接生成的代码路径",
                                    labelWidth: this.labelWidth,

                                },
                                {
                                    label: '包路径 ',
                                    prop: 'packPath',
                                    maxlength: 64,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 包路径 ",
                                        trigger: "blur"
                                    }],
                                    labelTip: "包路径,如：io.github.wslxm.springbootplus2, 定义规范后默认拼接生成的代码路径",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: '根模块 ',
                                    prop: 'rootModule',
                                    maxlength: 16,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 根模块 ",
                                        trigger: "blur"
                                    }],
                                    labelTip: "根模块,如：modules(管理端), client(用户端), 定义规范后默认拼接生成的代码路径",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: '子模块 ',
                                    prop: 'modulesName',
                                    maxlength: 16,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: true,
                                        message: "请输入 子模块 ",
                                        trigger: "blur"
                                    }],
                                    labelTip: "子模块,如：user(用户), order(订单) shop(商品), 定义规范后默认拼接生成的代码路径",
                                    labelWidth: this.labelWidth,
                                },

                                {
                                    label: '绝对生成路径',
                                    prop: 'fatherPath',
                                    maxlength: 256,
                                    showWordLimit: true,
                                    span: 20,
                                    rules: [{
                                        required: false,
                                        message: "请输入 生成路径",
                                        trigger: "blur"
                                    }],
                                    labelTip: "生成路径,不填默认根据当前模块项目模块,包路径,父模块，子模块拼接路径,自动生成到当前项目对应的位置,填写后可生成到绝对路径)",
                                    labelWidth: this.labelWidth,
                                },

                            ]
                        },
                        {
                            icon: 'el-icon-edit',
                            label: '模块信息及生成路径配置',
                            collapse: false,
                            prop: 'group4',
                            column: [

                                {
                                    label: '生成swagger注释 ',
                                    prop: 'entitySwagger',
                                    maxlength: 0,
                                    showWordLimit: true,
                                    span: 10,
                                    type: "switch",
                                    dicData: [
                                        {
                                            label: "否",
                                            value: false
                                        },{
                                            label: "是",
                                            value: true
                                        }
                                    ],
                                    rules: [{
                                        required: true,
                                        message: "请输入 实体类是否使用swagger注释 ",
                                        trigger: "blur"
                                    }],
                                    labelTip: "实体类是否使用swagger注释 (false情况下使用doc注释)",
                                    labelWidth: 160,
                                },
                                {
                                    label: '过滤Crud方法 ',
                                    prop: 'filterCrud',
                                    maxlength: 0,
                                    showWordLimit: true,
                                    span: 10,
                                    type: "switch",
                                    dicData: [
                                        {
                                            label: "否",
                                            value: false
                                        },{
                                            label: "是",
                                            value: true
                                        }
                                    ],
                                    rules: [{
                                        required: true,
                                        message: "请输入 是否过滤Crud方法 ",
                                        trigger: "blur"
                                    }],
                                    labelTip: "是否过滤crud方法- 默认生成 (controller/service/mapper/xml)",
                                    labelWidth: 160,
                                },

                                {
                                    label: 'db 表前缀 ',
                                    prop: 'dbTablePrefix',
                                    maxlength: 16,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: false,
                                        message: "请输入 db 表前缀 ",
                                        trigger: "blur"
                                    }],
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: 'db 字段前缀 ',
                                    prop: 'dbFieldPrefix',
                                    maxlength: 16,
                                    showWordLimit: true,
                                    span: 10,
                                    rules: [{
                                        required: false,
                                        message: "请输入 db 字段前缀 ",
                                        trigger: "blur"
                                    }],
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: '通用字段',
                                    prop: 'baseFields',
                                    maxlength: 512,
                                    showWordLimit: true,
                                    span: 20,
                                    rules: [{
                                        required: true,
                                        message: "请输入 数据库通用字段",
                                        trigger: "blur"
                                    }],
                                    labelTip: "通用字段默认不生成到Entity/Dto/Vo中,在BaseEntity 等中已定义",
                                    labelWidth: this.labelWidth,
                                },
                                {
                                    label: '排除vue字段类型',
                                    prop: 'vueFieldTypes',
                                    maxlength: 256,
                                    showWordLimit: true,
                                    span: 20,
                                    rules: [{
                                        required: false,
                                        message: "请输入 排除vue字段类型 ",
                                        trigger: "blur"
                                    }],
                                    labelTip: "排除vue字段类型 (字典code值，参考字典生成字段类型，如: 18=富文本 19=md编辑器 ), vue列表排除展示指定类型字段, 同时list接口 mybatis-plus 查询模式不查询该类型字段值 （详见字典- VueFieldType）",
                                    labelWidth: 150,
                                },
                                {
                                    label: '数据库关键字',
                                    prop: 'keywordArray',
                                    maxlength: 1024,
                                    showWordLimit: true,
                                    span: 20,
                                    rules: [{
                                        required: true,
                                        message: "请输入 数据库关键字",
                                        trigger: "blur"
                                    }],
                                    labelTip: "数据库关键字,生成的实体类遇到关键字将自动处理",
                                    labelWidth: this.labelWidth,
                                },

                            ]
                        }
                    ]
                }
            }
        },
        created() {
            if (this.isIdFind) {
                this.findId(this.rowData);
            } else {
                this.obj = this.rowData;
                this.initSuccess = true;
            }
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.crud.put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    console.debug(res);
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch(err => {
                    console.error(err);
                    done(form);
                })
            },
            findId(rowData) {
                if (rowData != null && rowData.id != null) {
                    this.crud.get(this.uri.info + "/" + rowData.id).then((res) => {
                        this.obj = res.data.data;
                        this.initSuccess = true;
                    })
                }
            },
            /**
             * 测试连接
             */
            dbTest() {
                this.crud.post(this.uri.dbTest + "/" + this.obj.id, this.obj).then((res) => {
                    // 成功
                })
            }
        }
    }
</script>
