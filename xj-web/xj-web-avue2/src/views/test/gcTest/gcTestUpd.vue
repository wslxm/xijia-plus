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
                            label: '颜色选择器',
                            prop: 'color',
                            type: 'color',
                            colorFormat: "hex",
                            showAlpha: false,
                            predefine: ["#FF8C00", "#FFD700", "#90EE90", "#00CED1", "#1E90FF",
                                "#C71585", "#FF4500", "#FF7800", "#FAD400", "#00BABD"],
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 颜色选择器 ",
                                trigger: "blur"
                            }],
                        },
                        {
                            // 需先配置高德js 详见: https://avuejs.com/form/form-input-map/#
                            // 新增可给定默认地址: 104.06601585298779,30.656922000443107,四川省成都市青羊区西御河街道天府广场今站购物中心
                            label: '地址选择器',
                            prop: 'map',
                            type: 'map',
                            dataType: 'string',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 地址选择器 ",
                                trigger: "blur"
                            }],
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
