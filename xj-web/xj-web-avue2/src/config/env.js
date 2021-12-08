// 配置编译环境和线上环境之间的切换


// 使用代理模式, 使用 /api 进行转发
let baseUrl = '/api';
let baseUploadUrl = baseUrl + '/api/open/aliOssFile/upload?resType=2&filePath=';
//let baseWebSocketUrl = 'ws://127.0.0.1:9048/websocket/{userId}/{fullName}/{head}';
let baseWebSocketUrl = 'ws://xijia.plus/websocket/{userId}/{fullName}/{head}';
// 图片地址配置
let iconfontVersion = ['567566_82imxaft0by'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;
// 未知
let codeUrl = `${baseUrl}/code`


const env = process.env
// if (env.NODE_ENV == 'development') {
//   //baseUrl = `/api`;  // 开发环境地址
// } else if (env.NODE_ENV == 'production') {
//   //baseUrl = `/api`;       // 生产环境地址
// } else if (env.NODE_ENV == 'test') {
//   // baseUrl = ``;  // 测试环境地址
// }

export {
    baseUrl,
    baseUploadUrl,
    baseWebSocketUrl,
    iconfontUrl,
    iconfontVersion,
    codeUrl,
    env,
}