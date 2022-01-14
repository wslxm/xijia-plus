import website from '@/config/website'

function isURL(s) {
    return /^http[s]?:\/\/.*/.test(s)
}

let RouterPlugin = function () {
    this.$router = null;
    this.$store = null;
};
RouterPlugin.install = function (option = {}) {
    this.$router = option.router;
    this.$store = option.store;
    let i18n = option.i18n
    this.$router.$avueRouter = {
        safe: this,
        // 设置标题l
        setTitle: (title) => {
            const defaultTitle = i18n.t('title');
            title = title ? `${title}——${defaultTitle}` : defaultTitle;
            document.title = title;
        },
        closeTag: (value) => {
            let tag = value || this.$store.getters.tag;
            if (typeof value === 'string') {
                tag = this.$store.getters.tagList.filter(ele => ele.value === value)[0]
            }
            this.$store.commit('DEL_TAG', tag)
        },
        generateTitle: (title, key) => {
            if (!key) return title;
            const hasKey = i18n.te('route.' + key)
            if (hasKey) {
                const translatedTitle = i18n.t('route.' + key)
                return translatedTitle
            }
            return title
        },
        //动态路由
        formatRoutes: function (menus = []) {
            // 获取所有路由
            let addRoutes = [];
            this.nextRoute(menus, addRoutes);
            // 注册
            //console.debug("路由数量：", addRoutes.length)
            addRoutes.forEach((router) => {
                this.safe.$router.addRoute(router)
            })
        },
        nextRoute(menus, addRoutes) {
            for (let i = 0; i < menus.length; i++) {
                let url = menus[i].url;
                let name = menus[i].name;
                let icon = menus[i].icon;
                let root = menus[i].root;
                if (root == 3) {
                    // 添加页面路由
                    addRoutes.push(this.addRoute(url, name, icon));
                }
                if (menus[i].menus != null && menus[i].menus.length > 0) {
                    // 递归
                    this.nextRoute(menus[i].menus, addRoutes);
                }
            }
        },
        /**
         * 添加到路由方法
         * @param url
         * @param name
         * @param icon
         */
        addRoute(url, name, icon) {
            let component = url;
            for (let i = 0; i < 5; i++) {
                component = component.substring(0, 1) === "/" ? component.substring(1) : component;
            }
            console.debug("动态路由注册：[" + name + "] [" + component + "][" + icon + "]")
            let router = {
                path: "/",
                component: () => import('@/page/index/index.vue'),
                // redirect: '/wel/index',
                children: [{
                    path: component,
                    component(resolve) {
                        require([`../${component}.vue`], resolve)
                    },
                    meta: {
                        keepAlive: true,
                        isTab: true,
                        isAuth: false,
                    },
                    name: name,
                    icon: icon,
                    query: null
                }]
            }
            return router;
        }
    }
}


export const formatPath = (ele, first) => {
    const propsDefault = website.menu;

    // 空path处理，path=随机时间戳 2022-1-14 新增
    if (ele[propsDefault.path] == null || ele[propsDefault.path] === "") {
        ele[propsDefault.path] =  ele.id;
    }

    const icon = ele[propsDefault.icon];
    ele[propsDefault.icon] = !icon ? propsDefault.iconDefault : icon;
    ele.meta = ele.meta || {}
    const iframeComponent = 'components/iframe/main';
    const iframeSrc = (href) => {
        return href.replace(/&/g, "#")
    }
    const isChild = ele[propsDefault.children] && ele[propsDefault.children].length !== 0;
    if (!isChild && first && !isURL(ele[propsDefault.path])) {
        ele[propsDefault.path] = ele[propsDefault.path]
        if (isURL(ele[propsDefault.href])) {
            let href = ele[propsDefault.href]
            ele.component = iframeComponent
            ele[propsDefault.query] = {url: iframeSrc(href)}
        }
    } else {
        ele[propsDefault.children] && ele[propsDefault.children].forEach(child => {
            if (isURL(child[propsDefault.href])) {
                let href = child[propsDefault.href]
                child.component = iframeComponent
                child[propsDefault.query] = {url: iframeSrc(href)}
            }
            // child[propsDefault.path] = `${ele[propsDefault.path]}/${child[propsDefault.path]}`
            child[propsDefault.path] = `${child[propsDefault.path]}`
            formatPath(child);
        })
    }
}
export default RouterPlugin;