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

    import {uploadPath} from "@/config/env";
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
                            dicData: this.dict.get('GENDER'),
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
                            type: 'checkbox',
                            dataType: 'string', // 字符串模式
                            dicData: this.dict.get(this.website.Dict.Base.Default),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 爱好 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '城市 ',
                            prop: 'city',
                            type: 'select',
                            filterable:true,
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
                            action: uploadPath + 'file/gc/img/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
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
                            uploadExceed(limit, files, fileList, column){
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
                            action: uploadPath + 'file/gc/img/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
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
                            uploadExceed(limit, files, fileList, column){
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
                            action: uploadPath + 'file/gc/video/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
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
                            uploadExceed(limit, files, fileList, column){
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
                            action: uploadPath + 'file/gc/all/',   // 上传地址 + 文件保存上传地址(详见接口描叙)
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
                            uploadExceed(limit, files, fileList, column){
                                // 文件数量验证
                                this.$message.warning(`当前限制文件数量为 $10, 当前共 ${files.length + fileList.length} `);
                            },
                        },
                        {
                            label: '时间 ',
                            prop: 'time',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
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
                            type: 'textarea',
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
                            label: '更多信息-md ',
                            prop: 'textThree',
                            maxlength: 0,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 更多信息-md ",
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
                            dicData: this.defaultdata.dicData,   // 自行替换字典数据
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
                            label: '数组框 ',
                            prop: 'array',
                            type: 'array',
                            dataType: 'string',
                            limit: 10,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请添加 数组框 ",
                                trigger: "blur"
                            }]
                        },
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
