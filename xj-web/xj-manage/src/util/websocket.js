// import {webSocketPath} from '@/config/env';
// import {getStore} from '@/util/store'
// import router from '@/router/router'
//
//
// import Vue from 'vue'
// import Vuex from 'vuex'
//
// Vue.use(Vuex)
//
//
// export default {
//
//
//     /* 以下是 websocket 处理*/
//     /**
//      * websocket 连接
//      */
//     initWebSocket: function () {
//         if (typeof (WebSocket) === "undefined") {
//             alert("您的浏览器不支持socket")
//         } else {
//             let webSocketURL = this.getWebsocketUrl();
//             console.log("websocket 请求地址: " + webSocketURL);
//             // 发起连接
//             return new WebSocket(webSocketURL);
//         }
//     },
//
//     /**
//      * 监听消息
//      * @param evt
//      * @param thin
//      */
//     onmessage: function (evt, thin) {
//         // websocket 封装的消息格式
//         // console.log(evt);
//         let received_msg = evt.data;           // 接收到的数据(str)
//         console.log("接收到消息: " + received_msg);
//         let obj = JSON.parse(received_msg);    // 接收到的数据(json)
//         // 解析数据
//         let msgType = obj.msgType;             // 数据类型 (0-心跳检测 1-上线通知 2-下线通知 3 在线用户名单通知 4 推送消息 )
//         let from = obj.from;                   // 来源Id，上下线时为上下线的用户id
//         let username = obj.username;           // 来源用户，上下线时为上下线的用户名
//         let to = obj.to;                       // 目标Id（接收人用户Id,逗号分隔,所有人时为-ALL)）
//         let content = obj.content;             // 内容 (text/json 前后端协商)
//         let extras = obj.extras;               // 扩展内容 (text/json 前后端协商)
//         let createTime = obj.createTime;       // 消息时间
//
//         let loginUserId = getStore({name: "userInfo"}).id;
//         /// console.log("当前用户id: " + loginUserId);
//         if (msgType === 1) {
//             // 上线通知, 只接收发给自己的消息
//             // if (to == loginUserId) {
//             // const h = this.$createElement;
//             // this.$notify({
//             //     offset: 50,
//             //     title: "消息",
//             //     message: h('i', {style: 'color: teal;cursor: pointer;'}, content),
//             //     duration: 1000 * 10,
//             // });
//             // }
//         }
//         if (msgType === 2) {
//             // 下线通知 (主要接收被迫下线通知), 只接收发给自己的消息
//             if (to == loginUserId) {
//                 let loginUserName = getStore({name: "userInfo"}).fullName;
//                 const h = thin.$createElement;
//                 thin.$notify({
//                     offset: 50,
//                     title: "消息",
//                     // message: h('i', {style: 'color: teal;cursor: pointer;'}, content),
//                     message: h('i', {style: 'color: teal;cursor: pointer;'}, content),
//                     duration: 1000 * 10,
//                 });
//             }
//         }
//         // 信息接收通知
//         if (msgType === 4) {
//             console.log("消息查看:" + content);
//             // 此层数据同数据库消息表数据
//             let adminMsgData = JSON.parse(content);
//             // 具体消息内容
//             let msgData = JSON.parse(adminMsgData.content);
//
//             const h = thin.$createElement;
//             // 弹出层展示消息内容
//             thin.$notify({
//                 title: msgData.title,
//                 message: h('i', {style: 'color: teal;cursor: pointer;'}, msgData.message),
//                 duration: 1000 * 10,
//                 onClick: function () {
//                     // 点击弹出层消息, 判断是否需要跳转路由
//                     let routePath = msgData.routePath;
//                     console.log("跳转路由:" + routePath);
//                     if (routePath != null && routePath !== "") {
//                         router.push({path: routePath + "&time=" + new Date().getTime()});
//                     }
//                 }
//             });
//             // 播放提示音
//             this.$refs.audio.currentTime = 0; // 从头开始播放提示音
//             this.$refs.audio.play();          // 播放
//             //this.say2( msgData.content);
//         }
//     },
//     /**
//      * 监听 websocket 连接打开
//      * @param evt
//      * @param thin
//      */
//     onopen: function (thin) {
//         let loginUserName = getStore({name: "userInfo"}).fullName;
//         const h = thin.$createElement;
//         thin.$notify({
//             offset: 50,
//             title: "消息",
//             message: h('i', {style: 'color: teal;cursor: pointer;'}, loginUserName + "【及时通知系统】已连接"),
//             duration: 1000 * 10,
//         });
//         console.log("socket连接成功")
//     },
//
//     /**
//      * 监听 websocket 连接关闭
//      * @param evt
//      * @param thin
//      */
//     onclose: function (thin) {
//         let loginUserName = getStore({name: "userInfo"}).fullName;
//         const h = thin.$createElement;
//         thin.$notify({
//             offset: 50,
//             title: "消息",
//             message: h('i', {style: 'color: teal;cursor: pointer;'}, loginUserName + "【及时通知系统】已离线"),
//             duration: 1000 * 10,
//         });
//         console.log("socket已经关闭")
//     },
//     /**
//      * 监听连接错误
//      * @param evt
//      * @param thin
//      */
//     onerror: function () {
//         console.log("连接错误")
//     },
//
//     /**
//      * 获取 websocket 连接地址
//      * @returns {Promise<void>}
//      */
//     getWebsocketUrl() {
//         // websocket uri
//         let webSocketURI = webSocketPath.replace("{userId}", getStore({name: "userInfo"}).id)
//             .replace("{fullName}", getStore({name: "userInfo"}).fullName)
//
//         // 根据 http 协议判断是否为 ws 或 wss
//         let prot = document.location.protocol == 'http:' ? 'ws:' : 'wss:';
//
//         // 取地址+端口（配置80端口时 port 是空的,所以判断一下）
//         let address = document.location.port ? document.location.hostname + ':' + document.location.port : document.location.hostname;
//
//         // 拼接请求地址
//         let webSocketURL = prot + "//" + address + webSocketURI;
//         return webSocketURL;
//     },
//     /**
//      * 检查 websocket 在线状态
//      * 调用者 this
//      * 调用者 this 需存在 isOnline 参数, 改方法实时变更 isOnline 值 (利用 vue 双向绑定机制), 在线 true / 不在线 false
//      */
//     async checkWebSocketState(thin) {
//         setInterval(() => {
//             // console.log("websocket在线状态:" + thin.socket.readyState)
//             if (thin.socket.readyState === WebSocket.OPEN) {
//                 thin.isOnline = true;
//             } else if (thin.socket.readyState === WebSocket.CLOSED) {
//                 thin.isOnline = false;
//             }
//         }, 2000)
//     }
// }