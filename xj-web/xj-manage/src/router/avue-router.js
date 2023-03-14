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
    let i18n = option.i18n;
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
                // 判断是否为 外部网址
                let component = null;
                let query = {};
                if (menus[i].component != null) {
                    // 外部网址 直接使用 component
                    component = menus[i].component;
                } else {
                    // 内部网址 使用 url 去掉第一个 / 后的地址 (对应文件目录),
                    component = url.substring(1);
                    // 去除 ? 后的参数(路由 url 上的参数仍然携带)
                    let urlArray = url.split("?");
                    url = urlArray[0];
                    // url = urlArray[0] + "/" + this.generateUUID();
                    //
                    component = component.split("?")[0];
                    // ? 后的参数转对象
                    if (urlArray.length > 1) {
                        let params = new URLSearchParams(urlArray[1]);
                        params.forEach((value, key) => {
                            query[key] = value;
                        });
                    }
                }
                if (root === 3) {
                    // 添加页面路由 (root=3 表示页面)
                    addRoutes.push(this.addRoute(url, component, name, icon, query));
                }
                if (menus[i].menus != null && menus[i].menus.length > 0) {
                    // 递归
                    this.nextRoute(menus[i].menus, addRoutes);
                }
            }
        },
        generateUUID() {
            var d = new Date().getTime();
            if (window.performance && typeof window.performance.now === "function") {
                d += performance.now(); //use high-precision timer if available
            }
            var uuid = 'xxxxxxxxxyxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = (d + Math.random() * 16) % 16 | 0;
                d = Math.floor(d / 16);
                return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
            });
            // console.log(uuid);
            return uuid;
        },
        /**
         * 添加到路由方法
         * @param path 路由地址
         * @param component 页面地址
         * @param name 页面名称
         * @param icon 页面图标
         * @param query 请求参数
         */
        addRoute(path, component, name, icon, query) {
            console.debug("动态路由注册：[" + name + "] [" + path + "] [" + component + "][" + icon + "][" + JSON.stringify(query) + "]");
            let router = {
                path: "/",
                component: () => import('@/page/index/index.vue'),
                // redirect: '/wel/index',
                children: [{
                    path: path,
                    component(resolve) {
                        require([`../${component}.vue`], resolve)
                    },
                    meta: {
                        keepAlive: false,
                        isTab: true,
                        isAuth: false,
                    },
                    name: name,
                    icon: icon,
                    query: query
                }]
            };
            return router;
        }
    }
}


export const formatPath = (ele, first) => {
    const propsDefault = website.menu;

    // 空path处理，path=随机时间戳 2022-1-14 新增
    if (ele[propsDefault.path] == null || ele[propsDefault.path] === "null" || ele[propsDefault.path] === "") {
        ele[propsDefault.path] = ele.id;
        console.log("=========空路由")
    }
    const icon = ele[propsDefault.icon];
    ele[propsDefault.icon] = !icon ? propsDefault.iconDefault : icon;
    ele.meta = ele.meta || {}
    const iframeComponent = 'components/iframe/main';
    const iframeSrc = (href) => {
        return href.replace(/&/g, "#")
    }
    const isChild = ele[propsDefault.children] && ele[propsDefault.children].length !== 0;
    if (!isChild) {
        ele[propsDefault.path] = ele[propsDefault.path]
        if (isURL(ele[propsDefault.path])) {
            let href = ele[propsDefault.path]
            ele.component = iframeComponent
            ele[propsDefault.query] = {url: iframeSrc(href)}
            // 重写路由地址 (A: 使用id当路由  B: 感觉 url 地址生成路由) 当前常用 B
            // A
            // ele[propsDefault.path] = "/" + ele.id
            // B
            let newPath = ele.url.replace("http://", "").replace("https://", "").replaceAll(".", "");
            let paramIndex = newPath.indexOf("?");
            newPath = paramIndex != -1 ? newPath.substring(0, paramIndex) : newPath
            ele[propsDefault.path] = "/" + newPath;
        }
    } else {
        ele[propsDefault.children] && ele[propsDefault.children].forEach(child => {
            if (isURL(child[propsDefault.href])) {
                let href = child[propsDefault.href]
                child.component = iframeComponent
                child[propsDefault.query] = {url: iframeSrc(href)}
            }
            child[propsDefault.path] = `${child[propsDefault.path]}`
            formatPath(child);
        })
    }
}
export default RouterPlugin;