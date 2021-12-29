<template>
    <div>
        <avue-crud ref="crudgcTest"
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
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
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
            Add: () => import('./gcTestAdd'),
            Upd: () => import('./gcTestUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/test/gcTest/list",
                    info: "/api/admin/test/gcTest",
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
                    label: '名称 ',
                    prop: 'name',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '年龄 ',
                    prop: 'age',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '性别 ',
                    prop: 'sex',
                    type: 'select',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.Default),
                },
                {
                    label: '爱好 ',
                    prop: 'like',
                    type: 'select',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                    dataType: 'string',
                    dicData: this.dict.get(this.website.Dict.Base.Default),
                },
                {
                    label: '城市 ',
                    prop: 'city',
                    type: 'select',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.Default),
                },
                {
                    label: '禁用 ',
                    prop: 'disable',
                    type: 'select',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.Default),
                },
                {
                    label: '头像  ',
                    prop: 'headUrl',
                    search: false,
                    overHidden: true,
                    html: true,
                    formatter: (val) => {
                        if(val.headUrl == null || val.headUrl == ''){
                            return "";
                        }else{
                            let imgs = val.headUrl.split(",");
                            let html = ""; 
                            imgs.forEach(item => html += "<img src='" + item + "'  style='border-radius: 40px;height: 40px;width: 40px;margin-top: 10px'>")
                            return html;
                        }
                    }
                },
                {
                    label: '时间',
                    prop: 'time',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },
                {
                    label: '更多信息',
                    prop: 'text',
                    search: false,
                    searchSpan: 5,
                    overHidden: true,
                },

            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudgcTest)
        },
        methods: {
            onLoad() {
                this.crud.list(this,true);
                this.crud.doLayout(this, this.$refs.crudgcTest)
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
                 if (column.property == "disable") {
                     // fontWeight: 'bold',fontSize: '20'
                     return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                 }
            }
        }
    }
</script>
