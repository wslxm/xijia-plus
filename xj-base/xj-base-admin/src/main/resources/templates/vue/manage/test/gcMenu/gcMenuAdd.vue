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
                    twoUrl: null,
                    url: null,
                    icon: null,
                    sort: null,
                    root: null,
                    disable: null,

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
                        {
                            label: '指定父id',
                            prop: 'pid',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 指定父id",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '菜单名',
                            prop: 'name',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 菜单名",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '第二路由 ',
                            prop: 'twoUrl',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 第二路由 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '菜单url',
                            prop: 'url',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 菜单url",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '图标',
                            prop: 'icon',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 图标",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            maxlength: 99999999999,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 排序",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '目录级别',
                            prop: 'root',
                            maxlength: 9,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 目录级别",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '禁用',
                            prop: 'disable',
                            type: 'switch',
                            dicData: this.dict.get('DISABLE'),
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
