<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {getDict} from '@/api/dict';
    import {post} from '@/api/crud';
    import website from '@/config/website';

    export default {
        // name: "RoleAdd",
        data() {
            return {
                defaultData: {
                    name: null,
                    code: null,
                    desc: '-',
                    disable: 0,
                    terminal: 1
                },
                obj: {},
                sizeValue: 'small'
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],    // 关闭弹层方法
            uri: {}             // 接口信息
        },
        computed: {
            option() {
                return {
                    size: this.sizeValue,
                    submitText: '提交',
                    emptyText: "关闭",
                    submitBtn: true,   // 提交按钮
                    emptyBtn: true,    // 清空按钮
                    mockBtn: false,    // 模拟按钮
                    printBtn: false,   // 打印按钮
                    column: [
                        {
                            label: "终端",
                            prop: "terminal",
                            type: "select",
                            dicData: getDict(website.Dict.Admin.Terminal),
                            span: 20,
                            rules: [{
                                required: true,
                            }],
                        },
                        {
                            label: "角色名称",
                            prop: "name",
                            // tip: '这是信息提示',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入用户名",
                                trigger: "blur"
                            }],
                        },
                        {
                            label: "code",
                            prop: "code",
                            span: 20,
                            rules: [{
                                required: true,
                            }],
                        },
                        {
                            label: "描叙",
                            prop: "desc",
                            span: 20,
                            rules: [{
                                required: true,
                            }],
                        },

                        {
                            label: "是否禁用",
                            prop: "disable",
                            span: 6,
                            type: "switch",
                            dicData: getDict(website.Dict.Base.Disable, false),
                            rules: [{
                                required: true,
                            }],
                        }
                    ]
                }
            }
        },
        mounted() {
            this.obj = this.defaultData
        },
        methods: {

            emptytChange() {
                // 关闭弹出
                this.closeDialog(false);
                // 还原默认数据
                this.obj = this.defaultData;
                // this.$message.success('已清空');
            },
            submit(form, done) {
                post(this.uri.info, this.obj).then((res) => {
                    // 添加成功关闭弹层并刷新数据
                    this.closeDialog(true);
                    done(form);
                }).catch(err => {
                    done(form);
                })
            },
        }
    }
</script>