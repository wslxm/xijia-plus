<template>
    <div>
        <el-container>
            <el-aside style="padding-left: 10px;padding-top: 2.5px" width="260px">
                <!--<span>选择数据源</span>-->
                <el-select style="width: 240px" v-model="dataSourceId" :clearable="true" filterable placeholder="选择数据源, 默认当前服务" @change="datasourceChange()">
                    <el-option
                            v-for="item in datasourceDic"

                            :key="item.id"
                            :label="item.dbTitle"
                            :value="item.id">
                    </el-option>
                </el-select>
                <!-- 数据表 -->
                <div style="height: 700px;margin-top: 4%;">
                    <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
                </div>
            </el-aside>

            <el-main>
                <!--  当前数据源: <font color="#ff69b4"> {{datasource.dbName}} - {{datasource.dbTitle}}</font> -->
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
                        <el-button type="primary" size="small" plain @click="dbDatasource()">数据源管理</el-button>
                        <el-button type="primary" size="small" plain @click="finDGenerateGetPath()">查看生成路径</el-button>
                        <el-button type="primary" size="small" plain @click="findGeneratePreview()">生成预览代码 (在线查看)</el-button>
                        <el-button type="primary" size="small" plain @click="generateCodeJava()">生成后端代码</el-button>
                        <el-button type="primary" size="small" plain @click="generateCodeVueFun()">生成并下载 (vue)</el-button>
                        <el-button type="primary" size="small" plain @click="generateCodeJavaAndVueFun()">生成并下载 (all)</el-button>
                    </template>
                    <!-- 数据类型 -->
                    <template slot-scope="{scope,row,index,type,size}" slot="vueFieldType">
                        <!--<el-col :span="6">-->
                        <!--  <avue-select v-model="row.vueFieldType" placeholder="请选择内容" type="tree" :dic="vueFieldTypeDic"></avue-select>-->

                        <el-select v-model="row.vueFieldType" filterable placeholder="请选择" @change="vueFieldTypeChange(row)">
                            <el-option
                                    v-for="item in vueFieldTypeDic"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                        <!-- </el-col>-->
                    </template>

                    <!-- 字典
                          // 单选-(radio)                 4
                          // 多选-(checkbox)              5
                          // 下拉选择-(select-单选)        6
                          // 下拉选择 (select-单选+搜索)    7
                          // 下拉选择 (select-多选+搜索)    8
                          // 开关-(switch)                 9
                    -->
                    <template slot-scope="{scope,row,index,type,size}" slot="dictCode">
                        <el-cascader v-if="isVueFieldTypeDict(row)"
                                     v-model="row.dictCode"
                                     placeholder="试试搜索：性别"
                                     filterable
                                     clearable
                                     :show-all-levels="false"
                                     :options="dictCodeOption"
                                     @change="handleChange"
                                     :props="dictCodeProps"
                        ></el-cascader>
                    </template>

                    <!-- 是否是搜索参数 -->
                    <template slot-scope="{scope,row,index,type,size}" slot="isSearch">
                        <el-switch v-model="row.isSearch"
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

    import router from '@/router/router'
    export default {
        components: {
            Paths: () => import('./generatePaths'),
            CodePreview: () => import('./generateCodePreview.vue')
        },
        data() {
            return {
                uri: {
                    infoFieldList: "/api/admin/gc/dataBase/table/field",  // 根据字段查询表字段
                    infoTableList: "/api/admin/gc/dataBase/table/list",   // 所有表
                    generateGetPath: "/api/admin/gc/generate/getPath",    // 代码生成路径
                    generatePreview: "/api/admin/gc/generate/preview",    // 生成预览代码
                    generateCode: "/api/admin/gc/generate/generateCode",   // 生成代码
                    generateCodeVue: "/api/admin/gc/generate/generateCodeVue", // 只生成vue代码(直接下载)
                    generateCodeJavaAndVue: "/api/admin/gc/generate/generateCodeJavaAndVue", // 生成java + vue代码(直接下载)
                    diceFindList: "/api/admin/sys/dictionary/list?isBottomLayer=false&code=ENUMS", // 获取字典数据
                    datasourceInfoList: "/api/admin/gc/datasource/findPage?size=10000",   // 查询数据源
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

                // 数据源
                datasourceDic: [],
                dataSourceId: null,
                // 数据表
                treeRowData: {name: "t_basic", comment: "系统通用字段表"},
                treeData: [],
                treeOption: {
                    defaultExpandAll: false,
                    filter: true,
                    addBtn: false,
                    props: {
                        labelText: '数据表',
                        label: 'name',
                        value: 'name',
                        children: ''
                    }
                },
                // 字段上字典值
                dictCodeOption: [],
                dictCodeProps: {
                    expandTrigger: "hover",   // 点击进入二级还是鼠标移动进入 click / hover
                    //filterable : true,        // 是否可搜索
                    // "show-all-levels" : false, // 是否显示完整路径
                    value: "code",
                    label: "name",
                    children: "dictList",
                }
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.index = false;
            this.option.menu = false;
            this.option.rowKey = "id";
            this.option.maxHeight = 600;
            // 开启多选
            this.option.selection = true;
            this.option.reserveSelection = true;
            this.option.column = [
                {
                    label: '字段名',
                    prop: 'name',
                    align: 'left',
                    // width: 200,
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
                    label: '选择字典',
                    prop: 'dictCode',
                    align: 'left',
                    // width: 240,
                },
                {
                    label: '是否搜索 (eq/like)',
                    prop: 'isSearch',
                    align: 'left',
                },
                {
                    label: '是否允许空 (mysql)',
                    prop: 'isNull',
                    align: 'left',
                },
                {
                    label: '默认值 (mysql)',
                    prop: 'defaultVal',
                    align: 'left',
                },

            ]
        },
        created() {


            // vue 需要使用到字典的字段拉出字典选择
            this.crud.get(this.uri.diceFindList).then((res) => {
                // 直接取 dictList , 不要第一级
                this.dictCodeOption = res.data.data[0].dictList;
            });

            // 查询数据源
            this.crud.get(this.uri.datasourceInfoList).then((res) => {
                this.datasourceDic = res.data.data.records;
            });

            // 查询数据表
            this.findTableList();
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudField)
        },
        methods: {
            onLoad() {
                this.loading = true;
                this.crud.get(this.uri.infoFieldList, {
                    tableName: this.search.tableName,
                    dataSourceId: this.dataSourceId
                }).then((res) => {
                    res.data.data.forEach((item) => {
                        item.vueFieldType = 1;
                    });
                    this.data = res.data.data;
                    this.checkeds();
                    this.loading = false;
                })
            },


            /**
             * 查询数据表
             */
            findTableList() {
                // 查询数据表
                this.crud.get(this.uri.infoTableList, {dataSourceId: this.dataSourceId}).then((res) => {
                    this.treeData = res.data.data;
                });
            },

            searchChange(params, done) {
                this.page.currentPage = 1;
                this.onLoad();
                done();
            },
            handleRowClick(row) {
                this.rowData = JSON.parse(JSON.stringify(row));
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
                    let checkednNames = list.map(item => item.name);
                    this.data.forEach(item => {
                        console.log(item.name, "--", checkednNames.includes(item.name));
                        item.isChecked = checkednNames.includes(item.name)
                    });
                })
            },
            // 获取代码生成路径
            finDGenerateGetPath() {
                this.crud.get(this.uri.generateGetPath, {
                    tableName: this.search.tableName,
                    dataSourceId: this.dataSourceId,
                } ).then((res) => {
                    this.generatePaths = res.data.data;
                    this.findPageDialogVisible = true;
                })
            },
            // 生成预览代码
            findGeneratePreview() {
                let data = {
                    tableComment: this.treeRowData.comment,
                    tableName: this.search.tableName,
                    dataSourceId: this.dataSourceId,
                    data: JSON.stringify(this.data)
                };
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
                        dataSourceId: this.dataSourceId,
                        data: JSON.stringify(this.data)
                    };
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
                    dataSourceId: this.dataSourceId,
                    data: JSON.stringify(this.data)
                };
                this.crud.download(this.uri.generateCodeVue, data);
            },
            // 生成java + vue代码（下载）
            generateCodeJavaAndVueFun() {
                let data = {
                    tableComment: this.treeRowData.comment,
                    tableName: this.search.tableName,
                    dataSourceId: this.dataSourceId,
                    data: JSON.stringify(this.data)
                };
                this.crud.download(this.uri.generateCodeJavaAndVue, data);
            },
            /**
             * 打开数据源管理
             * @author wangsong
             * @date 2022/8/9 13:42
             * @return
             */
            dbDatasource() {
                router.push({path: "/gc/db"});
            },

            /**
             * 选择字段类型
             * @param row
             */
            vueFieldTypeChange(row) {

                if (this.isVueFieldTypeDict(row)) {
                    // 判断是否可用字典，可用且没有选择的话设置默认字典类型
                    // if (row.dictCode == null) {
                    //     row.dictCode = ["BASE", "DEFAULT"];
                    // }
                } else {
                    // 不可用清除字典数据
                    row.dictCode = null;
                }
            },

            /**
             * 字典选择监听
             */
            handleChange(value) {
                console.log(value);
            },


            /**
             * 判断字段是否需要使用字典
             */
            isVueFieldTypeDict(row) {
                if (row.vueFieldType >= 4 && row.vueFieldType <= 9) {
                    return true;
                }
                return false;
            },

            /**
             * 数据源选择
             */
            datasourceChange() {
                console.log("切换数据源:" + this.dataSourceId);
                this.findTableList();
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

