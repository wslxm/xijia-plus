<template>
    <div>

        <div class="checkList">
            <el-checkbox-group v-model="checkList" @change="methodCheckListChange">
                <el-checkbox label="ALL"> ALL ({{checkNum[0]}})</el-checkbox>
                <el-checkbox label="GET"> GET ({{checkNum[1]}})</el-checkbox>
                <el-checkbox label="POST"> POST ({{checkNum[2]}})</el-checkbox>
                <el-checkbox label="PUT"> PUT ({{checkNum[3]}})</el-checkbox>
                <el-checkbox label="DELETE"> DELETE ({{checkNum[4]}})</el-checkbox>
            </el-checkbox-group>
        </div>
        <!--default-expand-all-->
        <el-tree
                :data="data"
                show-checkbox
                node-key="id"
                :default-expand-all=false
                :expand-on-click-node="false"
                :default-checked-keys="defaultCheckedKeys"
                ref="tree"
                highlight-current
                @check-change="heckChange"
                :props="defaultProps">
        </el-tree>

        <el-button style="margin-left: 45%" type="primary" size="small" plain @click="updRoleAuth()">保存权限</el-button>

    </div>
</template>


<script>
    export default {
        data() {
            return {
                data: [],
                checkList: [], // 请求方式选中值
                checkNum: [0, 0, 0, 0, 0, 0],// 选中数量(全部，get,post,put,delete, no全部)
                // 默认勾选节点
                defaultCheckedKeys: [],
                // 树展示配置
                defaultProps: {
                    children: 'authoritys',
                    label: function (data, node) {
                        if (data.pid !== "0") {
                            return data.method + " -- " + data.desc;
                        } else {
                            return data.desc;
                        }
                    }
                }
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],         // 关闭弹层方法
            uri: {},                 // 添加接口
            rowData: {},             // 当前行数据
        },
        mounted() {

        },
        created() {
            this.init(this.rowData);
        },
        methods: {
            /**
             * 获取数据
             */
            init(rowData) {
                this.crud.get(this.uri.authListByRole.replace("{roleId}", rowData.id), {
                    asc: "desc"
                }).then((res) => {
                    this.data = res.data.data;
                    // 处理默认选中
                    this.defaultCheckedKeys = this.getDefaultCheckeds();
                    // 获取相关数量
                    this.checkNumHandle();
                })
            },
            /**
             * 获取原始数据只选中数据， isChecked = true 的数据
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:56
             * @version 1.0.0
             */
            getDefaultCheckeds() {
                // 加载数据
                var checkedsKeys = [];
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    oneData.isChecked = false;
                    // 二级数据
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            if (oneData.authoritys[j].isChecked) {
                                checkedsKeys.push(oneData.authoritys[j].id);
                            }
                        }
                    }
                }
                return checkedsKeys;
            },


            /**
             * 勾选 请求方式处理数据
             *
             * @param checkList [ GET, POST,PUT,DELETE]
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:57
             * @version 1.0.0
             */
            methodCheckListChange(checkList) {
                // 处理源数据--选中未选中
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            // 选中下级
                            oneData.authoritys[j].isChecked = checkList.includes(oneData.authoritys[j].method);
                            oneData.authoritys[j].isChecked = checkList.includes("ALL") ? true : oneData.authoritys[j].isChecked;
                        }
                    }
                }
                // 处理选中状态
                this.$refs.tree.setCheckedKeys(this.getDefaultCheckeds(), true);
                this.checkNumHandle()
            },


            /**
             * 指定节点选中状态变化
             *
             * 1、更新原数据选中状态
             * 2、更新选中数量
             *
             */
            heckChange(data, checked, nextChecked) {
                // 更新原数据
                this.refreshData();
                // 更新选中数据
                this.checkNumHandle();
            },
            /**
             * 刷新原数据
             */
            refreshData() {
                // 当前选中数据ids
                var checkedKeys = this.$refs.tree.getCheckedKeys();
                // 加载数据
                for (let i = 0; i < this.data.length; i++) {
                    // this.data[i].isChecked = checkedKeys.includes(this.data[i].id);
                    // 二级数据
                    if (this.data[i].authoritys != null && this.data[i].authoritys.length > 0) {
                        for (let j = 0; j < this.data[i].authoritys.length; j++) {
                            this.data[i].authoritys[j].isChecked = checkedKeys.includes(this.data[i].authoritys[j].id);
                        }
                    }
                }
            },

            /**
             * 选中的数量统计
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:56
             * @version 1.0.0
             */
            checkNumHandle() {
                // 获取到选中的Id
                let checkNumAll = 0;
                let checkNumAllCount = 0;

                let checkNumGet = 0;
                let checkNumGetCount = 0;

                let checkNumPost = 0;
                let checkNumPostCount = 0;

                let checkNumPut = 0;
                let checkNumPutCount = 0;

                let checkNumDelete = 0;
                let checkNumDeleteCount = 0;

                let noCheckNum = 0; //非请求，父级数量
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    oneData.isChecked ? checkNumAll++ : checkNumAll;
                    noCheckNum++;
                    // 二级数据
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            checkNumAllCount++;
                            oneData.authoritys[j].isChecked ? checkNumAll++ : checkNumAll;
                            // 获取各请求数量
                            if (oneData.authoritys[j].method == "GET") {
                                checkNumGetCount++;
                                oneData.authoritys[j].isChecked ? checkNumGet++ : checkNumGet;
                            } else if (oneData.authoritys[j].method == "POST") {
                                checkNumPostCount++;
                                oneData.authoritys[j].isChecked ? checkNumPost++ : checkNumPost;
                            } else if (oneData.authoritys[j].method == "PUT") {
                                checkNumPutCount++;
                                oneData.authoritys[j].isChecked ? checkNumPut++ : checkNumPut;
                            } else if (oneData.authoritys[j].method == "DELETE") {
                                checkNumDeleteCount++;
                                oneData.authoritys[j].isChecked ? checkNumDelete++ : checkNumDelete;
                            }
                        }
                    }
                }
                this.checkNum.pop();
                this.checkNum.pop();
                this.checkNum.pop();
                this.checkNum.pop();
                this.checkNum.pop();
                this.checkNum.pop();
                this.checkNum.push(checkNumAll + " / " + checkNumAllCount); //0
                this.checkNum.push(checkNumGet + " / " + checkNumGetCount); //1
                this.checkNum.push(checkNumPost + " / " + checkNumPostCount); //2
                this.checkNum.push(checkNumPut + " / " + checkNumPutCount); //3
                this.checkNum.push(checkNumDelete + " / " + checkNumDeleteCount); //4
                //this.checkNum.push(noCheckNum + "/" + checkNumAllCount; //5
            },


            /**
             * 保存提交 选中的权限数据
             * @author wangsong
             * @date 2021/11/11 0011 10:55
             * @return
             * @version 1.0.0
             */
            updRoleAuth() {
                // 只保存二级数据，不保存父级类信息数据
                let authIds = [];
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    // oneData.isChecked ? authIds.push(oneData.id) : null;
                    // 二级数据
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            oneData.authoritys[j].isChecked ? authIds.push(oneData.authoritys[j].id) : null;
                        }
                    }
                }
                let data = {
                    "roleId": this.rowData.id,
                    "authIds": authIds
                };
                this.crud.put(this.uri.updRoleAuth, data).then(() => {
                   // this.$message.success("成功分配[" + data.authIds.length + "]条数据")
                })
            },
        }
    }
</script>

<style>
    .checkList {
        padding-left: 30%;
    }
</style>
