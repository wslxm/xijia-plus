<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">

            <template slot-scope="{}" slot="code">
                <div>
                    <el-row>
                        <el-col :span="10">
                            <el-input maxlength="32" show-word-limit v-model="obj.code" placeholder="请输入 字典code"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-button  style="margin-left: 10px" @click="obj.code =  toUp(obj.code)">转大写</el-button>
                        </el-col>
                    </el-row>
                </div>
            </template>
        </avue-form>
    </div>
</template>

<script>

export default {
    data() {
        return {
            obj: {},
            defaultData: {
                code: null,
                name: null,
                pid: 0,
                desc: "-",
                sort: 0,
                disable: 0,

            },
        }
    },
    props: {
        closeDialog: [],
        uri: {},
        rowData: {},
    },
    computed: {
        option() {
            return {
                submitBtn: true,
                emptyBtn: true,
                submitText: '提交',
                emptyText: "关闭",
                column: [
                    {
                        label: '字典名称',
                        prop: 'name',
                        maxlength: 64,
                        showWordLimit: true,
                        span: 20,
                        rules: [{
                            required: true,
                            message: "请输入 字典名称",
                            trigger: "blur"
                        }]
                    },
                    {
                        label: '字典Code',
                        prop: 'code',
                        maxlength: 32,
                        showWordLimit: true,
                        span: 20,
                        rules: [{
                            required: true,
                            message: "请输入 字典code",
                            trigger: "blur"
                        }]
                    },
                    {
                        label: '描叙',
                        prop: 'desc',
                        maxlength: 128,
                        showWordLimit: true,
                        span: 20,
                        rules: [{
                            required: true,
                            message: "请输入 描叙",
                            trigger: "blur"
                        }]
                    },
                    {
                        label: '排序',
                        prop: 'sort',
                        type: 'number',
                        precision: 0, //保留小数位
                        minRows: 0,
                        maxRows: 99999999999,
                        span: 20,
                        rules: [{
                            required: true,
                            message: "请输入 排序",
                            trigger: "blur"
                        }]
                    }, {
                        label: '扩展字段1',
                        prop: 'ext1',
                        maxlength: 128,
                        showWordLimit: true,
                        span: 20,
                        rules: [{
                            required: false,
                            message: " 扩展字段1",
                            trigger: "blur"
                        }]
                    }, {
                        label: '扩展字段2',
                        prop: 'ext2',
                        maxlength: 128,
                        showWordLimit: true,
                        span: 20,
                        rules: [{
                            required: false,
                            message: " 扩展字段2",
                            trigger: "blur"
                        }]
                    }, {
                        label: '扩展字段3',
                        prop: 'ext3',
                        maxlength: 128,
                        showWordLimit: true,
                        span: 20,
                        rules: [{
                            required: false,
                            message: " 扩展字段3",
                            trigger: "blur"
                        }]
                    },
                ]
            }
        }
    },
    watch: {
        //newNum = 新值，旧值
        "obj.code": function (newNum, oldNum) {
            console.log("=========" + this.newNum)
            this.$nextTick(() => {
                if (this.checkNumber(this.obj.code)) {
                    this.obj.sort = this.obj.code;
                } else {
                    this.obj.sort = 0;
                }
            })
        }
    },
    created() {
        this.obj = this.defaultData;
        // 判断是否是添加子类别，不是添加子类rowData不会发生改变，不会触发此方法
        if (this.rowData !== null && JSON.stringify(this.rowData) !== '{}') {
            this.obj.pid = this.rowData.id;
        }
    },
    methods: {
        emptytChange() {
            this.closeDialog(false);
        },
        submit(form, done) {
            this.crud.post(this.uri.info, this.obj).then((res) => {
                this.closeDialog(true);
                done(form);
            }).catch((err) => {
                console.error(err);
                done(form);
            })
        },
        //验证字符串是否是数字
        checkNumber(theObj) {
            var reg = /^[0-9]+.?[0-9]*$/;
            if (reg.test(theObj)) {
                return true;
            }
            return false;
        },
        toUp(str) {
            console.log(str)
            str = str.replace(/([A-Z])/g, "_$1").toUpperCase()
            console.log(str)
            return str;
        }
    }
}
</script>
