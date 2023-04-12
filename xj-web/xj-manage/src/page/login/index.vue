<template>
    <div class="login-container"
         @keyup.enter="handleLogin">
        <top-color v-show="false"></top-color>
        <div class="login-weaper animated bounceInDown">
            <div class="login-left">
                <div class="login-time">
                    {{ time }}
                </div>
                <img class="img"
                     src="/img/logo.png"
                     alt="">
                <!-- <p class="title">{{ $t('login.info') }}</p>-->
                <p class="title">{{ entryName }}</p>
            </div>

            <div class="login-border">
                <div class="login-main">
                    <h4 class="login-title">
                        {{ $t('login.title') }}{{ website.title }}
                        <top-lang></top-lang>
                    </h4>
                    <userLogin v-if="activeName==='user'"></userLogin>
                    <codeLogin v-else-if="activeName==='code'"></codeLogin>
                    <faceLogin v-else-if="activeName==='face'"></faceLogin>
                    <!-- 第三方登录 -->
                    <thirdLogin></thirdLogin>
                    <div class="login-menu">
                        <!-- 账号密码登录 -->
                        <a href="#" @click.stop="activeName='user'">{{ $t('login.userLogin') }}</a>
                        <!-- 手机号登录 -->
                        <a href="#" @click.stop="activeName='code'">{{ $t('login.phoneLogin') }}</a>
                        <!-- 刷脸登录 -->
                        <!-- <a href="#" @click.stop="activeName='face'">{{ $t('login.faceLogin') }} </a>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import userLogin from "./userlogin";
import codeLogin from "./codelogin";
import thirdLogin from "./thirdlogin";
import faceLogin from "./facelogin";
import {validatenull} from "@/util/validate";
import topLang from "@/page/index/top/top-lang";
import topColor from "@/page/index/top/top-color";

export default {
    name: "login",
    components: {
        userLogin,
        codeLogin,
        thirdLogin,
        faceLogin,
        topLang,
        topColor
    },
    data() {
        return {
            time: "",
            activeName: "user",
            /* 项目名称 */
            entryName: this.website.indexTitle
        };
    },
    watch: {
        $route() {
            const params = this.$route.query;
            this.socialForm.state = params.state;
            this.socialForm.code = params.code;
            if (!validatenull(this.socialForm.state)) {
                const loading = this.$loading({
                    lock: true,
                    text: `${this.socialForm.state === "WX" ? "微信" : "QQ"
                    }登录中,请稍后。。。`,
                    spinner: "el-icon-loading"
                });
                setTimeout(() => {
                    loading.close();
                }, 2000);
            }
        }
    },
    created() {
        this.getTime();
        setInterval(() => {
            this.getTime();
        }, 1000);
    },
    mounted() {
    },
    props: [],
    methods: {
        getTime() {
            this.time = this.$dayjs().format('YYYY年MM月DD日 HH:mm:ss')
        }
    }
};
</script>

<style lang="scss">
@import "@/styles/login.scss";
</style>