<template>
    <div>
        <el-card>
            <avue-crud ref="crudxjAdminBanner"
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
                <template slot-scope="{row,index,type,size}" slot="disable">
                    <el-switch v-model="row.disable" @change="updDisable(row)"
                               active-color="#13ce66" inactive-color="#ff4949"
                               :active-value=0 :inactive-value=1
                               active-text="" inactive-text="">
                    </el-switch>
                </template>

                <!-- 列表上进行编辑sort -->
                <template slot-scope="{row,index,type,size}" slot="sort">
                    <el-input v-model="row.sort" @blur="rowSortBlur(row)" placeholder=""></el-input>
                </template>

                <template slot-scope="" slot="menuLeft">
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
            Add: () => import('./bannerAdd'),
            Upd: () => import('./bannerUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/sys/banner/findPage",
                    info: "/api/admin/sys/banner",
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
                    label: '位置',
                    prop: 'position',
                    type: "switch",
                    search: true,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Admin.BannerPosition),
                },
                {
                    label: '标题',
                    prop: 'name',
                    search: true,
                    searchLabelWidth: 90,
                    overHidden: true,
                },
                {
                    label: '描叙',
                    prop: 'desc',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '图片 ',
                    prop: 'imgUrl',
                    search: false,
                    overHidden: true,
                    html: true,
                    formatter: (val) => {
                        if (val.imgUrl == null || val.imgUrl == '') {
                            return "";
                        } else {
                            let imgs = val.imgUrl.split(",");
                            let html = "";
                            imgs.forEach(item => html += "<img src='" + item + "'  style='height: 40px;width: 50px;margin-top: 10px'>")
                            return html;
                        }
                    }
                },
                {
                    label: '是否跳转',
                    prop: 'isSkip',
                    search: false,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.BannerIsSkip),
                },
                {
                    label: '跳转地址url',
                    prop: 'skipUrl',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '排序',
                    prop: 'sort',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '禁用',
                    prop: 'disable',
                    search: false,
                    overHidden: true,
                },
            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudxjAdminBanner)
        },
        methods: {
            onLoad() {
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudxjAdminBanner)
            },
            searchChange(params, done) {
                console.debug(params);
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
            // 排序
            rowSortBlur(row) {
                if (this.rowData.sort != row.sort) {
                    this.crud.put(this.uri.info + "/" + row.id, {sort: row.sort});
                }
            },
            handleRowClick(row) {
                this.rowData = JSON.parse(JSON.stringify(row));
                //this.rowData = row;
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
