<template>
    <div>

        <div style="background-color: #FFFFFF; ">
            <el-tabs style="padding-left: 2%;" v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="全部" name="-1"></el-tab-pane>
                <el-tab-pane v-for="(item,index) in configTypes" :label="item.label" :name="item.value+''"></el-tab-pane>
            </el-tabs>
        </div>


        <avue-crud ref="crudxjAdminConfig"
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
            <template slot-scope="{scope,row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
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
    </div>
</template>


<script>


    export default {
        components: {
            Add: () => import('./xjAdminConfigAdd'),
            Upd: () => import('./xjAdminConfigUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/xj/config/findPage",
                    info: "/api/admin/xj/config",
                },
                loading: true,
                dialogWidth: "80%",
                addDialogVisible: false,
                updDialogVisible: false,
                page: this.website.pageParams,
                search: {},
                data: [],
                rowData: {},
                option: {},
                activeName: 0,
                configTypes: []
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.column = [
                {
                    label: '配置code',
                    prop: 'code',
                    search: true,
                    overHidden: true,
                },
                {
                    label: '配置名称',
                    prop: 'name',
                    search: true,
                    overHidden: true,
                },
                {
                    label: '配置内容',
                    prop: 'content',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '类型',
                    prop: 'type',
                    search: false,
                    type: "select",
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.ConfigType),
                },
                {
                    label: '描述',
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

            ]
        },
        created() {
            this.configTypes = this.dict.get(this.website.Dict.Base.ConfigType)
            console.log("===" + JSON.stringify(this.configTypes))
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudxjAdminConfig)
        },
        methods: {
            onLoad() {
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudxjAdminConfig)
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
                        // 编辑
                        this.updDialogVisible = true;
                        break;
                    default:
                        this.$message.error('操作类型错误');
                        break;
                }
            },
            rowDel(row, index) {
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
            },
            cellStyle({row, column}) {
                if (column.property == "type") {
                    // fontWeight: 'bold',fontSize: '20'
                    if (row.type == 0) {
                        return {color: 'green'}
                    } else if (row.type == 1) {
                        return {color: '#20B2AA'}
                    } else if (row.type == 2) {
                        return {color: '#9932CC'}
                    }
                }
            },
            handleClick(tab, event) {
                this.search.type = tab.name === "-1" ? "" : tab.name;
                this.onLoad()
            }
        }
    }
</script>

