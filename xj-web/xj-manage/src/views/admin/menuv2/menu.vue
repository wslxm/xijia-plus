<template>
    <div>
        <el-card class="box-card" style="width: 100%;margin-left: 0%">
            <el-row>
                <!-- 左边媒体分类树结构数据 -->
                <el-col :span="5">
                    <el-card class="box-card" style="width: 98%;">
                        <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick">
                            <span class="el-tree-node__label" slot-scope="{ node, data }">
                               <i :class="data.icon"></i>  &nbsp; {{ data.name }}
                           </span>
                            <template slot-scope="scope" slot="menu">
                                <div class="avue-tree__item" @click="addDialogVisible = true"><i class="el-icon-plus"> 顶部菜单</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,2)" v-show="scope.node.root <= 2"><i class="el-icon-plus"> 子级菜单</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,3) " v-show="scope.node.root <= 2"><i class="el-icon-plus"> 子级页面</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,4)"><i class="el-icon-edit"> 变更父级</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,1)"><i class="el-icon-edit"> 编辑</i></div>
                                <div class="avue-tree__item" @click="findLeftTree()"><i class="el-icon-refresh"> 刷新</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,5)"><i class="el-icon-delete"> 删除</i></div>
                            </template>
                        </avue-tree>
                    </el-card>
                </el-col>
                <el-col :span="19">
                    <avue-crud ref="crudMenu"
                               :data="data"
                               :option="option"
                               :search.sync="search"
                               :table-loading="loading"
                               :cell-style="cellStyle"
                               @on-load="onLoad"
                               @refresh-change="onLoad"
                               @search-change="searchChange"
                               @row-click="handleRowClick">

                        <template slot-scope="" slot="menuLeft">
                            <!--                            <el-button type="primary" plain icon="el-icon-plus" v-show="rowTreeData.root === 1" size="mini" @click="addDialogVisible = true">目录</el-button>-->
                            <el-button type="primary" plain icon="el-icon-plus" v-show="rowTreeData.root <= 2" size="mini" @click="updRow(rowTreeData,2)">子级菜单</el-button>
                            <el-button type="primary" plain icon="el-icon-plus" v-show="rowTreeData.root <= 2" size="mini" @click="updRow(rowTreeData,3)">子级页面</el-button>
                            <!--                            <el-button type="primary" plain icon="el-icon-plus" v-show="rowTreeData.root >= 2" size="mini" @click="updRow(rowTreeData,4)">变更父级</el-button>-->
                            <!--                            <el-button type="primary" plain size="mini" @click="findLeftTree()">重载左侧</el-button>-->
                        </template>

                        <!-- 图标 -->
                        <!--  <template slot-scope="{row,index,type,size}" slot="icon">-->
                        <!--      <i :class="row.icon"></i>-->
                        <!--  </template>-->
                        <!-- 列表上进行编辑url -->
                        <template slot-scope="{row,index,type,size}" slot="url">
                            <el-input v-model="row.url" @blur="rowUrlBlur(row)" placeholder=""></el-input>
                        </template>
                        <!-- 列表上进行编辑sort -->
                        <template slot-scope="{row,index,type,size}" slot="sort">
                            <el-input v-model="row.sort" @blur="rowSortBlur(row)" placeholder=""></el-input>
                        </template>
                        <template slot-scope="{scope,row,index,type,size}" slot="disable">
                            <el-switch v-model="row.disable" @change="updDisable(row,index,row.disable)"
                                       active-color="#13ce66" inactive-color="#ff4949"
                                       :active-value=0 :inactive-value=1
                                       active-text="" inactive-text="">
                            </el-switch>
                        </template>
                        <template slot-scope="{row,index,type,size}" slot="menu">
                            <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
                            <!-- <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,4)">变更父级</el-button>-->
                            <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
                        </template>
                        <template slot-scope="{row,index,type,size}" slot="head">
                            <el-avatar :src="row.head"></el-avatar>
                        </template>
                    </avue-crud>
                </el-col>
            </el-row>
        </el-card>
        <el-dialog :title="rowData.name?'在 - ['+ rowData.name + '] - 下新增字典':'添加顶级数据'"
                   v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog :title="'编辑 -- '+rowData.name" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="变更父级" v-dialogDrag v-if="updPidDialogVisible" :visible.sync="updPidDialogVisible" width="35%" @close="closeDialog">
            <UpdPid :closeDialog="closeDialog" :uri="uri" :rowData="rowTreeData"></UpdPid>
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
            treeOption: JSON.parse(JSON.stringify(this.website.treeOption)),
            uri: {
                infoList: "/api/admin/sys/menu/tree",
                info: "/api/admin/sys/menu",
                updPidInfoList: "/api/admin/sys/menu/tree?root=2",
            },
            loading: true,
            dialogWidth: "60%",
            addDialogVisible: false,      // 添加弹层开关状态
            updDialogVisible: false,      // 添加弹层开关状态
            updPidDialogVisible: false,      // 变更父级开关状态
            page: this.website.pageParams,   // 分页参数
            search: {                        // 搜索参数
            },
            treeData: [],                 // 左侧菜单数据
            rowTreeData: [],              // 当前选中左侧菜单行数据
            data: [],                     // 右侧列表数据
            rowData: {},                  // 当前选中行数据
            option: {}
        }
    },
    activated:
        function () {
            this.crud.doLayout(this, this.$refs.crudMenu)
        }
    ,
    mounted() {
        // 1、左侧菜单配置
        this.treeOption.props.label = "name";
        this.treeOption.props.children = "menus";
        this.treeOption.menu = true;
        // 查询左侧菜单数据
        this.findLeftTree();

        // 2、右侧表格配置(树结构)
        this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
        this.option.index = false;
        this.option.defaultExpandAll = true;
        //this.option.cellBtnt = true
        this.option.rowKey = "id";
        this.option.height = 600;
        this.option.searchBtnText = "搜索并重载左侧";
        // 字段配置
        this.option.treeProps = {
            children: 'menus'
        };
        this.option.column =
            [
                {
                    label: '菜单名称',
                    prop: 'name',
                    align: 'left',
                    search: false,
                    width: 200,
                    searchSpan: 5,
                },
                // {
                //     label: '图标',
                //     prop: 'icon',
                //     // type: 'icon',
                //     html: true,
                //     formatter: (val) => {
                //         return "<i class=\"" + val.icon + "\"></i>"
                //     }
                // },
                {
                    label: '路由',
                    prop: 'url',
                    align: 'left',
                    width: 350,
                    //cell: true
                },
                {
                    label: '排序',
                    prop: 'sort',
                    //width: 150,
                },

                // {
                //     label: '目录级别',
                //     prop: 'root',
                //     // type: "switch",
                //     dicData: this.dict.get(this.website.Dict.Base.MenuRoot),
                //     width: 150,
                // },
                {
                    label: '禁用/启用',
                    prop: 'disable',
                },
                // {
                //     label: '添加菜单/页面',
                //     prop: 'addMenuOrPage',
                //     width: 250,
                // },
            ]
    }
    ,

    methods: {
        /**
         * 直接触发：  首次自动加载 / 点击分页 / 切换分页 / 跳转也 / 点击刷新
         * 被调用触发：搜索后 /  添加/编辑保存后 / 删除后
         * @author wangsong
         */
        onLoad() {
            // 列表默认查询顶级数据
            if (this.search.pid == null) {
                this.search.root = 1;
            } else {
                this.search.root = null;
            }
            this.search.isNextAll = false;
            this.search.isTree = true;
            // 查询
            this.crud.list(this, false);
            this.crud.doLayout(this, this.$refs.crudMenu);
        },
        // 搜索,并重置页数为1
        searchChange(params, done) {
            this.page.currentPage = 1;
            this.findLeftTree();
            this.onLoad();
            done();
        },


        /**
         * 查询左侧 菜单数据
         */
        findLeftTree() {
            this.crud.get(this.uri.infoList, {isTree: true, terminal: this.search.terminal}).then((res) => {
                this.treeData = res.data.data;
            });
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
        }
        ,
        // 行编辑
        updRow(row, type) {
            this.rowData = row;
            switch (type) {
                case 1:
                    // 编辑
                    this.updDialogVisible = true;
                    break;
                case 2:
                    // +菜单 (root级别 = 2)
                    this.addDialogVisible = true;
                    this.rowData.nextRoot = 2;
                    break;
                case 3:
                    // +页面 (root级别 = 3)
                    this.addDialogVisible = true;
                    this.rowData.nextRoot = 3;
                    break;
                case 4:
                    // 变更父级
                    this.updPidDialogVisible = true;
                    break;
                case 5:
                    // 删除(左边树上删除)
                    this.$confirm('此操作将永久删除该菜单及子菜单, 是否继续?', '提示', {
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
        }
        ,
        // 行删除
        rowDel(row, index) {
            this.crud.delRow(this, this.uri.info, row.id, index);
        }
        ,
        // 启用/禁用
        updDisable(row, index, disable) {
            this.crud.put(this.uri.info + "/" + row.id, {disable: disable});
        }
        ,
        // 编辑url
        rowUrlBlur(row) {
            if (this.rowData.url != row.url) {
                this.crud.put(this.uri.info + "/" + row.id, {url: row.url});
            }
        }
        ,
        // 编辑排序
        rowSortBlur(row) {
            if (this.rowData.sort != row.sort) {
                this.crud.put(this.uri.info + "/" + row.id, {sort: row.sort});
            }
        }
        ,
        // 点击保存行数据(供行操作的任意地方获取数据)
        handleRowClick(row) {
            this.rowData = JSON.parse(JSON.stringify(row));
        }
        ,
        // 自动配置,单元格样式数字，对指定列设置字体颜色,大小，粗细等
        cellStyle({row, column}) {
            if (column.property == "disable") {
                // fontWeight: 'bold',fontSize: '20'
                return row.disable == 0 ? {color: 'green'} : {color: 'red'}
            }
        },
        /**
         * 点击左菜单重置右菜单数据
         * @param data
         */
        nodeClick(data) {
            //this.$message.success(JSON.stringify(data))
            this.rowTreeData = data;
            this.search.pid = data.id;
            this.onLoad();
        }
    }
}
</script>