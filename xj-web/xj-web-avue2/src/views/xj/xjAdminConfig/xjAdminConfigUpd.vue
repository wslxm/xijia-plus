<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
        </avue-form>
    </div>
</template>

<script>

    import {baseUploadUrl} from "@/config/env";
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
                            label: '类型',
                            prop: 'type',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Base.ConfigType),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 类型",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '配置code',
                            prop: 'code',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 配置code|搜索值",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '配置名称',
                            prop: 'name',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 配置名称",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '配置内容',
                            prop: 'content',
                            type: 'textarea',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 配置内容",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            type: 'number',
                            minRows: 0,
                            maxRows: 99999999,
                            row: true,
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
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
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
