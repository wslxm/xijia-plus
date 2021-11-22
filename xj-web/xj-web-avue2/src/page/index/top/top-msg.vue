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
             <span style="display:flex;margin-left: 5%;margin-top: -5%">
                 <el-tabs v-model="activeName" @tab-click="handleClick">
                   <el-tab-pane label="全部" name="all">
                      <ui>
			        	  <li v-for="(item, index) in obj">
			        		  索引-{{index}} ---- {{item}}
			        	  </li>
			          </ui>
                   </el-tab-pane>
                   <el-tab-pane label="已读" name="1">
                        <ui>
			        	  <li v-for="(item, index) in obj">
			        		  索引-{{index}} ---- {{item}}
			        	  </li>
			            </ui>
                   </el-tab-pane>
                   <el-tab-pane label="未读" name="0">
                         <ui>
			        	  <li v-for="(item, index) in obj">
			        		  索引-{{index}} ---- {{item}}
			        	  </li>
			            </ui>
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
    /deep/ .el-drawer__header {
        margin-bottom: 5px;
    }
</style>
