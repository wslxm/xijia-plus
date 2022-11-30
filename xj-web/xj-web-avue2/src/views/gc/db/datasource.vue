<template>
    <div>
        <el-card>
            <avue-crud ref="crudxjAdminDatasource"
                       :data="data"
                       :option="option"
                       :page.sync="page"
                       :search.sync="search"
                       :table-loading="loading"
                       :cell-style="cellStyle"
                       @on-load="onLoad"
                       @refresh-change="onLoad"
                       @search-change="searchChange">
                <!-- 启用/禁用插槽(默认提供,按需使用) -->
                <template slot-scope="{row,index,type,size}" slot="disable">
                    <el-switch v-model="row.disable" @change="updDisable(row)"
                               active-color="#13ce66" inactive-color="#ff4949"
                               :active-value=0 :inactive-value=1
                               active-text="" inactive-text="">
                    </el-switch>
                </template>
                <template slot-scope="{}" slot="menuLeft">
                    <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
                </template>
                <template slot-scope="{row,index,type,size}" slot="menu">
                    <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
                    <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,2)">重置密码</el-button>
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

            <el-dialog title="重置密码" :visible.sync="updPwdDialogVisible" width="30%">
                <el-form ref="form" label-width="80px">
                    <el-form-item label="输入密码">
                        <el-input v-model="rowPassword.info"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
              <el-button @click="updPwdDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="updPwd()">确 定</el-button>
            </span>
            </el-dialog>
        </el-card>
    </div>
</template>


<script>
    export default {
        components: {
            Add: () => import('./datasourceAdd'),
            Upd: () => import('./datasourceUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/gc/datasource/findPage",
                    info: "/api/admin/gc/datasource",
                    dbTest: "/api/admin/gc/datasource/dataSourceTest",
                    updPwd: "/api/admin/gc/datasource/{id}/updPwd",
                },
                loading: true,
                dialogWidth: "60%",
                addDialogVisible: false,
                updDialogVisible: false,
                updPwdDialogVisible: false,
                page: this.website.pageParams,
                search: {},
                data: [],
                rowData: {},
                option: {},
                // 重置密码数据保存
                rowPassword: {
                    info: "123456",
                    default: "123456",
                },
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.column = [
                {
                    label: 'db -标题',
                    prop: 'dbTitle',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: 'db 库名',
                    prop: 'dbName',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                // {
                //     label: 'db 连接地址',
                //     prop: 'dbUrl',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                // {
                //     label: 'db 账号',
                //     prop: 'dbUsername',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                // {
                //     label: 'db 密码',
                //     prop: 'dbPassword',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                {
                    label: '作者',
                    prop: 'author',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '邮箱',
                    prop: 'email',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '描述信息',
                    prop: 'describe',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '项目名/路径',
                    prop: 'projectName',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '包路径',
                    prop: 'packPath',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '根模块',
                    prop: 'rootModule',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '子模块',
                    prop: 'modulesName',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: 'db 表前缀 ',
                    prop: 'dbTablePrefix',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                // {
                //     label: 'db 字段前缀 ',
                //     prop: 'dbFieldPrefix',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                // {
                //     label: '是否swagger ',
                //     prop: 'entitySwagger',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                // {
                //     label: '生成路径',
                //     prop: 'fatherPath',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                // {
                //     label: '排除vue字段类型 ',
                //     prop: 'vueFieldTypes',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },
                {
                    label: '数据库通用字段',
                    prop: 'baseFields',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                // {
                //     label: '数据库关键字',
                //     prop: 'keywordArray',
                //     search: false,
                //     searchSpan: 5,
                //     overHidden: true,
                // },

            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudxjAdminDatasource)
        },
        methods: {
            onLoad() {
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudxjAdminDatasource)
            },
            searchChange(params, done) {
                console.debug(params)
                this.page.currentPage = 1;
                this.onLoad();
                done();
            },
            closeDialog(isRefresh) {
                this.addDialogVisible = false;
                this.updDialogVisible = false;
                this.updPwdDialogVisible = false;
                this.rowData = {};
                if (isRefresh != null && isRefresh) {
                    this.onLoad();
                }
            },
            updRow(row, type) {
                this.rowData = row;
                switch (type) {
                    case 1:
                        this.updDialogVisible = true;
                        break;
                    case 2:
                        this.updPwdDialogVisible = true;
                        break;
                    default:
                        this.$message.error('操作类型错误');
                        break;
                }
            },
            // 修改密码
            updPwd() {
                this.crud.put(this.uri.updPwd.replace("{id}", this.rowData.id), null, {password: this.rowPassword.info});
                this.updPwdDialogVisible = false;
                this.rowPassword.info = this.rowPassword.default
            },
            rowDel(row, index) {
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
            },
            cellStyle({row, column}) {
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            }
        }
    }
</script>
