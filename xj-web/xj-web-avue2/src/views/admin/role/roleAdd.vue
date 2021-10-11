<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {getDict} from '@/api/dict';
    import {post} from '@/api/crud';

    export default {
        name: "RoleAdd",
        data() {
            return {
                obj: {},
                sizeValue: 'small'
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialogVisible: [], // 关闭弹层方法
            addRoleUri: String      // 添加接口
        },
        computed: {
            option() {
                return {
                    size: this.sizeValue,
                    submitText: '提交',
                    mockBtn: false,    // 模拟按钮
                    printBtn: false,   // 打印按钮
                    column: [
                        {
                            label: "终端",
                            prop: "terminal",
                            type: "select",
                            dicData: getDict("TERMINAL"),
                            span: 20,
                            // mock: {
                            //     type: 'dic'
                            // },
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
                            // minlength: 2,
                            // maxlength: 3,
                            // suffixIcon: 'el-icon-tickets',
                            // prefixIcon: 'el-icon-tickets',
                            // mock: {
                            //     type: 'name',
                            //     en: true,
                            // },
                            // click: ({value, column}) => {
                            //     this.$message.success('click')
                            // }
                            //disabled: true,
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
                            dicData: getDict("DISABLE"),
                            rules: [{
                                required: true,
                            }],
                        }
                    ]
                }
            }
        },
        /**
         * 默认参数
         */
        mounted() {
            this.obj = {
                name: '',
                code: '',
                desc: '-',
                disable: 0,
                terminal: 1
            }
        },
        /**
         * 请求方法
         * @author wangsong
         * @param null
         * @date 2021/10/10 0010 12:55
         * @return
         * @version 1.0.0
         */
        methods: {
            emptytChange() {
                this.$message.success('已清空');
            },
            submit(form, done) {
                console.log(this.obj)
                post(this.addRoleUri, this.obj).then((res) => {
                    // 添加成功关闭弹层
                    if (res.data.code == 200) {
                        this.closeDialogVisible();
                    }
                    done(form);
                }).catch(err => {
                    done(form);
                })
            },
        }
    }
</script>