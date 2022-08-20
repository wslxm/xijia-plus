<template>
    <div>
        <span @click="drawer=true">
             <el-badge :value="allNum.unreadNum" :max="99">
                <i class="el-icon-bell"></i>
            </el-badge>
        </span>
        <!-- show-close=true-->
        <!-- wrapperClosable=true-->
        <el-drawer style="padding-top: 3.2%"
                   title="通知列表"
                   size="18%"
                   direction="rtl"
                   :visible.sync="drawer"
                   :with-header="true">
             <span style="display:flex; margin-left: 5%;margin-top: -2%">
                 <el-tabs style="width: 100%" v-model="activeName" @tab-click="handleClick">
                     <!-- -->
                     <el-tab-pane label="全部" name="all">
                         <div v-for="(item) in obj">
                              <div style="margin-top: -0%">
                                     <el-badge :hidden="item.isRead === 1" is-dot class="item">
                                           <span class="msgTitle" @click="clickMsg(item)">{{ JSON.parse(item.content).title }} </span>
                                    </el-badge>
                                 <span class="msgTime">{{item.createTime}} </span>
                              </div>
                              <div class="msgContent">{{JSON.parse(item.content).message}}</div>
                              <el-divider></el-divider>
                         </div>
                     </el-tab-pane>
                     <!---->
                     <el-tab-pane label="已读" name="1">
                          <div v-for="(item) in obj">
                               <div style="margin-top: -3%">
                                 <el-badge :hidden="item.isRead === 1" is-dot class="item">
                                       <span class="msgTitle" @click="clickMsg(item)">{{ JSON.parse(item.content).title }} </span>
                                 </el-badge>
                                 <span class="msgTime"> {{item.createTime}} </span>    <!--{{item.isRead}}-->
                               </div>
                               <div class="msgContent">{{JSON.parse(item.content).message}}</div>
                               <el-divider></el-divider>
                          </div>
                     </el-tab-pane>
                     <!---->
                     <el-tab-pane label="未读" name="0">
                           <div v-for="(item) in obj">
                               <div style="margin-top: -3%" @click="clickMsg(item)">
                                   <el-badge :hidden="item.isRead == 1" is-dot class="item">
                                         <span class="msgTitle" @click="clickMsg(item)">{{ JSON.parse(item.content).title }} </span>
                                   </el-badge>
                                   <span class="msgTime"> {{item.createTime}} </span>    <!--{{item.isRead}}-->
                               </div>
                               <div class="msgContent">{{JSON.parse(item.content).message}}</div>
                               <el-divider></el-divider>
                           </div>
                     </el-tab-pane>
                 </el-tabs>
             </span>
            <div>
                <span style="font-size: 12px;margin-left: 5%"> 共 {{page.total}} 条 | 共 {{page.pages}} 页  </span>
                <span style="margin-left: 2%">  <el-button size="mini" @click="prevHandle()">上一页</el-button> </span>
                <span style="margin-left: 2%"> {{page.current}} </span>
                <span style="margin-left: 2%">  <el-button size="mini" @click="nextHandle()">下一页</el-button> </span>

            </div>
        </el-drawer>
    </div>
</template>

<script>
    import router from '@/router/router'
    export default {
        name: "top-msg",
        data() {
            return {
                uri: {
                    infoList: "/api/admin/xj/msg/findPage",
                    findAllNum: "/api/admin/xj/msg/findAllNum", //查询已读/未读/使用数量
                    read: "/api/admin/xj/msg/{id}/read", //修改为已读
                },
                page: {
                    current: 1, //当前页
                    size: 5,    //每页条数
                    total: 0,   //总条数
                    pages: 0,   //总页数
                },
                activeName: "all",
                drawer: false,
                obj: [],
                allNum: {},
            }
        },
        // beforeUpload : function () {
        //     this.handleClick();
        // },
        created() {
            // 首次进入加载数据
            // this.handleClick();
            // 数量查询
            this.crud.get(this.uri.findAllNum).then((res) => {
                this.allNum = res.data.data;
            })
        },
        watch: {
            //newNum = 新值，旧值
            drawer: function (newDrawer, oldDrawer) {
                // console.log(newDrawer,oldDrawer);
                if (newDrawer) {
                    this.handleClick();
                }
            }
        },

        methods: {
            /**
             * 查询
             */
            handleClick() {
                let isRead = "";
                if (this.activeName !== "all") {
                    isRead = this.activeName;
                }
                this.crud.get(this.uri.infoList, {current: this.page.current, size: this.page.size, isRead: isRead}).then((res) => {
                    this.obj = res.data.data.records;
                    this.page.current = res.data.data.current;
                    this.page.size = res.data.data.size;
                    this.page.total = res.data.data.total;
                    this.page.pages = res.data.data.pages;
                });
            },
            // 上一页
            prevHandle() {
                if (this.page.current - 1 > 0) {
                    this.page.current = this.page.current - 1;
                }
                this.handleClick();
            },
            // 下一页
            nextHandle() {
                if (this.page.current + 1 <= this.page.pages) {
                    this.page.current = this.page.current + 1;
                }
                this.handleClick();
            },
            // 点击消息
            clickMsg(item) {
                if (item.isRead === 0) {
                    // 修改为已读
                    this.crud.put(this.uri.read.replace("{id}", item.id), {isRead: 1}).then((res) => {
                        // 刷新当前列表
                        this.handleClick();
                        this.findAllNum();
                    });
                }

                // 跳转路由
                let routePath = JSON.parse(item.content).routePath;
                console.log("跳转路由:" + routePath);
                if (routePath != null && routePath !== "") {
                    router.push({path: routePath + "&time=" + new Date().getTime()});
                }
            },
            /**
             * 查询消息数量
             */
            findAllNum() {
                // 更新数量
                this.crud.get(this.uri.findAllNum).then((res) => {
                    this.allNum = res.data.data;
                })
            }

        }
    };
</script>

<style lang="scss" scoped>
    /* 设置弹层标题和内容的间隔 */
    /deep/ .el-drawer__header {
        margin-bottom: 5px;
    }

    /* 内容 */
    .msgContent {
        margin-right: 5%;
        margin-top: -7%;
        font-size: 13px;
        white-space: normal;
        //word-break: break-all;
        //word-wrap: break-word;
        line-height: 1.5
    }

    /* 标题 */
    .msgTitle {
        font-size: 15px;
        font-weight: bold;
        cursor: pointer;
        //font-style: italic;
    }

    /* 时间 */
    .msgTime {
        font-size: 14px;
        height: 20px;
        color: #dac5cc;
        float: right;
        margin-right: 5%
    }
</style>
