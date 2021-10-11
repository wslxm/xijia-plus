<template>
    <div>
        <avue-crud :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @search-change="searchChange"
                   @row-click="handleRowClick"
                   @cell-click="handleCellClick"
        >
            <!-- @row-del="rowDel"-->
            <!-- 自定义新增按钮 -->
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="dialogVisible = true"> 新增</el-button>
            </template>
            <!-- 自定义 编辑/删除按钮 -->
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button v-if="row.disable == 1" :size="size" :type="type" @click="updDisable(row,index,0)">启用</el-button>
                <el-button v-if="row.disable == 0" :size="size" :type="type" @click="updDisable(row,index,1)">禁用</el-button>
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="dialogUpdVisible = true">编辑</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>

            </template>
        </avue-crud>

        <!-- 新增弹窗配置 RoleAdd 为弹层页面 -->
        <el-dialog title="新增角色" :visible.sync="dialogVisible" width="60%">
            <RoleAdd :closeDialogVisible="closeDialogVisible" :addRoleUri="uri.role"></RoleAdd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>

        <!-- 新增弹窗配置 RoleAdd 为弹层页面 -->
        <el-dialog title="编辑角色" :visible.sync="dialogUpdVisible" width="60%">
            <RoleUpd :closeDialogVisible="closeDialogVisible" :updRoleUri="uri.role" :rowData="rowData"></RoleUpd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
    </div>
</template>


<script>
    import {del, get, put} from '@/api/crud';
    import {getDict} from '@/api/dict';
    import {pageParams} from '@/config/env';

    export default {
        components: {
            RoleAdd: () => import('./roleAdd'),
            RoleUpd: () => import('./roleUpd')
        },
        data() {
            return {
                uri: {
                    roleList: "/api/admin/role/list",
                    role: "/api/admin/role"
                },
                dialogVisible: false,      // 添加弹层开关状态
                dialogUpdVisible: false,   // 编辑弹层开关状态
                page: pageParams,         // 分页参数
                search: {},               // 查询参数
                data: [],                 // 列表数据
                rowData: {},              // 当前选中行数据
                option: {
                    card: true,           // 是否开启卡片模式
                    searchMenuSpan: 4,    // 搜索按钮于搜索框的间隙
                    border: true,          // 是否开启列分割线
                    align: 'center',       // 内容是否居中
                    // menuAlign:'center',   // 菜单是否居中(开启了行编辑无效)
                    index: true,          // 开启序号
                    indexLabel: '序号',     // 序号命令
                    // align: 'center',
                    // menuAlign: 'center',
                    // searchIcon: true,
                    // searchShowBtn: false,
                    // refreshBtn: false,    // 是否需要刷新按钮
                    // columnBtn: false,
                    // updateBtn: false,
                    // saveBtn: false,        // 新增页是否需要添加按钮
                    // cancelBtn: false,      // 新增页是否需要取消按钮
                    // addRowBtn: false,      // 是否开启新增按钮
                    // cellBtn: false,        // 是否开启行编辑功能
                    addBtn: false,            // 是否需要新增数据按钮
                    menu: true,               // 是否开启操作栏
                    editBtn: false,           // 是否需要编辑按钮(开启操作栏生效)
                    delBtn: false,            // 是否需要删除按钮(开启操作栏生效，行编辑开启时不展示删除按钮)
                    column: [
                        {
                            label: '角色名称',
                            prop: 'name',
                            search: true,
                            searchSpan: 5,
                            searchRules: [{
                                required: false,
                                // trigger: "blur"
                            }],
                        },
                        {
                            label: 'code',
                            prop: 'code'
                        },
                        {
                            label: '描叙',
                            prop: 'desc'
                        },
                        {
                            label: '终端',
                            prop: 'terminal',
                            dicData: getDict("TERMINAL"),
                            search: true,
                            searchRules: [{
                                required: false,
                                // trigger: "blur"
                            }],
                        },
                        {
                            label: '启用/禁用',
                            prop: 'disable',
                            type: "switch",
                            //cell: true,
                            dicData: getDict("DISABLE"),
                            //html:true,
                            // formatter:(val)=>{
                            //     console.log(val)
                            //     return '<span style="color:red">'+val.disable+'</span>'
                            // }
                            //addDisplay: true,    // 添加页是否展示该字段
                            //addDisabled: false   // 添加页该字段是否禁止输入(addDisplay=true时生效)
                        }
                    ]
                },
            }
        },
        methods: {

            /**
             * // 直接触发
             * 进入页面首次自动加载 -->  查询列表数据
             * 点击分页           -->  查询列表数据
             * 切换分页           -->  查询列表数据
             * // 被调用
             * 点击搜索按钮           -->  重置页数为1 后调用当前方法查询列表数据
             * 添加/编辑页点击保存/提交 -->  保存成功关闭弹层 后调用当前方法查询列表数据
             * 删除后                -->  保存成功关闭弹层 后调用当前方法查询列表数据
             * 禁用/启用后            -->  保存成功关闭弹层 后调用当前方法查询列表数据
             * @author wangsong
             */
            onLoad(page) {
                // 查询参数
                let params = this.search;
                // 分页参数
                params.current = this.page.currentPage;
                params.size = this.page.pageSize;
                // 发起请求
                get(this.uri.roleList, params).then(res => {
                    this.data = res.data.data.records;
                    this.page.total = res.data.data.total;
                })
            },
            /**
             * 点击搜索按钮 --> 重置页数为1，在进行参数查询
             * @author wangsong
             */
            searchChange(params, done) {
                done();
                this.page.currentPage = 1;
                this.onLoad();

            },
            /**
             * 添加/编辑页点击保存/提交 -->  保存数据成功后关闭弹层 和 刷新列表
             * @author wangsong
             */
            closeDialogVisible() {
                this.dialogVisible = false;
                this.dialogUpdVisible = false;
                //this.page.currentPage = 1;  //不重置页数
                this.onLoad();
            },
            /**
             * 点击删除 --> 删除行
             * @param form
             * @param index
             */
            rowDel(row, index) {
                this.$confirm(`此操作将永久删除序号【${index + 1}】的数据, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    del(this.uri.role + "/" + row.id).then(() => {
                        this.onLoad();
                    })
                })
            },
            /**
             * 点击删除 --> 删除行
             * @param form
             * @param index
             */
            updDisable(row, index, disable) {
                put(this.uri.role + "/" + row.id, {disable: disable}).then((res) => {
                    this.onLoad();
                })
            },
            /**
             * 点击行 -->  任意地方保存当前点击行数据, 用于点击任意操作按钮直接获取当前行数据
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/10/10 0010 23:15
             * @version 1.0.0
             */
            handleRowClick(row, event, column) {
                this.rowData = row;
            },
            /**
             * 点击单元格 -->  对点击不同的单元格做不同的操作
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/10/10 0010 18:19
             * @version 1.0.0
             */
            handleCellClick(row, column, cell, event) {
                if (column.property == "disable") {

                }
            },
            /**
             * 自动配置 --> 单元格样式数字，对指定列设置字体颜色,大小，粗细等
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/10/11 0011 23:53
             * @version 1.0.0
             */
            cellStyle({row, column, rowIndex, columnIndex}) {
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            }
        }
    }
</script>