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
            console.log("---");
            // const user = encryption({
            //   data: userInfo,
            //   type: 'Aes',
            //   key: 'avue',
            //   param: ['useranme', 'password']
            // });
            let password = Base64.encode(userInfo.password);
            //Base64.decode('abcdefg');
            return new Promise((resolve) => {
                loginByUsername(userInfo.username, password, userInfo.code, userInfo.redomStr).then(res => {
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
                commit('SET_TOKEN', '');
                commit('SET_MENUALL_NULL', []);
                commit('SET_MENU', []);
                commit('SET_TAG_LIST', []);
                commit('SET_ROLES', []);
                commit('DEL_ALL_TAG', []);
                commit('CLEAR_LOCK');
                removeToken();
                resolve()
            })
        },
        //注销session
        FedLogOut({commit}) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '');
                commit('SET_MENUALL_NULL', []);
                commit('SET_MENU', []);
                commit('SET_TAG_LIST', []);
                commit('SET_ROLES', []);
                commit('DEL_ALL_TAG', []);
                commit('CLEAR_LOCK');
                removeToken();
                resolve()
            })
        },
        /**
         * 获取顶部菜单
         * @returns {Promise<unknown>}
         * @constructor
         */
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
            console.log("菜单获取", item);
            return new Promise(resolve => {
                //item != null ? item.id : null
                getMenu().then((res) => {
                    let menus = res.data.data;
                    if (menus == null || menus.length === 0) {
                        // 缓存左菜单
                        commit('SET_MENUALL', []);
                        commit('SET_MENU', []);
                        resolve([]);
                        return;
                    }
                    //
                    let newMenus = deepClone(menus);
                    newMenus.forEach(ele => formatPath(ele, true));
                    // 缓存所有菜单数据
                    //setStore({name: 'xj-menus', content: newMenus})
                    if (item != null && item.id) {
                        for (let i = 0; i < newMenus.length; i++) {
                            if (newMenus[i].id === item.id) {
                                // 缓存左菜单
                                commit('SET_MENUALL', newMenus[i].menus);
                                commit('SET_MENU', newMenus[i].menus);
                                resolve(newMenus[i].menus);
                                break;
                            }
                        }
                    } else {
                        // 缓存左菜单
                        commit('SET_MENUALL', newMenus[0].menus);
                        commit('SET_MENU', newMenus[0].menus);
                        resolve(newMenus[0].menus)
                    }
                })
            })
        },
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            setToken(token);
            state.token = token;
        },
        SET_MENUID(state, menuId) {
            state.menuId = menuId;
        },
        SET_USERIFNO: (state, userInfo) => {
            state.userInfo = userInfo;
            setStore({name: 'userInfo', content: userInfo})
        },
        SET_MENUALL: (state, menuAll) => {
            let menu = state.menuAll;
            menuAll.forEach(ele => {
                let index = menu.findIndex(item => item.path == ele.path);
                if (index == -1) {
                    menu.push(ele);
                } else {
                    menu[index] = ele;
                }
            });
            state.menuAll = menu;
            setStore({name: 'menuAll', content: state.menuAll})
        },
        SET_MENUALL_NULL: (state) => {
            state.menuAll = [];
            setStore({name: 'menuAll', content: state.menuAll})
        },
        SET_MENU: (state, menu) => {
            state.menu = menu;
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
};

export default user