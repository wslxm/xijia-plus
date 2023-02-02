import {webSocketPath} from '@/config/env';
import {getStore} from '@/util/store'
import {getToken} from '@/util/auth'

/**
 * 获取 websocket 连接地址
 *
 * 需要已登录状态下获取，这里读取了缓存的账号信息
 * @returns {Promise<void>}
 */
function getWebsocketUrl() {
    // websocket uri
    let webSocketURI = webSocketPath.replace("{userId}", getStore({name: "userInfo"}).id)
        .replace("{fullName}", getStore({name: "userInfo"}).fullName)

    // 根据 http 协议判断是否为 ws 或 wss
    let prot = document.location.protocol == 'http:' ? 'ws:' : 'wss:';

    // 取地址+端口（配置80端口时 port 是空的,所以判断一下）
    let address = document.location.port ? document.location.hostname + ':' + document.location.port : document.location.hostname;

    // 拼接请求地址
    let webSocketURL = prot + "//" + address + webSocketURI;
    return webSocketURL;
}


const websocket = {
    state: {
        ws: null, // 建立的连接
        lockReconnect: false,   // 是否真正建立连接
        timeout: 15000,         // 15秒一次心跳
        timeoutObj: null,       // 心跳心跳倒计时
        serverTimeoutObj: null, // 心跳倒计时
        timeoutnum: null,       // 断开 重连倒计时
        msg: null               // 接收到的信息
    },
    getters: {},
    mutations: {
        // 初始化ws 用户登录后调用
        webSocketInit(state) {
            // 判断是否登录
            var token = getToken();
            if (token == null || token == "") {
                throw new Error("没有登录, 无法连接 websocket ")
            }
            let that = this
            // 获取连接地址
            let websocketUrl = getWebsocketUrl();
            // this 创建一个state.ws对象【发送、接收、关闭socket都由这个对象操作】
            state.ws = new WebSocket(websocketUrl);
            state.ws.onopen = function (res) {
                console.log("websocket 连接成功...");
                // 启动心跳检测
                that.commit("start");
            }
            state.ws.onmessage = function (res) {
                var resData = JSON.parse(res.data);
                if (resData.content === "心跳检测") {
                    // 收到服务器信息，心跳重置
                    that.commit("reset");
                } else {
                    // 接收到消息
                    state.msg = res;
                }
            }
            state.ws.onclose = function (e) {
                console.log('websocket 断开: ' + e.code + ' ' + e.reason + ' ' + e.wasClean)
                console.log(e)
                console.log("websocket 连接关闭了, 即将进行断线重连...");
                that.commit('reconnect');
            }
            state.ws.onerror = function (e) {
                console.log('websocket 连接错误: ' + e.code + ' ' + e.reason + ' ' + e.wasClean)
                console.log(e)
                console.log("websocket 连接错误, 即将进行断线重连...");
                that.commit('reconnect');
            }
        },
        /**
         * 关闭 websoccket
         * @author wangsong
         * @mail  1720696548@qq.com
         * @date  2023/2/2 0002 11:44
         * @version 1.0.0
         */
        webSocketOut(state) {
            console.log("websocket 主动关闭连接...");
            state.ws.close();
        },
        /**
         * 断线/ 连接异常 重连
         * @author wangsong
         * @date 2023/2/2 0002 9:24
         * @return
         * @version 1.0.0
         */
        reconnect(state) {
            // 重新连接
            let that = this;
            if (state.lockReconnect) {
                return;
            }
            state.lockReconnect = true;
            // 没连接上会一直重连,30秒重试请求重连，设置延迟避免请求过多
            state.timeoutnum && clearTimeout(state.timeoutnum);
            state.timeoutnum = setTimeout(() => {
                // 新连接
                that.commit('webSocketInit')
                state.lockReconnect = false;
            }, 5000);
        },
        /**
         * 重置心跳检测
         * @author wangsong
         * @date 2023/2/2 0002 9:24
         * @return
         * @version 1.0.0
         */
        reset(state) {
            //重置心跳
            let that = this;
            //清除时间
            clearTimeout(state.timeoutObj);
            clearTimeout(state.serverTimeoutObj);
            //重启心跳
            that.commit('start')
        },
        /**
         * 心跳检测
         * @author wangsong
         * @date 2023/2/2 0002 9:22
         * @return
         * @version 1.0.0
         */
        start(state) {
            //开启心跳
            var self = this;
            state.timeoutObj && clearTimeout(state.timeoutObj);
            state.serverTimeoutObj && clearTimeout(state.serverTimeoutObj);
            state.timeoutObj = setTimeout(() => {
                // 这里发送一个心跳，后端收到后，返回一个心跳消息，
                if (state.ws.readyState === 1) {
                    // 如果连接正常
                    console.log("websocket 心跳检测...");
                    // -1=心跳检测 / 0或无=普通消息
                    let loginUserId = getStore({name: "userInfo"}).id;
                    let loginUsername = getStore({name: "userInfo"}).fullName;
                    let message = {
                        "form": loginUserId,
                        "username": loginUsername,
                        "content": "心跳检测",    // 发送内容
                        "to": loginUserId,       // 接收人用户id
                    };
                    //发送数据
                    state.ws.send(JSON.stringify(message));
                } else {
                    // 否则重连
                    console.log("websocket 断线重连中...");
                    self.commit('reconnect');
                }
                state.serverTimeoutObj = setTimeout(function () {
                    // 超时关闭
                    state.ws.close();
                }, state.timeout);
            }, state.timeout);
        },
    },
    actions: {
        webSocketInit({commit}, url) {
            commit('webSocketInit', url)
        },
        webSocketSend({commit}, p) {
            commit('webSocketSend', p)
        },
        webSocketOut({commit}) {
            commit('webSocketOut')
        }
    }
}

export default websocket;