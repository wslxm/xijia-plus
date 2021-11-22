<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
            <!-- 菜单树 -->

            <template slot-scope="scope" slot="menuIds">
                <el-card class="box-card">
                    <!-- <div slot="header" class="clearfix">
                        <span>选择菜单</span>
                    </div> -->
                    <el-tree
                            :data="menuData"
                            show-checkbox
                            node-key="id"
                            @check="menusCheck"
                            :default-expand-all=true
                            :default-checked-keys="menuDefaultCheckedKeys"
                            :props="menusProps">
                    </el-tree>
                </el-card>
            </template>
        </avue-form>
    </div>
</template>

<script>

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
                sizeValue: 'small',
                // 菜单配置
                menuData: this.menus,
                menuDefaultCheckedKeys: [],
                menusProps: {
                    children: 'menus',
                    label: 'name'
                }
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],    // 关闭弹层方法
            uri: {},             // 接口信息
            menus: [],           // 树
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
                            dicData: this.dict.get(this.website.Dict.Admin.Terminal),
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
                        // {
                        //     label: "是否禁用",
                        //     prop: "disable",
                        //     span: 20,
                        //     type: "switch",
                        //     dicData: getDict(website.Dict.Base.Disable, false),
                        //     rules: [{
                        //         required: true,
                        //     }],
                        // }
                        {
                            label: '菜单',
                            prop: 'menuIds',
                            rules: [{
                                required: true,
                            }],
                            // formslot: true,
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
                this.crud.post(this.uri.info, this.obj).then((res) => {
                    console.log(res);
                    // 添加成功关闭弹层并刷新数据
                    this.closeDialog(true);
                    done(form);
                }).catch(() => {
                    done(form);
                })
            },
            //共两个参数，
            // 依次为：传递给 data 属性的数组中该节点所对应的对象、
            // 树目前的选中状态对象，包含 checkedNodes、checkedKeys、halfCheckedNodes、halfCheckedKeys 四个属性
            menusCheck(data, nodes) {
                this.obj.menuIds = [];
                this.obj.menuIds.push.apply(this.obj.menuIds, nodes.checkedKeys)
                this.obj.menuIds.push.apply(this.obj.menuIds, nodes.halfCheckedKeys)
                console.log("menuIds=", this.obj.menuIds)
            },
        }
    }
</script>
<style>
    .box-card {
        margin-top: -20px !important;
        width: 150%;
        height: 400px;
        overflow-y: auto!important;
    }

    /*滚动条样式 滚动条整体部分，可以设置宽度啥的*/
    .box-card::-webkit-scrollbar {
        width: 10px;
    }


</style>