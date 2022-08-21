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
                            label: '类型',
                            prop: 'type',
                            maxlength: 9,
                            showWordLimit: true,
                            span: 20,
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Base.BlacklistType),
                            rules: [{
                                required: true,
                                message: "请选择",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: 'ip',
                            prop: 'ip',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 ip",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '备注',
                            prop: 'desc',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 备注",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '禁用/启用',
                            prop: 'disable',
                            type: 'switch',
                            dicData: this.dict.get(this.website.Dict.Base.Disable,false),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 ",
                                trigger: "blur"
                            }]
                        },

                    ]
                }
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
            }
        }
    }
</script>
