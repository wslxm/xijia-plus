<template>
    <div>
        <el-card class="box-card" style="width: 100%;margin-left: 0%">
            <el-row>
                <!-- 左边媒体分类树结构数据 -->
                <el-col :span="5">
                    <el-card class="box-card" style="width: 98%;">
                        <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick">
                            <template slot-scope="scope" slot="menu">
                                <div class="avue-tree__item" @click="updRow({},0)"><i class="el-icon-plus"> 顶级字典</i></div>
                                <div v-show="!checkNumber(scope.node.code)" class="avue-tree__item" @click="updRow(scope.node,2)"><i class="el-icon-plus"> 子级字典</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,4)"><i class="el-icon-edit"> 变更父级</i></div>
                                <div class="avue-tree__item" @click="generateDict(scope.node)"><i class="el-icon-eleme"> 生成枚举</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,1)"><i class="el-icon-edit"> 编辑</i></div>
                                <div class="avue-tree__item" @click="findLeftTree()"><i class="el-icon-refresh"> 刷新</i></div>
                                <div class="avue-tree__item" @click="updRow(scope.node,5)"><i class="el-icon-delete"> 删除</i></div>
                            </template>
                        </avue-tree>
                    </el-card>
                </el-col>
                <el-col :span="19">
                    <avue-crud ref="crudDict"
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
                        <!-- 启用/禁用插槽(默认提供,按需使用) -->
                        <template slot-scope="{row,index,type,size}" slot="disable">
                            <el-switch v-model="row.disable" @change="updDisable(row)"
                                       active-color="#13ce66" inactive-color="#ff4949"
                                       :active-value=0 :inactive-value=1
                                       active-text="" inactive-text="">
                            </el-switch>
                        </template>

                        <!-- 列表上进行编辑sort -->
                        <template slot-scope="{row,index,type,size}" slot="sort">
                            <el-input v-model="row.sort" @blur="rowSortBlur(row)" placeholder=""></el-input>
                        </template>

                        <!-- 新增父类  -->
                        <template slot-scope="" slot="menuLeft">
                            <!--  <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="updRow({},0)">顶级</el-button>-->
                            <el-button type="primary" plain icon="el-icon-plus" size="mini" v-show="!checkNumber(rowTreeData.code)" @click="updRow(rowTreeData,2)">子级字典</el-button>
                            <!--   <el-button type="primary" plain size="mini" @click="updRow(rowTreeData,3) ">变更父级</el-button>-->
                            <!--   <el-button type="primary" plain size="mini" @click="generateDict()">生成枚举</el-button>-->
                            <!--   <el-button type="primary" plain size="mini" @click="findLeftTree()">重载左侧</el-button>-->
                        </template>

                        <template slot-scope="{row,index,type,size}" slot="menu">
                            <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
                            <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
                        </template>
                    </avue-crud>
                </el-col>
            </el-row>
        </el-card>

        <!-- 弹层 -->
        <el-dialog :title="rowData.name?'新增 - ['+ rowData.name + '] - 子级字典':'新增顶级字典'"
                   v-dialogDrag
                   v-if="addDialogVisible"
                   :visible.sync="addDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog :title="'编辑 -- '+rowData.name"
                   v-dialogDrag
                   v-if="updDialogVisible"
                   :visible.sync="updDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog :title="'变更父级 -- '+rowData.name"
                   adminDictionary.vue
                   v-if="updPidDialogVisible"
                   :visible.sync="updPidDialogVisible"
                   width="35%"
                   top="6vh"
                   @close="closeDialog">
            <AdminDictionaryPid :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></AdminDictionaryPid>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="预览"
                   adminDictionary.vue
                   v-if="generateDictDialogVisible"
                   :visible.sync="generateDictDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <GenerateDict :closeDialog="closeDialog" :generateDictData="generateDictData"></GenerateDict>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>
