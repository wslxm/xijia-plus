
// development | production | test
const env = process.env;
// 请求地址
let targetPath = env.NODE_ENV === 'development' ? "127.0.0.1:9048" : "xijia.plus";
console.log("当前请求服务器地址: " + targetPath);

// 使用代理模式, 使用 /api 进行转发
let baseProxyPathRewrite = '/api';
// 文件上传地址统一配置
let baseUploadUrl = baseProxyPathRewrite + '/api/open/aliOssFile/upload?resType=2&filePath=';
// websocket 地址统一配置
let baseWebSocketUrl = "ws://" + targetPath + "/websocket/{userId}/{fullName}";

// 阿里图标地址配置
let iconfontVersion = ['567566_82imxaft0by'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;
// 未知
let codeUrl = `${baseProxyPathRewrite}/code`;

module.exports = {
    targetPath,
    baseProxyPathRewrite,
    baseUploadUrl,
    baseWebSocketUrl,
    iconfontUrl,
    iconfontVersion,
    codeUrl,
    env,
};
