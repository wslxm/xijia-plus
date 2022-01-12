<template>
    <div class="avue-contail" :class="{'avue--collapse':isCollapse,}">
        <screenshot v-if="setting.screenshot"></screenshot>
        <setting></setting>

        <div class="avue-layout"
             :class="{'avue-layout--horizontal':isHorizontal}">
            <div class="avue-sidebar" v-show="validSidebar">
                <!-- 左侧导航栏 -->
                <logo/>
                <sidebar/>
            </div>
            <div class="avue-main">
                <!-- 顶部导航栏 -->
                <top ref="top"/>
                <!-- 顶部标签卡 -->
                <tags/>
                <transition name="fade-scale">
                    <search class="avue-view"
                            v-show="isSearch"></search>
                </transition>
                <!-- 主体视图层 -->
                <div style="flex:auto;overflow-y:auto;overflow-x:hidden;"
                     id="avue-view"
                     v-show="!isSearch">
                    <keep-alive>
                        <router-view class="avue-view"
                                     v-if="$route.meta.keepAlive && isRefresh"/>
                    </keep-alive>
                    <router-view class="avue-view"
                                 v-if="!$route.meta.keepAlive && isRefresh"/>
                </div>
                <!--                <div class="avue-footer">-->
                <!--                    <p class="copyright">© 2019-2021 spring-boot-plus2</p>-->
                <!--                </div>-->
            </div>

        </div>
        <!-- 消息提示音-->
        <audio controls="controls" hidden src="/audio/notice/1.mp3" ref="audio"></audio>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import tags from "./tags";
    import screenshot from './screenshot';
    import setting from './setting';
    import search from "./search";
    import logo from "./logo";
    import top from "./top/";
    import sidebar from "./sidebar/";
    import admin from "@/util/admin";
    import {validatenull} from "@/util/validate";
    import index from '@/mixins/index'
    import {baseWebSocketUrl} from "@/config/env";


    export default {
        components: {
            top,
            logo,
            tags,
            search,
            sidebar,
            setting,
            screenshot
        },
        name: "index",
        mixins: [index],
        provide() {
            return {
                index: this
            };
        },
        data() {
            return {
                //搜索控制
                isSearch: false
            };
        },
        mounted() {
            this.init();
        },
        computed: {
            ...mapGetters(["isHorizontal", "setting", "userInfo", "isRefresh", "isCollapse", "menu"]),
            validSidebar() {
                return !((this.$route.meta || {}).menu == false || (this.$route.query || {}).menu == 'false')
            }
        },
        props: [],
        methods: {
            // 屏幕检测
            init() {
                // 获取或更新字典
                this.dict.refreshDict();
                this.$store.commit("SET_SCREEN", admin.getScreen());
                window.onresize = () => {
                    setTimeout(() => {
                        this.$store.commit("SET_SCREEN", admin.getScreen());
                    }, 0);
                };
                this.initWebSocket();
            },
            //打开菜单
            openMenu(item = {}) {
                console.debug("--获取左侧菜单")
                this.$store.dispatch("GetMenu", item).then(data => {
                    if (data.length !== 0) {
                        this.$router.$avueRouter.formatRoutes(data, true);
                    }
                    //当点击顶部菜单做的事件
                    if (!validatenull(item)) {
                        let itemActive = {},
                            childItemActive = 0;
                        //vue-router路由
                        if (item.path) {
                            itemActive = item;
                        } else {
                            console.log(this.menu)
                            // if (this.menu[childItemActive].length == 0) {
                            //     itemActive = this.menu[childItemActive];
                            // } else {
                            //     itemActive = this.menu[childItemActive].children[childItemActive];
                            // }
                            if (this.menu[childItemActive].menus == null || this.menu[childItemActive].menus.length == 0) {
                                itemActive = this.menu[childItemActive];
                            } else {
                                itemActive = this.menu[childItemActive].menus[childItemActive];
                            }
                        }
                        console.debug("3")
                        this.$store.commit('SET_MENUID', item);
                        this.$router.push({
                            path: itemActive.path
                        });
                    }

                });
            },

            /* 以下是 websocket 处理*/
            initWebSocket: function () {
                if (typeof (WebSocket) === "undefined") {
                    alert("您的浏览器不支持socket")
                } else {
                    // 实例化socket
                    let webSocketUrl = baseWebSocketUrl.replace("{userId}", this.userInfo.id)
                        .replace("{fullName}", this.userInfo.fullName)
                        .replace("{head}", "untreated")
                    // console.log(webSocketUrl)
                    this.socket = new WebSocket(webSocketUrl);
                    // 监听socket连接
                    this.socket.onopen = this.onopen
                    // 监听socket错误信息
                    this.socket.onerror = this.onerror
                    // 监听socket消息
                    this.socket.onmessage = this.onmessage
                }
            },
            onmessage: function (evt) {
                console.log(evt)
                let received_msg = evt.data;           // 接收到的数据(str)
                let obj = JSON.parse(received_msg);    // 接收到的数据(json)
                // 解析数据
                let msgType = obj.msgType;             // 数据类型 (0-心跳检测 1-上线通知 2-下线通知 3 在线用户名单通知 4 推送消息 )
                let from = obj.from;                   // 来源Id，上下线时为上下线的用户id
                let username = obj.username;           // 来源用户，上下线时为上下线的用户名
                let to = obj.to;                       // 目标Id（接收人用户Id,逗号分隔,所有人时为-ALL)）
                let content = obj.content;             // 内容 (text/json 前后端协商)
                let extras = obj.extras;               // 扩展内容 (text/json 前后端协商)
                let createTime = obj.createTime;       // 消息时间
                // 信息接收通知
                if (msgType === 4) {
                    let msgData = JSON.parse(content);
                    const h = this.$createElement;
                    this.$notify({
                        title: this.dict.convert(this.website.Dict.Admin.MsgType, msgData.msgType),
                        message: h('i', {style: 'color: teal'}, msgData.content),
                        duration: 1000 * 10,
                        onClick: function () {
                            // 处理其他业务
                            console.debug("点击了信息")
                        }
                    });
                    // 播放提示音
                    this.$refs.audio.currentTime = 0; // 从头开始播放提示音
                    this.$refs.audio.play();          // 播放
                    //this.say2( msgData.content);
                }
            },
            onopen: function () {
                console.log("socket连接成功")
            },
            onerror: function () {
                console.log("连接错误")
            },
            onclose: function () {
                console.log("socket已经关闭")
            },
            say2(content) {
                const ssu = new SpeechSynthesisUtterance();
                ssu.text = content;
                ssu.lang = 'zh';
                ssu.rate = 0.8;
                speechSynthesis.speak(ssu);
            }
        },
        destroyed() {
            // 销毁监听
            this.socket.onclose = this.onclose
        },
    };
</script>
<style>
    .avue-tags__menu {
        z-index: 1 !important;
    }
</style>