<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
            <template slot-scope="{row}" slot="textTwo">
                <TinymceEditor v-if="initSuccess" :content.sync="obj.textTwo"/>
            </template>
            <template slot-scope="{row}" slot="textThree">
                <MdEditor v-if="initSuccess" :content.sync="obj.textThree"/>
            </template>

        </avue-form>
    </div>
</template>

<script>

    import {baseUploadUrl} from "@/config/env";
    export default {
        data() {
            return {
                obj: {},
                initSuccess: false,
                defaultData: {
                    name: null,
                    age: null,
                    sex: null,
                    like: null,
                    city: null,
                    disable: null,
                    headUrl: null,
                    time: null,
                    text: null,
                    textTwo: null,
                    textThree: null,

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
                            label: '名称 ',
                            prop: 'name',
                            maxlength: 64,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 名称 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '年龄 ',
                            prop: 'age',
                            maxlength: 9999999999,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 年龄 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '性别 ',
                            prop: 'sex',
                            maxlength: 9,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 性别 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '爱好 ',
                            prop: 'like',
                            maxlength: 64,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 爱好 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '城市 ',
                            prop: 'city',
                            maxlength: 9,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 城市 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '禁用 ',
                            prop: 'disable',
                            maxlength: 9,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 禁用 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '头像 ',
                            prop: 'headUrl',
                            maxlength: 1024,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 头像 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '时间',
                            prop: 'time',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 时间",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'text',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'textTwo',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'textThree',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息",
                                trigger: "blur"
                            }]
                        },

                    ]
                }
            }
        },
        created() {
            this.obj = this.defaultData;
            this.initSuccess = true;
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
