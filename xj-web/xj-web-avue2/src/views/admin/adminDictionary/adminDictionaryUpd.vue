<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
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
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch(err => {
                    done(form);
                })
            },
            findId(newRowData) {
                if (newRowData != null && newRowData.id != null) {
                    get(this.uri.info + "/" + newRowData.id).then((res) => {
                         this.obj = res.data.data;
                    })
                }
             }
        }
    }
</script>
