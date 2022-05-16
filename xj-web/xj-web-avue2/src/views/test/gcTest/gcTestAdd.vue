<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
            <template slot-scope="{row}" slot="textTwo">
                <TinymceEditor :content.sync="obj.textTwo"/>
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
                            type: 'radio',
                            dicData: this.dict.get('GENDER'),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 性别 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '爱好 ',
                            prop: 'like',
                            type: 'checkbox',
                            dataType: 'string', // 字符串模式
                            dicData: this.dict.get('MSG_USER_TYPE'),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 爱好 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '城市 ',
                            prop: 'city',
                            type: 'select',
                            dicData: this.dict.get('PAY_CHANNEL'),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 城市 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '禁用 ',
                            prop: 'disable',
                            type: 'switch',
                            dicData: this.dict.get('DISABLE'),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 禁用 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '头像 ',
                            prop: 'headUrl',
                            dataType: 'string', // 字符串模式
                            type: 'upload',
                            listType: 'picture-img',                // 单图-[picture-img] 多图-[picture-card] 缩略图-[picture](不定义=附件)
                            action: baseUploadUrl + 'file/gc/',     // 上传地址(详见接口描叙,默认允许任意文件)
                            tip: '只能上传jpg/png文件，且不超过500kb',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请上传 头像 ",
                                trigger: "blur"
                            }],
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功')
                                done()
                            }
                        },
                        {
                            label: '时间',
                            prop: 'time',
                            type: 'datetime',
                            format: 'yyyy-MM-dd hh:mm:ss',
                            valueFormat: 'yyyy-MM-dd hh:mm:ss',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 时间",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'text',
                            type: 'textarea',
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
