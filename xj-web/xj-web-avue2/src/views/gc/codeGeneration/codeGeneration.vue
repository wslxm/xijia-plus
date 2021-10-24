<template>
    <div >
        <el-container>
            <el-aside style="padding-left: 10px;padding-top: 2.5px" width="220px">
                <span>数据表选择</span>
                <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
            </el-aside>
            <el-main>
                当前表: <font color="#ff69b4"> {{treeRowData.name}} - {{treeRowData.comment}}</font>
                <!--crud-->
                <avue-crud
                        ref="crudField"
                        :data="data"
                        :option="option"
                        :page.sync="page"
                        :search.sync="search"
                        @on-load="onLoad"
                        @refresh-change="onLoad"
                        @selection-change="selectionChange"
                        @search-change="searchChange"
                        @row-click="handleRowClick">
                    <template slot-scope="scope" slot="menuLeft">
                        <el-button type="primary" size="small" plain @click="finDGenerateGetPath()">查看生成路径</el-button>
                        <el-button type="primary" size="small" plain @click="findGeneratePreview()">生成预览代码(在线查看)</el-button>
                        <el-button type="primary" size="small" plain @click="addDialogVisible = true">生成后端代码</el-button>
                        <el-button type="primary" size="small" plain @click="addDialogVisible = true">生成layui代码</el-button>
                        <el-button type="primary" size="small" plain @click="addDialogVisible = true">生成vue代码</el-button>
                    </template>
                    <template slot-scope="{row,index,type,size}" slot="menu">
                       <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                       <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>-->
                    </template>
                </avue-crud>
                <!--crud-->
            </el-main>
        </el-container>

        <!-- {{generatePaths}}-->
        <el-dialog title="查看生成路径" :visible.sync="findPageDialogVisible" width="60%" :destroy-on-close="true">
            <Paths :generatePaths="generatePaths"></Paths>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="生成代码预览" :visible.sync="generateCodePreviewDialogVisible" width="80%" :destroy-on-close="true">
            <CodePreview :generateCodePreviews="generateCodePreviews"></CodePreview>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <!-- <el-dialog title="新增" :visible.sync="addDialogVisible" :width="dialogWidth" @close="closeDialog" :destroy-on-close="true">-->
        <!--     <Add :closeDialog="closeDialog" :uri="uri"></Add>-->
        <!--     <span slot="footer" class="dialog-footer"></span>-->
        <!-- </el-dialog>-->
        <!-- <el-dialog title="编辑" :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog" :destroy-on-close="true">-->
        <!--     <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>-->
        <!--     <span slot="footer" class="dialog-footer"></span>-->
        <!-- </el-dialog> -->
    </div>
</template>


<script>
    import {get, post} from '@/api/crud';
    import website from '@/config/website';

    export default {
        components: {
            // Add: () => import('./adminAuthorityAdd'),
            // Upd: () => import('./adminAuthorityUpd')
            Paths: () => import('./generatePaths'),
            CodePreview: () => import('./generateCodePreview.vue')
        },
        data() {
            return {
                uri: {
                    infoFieldList: "/api/admin/dataBase/table/field",  // 根据字段查询表字段
                    infoTableList: "/api/admin/dataBase/table/list",   // 所有表
                    generateGetPath: "/api/admin/generate/getPath",    // 代码生成路径
                    generatePreview: "/api/admin/generate/preview",    // 生成预览代码
                },
                dialogWidth: "60%",
                findPageDialogVisible: false,
                generateCodePreviewDialogVisible: false,
                updDialogVisible: false,
                page: website.pageParams,
                search: {tableName: "t_basic"},
                data: [],
                checkednDatas: [],         // 复选选择数据
                rowData: {},
                option: {},
                generatePaths: {},         // 代码生成路径数据
                generateCodePreviews: {},  // 预览代码数据
                // 数据表
                treeRowData: {name: "t_basic", comment: "系统通用字段表"},
                treeData: [],
                treeOption: {
                    defaultExpandAll: false,
                    filter: false,
                    formOption: {
                        labelWidth: 100,
                        column: [{
                            label: '搜索',
                            prop: 'name'
                        }],
                    },
                    props: {
                        labelText: '数据表',
                        label: 'name',
                        value: 'name',
                        children: ''
                    }
                },
            }
        },
        mounted() {
            this.option = website.optionConfig
            this.option.index = false;
            this.option.menu = false;
            //this.option.height = 200;
            // 开启多选
            this.option.selection = true,
                this.option.column = [
                    {
                        label: '字段名',
                        prop: 'name',
                        align: 'left',
                        width: 300,
                    },
                    {
                        label: '字段类型(长度)',
                        prop: 'typeDetail',
                        align: 'left',
                    },
                    {
                        label: '字段描叙',
                        prop: 'desc',
                        align: 'left',
                        overHidden: true
                    },

                    {
                        label: '是否允许空',
                        prop: 'isNull',
                        align: 'left',
                    },
                    {
                        label: '默认值',
                        prop: 'defaultVal',
                        align: 'left',
                    },
                ]
        },
        created() {
            get(this.uri.infoTableList).then((res) => {
                this.treeData = res.data.data;
            })

        },
        methods: {
            onLoad() {
                get(this.uri.infoFieldList, {tableName: this.search.tableName}).then((res) => {
                    this.data = res.data.data;
                    this.checkeds();
                })
            },
            searchChange(params, done) {
                this.page.currentPage = 1;
                this.onLoad();
                done();
            },
            handleRowClick(row) {
                this.rowData = row;
            },
            // 选择数据表
            nodeClick(data) {
                this.treeRowData = data;
                this.search.tableName = data.name;
                this.onLoad();
                //this.$message.success(JSON.stringify(data))
            },
            // 选中的复选字段
            selectionChange(list) {
                this.checkednDatas = list;
                // 先修改原数据全为false不选择
                // 先修改原数据全为false不选择
                // for (let i = 0; i < this.data.length; i++) {
                //     if (list.includes(this.data[i])) {
                //         this.data[i].isChecked = true
                //     }else{
                //         this.data[i].isChecked = false
                //     }
                // }
                // this.$message.success(JSON.stringify(this.checkednDatas))
            },
            // 复选选中数据处理
            checkeds() {
                this.$nextTick(function () {
                    for (let i = 0; i < this.data.length; i++) {
                        if (this.data[i].isChecked) {
                            this.$refs.crudField.toggleSelection([this.data[i]])
                        }
                    }
                })
            },
            // 获取代码生成路径
            finDGenerateGetPath() {
                get(this.uri.generateGetPath, {tableName: this.search.tableName}).then((res) => {
                    this.generatePaths = res.data.data;
                    this.findPageDialogVisible = true;
                })
            },
            // 生成预览代码
            findGeneratePreview() {
                let data = {
                    tableComment: this.treeRowData.comment,
                    tableName: this.search.tableName,
                    dataSourceId: "",
                    data: JSON.stringify(this.data)
                }
                post(this.uri.generatePreview, data).then((res) => {
                    this.generateCodePreviews = res.data.data;
                    this.generateCodePreviewDialogVisible = true;
                })
            }

        }
    }
</script>


<style>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 100%;
    }
</style>
