<template>
    <div>
        <avue-crud ref="crudUser"
                   :data="data"
                   :option="option"
                   :page.sync="page"
                   :search.sync="search"
                   :table-loading="loading"
                   :cell-style="cellStyle"
                   @on-load="onLoad"
                   @refresh-change="onLoad"
                   @search-change="searchChange">
            <template slot-scope="{row}" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <template slot-scope="{row,index,type,size}" slot="head">
                <el-avatar :src="row.head"></el-avatar>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,2)">重置密码</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
        </avue-crud>
        <el-dialog title="新增"
                   v-dialogDrag
                   v-if="addDialogVisible"
                   :visible.sync="addDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>

        <el-dialog title="编辑"
                   v-dialogDrag
                   v-if="updDialogVisible"
                   :visible.sync="updDialogVisible"
                   :width="dialogWidth"
                   top="6vh"
                   @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>

        <el-dialog title="重置密码" :visible.sync="updPwdDialogVisible" width="30%">
            <el-form ref="form" label-width="80px">
                <el-form-item label="输入密码">
                    <el-input v-model="rowPassword.info"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="updPwdDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="updPwd()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>


<script>

    export default {
        components: {
            Add: () => import('./userAdd'),
            Upd: () => import('./userUpd')
        },
        data() {
            return {
                uri: {
                    infoList: "/api/admin/user/list",
                    info: "/api/admin/user",
                    organInfo: "/api/admin/organ/list",
                    roleInfo: "/api/admin/role/list",
                    resetPassword: "/api/admin/user/{id}/resetPassword"
                },
                loading: true,
                dialogWidth: "60%",
                addDialogVisible: false,        // 添加弹层开关状态
                updDialogVisible: false,        // 编辑弹层开关状态
                updPwdDialogVisible: false,     // 重置密码弹层开关状态
                page: this.website.pageParams,  // 分页参数
                data: [],                       // 列表数据
                rowData: {},                    // 当前选中行数据
                option: {},
                // 搜索参数
                search: {
                    terminal: this.website.Terminal
                },
                // 重置密码数据保存
                rowPassword: {
                    info: "123456",
                    default: "123456",
                },
            }
        },
        activated: function () {
            this.crud.doLayout(this, this.$refs.crudUser)
        },
        mounted() {
            // 基础配置
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            // 字段配置
            this.option.column = [
                {
                    label: '头像',
                    prop: 'head',
                },
                {
                    label: '账号',
                    prop: 'username',
                    search: true,
                    searchSpan: 5,
                },
                {
                    label: '手机号',
                    prop: 'phone',
                },
                {
                    label: '姓名',
                    prop: 'fullName',
                    search: true,
                    searchSpan: 5,
                },
                // {
                //     label: '年龄',
                //     prop: 'age',
                // },
                // {
                //     label: '性别',
                //     prop: 'gender',
                //     dicData: this.dict.get(this.website.Dict.Base.Gender),
                // },
                {
                    label: '注册时间',
                    prop: 'regTime',
                },
                {
                    label: '终端',
                    prop: 'terminal',
                    type: "select",
                    hide: true,
                    search: this.website.isTerminalSearch,
                    searchSpan: 5,
                    searchValue: this.search.terminal,
                    searchOrder: 1,
                    dicData: this.dict.get(this.website.Dict.Admin.Terminal),
                },
                {
                    label: '职位',
                    prop: 'position',
                    dicData: this.dict.get(this.website.Dict.Admin.Position),
                },
                {
                    label: '禁用/启用',
                    prop: 'disable',
                    type: "switch",
                    dicData: this.dict.get(this.website.Dict.Base.Disable),
                }
            ]
        },
        watch: {
            // 监听路由参数变化, 让其支持消息点击跳转，并能携带动态参数进行查询
            // 携带将与搜索框搜索参数同步
            $route() {
                // 设置url 参数到搜索条件中
                this.setSearchByUrlParams();
                // 调用查询
                this.onLoad();
            },
        },
        created() {
            // 设置url 参数到搜索条件中
            this.setSearchByUrlParams();
        },
        methods: {

            /**
             * 设置url 参数到搜索条件中
             */
            setSearchByUrlParams() {
                // 添加 url 中的参数为查询条件
                let params = new URLSearchParams(window.location.href.split('?')[1]);
                // 清除之前的搜索条件
                for (let searchKey in this.search) {
                    delete this.search[searchKey];
                }
                // 并同步搜索参数
                params.forEach((value, key) => {
                    this.search[key] = value;
                });
            },

            /**
             * 直接触发：  首次自动加载 / 点击分页 / 切换分页 / 跳转也 / 点击刷新
             * 被调用触发：搜索后 /  添加/编辑保存后 / 删除后
             * @author wangsong
             */
            onLoad() {
                // 是否只查询自己权限及以下的数据
                this.search.isOwnData = true;
                // 查询
                this.crud.list(this, true);
                this.crud.doLayout(this, this.$refs.crudUser);
            },
            // 搜索,并重置页数为1
            searchChange(params, done) {
                this.page.currentPage = 1;
                this.onLoad();
                done();
            },
            // 添加/编辑保存数据后 关闭弹层+ true-刷新列表 false-不刷新
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
                        // 编辑弹层
                        this.updDialogVisible = true;
                        break;
                    case 2:
                        // 修改密码弹层
                        this.updPwdDialogVisible = true;
                        break;
                    default:
                        this.$message.error('操作类型错误');
                        break;
                }
            },
            // 行删除
            rowDel(row, index) {
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
            },
            // 修改密码
            updPwd() {
                this.crud.put(this.uri.resetPassword.replace("{id}", this.rowData.id), null, {password: this.rowPassword.info});
                this.updPwdDialogVisible = false;
                this.rowPassword.info = this.rowPassword.default
            },
            // 自动配置,单元格样式数字，对指定列设置字体颜色,大小，粗细等
            cellStyle({row, column}) {
                if (column.property == "disable") {
                    // fontWeight: 'bold',fontSize: '20'
                    return row.disable == 0 ? {color: 'green'} : {color: 'red'}
                }
            }
        }
    }
</script>