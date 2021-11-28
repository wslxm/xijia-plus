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
             <span style="display:flex; margin-left: 5%;margin-top: -5%">
                 <el-tabs style="width: 100%" v-model="activeName" @tab-click="handleClick">
                     <!-- -->
                     <el-tab-pane label="全部" name="all">
                         <div v-for="(item) in obj">
                              <div style="margin-top: -3%">
                                 <span class="msgTitle">{{dict.convert(website.Dict.Admin.MsgType, item.msgType) }} </span>
                                 <span class="msgTime"> {{item.createTime}} </span>    <!--{{item.isRead}}-->
                              </div>
                              <div class="msgContent">{{item.content}}</div>
                              <el-divider></el-divider>
                         </div>
                     </el-tab-pane>
                     <!---->
                     <el-tab-pane label="已读" name="1">
                          <div v-for="(item) in obj">
                               <div style="margin-top: -3%">
                                 <span class="msgTitle">{{dict.convert(website.Dict.Admin.MsgType, item.msgType) }} </span>
                                 <span class="msgTime"> {{item.createTime}} </span>    <!--{{item.isRead}}-->
                               </div>
                               <div class="msgContent">{{item.content}}</div>
                               <el-divider></el-divider>
                          </div>
                     </el-tab-pane>
                     <!---->
                     <el-tab-pane label="未读" name="0">
                           <div v-for="(item) in obj">
                               <div style="margin-top: -3%">
                                 <span class="msgTitle">{{dict.convert(website.Dict.Admin.MsgType, item.msgType) }} </span>
                                 <span class="msgTime"> {{item.createTime}} </span>    <!--{{item.isRead}}-->
                               </div>
                               <div class="msgContent">{{item.content}}</div>
                               <el-divider></el-divider>
                           </div>
                     </el-tab-pane>
                 </el-tabs>
             </span>
        </el-drawer>
    </div>
</template>

<script>

    export default {
        name: "top-msg",
        data() {
            return {
                uri: {
                    infoList: "/api/admin/xj/msg/list",
                    findAllNum: "/api/admin/xj/msg/findAllNum", //查询已读/未读/使用数量
                },
                activeName: "all",
                drawer: false,
                obj: [],
                allNum: {},
            }
        },
        created() {
            this.crud.get(this.uri.infoList).then((res) => {
                this.obj = res.data.data.records;
            })
            this.crud.get(this.uri.findAllNum).then((res) => {
                this.allNum = res.data.data;
            })
        },
        methods: {
            handleClick() {
                let isRead = "";
                if (this.activeName != "all") {
                    isRead = this.activeName;
                }
                this.crud.get(this.uri.infoList, {isRead: isRead}).then((res) => {
                    this.obj = res.data.data.records;
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
        margin-top: -0%;
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
        font-style: italic;
    }

    /* 时间 */
    .msgTime {
        font-size: 14px;
        height: 20px;
        color: #dad8d5;
        float: right;
        margin-right: 5%
    }
</style>
