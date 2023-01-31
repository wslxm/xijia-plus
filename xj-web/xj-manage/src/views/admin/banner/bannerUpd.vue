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
                            label: '位置',
                            prop: 'position',
                            labelWidth: '100',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Admin.BannerPosition),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 位置",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: 'banner标题',
                            prop: 'name',
                            labelWidth: '100',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 banner标题",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: 'banner描叙',
                            prop: 'desc',
                            labelWidth: '100',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 banner描叙",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: 'banner图片',
                            prop: 'imgUrl',
                            labelWidth: '100',
                            dataType: 'string', // 字符串模式
                            type: 'upload',
                            listType: 'picture-img',                // 单图-[picture-img] 多图-[picture-card] 缩略图-[picture](不定义=附件)
                            action: uploadPath + 'image/gc/',     // 上传地址(详见接口描叙,默认允许任意文件)
                            tip: '只能上传jpg/png/gif文件',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请上传 banner图片",
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
                                this.$message.success('上传成功');
                                done()
                            }
                        },
                        {
                            label: 'banner排序',
                            prop: 'sort',
                            labelWidth: '100',
                            type: 'number',
                            minRows: 0,
                            maxRows: 99999999,
                            row: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 banner排序",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '是否跳转',
                            prop: 'isSkip',
                            labelWidth: '100',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Base.BannerIsSkip),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 是否跳转",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '跳转地址url',
                            prop: 'skipUrl',
                            labelWidth: '100',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 20,
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
