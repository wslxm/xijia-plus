// vue
import Vue from 'vue';
import axios from './router/axios';
import VueAxios from 'vue-axios';
import App from './App';
import router from './router/router';
import store from './store';
import dayjs from 'dayjs'
// avue
import basicContainer from './components/basic-container/main'; // base容器
import basicBlock from './components/basic-block/main'          // base块
import website from '@/config/website' ;                        // 当前系统配置数据
import defaultdata from '@/config/defaultdata' ;                  // 相关默认数据
import {iconfontUrl, iconfontVersion} from '@/config/env';      // 阿里图标地址
import icon from "@/util/icon";                                 // 系统图标
import {loadStyle} from './util/util';                          // 动态插入css方法
import * as urls from '@/config/env';                           // 服务器请求连接地址配置


import './permission';                      // 权限
import './error';                           // 日志
import './cache';                           // 页面缓冲
import i18n from './lang'                   // 国际化
// 自定义
import '@/util/dialogdrag';                 // 弹出层拖拽插件
import crud from '@/util/crud'              // 通用crud/文件下载等方法
import dict from '@/util/dict'              // 字典
import './styles/common.scss';              // 通用css
const Base64 = require('js-base64').Base64; // base64工具(其他地方可直接使用Base64方法)

// avue + element-ui
import 'element-ui/lib/theme-chalk/index.css';
import '@smallwei/avue/lib/index.css'
import Element from 'element-ui';
import AVUE from '@smallwei/avue'

// 注入
Vue.prototype.$dayjs = dayjs;
Vue.prototype.icon = icon;
Vue.prototype.website = website;
Vue.prototype.defaultdata = defaultdata;
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
}).$mount('#app');


// tinymce 富文本插件， vueTinymceEditor为当前项目二次封装
import tinymce from 'tinymce'
import VueTinymce from '@packy-tang/vue-tinymce'

Vue.prototype.$tinymce = tinymce;
Vue.use(VueTinymce);
import vueTinymceEditor from '@/components/vue-tinymce/index';

Vue.component('TinymceEditor', vueTinymceEditor);

// v-md-editor 插件
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';

// github 主题
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// 代码高亮引入所有语言包
import hljs from 'highlight.js';

VueMarkdownEditor.use(githubTheme, {
    Hljs: hljs,
});
Vue.use(VueMarkdownEditor);

// tip 提示插件
import createTipPlugin from '@kangc/v-md-editor/lib/plugins/tip/index';
import '@kangc/v-md-editor/lib/plugins/tip/tip.css';
// 代码行号插件 +  内容定位
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import createAlignPlugin from '@kangc/v-md-editor/lib/plugins/align';
// 表情
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';

VueMarkdownEditor.use(createTipPlugin());
VueMarkdownEditor.use(createLineNumbertPlugin());
VueMarkdownEditor.use(createAlignPlugin());
VueMarkdownEditor.use(createEmojiPlugin());


// v-md-editor 插件二次封装
import mdEditor from '@/components/v-md-editor/index';
Vue.component('MdEditor', mdEditor);
