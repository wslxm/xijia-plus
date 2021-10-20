import {removeToken, setToken} from '@/util/auth'
import {getStore, setStore} from '@/util/store'
import {deepClone} from '@/util/util'
import {getMenu, getTopMenu, getUserInfo, loginByUsername, refeshToken} from '@/api/user'
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
            //   data: userInfo,
            //   type: 'Aes',
            //   key: 'avue',
            //   param: ['useranme', 'password']
            // });
            return new Promise((resolve) => {
                loginByUsername(userInfo.username, userInfo.password, userInfo.code, userInfo.redomStr).then(res => {
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
                    commit('DEL_ALL_TAG');
                    commit('CLEAR_LOCK');
                    resolve();
                })
            })
        },
        GetUserInfo({commit}) {
            return new Promise((resolve, reject) => {
                getUserInfo().then((res) => {
                    const data = res.data.data;
                    // commit('SET_USERIFNO', data.userInfo);
                    // commit('SET_ROLES', data.roles);
                    // commit('SET_PERMISSION', data.permission)
                    commit('SET_USERIFNO', data);
                    commit('SET_ROLES', data.roles);
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
            return new Promise((resolve) => {
                //  logout().then(() => {
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
                getTopMenu().then((res) => {
                    const data = res.data.data;
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
         * @version 1.0.1
         */
        GetMenu({commit}, item) {
            return new Promise(resolve => {
                if (item != null && item.id != null) {
                    // 直接缓存中获取返回
                    let menus = getStore({name: 'xj-menus'}) || [];
                    let newMenus = [];
                    for (let i = 0; i < menus.length; i++) {
                        if (item.id == menus[i].id) {
                            newMenus = menus[i].menus
                            break;
                        }
                    }
                    // 缓存左菜单
                    commit('SET_MENUALL', newMenus)
                    commit('SET_MENU', newMenus)
                    resolve(newMenus);
                } else {
                    getMenu(item.id).then((res) => {
                        let menus = res.data.data;
                        let newMenus = deepClone(menus);
                        newMenus.forEach(ele => formatPath(ele, true));
                        // 缓存所有菜单数据
                        setStore({name: 'xj-menus', content: newMenus})
                        // 缓存左菜单
                        commit('SET_MENUALL', newMenus[0].menus)
                        commit('SET_MENU', newMenus[0].menus)
                        resolve(newMenus[0].menus)
                    })
                }
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
 * 左菜单/处理返回数据成avue格式数据
 * @returns {*}
 * @constructor
 */
// function nextMenu(menus) {
//     // 一级菜单
//     for (let i = 0; i < menus.length; i++) {
//         const menu = menus[i]
//         menu.label = menu.name;
//         menu.icon = menu.icon
//         menu.component = menu.url
//         menu.iconBgColor = "#8B694B";
//         //menu.meta = {i18n: 'cache', keepAlive: false};
//         // 根据路径删除访问路径
//         menu.path = ""
//         if (menu.url != null && menu.url != '') {
//             menu.path = menu.url
//         }
//         menu.children = menu.menus;
//         if (menu.children != null && menu.children.length > 0) {
//             nextMenu(menu.children);
//         }
//     }
// }

export default user