<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">

            <!-- 菜单树 -->
            <template slot-scope="scope" slot="menuIds">
                <!--<div slot="header" class="clearfix">
                    <span>选择菜单</span>
                </div>-->
                <el-tree
                        :data="menuData"
                        show-checkbox
                        node-key="id"
                        @check="menusCheck"
                        :default-expand-all=false
                        :default-checked-keys="menuDefaultCheckedKeys"
                        :props="menusProps">
                </el-tree>
            </template>
        </avue-form>
    </div>
</template>

<script>
    export default {
        // name: "RoleUpd",
        data() {
            return {
                obj: this.rowData,
                sizeValue: 'small',
                // 菜单配置
                menuData: this.menus,
                menuDefaultCheckedKeys: [],
                menusProps: {
                    children: 'menus',
                    label: 'name'
                },
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],         // 关闭弹层方法
            uri: {},                 // 添加接口
            rowData: {},             // 当前行数据

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
                            label: "角色名称",
                            prop: "name",
                            // tip: '这是信息提示',
                            span: 20,
                            maxlength: 32,
                            showWordLimit: true,
                            rules: [{
                                required: true,
                                message: "请输入 角色名称",
                                trigger: "blur"
                            }],
                        },
                        {
                            label: "code",
                            prop: "code",
                            span: 20,
                            maxlength: 32,
                            showWordLimit: true,
                            rules: [{
                                required: true,
                                message: "请输入 角色code",
                                trigger: "blur"
                            }],
                        },
                        {
                            label: "描叙",
                            prop: "desc",
                            span: 20,
                            maxlength: 256,
                            showWordLimit: true,
                            rules: [{
                                required: true,
                                message: "请输入 角色code",
                                trigger: "blur"
                            }],
                        },
                        // {
                        //     label: "是否禁用",
                        //     prop: "disable",
                        //     span: 20,
                        //     type: "switch",
                        //     dicData: getDict(website.Dict.Base.Disable, false),
                        // },
                        {
                            label: '菜单',
                            prop: 'menuIds',
                            rules: [{
                                required: true,
                                message: "请选择 菜单",
                                trigger: "blur"
                            }],
                            // formslot: true,
                        }
                    ]
                }
            }
        },
        created() {
            // 获取菜单选中数据
            this.obj = this.rowData;
            // 获取菜单数据(弹层数据)
            this.crud.get(this.uri.menuList.replace("{roleId}", this.rowData.id), {isOwnData: true}).then((res) => {
                // 获取选中菜单
                this.menuData = res.data.data;
                this.obj.menuIds = [];
                this.menuDefaultCheckedKeys = [];
                this.nextMenuIds(res.data.data, this.obj.menuIds, this.menuDefaultCheckedKeys);
                console.log("总值=", this.obj.menuIds);
                console.log("需回显值=", this.menuDefaultCheckedKeys)
            })
        },

        /**
         * 请求方法
         * @author wangsong
         * @param null
         * @date 2021/10/10 0010 12:55
         * @return
         * @version 1.0.1
         */
        methods: {
            emptytChange() {
                this.closeDialog(false);
                this.obj = this.rowData;
                this.menuDefaultCheckedKeys = [];
            },
            submit(form, done) {
                this.crud.put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    // 添加成功关闭弹层
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch((err) => {
                    console.error(err);
                    done(form);
                })
            },
            //共两个参数，
            // 依次为：传递给 data 属性的数组中该节点所对应的对象、
            // 树目前的选中状态对象，包含 checkedNodes、checkedKeys、halfCheckedNodes、halfCheckedKeys 四个属性
            menusCheck(data, nodes) {
                this.obj.menuIds = [];
                this.obj.menuIds.push.apply(this.obj.menuIds, nodes.checkedKeys);
                this.obj.menuIds.push.apply(this.obj.menuIds, nodes.halfCheckedKeys);
                console.debug("menuIds=", this.obj.menuIds)
            },

            /**
             * 获取菜单的ids
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/10/20 0020 21:48
             * @version 1.0.0
             */
            nextMenuIds(menus, menuIds, menuDefaultCheckedKeys) {
                for (let i = 0; i < menus.length; i++) {
                    if (menus[i].isChecked) {
                        menuIds.push(menus[i].id);
                        if (menus[i].menus != null && menus[i].menus.length > 0) {
                            // 不是最后一级递归处理
                            this.nextMenuIds(menus[i].menus, menuIds, menuDefaultCheckedKeys)
                        } else {
                            // 只设置最后一级默认到回显值
                            menuDefaultCheckedKeys.push(menus[i].id);
                        }
                    }
                }
            }
        }
    }
</script>
