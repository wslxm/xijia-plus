import {removeToken, setToken} from 'utils/auth'
import {getStore, setStore} from 'utils/store'
import {deepClone} from 'utils/util'
import {getMenu, getTopMenu, getUserInfo, loginByUsername, logout, refeshToken} from '@/api/user'
import {formatPath} from '@/router/avue-router'

const user = {
    state: {
        userInfo: {},
        permission: {},
        roles: [],
        menuId: {},
        menu: getStore({name: 'menu'}) || [],
        menuAll: getStore({name: 'menuAll'}) || [],
        token: getStore({name: 'token'}) || '',
    },
    actions: {
        //根据用户名登录
        LoginByUsername({commit}, userInfo = {}) {
            // const user = encryption({
            //     data: userInfo,
            //     type: 'Aes',
            //     key: 'avue',
            //     param: ['useranme', 'password']
            // });

            return new Promise((resolve) => {
                loginByUsername(userInfo.username, userInfo.password, userInfo.code, userInfo.redomStr).then(res => {
                    const data = res.data.data;
                    commit('SET_TOKEN', res.headers.token);
                    commit('DEL_ALL_TAG', []);
                    commit('CLEAR_LOCK');
                    resolve();
                })
            })
        },
        //根据手机号登录
        LoginByPhone({commit}, userInfo) {
            return new Promise((resolve) => {
                loginByUsername(userInfo.phone, userInfo.code).then(res => {
                    const data = res.data.data;
                    commit('SET_TOKEN', data);
                    commit('DEL_ALL_TAG', []);
                    commit('CLEAR_LOCK');
                    resolve();
                })
            })
        },
        GetUserInfo({commit}) {
            return new Promise((resolve, reject) => {
                getUserInfo().then((res) => {
                    const data = res.data.data;
                    commit('SET_USERIFNO', data);
                    commit('SET_ROLES', data.roles);
                    //commit('SET_PERMISSION',"" )
                    resolve(data);
                }).catch(err => {
                    reject(err);
                })
            })
        },
        //刷新token
        RefeshToken({state, commit}) {
            return new Promise((resolve, reject) => {
                refeshToken(state.refeshToken).then(res => {
                    const data = res.data.data;
                    commit('SET_TOKEN', data);
                    resolve(data);
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 登出
        LogOut({commit}) {
            return new Promise((resolve, reject) => {
                logout().then(() => {
                    commit('SET_TOKEN', '')
                    commit('SET_MENUALL_NULL', []);
                    commit('SET_MENU', [])
                    commit('SET_TAG_LIST', [])
                    commit('SET_ROLES', [])
                    commit('DEL_ALL_TAG', []);
                    commit('CLEAR_LOCK');
                    removeToken()
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },
        //注销session
        FedLogOut({commit}) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                commit('SET_MENUALL_NULL', []);
                commit('SET_MENU', [])
                commit('SET_TAG_LIST', [])
                commit('SET_ROLES', [])
                commit('DEL_ALL_TAG', []);
                commit('CLEAR_LOCK');
                removeToken()
                resolve()
            })
        },
        GetTopMenu() {
            return new Promise(resolve => {
                console.log("获取顶部菜单")
                getTopMenu().then((res) => {
                    console.log("获取顶部菜单" + res)
                    // 处理返回数据成avue格式数据，详见mock/menu.js 文件静态数据
                    const top = [];
                    for (let i = 0; i < res.data.data.length; i++) {
                        let resTopMenu = res.data.data[i];
                        let topMenu = {};
                        topMenu.label = resTopMenu.name;
                        topMenu.path = "";
                        topMenu.icon = resTopMenu.icon;
                        topMenu.meta = {} // i18n: 'dashboard'
                        // 让parentId = 索引
                        topMenu.parentId = i;
                        top.push(topMenu);
                    }
                    const data = top;
                    resolve(data)
                })
            })
        },
        /**
         * 获取系统菜单
         * @author wangsong
         * @param parentId 注意这里的 parentId = 索引
         * @date 2021/9/21 0021 19:02
         * @return
         * @version 1.0.0
         */
        GetMenu({commit}, parentId) {
            console.log("获取左菜单 parentId=" + parentId)
            if (parentId == null) {
                parentId = 0;
            }
            let resDateJson = null;
            return new Promise(resolve => {
                getMenu(null).then((res) => {
                    const resDatas = res.data.data[parentId].menus;
                    var data = getAvueMenuData(resDatas);
                    // avue方法
                    let menu = deepClone(data);
                    menu.forEach(ele => formatPath(ele, true));
                    commit('SET_MENUALL', menu)
                    commit('SET_MENU', menu)
                    resolve(res)
                })
            })
        },
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            setToken(token)
            state.token = token;
            setStore({name: 'token', content: state.token})
        },
        SET_MENUID(state, menuId) {
            state.menuId = menuId;
        },
        SET_USERIFNO: (state, userInfo) => {
            state.userInfo = userInfo;
        },
        SET_MENUALL: (state, menuAll) => {
            let menu = state.menuAll;
            menuAll.forEach(ele => {
                if (!menu.find(item => item.label == ele.label && item.path == ele.path)) {
                    menu.push(ele);
                }
            })
            state.menuAll = menu
            setStore({name: 'menuAll', content: state.menuAll})
        },
        SET_MENUALL_NULL: (state) => {
            state.menuAll = []
            setStore({name: 'menuAll', content: state.menuAll})
        },
        SET_MENU: (state, menu) => {
            state.menu = menu
            setStore({name: 'menu', content: state.menu})
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles;
        },
        SET_PERMISSION: (state, permission) => {
            state.permission = {};
            permission.forEach(ele => {
                state.permission[ele] = true;
            });
        }
    }
}

/**
 * 左菜单/ 处理返回数据成avue格式数据,格式详见 mock/menu.js 文件静态数据
 * @returns {*}
 * @constructor
 */
function getAvueMenuData(resDatas) {
    const data = [];
    for (let i = 0; i < resDatas.length; i++) {
        const menu = {};
        const resData = resDatas[i];
        menu.label = resData.name;
        menu.path = ""
        menu.icon = resData.icon
        menu.iconBgColor = "#8B694B";
        //menu.meta = {i18n: 'cache', keepAlive: false};
        menu.children = [];
        if (resData.menus != null) {
            for (let i = 0; i < resData.menus.length; i++) {
                let menuTwo = {};
                let resDataTwo = resData.menus[i];
                menuTwo.label = resDataTwo.name;
                // 生成一个访问key
                let urls = resDataTwo.url.split('/');
                let newUrl = "";
                for (let j = 0; j < urls.length; j++) {
                    newUrl += urls[j];
                }
                //
                menuTwo.path = newUrl;
                menuTwo.component = resDataTwo.url
                menuTwo.icon = resDataTwo.icon
                menuTwo.iconBgColor = "#8B694B";
                //menuTwo.meta = {i18n: 'cache', keepAlive: false};
                menuTwo.children = [];
                menu.children.push(menuTwo);
            }
        }
        data.push(menu);
    }
    return data;
}

export default user