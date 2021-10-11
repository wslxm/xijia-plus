<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {getDict} from '@/api/dict';
    import {put} from '@/api/crud';

    export default {
        name: "RoleUpd",
        data() {
            return {
                obj: this.rowData,
                sizeValue: 'small'
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialogVisible: [], // 关闭弹层方法
            updRoleUri: String,     // 添加接口
            rowData: {},            // 当前行数据
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            rowData: function (newRowData, oldRowData) {
                this.obj = newRowData;
            }
        },
        computed: {
            option() {
                return {
                    size: this.sizeValue,
                    submitText: '提交',
                    mockBtn: false,    // 模拟按钮
                    printBtn: false,   // 打印按钮
                    closeBtn: false,   // 打印按钮 关闭
                    column: [
                        {
                            label: "终端",
                            prop: "terminal",
                            type: "select",
                            dicData: getDict("TERMINAL"),
                            span: 20,
                            mock: {
                                type: 'dic'
                            },
                        },
                        {
                            label: "角色名称",
                            prop: "name",
                            span: 20,
                        },
                        {
                            label: "code",
                            prop: "code",
                            span: 20
                        },
                        {
                            label: "描叙",
                            prop: "desc",
                            span: 20
                        }, {
                            label: "是否禁用",
                            prop: "disable",
                            span: 6,
                            type: "switch",
                            dicData: getDict("DISABLE"),
                        }
                    ]
                }
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
                put(this.updRoleUri + "/" + this.obj.id, this.obj).then((res) => {
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