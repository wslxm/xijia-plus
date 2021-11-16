<style>

</style>
<template>
    <div>
        <avue-crud style="margin-top: -13%"
                   ref="crudDictPid"
                   :data="data"
                   :option="option"
                   @select="select"
        ></avue-crud>
        <el-row style="margin-left: 32%">
            <el-button type="primary" @click="updPid()">提交</el-button>
            <el-button type="primary" @click="emptytChange()">关闭</el-button>
        </el-row>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                data: [],
                option: {},
                checkedRow: {}, //当前选中行数据
            }
        },
        props: {
            closeDialog: [],           // 关闭弹层方法
            uri: {},                   // 接口数据
            rowData: {},               // 当前行数据
        },
        watch: {
            rowData: function (newRowData, oldRowData) {
                console.debug("原:", oldRowData.id, "  -->新:", newRowData.id)
                if (newRowData != null && newRowData.id != null) {
                    this.init(newRowData);
                }
            }
        },
        created() {
            // 添加配置
            this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
            this.option.refreshBtn = false
            this.option.height = 560
            this.option.index = false;
            this.option.menu = false;
            this.option.tip = false;
            this.option.rowKey = "id"
            //this.option.height = 200;
            // 开启多选
            this.option.selection = true;
            this.option.reserveSelection = true;
            this.option.defaultExpandAll = true
            this.option.treeProps = {
                children: 'dictList'
            }
            this.option.column = [
                {
                    label: '字典名',
                    prop: 'name',
                    align: 'left',
                    width: 300,
                }
            ]
        },
        computed: {},
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            init(newRowData) {
                // 处理查询数据
                this.crud.get(this.uri.updPidInfoList).then((res) => {
                    this.data = res.data.data;
                    // 找到当前选中数据的父级
                    this.checkedRow = this.getPidDictNext(this.data, newRowData)
                    // 默认选中
                    this.$refs.crudDictPid.toggleSelection([this.checkedRow]);
                })
            },
            // 递归找父级
            getPidDictNext(dictDatas, dictData) {
                for (let i = 0; i < dictDatas.length; i++) {
                    if (dictDatas[i].id === dictData.pid) {
                        return dictDatas[i];
                    } else {
                        if (dictDatas[i].dictList != null && dictDatas[i].dictList.length > 0) {
                            let newDictData = this.getPidDictNext(dictDatas[i].dictList, dictData)
                            if (newDictData != null) {
                                return newDictData;
                            }
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
            select(selection, row) {
                this.checkedRow = row;
                this.$refs.crudDictPid.toggleSelection();
                this.$refs.crudDictPid.toggleSelection([row]);
            },
            // 提交修改请求
            updPid() {
                let pid = this.checkedRow.id;
                this.crud.put(this.uri.info + "/" + this.rowData.id, {pid: pid}).then((res) => {
                    this.closeDialog(true);
                })
            },
        }
    }
</script>