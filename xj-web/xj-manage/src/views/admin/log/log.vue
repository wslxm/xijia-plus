<template>
    <div>
        <el-card>
            <avue-crud ref="crudxjAdminLog"
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
                <template slot-scope="{row,index,type,size}" slot="menu">
                    <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">查看</el-button>
                </template>


                <template slot-scope="{row,index,type,size}" slot="createTimeSearch">
                    <div class="block">
                        <el-date-picker
                                v-model="newCreateTime"
                                format="yyyy-MM-dd HH:mm:ss"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                type="datetimerange"
                                align="right"
                                unlink-panels
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </div>
                </template>

            </avue-crud>

            <el-dialog title="查看" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog">
                <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
                <span slot="footer" class="dialog-footer"></span>
            </el-dialog>
        </el-card>
    </div>
</template>


<script>
    export default {
        components: {
            Upd: () => import('./logUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/sys/log/findPage",
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
                stateDict: [
                    {
                        label: "成功",
                        value: 1
                    },
                    {
                        label: "失败",
                        value: 0
                    }
                ],
                methodDict: [
                    {
                        label: "GET",
                        value: "GET"
                    },
                    {
                        label: "POST",
                        value: "POST"
                    },
                    {
                        label: "PUT",
                        value: "PUT"
                    },
                    {
                        label: "DELETE",
                        value: "DELETE"
                    }
                ],

                // 时间搜索
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                newCreateTime: '',
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            //this.option.menu = false
            this.option.index = false;
            //this.option.viewBtn =true
            this.option.column = [
                {
                    label: '请求人',
                    prop: 'fullName',
                    search: true,
                    overHidden: true,
                    searchSpan: 5,
                },
                // {
                //     label: '请求人Id ',
                //     prop: 'userId',
                //     search: false,
                //     overHidden: true,
                // },
                {
                    label: '请求端',
                    prop: 'type',
                    search: true,
                    overHidden: true,
                    type: 'select',
                    searchSpan: 5,
                    dicData: this.dict.get(this.website.Dict.Base.AuthorityType),

                    // html: true,
                    // formatter: (val) => {
                    //     if (val.type === 0) {
                    //         return '<span >管理端</span>'
                    //     } else if (val.type == 1) {
                    //         return '<span >用户端</span>'
                    //     }else{
                    //         return '<span></span>'
                    //     }
                    // },
                },
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
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '请求方法',
                    prop: 'methodDesc',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '请求uri',
                    prop: 'uri',
                    search: true,
                    searchSpan: 5,
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
                    type: 'select',
                    search: true,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.methodDict
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
                    searchSpan: 7,
                    search: true,
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
                    searchSpan: 4,
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
                // 时间查询处理
                if (this.newCreateTime != null && this.newCreateTime !== "") {
                    this.search.createTimeStart = this.newCreateTime[0];
                    this.search.createTimeEnd = this.newCreateTime[1];
                } else {
                    this.search.createTimeStart = null;
                    this.search.createTimeEnd = null;
                }

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
                this.rowData = JSON.parse(JSON.stringify(row));
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
