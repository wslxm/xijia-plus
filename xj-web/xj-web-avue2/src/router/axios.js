/**
 * 全站http配置
 *
 * axios参数说明
 * isSerialize是否开启form表单提交
 * isToken是否需要token
 */
import axios from 'axios'
import store from '@/store/';
import router from '@/router/router'
import {serialize} from '@/util/util'
import {getToken, setToken} from '@/util/auth'
import {setStore} from '@/util/store'
import {Message} from 'element-ui'
import {signAll} from '@/util/SignUtil'
import website from '@/config/website';
import NProgress from 'nprogress'  // progress bar
import 'nprogress/nprogress.css'
import {isEmpty} from "element-ui/src/utils/util"; // progress bar style
axios.defaults.timeout = 10000;


//返回其他状态吗
axios.defaults.validateStatus = function (status) {
    return status >= 200 && status <= 500; // 默认的
};


//跨域请求，允许保存cookie
axios.defaults.withCredentials = true;
// axios.defaults.baseURL = '/api'
// NProgress Configuration
NProgress.configure({
    showSpinner: false
});


//HTTP request拦截
axios.interceptors.request.use(config => {
    NProgress.start();  // start progress bar
    const meta = (config.meta || {});

    // 请求中添加token
    const isToken = meta.isToken === false;
    if (getToken() && !isToken) {
        // 让每个请求携带token--['Authorization']为自定义key 请根据实际情况自行修改
        config.headers[website.Authorization] = getToken()
    }

    //  headers中配置serialize为true开启序列化
    if (config.method === 'post' && meta.isSerialize === true) {
        config.data = serialize(config.data);
    }

    // 参数加签
    let timestamp = new Date().getTime();
    let sign = signAll(config.url,  config.params, config.data, timestamp)
    config.headers['timestamp'] = `${timestamp}`;
    config.headers['sign'] = `${sign}`;
    return config;
}, error => {
    return Promise.reject(error)
});



//HTTP response拦截
axios.interceptors.response.use(res => {
    console.debug(res.config.url, "  =》 res:", res)
    NProgress.done();
    const status = Number(res.status) || 200;
    const statusWhiteList = website.statusWhiteList || [];
    const message = res.data.message || '后台服务走丢了,请稍后重试...';
    // 如果在白名单里则自行catch逻辑处理
    if (statusWhiteList.includes(status)) return Promise.reject(res);
    // 如果是401则跳转到登录页面
    if (status === 401) store.dispatch('FedLogOut').then(() => router.push({path: '/login'}));
    // 如果请求为非200否者默认统一处理
    if (status !== 200) {
        Message({
            message: message,
            type: 'error'
        });
        return Promise.reject(new Error(message))
    }
    // 判断是否是文件，是文件就直接返回
    if ((res.data != null && res.data instanceof Blob)
        || (res.headers["content-type"] != null && res.headers["content-type"].indexOf('application/octet-stream') != -1)) {
        return res;
    }
    //==================================================
    //==================统一处理业务code码================
    //==================================================
    if (res.data.code !== 200) {
        // 请求数据失败
        // 返回 10000 表示码云登录,跳登录页
        if (res.data.code === 10000) {
            Message({
                message: res.data.msg,
                type: 'error'
            });
            // 退出登录
            store.dispatch("LogOut").then(() => {
                router.push({path: "/login"});
            });
            return Promise.reject(new Error(res.data.msg))
        } else {
            // 非10000直接抛后台给的错误提示信息
            Message({
                message: res.data.msg,
                type: 'error'
            });
            return Promise.reject(new Error(res.data.msg))
        }
    } else {
        // 请求数据成功
        if (res.config != null && res.config.method !== "get" && res.config.method !== "GET") {
            // 成功提示
            Message({
                message: res.data.msg,
                type: 'success'
            })
        }
        // 自动刷新 token 来进行续期
        if (res.headers.token !== undefined && res.headers.token !== null) {
            let newToken = res.headers.token;
            setToken(newToken);
            //setStore({name: 'token', content: newToken})
        }
    }
    return res;
}, error => {
    NProgress.done();
    return Promise.reject(new Error(error));
});

export default axios;