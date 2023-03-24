<template>
  <div>
    <el-card>
      <avue-crud ref="crudGcTest"
                 :data="data"
                 :option="option"
                 :page.sync="page"
                 :search.sync="search"
                 :table-loading="loading"
                 :cell-style="cellStyle"
                 @on-load="onLoad"
                 @refresh-change="onLoad"
                 @search-change="searchChange"
                 @row-click="handleRowClick">
        <!-- 启用/禁用插槽(默认提供,按需使用) -->
        <template slot-scope="{row,index,type,size}" slot="disable">
          <el-switch v-model="row.disable" @change="updDisable(row)"
                     active-color="#13ce66" inactive-color="#ff4949"
                     :active-value=0 :inactive-value=1
                     active-text="" inactive-text="">
          </el-switch>
        </template>

        <template slot-scope="{row,index,type,size}" slot="timeSearch">
          <div class="block">
            <el-date-picker
                v-model="search.time"
                value-format="yyyy-MM-dd HH:mm:ss"
                type="datetimerange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="defaultdata.timeOptions">
            </el-date-picker>
          </div>
        </template>

        <template slot-scope="{}" slot="menuLeft">
          <el-button type="primary" icon="el-icon-plus" size="small" plain @click="addDialogVisible = true">新增</el-button>
        </template>
        <template slot-scope="{row,index,type,size}" slot="menu">
          <el-button icon="el-icon-edit" :size="size" :type="type" @click="updRow(row,1)">编辑</el-button>
          <el-button icon="el-icon-delete" :size="size" :type="type" @click="rowDel(row,index)">删除</el-button>
        </template>
      </avue-crud>
      <!-- 弹层 -->
      <el-dialog title="新增" v-dialogDrag v-if="addDialogVisible" :visible.sync="addDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
        <Add :closeDialog="closeDialog" :uri="uri"></Add>
        <span slot="footer" class="dialog-footer"></span>
      </el-dialog>
      <el-dialog title="编辑" v-dialogDrag v-if="updDialogVisible" :visible.sync="updDialogVisible" :width="dialogWidth" top="6vh" @close="closeDialog">
        <Upd :closeDialog="closeDialog" :uri="uri" :rowData="rowData"></Upd>
        <span slot="footer" class="dialog-footer"></span>
      </el-dialog>
    </el-card>
  </div>
</template>


