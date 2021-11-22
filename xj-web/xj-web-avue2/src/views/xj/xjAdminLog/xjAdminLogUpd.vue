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
                    detail: true,
                    labelWidth: 110,
                    submitText: '提交',
                    emptyText: "关闭",
                    column: [
                        {
                            label: '请求人',
                            prop: 'fullName',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求终端',
                            prop: 'type',
                            type: 'select',
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 12,
                        },
                        {
                            label: '请求人Id ',
                            prop: 'userId',
                            maxlength: 32,
                            showWordLimit: true,
                        },
                        {
                            label: '请求来源',
                            prop: 'referer',
                            maxlength: 512,
                            showWordLimit: true,
                            span: 12,

                        },
                        {
                            label: '请求类',
                            prop: 'classDesc',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '用户主机名',
                            prop: 'host',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求方法',
                            prop: 'methodDesc',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '用户真实Ip',
                            prop: 'ip',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求uri',
                            prop: 'uri',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },


                        {
                            label: '服务器地址',
                            prop: 'serverName',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求url',
                            prop: 'url',
                            maxlength: 512,
                            showWordLimit: true,
                            span: 12,

                        },
                        {
                            label: '服务器端口',
                            prop: 'port',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求方式',
                            prop: 'method',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求包路径',
                            prop: 'packageName',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '请求时间',
                            prop: 'createTime',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },

                        {
                            label: '请求类路径',
                            prop: 'className',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 12,
                        },

                        {
                            label: '请求结果',
                            prop: 'state',
                            maxlength: 9,
                            showWordLimit: true,
                            span: 24,
                        },
                        {
                            label: '请求数据',
                            prop: 'requestData',
                            type: 'textarea',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 12
                        },
                        {
                            label: '返回数据',
                            prop: 'responseData',
                            type: 'textarea',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 12,
                        },

                        {
                            label: '响应总耗时',
                            prop: 'executeTime',
                            maxlength: 7766279631452241920,
                            showWordLimit: true,
                            span: 12,
                        },
                        {
                            label: '业务总耗时',
                            prop: 'businessTime',
                            maxlength: 7766279631452241920,
                            showWordLimit: true,
                            span: 12,
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
