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
                    pid: null,
                    method: null,
                    url: null,
                    desc: null,
                    disable: null,
                    type: null,
                    state: null,
                    isSign: null,

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
                            label: '权限类Id',
                            prop: 'pid',
                            span: 20,
                        },
                        {
                            label: '请求方式',
                            prop: 'method',
                            span: 20,
                        },
                        {
                            label: '权限url',
                            prop: 'url',
                            span: 20,
                        },
                        {
                            label: '权限备注信息',
                            prop: 'desc',
                            span: 20,
                        },
                        {
                            label: '禁用',
                            prop: 'disable',
                            span: 20,
                        },
                        {
                            label: '终端',
                            prop: 'type',
                            span: 20,
                        },
                        {
                            label: '授权状态',
                            prop: 'state',
                            span: 20,
                        },
                        {
                            label: '是否需要验签',
                            prop: 'isSign',
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
