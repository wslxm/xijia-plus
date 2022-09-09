<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">

        </avue-form>
    </div>
</template>

<script>

    import {baseUploadUrl} from "@/config/env";
    export default {
        data() {
            return {
                obj: {},
                initSuccess: false,
                defaultData: {
                    icon: null,
                    color: null,

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
                            label: '图标 ',
                            prop: 'icon',
                            type: 'icon',
                            iconList: this.icon.iconList,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 图标 ",
                                trigger: "blur"
                            }],
                        },
                        {
                            label: '颜色选择器',
                            prop: 'color',
                            type: 'color',
                            colorFormat: "hex",
                            showAlpha: false,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 颜色选择器 ",
                                trigger: "blur"
                            }],
                        },

                    ]
                }
            }
        },
        created() {
            this.obj = this.defaultData;
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
