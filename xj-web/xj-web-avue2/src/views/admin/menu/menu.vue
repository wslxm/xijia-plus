<template>
    <div>
        <avue-crud ref="crudMenu"
                   :data="data"
                   :option="option"
                   :search.sync="search"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @refresh-change="onLoad"
                   @search-change="searchChange"
                   @row-click="handleRowClick">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
            </template>

            <!-- 行编辑url -->
            <template slot-scope="{scope,row,index,type,size}" slot="url">
                <el-input v-model="row.url" @blur="rowUrlBlur(row)" placeholder=""></el-input>
            </template>
            <!-- 行编辑url -->
            <template slot-scope="{scope,row,index,type,size}" slot="sort">
                <el-input v-model="row.sort" @blur="rowSortBlur(row)" placeholder=""></el-input>
            </template>


            <template slot-scope="{scope,row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row,index,row.disable)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <template slot-scope="{row,index,type,size}" slot="addMenuOrPage">
                <el-button type="primary" plain icon="el-icon-plus" v-show="row.root <= 2" size="mini" @click="addDialogVisible = true,row.nextRoot=2">菜单</el-button>
                <el-button type="primary" plain icon="el-icon-plus" v-show="row.root == 2" size="mini" @click="addDialogVisible = true,row.nextRoot=3">页面</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updPidDialogVisible = true">变更父级</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="head">
                <el-avatar :src="row.head"></el-avatar>
            </template>
        </avue-crud>
        <el-dialog title="新增" v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="编辑" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="变更父级" v-dialogDrag v-if="updPidDialogVisible" :visible.sync="updPidDialogVisible" width="25%" @close="closeDialog">
            <UpdPid :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></UpdPid>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>

    export default {
        components: {
            Add: () => import('./menuAdd'),
            Upd: () => import('./menuUpd'),
            UpdPid: () => import('./menuPid')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/menu/list?isTree=true",
                    info: "/api/admin/menu",
                    infoPidList: "/api/admin/menu/list?disable=0&isTree=true&root={root}",
                },
                dialogWidth: "60%",
                addDialogVisible: false,      // 添加弹层开关状态
                updDialogVisible: false,      // 添加弹层开关状态
                updPidDialogVisible: false,      // 变更父级开关状态
                page: this.website.pageParams,   // 分页参数
                search: {                        // 搜索参数
                    terminal: 2
                },
                data: [],                     // 列表数据
                rowData: {},                  // 当前选中行数据
                option: {}
            }
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudMenu)
        },
        mounted() {
            // 基础配置
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.index = false
            this.option.defaultExpandAll = true
            //this.option.cellBtnt = true
            this.option.rowKey = "id"
            this.option.height = 600
            // 字段配置
            this.option.treeProps = {
                children: 'menus'
            }
            this.option.column =
                [
                    {
                        label: '终端',
                        prop: 'terminal',
                        dicData: this.dict.get(this.website.Dict.Admin.Terminal, true, false, true),
                        search: true,
                        searchValue: this.search.terminal,
                        searchSpan: 5,
                        type: "select",
                        hide: true,
                    },
                    {
                        label: '菜单名称',
                        prop: 'name',
                        align: 'left',
                        search: false,
                        width: 200,
                        searchSpan: 5,
                    },
                    {
                        label: '路由',
                        prop: 'url',
                        align: 'left',
                        width: 300,
                        //cell: true
                    },
                    {
                        label: '排序',
                        prop: 'sort',
                        width: 150,
                    },
                    {
                        label: '图标',
                        prop: 'icon',
                        width: 150,
                    },
                    {
                        label: '目录级别',
                        prop: 'root',
                        // type: "switch",
                        dicData: this.dict.get(this.website.Dict.Base.MenuRoot),
                        width: 150,
                    },
                    {
                        label: '禁用/启用',
                        prop: 'disable',
                    },
                    {
                        label: '添加菜单/页面',
                        prop: 'addMenuOrPage',
                        width: 250,
                    },
                ]
        },

        methods: {
            /**
             * 直接触发：  首次自动加载 / 点击分页 / 切换分页 / 跳转也 / 点击刷新
             * 被调用触发：搜索后 /  添加/编辑保存后 / 删除后
             * @author wangsong
             */
            onLoad() {
                this.crud.list(this, false);
                this.crud.doLayout(this, this.$refs.crudMenu)
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
                this.updPidDialogVisible = false;
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
            // 编辑url
            rowUrlBlur(row) {
                if (this.rowData.url != row.url) {
                    this.crud.put(this.uri.info + "/" + row.id, {url: row.url});
                }
            },
            // 编辑排序
            rowSortBlur(row) {
                if (this.rowData.sort != row.sort) {
                    this.crud.put(this.uri.info + "/" + row.id, {sort: row.sort});
                }
            },
            // 点击保存行数据(供行操作的任意地方获取数据)
            handleRowClick(row) {
                this.rowData = JSON.parse(JSON.stringify(row));
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