export default {
    components: {
        Add: () => import('./dictionaryAdd'),
        Upd: () => import('./dictionaryUpd'),
        GenerateDict: () => import('./generateDictPreview'), //生成js/枚举类预览
        AdminDictionaryPid: () => import('./dictionaryPid') //变更父级
    },
    data() {
        return {
            treeOption: JSON.parse(JSON.stringify(this.website.treeOption)),
            treeData: [],
            rowTreeData: {},
            uri: {
                infoList: "/api/admin/sys/dictionary/list",
                info: "/api/admin/sys/dictionary",
                generateEnum: "/api/admin/sys/dictionary/generateEnum?enumsKey={enumsKey}",
                updPidInfoList: "/api/admin/sys/dictionary/list?isTree=true&isBottomLayer=false",
            },
            loading: true,
            dialogWidth: "60%",
            addDialogVisible: false,
            updDialogVisible: false,
            updPidDialogVisible: false,
            generateDictDialogVisible: false,
            page: this.website.pageParams,
            search: {}, // 一级/二级/三级选中数据
            data: [],
            rowData: {},
            option: {},
            dictOneDict: [], // 一级选择数据
            dictTwoDict: [], // 二级选择数据
            dictThreeDict: [], // 三级选择数据
            generateDictData: [], // 生成枚举数据
        }
    },
    mounted() {

        // 1、左侧菜单配置
        this.treeOption.props.label = "name";
        this.treeOption.props.children = "dictList";
        this.treeOption.menu = true;
        // 查询左侧菜单数据
        this.findLeftTree();

        this.option = JSON.parse(JSON.stringify(this.website.optionConfig));

        //this.option.menu = false
        this.option.defaultExpandAll = true;
        this.option.index = false;
        this.option.rowKey = "id";
        //this.option. = "id"
        this.option.height = 600;
        this.option.treeProps = {
            children: 'dictList'
        };
        this.option.column = [
            {
                label: '字典名称',
                prop: 'name',
                search: false,
                overHidden: true,
            },
            {
                label: '字典Code',
                prop: 'code',
                search: false,
                overHidden: true,
            },
            {
                label: '描叙',
                prop: 'desc',
                search: false,
                overHidden: true,
            },
            {
                label: '排序',
                prop: 'sort',
                search: false,
                overHidden: true,
            },
            {
                label: '禁用',
                prop: 'disable',
                search: false,
                overHidden: true,
            },
        ]
    },
    created() {
    },
    // 被 keep-alive 缓存的组件激活时调用, crudDoLayout为防止表格错乱
    activated: function () {
        this.crud.doLayout(this, this.$refs.crudDict)
    },
    methods: {
        onLoad() {
            this.search.code = this.rowTreeData.code;
            // 是否查询所有层级数据
            this.search.isNextAll = false;
            // 开始查询
            this.crud.list(this, false).then((res) => {
                this.crud.doLayout(this, this.$refs.crudDict)
            });
        },
        searchChange(done) {
            this.page.currentPage = 1;
            this.onLoad();
            done();
        },
        /**
         * 查询左侧 字典数据
         */
        findLeftTree() {
            this.crud.get(this.uri.infoList, {isTree: true, isBottomLayer: false}).then((res) => {
                this.treeData = res.data.data;
            });
        },
        closeDialog(isRefresh) {
            this.addDialogVisible = false;
            this.updDialogVisible = false;
            this.generateDictDialogVisible = false;
            this.updPidDialogVisible = false;
            this.rowData = {};
            if (isRefresh != null && isRefresh) {
                this.onLoad();
            }
        },
        // 行编辑
        updRow(row, type) {
            this.rowData = row;
            switch (type) {
                case 0:
                    // 新增顶级
                    this.addDialogVisible = true;
                    break;
                case 1:
                    // 编辑
                    this.updDialogVisible = true;
                    break;
                case 2:
                    // +子类
                    if (Object.keys(row).length === 0) {
                        this.$message.error('没有选择字典');
                        break;
                    } else {
                        this.addDialogVisible = true;
                        break;
                    }
                case 4:
                    // 变更父级
                    this.updPidDialogVisible = true;
                    break;
                case 5:
                    // 删除(左边树上删除)
                    this.$confirm('此操作将永久删除该字典及子字典数据, 是否继续?', '提示', {
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
        rowDel(row, index) {
            this.crud.delRow(this, this.uri.info, row.id, index);
        },
        // 排序
        rowSortBlur(row) {
            if (this.rowData.sort != row.sort) {
                this.crud.put(this.uri.info + "/" + row.id, {sort: row.sort});
            }
        },
        // 启用/禁用
        updDisable(row) {
            this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
        },
        handleRowClick(row) {
            this.rowData = JSON.parse(JSON.stringify(row));
            //this.rowData = row;
        },
        cellStyle({row, column}) {
            if (column.property == "disable") {
                // fontWeight: 'bold',fontSize: '20'
                return row.disable == 0 ? {color: 'green'} : {color: 'red'}
            }
        },
        handleChange(form) {
            this.$message.success(JSON.stringify(form))
        },
        //验证字符串是否是数字
        checkNumber(theObj) {
            var reg = /^[0-9]+.?[0-9]*$/;
            if (reg.test(theObj)) {
                return true;
            }
            return false;
        },
        // 生成枚举
        generateDict(row) {
            let enumsKey = "ENUMS";
            if (row.code != null) {
                enumsKey = row.code;
            }
            this.crud.get(this.uri.generateEnum.replace("{enumsKey}", enumsKey)).then((res) => {
                this.generateDictDialogVisible = true;
                this.generateDictData = res.data.data;
            })
        },
        /**
         * 点击左菜单重置右菜单数据
         * @param data
         */
        nodeClick(data) {
            this.rowTreeData = data;
            this.search.pid = data.id;
            this.onLoad();
        }
    }
}
</script>
<style>
</style>
