<template>
    <div>
        <el-card>
            <avue-crud ref="crudxjAdminBlacklist"
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
        </el-card>
    </div>
</template>


<script>
    export default {
        components: {
            Add: () => import('./blacklistAdd'),
            Upd: () => import('./blacklistUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/sys/blacklist/findPage",
                    info: "/api/admin/sys/blacklist",
                },
                loading: true,
                dialogWidth: "60%",
                addDialogVisible: false,
                updDialogVisible: false,
                page: this.website.pageParams,
                search: {},
                data: [],
                rowData: {},
                option: {},
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.column = [
                {
                    label: '配置类型',
                    prop: 'type',
                    type: "switch",
                    search: true,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.BlacklistType),
                },
                {
                    label: 'ip',
                    prop: 'ip',
                    search: true,
                    overHidden: true,
                },
                {
                    label: '备注',
                    prop: 'desc',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '禁用/启用',
                    prop: 'disable',
                    type: "switch",
                    search: true,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.Disable),
                },

            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudxjAdminBlacklist)
        },
        methods: {
            onLoad() {
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudxjAdminBlacklist)
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
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            }
        }
    }
</script>
