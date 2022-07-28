<template>
    <div>
        <avue-crud ref="crudRole"
                   :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
                   :table-loading="loading"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @refresh-change="onLoad"
                   @search-change="searchChange">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>

                <el-tooltip class="item" effect="dark" content="给所有角色分配所有接口的访问权限" placement="top-start">
                    <el-button type="primary" size="small" plain
                               v-if="website.isTerminalSearch"
                               @click="updRoleAuthAll()">权限ALL
                    </el-button>
                </el-tooltip>

            </template>
            <template slot-scope="{row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row,index,row.disable)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,2)">资源分配</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
        </avue-crud>
        <!-- 弹层 -->
        <el-dialog title="新增" v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="编辑" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog :title="'资源分配-'+this.rowData.name" v-dialogDrag v-if="updAuthDialogVisible" :visible.sync="updAuthDialogVisible" width="60%" top="6vh" @close="closeDialog">
            <RoleAuthV2 :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></RoleAuthV2>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>

    export default {
        components: {
            Add: () => import('./roleAdd'),
            Upd: () => import('./roleUpd'),
            RoleAuth: () => import('./roleAuth'),
            RoleAuthV2: () => import('./roleAuthV2'),
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/role/list",
                    info: "/api/admin/role",
                    updRoleAuthAll: "/api/admin/role/updRoleAuthAll",  // 使用角色拥有所有权限
                    menuList: "/api/admin/menu/list?disable=0&isTree=true&roleId={roleId}", // 分配菜单查询信息
                    authListByRole: "/api/admin/authority/list?type=0&isTree=true&roleId={roleId}",  // 分配资源查询信息
                    updRoleAuth: "/api/admin/role/updRoleAuth",   // 角色-分配资源
                },
                loading: true,
                dialogWidth: "40%",
                addDialogVisible: false,         // 添加弹层开关状态
                updDialogVisible: false,         // 编辑弹层开关状态
                updAuthDialogVisible: false,     // 资源分配
                rowData: {},                     // 当前选中行数据()
                menus: [],                       // 弹层菜单数据
                page: this.website.pageParams,   // 分页参数
                data: [],                        // 列表数据
                option: {},                      // 列表配置( mounted() 方法中配置)
                search: {                        // 搜索参数
                    terminal: this.website.Terminal
                },
            }
        },

        activated: function () {
            this.crud.doLayout(this, this.$refs.crudRole)
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
                    prop: 'code',
                    search: true,
                    searchSpan: 5,
                },
                {
                    label: '描叙',
                    prop: 'desc'
                },
                {
                    label: '终端',
                    prop: 'terminal',
                    dicData: this.dict.get(this.website.Dict.Admin.Terminal, true, false, true),
                    hide: true,
                    search: this.website.isTerminalSearch,
                    searchValue: this.search.terminal,
                    searchOrder: 1,
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
                    type: "switch",
                    search: true,
                    searchSpan: 5,
                    //cell: true,
                    dicData: this.dict.get(this.website.Dict.Base.Disable),
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
                // 是否只查询自己权限及以下的数据
                this.search.isOwnData = true;
                // 查询
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudRole)
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
                this.updAuthDialogVisible = false;
                this.rowData = {};
                if (isRefresh != null && isRefresh) {
                    this.onLoad();
                }
            },
            // 行编辑
            updRow(row, type) {
                this.rowData = row;
                switch (type) {
                    case 1:
                        // 编辑弹层
                        this.updDialogVisible = true;
                        break;
                    case 2:
                        // 资源分配
                        this.updAuthDialogVisible = true;
                        break;
                    default:
                        this.$message.error('操作类型错误');
                        break;
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