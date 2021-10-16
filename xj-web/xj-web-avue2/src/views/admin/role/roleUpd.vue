<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option" @reset-change="emptytChange" @submit="submit">
        </avue-form>
    </div>
</template>

<script>
    import {getDict} from '@/api/dict';
    import {put} from '@/api/crud';
    import website from '@/config/website';

    export default {
        // name: "RoleUpd",
        data() {
            return {
                obj: this.rowData,
                sizeValue: 'small'
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],         // 关闭弹层方法
            uri: {},                 // 添加接口
            rowData: {},             // 当前行数据
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            rowData: function (newRowData, oldRowData) {
                this.obj = newRowData;
            }
        },
        computed: {
            option() {
                return {
                    size: this.sizeValue,
                    submitText: '提交',
                    emptyText: "关闭",
                    submitBtn: true,   // 提交按钮
                    emptyBtn: true,    // 清空按钮
                    mockBtn: false,    // 模拟按钮
                    printBtn: false,   // 打印按钮
                    column: [
                        {
                            label: "终端",
                            prop: "terminal",
                            type: "select",
                            dicData: getDict(website.Dict.Admin.Terminal),
                            span: 20,
                            mock: {
                                type: 'dic'
                            },
                        },
                        {
                            label: "角色名称",
                            prop: "name",
                            span: 20,
                        },
                        {
                            label: "code",
                            prop: "code",
                            span: 20
                        },
                        {
                            label: "描叙",
                            prop: "desc",
                            span: 20
                        }, {
                            label: "是否禁用",
                            prop: "disable",
                            span: 6,
                            type: "switch",
                            dicData: getDict(website.Dict.Base.Disable, false),
                        }
                    ]
                }
            }
        },
        /**
         * 请求方法
         * @author wangsong
         * @param null
         * @date 2021/10/10 0010 12:55
         * @return
         * @version 1.0.1
         */
        methods: {
            emptytChange() {
                this.closeDialog(false);
                this.obj = this.rowData;
            },
            submit(form, done) {
                put(this.uri.info + "/" + this.obj.id, this.obj).then((res) => {
                    // 添加成功关闭弹层
                    if (res.data.code == 200) {
                        this.closeDialog(true);
                    }
                    done(form);
                }).catch(err => {
                    done(form);
                })
            },
        }
    }
</script>