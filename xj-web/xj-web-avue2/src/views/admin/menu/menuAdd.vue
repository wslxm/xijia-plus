<template>
    <div>
        <avue-form :defaults.sync="defaults" ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
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
                // 默认数据
                defaultData: {
                    pid: null,
                    terminal: 1,
                    root: 1,
                    name: null,
                    url: null,
                    icon: "el-icon-document-remove",
                    sort: 0,
                },
                defaults: {},
                // 当前数据
                obj: {},
                iconList: icon.iconList
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],    // 关闭弹层方法
            uri: {},            // 接口信息
            rowData: {},        // 行数据
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            rowData: function (newRowData, oldRowData) {
                console.debug("原:", oldRowData.id, "  -->新:", newRowData.id)
                if (newRowData != null && newRowData.id != null) {
                    // 根据选择行设置相关默认值
                    this.obj.terminal = newRowData.terminal;
                    this.obj.root = newRowData.nextRoot;
                    this.obj.pid = newRowData.id;
                    // 修改为不可选
                    this.defaults.terminal.disabled = true;
                    // 添加页面时需展示路由字段
                    if (this.obj.root == 3) {
                        this.defaults.url.display = true;
                    }
                    console.debug("===" + this.obj)
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
                            label: '父id',
                            prop: 'pid',
                            display: false
                        },
                        {
                            label: '终端',
                            prop: 'terminal',
                            span: 20,
                            //disabled: true,
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
                            display: false
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
        mounted() {
            this.obj = this.defaultData
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.crud.post(this.uri.info, this.obj).then((res) => {
                    console.debug(res);
                    this.closeDialog(true);
                    done(form);
                }).catch(err => {
                    console.debug(err);
                    done(form);
                })
            },
        }
    }
</script>