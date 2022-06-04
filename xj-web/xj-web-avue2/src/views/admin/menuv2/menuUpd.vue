<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
            <template slot="icon">
                <avue-input-icon v-model="obj.icon" placeholder="请选择图标" :icon-list="iconList"></avue-input-icon>
            </template>
        </avue-form>
    </div>
</template>

<script>
    import icon from "@/util/icon";

    export default {
        data() {
            return {
                obj: {},              // 表单数据
                iconList: icon.iconList
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
                            label: '终端',
                            prop: 'terminal',
                            span: 20,
                            disabled: true,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Admin.Terminal),
                            rules: [{
                                required: true,
                                message: "请选择 终端",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '菜单级别',
                            prop: 'root',
                            span: 20,
                            disabled: true,
                            type: "radio",
                            dicData: this.dict.get(this.website.Dict.Base.MenuRoot),
                            rules: [{
                                required: true,
                                message: "请选择 菜单级别",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '菜单名称',
                            prop: 'name',
                            span: 20,
                            maxlength: 32,
                            showWordLimit: true,
                            rules: [{
                                required: true,
                                message: "请输入 菜单名称",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '路由',
                            prop: 'url',
                            span: 20,
                            display: this.obj.root === 3,
                            maxlength: 128,
                            showWordLimit: true,
                            rules: [{
                                required: false,
                                message: "请输入 菜单路由",
                                trigger: "blur"
                            }],
                        },

                        {
                            label: '图标',
                            prop: 'icon',
                            span: 20,
                            rules: [{
                                required: false,
                                message: "请选择 图标",
                                trigger: "blur"
                            }],
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            span: 20,
                            type: 'number',
                            precision: 0, //保留小数位
                            minRows: 0,
                            maxRows: 99999999999,
                            span: 20,
                            rules: [{
                                required: false,
                                message: "请输入 排序",
                                trigger: "blur"
                            }]
                        },

                    ]
                }
            }
        },
        created() {
            this.obj = this.rowData;
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