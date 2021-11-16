<template>
    <div>
        <!-- 搜索 -->
        <el-card class="el-card__body">
            <el-col :span="5">
                <label>选择接口端: </label>
                <el-select v-model="search.type" clearable size="small" style="width: 220px" placeholder="" @change="typeDictHandle()">
                    <el-option
                            v-for="item in typeDict"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-col>
        </el-card>

        <avue-crud ref="crudAuth"
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
                <el-switch v-if="row.method && row.url.indexOf('/api/admin/authority') == -1"
                           v-model="row.disable" @change="updDisable(row)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <!-- 是否验签 -->
            <template slot-scope="{scope,row,index,type,size}" slot="isSign">
                <el-switch v-if="row.method" v-model="row.isSign" @change="updIsSign(row)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=true :inactive-value=false
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <!-- 权限状态 -->
            <template slot-scope="{scope,row,index,type,size}" slot="state">
                <el-select v-if="row.method" v-model="row.state" @change="updState(row)" placeholder="请选择">
                    <el-option
                            v-for="item in stateDict"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </template>
        </avue-crud>
    </div>
</template>


<script>
    export default {
        components: {
            Add: () => import('./adminAuthorityAdd'),
            Upd: () => import('./adminAuthorityUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/authority/list?isTree=true",
                    info: "/api/admin/authority",
                },
                dialogWidth: "60%",
                addDialogVisible: false,
                updDialogVisible: false,
                page: this.website.pageParams,
                search: {type: 0}, //默认看通用接口
                data: [],
                rowData: {},
                option: {},
                stateDict: this.dict.get(this.website.Dict.Base.AuthorityState),
                typeDict: this.dict.get(this.website.Dict.Base.AuthorityType),
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.menu = false
            this.option.defaultExpandAll = true
            this.option.index = false
            this.option.rowKey = "id"
            //this.option. = "id"
            this.option.height = 800
            this.option.treeProps = {
                children: 'authoritys'
            }
            this.option.column = [
                {
                    label: '权限备注信息',
                    prop: 'desc',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '请求方式',
                    prop: 'method',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '权限url',
                    prop: 'url',
                    search: false,
                    overHidden: true,
                },
                {
                    label: '终端',
                    prop: 'type',
                    type: "select",
                    search: false,
                    //searchValue: this.search.type,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.AuthorityType),
                },
                {
                    label: '禁用',
                    prop: 'disable',
                    search: false,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.Disable),
                },
                {
                    label: '是否需要验签',
                    prop: 'isSign',
                    search: false,
                    overHidden: true,
                    //dicData: this.dict.get(this.website.Dict.Base.Default),
                },
                {
                    label: '授权状态',
                    prop: 'state',
                    search: false,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.AuthorityState),
                },
            ]
        },
        created() {
        },
        activated: function () {
            console.log(this.$refs.crudAuth)
            this.crud.doLayout(this, this.$refs.crudAuth)
        },
        methods: {
            onLoad() {
                this.crud.list(this, false);
                console.log(this.$refs.crudAuth)
                this.crud.doLayout(this, this.$refs.crudAuth)
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
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
            },
            // 是否验签
            updIsSign(row) {
                this.crud.put(this.uri.info + "/" + row.id, {isSign: row.isSign});
            },
            // 修改认证类型
            updState(row) {
                this.crud.put(this.uri.info + "/" + row.id, {state: row.state});
            },
            handleRowClick(row) {
                this.rowData = row;
            },

            cellStyle({row, column}) {
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            },
            // 切换端
            typeDictHandle() {
                this.onLoad()
            },
        },
    }
</script>
