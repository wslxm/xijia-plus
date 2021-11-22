<template>
    <div>
        <avue-crud ref="crudxjAdminLog"
                   :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @refresh-change="onLoad"
                   @search-change="searchChange"
                   @row-click="handleRowClick">
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">查看</el-button>
            </template>
        </avue-crud>

        <el-dialog title="查看" v-dialogDrag v-if="updDialogVisible"  :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData" :organs="organs" :roles="roles"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>
    export default {
        components: {
            Upd: () => import('./xjAdminLogUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/xj/log/list",
                },
                dialogWidth: "80%",
                addDialogVisible: false,
                updDialogVisible: false,
                page: this.website.pageParams,
                search: {},
                data: [],
                rowData: {},
                option: {},
                stateDict: [
                    {
                        label: "成功",
                        value: 1
                    },
                    {
                        label: "失败",
                        value: 0
                    }
                ]
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            //this.option.menu = false
            this.option.index = false
            //this.option.viewBtn =true
            this.option.column = [
                {
                    label: '请求人',
                    prop: 'fullName',
                    search: true,
                    overHidden: true,
                },
                // {
                //     label: '请求人Id ',
                //     prop: 'userId',
                //     search: false,
                //     overHidden: true,
                // },
                // {
                //     label: '访问端',
                //     prop: 'type',
                //     search: true,
                //     overHidden: true,
                //     html: true,
                //     formatter: (val) => {
                //         if (val.type == 0) {
                //             return '<span >管理端</span>'
                //         } else if (val.type == 1) {
                //             return '<span >用户端</span>'
                //         }else{
                //             return '<span></span>'
                //         }
                //     },
                // },
                // {
                //     label: '请求来源',
                //     prop: 'referer',
                //     search: true,
                //     overHidden: true,
                // },
                // {
                //     label: '请求url',
                //     prop: 'url',
                //     search: false,
                //     overHidden: true,
                // },
                {
                    label: '请求类',
                    prop: 'classDesc',
                    search: true,
                    overHidden: true,
                },
                {
                    label: '请求方法',
                    prop: 'methodDesc',
                    search: true,
                    overHidden: true,
                },
                {
                    label: '请求uri',
                    prop: 'uri',
                    search: true,
                    overHidden: true,
                },
                // {
                //     label: '用户真实Ip',
                //     prop: 'ip',
                //     search: false,
                //     overHidden: true,
                // },
                // {
                //     label: '用户主机名',
                //     prop: 'host',
                //     search: false,
                //     overHidden: true,
                // },

                {
                    label: '请求方式',
                    prop: 'method',
                    search: false,
                    overHidden: true,
                },
                // {
                //     label: '服务器地址',
                //     prop: 'serverName',
                //     search: false,
                //     overHidden: true,
                // },
                // {
                //     label: '服务器端口',
                //     prop: 'port',
                //     search: false,
                //     overHidden: true,
                // },
                // {
                //     label: '请求包',
                //     prop: 'packageName',
                //     search: true,
                //     overHidden: true,
                // },
                // {
                //     label: '请求类',
                //     prop: 'className',
                //     search: true,
                //     overHidden: true,
                // },

                {
                    label: '请求时间',
                    prop: 'createTime',
                    overHidden: true,
                },
                {
                    label: '请求数据',
                    prop: 'requestData',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '响应数据',
                    prop: 'responseData',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '响应总耗时',
                    prop: 'executeTime',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '结果',
                    prop: 'state',
                    type: 'select',
                    search: true,
                    overHidden: true,
                    html: true,
                    dicData: this.stateDict,
                    formatter: (val) => {
                        if (val.state == 0) {
                            return '<span style="color: red">失败</span>'
                        } else if (val.state == 1) {
                            return '<span style="color: #2bc03f">成功</span>'
                        }
                    },
                },

                // {
                //     label: '业务总耗时',
                //     prop: 'businessTime',
                //     search: false,
                //     overHidden: true,
                // },

            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudxjAdminLog)
        },
        methods: {
            onLoad() {
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudxjAdminLog)
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
            }
        }
    }
</script>
