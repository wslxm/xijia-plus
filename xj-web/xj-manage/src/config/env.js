// 环境
// development | production | test
const env = process.env;
// 请求地址
let targetPath = "127.0.0.1:9048";
// websocket 地址统一配置
let baseWebSocketUrl = "ws://" + "127.0.0.1:9048" + "/websocket/{userId}/{fullName}";
// 转发代理地址
let baseProxyPathRewrite = '/api';
// 文件上传地址统一配置
let baseUploadUrl = baseProxyPathRewrite + '/api/open/file/upload?resType=2&filePath=';

// 启动输出
console.log("当前 请求服务器地址: " + targetPath);
console.log("当前 websocket请求地址: " + baseWebSocketUrl);
console.log("当前 转发代理地址: " + baseProxyPathRewrite);
console.log("当前 文件上传地址: " + baseUploadUrl);

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
