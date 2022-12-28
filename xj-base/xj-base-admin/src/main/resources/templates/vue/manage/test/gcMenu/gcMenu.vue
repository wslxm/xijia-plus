<template>
    <div>
        <el-card class="box-card" style="width: 100%;margin-left: 0%">
            <el-row>
                <!-- 左自关联数据 -->
                <el-col :span="5">
                    <el-card class="box-card" style="width: 98%;">
                        <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick">
                            <!-- 图标/文字- 根据根据不同内容展示不同图标,或者不要  -->
                            <span class="el-tree-node__label" slot-scope="{ node, data }">
                               <i class="el-icon-document-remove"></i>  &nbsp; {{ data.name }}
                           </span>
                            <!-- 右键按钮  -->
                            <template slot-scope="scope" slot="menu">
                                <div class="avue-tree__item" @click="updRow(scope.node,1)"><i class="el-icon-plus"> 顶级</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,2)"><i class="el-icon-plus"> 子级</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,3)"><i class="el-icon-edit"> 变更父级</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,4)"><i class="el-icon-edit"> 编辑</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,5)"><i class="el-icon-refresh"> 刷新</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,6)"><i class="el-icon-delete"> 删除</i></div>
                            </template>
                        </avue-tree>
                    </el-card>
                </el-col>
                <!-- 列表 -->
                <el-col :span="19">
                    <avue-crud ref="crudgcMenu"
                               :data="data"
                               :option="option"
                               :page.sync="page"
                               :search.sync="search"
                               :table-loading="loading"
                               :cell-style="cellStyle"
                               @on-load="onLoad"
                               @refresh-change="onLoad"
                               @search-change="searchChange"
                               @row-click="handleRowClick">
                        <template slot-scope="" slot="menuLeft">
                            <!--  <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="updRow(rowData,2)">数据</el-button>-->
                        </template>
                         <!-- 启用/禁用插槽(默认提供,按需使用) -->
                        <template slot-scope="{scope,row,index,type,size}" slot="disable">
                            <el-switch v-model="row.disable" @change="updDisable(row,index,row.disable)"
                                       active-color="#13ce66" inactive-color="#ff4949"
                                       :active-value=0 :inactive-value=1
                                       active-text="" inactive-text="">
                            </el-switch>
                        </template>

 

                        <template slot-scope="{row,index,type,size}" slot="menu">
                            <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,4)">编辑</el-button>
                            <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
                        </template>
                    </avue-crud>
                </el-col>
            </el-row>
        </el-card>
        <el-dialog :title="rowData.name?'在 - 【'+ rowData.name + '】 - 下新增数据':'添加顶级数据'" v-dialogDrag
                   v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog :title="'编辑数据 - 【'+rowData.name + '】'" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog :title="'变更数据 - 【'+rowData.name + '】 的父级'" v-dialogDrag v-if="updPidDialogVisible" :visible.sync="updPidDialogVisible" width="35%" @close="closeDialog">
            <UpdPid :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></UpdPid>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>

export default {
    components: {
        Add: () => import('./gcMenuAdd'),
        Upd: () => import('./gcMenuUpd'),
        UpdPid: () => import('./gcMenuPid')
    },
    data() {
        return {
            treeOption: JSON.parse(JSON.stringify(this.website.treeOption)),
            uri: {
                infoTree: "/api/admin/test/gcMenu/tree",
                infoList: "/api/admin/test/gcMenu/findPage",
                info: "/api/admin/test/gcMenu",
            },
            loading: true,
            dialogWidth: "60%",
            addDialogVisible: false,      // 添加弹层开关状态
            updDialogVisible: false,      // 添加弹层开关状态
            updPidDialogVisible: false,      // 变更父级开关状态
            page: this.website.pageParams,   // 分页参数
            search: {},                      // 搜索参数
            treeData: [],                 // 左侧树数据
            data: [],                     // 右侧列表数据
            rowData: {},                  // 当前选中行数据(左树列表)
            option: {}                    // 列表配置
        }
    },
    activated:
        function () {
            this.crud.doLayout(this, this.$refs.crudgcMenu)
        }
    ,
    mounted() {
        // 1、左侧菜单配置
        this.treeOption.props.label = "name";
        this.treeOption.props.children = "children";
        this.treeOption.menu = true;
        // 查询左侧菜单数据
        this.findLeftTree();

        // 2、右侧表格配置(树结构)
        this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
        this.option.index = true;
        this.option.cellBtnt = true
        this.option.column = [
                            {
                    label: '指定父id',
                    prop: 'pid',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '菜单名',
                    prop: 'name',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '第二路由 ',
                    prop: 'twoUrl',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '菜单url',
                    prop: 'url',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '图标 ',
                    prop: 'icon',
                    // type: 'icon',
                    html: true,
                    formatter: (val) => {
                        return '<i class=' + val.icon + '></i>'
                    }
                },
                {
                    label: '排序',
                    prop: 'sort',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '目录级别',
                    prop: 'root',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '禁用',
                    prop: 'disable',
                    type: 'select',
                    search: true,
                    filterable:true,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get('DISABLE'),
                },

        ]
    },

    methods: {
        onLoad() {
            this.crud.list(this, true);
            this.crud.doLayout(this, this.$refs.crudgcMenu);
        },
        searchChange(params, done) {
            this.page.currentPage = 1;
            this.findLeftTree();
            this.onLoad();
            done();
        },
        findLeftTree() {
            this.crud.get(this.uri.infoTree, {terminal: this.search.terminal}).then((res) => {
                this.treeData = res.data.data;
            });
        },
        // 点击左左侧数据 重置右列表数据
        nodeClick(data) {
            //this.$message.success(JSON.stringify(data))
            this.rowData = data;
            this.search.pid = data.id;
            this.onLoad();
        },

        closeDialog(isRefresh) {
            this.addDialogVisible = false;
            this.updDialogVisible = false;
            this.updPidDialogVisible = false;
            this.rowData = {};
            if (isRefresh != null && isRefresh) {
                this.onLoad();
            }
        },
        // 行操作
        updRow(row, type) {
            this.rowData = row;
            switch (type) {
                case 1:
                    // +顶级数据
                    this.rowData = {};
                    this.addDialogVisible = true;
                    break;
                case 2:
                    // +子级数据
                    this.addDialogVisible = true;
                    break;
                case 3:
                    // 变更父级
                    this.updPidDialogVisible = true;
                    break;
                case 4:
                    // 编辑
                    this.updDialogVisible = true;
                    break;
                case 5:
                    // 刷新
                    this.findLeftTree()
                    break;
                case 6:
                    // 删除(左边树上删除)
                    this.$confirm('此操作将永久删除【' + row.name + '】以及子数据, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.crud.del(this.uri.info + "/" + this.rowData.id).then((res) => {
                            this.findLeftTree();
                            this.onLoad();
                            done();
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
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
        },
    }
}
</script>
