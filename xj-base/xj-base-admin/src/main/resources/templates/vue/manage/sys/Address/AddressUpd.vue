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
                isIdFind: true,
                initSuccess: false,
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
                            label: '父级挂接id',
                            prop: 'pid',
                            labelWidth: 110,
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 父级挂接id",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '区划名称',
                            prop: 'name',
                            labelWidth: 110,
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 区划名称",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '区划编码',
                            prop: 'code',
                            labelWidth: 110,
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: false,
                                message: "请输入 区划编码",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '备注',
                            prop: 'remark',
                            labelWidth: 110,
                            maxlength: 64,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: false,
                                message: "请输入 备注",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '级次id 0',
                            prop: 'level',
                            type: 'number',
                            labelWidth: 110,
                            precision: 0,  //保留小数位,
                            minRows: 0,
                            maxRows: 9,
                            row: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 级次id 0",
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
                this.initSuccess = true;
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
            findId(rowData) {
                if (rowData != null && rowData.id != null) {
                    this.crud.get(this.uri.info + "/" + rowData.id).then((res) => {
                         this.obj = res.data.data;
                         this.initSuccess = true;
                    })
                }
             }
        }
    }
</script>
