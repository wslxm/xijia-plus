import Layout from '@/page/index/'
import Store from '@/store/'

export default [
    {
        path: '/wel',
        // component: () => Store.getters.isMacOs ? import('@/mac/index.vue') : import('@/page/index/index.vue'),
        component: () => import('@/page/index/index.vue'),
        redirect: 'wel/jvmInfo',
        children: [{
            path: 'index',
            name: '首页',
            meta: {
                i18n: 'dashboard'
            },
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/wel/index')
        }, {
            path: 'more',
            name: '控制台',
            meta: {
                i18n: 'more',
                menu: false,
            },
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/wel/dashboard.vue')
        }, {
            path: 'jvmInfo',
            name: '首页',
            meta: {
                i18n: 'dashboard',
                menu: true, // 是否加载左菜单
                isTab: true, // 是否加载tab
            },
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/wel/jvmInfo.vue')
        }
        ]
    }
    , {
        path: '/form-detail',
        component: Layout,
        children: [{
            path: 'index',
            name: '详情页',
            meta: {
                i18n: 'detail'
            },
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/util/form-detail')
        }]
    }, {
        path: '/info',
        component: Layout,
        redirect: '/info/index',
        children: [{
            path: 'index',
            name: '个人信息',
            meta: {
                i18n: 'info'
            },
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/admin/user/info')
        }, {
            path: 'setting',
            name: '个人设置',
            meta: {
                i18n: 'setting'
            },
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/admin/user/setting')
        }]
    }, {
        path: '/query',
        name: '参数',
        component: Layout,
        meta: {
            i18n: 'params'
        },
        children: [{
            path: ':params',
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/util/params')
        }]
    },
    {
        path: '/test',
        redirect: '/test/index',
        component: Layout,
        children: [{
            name: '测试页面',
            meta: {
                i18n: 'test'
            },
            path: 'index',
            component: () =>
                import( /* webpackChunkName: "views" */ '@/views/util/test')
        }
        ]
    }
    , {
        path: '/gc',
        component: Layout,
        children: [
            {
                path: 'db',
                name: '数据源管理',
                component: () => import('@/views/gc/db/datasource'),
                meta: {
                    menu: true,  // 是否加载左菜单
                    isTab: true, // 是否加载tab
                }
            },
        ]
    }
]