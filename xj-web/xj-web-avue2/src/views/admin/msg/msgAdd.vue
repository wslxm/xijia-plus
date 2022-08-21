<template>
    <div>
        <avue-form ref="form" v-model="obj" :option="option"
                   @reset-change="emptytChange"
                   @submit="submit">

            <!-- 选择系统用户 -->
            <template slot-scope="scope" slot="selectUserId">
                <el-select v-model="obj.selectUserId" filterable :clearable="true" @change="selectUserIdHandle()" placeholder="请选择">
                    <el-option
                            v-for="item in userList"
                            :key="item.id"
                            :label="item.fullName"
                            :value="item.id">
                    </el-option>
                </el-select>
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
                    userId: null,
                    content: null,
                    userType: 2,
                    msgType: 0,
                    isRead: false,
                },
                // 是否展示用户列表
                isUserList: true,
                userList: []
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
                            label: '通知终端:',
                            prop: 'userType',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Admin.MsgUserType),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 通知终端",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '接收人id',
                            prop: 'userId',
                            maxlength: 32,
                            showWordLimit: true,
                            span: 8,
                            rules: [{
                                required: true,
                                message: "请输入 消息接收人id",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '选择系统用户',
                            prop: 'selectUserId',
                            labelWidth: 130,
                            span: 8,
                            display: this.isUserList
                        },
                        {
                            label: '消息类型',
                            prop: 'msgType',
                            type: 'radio',
                            dicData: this.dict.get(this.website.Dict.Admin.MsgType),
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请选择 消息类型 ",
                                trigger: "blur"
                            }]
                        },
                        {
                            label: '内容',
                            prop: 'content',
                            type: 'textarea',
                            maxlength: 256,
                            showWordLimit: true,
                            span: 20,
                            rules: [{
                                required: true,
                                message: "请输入 内容",
                                trigger: "blur"
                            }]
                        },
                    ]
                }
            }
        },
        watch: {
            "obj.userType"(newUserType, oldUserType) {
                // 设置url 参数到搜索条件中
                this.isUserList = newUserType !== 1;
            },
        },


        created() {
            this.obj = this.defaultData;
            // 查询系统用户数据
            this.crud.get(this.uri.userList).then((res) => {
                this.userList = res.data.data;
            })
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
                    done(form);
                })
            },
            /**
             * 是否打开 选择系统用户选择 (选择发送消息到管理端时打开)
             */
            selectUserIdHandle() {
                this.obj.userId = this.obj.selectUserId;
            }
        }
    }
</script>
