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
        watch: {
            rowData: function (newRowData, oldRowData) {
                console.log("原:", oldRowData.id, "  -->新:", newRowData.id)
                if (this.isIdFind) {
                    this.findId(newRowData);
                } else {
                    this.obj = newRowData;
                }
            }
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
                            label: '权限类Id',
                            prop: 'pid',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 权限类Id",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '请求方式',
                            prop: 'method',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 请求方式",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '权限url',
                            prop: 'url',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 权限url",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '权限备注信息',
                            prop: 'desc',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 权限备注信息",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '禁用',
                            prop: 'disable',
                            type: 'switch',
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 禁用",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '终端',
                            prop: 'type',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 终端",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '授权状态',
                            prop: 'state',
                            type: 'select',
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 授权状态",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '是否需要验签',
                            prop: 'isSign',
                            type: 'switch',
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 是否需要验签",
                                trigger: "blur"
                            }]
                        },

                    ]
                }
            }
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.obj.authoritys = null;
                this.crud.put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    console.debug(res);
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
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
             }
        }
    }
</script>
