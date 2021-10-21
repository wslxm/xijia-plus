<template>
    <div>
        <el-tree
                ref="reftree"
                :data="menuData"
                show-checkbox
                node-key="id"
                @check="menusCheck"
                :default-expand-all=true
                :default-checked-keys="menuDefaultCheckedKeys"
                :props="menusProps">
        </el-tree>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit"></avue-form>
    </div>
</template>

<script>
    import {get, put} from '@/api/crud';

    export default {
        data() {
            return {
                // 菜单配置
                menuData: [],
                menuDefaultCheckedKeys: [],
                obj: {},
                menusProps: {
                    children: 'menus',
                    label: 'name'
                },
                option: {
                    submitText: '提交',
                    emptyText: "关闭",
                    submitBtn: true,   // 提交按钮
                    mockBtn: false,    // 模拟按钮
                    printBtn: false,   // 打印按钮
                }
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
                if (newRowData != null && newRowData.id != null) {
                    this.obj = this.rowData;
                    get(this.uri.infoPidList).then((res) => {
                        this.menuData = res.data.data;
                        this.menuDefaultCheckedKeys[0] = newRowData.pid;
                    })
                }
            }
        },
        computed: {},
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                // let pid = this.$refs.reftree.getCheckedKeys()[0];
                let pid = this.obj.pid;
                put(this.uri.info + "/" + this.obj.id, {pid, pid}).then((res) => {
                    // 添加成功关闭弹层
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch(err => {
                    done(form);
                })
            },
            // 共两个参数，
            // 依次为：传递给 data 属性的数组中该节点所对应的对象、
            // 树目前的选中状态对象，包含 checkedNodes、checkedKeys、halfCheckedNodes、halfCheckedKeys 四个属性
            menusCheck(data, nodes) {
                this.obj.pid = data.id;
                this.$refs.reftree.setCheckedKeys([data.id]);
            },
        }
    }
</script>