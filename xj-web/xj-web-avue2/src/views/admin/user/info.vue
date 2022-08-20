<template>
    <div class="user-info">
        <el-row :span="24">

            <!-- 左边个人用户信息 -->
            <el-col :span="8">
                <basic-container>
                    <div class="user-info__content">
                        <img class="user-info__img"
                             :src="data.headPic"
                             alt=""/>
                        <p class="user-info__name">{{data.username}}</p>
                        <p class="user-info__name">{{data.fullName}}</p>
                        <!-- <router-link class="user-info__setting" :to="{path:'/info/setting'}">个人设置</router-link>-->
                        <!-- <p class="user-info__desc">{{form.ms}}</p> -->
                        <div class="user-info__detail-desc">
                            <p><i class="el-icon-phone"></i><span>{{data.phone}}</span></p>
                            <p><i class="el-icon-location-information"></i><span>{{data.dep !=null?data.address: '-'}}  </span></p>
                            <p><i class="el-icon-postcard"></i><span> {{data.dep !=null?data.dep.depNames: '-'}}  </span></p>
                            <p><i class="el-icon-crop"></i><span>  {{this.dict.convert("POSITION",data.position)}} </span></p>
                        </div>
                        <div class="user-info__divider"></div>
                        <h4>角色</h4>
                        <div class="user-info__tags">
                            <el-tag effect="plain" v-for="(role,index) in data.roles" :key="index">
                                {{role.name}}
                            </el-tag>
                        </div>
                    </div>
                </basic-container>
            </el-col>

            <!-- 表单 -->
            <el-col :span="16">
                <basic-container>
                    <avue-form :option="option"
                               v-model="form"
                               @chang="handleChange"
                               @submit="handleSubmit">

                    </avue-form>
                </basic-container>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    // import option from "@/const/user/info";
    import {baseUploadUrl} from "@/config/env";

    export default {
        data() {
            return {
                uri: {
                    findUser: "/api/admin/user/findUser",               // 查询当前登录人的信息
                    updByPassword: "/api/admin/user/updByPassword",     // 修改当前代登录人的密码
                    updUser: "/api/admin/user/updUser",                 // 修改当前登录人的信息
                },
                type: "info",
                // option: option,
                option: {
                    tabs: true,
                    group: [{
                        label: '个人信息',
                        prop: 'info',
                        column: [
                            {
                                label: '头像',
                                prop: 'headPic',
                                span: 24,
                                dataType: 'string', // 字符串模式
                                type: 'upload',
                                listType: 'picture-img',                  // 图片格式, 单图-[picture-img]  多图-[picture-card]  缩略图-[picture]
                                action: baseUploadUrl + 'image/head/',    // 上传地址 + 文件保存上传地址(详见接口描叙)
                                tip: '只能上传jpg/png文件，且不超过500kb',
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
                                rules: [{
                                    required: true,
                                    message: "请上传 头像 ",
                                    trigger: "blur"
                                }]
                            },
                            {
                                label: '姓名',
                                prop: 'fullName',
                                span: 20,
                                rules: [{
                                    required: true,
                                    message: "请输入 姓名 ",
                                    trigger: "blur"
                                }]
                            },
                            {
                                label: '性别',
                                prop: 'gender',
                                span: 20,
                                type: "radio",
                                dicData: this.dict.get(this.website.Dict.Base.Gender),
                                rules: [{
                                    required: true,
                                    message: "请选择 性别 ",
                                    trigger: "blur"
                                }]
                            },
                            {
                                label: '年龄',
                                prop: 'age',
                                span: 20,
                                rules: [{
                                    required: false,
                                    message: "请输入 年龄 ",
                                    trigger: "blur"
                                }]
                            },
                            {
                                label: '地址',
                                prop: 'address',
                                span: 20,
                                rules: [{
                                    required: false,
                                    message: "请输入 地址 ",
                                    trigger: "blur"
                                }]
                            }]
                    }, {
                        label: '修改密码',
                        prop: 'password',
                        column: [{
                            label: '原密码',
                            span: 16,
                            row: true,
                            type: 'password',
                            prop: 'oldPassword',
                        }, {
                            label: '新密码',
                            span: 16,
                            row: true,
                            type: 'password',
                            prop: 'password',
                        }, {
                            label: '确认密码',
                            span: 16,
                            row: true,
                            type: 'password',
                            prop: 'passwordTwo',
                        }]
                    }]
                },
                // 用户原数据
                data: {},
                form: {
                    oldPassword: null,
                    password: null,
                    passwordTwo: null
                },
            };
        },
        created() {
            // 获取用户信息
            this.crud.get(this.uri.findUser).then((res) => {
                this.data = res.data.data;
                // 表单数据回显
                this.form.address = this.data.address;
                this.form.age = this.data.age;
                this.form.gender = this.data.gender;
                this.form.fullName = this.data.fullName;
                this.form.headPic = this.data.headPic;
            }).catch(err => {
                console.error(err);
            })
        },
        methods: {
            handleSubmit(form, done) {
                if ((form.oldPassword !== null && form.oldPassword !== "")
                    || (form.password !== null && form.password !== "")
                    || (form.passwordTwo !== null && form.passwordTwo !== "")
                ) {
                    // 判断是否输入完整
                    if (form.oldPassword === null || form.oldPassword === "") {
                        this.$message.error('请输入 原密码');
                        done(form);
                        throw new Error();
                    }
                    if (form.password === null || form.password === "") {
                        this.$message.error('请输入 新密码');
                        done(form);
                        throw new Error();
                    }
                    if (form.passwordTwo === null || form.passwordTwo === "") {
                        this.$message.error('请输入 确认密码');
                        done(form);
                        throw new Error();
                    }
                    if (form.password === form.oldPassword) {
                        this.$message.error('新密码不能和原密码相同');
                        done(form);
                        throw new Error();
                    }
                    if (form.password !== form.passwordTwo) {
                        this.$message.error('两次输入的密码不一致');
                        done(form);
                        throw new Error();
                    }
                    // 修改密码
                    let params = {
                        oldPassword: form.oldPassword,
                        password: form.password
                    };
                    this.crud.put(this.uri.updByPassword, null, params).then(() => {
                        done(form);
                    }).catch(err => {
                        console.error(err);
                        done(form);
                    })
                } else {
                    // 修改个人信息
                    this.crud.put(this.uri.updUser, form).then(() => {
                        done(form);
                    }).catch(err => {
                        console.error(err);
                        done(form);
                    })
                }
            },
            handleChange(item) {
                this.type = item.prop;
            }
        }
    };
</script>

<style lang="scss">
    .user-info {
        .avue-tabs {
            padding: 0 10px;
        }

        .el-tabs__content {
            padding: 20px 0;
        }

        &__img {
            display: block;
            margin: 0 auto;
            border-radius: 100%;
            width: 100px;
            height: 100px;
        }

        &__name {
            text-align: center;
            font-size: 20px;
            line-height: 28px;
            font-weight: 500;
            color: rgba(0, 0, 0, 0.85);
            margin-bottom: 0;
            margin-top: 10px;
        }

        &__setting {
            margin-bottom: 12px;
            display: block;
            font-size: 12px;
            color: #409eff;
            text-align: center;
        }

        &__desc {
            text-align: center;
            width: 200px;
            margin: 0 auto;
        }

        &__detail-desc {
            margin-top: 50px;
            font-size: 14px;

            p {
                margin-bottom: 10px;
            }

            span {
                margin-left: 10px;
            }
        }

        &__divider {
            border-top: 1px dashed #e8e8e8;
            display: block;
            height: 0;
            width: 100%;
            margin: 24px 0;
            clear: both;
        }

        &__tags {
            .el-tag {
                margin: 0 5px 5px 0;
            }
        }
    }
</style>
