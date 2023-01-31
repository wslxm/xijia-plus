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
                initSuccess: false,
                defaultData: {
                    pid: null,
                    name: null,
                    desc: null,
                    code: null,
                    sort: null,
                    disable: 0,

                },
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
                        // {
                        //     label: '父Id ',
                        //     prop: 'pid',
                        //     maxlength: 32,
                        //     showWordLimit: true,
                        //     span: 20,
                        //     rules: [{
                        //         required: true,
                        //         message: "请输入 父Id ",
                        //         trigger: "blur"
                        //     }]
                        // },
                        {
                            label: '名称',
                            prop: 'name',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 公司/部门名称",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '描叙',
                            prop: 'desc',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 公司/部门描叙",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '编码 ',
                            prop: 'code',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 部门编码 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            type: 'number',
                            precision: 0,  //保留小数位,
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
                        {
                            label: '禁用',
                            prop: 'disable',
                            type: 'switch',
                            dicData: this.dict.get('DISABLE',false),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 禁用",
                                trigger: "blur"
                            }]
                        },

                    ]
                }
            }
        },
        created() {
            this.obj = this.defaultData;
            this.obj.pid = this.rowData.id;
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
