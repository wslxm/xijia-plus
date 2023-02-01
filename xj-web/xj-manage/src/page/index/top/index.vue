<template>
    <div class="avue-top">
        <div class="top-bar__left">
            <div class="avue-breadcrumb" :class="[{ 'avue-breadcrumb--active': isCollapse }]"
                 v-if="setting.collapse&&!isHorizontal">
                <i class="icon-navicon" @click="setCollapse"></i>
            </div>
        </div>
        <div class="top-bar__title">
            <div class="top-bar__item top-bar__item--show" v-if="setting.menu">
                <top-menu ref="topMenu"></top-menu>
            </div>
            <span class="top-bar__item" v-if="setting.search">
                 <top-search></top-search>
           </span>
        </div>
        <!--  顶部按钮 -->
        <div class="top-bar__right">
            <!-- 主题颜色 -->
            <el-tooltip v-if="setting.color"
                        effect="dark"
                        :content="$t('navbar.color')"
                        placement="bottom">
                <div class="top-bar__item">
                    <top-color></top-color>
                </div>
            </el-tooltip>
            <!-- 日志 -->
            <el-tooltip v-if="setting.debug"
                        effect="dark"
                        :content="logsFlag?$t('navbar.bug'):logsLen+$t('navbar.bugs')"
                        placement="bottom">
                <div class="top-bar__item">
                    <top-logs></top-logs>
                </div>
            </el-tooltip>
            <!-- 锁屏 -->
            <el-tooltip v-if="setting.lock"
                        effect="dark"
                        :content="$t('navbar.lock')"
                        placement="bottom">
                <div class="top-bar__item">
                    <top-lock></top-lock>
                </div>
            </el-tooltip>
            <!-- 主题 -->
            <el-tooltip v-if="setting.theme"
                        effect="dark"
                        :content="$t('navbar.theme')"
                        placement="bottom">
                <div class="top-bar__item top-bar__item--show">
                    <top-theme></top-theme>
                </div>
            </el-tooltip>
            <!-- 消息 -->
            <el-tooltip effect="dark"
                        :content="$t('navbar.notice')"
                        placement="bottom">
                <div class="top-bar__item top-bar__item--show">
                    <!-- <top-notice></top-notice>-->
                    <top-msg></top-msg>
                </div>
            </el-tooltip>
            <!-- 语言 -->
            <el-tooltip effect="dark"
                        :content="$t('navbar.language')"
                        placement="bottom">
                <div class="top-bar__item top-bar__item--show">
                    <top-lang></top-lang>
                </div>
            </el-tooltip>
            <!-- 全屏 -->
            <el-tooltip v-if="setting.fullscren"
                        effect="dark"
                        :content="isFullScren?$t('navbar.screenfullF'):$t('navbar.screenfull')"
                        placement="bottom">
                <div class="top-bar__item">
                    <i :class="isFullScren?'icon-tuichuquanping':'icon-quanping'"
                       @click="handleScreen"></i>
                </div>
            </el-tooltip>
            <!-- 头像-->
            <img class="top-bar__img" :style="isOnline?'border: 1px solid #eee' : 'border: 1px solid red' " :src="userInfo.headPic">
            <!-- 下拉菜单  -->
            <el-dropdown>
                 <span class="el-dropdown-link">  {{ userInfo.fullName }}
                     <i class="el-icon-arrow-down el-icon--right"></i>
                 </span>
                <el-dropdown-menu slot="dropdown">
                    <!--    <el-dropdown-item>-->
                    <!--      <router-link to="/">{{$t('navbar.dashboard')}}</router-link>-->
                    <!--    </el-dropdown-item>-->
                    <el-dropdown-item>
                        <router-link to="/info/index">{{ $t('navbar.userinfo') }}</router-link>
                    </el-dropdown-item>
                    <!--    <el-dropdown-item>-->
                    <!--      <router-link to="/info/setting">{{$t('navbar.setting')}}</router-link>-->
                    <!--    </el-dropdown-item>-->
                    <el-dropdown-item @click.native="logout"
                                      divided>{{ $t('navbar.logOut') }}
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<script>
    import {mapGetters} from "vuex";
    import {fullscreenToggel, listenfullscreen} from "@/util/util";
    import topLock from "./top-lock";
    import topMenu from "./top-menu";
    import topSearch from "./top-search";
    import topTheme from "./top-theme";
    import topLogs from "./top-logs";
    import topColor from "./top-color";
    import topNotice from './top-notice'
    import topMsg from './top-msg'
    import topLang from "./top-lang";
    import {webSocketPath} from "@/config/env";
    import router from "@/router/router";

    export default {
        components: {
            topLock,
            topMenu,
            topSearch,
            topTheme,
            topLogs,
            topColor,
            topNotice,
            topMsg,
            topLang
        },
        name: "top",
        data() {
            return {
                isOnline: true,
            };
        },
        filters: {},
        created() {
            this.initWebSocket();
        },
        mounted() {
            listenfullscreen(this.setScreen);
        },
        computed: {
            ...mapGetters([
                "setting",
                "userInfo",
                "isFullScren",
                "tagWel",
                "tagList",
                "isCollapse",
                "tag",
                "logsLen",
                "logsFlag",
                "isHorizontal"
            ])
        },
        methods: {
            handleScreen() {
                fullscreenToggel();
            },
            setCollapse() {
                this.$store.commit("SET_COLLAPSE");
            },
            setScreen() {
                this.$store.commit("SET_FULLSCREN");
            },
            logout() {
                this.$confirm(this.$t("logoutTip"), this.$t("tip"), {
                    confirmButtonText: this.$t("submitText"),
                    cancelButtonText: this.$t("cancelText"),
                    type: "warning"
                }).then(() => {
                    this.$store.dispatch("LogOut").then(() => {
                        this.$router.push({path: "/login"});
                    });
                });
            },
            /* 以下是 websocket 处理*/
            initWebSocket: function () {
                if (typeof (WebSocket) === "undefined") {
                    alert("您的浏览器不支持socket")
                } else {
                    let webSocketURL = this.getWebsocketUrl();
                    console.log("websocket 请求地址: " + webSocketURL);

                    // 发起连接
                    this.socket = new WebSocket(webSocketURL);
                    // 监听socket连接
                    this.socket.onopen = this.onopen()
                    // 监听socket错误信息
                    this.socket.onerror = this.onerror
                    // 监听socket消息
                    this.socket.onmessage = this.onmessage
                    // 销毁监听
                    this.socket.onclose = this.onclose
                    // 检查websocket 状态
                    this.checkWebSocketState();
                }
            },
            onmessage: function (evt) {
                // websocket 封装的消息格式
                // console.log(evt);
                let received_msg = evt.data;           // 接收到的数据(str)
                console.log("接收到消息: " + received_msg);
                let obj = JSON.parse(received_msg);    // 接收到的数据(json)
                // 解析数据
                let msgType = obj.msgType;             // 数据类型 (0-心跳检测 1-上线通知 2-下线通知 3 在线用户名单通知 4 推送消息 )
                let from = obj.from;                   // 来源Id，上下线时为上下线的用户id
                let username = obj.username;           // 来源用户，上下线时为上下线的用户名
                let to = obj.to;                       // 目标Id（接收人用户Id,逗号分隔,所有人时为-ALL)）
                let content = obj.content;             // 内容 (text/json 前后端协商)
                let extras = obj.extras;               // 扩展内容 (text/json 前后端协商)
                let createTime = obj.createTime;       // 消息时间

                let loginUserId = this.userInfo.id;
                console.log("当前用户id: " + loginUserId);
                if (msgType === 1) {
                    // 上线通知, 只接收发给自己的消息
                    // if (to == loginUserId) {
                    // const h = this.$createElement;
                    // this.$notify({
                    //     offset: 50,
                    //     title: "消息",
                    //     message: h('i', {style: 'color: teal;cursor: pointer;'}, content),
                    //     duration: 1000 * 10,
                    // });
                    // }
                }
                if (msgType === 2) {
                    // 下线通知 (主要接收被迫下线通知), 只接收发给自己的消息
                    if (to == loginUserId) {
                        const h = this.$createElement;
                        this.$notify({
                            offset: 50,
                            title: "消息",
                            message: h('i', {style: 'color: teal;cursor: pointer;'}, content),
                            duration: 1000 * 10,
                        });
                    }
                }
                // 信息接收通知
                if (msgType === 4) {
                    console.log("消息查看:" + content);
                    // 此层数据同数据库消息表数据
                    let adminMsgData = JSON.parse(content);
                    // 具体消息内容
                    let msgData = JSON.parse(adminMsgData.content);
                    const h = this.$createElement;

                    // 弹出层展示消息内容
                    this.$notify({
                        title: msgData.title,
                        message: h('i', {style: 'color: teal;cursor: pointer;'}, msgData.message),
                        duration: 1000 * 10,
                        onClick: function () {
                            // 点击弹出层消息, 判断是否需要跳转路由
                            let routePath = msgData.routePath;
                            console.log("跳转路由:" + routePath);
                            if (routePath != null && routePath !== "") {
                                router.push({path: routePath + "&time=" + new Date().getTime()});
                            }
                        }
                    });
                    // 播放提示音
                    this.$refs.audio.currentTime = 0; // 从头开始播放提示音
                    this.$refs.audio.play();          // 播放
                    //this.say2( msgData.content);
                }
            },
            onopen: function () {
                let loginUserName = this.userInfo.fullName;
                const h = this.$createElement;
                this.$notify({
                    offset: 50,
                    title: "消息",
                    message: h('i', {style: 'color: teal;cursor: pointer;'}, loginUserName + ",【及时通知系统】已连接"),
                    duration: 1000 * 10,
                });
                // console.log("socket连接成功")
            },
            onerror: function () {
                console.log("连接错误")
            },
            onclose: function () {
                const h = this.$createElement;
                this.$notify({
                    offset: 50,
                    title: "消息",
                    message: h('i', {style: 'color: teal;cursor: pointer;'}, loginUserName + "【及时通知系统】已离线"),
                    duration: 1000 * 10,
                });
                console.log("socket已经关闭")
            },
            say2(content) {
                const ssu = new SpeechSynthesisUtterance();
                ssu.text = content;
                ssu.lang = 'zh';
                ssu.rate = 0.8;
                speechSynthesis.speak(ssu);
            },
            /**
             * 检查 websocket 在线状态
             * @returns {Promise<void>}
             */
            async checkWebSocketState() {
                let thin = this;
                setInterval(() => {
                    // console.log("websocket在线状态:" + thin.socket.readyState)
                    if (thin.socket.readyState === WebSocket.OPEN) {
                        thin.isOnline = true;
                    } else if (thin.socket.readyState === WebSocket.CLOSED) {
                        thin.isOnline = false;
                    }
                }, 2000)
            },
            /**
             * 获取 websocket 连接地址
             * @returns {Promise<void>}
             */
            getWebsocketUrl() {
                // websocket uri
                let webSocketURI = webSocketPath.replace("{userId}", this.userInfo.id)
                    .replace("{fullName}", this.userInfo.fullName)

                // 根据 http 协议判断是否为 ws 或 wss
                let prot = document.location.protocol == 'http:' ? 'ws:' : 'wss:';

                // 取地址+端口（配置80端口时 port 是空的,所以判断一下）
                let address = document.location.port ? document.location.hostname + ':' + document.location.port : document.location.hostname;

                // 拼接请求地址
                let webSocketURL = prot + "//" + address + webSocketURI;
                return webSocketURL;
            }
        }
    };
</script>

<style lang="scss" scoped>
</style>