<template>
    <div>
        <el-container>
            <el-aside style="padding-left: 10px;padding-top: 2.5px" width="220px">
                <span>数据表选择</span>
                <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
            </el-aside>
            <el-main>
                当前表: <font color="#ff69b4"> {{treeRowData.name}} - {{treeRowData.comment}}</font>
                <!-- crud -->
                <avue-crud ref="crudField"
                           :data="data"
                           :option="option"
                           :page.sync="page"
                           :search.sync="search"
                           :table-loading="loading"
                           @on-load="onLoad"
                           @refresh-change="onLoad"
                           @selection-change="selectionChange"
                           @search-change="searchChange"
                           @row-click="handleRowClick">
                    <template slot-scope="scope" slot="menuLeft">
                        <el-button type="primary" size="small" plain @click="finDGenerateGetPath()">查看生成路径</el-button>
                        <el-button type="primary" size="small" plain @click="findGeneratePreview()">生成预览代码(在线查看)</el-button>
                        <el-button type="primary" size="small" plain @click="generateCodeJava()">生成代码</el-button>
                        <el-button type="primary" size="small" plain @click="generateCodeVueFun()">生成并下载vue代码</el-button>
                    </template>
                    <!-- 数据类型 -->
                    <template slot-scope="{scope,row,index,type,size}" slot="vueFieldType">
                        <!--<el-col :span="6">-->
                      <!--  <avue-select v-model="row.vueFieldType" placeholder="请选择内容" type="tree" :dic="vueFieldTypeDic"></avue-select>-->

                        <el-select v-model="row.vueFieldType" filterable placeholder="请选择">
                            <el-option
                                    v-for="item in vueFieldTypeDic"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                        <!-- </el-col>-->
                    </template>
                    <!-- 是否是搜索参数 -->
                    <template slot-scope="{scope,row,index,type,size}" slot="search">
                        <el-switch v-model="row.search"
                                   active-color="#13ce66" inactive-color="#ff4949"
                                   :active-value=true :inactive-value=false
                                   active-text="" inactive-text="">
                        </el-switch>
                    </template>
                    <template slot-scope="{row,index,type,size}" slot="menu">
                        <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                        <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
                    </template>
                </avue-crud>
                <!--crud-->
            </el-main>
        </el-container>

        <!-- {{generatePaths}}-->
        <el-dialog v-dialogDrag title="查看生成路径" v-if="findPageDialogVisible" :visible.sync="findPageDialogVisible" top="6vh" width="60%">
            <Paths :generatePaths="generatePaths"></Paths>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog v-dialogDrag title="生成代码预览" v-if="generateCodePreviewDialogVisible" :visible.sync="generateCodePreviewDialogVisible" top="6vh" width="80%">
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

    export default {
        components: {
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
                    generateCode: "/api/admin/generate/generateCode",   // 生成代码
                    generateCodeVue: "/api/admin/generate/generateCodeVue", // 只生成vue代码(直接下载)
                },
                loading: true,
                dialogWidth: "60%",
                findPageDialogVisible: false,
                generateCodePreviewDialogVisible: false,
                updDialogVisible: false,
                page: this.website.pageParams,
                search: {tableName: "t_basic"},
                data: [],
                rowData: {},
                option: {},
                generatePaths: {},         // 代码生成路径数据
                generateCodePreviews: {},  // 预览代码数据
                vueFieldTypeDic: this.dict.get(this.website.Dict.Base.VueFieldType),  // 字段类型选择数据
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
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.index = false;
            this.option.menu = false;
            this.option.rowKey = "id"
            //this.option.height = 200;
            // 开启多选
            this.option.selection = true;
            this.option.reserveSelection = true;
            this.option.column = [
                {
                    label: '字段名',
                    prop: 'name',
                    align: 'left',
                    // width: 200,
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
                    label: 'vue表单类型',
                    prop: 'vueFieldType',
                },
                {
                    label: '是否搜索(eq搜索)',
                    prop: 'search',
                    align: 'left',
                },
                {
                    label: '是否允许空(mysql)',
                    prop: 'isNull',
                    align: 'left',
                },
                {
                    label: '默认值(mysql)',
                    prop: 'defaultVal',
                    align: 'left',
                },

            ]
        },
        created() {
            this.crud.get(this.uri.infoTableList).then((res) => {
                this.treeData = res.data.data;
            })

        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudField)
        },
        methods: {
            onLoad() {
                this.loading = true;
                this.crud.get(this.uri.infoFieldList, {tableName: this.search.tableName}).then((res) => {
                    this.data = res.data.data;
                    res.data.data.forEach((item) => {
                        item.vueFieldType = 1;
                    })
                    this.checkeds();
                    this.loading = false;
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
            // 加载数据处理复选选中数据处理（调用 toggleSelection 后会自动触发 selectionChange）
            checkeds() {
                // 清除数据
                this.$refs.crudField.toggleSelection();
                // 加载数据
                for (let i = 0; i < this.data.length; i++) {
                    if (this.data[i].isChecked) {
                        this.$refs.crudField.toggleSelection([this.data[i]]);
                    }
                }
            },
            // 选择数据表  查询刷新字段表数据
            nodeClick(data) {
                this.treeRowData = data;
                this.search.tableName = data.name;
                this.onLoad();
                //this.$message.success(JSON.stringify(data))
            },
            // 选中的复选字段，给所有数据处理当前 isChecked 参数
            selectionChange(list) {
                this.$nextTick(function () {
                    let checkednNames = list.map(item => item.name)
                    this.data.forEach(item => {
                        console.log(item.name, "--", checkednNames.includes(item.name))
                        item.isChecked = checkednNames.includes(item.name)
                    });
                })
            },
            // 获取代码生成路径
            finDGenerateGetPath() {
                this.crud.get(this.uri.generateGetPath, {tableName: this.search.tableName}).then((res) => {
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
                this.crud.post(this.uri.generatePreview, data).then((res) => {
                    for (var k in res.data.data) {
                        res.data.data[k] = res.data.data[k] + "?" + Date.now();
                    }
                    this.generateCodePreviews = res.data.data;
                    this.generateCodePreviewDialogVisible = true;
                })
            },
            // 生成后端代码（生成到项目）
            generateCodeJava() {
                this.$confirm(`生成服务端代码, 如果重新生成将覆盖原数据, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let data = {
                        tableComment: this.treeRowData.comment,
                        tableName: this.search.tableName,
                        dataSourceId: "",
                        data: JSON.stringify(this.data)
                    }
                    this.crud.post(this.uri.generateCode, data).then(() => {
                        this.$message.success("代码生成成功");
                    })
                })
            },
            // 生成vue代码（下载）
            generateCodeVueFun() {
                let data = {
                    tableComment: this.treeRowData.comment,
                    tableName: this.search.tableName,
                    dataSourceId: "",
                    data: JSON.stringify(this.data)
                };
                this.crud.download(this.uri.generateCodeVue, data);
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
