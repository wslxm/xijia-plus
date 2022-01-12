<template>
    <el-scrollbar class="avue-menu"
                  v-if="reload">
        <div v-if="validatenull(menu)&&!isHorizontal"
             class="avue-sidebar--tip">{{$t('menuTip')}}
        </div>
        <el-menu unique-opened
                 :default-active="nowTagValue"
                 :mode="setting.sidebar"
                 :collapse="keyCollapse">
            <!-- 子菜单项 -->
            <template v-for="item in menu">
                <el-menu-item v-if="validatenull(item[childrenKey]) && validRoles(item)"
                              :index="getPath(item)"
                              @click="open(item)"
                              :key="item[labelKey]">
                    <i :class="item[iconKey]"></i>
                    <span slot="title"
                          :alt="item[pathKey]">{{getTitle(item)}}</span>
                </el-menu-item>
                <el-submenu v-else-if="!validatenull(item[childrenKey])&&validRoles(item)"
                            :index="getPath(item)"
                            :key="item[labelKey]">
                    <template slot="title">
                        <i :class="item[iconKey]"></i>
                        <span slot="title"
                              :class="{'el-menu--display':keyCollapse}">{{getTitle(item)}}</span>
                    </template>
                    <template v-for="(child,cindex) in item[childrenKey]">
                        <el-menu-item :index="getPath(child)"
                                      @click="open(child)"
                                      v-if="validatenull(child[childrenKey])"
                                      :key="child[labelKey]">
                            <i :class="child[iconKey]"></i>
                            <span slot="title">{{getTitle(child)}}</span>
                        </el-menu-item>
                        <sidebar-item v-else
                                      :menu="[child]"
                                      :key="cindex"></sidebar-item>
                    </template>
                </el-submenu>
            </template>
        </el-menu>
    </el-scrollbar>
</template>

<script>

    import {mapGetters} from "vuex";
    import {validatenull} from "@/util/validate";

    export default {
        name: "sidebar",
        inject: ["index"],
        data() {
            return {
                reload: true,
                props: this.website.menu
            };
        },
        created() {
            this.index.openMenu()
        },
        watch: {
            isHorizontal() {
                this.reload = false;
                this.$nextTick(() => {
                    this.reload = true;
                })
            }
        },
        computed: {
            ...mapGetters(["roles", "screen", "isHorizontal", "setting", "menu", "tag", "keyCollapse", "menuId"]),
            nowTagValue() {
                return this.$route.path;
            },
            labelKey() {
                return this.props.label
            },
            pathKey() {
                return this.props.path
            },
            queryKey() {
                return this.props.query
            },
            iconKey() {
                return this.props.icon;
            },
            childrenKey() {
                return this.props.children;
            },
        },
        methods: {
            getPath(item) {
                return item[this.pathKey] + ''
            },
            getTitle(item) {
                return this.$router.$avueRouter.generateTitle(
                    item[this.labelKey],
                    item.meta.i18n
                );
            },
            validRoles(item) {
                item.meta = item.meta || {};
                return item.meta.roles ? item.meta.roles.includes(this.roles) : true;
            },
            validatenull(val) {
                return validatenull(val);
            },
            open(item) {
                if (this.screen <= 1) this.$store.commit("SET_COLLAPSE");
                this.$router.push({
                    path: item[this.pathKey],
                    query: item[this.queryKey]
                });
            }
        }
    };
</script>
<style lang="scss" scoped>
</style>

