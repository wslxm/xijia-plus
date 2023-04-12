<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
        </avue-form>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                obj: {},
                isIdFind: false,
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
                                message: "请输入 字典类型",
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
                        },  {
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
                console.debug("=========" + this.newNum)
                this.$nextTick(() => {
                    if (this.checkNumber(this.obj.code)) {
                        this.obj.sort = this.obj.code;
                    }
                })
            }
        },
        created() {
            if (this.isIdFind) {
                this.findId(this.rowData);
            } else {
                this.obj = this.rowData;
            }
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.obj.dictList = null;
                this.crud.put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    this.closeDialog(true);
                    done(form);
                }).catch(err => {
                    console.error(err);
                    done(form);
                })
            },
            findId(newRowData) {
                if (newRowData != null && newRowData.id != null) {
                    this.crud.get(this.uri.info + "/" + newRowData.id).then((res) => {
                        this.obj = res.data.data;
                    })
                }
            },
            //验证字符串是否是数字
            checkNumber(theObj) {
                var reg = /^[0-9]+.?[0-9]*$/;
                if (reg.test(theObj)) {
                    return true;
                }
                return false;
            },
        }
    }
</script>
