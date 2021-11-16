<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
        </avue-form>
    </div>
</template>

<script>

    export default {
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
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            rowData: function (newRowData, oldRowData) {
                console.debug("原:", oldRowData.id, "  -->新:", newRowData.id)
                if (newRowData != null && newRowData.id != null) {
                    this.obj = this.rowData;

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
                            disabled: true,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Admin.Terminal),
                        },
                        {
                            label: '菜单级别',
                            prop: 'root',
                            span: 20,
                            disabled: true,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Base.MenuRoot),
                        },
                        {
                            label: '菜单名称',
                            prop: 'name',
                            span: 20,
                        },
                        {
                            label: '路由',
                            prop: 'url',
                            span: 20,
                        },

                        {
                            label: '图标',
                            prop: 'icon',
                            span: 20,
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            span: 20,
                        },

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