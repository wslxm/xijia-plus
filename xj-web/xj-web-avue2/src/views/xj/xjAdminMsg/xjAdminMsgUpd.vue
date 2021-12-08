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
                    detail: true,
                    submitBtn: true,
                    emptyBtn: true,
                    submitText: '提交',
                    emptyText: "关闭",
                    column: [
                        {
                            label: '通知终端:',
                            prop: 'userType',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Admin.MsgUserType),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 通知终端",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '接收人id',
                            prop: 'userId',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 消息接收人",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '消息类型',
                            prop: 'msgType',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Admin.MsgType),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 消息类型 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '内容',
                            prop: 'content',
                            type: 'textarea',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 内容",
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
