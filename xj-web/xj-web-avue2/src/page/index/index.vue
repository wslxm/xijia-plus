<template>
    <div class="avue-contail"
         :class="{'avue--collapse':isCollapse,}">
        <screenshot v-if="setting.screenshot"></screenshot>
        <setting></setting>
        <div class="avue-layout"
             :class="{'avue-layout--horizontal':isHorizontal}">
            <div class="avue-sidebar"
                 v-show="validSidebar">
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
                <div class="avue-footer">
                    <p class="copyright">© 2019-2021 spring-boot-plus2</p>
                </div>
            </div>
        </div>

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
            ...mapGetters(["isHorizontal", "setting", "isRefresh", "isCollapse", "menu"]),
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
        }
    };
</script>
<style>
    .avue-tags__menu{
        z-index: 1!important;
    }
</style>