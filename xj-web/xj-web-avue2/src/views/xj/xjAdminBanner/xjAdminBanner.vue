<template>
    <div>
        <avue-crud :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @refresh-change="onLoad"
                   @search-change="searchChange"
                   @row-click="handleRowClick">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
        </avue-crud>
        <el-dialog title="新增" :visible.sync="addDialogVisible" :width="dialogWidth" @close="closeDialog" :destroy-on-close="true">
            <Add :closeDialog="closeDialog" :uri="uri"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="编辑" :visible.sync="updDialogVisible" :width="dialogWidth" @close="closeDialog" :destroy-on-close="true">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>
    import {get,post,put,del,delRow,list,update} from '@/api/crud';
    import {getDict} from '@/api/dict';
    import website from '@/config/website';

    export default {
        components: {
            Add: () => import('./xjAdminBannerAdd'),
            Upd: () => import('./xjAdminBannerUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/xj/banner/list",
                    info: "/api/admin/xj/banner",
                },
                dialogWidth: "60%",
                addDialogVisible: false,
                updDialogVisible: false,
                page: website.pageParams,
                search: {},
                data: [],
                rowData: {},
                option: {},
            }
        },
        mounted() {
            this.option = website.optionConfig
            this.option.column = [
                 {
                    label: '位置',
                    prop: 'position',
                }, 
                {
                    label: 'banner标题',
                    prop: 'name',
                }, 
                {
                    label: 'banner描叙',
                    prop: 'desc',
                }, 
                {
                    label: 'banner图片',
                    prop: 'imgUrl',
                }, 
                {
                    label: 'banner排序',
                    prop: 'sort',
                }, 
                {
                    label: 'banner禁用',
                    prop: 'disable',
                }, 
                {
                    label: '是否跳转',
                    prop: 'isSkip',
                }, 
                {
                    label: '跳转地址url',
                    prop: 'skipUrl',
                }, 

            ]
        },
        created() {
        },
        methods: {
            onLoad() {
                list(this);
            },
            searchChange(params, done) {
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
                delRow(this, this.uri.info, row.id, index);
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
