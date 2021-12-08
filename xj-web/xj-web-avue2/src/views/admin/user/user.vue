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
                   @search-change="searchChange"
                   @row-click="handleRowClick">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
            </template>
            <template slot-scope="{scope,row,index,type,size}" slot="disable">
                <el-switch v-model="row.disable" @change="updDisable(row)"
                           active-color="#13ce66" inactive-color="#ff4949"
                           :active-value=0 :inactive-value=1
                           active-text="" inactive-text="">
                </el-switch>
            </template>
            <template slot-scope="{row,index,type,size}" slot="menu">
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updDialogVisible = true">编辑</el-button>
                <el-button icon="el-icon-edit" :size="size" :type="type" @click="updPwdDialogVisible = true">重置密码</el-button>
                <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
            </template>
            <template slot-scope="{row,index,type,size}" slot="head">
                <el-avatar :src="row.head"></el-avatar>
            </template>
        </avue-crud>
        <el-dialog title="新增" v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" top="6vh" :width="dialogWidth" @close="closeDialog">
            <Add :closeDialog="closeDialog" :uri="uri" :organs="organs" :roles="roles"></Add>
            <span slot="footer" class="dialog-footer"></span>
        </el-dialog>
        <el-dialog title="编辑" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" top="6vh" :width="dialogWidth" @close="closeDialog">
            <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData" :organs="organs" :roles="roles"></Upd>
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
                    organInfo: "/api/admin/organ/list?disable=0",
                    roleInfo: "/api/admin/role/list",
                    resetPassword: "/api/admin/user/{id}/resetPassword"
                },
                loading: true,
                dialogWidth: "60%",
                updPwdDialogVisible: false,   // 重置密码弹层开关状态
                addDialogVisible: false,      // 添加弹层开关状态
                updDialogVisible: false,      // 添加弹层开关状态
                page: this.website.pageParams,  // 分页参数
                search: {                       // 搜索参数
                    terminal: 2
                },
                data: [],                  // 列表数据
                organs: [],                // 部门数据
                roles: [],                 // 角色数据
                rowData: {},               // 当前选中行数据
                rowPassword: {             // 重置密码数据保存
                    info: "123456",
                    default: "123456",
                },
                option: {},
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
                {
                    label: '年龄',
                    prop: 'age',
                },
                {
                    label: '性别',
                    prop: 'gender',
                    dicData: this.dict.get(this.website.Dict.Base.Gender),
                },
                {
                    label: '注册时间',
                    prop: 'regTime',
                },
                {
                    label: '终端',
                    prop: 'terminal',
                    type: "select",
                    search: true,
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
        created() {
            // 部门数据(弹层数据)
            this.crud.get(this.uri.organInfo, {disable: 0, isTree: true}).then((res) => {
                this.organs = res.data.data;
            })
            // 角色数据(弹层数据)
            this.crud.get(this.uri.roleInfo, {disable: 0}).then((res) => {
                console.debug(res)
                this.roles = res.data.data.records;
                for (const role of this.roles) {
                    role.value = role.id;
                    role.label = role.name;
                }
            })
        },
        methods: {
            /**
             * 直接触发：  首次自动加载 / 点击分页 / 切换分页 / 跳转也 / 点击刷新
             * 被调用触发：搜索后 /  添加/编辑保存后 / 删除后
             * @author wangsong
             */
            onLoad() {
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
            // 行删除
            rowDel(row, index) {
                this.crud.delRow(this, this.uri.info, row.id, index);
            },
            // 启用/禁用
            updDisable(row) {
                this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
            },
            updPwd() {
                this.crud.put(this.uri.resetPassword.replace("{id}", this.rowData.id), null, {password: this.rowPassword.info});
                this.updPwdDialogVisible = false
                this.rowPassword.info = this.rowPassword.default
            },
            // 点击保存行数据(供行操作的任意地方获取数据)
            handleRowClick(row) {
                this.rowData = row;
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