<template>
    <div>
        <!--:upload-before="uploadBefore"
        :upload-after="uploadAfter"-->
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {baseUploadUrl} from "@/config/env";
    import pinyin from 'js-pinyin'
    export default {
        // name: "RoleUpd",
        data() {
            return {
                obj: {},              // 表单数据
                deps: [],                // 部门数据
                roles: [],                 // 角色数据
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],           // 关闭弹层方法
            uri: {},                   // 接口数据
            rowData: {},               // 当前行数据
        },
        computed: {
            option() {
                return {
                    submitText: '提交',
                    emptyText: "关闭",
                    submitBtn: true,   // 提交按钮
                    emptyBtn: true,    // 清空按钮
                    mockBtn: false,    // 模拟按钮
                    printBtn: false,   // 打印按钮
                    column: [
                        {
                            label: '头像',
                            prop: 'headPic',
                            span: 24,
                            rules: [{
                                required: true,
                                message: "请上传 头像 ",
                                trigger: "blur"
                            }],
                            dataType: 'string',  // 字符串模式
                            type: 'upload',
                            listType: 'picture-img',                // 图片格式, 单图-[picture-img]  多图-[picture-card]  缩略图-[picture] 普通文件空
                            action: baseUploadUrl + 'image/head/',  // 上传地址 + 文件保存上传地址(详见接口描叙)
                            multiple: false,       // 文件多选
                            drag: true,            // 拖拽排序
                            limit: 1,              // 上传数量 1 个
                            fileSize: 500,         // 上传大小 500 kb内
                            loadText: '上传中...',  // 上传中文字提示
                            tip: '只能上传jpg/png文件，且不超过500kb',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功');
                                done()
                            },
                            uploadError(error, column) {
                                // 上传失败
                                this.$message.error(error);
                            },
                            uploadExceed(limit, files, fileList, column) {
                                // 文件数量验证
                                this.$message.warning(`当前限制文件数量为 ${limit}, 当前共 ${files.length + fileList.length} `);
                            },
                        },
                        {
                            label: '姓名',
                            prop: 'fullName',
                            span: 20,
                            maxlength: 16,
                            showWordLimit: true,
                            rules: [{
                                required: true,
                                message: "请输入 姓名 ",
                                trigger: "blur"
                            }]

                        },
                        {
                            label: '账号',
                            prop: 'username',
                            span: 20,
                            maxlength: 20,
                            showWordLimit: true,
                            rules: [{
                                required: true,
                                message: "请输入 账号 ",
                                trigger: "blur"
                            }],
                            labelTip: '可以使用 账号 登录系统',
                        },
                        {
                            label: '手机号',
                            prop: 'phone',
                            //type: 'number',
                            maxlength: 11,
                            showWordLimit: true,
                            row: false,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 手机号 ",
                                trigger: "blur"
                            }],
                            labelTip: '可以使用 手机号 登录系统',
                        },
                        // {
                        //     label: '密码',
                        //     prop: 'password',
                        //     maxlength: 16,
                        //     showWordLimit: true,
                        //     span: 20,
                        //     rules: [{
                        //         required: true,
                        //         message: "请输入 密码 ",
                        //         trigger: "blur"
                        //     }],
                        //     labelTip: '登录系统时的密码,密码默认生成规则:【姓名首字母大写+手机号后六位】',
                        // },
                        {
                            label: '性别',
                            prop: 'gender',
                            span: 20,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Base.Gender),
                            rules: [{
                                required: true,
                                message: "请选择 性别 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '年龄',
                            prop: 'age',
                            type: 'number',
                            span: 20,
                            precision: 0, //保留小数位
                            minRows: 0,
                            maxRows: 999,
                            row: true,
                            rules: [{
                                required: false,
                                message: "请输入 年龄 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '地址',
                            prop: 'address',
                            span: 20,
                            maxlength: 64,
                            showWordLimit: true,
                            rules: [{
                                required: false,
                                message: "请输入 地址 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '部门',
                            prop: 'depIds',
                            span: 20,
                            type: "cascader",
                            dicData: this.deps,
                            props: {
                                value: "id",
                                label: "name",
                                children: "deps"
                            },
                            rules: [{
                                required: false,
                                message: "请选择 部门 ",
                                trigger: "blur"
                            }],
                            labelTip: '用于控制业务走向,通过部门+职位组合可满足大多数场景下的业务控制, 如给指定部门的人推送消息',
                        },
                        {
                            label: '职位',
                            prop: 'position',
                            span: 20,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Admin.Position),
                            rules: [{
                                required: true,
                                message: "请选择 职位 ",
                                trigger: "blur"
                            }],
                            labelTip: '用于控制业务走向,通过部门+职位组合可满足大多数场景下的业务控制, 如给指定职位的人推送消息',
                        },
                        {
                            label: '角色',
                            prop: 'roleIds',
                            span: 20,
                            type: "checkbox",
                            dicData: this.roles,
                            rules: [{
                                required: true,
                                message: "请选择 角色 ",
                                trigger: "blur"
                            }],
                            labelTip: '用于控制菜单以及接口权限',
                        },
                        {
                            label: '启用/禁用',
                            prop: 'disable',
                            span: 20,
                            type: "switch",
                            dicData: this.dict.get(this.website.Dict.Base.Disable, false),
                            rules: [{
                                required: true,
                                message: "请选择 启用/禁用 ",
                                trigger: "blur"
                            }]
                        }
                    ]
                }
            }
        },
        // watch: {
        //     //newNum = 新值，旧值
        //     "obj.fullName": function (newNum, oldNum) {
        //         this.$nextTick(() => {
        //             // 账号等于姓名拼音
        //             pinyin.setOptions({checkPolyphone: false, charCase: 0})
        //             //this.obj.username = pinyin.getFullChars(this.obj.fullName).toLowerCase()
        //         })
        //     },
        // },
        // 打开弹层立即执行
        created() {
            // 部门数据(弹层数据)
            this.crud.get(this.uri.depInfo, {disable: 0, isTree: true}).then((res) => {
                this.deps = res.data.data;
            });
            // 角色数据(弹层数据)
            this.crud.get(this.uri.roleInfo, {disable: 0}).then((res) => {
                console.debug(res);
                this.roles = res.data.data.records;
                for (const role of this.roles) {
                    role.value = role.id;
                    role.label = role.name;
                }
            });
            // id 查询用户信息
            this.crud.get(this.uri.info + "/" + this.rowData.id).then((res) => {
                this.obj = res.data.data;
            })
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.crud.put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    // 添加成功关闭弹层
                    this.closeDialog(true);
                    done(form);
                }).catch(err => {
                    console.error(err);
                    done(form);
                })
            },
        }
    }
</script>