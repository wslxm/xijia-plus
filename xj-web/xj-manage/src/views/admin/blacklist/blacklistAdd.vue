<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
        </avue-form>
    </div>
</template>

<script>

    import {uploadPath} from "@/config/env";
    export default {
        data() {
            return {
                obj: {},
                defaultData: {
                    type: 1,
                    ip: null,
                    desc: null,
                    disable: 0,

                },
            }
        },
        props: {
            closeDialog: [],
            uri: {},
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
                                message: "请选择 ip",
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
            this.obj = this.defaultData
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.crud.post(this.uri.info, this.obj).then((res) => {
                    console.debug(res);
                    if (res.data.code == 200) {
                       this.closeDialog(true);
                    }
                    done(form);
                }).catch((err) => {
                    console.error(err);
                    done(form);
                })
            },
        }
    }
</script>
