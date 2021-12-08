<template>
    <div>
        <avue-crud ref="crudxjAdminMsg"
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
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">查看</el-button>
               <!-- <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>-->
            </template>
        </avue-crud>
        <!-- 弹层 -->
        <el-dialog title="新增" v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="查看" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>
    export default {
        components: {
            Add: () => import('./xjAdminMsgAdd'),
            Upd: () => import('./xjAdminMsgUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/xj/msg/list",
                    info: "/api/admin/xj/msg",
                    userList: "/api/admin/user/list/keyData",
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
                    label: '接收人Id',
                    prop: 'userId',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '内容',
                    prop: 'content',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '通知终端',
                    prop: 'userType',
                    type: 'select',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Admin.MsgUserType),
                },
                {
                    label: '消息类型',
                    prop: 'msgType',
                    type: 'select',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Admin.MsgType),
                },
                {
                    label: '是否已读',
                    prop: 'isRead',
                    type: 'select',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.IsRead),
                },

            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudxjAdminMsg)
        },
        methods: {
            onLoad() {
                this.crud.list(this,true);
                this.crud.doLayout(this, this.$refs.crudxjAdminMsg)
            },
            searchChange(params,done) {
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
                 if (column.property == "isRead") {
                     // fontWeight: 'bold',fontSize: '20'
                     return row.isRead == 1 ? {color: 'green'} : {color: 'red'}
                 }
            }
        }
    }
</script>
