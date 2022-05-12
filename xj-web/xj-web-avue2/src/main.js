import Vue from 'vue';
import axios from './router/axios';
import VueAxios from 'vue-axios';
import App from './App';
import router from './router/router';
import './permission'; // 权限
import './error'; // 日志
import './cache'; // 页面缓冲
import '@/util/dialogdrag'

import store from './store';
import { loadStyle } from './util/util'
import * as urls from '@/config/env';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import AVUE from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'

import { iconfontUrl, iconfontVersion } from '@/config/env';
import i18n from './lang' // Internationalization
import './styles/common.scss';
import basicBlock from './components/basic-block/main'
import basicContainer from './components/basic-container/main'
import crudCommon from '@/mixins/crud.js'
import dayjs from 'dayjs'
import website from '@/config/website'
// 通用crud/文件下载等方法
import crud from '@/util/crud'
// 字典
import dict from '@/util/dict'
// base64 工具(其他地方可直接使用Base64方法)
const Base64 = require('js-base64').Base64;





// 富文本 css
// 一定要引入这三个css，不然文本编辑器会出现不规则黑白几何图形
// 这三个css可以在main.js中引入，也可以在具体使用的.vue文件中引入，建议在main.js中引用。因为在添加quill-image-resize时发现在组件中引用报错，暂时没有发现问题出现在哪里
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

// 富文本 组件注册
import VueQuillEditor from 'vue-quill-editor'
Vue.use(VueQuillEditor);

// 富文本 组件二次封装注册
import xjQuillEditor from '@/components/vue-quill-editor/index';
Vue.component('xjQuillEditor', xjQuillEditor);


import tinymce from 'tinymce'
import VueTinymce from '@packy-tang/vue-tinymce'

Vue.prototype.$tinymce = tinymce;
Vue.use(VueTinymce);



window.$crudCommon = crudCommon;
Vue.prototype.$dayjs = dayjs;
Vue.prototype.website = website;
Vue.prototype.crud = crud;
Vue.prototype.dict = dict;
Vue.config.productionTip = false;
Vue.use(VueAxios, axios);
Vue.use(Element, {
  i18n: (key, value) => i18n.t(key, value)
});
Vue.use(AVUE, {
  i18n: (key, value) => i18n.t(key, value)
});
//注册全局容器
Vue.component('basicContainer', basicContainer);
Vue.component('basicBlock', basicBlock);

// 加载相关url地址
Object.keys(urls).forEach(key => {
  Vue.prototype[key] = urls[key];
});
// 动态加载阿里云字体库
iconfontVersion.forEach(ele => {
  loadStyle(iconfontUrl.replace('$key', ele));
});

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')