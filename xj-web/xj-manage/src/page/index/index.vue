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
                <!--  <div class="avue-footer">-->
                <!--      <p class="copyright">© 2019-2021 xijia-plus</p>-->
                <!--  </div>-->
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
            // 初始化 webSocket
            this.$store.dispatch('webSocketInit');
        },
        computed: {
            ...mapGetters(["isHorizontal", "setting", "userInfo", "isRefresh", "isCollapse", "menu"]),
            validSidebar() {
                return !((this.$route.meta || {}).menu == false || (this.$route.query || {}).menu == 'false')
            }
        },

        props: [],
        watch: {
            // 监听 websocket 消息
            '$store.getters.socketMsgs': {
                //处理接收到的消息
                handler: function () {
                    let that = this
                    // websocket 封装的消息格式
                    let receivedMsg = that.$store.getters.socketMsgs.data;
                    console.log("接收到websocket消息: " + receivedMsg)
                    let obj = JSON.parse(receivedMsg);     // 接收到的数据(json)
                    // 解析数据
                    let from = obj.from;                   // 来源Id，上下线时为上下线的用户id
                    let username = obj.username;           // 来源用户，上下线时为上下线的用户名
                    let to = obj.to;                       // 目标Id（接收人用户Id,逗号分隔,所有人时为-ALL)）
                    let content = obj.content;             // 内容 (text/json 前后端协商)
                    let extras = obj.extras;               // 扩展内容 (text/json 前后端协商)
                    let createTime = obj.createTime;       // 消息时间
                    // 当前用户
                    // let loginUserId = getStore({name: "userInfo"}).id;
                    // console.log("当前用户id: " + loginUserId);
                    // 信息接收通知
                    // console.log("消息查看:" + content);
                    // 具体信息： 此层数据同数据库消息表数据
                    let adminMsgData = JSON.parse(content);

                    // 具体消息内容
                    let msgData = JSON.parse(adminMsgData.content);

                    // 右上角弹出消息
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
                    // 播放提示音 (如果需要)
                    // this.$refs.audio.currentTime = 0;  // 从头开始播放提示音
                    // this.$refs.audio.play();           // 播放
                    // this.say2( msgData.content);
                }
            }
        },
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
                        console.debug("=======3")
                        this.$store.commit('SET_MENUID', item);
                        this.$router.push({
                            path: itemActive.path
                        });
                    }

                });
            },
        },
        destroyed() {

        },
    };
</script>
<style>
    .avue-tags__menu {
        z-index: 1 !important;
    }

    /* 弹出消息,显示小手 */
    .el-notification__title {
        cursor: pointer;
    }
</style>