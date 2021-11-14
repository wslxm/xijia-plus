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

    export default {
        // name: "RoleUpd",
        data() {
            return {
                obj: {},              // 表单数据
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],           // 关闭弹层方法
            uri: {},                   // 接口数据
            rowData: {},               // 当前行数据
            organs: [],                // 部门数据
            roles: [],                 // 角色数据
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            rowData: function (newRowData, oldRowData) {
                console.debug("原:", oldRowData.id, "  -->新:", newRowData.id)
                if (newRowData != null && newRowData.id != null) {
                    this.crud.get(this.uri.info + "/" + newRowData.id).then((res) => {
                        this.obj = res.data.data;
                    })
                }
            }
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
                            label: '终端',
                            prop: 'terminal',
                            span: 20,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Admin.Terminal),
                            rules: [{
                                required: true,
                                message: "请选择 终端 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '头像',
                            prop: 'head',
                            span: 24,
                            dataType: 'string', // 字符串模式
                            type: 'upload',
                            listType: 'picture-img',                  // 图片格式, 单图-[picture-img]  多图-[picture-card]  缩略图-[picture]
                            action: baseUploadUrl + 'image/head/',    // 上传地址 + 文件保存上传地址(详见接口描叙)
                            tip: '只能上传jpg/png文件，且不超过500kb',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功')
                                done()
                            },
                            rules: [{
                                required: true,
                                message: "请上传 头像 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '姓名',
                            prop: 'fullName',
                            span: 20,
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
                            rules: [{
                                required: true,
                                message: "请输入 账号 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '手机号',
                            prop: 'phone',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 手机号 ",
                                trigger: "blur"
                            }]
                        },
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
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 年龄 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '地址',
                            prop: 'address',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 地址 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '部门',
                            prop: 'organId',
                            span: 20,
                            type: "cascader",
                            dicData: this.organs,
                            props: {
                                value: "id",
                                label: "name",
                                children: "organs"
                            },
                            rules: [{
                                required: true,
                                message: "请选择 部门 ",
                                trigger: "blur"
                            }]
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
                            }]
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
                            }]
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
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.crud.put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    console.debug(res);
                    // 添加成功关闭弹层
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch(err => {
                    console.error(err);
                    done(form);
                })
            },
        }
    }
</script>