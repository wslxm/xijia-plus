<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   :upload-before="uploadBefore"
                   @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {get,post,put,del,delRow,list,update} from '@/api/crud';
    import {getDict} from '@/api/dict';
    import website from '@/config/website';
    import {baseUploadUrl} from "../../../config/env";

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
                            span: 20,
                        },
                        {
                            label: '年龄 ',
                            prop: 'age',
                            type: 'number',
                            minRows: 0,
                            maxRows: 99999999,
                            row: true,
                            span: 20,
                        },
                        {
                            label: '性别 ',
                            prop: 'sex',
                            type: 'radio',
                            dicData: getDict(website.Dict.Base.Gender),
                            span: 20,
                        },
                        {
                            label: '爱好 ',
                            prop: 'like',
                            type: 'checkbox',
                            dataType: 'string', // 字符串模式
                            dicData: getDict(website.Dict.Base.Default),
                            span: 20,
                        },
                        {
                            label: '城市 ',
                            prop: 'city',
                            type: 'select',
                            dicData: getDict(website.Dict.Base.Default),
                            span: 20,
                        },
                        {
                            label: '禁用 ',
                            prop: 'disable',
                            type: 'switch',
                            dicData: getDict(website.Dict.Base.Default),
                            span: 20,
                        },
                        {
                            label: '头像 ',
                            prop: 'headUrl',
                            span: 20,
                            dataType: 'string', // 字符串模式
                            type: 'upload',
                            listType: 'picture-img',                // 单图-[picture-img] 多图-[picture-card] 缩略图-[picture](不定义=附件)
                            action: baseUploadUrl + 'file/gc/',     // 上传地址(详见接口描叙,默认允许任意文件)
                            tip: '只能上传jpg/png文件，且不超过500kb',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done, loading, column) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done, loading, column) => {
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
                        },

                    ]
                }
            }
        },
        mounted() {
            this.obj = this.defaultData
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                post(this.uri.info, this.obj).then((res) => {
                    console.log(res);
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
