<template>
    <div>
        <!-- 搜索 -->
        <el-card class="el-card__body">
            <el-col :span="24">
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

        <el-card class="box-card" style="width: 100%;margin-left: 0%">
            <el-row>
                <!-- 左边媒体分类树结构数据 -->
                <el-col :span="5">
                    <el-card class="box-card" style="width: 98%;">
                        <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
                    </el-card>
                </el-col>
                <el-col :span="19">
                    <avue-crud ref="crudAuth"
                               :data="data"
                               :option="option"
                               :page.sync="page"
                               :search.sync="search"
                               :table-loading="loading"
                               :cell-style="cellStyle"
                               @refresh-change="onLoad"
                               @search-change="searchChange">
                        <!-- @on-load="onLoad"
                         @refresh-change="onLoad"-->
                        <!-- 启用/禁用插槽(默认提供,按需使用) -->
                        <template slot-scope="{scope,row,index,type,size}" slot="disable">
                            <el-switch v-if="row.method && row.url.indexOf('/api/admin/sys/authority') == -1"
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
                </el-col>
            </el-row>
        </el-card>


    </div>
</template>


<script>
    export default {
        components: {
            // Add: () => import('./adminAuthorityAdd'),
            // Upd: () => import('./adminAuthorityUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/sys/authority/list",
                    info: "/api/admin/sys/authority",
                },
                treeOption: JSON.parse(JSON.stringify(this.website.treeOption)),
                treeData: [],
                rowTreeData: [],              // 当前选中左侧菜单行数据
                loading: true,
                dialogWidth: "60%",
                addDialogVisible: false,
                updDialogVisible: false,
                page: this.website.pageParams,
                search: {type: 0}, // 默认看通用接口
                data: [],
                rowData: {},
                option: {},
                stateDict: this.dict.get(this.website.Dict.Base.AuthorityState),
                typeDict: this.dict.get(this.website.Dict.Base.AuthorityType),
            }
        },
        mounted() {

            // 1、左侧菜单配置
            this.treeOption.props.label = "desc";
            this.treeOption.props.children = "authoritys";
            // 查询左侧菜单数据
            this.findLeftTree();


            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.menu = false;
            this.option.defaultExpandAll = true;
            this.option.index = false;
            this.option.rowKey = "id";
            //this.option. = "id"
            this.option.height = 600;
            this.option.treeProps = {
                children: 'authoritys'
            };
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
                // {
                //     label: '终端',
                //     prop: 'type',
                //     type: "select",
                //     search: false,
                //     //searchValue: this.search.type,
                //     overHidden: true,
                //     dicData: this.dict.get(this.website.Dict.Base.AuthorityType),
                // },
                {
                    label: '禁用',
                    prop: 'disable',
                    search: false,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.Disable),
                },
                {
                    label: '是否验签',
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
            this.crud.doLayout(this, this.$refs.crudAuth)
        },
        methods: {
            onLoad() {
                this.search.pid = this.rowTreeData.id;
                this.search.isTree = true;
                this.crud.list(this, false);
                this.crud.doLayout(this, this.$refs.crudAuth)
            },
            searchChange(params, done) {
                this.page.currentPage = 1;
                this.onLoad();
                done();
            },
            findLeftTree() {
                this.crud.get(this.uri.infoList, {
                    type: this.search.type,
                    isTree: true,
                    asc: "desc",
                }).then((res) => {
                    this.treeData = res.data.data;
                    // 默认选中第一条
                    if (this.treeData != null && this.treeData.length > 0) {
                        this.rowTreeData = this.treeData[0];
                        this.onLoad();
                    }
                });
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
            cellStyle({row, column}) {
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            },
            // 切换端
            typeDictHandle() {
                this.findLeftTree();
                // this.onLoad()
            },
            /**
             * 点击左菜单重置右菜单数据
             * @param data
             */
            nodeClick(data) {
                //this.$message.success(JSON.stringify(data))
                this.rowTreeData = data;
                this.onLoad();
            }
        },
    }
</script>
