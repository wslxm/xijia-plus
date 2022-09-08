<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">
            <template slot-scope="{row}" slot="textTwo">
                <TinymceEditor v-if="initSuccess" :content.sync="obj.textTwo"/>
            </template>
            <template slot-scope="{row}" slot="textThree">
                <MdEditor v-if="initSuccess" :content.sync="obj.textThree"/>
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
                initSuccess: false,
                defaultData: {
                    name: null,
                    age: null,
                    sex: null,
                    like: null,
                    city: null,
                    disable: null,
                    headUrl: null,
                    time: null,
                    text: null,
                    textTwo: null,
                    textThree: null,
                    cascader: null,

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
                            label: '名称 ',
                            prop: 'name',
                            maxlength: 64,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 名称 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '年龄 ',
                            prop: 'age',
                            type: 'number',
                            precision: 2,  //保留小数位,
                            minRows: 0,
                            maxRows: 99999999,
                            row: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 年龄 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '性别 ',
                            prop: 'sex',
                            type: 'radio',
                            dicData: this.dict.get('SEX'),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 性别 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '爱好 ',
                            prop: 'like',
                            maxlength: 64,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 爱好 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '城市 ',
                            prop: 'city',
                            type: 'select',
                            filterable: true,
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 城市 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '禁用 ',
                            prop: 'disable',
                            type: 'switch',
                            dicData: this.dict.get('DISABLE'),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 禁用 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '单图文件',
                            prop: 'headUrl',
                            span: 24,
                            rules: [{
                                required: true,
                                message: "请上传 单图文件 ",
                                trigger: "blur"
                            }],
                            dataType: 'string',
                            accept: 'image/png, image/jpeg, image/jpg, image/gif',
                            type: 'upload',
                            listType: 'picture-img',
                            action: baseUploadUrl + 'file/gc/img/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
                            multiple: true,          // 文件多选
                            drag: true,              // 拖拽排序
                            limit: 1,                // 上传数量 1 个
                            //fileSize: 500,         // 上传大小 500 kb内
                            tip: '只能上传 jpg/png/gif 格式的图片',
                            loadText: '上传中...',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功');
                                done()
                            },
                            uploadError(error, column) {
                                // 上传失败
                                this.$message.error(error);
                            },
                            uploadExceed(limit, files, fileList, column) {
                                // 文件数量验证
                                this.$message.warning(`当前限制文件数量为 $1, 当前共 ${files.length + fileList.length} `);
                            },
                        },
                        {
                            label: '多图文件 ',
                            prop: 'headFiles',
                            span: 24,
                            rules: [{
                                required: true,
                                message: "请上传 多图文件  ",
                                trigger: "blur"
                            }],
                            dataType: 'string',
                            accept: 'image/png, image/jpeg, image/jpg, image/gif',
                            type: 'upload',
                            listType: 'picture-card',
                            action: baseUploadUrl + 'file/gc/img/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
                            multiple: true,          // 文件多选
                            drag: true,              // 拖拽排序
                            limit: 10,                // 上传数量 1 个
                            //fileSize: 500,         // 上传大小 500 kb内
                            tip: '只能上传10张 jpg/png/gif 格式的图片',
                            loadText: '上传中...',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功');
                                done()
                            },
                            uploadError(error, column) {
                                // 上传失败
                                this.$message.error(error);
                            },
                            uploadExceed(limit, files, fileList, column) {
                                // 文件数量验证
                                this.$message.warning(`当前限制文件数量为 $10, 当前共 ${files.length + fileList.length} `);
                            },
                        },
                        {
                            label: '视频文件 ',
                            prop: 'videoFiles',
                            span: 24,
                            rules: [{
                                required: true,
                                message: "请上传 视频文件  ",
                                trigger: "blur"
                            }],
                            dataType: 'string',
                            accept: 'video/mp4',
                            type: 'upload',
                            listType: 'picture-img',
                            action: baseUploadUrl + 'file/gc/video/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
                            multiple: true,          // 文件多选
                            drag: true,              // 拖拽排序
                            limit: 1,                // 上传数量 1 个
                            //fileSize: 500,         // 上传大小 500 kb内
                            tip: '只能上传mp4格式的视频',
                            loadText: '上传中...',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功');
                                done()
                            },
                            uploadError(error, column) {
                                // 上传失败
                                this.$message.error(error);
                            },
                            uploadExceed(limit, files, fileList, column) {
                                // 文件数量验证
                                this.$message.warning(`当前限制文件数量为 $1, 当前共 ${files.length + fileList.length} `);
                            },
                        },
                        {
                            label: '任意文件 ',
                            prop: 'files',
                            span: 24,
                            rules: [{
                                required: true,
                                message: "请上传 任意文件  ",
                                trigger: "blur"
                            }],
                            dataType: 'string',
                            accept: null,
                            type: 'upload',
                            listType: '',
                            action: baseUploadUrl + 'file/gc/all/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
                            multiple: true,          // 文件多选
                            drag: true,              // 拖拽排序
                            limit: 10,                // 上传数量 1 个
                            //fileSize: 500,         // 上传大小 500 kb内
                            tip: '',
                            loadText: '上传中...',
                            propsHttp: {
                                res: 'data'
                            },
                            uploadBefore: (file, done) => {
                                // 文件上传前处理
                                done(file)
                            },
                            uploadAfter: (res, done) => {
                                this.$message.success('上传成功');
                                done()
                            },
                            uploadError(error, column) {
                                // 上传失败
                                this.$message.error(error);
                            },
                            uploadExceed(limit, files, fileList, column) {
                                // 文件数量验证
                                this.$message.warning(`当前限制文件数量为 $10, 当前共 ${files.length + fileList.length} `);
                            },
                        },
                        {
                            label: '时间',
                            prop: 'time',
                            type: 'datetime',
                            format: 'yyyy-MM-dd hh:mm:ss',
                            valueFormat: 'yyyy-MM-dd hh:mm:ss',
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 时间",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'text',
                            type: 'textarea',
                            maxlength: 128,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'textTwo',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '更多信息',
                            prop: 'textThree',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息",
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
