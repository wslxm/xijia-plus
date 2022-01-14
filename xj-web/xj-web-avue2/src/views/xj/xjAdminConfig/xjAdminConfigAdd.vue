<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">

            <template slot-scope="scope" slot="content">
                <span v-if="obj.type == 0">
                        <el-input
                                type="textarea"
                                :rows="3"
                                placeholder="请输入内容"
                                v-model="obj.content">
                       </el-input>
                </span>
                <span v-if="obj.type == 1">
                     <el-upload
                             :action="baseUploadUrl + 'image/config/'"
                             list-type="picture-card"
                             :on-success="handleSuccess"
                             :on-remove="handleRemove">
                             <i class="el-icon-plus"></i>
                      </el-upload>
                </span>
                <span v-if="obj.type == 2">
                      <el-switch
                              v-model="obj.content"
                              active-color="#13ce66"
                              inactive-color="#ff4949"
                              active-value="true"
                              inactive-value="false">
                     </el-switch>
                </span>
            </template>
        </avue-form>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                obj: {},
                defaultData: {
                    code: null,
                    name: null,
                    content: null,
                    sort: null,
                    type: 0,
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
                            label: '类型',
                            prop: 'type',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Base.ConfigType),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 类型",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '配置code',
                            prop: 'code',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 配置code|搜索值",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '配置名称',
                            prop: 'name',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 配置名称",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '配置内容',
                            prop: 'content',
                            type: 'textarea',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 配置内容",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            type: 'number',
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


                    ]
                }
            }
        },
        created() {
            this.obj = this.defaultData
        },
        methods: {
            emptytChange() {
                this.closeDialog(false);
            },
            submit(form, done) {
                this.crud.post(this.uri.info, this.obj).then((res) => {
                    this.closeDialog(true);
                    done(form);
                }).catch((err) => {
                    console.error(err);
                    done(form);
                })
            },
            // 文件删除时的回调
            handleRemove(file, fileList) {
                this.obj.content = this.obj.content.replace("," + file.response.data.url, "");
                this.obj.content = this.obj.content.replace(file.response.data.url + ",", "");
                this.obj.content = this.obj.content.replace(file.response.data.url, "");
            },
            // 文件上传完成后的回调
            handleSuccess(res) {
                if (!this.obj.content) {
                    this.obj.content = res.data.url;
                } else {
                    this.obj.content += "," + res.data.url
                }
            }
        }
    }
</script>
