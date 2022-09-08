<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
            <template slot-scope="{row}" slot="text">
                <MdEditor v-if="initSuccess" :content.sync="obj.text"/>
            </template>
            <template slot-scope="{row}" slot="textTwo">
                <TinymceEditor v-if="initSuccess" :content.sync="obj.textTwo"/>
            </template>

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
                            label: '时间 ',
                            prop: 'time',
                            type: 'datetime',
                            format: 'yyyy-MM-dd hh:mm:ss',
                            valueFormat: 'yyyy-MM-dd hh:mm:ss',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 时间 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: "时间-小时 ",
                            prop: "timeTwo",
                            type: "time",
                            span: 20,
                            pickerOptions: {
                                start: '06:00',
                                step: '00:30',
                                end: '23:00'
                            },
                            rules: [{
                                required: true,
                                message: "请输入 时间-小时  ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息-text ',
                            prop: 'text',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息-text ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息-fwb ',
                            prop: 'textTwo',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息-fwb ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '级联选择器  ',
                            prop: 'cascader',
                            span: 20,
                            type: "cascader",
                            dataType: 'string',
                            filterable: true, 
                            dicData: this.defaultDic.dicData,   // 自行替换字典数据  
                            props: {
                                value: "id",
                                label: "name",
                                children: "children"
                            },
                            rules: [{
                                required: true,
                                message: "请选择 级联选择器   ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '地址选择器',
                            prop: 'map',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 地址选择器",
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
