<template>
    <div>
        <avue-crud :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @refresh-change="onLoad"
                   @search-change="searchChange"
                   @row-click="handleRowClick">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
                <el-button type="primary" size="small" plain @click="updRoleAuthAll()">所有角色分配所有权限</el-button>
            </template>
            <template slot-scope="{scope,row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row,index,row.disable)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
        </avue-crud>
        <!-- 弹层 -->
        <el-dialog title="新增" v-dialogDrag :visible.sync="addDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog" :destroy-on-close="true">
            <Add :closeDialog="closeDialog" :uri="uri" :menus="menus"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="编辑" v-dialogDrag :visible.sync="updDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog" :destroy-on-close="true">
            <Upd :closeDialog="closeDialog" :uri="uri" :menus="menus" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>

    export default {
        components: {
            Add: () => import('./roleAdd'),
            Upd: () => import('./roleUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/role/list",
                    info: "/api/admin/role",
                    updRoleAuthAll: "/api/admin/role/updRoleAuthAll",
                    menuList: "/api/admin/menu/list?disable=0&isTree=true&roleId={roleId}"
                },
                dialogWidth: "40%",
                addDialogVisible: false,   // 添加弹层开关状态
                updDialogVisible: false,   // 添加弹层开关状态
                rowData: {},               // 当前选中行数据()
                menus: [],                 // 弹层菜单数据
                page: this.website.pageParams,  // 分页参数
                search: {                        // 搜索参数
                    terminal: 2
                },
                data: [],                  // 列表数据
                option: {}                 // 列表配置( mounted() 方法中配置)
            }
        },
        created() {
            // 获取菜单数据(添加弹层数据)
            this.crud.get(this.uri.menuList.replace("{roleId}", "")).then((res) => {
                console.debug("==", res.data.data)
                this.menus = res.data.data;
            })
        },
        mounted() {
            // 基础配置
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            // 字段配置
            this.option.column = [
                {
                    label: '角色名称',
                    prop: 'name',
                    search: true,
                    searchSpan: 5,
                    searchRules: [{
                        required: false,
                        // trigger: "blur"
                    }],
                },
                {
                    label: 'code',
                    prop: 'code'
                },
                {
                    label: '描叙',
                    prop: 'desc'
                },
                {
                    label: '终端',
                    prop: 'terminal',
                    dicData: this.dict.get(this.website.Dict.Admin.Terminal, true, false, true),
                    search: true,
                    searchValue: this.search.terminal,
                    searchSpan: 5,
                    type: "select",
                    searchRules: [{
                        required: false,
                        // trigger: "blur"
                    }],
                },
                {
                    label: '禁用/启用',
                    prop: 'disable',
                    // type: "switch",
                    //cell: true,
                    // dicData: getDict(Dict.Base.Disable),
                    //html:true,
                    // formatter:(val)=>{
                    //     console.log(val)
                    //     return '<span style="color:red">'+val.disable+'</span>'
                    // }
                    //addDisplay: true,    // 添加页是否展示该字段
                    //addDisabled: false   // 添加页该字段是否禁止输入(addDisplay=true时生效)
                }
            ]
        },

        methods: {
            /**
             * 直接触发：  首次自动加载 / 点击分页 / 切换分页 / 跳转也 / 点击刷新
             * 被调用触发：搜索后 /  添加/编辑保存后 / 删除后
             * @author wangsong
             */
            onLoad() {
                this.crud.list(this, true);
            },
            // 搜索,并重置页数为1
            searchChange(params, done) {
                this.page.currentPage = 1;
                this.onLoad();
                done();
            },
            // 添加/编辑保存数据后 关闭弹层+ true-刷新列表 false-不刷新
            closeDialog(isRefresh) {
                this.addDialogVisible = false;
                this.updDialogVisible = false;
                this.rowData = {};
                if (isRefresh != null && isRefresh) {
                    this.onLoad();
                }
            },
            // 行删除
            rowDel(row, index) {
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row, index, disable) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: disable});
            },
            updRoleAuthAll() {
                this.$confirm(`确认让所有角色拥有所有权限嘛?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.crud.put(this.uri.updRoleAuthAll);
                })

            },
            // 点击保存行数据(供行操作的任意地方获取数据)
            handleRowClick(row) {
                this.rowData = row;
            },
            // 自动配置,单元格样式数字，对指定列设置字体颜色,大小，粗细等
            cellStyle({row, column}) {
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            }
        }
    }
</script>