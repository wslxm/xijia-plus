import {removeToken, setToken} from '@/util/auth'
import {getStore, setStore} from '@/util/store'
import {deepClone} from '@/util/util'
import {formatPath} from '@/router/avue-router';
import crud from '@/util/crud'
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
        // 根据用户名登录
        LoginByUsername({commit}, userInfo = {}) {
            return new Promise((resolve) => {
                let password = Base64.encode(userInfo.password);
                crud.post("/api/admin/sys/user/login", {
                    username: userInfo.username,
                    password: password,
                    code: userInfo.code,
                    redomStr: userInfo.redomStr,
                }).then(res => {
                    commit('SET_TOKEN', res.headers.token);
                    commit('DEL_ALL_TAG', []);
                    commit('CLEAR_LOCK');
                    resolve(res);
                });
            })
        },
        //根据手机号登录
        LoginByPhone({commit}, userInfo) {
            return new Promise((resolve) => {
                crud.post("/api/admin/sys/user/login", {
                    username: userInfo.phone,
                    password: password,
                    code: userInfo.code,
                    redomStr: userInfo.redomStr,
                }).then(res => {
                    commit('SET_TOKEN', res.headers.token);
                    commit('DEL_ALL_TAG', []);
                    commit('CLEAR_LOCK');
                    resolve(res);
                });
            })
        },
        // 获取用户信息
        GetUserInfo({commit}) {
            return new Promise((resolve, reject) => {
                crud.get("/api/admin/sys/user/findUser").then(res => {
                    const data = res.data.data;
                    commit('SET_USERIFNO', data);
                    commit('SET_ROLES', data.roles);
                    resolve(res);
                });
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
        // 注销session
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
                crud.get("/api/admin/sys/menu/list", {
                    disable: 0,
                    isTree: true,
                    isLoginUser: true,
                    isBottomLayer: false,
                }).then(res => {
                    const data = res.data.data;
                    resolve(data)
                });
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
                crud.get("/api/admin/sys/menu/list", {
                    pid: item.id,
                    disable: 0,
                    isTree: true,
                    isLoginUser: true,
                }).then(res => {
                    let menus = res.data.data;
                    if (menus == null || menus.length === 0) {
                        // 清空
                        commit('SET_MENUALL', []);
                        commit('SET_MENU', []);
                        resolve([]);
                    } else {
                        // 所有菜单
                        let newMenus = deepClone(menus);
                        newMenus.forEach(ele => formatPath(ele, true));
                        // 默认左菜单展示索引0的菜单, 如果是点击的顶部菜单，存在pid,则左菜单展示对应pid的菜单
                        let leftMenus = newMenus[0].menus;
                        if (item != null && item.id) {
                            for (let i = 0; i < newMenus.length; i++) {
                                if (newMenus[i].id === item.id) {
                                    leftMenus = newMenus[i].menus;
                                }
                            }
                        }
                        // 缓存左菜单并返回
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
    }
};

export default user