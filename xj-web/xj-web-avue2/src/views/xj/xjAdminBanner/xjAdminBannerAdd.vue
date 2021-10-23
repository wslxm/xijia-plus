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
                    position: null,
                    name: null,
                    desc: null,
                    imgUrl: null,
                    sort: null,
                    disable: null,
                    isSkip: null,
                    skipUrl: null,

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
                            label: '位置',
                            prop: 'position',
                            span: 20,
                        },
                        {
                            label: 'banner标题',
                            prop: 'name',
                            span: 20,
                        },
                        {
                            label: 'banner描叙',
                            prop: 'desc',
                            span: 20,
                        },
                        {
                            label: 'banner图片',
                            prop: 'imgUrl',
                            span: 20,
                        },
                        {
                            label: 'banner排序',
                            prop: 'sort',
                            span: 20,
                        },
                        {
                            label: 'banner禁用',
                            prop: 'disable',
                            span: 20,
                        },
                        {
                            label: '是否跳转',
                            prop: 'isSkip',
                            span: 20,
                        },
                        {
                            label: '跳转地址url',
                            prop: 'skipUrl',
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
