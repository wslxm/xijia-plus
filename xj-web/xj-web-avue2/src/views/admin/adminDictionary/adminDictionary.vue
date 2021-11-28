<template>
    <div>
        <!-- 搜索 -->
        <el-card class="el-card__body">
            <el-col :span="24">
                <label>字典筛选: </label>
                <span style="margin-left: 5px">
                    <el-select v-model="search.diceOneCode" clearable size="small" style="width: 220px" placeholder="筛选一级" @change="diceOneHandle(search.diceOneCode)">
                    <el-option
                            v-for="item in dictOneDict"
                            :key="item.code"
                            :label="item.name"
                            :value="item.code">
                    </el-option>
                </el-select>
                </span>
                <span style="margin-left: 20px">
                    <el-select v-model="search.diceTwoCode" clearable size="small" style="width: 220px" placeholder="筛选二级" @change="diceTwoHandle(search.diceTwoCode)">
                    <el-option
                            v-for="item in dictTwoDict"
                            :key="item.code"
                            :label="item.name"
                            :value="item.code">
                    </el-option>
                </el-select></span>
                <span style="margin-left: 20px">
                    <el-select v-model="search.diceThreeCode" clearable size="small" style="width: 220px" placeholder="筛选三级" @change="diceThreeHandle(search.diceThreeCode)">
                    <el-option
                            v-for="item in dictThreeDict"
                            :key="item.code"
                            :label="item.name"
                            :value="item.code">
                    </el-option>
                </el-select></span>
                <span style="margin-left: 20px">
                    <el-button type="primary" size="small" plain @click="generateDict()">生成枚举</el-button>
                </span>
            </el-col>
        </el-card>

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
            <template slot-scope="{scope,row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <!-- 添加子类别 type=2 -->
            <template slot-scope="{row,index,type,size}" slot="addDict">
                <el-button type="primary" plain icon="el-icon-plus" v-show="!checkNumber(row.code)" size="mini" @click="addDialogVisible = true">子类别
                </el-button>
            </template>
            <template slot-scope="scope" slot="menuLeft">
                <!-- 新增父类 type=1 -->
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增父类</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button> <!--row.type=3,-->
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updPidDialogVisible = true">变更父级</el-button> <!--row.type=3,-->
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
        </avue-crud>
        <!-- 弹层 -->
        <el-dialog :title="rowData.name?'新增 ['+ rowData.name + '] 的子类':'新增父类'"
                   v-dialogDrag
                   v-if="addDialogVisible"
                   :visible.sync="addDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="编辑"
                   v-dialogDrag
                   v-if="updDialogVisible"
                   :visible.sync="updDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="变更父级"
                   adminDictionary.vue
                   v-if="updPidDialogVisible"
                   :visible.sync="updPidDialogVisible"
                   width="30%"
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
            Add: () => import('./adminDictionaryAdd'),
            Upd: () => import('./adminDictionaryUpd'),
            GenerateDict: () => import('./generateDictPreview'), //生成js/枚举类预览
            AdminDictionaryPid: () => import('./adminDictionaryPid') //变更父级
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/dictionary/list",
                    info: "/api/admin/dictionary",
                    generateEnum: "/api/admin/dictionary/generateEnum?enumsKey={enumsKey}",
                    updPidInfoList: "/api/admin/dictionary/list?isTree=true&isBottomLayer=false",
                    updPidInfoList: "/api/admin/dictionary/list?isTree=true&isBottomLayer=false",
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
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));

            //this.option.menu = false
            this.option.defaultExpandAll = true
            this.option.index = false
            this.option.rowKey = "id"
            //this.option. = "id"
            this.option.height = 600
            this.option.treeProps = {
                children: 'dictList'
            }
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
                {
                    label: '添加子类别',
                    prop: 'addDict',
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
                console.log("===")
                // 获取搜索key- code
                let code = null;
                if (this.search.diceThreeCode != null) {
                    code = this.search.diceThreeCode;
                } else if (this.search.diceTwoCode != null) {
                    code = this.search.diceTwoCode;
                } else if (this.search.diceOneCode != null) {
                    code = this.search.diceOneCode;
                }
                this.search.code = code;
                // 开始查询
                this.crud.list(this, false).then((res) => {
                    // 首次查询处理一级数据
                    if (this.dictOneDict.length == 0) {
                        this.dictOneDict = this.data;
                    }
                    this.crud.doLayout(this, this.$refs.crudDict)
                })
            },
            searchChange(done) {
                this.page.currentPage = 1;
                this.onLoad();
                done();
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
            rowDel(row, index) {
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
            },
            handleRowClick(row) {
                this.rowData = row;
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
            /**
             * 处理一级选择数据
             * @author wangsong
             * @date 2021/11/11 0011 22:55
             * @return
             * @version 1.0.0
             */
            diceOneHandle(diceOneCode) {
                this.crud.get(this.uri.infoList, {code: diceOneCode}).then((res) => {
                    this.data = res.data.data;
                    this.dictTwoDict = res.data.data[0].dictList;
                    // 选中下级数据置空
                    this.search.diceTwoCode = null;
                    this.search.diceThreeCode = null;
                })
            },
            diceTwoHandle(diceTwoCode) {
                if (diceTwoCode) {
                    this.crud.get(this.uri.infoList, {code: diceTwoCode}).then((res) => {
                        this.data = res.data.data;
                        this.dictThreeDict = res.data.data[0].dictList;
                        // 选中下级数据置空
                        this.search.diceThreeCode = null;
                    })
                } else {
                    this.diceOneHandle(this.search.diceOneCode)
                }
            },
            diceThreeHandle(diceThreeCode) {
                if (diceThreeCode) {
                    this.crud.get(this.uri.infoList, {code: diceThreeCode}).then((res) => {
                        this.data = res.data.data;
                    })
                } else {
                    this.diceTwoHandle(this.search.diceTwoCode)
                }
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
            generateDict() {
                let enumsKey = "ENUMS";
                if (this.search.diceThreeCode != null) {
                    enumsKey = this.search.diceThreeCode;
                } else if (this.search.diceTwoCode != null) {
                    enumsKey = this.search.diceTwoCode;
                } else if (this.search.diceOneCode != null) {
                    enumsKey = this.search.diceOneCode;
                }
                this.crud.get(this.uri.generateEnum.replace("{enumsKey}", enumsKey)).then((res) => {
                    this.generateDictDialogVisible = true;
                    this.generateDictData = res.data.data;
                })
            }
        }
    }
</script>
<style>
</style>
