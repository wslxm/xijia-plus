<template>
    <div>
        <avue-crud style="margin-top: -0%"
                   ref="cruddepPid"
                   :data="data"
                   :option="option"
                   @select="select"
        ></avue-crud>
        <el-row style="margin-left: 32%;margin-top: 2%">
            <el-button style="float: right; margin-right: 20px" size="small" @click="updPid()">提交</el-button>
            <el-button style="float: right; margin-right: 10px" size="small" @click="emptytChange()">关闭</el-button>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            data: [],
            option: {},
            checkedRow: {},      //当前选中行数据
            noCheckedRowIds: [], //当前选中下级ids
        }
    },
    props: {
        closeDialog: [],           // 关闭弹层方法
        uri: {},                   // 接口数据
        rowData: {},               // 当前行数据
    },
    created() {
        if (this.rowData !== null && JSON.stringify(this.rowData) !== '{}') {
            this.init(this.rowData);
        }
        // 添加配置
        this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
        this.option.refreshBtn = false;
        this.option.height = 560;
        this.option.index = false;
        this.option.menu = false;
        this.option.tip = false;
        this.option.rowKey = "id";
        // this.option.height = 200;
        // 开启多选
        this.option.selection = true;
        this.option.reserveSelection = true;
        this.option.defaultExpandAll = true;
        this.option.treeProps = {
            children: 'children'
        };
        // 不能选择自己及下级
        this.option.selectable = (row, index) => {
            return this.noCheckedRowIds.indexOf(row.id) === -1;
        };
        // 展示内容
        this.option.column = [
            {
                label: '菜单名称',
                prop: 'name',
                align: 'left',
                // width: 300,
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
            this.crud.get(this.uri.infoTree).then((res) => {
                this.data = res.data.data;
                // 找到当前选中数据的父级
                this.checkedRow = this.getPidNext(this.data, newRowData);
                // 默认选中父级
                this.$refs.cruddepPid.toggleSelection([this.checkedRow]);
                // 当前行+下级的ids 禁止选择
                this.getMenuNextIds([newRowData], this.noCheckedRowIds)
            })
        },
        // 递归找父级, 设置默认选中
        getPidNext(children, data) {
            for (let i = 0; i < children.length; i++) {
                if (children[i].id === data.pid) {
                    return children[i];
                } else {
                    if (children[i].children != null && children[i].children.length > 0) {
                        let newData = this.getPidNext(children[i].children, data)
                        if (newData != null) {
                            return newData;
                        }
                    }
                }
            }
        },
        // 递归获取自己 + 自己的下级ids, 自己以及自己的下级不能选
        getMenuNextIds(children, ids) {
            for (let i = 0; i < children.length; i++) {
                ids.push(children[i].id)
                if (children[i].children !== null) {
                    this.getMenuNextIds(children[i].children, ids);
                }
            }
        },
        // 勾选 请求方式处理数据
        select(selection, row) {
            this.checkedRow = row;
            this.$refs.cruddepPid.toggleSelection();
            this.$refs.cruddepPid.toggleSelection([row]);
        },
        // 提交修改请求
        updPid() {
            let pid = this.checkedRow.id;
            if (pid == this.rowData.id) {
                this.$message.error("不能选择当前数据")
                throw new Error("不能选择当前数据");
            }
            if (pid == this.rowData.pid) {
                this.$message.error("分配前的父级数据不能和分配后的父级数据相同")
                throw new Error("分配前的父级数据不能和分配后的父级数据相同");
            }
            this.crud.put(this.uri.info + "/" + this.rowData.id, {pid: pid}).then((res) => {
                this.closeDialog(true);
            })
        },
    }
}
</script>