<script>
export default {
  components: {
    Add: () => import('./gcTestAdd'),
    Upd: () => import('./gcTestUpd')
  },
  data() {
    return {
      uri: {
        infoList: "/api/admin/test/gcTest/findPage",
        info: "/api/admin/test/gcTest",
      },
      loading: true,
      dialogWidth: "60%",
      addDialogVisible: false,
      updDialogVisible: false,
      page: this.website.pageParams,
      search: {},
      data: [],
      rowData: {},
      option: {},
    }
  },
  async mounted() {
    this.option = JSON.parse(JSON.stringify(this.website.optionConfig));
    this.option.column = [
      {label: '名称 ',prop: 'name',search: false,searchSpan: 5, overHidden: true},
      {label: '年龄 ',prop: 'age',search: false,searchSpan: 5, overHidden: true},
      {
        label: '性别 ', prop: 'sex',search: false,searchSpan: 5, overHidden: true, type: 'select', filterable:true,
        dicData: this.dict.get(this.website.Dict.Base.Default),
      },
      {
        label: '爱好 ',prop: 'like', search: false, searchSpan: 5,overHidden: true, type: 'select', dataType: 'string',
        dicData: this.dict.get(this.website.Dict.Base.Default),
      },
      {
        label: '城市 ', prop: 'city',search: false,searchSpan: 5, overHidden: true, type: 'select', filterable:true,
        dicData: this.dict.get(this.website.Dict.Base.Default),
      },
      {
        label: '禁用 ', prop: 'disable',search: false,searchSpan: 5, overHidden: true, type: 'select', filterable:true,
        dicData: this.dict.get(this.website.Dict.Base.Default),
      },
      {
        label: '单图文件 ',prop: 'headUrl', search: false, overHidden: true, html: true,
        formatter: (val) => {
          if(val.headUrl == null || val.headUrl == ''){
            return "";
          }else{
            let imgs = val.headUrl.split(",");
            let html = "";
            imgs.forEach(item => html += "<img src='" + item + "'  style='border-radius: 40px;height: 40px;width: 40px;margin-top: 10px'>")
            return html;
          }
        }
      },
      {
        label: '多图文件  ',prop: 'headFiles', search: false, overHidden: true, html: true,
        formatter: (val) => {
          if(val.headFiles == null || val.headFiles == ''){
            return "";
          }else{
            let imgs = val.headFiles.split(",");
            let html = "";
            imgs.forEach(item => html += "<img src='" + item + "'  style='border-radius: 40px;height: 40px;width: 40px;margin-top: 10px'>")
            return html;
          }
        }
      },
      {label: '视频文件 ',prop: 'videoFiles',search: false,searchSpan: 5, overHidden: true},
      {label: '任意文件 ',prop: 'files',search: false,searchSpan: 5, overHidden: true},
      {label: '时间 ',prop: 'time',search: false,searchSpan: 7, overHidden: true},
      {
        label: '时间-小时 ', prop: 'timeTwo', search: false, searchSpan: 5, overHidden: true, type: 'time',
        pickerOptions:{
          start: '06:00',
          step: '00:30',
          end: '23:00'
        }
      },
      {label: '更多信息-text ',prop: 'text',search: false,searchSpan: 5, overHidden: true},
      {
        label: '级联选择器', prop: 'cascader', search: false, searchSpan: 5, overHidden: true, type: 'cascader',dataType: 'string',
        filterable: true,
        dicData: this.defaultdata.dicData,
        props: {
          value: "id",
          label: "name",
          children: "children"
        }
      },
      {label: '数组框 ',prop: 'array',search: false,searchSpan: 5, overHidden: true},
      {
        label: '图标  ', prop: 'icon', html: true,
        formatter: (val) => {
          return '<i class=' + val.icon + '></i>'
        }
      },
      {label: '颜色选择器',  prop: 'color', type: 'color', overHidden: true, colorFormat: 'hex', showAlpha: false},
      {label: '地址选择器', prop: 'map',overHidden: true, html: true,
        formatter: (val) => {
          if (val.map != null && val.map.split(',').length >= 3) {
            return val.map.split(',')[2];
          }
          return ''
        }
      },

    ]
  },
  created() {
  },
  activated: function () {
    this.crud.doLayout(this, this.$refs.crudGcTest)
  },
  methods: {
    onLoad() {
      this.crud.list(this,true);
      this.crud.doLayout(this, this.$refs.crudGcTest)
    },
    searchChange(params,done) {
      console.debug(params)
      this.page.currentPage = 1;
      this.onLoad();
      done();
    },
    closeDialog(isRefresh) {
      this.addDialogVisible = false;
      this.updDialogVisible = false;
      this.rowData = {};
      if (isRefresh != null && isRefresh) {
        this.onLoad();
      }
    },
    updRow(row, type) {
      this.rowData = row;
      switch (type) {
        case 1:
          this.updDialogVisible = true;
          break;
        default:
          this.$message.error('操作类型错误');
          break;
      }
    },
    rowDel(row, index) {
      this.crud.delRow(this, this.uri.info, row.id, index);
    },
    // 启用/禁用
    updDisable(row) {
      this.crud.put(this.uri.info + "/" + row.id, {disable: row.disable});
    },
    // 点击保存行数据(供行操作的任意地方获取数据)
    handleRowClick(row) {
      this.rowData = JSON.parse(JSON.stringify(row));
    },
    cellStyle({row, column}) {
      if (column.property == "disable") {
        // fontWeight: 'bold',fontSize: '20'
        return row.disable == 0 ? {color: 'green'} : {color: 'red'}
      }
    }
  }
}
</script>