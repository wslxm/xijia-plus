<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   :upload-before="uploadBefore"
                   :upload-after="uploadAfter"
                   @reset-change="emptytChange"
                   @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {getDict,} from '@/api/dict';
    import {get, put} from '@/api/crud';
    import website from '@/config/website';
    import {baseUploadUrl} from "../../../config/env";

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
                if (newRowData != null && newRowData.id != null) {
                    get(this.uri.info + "/" + newRowData.id).then((res) => {
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
                            dicData: getDict(website.Dict.Admin.Terminal),
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
                        },
                        {
                            label: '姓名',
                            prop: 'fullName',
                            span: 20,
                        },
                        {
                            label: '账号',
                            prop: 'username',
                            span: 20,
                        },
                        {
                            label: '密码',
                            prop: 'password',
                            span: 20,
                        },
                        {
                            label: '手机号',
                            prop: 'phone',
                            span: 20,
                        },
                        {
                            label: '性别',
                            prop: 'gender',
                            span: 20,
                            type: "radio",
                            dicData: getDict(website.Dict.Base.Gender),
                        },
                        {
                            label: '年龄',
                            prop: 'age',
                            span: 20,
                        },
                        {
                            label: '地址',
                            prop: 'address',
                            span: 20,
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
                            }
                        },
                        {
                            label: '职位',
                            prop: 'position',
                            span: 20,
                            type: "radio",
                            dicData: getDict(website.Dict.Admin.Position),
                        },
                        {
                            label: '角色',
                            prop: 'roleIds',
                            span: 20,
                            //dataType: 'string',
                            type: "checkbox",
                            dicData: this.roles
                        },
                        {
                            label: '启用/禁用',
                            prop: 'disable',
                            span: 20,
                            type: "switch",
                            dicData: getDict(website.Dict.Base.Disable, false),
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
                put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    // 添加成功关闭弹层
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch(err => {
                    done(form);
                })
            },
            uploadBefore(file, done, loading, column) {
                done(file)
            },
            uploadAfter(res, done, loading, column) {
                this.$message.success('上传成功')
                done();
            },
        }
    }
</script>