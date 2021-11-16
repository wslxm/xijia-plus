<template>
    <div>
        <div class="checkList">
            <el-checkbox-group v-model="checkList" @change="methodCheckListChange">
                <el-checkbox label="GET"> GET ({{checkNum[1]}})</el-checkbox>
                <el-checkbox label="POST"> POST ({{checkNum[2]}})</el-checkbox>
                <el-checkbox label="PUT"> PUT ({{checkNum[3]}})</el-checkbox>
                <el-checkbox label="DELETE"> DELETE ({{checkNum[4]}})</el-checkbox>
            </el-checkbox-group>
        </div>
        <avue-crud
                ref="auths"
                :data="data"
                :option="option"
                :cell-style="cellStyle"
                @select="select"
                @select-all="selectAll"
                @selection-change="selectionChange">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="primary" size="small" plain @click="updRoleAuth()">保存</el-button>
                当前选中数：{{checkNum[0]}} |-接口数：{{checkNum[0] - checkNum[5] }} |-父级数：{{checkNum[5] }}
            </template>
        </avue-crud>

    </div>
</template>


<script>
    export default {
        data() {
            return {
                data: [],
                option: {},
                checkList: [], // 请求方式选中值
                checkNum: [0, 0, 0, 0, 0, 0] // 选中数量(全部，get,post,put,delete, no全部)
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],         // 关闭弹层方法
            uri: {},                 // 添加接口
            rowData: {},             // 当前行数据
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            rowData: function (newRowData, oldRowData) {
                console.log("原:", oldRowData.id, "  -->新:", newRowData.id)
                if (newRowData != null && newRowData.id != null) {
                    this.init(newRowData);
                }
            }
        },
        mounted() {
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.menu = false
            this.option.index = false
            this.option.refreshBtn = false
            this.option.height = 600
            // 开启数结构
            this.option.defaultExpandAll = true
            this.option.rowKey = "id"
            this.option.treeProps = {
                children: 'authoritys'
            }
            // 开启多选//this.option.selectOnIndeterminate = false; // 关闭全选
            this.option.selection = true;
            this.option.reserveSelection = true;
            this.option.tip = false;
            this.option.column = [
                {
                    label: '权限备注信息',
                    prop: 'desc',
                    overHidden: true,
                },
                {
                    label: '请求方式',
                    prop: 'method',
                },
                {
                    label: '权限url',
                    prop: 'url',
                    overHidden: true,
                },
                {
                    label: '接口类型',
                    prop: 'type',
                    search: false,
                    overHidden: true,
                    dicData: this.dict.get(this.website.Dict.Base.AuthorityType),
                },
            ]
        },
        created() {
        },
        methods: {
            init(rowData) {
                this.crud.get(this.uri.authListByRole.replace("{roleId}", rowData.id)).then((res) => {
                    this.data = res.data.data;
                    //解决表格错位
                    this.$nextTick(() => {
                        this.$refs.auths.doLayout();
                    });
                    // 处理默认选中
                    this.checkeds();
                })
            },
            // 选中的复选字段，给所有数据处理当前 isChecked 参数
            selectionChange() {//list
                //
            },
            /**
             * 勾选指定字段， 处理源数据--选中未选中
             */
            select(selection, row) {
                console.log("勾选单个")
                row.isChecked = !row.isChecked;
                // 处理下级数据，勾选的如果是父级设置下级 所有为 选中 或 取消选中
                if (row.authoritys != null && row.authoritys.length > 0) {
                    for (let j = 0; j < row.authoritys.length; j++) {
                        row.authoritys[j].isChecked = row.isChecked;
                    }
                }
                // 处理表格选中状态
                this.checkeds();
            },
            /**
             * 勾选全部 处理数据（全选/反选）
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:57
             * @version 1.0.0
             */
            selectAll() {//selection
                console.log("勾选全选")
                // 判断是否有选中, 有取消全部选中，没有选择全部
                let isChecked = true
                for (let i = 0; i < this.data.length; i++) {
                    if (this.data[i].isChecked) {
                        isChecked = false;
                        break;
                    }
                }
                // 处理源数据
                this.isCheckedDataHandle(isChecked);
                // 处理表格选中状态
                this.checkeds();
            },
            /**
             *  全选 处理源数据--选中未选中
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:57
             * @version 1.0.0
             */
            isCheckedDataHandle(isChecked) {
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    oneData.isChecked = isChecked;
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            oneData.authoritys[j].isChecked = isChecked;
                        }
                    }
                }
            },
            /**
             * 勾选 请求方式处理数据
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:57
             * @version 1.0.0
             */
            methodCheckListChange(checkList) {
                // 处理源数据--选中未选中
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    // 父级是否选中
                    let oneIsChecked = false;
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            oneData.authoritys[j].isChecked = checkList.includes(oneData.authoritys[j].method);
                            if (!oneIsChecked) {
                                oneIsChecked = oneData.authoritys[j].isChecked
                            }
                        }
                    }
                    oneData.isChecked = oneIsChecked;
                }
                // 处理表格的选中状态
                this.checkeds();
            },

            /**
             * 处理各种操作后的 源数据 isChecked = true 的数据全部设置为选中状态（单选，全选，首次加载数据回显等触发）
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:56
             * @version 1.0.0
             */
            checkeds() {
                // 清除列表的原选中状态
                this.$refs.auths.toggleSelection();
                // 加载数据
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    if (oneData.isChecked) {
                        this.$refs.auths.toggleSelection([oneData]);
                    }
                    // 二级数据
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            if (oneData.authoritys[j].isChecked) {
                                this.$refs.auths.toggleSelection([oneData.authoritys[j]]);
                            }
                        }
                    }
                }
                // 获取总数
                this.checkNumHandle();
            },
            /**
             * 数量统计
             * @author wangsong
             * @mail  1720696548@qq.com
             * @date  2021/11/11 0011 10:56
             * @version 1.0.0
             */
            checkNumHandle() {
                let checkNumAll = 0;
                let checkNumGet = 0;
                let checkNumPost = 0;
                let checkNumPut = 0;
                let checkNumDelete = 0;
                let noCheckNum = 0; //非请求，父级数量
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    oneData.isChecked ? checkNumAll++ : checkNumAll;
                    noCheckNum++;
                    // 二级数据
                    if (oneData.authoritys != null && oneData.authoritys.length > 0) {
                        for (let j = 0; j < oneData.authoritys.length; j++) {
                            oneData.authoritys[j].isChecked ? checkNumAll++ : checkNumAll;
                            // 获取各请求数量
                            if (oneData.authoritys[j].isChecked) {
                                if (oneData.authoritys[j].method == "GET") {
                                    checkNumGet++;
                                } else if (oneData.authoritys[j].method == "POST") {
                                    checkNumPost++;
                                } else if (oneData.authoritys[j].method == "PUT") {
                                    checkNumPut++;
                                } else if (oneData.authoritys[j].method == "DELETE") {
                                    checkNumDelete++;
                                }
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
                this.checkNum.push(checkNumAll); //0
                this.checkNum.push(checkNumGet); //1
                this.checkNum.push(checkNumPost); //2
                this.checkNum.push(checkNumPut); //3
                this.checkNum.push(checkNumDelete); //4
                this.checkNum.push(noCheckNum); //5
            },
            /**
             * 保存提交 选中的权限数据
             * @author wangsong
             * @date 2021/11/11 0011 10:55
             * @return
             * @version 1.0.0
             */
            updRoleAuth() {
                let authIds = [];
                for (let i = 0; i < this.data.length; i++) {
                    let oneData = this.data[i];
                    oneData.isChecked ? authIds.push(oneData.id) : null;
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
                }
                this.crud.put(this.uri.updRoleAuth, data).then(() => {
                    this.$message.success("成功分配[" + data.authIds.length + "]条数据")
                })
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

<style>
    .checkList {
        padding-left: 50%;
    }
</style>
