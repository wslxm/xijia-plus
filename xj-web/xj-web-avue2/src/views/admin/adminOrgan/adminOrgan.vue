<template>
    <div>
        <avue-crud ref="crudadminOrgan"
                   :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
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
            <!-- 添加子类别 type=2 -->
            <template slot-scope="{row,index,type,size}" slot="addOrgan">
                <el-button type="primary" plain icon="el-icon-plus" v-show="row.root < 3" size="mini" @click="addDialogVisible = true">子公司/部门
                </el-button>
            </template>
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增公司</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
        </avue-crud>
        <!-- 弹层 -->
        <el-dialog title="新增" v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Add>
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
            Add: () => import('./adminOrganAdd'),
            Upd: () => import('./adminOrganUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/organ/list?isTree=true",
                    info: "/api/admin/organ",
                },
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
            //this.option.menu = false
            this.option.defaultExpandAll = true
            this.option.index = false
            this.option.rowKey = "id"
            //this.option. = "id"
            this.option.height = 800
            this.option.treeProps = {
                children: 'organs'
            }
            this.option.column = [
                {
                    label: '部门/公司名称',
                    prop: 'name',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '部门编码 ',
                    prop: 'code',
                    search: false,
                    overHidden: true,
                },

                {
                    label: '部门/公司描叙',
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
                {
                    label: '禁用',
                    prop: 'disable',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '级别',
                    prop: 'root',
                    search: false,
                },
                {
                    label: '添加子类别',
                    prop: 'addOrgan',
                },
                // {
                //     label: '级别',
                //     prop: 'root',
                //     search: false,
                //     overHidden: true,
                //     dicData: this.dict.get(this.website.Dict.Base.Default),
                // },
            ]
        },
        created() {
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudadminOrgan)
        },
        methods: {
            onLoad() {
                this.crud.list(this, false)
                this.crud.doLayout(this, this.$refs.crudadminOrgan)
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
