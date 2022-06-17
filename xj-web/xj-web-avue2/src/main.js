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

/* 富文本*/
import tinymce from 'tinymce'
import VueTinymce from '@packy-tang/vue-tinymce'
Vue.prototype.$tinymce = tinymce;
Vue.use(VueTinymce);
/* 富文本二次封装  */
import vueTinymceEditor from '@/components/vue-tinymce/index';
Vue.component('TinymceEditor', vueTinymceEditor);


/*  =====================================*/
/*  =====  v-md-editor 编辑器 start  ==== */
/*  =====================================*/
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});

// v-md-editor 编辑器 代码高亮
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// 引入所有语言包
import hljs from 'highlight.js';

VueMarkdownEditor.use(githubTheme, {
  Hljs: hljs,
});

Vue.use(VueMarkdownEditor);
/*  =====================================*/
/*  =====  v-md-editor 编辑器 end  ====*/
/*  =====================================*/


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