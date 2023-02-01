/**
 * 全站权限配置
 */
import router from './router/router'
import store from './store'
import {validatenull} from '@/util/validate'
import {getToken} from '@/util/auth'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import website from '@/config/website'

NProgress.configure({showSpinner: false});
const lockPage = website.lockPage;  //锁屏页

router.beforeEach((to, from, next) => {
    // console.debug("====路由跳转=======")

    // 是否加载左菜单
    const meta = to.meta || {};
    const isMenu = meta.menu === undefined ? to.query.menu : meta.menu;
    store.commit('SET_IS_MENU', isMenu === undefined);

    // 获取 路由地址
    const value = to.path;
    // 获取 路由名称
    const label = to.query.name || to.name;
    // 是否新窗口打开
    if (meta.target) {
        window.open(to.query.url.replace(/#/g, "&"))
        return
    }
    // 是否添加到 Tab
    if (!meta.target && meta.isTab !== false && !validatenull(value) && !validatenull(label)) {
        store.commit('ADD_TAG', {
            label: label,
            value: value,
            params: to.params,
            query: to.query,
            meta: to.meta
        });
    }

    /* 判断是否登录 */
    if (getToken()) {
        if (store.getters.isLock && to.path != lockPage) {
            // 如果系统激活锁屏，全部跳转到锁屏页
            next({path: lockPage})
        } else if (to.path === '/login') {
            // 如果是登录成功访问跳转到主页
            next({path: '/'})
        } else {
            // 如果用户信息为空则获取用户信息后正常跳转到点击的页面，如果获取用户信息失败，则跳转到登录页
            store.dispatch('GetUserInfo').then(() => {
                next()
            }).catch(() => {
                store.dispatch('FedLogOut').then(() => {
                    next({path: '/login'})
                })
            })
        }
    } else {
        // 判断是否需要登录认证, 需要登录认证没有登录访问去登录页, 不需要正常跳转即可
        if (meta.isAuth === false) {
            next()
        } else {
            next('/login')
        }
    }
})

router.afterEach(() => {
    NProgress.done();
    console.log("===========11")
    let title = store.getters.tag.label;
    let i18n = store.getters.tag.meta.i18n;
    title = router.$avueRouter.generateTitle(title, i18n)
    //根据当前的标签也获取label的值动态设置浏览器标题
    router.$avueRouter.setTitle(title);
});