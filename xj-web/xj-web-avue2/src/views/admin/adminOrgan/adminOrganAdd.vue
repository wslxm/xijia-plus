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
                defaultData: {
                    pid: null,
                    code: null,
                    name: null,
                    desc: "-",
                    sort: 0,
                    disable: null,
                    root: null,

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
                            label: '名称',
                            prop: 'name',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 部门/公司名称",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '编码 ',
                            prop: 'code',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 部门编码 ",
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
                                message: "请输入 部门/公司描叙",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            maxlength: 99999999999,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 排序",
                                trigger: "blur"
                            }]
                        },
                    ]
                }
            }
        },
        created() {
            this.obj = this.defaultData;
            if (this.rowData !== null && JSON.stringify(this.rowData) !== '{}') {
                this.obj.pid = this.rowData.id;
                this.obj.root = this.rowData.root + 1;
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
        }
    }
</script>
