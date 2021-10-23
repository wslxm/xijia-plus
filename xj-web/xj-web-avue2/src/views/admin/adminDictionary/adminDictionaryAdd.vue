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

    export default {
        data() {
            return {
                obj: {},
                defaultData: {
                    code: null,
                    name: null,
                    pid: null,
                    desc: null,
                    sort: null,
                    disable: null,

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
                            label: '字典类型',
                            prop: 'code',
                            span: 20,
                        },
                        {
                            label: '字典名称',
                            prop: 'name',
                            span: 20,
                        },
                        {
                            label: '父Id',
                            prop: 'pid',
                            span: 20,
                        },
                        {
                            label: '描叙',
                            prop: 'desc',
                            span: 20,
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            span: 20,
                        },
                        {
                            label: '禁用',
                            prop: 'disable',
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
                    if (res.data.code == 200) {
                       this.closeDialog(true);
                    }
                    done(form);
                }).catch(() => {
                    done(form);
                })
            },
        }
    }
</script>
